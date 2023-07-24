-- 人力链信息
create table if not exists tbl_staff_info_original(
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




