CREATE TABLE employee
(
  id VARCHAR(50) PRIMARY KEY NOT NULL COMMENT '物理ID',
  employee_id VARCHAR(50) NOT NULL COMMENT '工号',
  employee_name VARCHAR(50) COMMENT '员工姓名',
  basic_wage INT NOT NULL COMMENT '基本薪资',
  role CHAR(1) NOT NULL COMMENT '工种，干部为a,基层为b,暂时不支持其他类型'
);
CREATE UNIQUE INDEX employee_id_uindex ON employee (id);
ALTER TABLE employee COMMENT = '员工';

CREATE TABLE attendance_info
(
  id VARCHAR(50) PRIMARY KEY NOT NULL COMMENT '物理ID' AUTO_INCREMENT,
  employee_id VARCHAR(50) NOT NULL COMMENT '员工ID',
  work_month CHAR(6) NOT NULL COMMENT '出勤月份',
  work_day FLOAT NOT NULL COMMENT '出勤天数'
);
CREATE UNIQUE INDEX attendance_info_id_uindex ON attendance_info (id);
ALTER TABLE attendance_info COMMENT = '出勤天数';

CREATE TABLE payment
(
  id VARCHAR(50) PRIMARY KEY COMMENT '物理ID',
  employee_id VARCHAR(50) NOT NULL COMMENT '工号',
  employee_name VARCHAR(50) COMMENT '员工姓名',
  work_month CHAR(6) COMMENT '工作月份',
  work_day FLOAT NOT NULL COMMENT '出勤天数',
  current_wage DECIMAL NOT NULL COMMENT '当月薪资',
  social_security DECIMAL COMMENT '社保扣款',
  welfare DECIMAL COMMENT '公司福利',
  other_deduction DECIMAL COMMENT '其他扣款',
  pay DECIMAL NOT NULL COMMENT '实际工资'
);
ALTER TABLE payment COMMENT = '工资';

