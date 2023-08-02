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
    corporation varchar(255),   -- 企业名称
    research bigint,            -- 研发支出
    device bigint,              -- 设备支出
    production bigint,          -- 生产支出
    storage bigint,             -- 仓储支出
    materiel bigint,            -- 物料支出
    transportation bigint,      -- 运输支出
    salary bigint,              -- 人员工资支出
    revenue bigint,             -- 总收入
    profit bigint,              -- 利润
    fixedAssets bigint,         -- 固定资产
    cashAssets bigint,          -- 流动资产
    finance bigint,             -- 融资
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链企业财务分布
create table if not exists tbl_wealth_finance_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    research bigint ,           -- 研发支出
    device bigint,              -- 设备支出
    production bigint,          -- 生产支出
    storage bigint,             -- 仓储支出
    materiel bigint,            -- 物料支出
    transportation bigint,      -- 运输支出
    salary bigint,              -- 人员工资支出
    revenue bigint,             -- 总收入
    eventTime bigint,           -- 事件时间
    updateTime bigint,          -- 更新时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 财务链企业内部资产分布
create table if not exists tbl_wealth_asset_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    profit bigint,              -- 利润
    fixedAssets bigint,         -- 固定资产
    cashAssets bigint,          -- 流动资产
    finance bigint,             -- 融资
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
    quantity bigint,            -- 运输量
    inventory bigint,           -- 运输剩余库存
    mileage bigint,             -- 运输里程数
    cost bigint,                -- 运输费用
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
    mileage bigint,             -- 运输里程数
    cost bigint,                -- 运输费用
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
    quantity bigint,            -- 运输量
    inventory bigint,           -- 运输剩余库存
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
    quantity bigint,            -- 生产产品产量
    cost bigint,                -- 生产产品费用
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
    quantity bigint,            -- 生产产品产量
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
    quantity bigint,            -- 生产产品产量
    province varchar(255),      -- 生产产品省份
    country varchar(255),       -- 生产产品国家
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 生产链整体产量
create table if not exists tbl_production_trend_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 生产产品
    quantity bigint,            -- 生产产品产量
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链报表
create table if not exists tbl_sale_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity bigint,            -- 销量
    income bigint,              -- 销售营收
    cost bigint,                -- 销售支出
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    inventory bigint,           -- 销售库存
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
    quantity bigint,            -- 销量
    income bigint,              -- 销售营收
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链销量分布
create table if not exists tbl_sale_count_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity bigint,            -- 销量
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
    quantity bigint,            -- 销量
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链出口分布
create table if not exists tbl_sale_export_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity bigint,            -- 销量
    province varchar(255),      -- 销售省份
    country varchar(255),       -- 销售国家
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- 销售链销售收入分布
create table if not exists tbl_sale_profit_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),   -- 企业名称
    categories tinyint,         -- 产品类型
    types tinyint,              -- 销售产品
    quantity bigint,            -- 销量
    income bigint,              -- 销售营收
    eventTime bigint,           -- 事件时间
    PRIMARY KEY (id),
    INDEX (corporation)
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