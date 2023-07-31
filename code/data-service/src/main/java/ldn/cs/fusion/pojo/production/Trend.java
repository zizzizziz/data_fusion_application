package ldn.cs.fusion.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.AutomapConstructor;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trend {
    private int id;

    private String corporation;

    private Date months; //mysql里month()会返回int

    private long eventTime;

    private long updateTime;
}
