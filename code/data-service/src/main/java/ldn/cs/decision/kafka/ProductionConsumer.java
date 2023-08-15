package ldn.cs.decision.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.production.ProductionPrediction;
import ldn.cs.decision.dao.ProductionDecisionDao;
import ldn.cs.decision.pojo.production.Production;
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
public class ProductionConsumer {
    @Autowired
    private ProductionDecisionDao productionDecisionDao;

    // 历史数据, 等待生成
    private static final List<Production> historyData = loadHistoryDataFromResource();
    private static List<Production> loadHistoryDataFromResource() {
        List<Production> historyData = new ArrayList<>();
        try {
            InputStream inputStream = ProductionConsumer.class.getResourceAsStream("/train/production.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Production production = JSON.parseObject(line, Production.class);
                    historyData.add(production);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    @KafkaListener(topics = "topic_production_message", groupId = "topic_production_message_group")
    public void consumeProduction(String message) {
        Production production = JSONObject.parseObject(message, Production.class);
        List<Production> predictionResult = new ArrayList<>();
        predictionResult.add(production);

        // 获取当前时间
        long eventTime = production.getEventTime();

        // 分别按照年、季度、月进行预测
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getNextFourYearsTimestamps(eventTime), production));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingQuartersTimestamps(eventTime), production));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingMonthsTimestamps(eventTime), production));

        productionDecisionDao.addProductionPredictionInfos(predictionResult);
    }

    private List<Production> predictForPeriods(Map<Integer, List<Long>> timestamps, Production currentProduction) {
        List<Production> result = new ArrayList<>();
        for (List<Long> time : timestamps.values()) {
            Long nextTime = time.get(0);
            Production nextProduction = ProductionPrediction.getInstance().getNextProduction(historyData, currentProduction, nextTime);
            result.add(nextProduction);
        }
        return result;
    }
}

