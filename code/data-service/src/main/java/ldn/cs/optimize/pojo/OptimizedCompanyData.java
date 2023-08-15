package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedCompanyData {
    private Map<String, List<OptimizedThreshold>> thresholdMap;

    private Map<String, List<OptimizedConvey>> conveyMap;

    private Map<String, List<OptimizedCorporation>> corporationMap;

    private Map<String, List<OptimizedProduct>> productMap;

    private Map<String, List<OptimizedSalesDetail>> salesDetailMap;

    private Map<String, List<OptimizedStaff>> staffMap;
}
