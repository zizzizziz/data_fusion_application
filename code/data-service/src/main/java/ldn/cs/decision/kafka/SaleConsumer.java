package ldn.cs.decision.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.sale.SalePrediction;
import ldn.cs.decision.dao.SaleDecisionDao;
import ldn.cs.decision.pojo.sale.Sale;
import ldn.cs.decision.utils.ComputeFutureTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SaleConsumer {
    @Autowired
    private SaleDecisionDao saleDecisionDao;

    // 历史数据, 等待生成
    private static final List<Sale> historyData = loadHistoryDataFromResource();
    private static List<Sale> loadHistoryDataFromResource() {
        List<Sale> historyData = new ArrayList<>();
        try {
            InputStream inputStream = SaleConsumer.class.getResourceAsStream("/train/sale.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Sale sale = JSON.parseObject(line, Sale.class);
                    historyData.add(sale);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    @KafkaListener(topics = "topic_sale_message", groupId = "topic_sale_message_group")
    public void consumeSale(String message) {
        Sale sale = JSONObject.parseObject(message, Sale.class);
        List<Sale> predictionResult = new ArrayList<>();
        predictionResult.add(sale);

        // 获取当前时间
        long eventTime = sale.getEventTime();

        // 分别按照年、季度、月进行预测
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getNextFourYearsTimestamps(eventTime), sale));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingQuartersTimestamps(eventTime), sale));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingMonthsTimestamps(eventTime), sale));

        saleDecisionDao.addSalePredictionInfos(predictionResult);
    }

    private List<Sale> predictForPeriods(Map<Integer, List<Long>> timestamps, Sale currentSale) {
        List<Sale> result = new ArrayList<>();
        for (List<Long> time : timestamps.values()) {
            Long nextTime = time.get(0);
            Sale nextSale = SalePrediction.getInstance().getNextSale(historyData, currentSale, nextTime);
            result.add(nextSale);
        }
        return result;
    }
}

