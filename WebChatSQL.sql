CREATE DATABASE webchat;
USE WebChat;
-- 用户表
create table user(
	uid int auto_increment primary key,
    username varchar(20) unique not null,
    password varchar(20) not null,
    nickname varchar(10),
    email varchar(30) not null,
    state int default 1,
    img varchar(20) default 'userDefault',
    problem varchar(30) not null,
	answer varchar(30) not null,
    registtime TIMESTAMP default CURRENT_TIMESTAMP 
);
-- 群聊表
create table groupchat(
	gid int auto_increment primary key,
    groupname varchar(20),
    createid int,
    invitecode varchar(10) not null,
    createtime TIMESTAMP default CURRENT_TIMESTAMP,
    foreign key(createid) references user(uid)
);
-- 群员表
create table groupmembers(
	uid int,
    gid int,
    foreign key(uid) references user(uid),
    foreign key(gid) references groupchat(gid)
);
-- 组别表
create table category(
	cid int auto_increment primary key,
	cname varchar(10)
);
-- 好友表
create table friend(
	uid int,
    fuid int,
    note varchar(10),
    cid int,
    foreign key(uid) references user(uid),
    foreign key(fuid) references user(uid),
    foreign key(cid) references category(cid)
);
-- 私聊记录
create table privatechatrecord(
	id int auto_increment primary key,
	uid int,
    tuid int,
    sendtime TIMESTAMP default CURRENT_TIMESTAMP,
    content text,
    foreign key(uid) references user(uid),
    foreign key(tuid) references user(uid)
);
-- 群聊记录
create table groupchatrecord(
	id int auto_increment primary key,
	uid int,
    gid int,
    sendtime TIMESTAMP default CURRENT_TIMESTAMP,
    content text,
    foreign key(uid) references user(uid),
    foreign key(gid) references groupchat(gid)
);




