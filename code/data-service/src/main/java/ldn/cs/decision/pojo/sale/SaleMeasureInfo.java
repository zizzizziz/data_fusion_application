package ldn.cs.decision.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleMeasureInfo extends SaleWarningInfo {
    private String measure;
}
