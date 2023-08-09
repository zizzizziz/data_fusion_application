package ldn.cs.fusion.kafka;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
    private DataExtractionService dataExtractionService;

    @Autowired
    private DataFusionService dataFusionService;

    @KafkaListener(topics = "topic_data_service_original_message", groupId = "topic_data_service_original_message_group")
    public void consumer(String message) {
        if (!StringUtils.hasLength(message)) {
            return;
        }
        // 1. 将Message转化为Map对象
        Map<String, Object> fusionObject = JSON.parseObject(message, new TypeReference<Map<String, Object>>() {});
        // 2. 数据感知中的人财物产销对应的功能模型提取对应的属性, 提取到了之后进行入库
        dataExtractionService.PersonPerception(fusionObject);
        dataExtractionService.PositionPerception(fusionObject);
        dataExtractionService.SkillPerception(fusionObject);
        dataExtractionService.AssetPerception(fusionObject);
        dataExtractionService.FinancePerception(fusionObject);
        dataExtractionService.InventoryPerception(fusionObject);
        dataExtractionService.TrafficPerception(fusionObject);
        dataExtractionService.TrendPerception(fusionObject);
        dataExtractionService.BirthPerception(fusionObject);
        dataExtractionService.YieldPerception(fusionObject);
        dataExtractionService.SaleTrendPerception(fusionObject);
        dataExtractionService.SaleCountPerception(fusionObject);
        dataExtractionService.ExportPerception(fusionObject);
        dataExtractionService.SaleBirthPerception(fusionObject);
        dataExtractionService.ProfitPerception(fusionObject);
        // 3. 数据融合中的人财物产销对应的属性，提取到了之后就入库, 然后再把这条消息发给安玲。
        dataFusionService.StaffFusion(fusionObject);
        dataFusionService.WealthFusion(fusionObject);
        dataFusionService.ConveyFusion(fusionObject);
        dataFusionService.ProductionFusion(fusionObject);
        dataFusionService.SaleFusion(fusionObject);
    }
}

