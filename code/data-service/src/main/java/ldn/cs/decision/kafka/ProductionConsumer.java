package ldn.cs.decision.kafka;


import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.production.ProductionPrediction;
import ldn.cs.decision.dao.ProductionDecisionDao;
import ldn.cs.decision.pojo.production.Production;
import ldn.cs.decision.utils.ComputeFutureTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductionConsumer {
    @Autowired
    private ProductionDecisionDao productionDecisionDao;

    // 历史数据, 等待生成
    private static final List<Production> historyData = new ArrayList<>();
    static {
        historyData.add(new Production(1,"小丫家电", 1, 1, BigDecimal.valueOf(200), BigDecimal.valueOf(2000), "中国", "广东", 1, 1666945270, 1666945270));
        historyData.add(new Production(2,"小丫家电", 1, 2, BigDecimal.valueOf(203), BigDecimal.valueOf(2000), "中国", "上海", 1, 1666945270, 1666945270));
        historyData.add(new Production(3,"小丫家电", 1, 3, BigDecimal.valueOf(100), BigDecimal.valueOf(2000), "中国", "福建", 1, 1666945270, 1666945270));
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

