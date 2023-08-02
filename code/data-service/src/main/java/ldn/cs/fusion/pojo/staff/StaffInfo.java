package ldn.cs.fusion.pojo.staff;

import ldn.cs.fusion.pojo.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInfo {
    private List<Staff> staffs;

    private int total;
}
