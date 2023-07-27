package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleInfo {
    private List<Sale> sales;

    private int total;
}
