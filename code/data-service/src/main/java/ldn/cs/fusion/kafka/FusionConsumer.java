package ldn.cs.fusion.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import ldn.cs.access.service.OriginalService;
import ldn.cs.access.utils.DataFusion;
import ldn.cs.fusion.service.DataExtractionService;
import ldn.cs.fusion.service.DataFusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class FusionConsumer {

    @Autowired
    private DataFusion dataFusion;

    @Autowired
    private OriginalService originalService;

    @KafkaListener(topics = "topic_data_service_original_message", groupId = "topic_data_service_original_message_group")
    public void consumer(String message) {
        if (!StringUtils.hasLength(message)) {
            return;
        }
        // 将Message转化为Map对象
        Map<String, Object> fusionObject = JSON.parseObject(message, new TypeReference<Map<String, Object>>() {});
        dataFusion.fusion(fusionObject);
        originalService.addOriginalInfos(fusionObject);
    }
}

