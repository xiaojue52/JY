-- Create table
create table USERS
(
  USERNAME VARCHAR2(50),
  PASSWORD VARCHAR2(50),
  ID       NUMBER,
  TAG      VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
-- Create table
create table DEVICES
(
  ID                  NUMBER,
  DEVICE_BOX          VARCHAR2(100),
  NOTE                VARCHAR2(500),
  WARNING_TEMPERATURE VARCHAR2(20),
  NAME                VARCHAR2(100),
  LOCATION            VARCHAR2(100),
  CURRENT_DATA        VARCHAR2(20),
  STATUS              VARCHAR2(20),
  OWNER               VARCHAR2(20),
  CREATE_DATE         NUMBER,
  REASON              VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create table
create table DEVICE_BOX
(
  ID            NUMBER,
  MAXTEMP       VARCHAR2(10),
  LOCATION      VARCHAR2(1000),
  DEVICE_NUMBER NUMBER,
  NOTE          VARCHAR2(4000),
  NAME          VARCHAR2(100),
  OWNER         VARCHAR2(20),
  IDENTIFY      VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
  -- Create table
create table UNHANDLED_EXCEPTION
(
  ID                  NUMBER,
  DEVICE_BOX          VARCHAR2(100),
  NOTE                VARCHAR2(500),
  WARNING_TEMPERATURE VARCHAR2(20),
  NAME                VARCHAR2(100),
  LOCATION            VARCHAR2(100),
  CURRENT_DATA        VARCHAR2(20),
  STATUS              VARCHAR2(20),
  OWNER               VARCHAR2(20),
  REASON              VARCHAR2(200),
  CREATE_DATE         NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
  -- Create table
create table DEVICES_HISTORY
(
  ID                  NUMBER,
  DEVICE_BOX          VARCHAR2(100),
  NOTE                VARCHAR2(500),
  WARNING_TEMPERATURE VARCHAR2(20),
  NAME                VARCHAR2(100),
  LOCATION            VARCHAR2(100),
  CURRENT_DATA        VARCHAR2(20),
  STATUS              VARCHAR2(20),
  OWNER               VARCHAR2(20),
  CREATE_DATE         NUMBER,
  REASON              VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );