package ldn.cs.decision.kafka;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import ldn.cs.decision.alghthrims.convey.ConveyPrediction;
import ldn.cs.decision.dao.ConveyDecisionDao;
import ldn.cs.decision.pojo.convey.Convey;
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
public class ConveyConsumer {
    @Autowired
    private ConveyDecisionDao conveyDecisionDao;

    // 历史数据, 等待生成
    private static final List<Convey> historyData = loadHistoryDataFromResource();
    private static List<Convey> loadHistoryDataFromResource() {
        List<Convey> historyData = new ArrayList<>();
        try {
            InputStream inputStream = ConveyConsumer.class.getResourceAsStream("/train/convey.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Convey convey = JSON.parseObject(line, Convey.class);
                    historyData.add(convey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    @KafkaListener(topics = "topic_convey_message", groupId = "topic_convey_message_group")
    public void consumeConvey(String message) {
        Convey convey = JSONObject.parseObject(message, Convey.class);
        List<Convey> predictionResult = new ArrayList<>();
        predictionResult.add(convey);

        // 获取当前时间
        long eventTime = convey.getEventTime();

        // 分别按照年、季度、月进行预测
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getNextFourYearsTimestamps(eventTime), convey));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingQuartersTimestamps(eventTime), convey));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingMonthsTimestamps(eventTime),convey));

        conveyDecisionDao.addConveyPredictionInfos(predictionResult);
    }

    private List<Convey> predictForPeriods(Map<Integer, List<Long>> timestamps, Convey currentConvey) {
        List<Convey> result = new ArrayList<>();
        for (List<Long> time : timestamps.values()) {
            Long nextTime = time.get(0);
            Convey nextConvey = ConveyPrediction.getInstance().getNextConvey(historyData, currentConvey, nextTime);
            result.add(nextConvey);
        }
        return result;
    }
}




