INSERT INTO `pmlog`.`project` (`idProject`, `modelFieldName`, `name`, `jiraProjectKeyA1QA`, `jiraProjectKeyPM`) VALUES ('1', 'Mobile', 'Mobile', 'Mobile');
INSERT INTO `pmlog`.`project` (`idProject`, `modelFieldName`, `name`, `jiraProjectKeyA1QA`, `jiraProjectKeyPM`) VALUES ('2', 'Social', 'Social', 'Social');
INSERT INTO `pmlog`.`project` (`idProject`, `modelFieldName`, `name`, `jiraProjectKeyA1QA`, `jiraProjectKeyPM`) VALUES ('3', 'Server', 'Server', 'Server');
INSERT INTO `pmlog`.`project` (`idProject`, `modelFieldName`, `name`, `jiraProjectKeyA1QA`, `jiraProjectKeyPM`) VALUES ('4', 'Other project(-s)', 'Other project(-s)', 'Other project(-s)');

INSERT INTO `pmlog`.`type` (`idType`, `name`) VALUES ('1', 'input');
INSERT INTO `pmlog`.`type` (`idType`, `name`) VALUES ('2', 'dropdown');
INSERT INTO `pmlog`.`type` (`idType`, `name`) VALUES ('3', 'checkbox');

INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('1', '2', 'Project', '1', 'project');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('2', '2', 'Activity', '1', 'activity');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('3', '1', 'Build', '1', 'build');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('4', '3', 'Devices', '1', 'devices');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('5', '3', 'Environment', '1', 'environment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('6', '1', 'Time spent', '1', 'time');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('7', '1', 'Comment', '1', 'comment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('8', '2', 'Project ', '2', 'project');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('9', '2', 'Activity', '2', 'activity');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('10', '1', 'Sprint', '2', 'sprint');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('11', '1', 'Time spent (in minutes) ', '2', 'time');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('12', '1', 'Comment ', '2', 'comment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('13', '1', 'Link', '2', 'link');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('14', '2', 'Project ', '3', 'project');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('15', '2', 'Activity ', '3', 'activity');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('16', '3', 'Environment', '3', 'environment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('17', '1', 'Sprint', '3', 'sprint');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('18', '1', 'Time spent (in minutes)', '3', 'time');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('19', '1', 'Comment ', '3', 'comment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('20', '1', 'Link', '3', 'link');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('21', '1', 'Project ', '4', 'project');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('22', '2', 'Activity type', '4', 'activity');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('23', '1', 'Time spent (in minutes)', '4', 'time');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('24', '1', 'Comment ', '4', 'comment');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('25', '1', 'Milestone', '1', 'milestone');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('26', '1', 'Test runs', '1', 'testruns');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('27', '1', 'The number of checked cases', '1', 'numberOfCheckedCases');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('28', '1', 'The number of checked defects', '1', 'numberOfCheckedDefects');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('29', '1', 'The number of reopened defects', '1', 'numberOfReopenedDefects');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('30', '1', 'Link to the filter with reopened defects', '1', 'linkToReopenedDefects');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('31', '1', 'The number of checked stories', '1', 'numberOfCheckedStories');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('32', '1', 'The number of reopened stories', '1', 'numberOfReopenedStories');
INSERT INTO `pmlog`.`field` (`idField`, `idType`, `name`, `idProject`, `modelFieldName`) VALUES ('33', '1', 'Link to the filter with reopened stories', '1', 'linkToReopenedStories');

INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('1', '1', 'Cashman');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('2', '1', 'Heart of Vegas');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('3', '2', 'Regression');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('4', '2', 'Smoke test');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('5', '2', 'Issue and task investigation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('6', '2', 'Unknown & Need more info Issues');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('7', '2', 'Ad hoc testing');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('8', '2', 'Exploratory testing');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('9', '2', 'Bug verification');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('10', '2', 'Full test pass');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('11', '2', 'Communication');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('12', '2', 'Environment setup');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('13', '2', 'Other');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('14', '5', 'Staging');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('15', '5', 'UAT');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('16', '5', 'Production');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('17', '8', 'Cashman');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('18', '8', 'Heart of Vegas');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('19', '9', 'Communication');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('20', '9', 'Regression');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('21', '9', 'Testing activity');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('22', '9', 'Issue investigation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('23', '9', 'Test documentation creation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('24', '9', 'Review');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('25', '9', 'Project Management');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('26', '9', 'Explorative testing');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('27', '9', 'Ad-hoc');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('28', '9', 'Other');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('29', '14', 'CMS');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('30', '14', 'HVS');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('31', '14', 'UMS');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('32', '14', 'JE');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('33', '14', 'FBT');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('34', '14', 'ACL');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('35', '14', 'ACM');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('36', '14', 'SE');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('37', '14', 'Beacon');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('38', '14', 'Cerberus');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('39', '14', 'Trinity');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('40', '15', 'Testing activities');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('41', '15', 'Regression');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('42', '15', 'Test documentation creation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('43', '15', 'Communication');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('44', '15', 'Review');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('45', '15', 'Code writing');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('46', '15', 'Issue investigation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('47', '15', 'Project Management');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('48', '15', 'Product investigation');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('49', '15', 'Other');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('50', '16', 'Staging');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('51', '16', 'UAT');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('52', '16', 'Production');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('53', '22', 'Testing');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('54', '22', 'Communication');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('55', '22', 'Other');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('56', '4', 'Samsung Galaxy S3 Duos 4.4.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('57', '4', 'Samsung Galaxy S Duos	4.0.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('58', '4', 'Samsung Galaxy S2	4.0.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('59', '4', 'Samsung Galaxy S3 4.0.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('60', '4', 'Samsung Galaxy S3 mini 4.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('61', '4', 'Samsung Galaxy S4 mini 4.4.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('62', '4', 'Samsung Galaxy S4 5.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('63', '4', 'Samsung Galaxy Note 3 (белый) 5.0');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('64', '4', 'Samsung Galaxy Note 3 4.4.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('65', '4', 'Samsung Galaxy Note 4 6.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('66', '4', 'HTC One 4.4.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('67', '4', 'Motorola Moto G 5.0.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('68', '4', 'Motorola Moto X 4.2.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('69', '4', 'Samsung Galaxy S5 6.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('70', '4', 'Samsung Galaxy S5 (золотой) 5.0');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('71', '4', 'Motorola DROID RAZR M 4.4.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('72', '4', 'Motorola DROID RAZR M 4.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('73', '4', 'Samsung Galaxy Tab A 6.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('74', '4', 'Verizon Ellipsis 7	4.2.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('75', '4', 'LG G PAD 7.0 4.4.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('76', '4', 'ASUS Nexus 7 (2013) 4.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('77', '4', 'Samsung Galaxy Tab 2 4.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('78', '4', 'Samsung Galaxy Tab 2 10.1 4.0.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('79', '4', 'Samsung Galaxy Tab 2 10.1 4.0.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('80', '4', 'Samsung Galaxy Tab 4	5.0.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('81', '4', 'Kindle Fire HD 6 (4th Gen) 5.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('82', '4', 'Kindle Fire HD 6 (4th Gen) 4.4.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('83', '4', 'Kindle Fire HDX 7" (3rd Gen) 4.4.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('84', '4', 'Kindle Fire HDX 7" (3rd Gen) 4.4.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('85', '4', 'Kindle Fire (2nd Gen) 4.0.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('86', '4', 'Kindle Fire (2nd Gen) 4.0.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('87', '4', 'Kindle Fire HDX 8.9 (4th Gen) 5.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('88', '4', 'iPhone 4 7.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('89', '4', 'iPhone 4s 9.2.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('90', '4', 'iPhone 4s 8');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('91', '4', 'iPhone 5 8.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('92', '4', 'iPhone 5s 9.2.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('93', '4', 'iPhone 6 10.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('94', '4', 'iPhone 6 9.0');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('95', '4', 'iPhone 6+ 10.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('96', '4', 'iPhone 6+ 8.0');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('97', '4', 'iPhone SE 9.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('98', '4', 'iPad 2	8.4.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('99', '4', 'iPad 2	7.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('100', '4', 'iPad 3 8.1.3');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('101', '4', 'iPad 4 7.1.2');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('102', '4', 'iPad Mini 9.3.5');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('103', '4', 'iPad Air 10.0.1');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('104', '4', 'iPad Air 8.4');
INSERT INTO `pmlog`.`dropdown` (`idDropdown`, `idField`, `itemName`) VALUES ('105', '4', 'iPad Air 2 9.1');



















