package ldn.cs.decision.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.wealth.WealthPrediction;
import ldn.cs.decision.dao.WealthDecisionDao;
import ldn.cs.decision.pojo.wealth.Wealth;
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
public class WealthConsumer {
    @Autowired
    private WealthDecisionDao wealthDecisionDao;

    // 历史数据, 等待生成
    private static final List<Wealth> historyData = loadHistoryDataFromResource();
    private static List<Wealth> loadHistoryDataFromResource() {
        List<Wealth> historyData = new ArrayList<>();
        try {
            InputStream inputStream = WealthConsumer.class.getResourceAsStream("/train/wealth.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Wealth wealth = JSON.parseObject(line, Wealth.class);
                    historyData.add(wealth);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    @KafkaListener(topics = "topic_wealth_message", groupId = "topic_wealth_message_group")
    public void consumeWealth(String message) {
        Wealth wealth = JSONObject.parseObject(message, Wealth.class);
        List<Wealth> predictionResult = new ArrayList<>();
        predictionResult.add(wealth);

        // 获取当前时间
        long eventTime = wealth.getEventTime();

        // 分别按照年、季度、月进行预测
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getNextFourYearsTimestamps(eventTime), wealth));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingQuartersTimestamps(eventTime), wealth));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingMonthsTimestamps(eventTime), wealth));

        wealthDecisionDao.addWealthPredictionInfos(predictionResult);
    }

    private List<Wealth> predictForPeriods(Map<Integer, List<Long>> timestamps, Wealth currentWealth) {
        List<Wealth> result = new ArrayList<>();
        for (List<Long> time : timestamps.values()) {
            Long nextTime = time.get(0);
            Wealth nextWealth = WealthPrediction.getInstance().getNextWealth(historyData, currentWealth, nextTime);
            result.add(nextWealth);
        }
        return result;
    }
}

