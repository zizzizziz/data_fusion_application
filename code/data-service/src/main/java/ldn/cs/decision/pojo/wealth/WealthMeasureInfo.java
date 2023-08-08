package ldn.cs.decision.pojo.wealth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthMeasureInfo extends WealthWarningInfo {
    private String measure;
}
