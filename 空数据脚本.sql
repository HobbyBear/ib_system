/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20-6-9 19:48:28                              */
/*==============================================================*/


drop table if exists bus;

drop table if exists line;

drop table if exists line_station;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists scheduling;

drop table if exists station;

drop table if exists user;

/*==============================================================*/
/* Table: bus                                                   */
/*==============================================================*/
create table bus
(
   b_id                 int not null auto_increment,
   b_license            varchar(20) not null,
   b_type               varchar(25) not null,
   b_status             varchar(2) not null,
   b_startTime          time,
   primary key (b_id),
   key AK_Key_2 (b_license)
);

/*==============================================================*/
/* Table: line                                                  */
/*==============================================================*/
create table line
(
   l_id                 int not null auto_increment,
   l_name               varchar(20) not null,
   l_status             varchar(2) not null,
   l_startLineTime      datetime,
   l_direction          varchar(2) not null,
   primary key (l_id),
   key AK_key_2 (l_name)
);

/*==============================================================*/
/* Table: line_station                                          */
/*==============================================================*/
create table line_station
(
   ls_id                int not null auto_increment,
   l_id                 int not null,
   s_id                 int not null,
   primary key (ls_id)
);

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   p_id                 int not null auto_increment,
   p_name               varchar(50) not null,
   p_describe           varchar(100),
   primary key (p_id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   r_id                 int not null auto_increment,
   r_name               varchar(20) not null,
   r_describe           varchar(1024),
   primary key (r_id)
);

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   rp_id                int not null auto_increment,
   p_id                 int not null,
   r_id                 int not null,
   primary key (rp_id)
);

/*==============================================================*/
/* Table: scheduling                                            */
/*==============================================================*/
create table scheduling
(
   sche_id              int not null auto_increment,
   l_id                 int not null,
   b_license            varchar(20) not null,
   sche_tcNumber        varchar(25) not null,
   sche_tcTime          varchar(25) not null,
   u_id                 int not null,
   s_srartStation       int not null,
   s_endStation         int not null,
   primary key (sche_id)
);

/*==============================================================*/
/* Table: station                                               */
/*==============================================================*/
create table station
(
   s_id                 int not null auto_increment,
   s_name               varchar(50) not null,
   s_longitude          varchar(50) not null,
   s_latitude           varchar(50) not null,
   s_location           varchar(50) not null,
   s_street             varchar(50) not null,
   primary key (s_id),
   key AK_key_2 (s_name)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   u_id                 int not null auto_increment,
   r_id                 int,
   u_loginName          varchar(25) not null,
   u_password           varchar(50) not null,
   u_name               varchar(25) not null,
   u_telephone          varchar(11) not null,
   u_idCard             varchar(18) not null,
   u_driving            numeric(8,0),
   u_status             varchar(2) not null,
   primary key (u_id),
   key AK_key_2 (u_loginName),
   key AK_key_3 (u_telephone),
   key AK_key_4 (u_idCard)
);

alter table line_station add constraint FK_line_station1 foreign key (l_id)
      references line (l_id) on delete restrict on update restrict;

alter table line_station add constraint FK_line_station2 foreign key (s_id)
      references station (s_id) on delete restrict on update restrict;

alter table role_permission add constraint FK_role_permission1 foreign key (r_id)
      references role (r_id) on delete restrict on update restrict;

alter table role_permission add constraint FK_role_permission2 foreign key (p_id)
      references permission (p_id) on delete restrict on update restrict;

alter table scheduling add constraint FK_bus_sche foreign key (b_license)
      references bus (b_license) on delete restrict on update restrict;

alter table scheduling add constraint FK_line_sche foreign key (l_id)
      references line (l_id) on delete restrict on update restrict;

alter table scheduling add constraint FK_station_sche foreign key (s_srartStation)
      references station (s_id) on delete restrict on update restrict;

alter table scheduling add constraint FK_station_sche2 foreign key (s_endStation)
      references station (s_id) on delete restrict on update restrict;

alter table scheduling add constraint FK_user_sche foreign key (u_id)
      references user (u_id) on delete restrict on update restrict;

alter table user add constraint FK_user_role foreign key (r_id)
      references role (r_id) on delete restrict on update restrict;

