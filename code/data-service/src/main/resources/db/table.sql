-- 设备
create table if not exists tbl_device_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    ip varchar(32) NOT NULL,
    port int NOT NULL,
    updateTime bigint,
    KEY (id),
    PRIMARY KEY (ip, port)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 原始数据
create table if not exists tbl_original_info(
    id bigint NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    staffCategories tinyint,    -- 员工类型
    positions tinyint,          -- 员工职位
    skill varchar(255),         -- 员工技能
    amount bigint,              -- 员工数量
    research decimal(20,2),            -- 研发支出
    device decimal(20,2),              -- 设备支出
    production decimal(20,2),          -- 生产支出
    storage decimal(20,2),             -- 仓储支出
    materiel decimal(20,2),            -- 物料支出
    transportation decimal(20,2),      -- 运输支出
    salary decimal(20,2),              -- 人员工资支出
    revenue decimal(20,2),             -- 总收入
    profit decimal(20,2),              -- 利润
    fixedAssets decimal(20,2),         -- 固定资产
    cashAssets decimal(20,2),          -- 流动资产
    finance decimal(20,2),             -- 融资
    conveyCategories tinyint,       -- 运输工具
    conveyTypes tinyint,            -- 运输货物
    conveyQuantity decimal(20,2),   -- 运输量
    conveyInventory decimal(20,2),  -- 运输剩余库存
    mileage decimal(20,2),          -- 运输里程数
    conveyCost decimal(20,2),       -- 运输费用
    saleCategories tinyint,       -- 产品类型
    saleTypes tinyint,            -- 销售产品
    saleQuantity decimal(20,2),   -- 销量
    income decimal(20,2),         -- 销售营收
    saleCost decimal(20,2),       -- 销售支出
    saleProvince varchar(255),    -- 销售省份
    saleCountry varchar(255),     -- 销售国家
    saleInventory decimal(20,2),  -- 销售库存
    score int,                    -- 服务评价
    productionCategories tinyint,       -- 产品类型
    productionTypes tinyint,            -- 生产产品
    productionQuantity decimal(20,2),   -- 生产产品产量
    productionCost decimal(20,2),       -- 生产产品费用
    productionProvince varchar(255),    -- 生产产品省份
    productionCountry varchar(255),     -- 生产产品国家
    quality int,                        -- 生产产品质量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 人力链报表
create table if not exists tbl_staff_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 员工类型
    positions tinyint,          -- 员工职位
    skill varchar(255),         -- 员工技能
    amount bigint,              -- 员工数量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 人力链企业员工分布
create table if not exists tbl_staff_person_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 员工类型
    amount bigint,              -- 员工数量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 人力链企业员工职位分布
create table if not exists tbl_staff_position_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    positions tinyint,          -- 员工职位
    amount bigint,              -- 员工数量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 人力链企业员工技能分布
create table if not exists tbl_staff_skill_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    skill varchar(255),         -- 员工技能
    amount bigint,              -- 员工数量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链报表
create table if not exists tbl_wealth_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),          -- 企业名称
    research decimal(20,2),            -- 研发支出
    device decimal(20,2),              -- 设备支出
    production decimal(20,2),          -- 生产支出
    storage decimal(20,2),             -- 仓储支出
    materiel decimal(20,2),            -- 物料支出
    transportation decimal(20,2),      -- 运输支出
    salary decimal(20,2),              -- 人员工资支出
    revenue decimal(20,2),             -- 总收入
    profit decimal(20,2),              -- 利润
    fixedAssets decimal(20,2),         -- 固定资产
    cashAssets decimal(20,2),          -- 流动资产
    finance decimal(20,2),             -- 融资
    eventTime bigint,                  -- 事件时间
    updateTime bigint,                 -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链企业财务分布
