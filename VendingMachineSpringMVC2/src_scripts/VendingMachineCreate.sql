create database VendingMachine;

use VendingMachine;

create table if not exists `Item` (
`item_id` int(11) not null auto_increment,
`item_cost` varchar(20) not null,
`item_type` varchar(30) not null,
`item_quantity` varchar(10) not null default "10",
primary key (`item_id`)
)engine=InnoDB default charset=latin1 auto_increment=1;