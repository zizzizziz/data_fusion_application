-- 人力链报表信息
create table if not exists tbl_staff_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    position tinyint,
    skill varchar(255),
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_person_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    categories tinyint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_position_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    position tinyint,
    eventTime bigint,
    updateTime bigint,
    PRIMARY KEY (id),
    INDEX (corporation)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

create table if not exists tbl_staff_skill_info(
    id int(11) NOT NULL AUTO_INCREMENT,
    corporation varchar(255),
    skill varchar(255),
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
    type tinyint,
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
    type tinyint,
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