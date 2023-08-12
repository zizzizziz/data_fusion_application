package ldn.cs.decision.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionMeasureInfo extends ProductionWarningInfo {
    private String measure;
}
