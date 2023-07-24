package ldn.cs.fusion.pojo;

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
