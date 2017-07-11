create database DvdLibrary;

use DvdLibrary;

create table if not exists `Dvd` (
`dvd_id` int(11) not null auto_increment,
`dvd_title` varchar(30) not null,
`release_date` int(4) not null,
`dvd_director` varchar(30) not null,
`dvd_rating` varchar(10) not null,
`dvd_notes` varchar(2000) default null,
primary key (`dvd_id`)
)engine=InnoDB default charset=latin1 auto_increment=1;
