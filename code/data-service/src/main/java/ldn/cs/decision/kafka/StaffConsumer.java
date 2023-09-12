package ldn.cs.decision.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ldn.cs.decision.alghthrims.staff.StaffPrediction;
import ldn.cs.decision.dao.StaffDecisionDao;
import ldn.cs.decision.pojo.staff.Staff;
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
public class StaffConsumer {
    @Autowired
    private StaffDecisionDao staffDecisionDao;

    // 历史数据, 等待生成
    private static final List<Staff> historyData = loadHistoryDataFromResource();
    private static List<Staff> loadHistoryDataFromResource() {
        List<Staff> historyData = new ArrayList<>();
        try {
            InputStream inputStream = StaffConsumer.class.getResourceAsStream("/train/staff.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Staff staff = JSON.parseObject(line, Staff.class);
                    historyData.add(staff);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyData;
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

