drop database if exists GradeManagement;
create database GradeManagement;
use GradeManagement;
create table student (
	id varchar(20) not null, 
	name varchar(40) not null,
	password varchar(40),
	img varchar(40) not null,
	primary key(id)
)engine=innodb,default charset=utf8;

create table course (
	id int not null auto_increment,
	name varchar(20) not null,
	teacher_id varchar(20) not null,
	weekday varchar(20) not null,
	period varchar(20) not null,
	primary key(id)
)engine=innodb,default charset=utf8;

create table score (
	id int not null auto_increment,
	score double,
	course_id int not null,
	student_id varchar(20) not null,
	primary key(id)
)engine=innodb,default charset=utf8;
	
create table admin (
	username varchar(20) not null,
	password varchar(20) not null,
	primary key(username)
)engine=innodb,default charset=utf8;

create table teacher (
	id varchar(20) not null,
	name varchar(40) not null,
	password varchar(20) not null,
	img varchar(40) not null default 'bgimg.jpg',
	primary key(id)
)engine=innodb, default charset=utf8;

alter table score
	add constraint FK_ID1 
	foreign key (student_id) 
	references student(id) on delete cascade;
alter table score
	add constraint FK_ID2
	foreign key (course_id)
	references course(id) on delete cascade;
alter table score
	add constraint UNIQUE_UNI
	unique(course_id, student_id);

alter table course
	add constraint FK_ID3
	foreign key (teacher_id)
	references teacher(id) on delete cascade;

alter table course
	add constraint UNIQUE_UNI1
	unique key(weekday, period);

insert into admin values ('admin', 'admin');