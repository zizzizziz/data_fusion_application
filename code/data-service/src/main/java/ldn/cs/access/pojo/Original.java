package ldn.cs.access.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Original {

    private long id;

    private String corporation;

    private int staffCategories;

    private int positions;

    private String skill;

    private long amount;

    private BigDecimal research;

    private BigDecimal device;

    private BigDecimal production;

    private BigDecimal storage;

    private BigDecimal materiel;

    private BigDecimal transportation;

    private BigDecimal salary;

    private BigDecimal revenue;

    private BigDecimal profit;

    private BigDecimal fixedAssets;

    private BigDecimal cashAssets;

    private BigDecimal finance;

    private int conveyCategories;

    private int conveyTypes;

    private BigDecimal conveyQuantity;

    private BigDecimal conveyInventory;

    private BigDecimal mileage;

    private BigDecimal conveyCost;

    private int saleCategories;

    private int saleTypes;

    private BigDecimal saleQuantity;

    private BigDecimal income;

    private BigDecimal saleCost;

    private String saleProvince;

    private String saleCountry;

    private BigDecimal saleInventory;

    private int productionCategories;

    private int productionTypes;

    private BigDecimal productionQuantity;

    private BigDecimal productionCost;

    private String productionProvince;

    private String productionCountry;

    private int quality;

    private int score;

    private long eventTime;

    private long updateTime;

}
