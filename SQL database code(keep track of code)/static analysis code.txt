create table user (
	UID varchar(12),
	password varchar(12),
	access int,
	primary key(UID,password) );

create table coder (
	UID varchar(12),
	cdinfo nvarchar(255),
	date varchar(8),
	time varchar (5),
	primary key (UID,cdinfo),
	foreign key (UID) references user (UID) on delete cascade );
create table attackvector (
	start int,
	end int,
	type varchar(12),
	resource varchar(12),
	detail nvarchar(255),
    cdinfo nvarchar(255),
    UID varchar(12),
	primary key (start,end,type,cdinfo) ,
	foreign key (UID,cdinfo) references coder (UID,cdinfo) on delete cascade );