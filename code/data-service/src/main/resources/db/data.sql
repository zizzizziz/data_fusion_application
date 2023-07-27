INSERT INTO tbl_staff_info(id, corporation, categories, positions, skill, amount, eventTime, updateTime)
VALUES (1, '小丫家电', 1, 2, '后台开发',100, 20231112001, 202307172250),
       (2, '小丫家电', 1, 2, '前端开发',200, 20231112001, 202307172250),
       (3, '小丫家电', 2, 1, '后台开发',200, 20231112001, 202012121212),
       (4, '小丫家电', 2, 1, '前端开发',100, 20231112001, 202012121212),
       (5, '小丫家电', 2, 1, '前端开发',100, 20231112001, 202012121212),
       (6, '比亚迪', 1, 1, '后台开发', 100,20231112001, 202012121212),
       (7, '比亚迪', 1, 1, '后台开发', 200,20231112001, 202012121212),
       (8, '比亚迪', 1, 1, '前端开发', 200,20231112001, 202012121212),
       (9, '比亚迪', 1, 1, '前端开发', 100,20231112001, 202307172250),
       (10, '比亚迪', 1, 1, '后台开发',100,20231112001, 202307172250);

INSERT INTO tbl_staff_position_info(id, corporation, positions, amount, eventTime, updateTime)
VALUES (1, '小丫家电', 1,100, 1690185315000, 202307172250),
       (2, '小丫家电', 1,200, 1690185315000, 202307172250),
       (3, '小丫家电', 2,200, 1690185315000, 202307172250),
       (4, '小丫家电', 2,100, 1690185315000, 202307172250),
       (5, '小丫家电', 2,100, 1690185315000, 202307172250),
       (6, '比亚迪', 1, 100,1643010915000, 202307172250),
       (7, '比亚迪', 1, 200,1643010915000, 202307172250),
       (8, '比亚迪', 1, 200,1643010915000, 202307172250),
       (9, '比亚迪', 1, 100,1643010915000, 202307172250),
       (10, '比亚迪', 1,100, 1643010915000,202307172250);

INSERT INTO tbl_staff_person_info(id, corporation, categories, amount, eventTime, updateTime)
VALUES (1, '小丫家电', 1,100, 1690185315000, 202307172250),
       (2, '小丫家电', 1,200, 1690185315000, 202307172250),
       (3, '小丫家电', 2,200, 1690185315000, 202307172250),
       (4, '小丫家电', 2,100, 1690185315000, 202307172250),
       (5, '小丫家电', 2,100, 1690185315000, 202307172250),
       (6, '比亚迪', 1, 100,1643010915000,202307172250),
       (7, '比亚迪', 1, 200,1643010915000,202307172250),
       (8, '比亚迪', 1, 200,1643010915000,202307172250),
       (9, '比亚迪', 1, 100,1643010915000,202307172250),
       (10, '比亚迪', 1,100, 1643010915000,202307172250);

INSERT INTO tbl_staff_skill_info(id, corporation, skill, amount, eventTime, updateTime)
VALUES (1, '小丫家电', '后台开发',100, 1690185315000, 202307172250),
       (2, '小丫家电', '后台开发',200, 1690185315000, 202307172250),
       (3, '小丫家电', '前端开发',200, 1690185315000, 202307172250),
       (4, '小丫家电', '前端开发',100, 1690185315000, 202307172250),
       (5, '小丫家电', '前端开发',100, 1690185315000, 202307172250),
       (6, '比亚迪', '后台开发', 100,1643010915000, 202307172250),
       (7, '比亚迪', '后台开发', 200,1643010915000, 202307172250),
       (8, '比亚迪', '前端开发', 200,1643010915000, 202307172250),
       (9, '比亚迪', '前端开发', 100,1643010915000, 202307172250),
       (10, '比亚迪', '后台开发',100, 1643010915000,202307172250);
