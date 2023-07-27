-- 人力链报表信息
create table if not exists tbl_staff_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    positions tinyint,
    skill varchar(255),
    amount bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_person_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    amount bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_position_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    positions tinyint,
    amount bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_skill_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    skill varchar(255),
    amount bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

--wealth表
create table if not exists tbl_wealth_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    research bigint,
    device bigint,
    production bigint,
    storage bigint,
    materiel bigint,
    transportation bigint,
    salary bigint,
    revenue bigint,
    profit bigint,
    fixedAssets bigint,
    cashAssets bigint,
    finance bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_wealth_finance_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    research bigint ,
    device bigint,
    production bigint,
    storage bigint,
    materiel bigint,
    transportation bigint,
    salary bigint,
    revenue bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_wealth_asset_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    profit bigint,
    fixedAssets bigint,
    cashAssets bigint,
    finance bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

--convey
create table if not exists tbl_convey_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    types tinyint,
    goods varchar(255),
    quantity bigint,
    inventory bigint,
    mileage bigint,
    cost bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_convey_traffic_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    types tinyint,
    mileage bigint,
    cost bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_convey_inventory_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    goods varchar(255),
    quantity bigint,
    inventory bigint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

-- production
create table if not exists tbl_production_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    types tinyint,
    quantity bigint,
    cost bigint,
    province varchar(255),
    country varchar(255),
    quality int,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_production_yield_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint, -- 产品类型
    types tinyint,      -- 产品
    quantity bigint,    -- 产品数量
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_production_birth_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    types tinyint,
    quantity bigint,
    province varchar(255),
    country varchar(255),
    eventTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_production_trend_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    types tinyint,
    quantity bigint,
    province varchar(255),
    country varchar(255),
    eventTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;
-- Sale
create table if not exists tbl_sale_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    types tinyint,
    quantity bigint,
    income bigint,
    cost bigint,
    province varchar(255),
    country varchar(255),
    inventory bigint,
    score int,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_sale_trend_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    types tinyint,
    quantity bigint,
    income bigint,
    cost bigint,
    province varchar(255),
    country varchar(255),
    inventory bigint,
    score int,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
    ) ENGINE=InnoDB DEFAULT CHARSET = utf8;

