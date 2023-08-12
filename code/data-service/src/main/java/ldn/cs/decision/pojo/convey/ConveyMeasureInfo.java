package ldn.cs.decision.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConveyMeasureInfo extends ConveyWarningInfo {
    private String measure;
}