-- Wealth
INSERT INTO tbl_wealth_info(id, corporation, research, device, production, storage, materiel, transportation, salary, revenue, profit, fixedAssets, cashAssets, finance, eventTime, updateTime)
VALUES (1, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (2, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (3, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (4, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (5, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (6, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (7, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (8, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250);

INSERT INTO tbl_wealth_finance_info(id, corporation, research, device, production, storage, materiel, transportation, salary, revenue, eventTime, updateTime)
VALUES (1, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (2, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (3, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (4, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (5, '小丫家电', 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (6, '比亚迪', 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1690185315000, 202307172250);

INSERT INTO tbl_wealth_asset_info(id, corporation, profit, fixedAssets, cashAssets, finance, eventTime, updateTime)
VALUES (1, '小丫家电', 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (2, '比亚迪', 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (3, '小丫家电', 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (4, '比亚迪', 2000, 2000, 2000, 2000, 1690185315000, 202307172250),
       (5, '小丫家电', 1000, 1000, 1000, 1000, 1690185315000, 202307172250),
       (6, '比亚迪', 2000, 2000, 2000, 2000, 1690185315000, 202307172250);

-- Convey
INSERT INTO tbl_convey_info(id, corporation, types, goods, quantity, inventory, mileage, cost, eventTime, updateTime)
VALUES (1, '小丫家电', 1, '冰箱', 200, 500, 1000, 50000, 1690185315000, 202012121212),
       (2, '小丫家电', 1, '冰箱', 200, 500, 1000, 50000, 1690185315000, 202012121212),
       (3, '小丫家电', 2, '冰箱', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (4, '小丫家电', 2, '电饭煲', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (5, '小丫家电', 2, '电饭煲', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (6, '比亚迪', 3, '发动机', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (7, '比亚迪', 3, '发动机', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (8, '比亚迪', 3, '发动机', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (9, '比亚迪', 4, '发动机', 200, 500, 1000, 50000, 1690185315000, 202307172250),
       (10, '比亚迪', 4, '发动机', 200, 500, 1000, 50000, 1690185315000, 202307172250);

INSERT INTO tbl_convey_traffic_info(id, corporation, types, mileage, cost, eventTime)
VALUES (1, '小丫家电', 1, 1000, 50000, 1690185315000),
       (2, '小丫家电', 1, 1000, 50000, 1690185315000),
       (3, '小丫家电', 2, 1000, 50000, 1690185315000),
       (4, '小丫家电', 2, 1000, 50000, 1690185315000),
       (5, '小丫家电', 2, 1000, 50000, 1690185315000),
       (6, '比亚迪', 3, 1000, 50000, 1690185315000),
       (7, '比亚迪', 3, 1000, 50000, 1690185315000),
       (8, '比亚迪', 3, 1000, 50000, 1690185315000),
       (9, '比亚迪', 4, 1000, 50000, 1690185315000),
       (10, '比亚迪',4, 1000, 50000, 1690185315000);

INSERT INTO tbl_convey_inventory_info(id, corporation, goods, quantity, inventory, eventTime)
VALUES (1, '小丫家电', '冰箱', 200, 500, 1690185315000),
       (2, '小丫家电', '冰箱', 200, 500, 1690185315000),
       (3, '小丫家电', '冰箱', 200, 500, 1690185315000),
       (4, '小丫家电', '电饭煲', 200, 500, 1690185315000),
       (5, '小丫家电', '电饭煲', 200, 500, 1690185315000),
       (6, '比亚迪', '发动机', 200, 500, 1690185315000),
       (7, '比亚迪', '发动机', 200, 500, 1690185315000),
       (8, '比亚迪', '发动机', 200, 500, 1690185315000),
       (9, '比亚迪', '发动机', 200, 500, 1690185315000),
       (10, '比亚迪','发动机', 200, 500, 1690185315000);

-- production表
INSERT INTO tbl_production_birth_info(id, corporation, categories, types, quantity, province, country, eventTime)
VALUES (1, '小丫家电', 1, 1, 200, '中国','广东', 1690185315000),
       (2, '小丫家电', 2, 2, 230, 'France', 'Paris',1690185315000),
       (3, '小丫家电', 3, 3, 100, '中国','福建', 1690185315000),
       (4, '小丫家电', 4, 4, 20, '中国','海南',1690185315000),
       (5, '小丫家电', 5, 5, 50, '中国','北京', 1690185315000),
       (6, '小丫家电', 1, 1, 200, '中国','广东', 1690185315000),
       (7, '小丫家电', 2, 2, 200, '中国','广东', 1690185315000),
       (8, '小丫家电', 1, 1, 200, '中国','广东', 1690185315000),
       (9, '比亚迪', 2, 2, 200, '中国','广东', 1690185315000),
       (10, '比亚迪', 1, 1, 200, '中国','广东', 1690185315000);

INSERT INTO tbl_production_yield_info(id, corporation,categories, types, quantity, eventTime, updateTime )
VALUES (1, '小丫家电', 1, 1, 200,  1690185315000,1690185315000),
       (2, '小丫家电', 1, 2, 230, 1690185315000, 1690185315000),
       (3, '苏泊尔', 1, 3, 100, 1690185315000, 1690185315000),
       (4, '美的', 1, 4, 50,  1690185315000, 1690185315000),
       (5, '比亚迪', 2, 1, 100,  1690185315000, 1690185315000),
       (6, '红旗', 2, 1, 200, 1690185315000, 1690185315000),
       (7, '红旗', 2, 2, 200, 1690185315000, 1690185315000);

