package ldn.cs.decision.kafka;


import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.staff.StaffPrediction;
import ldn.cs.decision.dao.StaffDecisionDao;
import ldn.cs.decision.pojo.staff.Staff;
import ldn.cs.decision.utils.ComputeFutureTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StaffConsumer {
    @Autowired
    private StaffDecisionDao staffDecisionDao;

    // 历史数据, 等待生成
    private static final List<Staff> historyData = new ArrayList<>();
    static {
        historyData.add(new Staff(1, "小丫家电", 1, 2, "后台开发", 1, 1688268377, 1688268377));
        historyData.add(new Staff(1, "小丫家电", 1, 2, "后台开发", 2, 1685676377, 1685676377));
        historyData.add(new Staff(1, "小丫家电", 1, 2, "后台开发", 3, 1659410777, 1659410777));
    }

    @KafkaListener(topics = "topic_staff_message", groupId = "topic_staff_message_group")
    public void consumeStaff(String message) {
        Staff staff = JSONObject.parseObject(message, Staff.class);
        List<Staff> predictionResult = new ArrayList<>();
        predictionResult.add(staff);

        // 获取当前时间
        long eventTime = staff.getEventTime();

        // 分别按照年、季度、月进行预测
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getNextFourYearsTimestamps(eventTime), staff));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingQuartersTimestamps(eventTime), staff));
        predictionResult.addAll(predictForPeriods(ComputeFutureTime.getRemainingMonthsTimestamps(eventTime), staff));

        staffDecisionDao.addStaffPredictionInfos(predictionResult);
    }

    private List<Staff> predictForPeriods(Map<Integer, List<Long>> timestamps, Staff currentStaff) {
        List<Staff> result = new ArrayList<>();
        for (List<Long> time : timestamps.values()) {
            Long nextTime = time.get(0);
            Staff nextStaff = StaffPrediction.getInstance().getNextStaff(historyData, currentStaff, nextTime);
            result.add(nextStaff);
        }
        return result;
    }
}

