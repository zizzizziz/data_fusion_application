REPLACE INTO tbl_decision_threshold_info(categories, attributes, attributesValue, updateTime)
VALUES (1, '人员数量', 100, 1690946777),
       (2, '研发费用', 2500, 1690946777),
       (2, '设备费用', 2500, 1690946777),
       (2, '生产费用', 2500, 1690946777),
       (2, '仓储费用', 2500, 1690946777),
       (2, '物料费用', 2500, 1690946777),
       (2, '运输费用', 2500, 1690946777),
       (2, '人工费用', 2500, 1690946777),
       (2, '利润', 2500, 1690946777),
       (2, '流动资产', 2500, 1690946777),
       (2, '融资', 2500, 1690946777),
       (3, '运输数量', 300, 1690946777),
       (3, '运输剩余库存', 600, 1690946777),
       (3, '运输里程数', 3500, 1690946777),
       (3, '运输费用', 2500, 1690946777),
       (4, '生产产量', 100, 1690946777),
       (4, '生产费用', 2300, 1690946777),
       (5, '产品销量', 200, 1690946777),
       (5, '销售营收', 17000, 1690946777),
       (5, '销售费用', 1750, 1690946777),
       (5, '产品库存', 100, 1690946777),
       (5, '销售服务', 100, 1690946777);

-- 公司表
REPLACE INTO tbl_optimized_corporation_info (corporation)
SELECT corporation FROM tbl_staff_skill_info GROUP BY corporation;

-- 优化员工技能表
REPLACE INTO tbl_optimized_staff_info (corporation, skill, amount)
SELECT corporation, skill, SUM(amount) as amount FROM tbl_staff_skill_info GROUP BY corporation, skill;

-- 优化产品表Product
REPLACE INTO tbl_optimized_product_info (corporation, types, cost, province, quantity, updateTime)
SELECT
    corporation,
    types,
    SUM(cost) AS cost,
    province,
    SUM(quantity) AS quantity,
    MAX(updateTime) AS updateTime
FROM
    tbl_production_info
GROUP BY
    corporation, types, province;


-- 销售表
REPLACE INTO tbl_optimized_sales_detail_info (corporation, types, quantity, income, province, inventory, updateTime)
SELECT
    corporation,
    types,
    SUM(quantity) as quantity,
    SUM(income) as income,
    province,
    SUM(inventory) as inventory,
    MAX(updateTime) as updateTime
FROM tbl_sale_info
GROUP BY corporation, types, province;

-- 物流表
REPLACE INTO tbl_optimized_convey_info (corporation, types, categories, cost, transportVolume, updateTime)
SELECT
    corporation,
    types,
    categories,
    SUM(cost) as cost,
    SUM(quantity) as transportVolume,
    MAX(updateTime) as updateTime
FROM tbl_convey_info
GROUP BY corporation, types, categories;