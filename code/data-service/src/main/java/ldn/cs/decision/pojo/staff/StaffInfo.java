package ldn.cs.decision.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInfo {
    private List<Staff> staff;

    private int total;
}
