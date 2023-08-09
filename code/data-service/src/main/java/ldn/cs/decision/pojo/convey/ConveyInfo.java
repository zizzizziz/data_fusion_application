package ldn.cs.decision.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConveyInfo {
    private List<Convey> convey;

    private int total;
}
