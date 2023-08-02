package ldn.cs.decision.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffMeasureInfo extends StaffWarningInfo{
    private String measure;
}