create table if not exists tbl_wealth_finance_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),          -- 企业名称
    research decimal(20,2) ,           -- 研发支出
    device decimal(20,2),              -- 设备支出
    production decimal(20,2),          -- 生产支出
    storage decimal(20,2),             -- 仓储支出
    materiel decimal(20,2),            -- 物料支出
    transportation decimal(20,2),      -- 运输支出
    salary decimal(20,2),              -- 人员工资支出
    revenue decimal(20,2),             -- 总收入
    eventTime bigint,                  -- 事件时间
    updateTime bigint,                 -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链企业内部资产分布
create table if not exists tbl_wealth_asset_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    profit decimal(20,2),       -- 利润
    fixedAssets decimal(20,2),  -- 固定资产
    cashAssets decimal(20,2),   -- 流动资产
    finance decimal(20,2),      -- 融资
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 物流链报表
create table if not exists tbl_convey_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 运输工具
    types tinyint,              -- 运输货物
    quantity decimal(20,2),     -- 运输量
    inventory decimal(20,2),    -- 运输剩余库存
    mileage decimal(20,2),      -- 运输里程数
    cost decimal(20,2),         -- 运输费用
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 物流链企业运输类型分布
create table if not exists tbl_convey_traffic_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 运输工具
    mileage decimal(20,2),      -- 运输里程数
    cost decimal(20,2),         -- 运输费用
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 物流链企业货物库存分布
create table if not exists tbl_convey_inventory_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    types tinyint,              -- 运输货物
    quantity decimal(20,2),     -- 运输量
    inventory decimal(20,2),           -- 运输剩余库存
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 生产链报表
create table if not exists tbl_production_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity decimal(20,2),     -- 生产产品产量
    cost decimal(20,2),         -- 生产产品费用
    province varchar(255),      -- 生产产品省份
    country varchar(255),       -- 生产产品国家
    quality int,                -- 生产产品质量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 生产链产量分布
create table if not exists tbl_production_yield_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity decimal(20,2),     -- 生产产品产量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 生产链产地分布
create table if not exists tbl_production_birth_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity decimal(20,2),     -- 生产产品产量
    province varchar(255),      -- 生产产品省份
    country varchar(255),       -- 生产产品国家
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 生产链整体产量
create table if not exists tbl_production_trend_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity decimal(20,2),     -- 生产产品产量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链报表
create table if not exists tbl_sale_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    income decimal(20,2),       -- 销售营收
    cost decimal(20,2),         -- 销售支出
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    inventory decimal(20,2),    -- 销售库存
    score int,                  -- 服务评价
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链整体销量
create table if not exists tbl_sale_trend_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    income decimal(20,2),       -- 销售营收
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链销量分布
create table if not exists tbl_sale_count_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链销地分布
create table if not exists tbl_sale_birth_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链出口分布
create table if not exists tbl_sale_export_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    country varchar(255),       -- 销售国家
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链销售收入分布
create table if not exists tbl_sale_profit_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    income decimal(20,2),       -- 销售营收
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 决策元阈值管理模块
create table if not exists tbl_decision_threshold_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    categories tinyint,                 -- 类型, 1-5分别对应人财物产销
    attributes varchar(255),            -- 阈值属性
    attributesValue decimal(20, 2),     -- 阈值
    updateTime bigint,                  -- 更新时间
    KEY (id),
    PRIMARY KEY(categories, attributes)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 人力链预测报表数据
create table if not exists tbl_staff_prediction_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 员工类型
    positions tinyint,          -- 员工职位
    skill varchar(255),         -- 员工技能
    amount bigint,              -- 员工数量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链预测报表
