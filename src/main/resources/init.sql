/*权限表*/
CREATE TABLE PERMISSION (
  pid int(11) not null auto_increment,
  name varchar(100) not null default '',
  url varchar(255) default '',
  primary key (pid)
) engine = InnoDB default charset = utf8;

insert into PERMISSION values('1', 'add', '');
insert into PERMISSION values('2', 'delete', '');
insert into PERMISSION values('3', 'update', '');
insert into PERMISSION values('4', 'select', '');
/*insert into PERMISSION values('5', 'upload', '');
insert into PERMISSION values('6', 'download', '');*/



/*用户表*/
CREATE TABLE USER (
  uid int(11) not null auto_increment,
  user_name varchar(100) not null default '',
  password varchar(255) default '',
  salt varchar(20) default 'shiro',
  primary key (uid)
) engine = InnoDB default charset = utf8;

insert into USER values ('1', 'admin', '123');
insert into USER values ('2', 'demo', '123');


/*角色表*/
CREATE TABLE ROLE (
  rid int(11) not null auto_increment,
  role_name varchar(100) not null default '',
  primary key (rid)
) engine = InnoDB default charset = utf8;

insert into ROLE values ('1', 'admin');
insert into ROLE values ('2', 'normal');

/*用户角色关系表*/
CREATE TABLE USER_ROLE (
  uid int(11) not null,
  rid int(11) not null
) engine = InnoDB default charset = utf8;

insert into USER_ROLE values (1, 1);
insert into USER_ROLE values (1, 2);
insert into USER_ROLE values (2, 1);
insert into USER_ROLE values (2, 2);

/*角色权限关系表*/
CREATE TABLE ROLE_PERMISSION (
  rid int(11) not null,
  pid int(11) not null
) engine = InnoDB default charset = utf8;


insert into ROLE_PERMISSION values (1, 1);
insert into ROLE_PERMISSION values (1, 2);
insert into ROLE_PERMISSION values (1, 3);
insert into ROLE_PERMISSION values (1, 4);
insert into ROLE_PERMISSION values (2, 4);