package ldn.cs.fusion.pojo.wealth;

import ldn.cs.fusion.pojo.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthInfo {
    private List<Wealth> wealths;

    private int total;
}