create table if not exists tbl_wealth_prediction_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),          -- 企业名称
    research decimal(20,2),            -- 研发支出
    device decimal(20,2),              -- 设备支出
    production decimal(20,2),          -- 生产支出
    storage decimal(20,2),             -- 仓储支出
    materiel decimal(20,2),            -- 物料支出
    transportation decimal(20,2),      -- 运输支出
    salary decimal(20,2),              -- 人员工资支出
    revenue decimal(20,2),             -- 总收入
    profit decimal(20,2),              -- 利润
    fixedAssets decimal(20,2),         -- 固定资产
    cashAssets decimal(20,2),          -- 流动资产
    finance decimal(20,2),             -- 融资
    eventTime bigint,                  -- 事件时间
    updateTime bigint,                 -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_convey_prediction_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 运输工具
    types tinyint,              -- 运输货物
    quantity decimal(20,2),     -- 运输量
    inventory decimal(20,2),           -- 运输剩余库存
    mileage decimal(20,2),      -- 运输里程数
    cost decimal(20,2),         -- 运输费用
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_production_prediction_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity decimal(20,2),     -- 生产产品产量
    cost decimal(20,2),         -- 生产产品费用
    province varchar(255),      -- 生产产品省份
    country varchar(255),       -- 生产产品国家
    quality int,                -- 生产产品质量
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链预测报表

create table if not exists tbl_sale_prediction_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity decimal(20,2),     -- 销量
    income decimal(20,2),       -- 销售营收
    cost decimal(20,2),         -- 销售支出
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    inventory decimal(20,2),    -- 销售库存
    score int,                  -- 服务评价
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 阈值信息表
create table if not exists tbl_optimized_threshold_info(
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    corporation VARCHAR(255) COMMENT '企业名称',
    attribute varchar(20) COMMENT '属性',
    attributeValue double COMMENT '属性值',
    optimizationType tinyint COMMENT '优化类型 1:成本优先 2:质量优先 3:服务优先 4：效率优先',
    updateTime bigint DEFAULT null COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 优化公司表
CREATE TABLE IF NOT EXISTS tbl_optimized_corporation_info (
    id INT AUTO_INCREMENT,
    corporation VARCHAR(255),
    updateTime BIGINT,
    KEY (id),
    PRIMARY KEY (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 优化员工技能表
CREATE TABLE IF NOT EXISTS tbl_optimized_staff_info (
    id INT AUTO_INCREMENT,
    corporation VARCHAR(255),
    skill VARCHAR(255),
    amount BIGINT,
    updateTime BIGINT,
    KEY (id),
    PRIMARY KEY (corporation, skill)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 优化产品表Product
CREATE TABLE IF NOT EXISTS tbl_optimized_product_info (
    id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    corporation VARCHAR(255) COMMENT '企业名称',
    types TINYINT COMMENT '产品类型',
    cost decimal(20,2) COMMENT '生产产品费用',
    province VARCHAR(255) COMMENT '生产产品省份',
    quantity decimal(20,2) COMMENT '产品产量',
    updateTime BIGINT COMMENT '更新时间',
    key (id),
    PRIMARY KEY (corporation, types, province),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT='产品信息表';

-- 销售表
CREATE TABLE IF NOT EXISTS tbl_optimized_sales_detail_info (
    id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    corporation VARCHAR(255) COMMENT '企业名称',
    types TINYINT COMMENT '产品类型',
    quantity decimal(20,2) COMMENT '产品销量',
    income decimal(20,2) COMMENT '销售营收',
    province VARCHAR(255) COMMENT '销售省份',
    inventory decimal(20,2) COMMENT '销售库存',
    updateTime BIGINT DEFAULT NULL COMMENT '更新时间',
    key (id),
    PRIMARY KEY (corporation, types, province)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售详情表';

-- 物流表
CREATE TABLE IF NOT EXISTS tbl_optimized_convey_info (
    id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    corporation VARCHAR(255) COMMENT '企业名称',
    types TINYINT COMMENT '运输货物',
    categories TINYINT COMMENT '产品运输工具',
    cost decimal(20,2) COMMENT '产品运输费用',
    transportVolume decimal(20,2) COMMENT '产品运输量',
    updateTime BIGINT DEFAULT NULL COMMENT '更新时间',
    key (id),
    PRIMARY KEY (corporation, types)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流表';