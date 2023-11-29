package ldn.cs.access.utils;


import ldn.cs.fusion.service.DataExtractionService;
import ldn.cs.fusion.service.DataFusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataFusion {
    @Autowired
    private DataFusionService dataFusionService;

    @Autowired
    private DataExtractionService dataExtractionService;

    public void fusion(Map<String, Object> fusionObject){
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
