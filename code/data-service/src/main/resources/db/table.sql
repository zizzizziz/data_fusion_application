-- 设备
create table if not exists tbl_device_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    ip varchar(32) NOT NULL,
    port int NOT NULL,
    updateTime bigint,
    KEY (id),
    PRIMARY KEY (ip, port)
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
    inventory decimal(20,2),           -- 运输剩余库存
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
    PRIMARY KEY (id)
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