create database HotelReservations;

use HotelReservations;
 /*This table is where the reservations are given the start/end date, billing status, and customer association*/
create table Reservation (
ReservationID int(11) not null auto_increment,
CustomerID int(11) not null,
StartDate date not null,
EndDate date not null,
BilledID int(11) default 0 not null,
primary key (ReservationID)
);
/*alter table Reservation add constraint fk_Reservation_CustomerID foreign key (CustomerID) references Customer(CustomerID);
alter table Reservation add constraint fk_Reservation_BilledID foreign key (BilledID) references Billed(BilledID);*/

create table Billed (
BilledID int(11) not null auto_increment,
isBilled bool not null,
primary key (BilledID)
);

create table RoomReservation (
RoomID int(11) not null,
ReservationID int(11) not null,
primary key(RoomID, ReservationID)
);
/*alter table RoomReservation add constraint fk_RoomReservation_RoomID foreign key (RoomID) references Room(RoomID);
alter table ReservationID add constraint fk_RoomReservation_ReservationID foreign key (ReservationID) references Reservation(ReservationID);*/

create table Room (
RoomID int(11) not null auto_increment,
RoomTypeID int(11) not null,
FloorID int(11) not null,
SeasonID int(11) default 1,
primary key(RoomID)
);
/*alter table Room add constraint fk_Room_RoomTypeID foreign key (RoomTypeID) references RoomType(RoomTypeID);
alter table Room add constraint fk_Room_FloorID foreign key (FloorID) references FloorNum(FloorID);
alter table Room add constraint fk_Room_SeasonID foreign key (SeasonID) references Season(SeasonID);*/

create table FloorNum (
FloorID int(11) not null auto_increment,
Floor int(11) not null,
primary key(FloorID)
);

create table RoomType (
RoomTypeID int(11) not null auto_increment,
RoomSizeID int(11) not null,
primary key(RoomTypeID)
);
/*alter table RoomType add constraint fk_RoomType_RoomSizeID foreign key (RoomSizeID) references RoomSize(RoomSizeID);*/

create table RoomSize (
RoomSizeID int(11) not null auto_increment,
RoomSizeType varchar(10) not null,
RoomOccupancyLimit int(11) not null,
RoomSizeBaseRate int(11) not null,
primary key(RoomSizeID)
);


create table Amenities (
AmenitiesID int(11) not null auto_increment,
AmenityName varchar(20) not null,
AmenityRate int(11) not null,
primary key(AmenitiesID)
);

create table Season (
SeasonID int(11) not null auto_increment,
StartDate date not null,
EndDate date not null,
RateMultiplierPercent int(11) null,
primary key(SeasonID)
);

create table RoomTypeAmenities (
RoomTypeID int(11) not null,
AmenitiesID int(11) not null,
primary key(RoomTypeID,AmenitiesID)
);
/*alter table RoomTypeAmenities add constraint fk_RoomTypeAmenities_RoomTypeID foreign key (RoomTypeID) references RoomType(RoomTypeID);
alter table RoomTypeAmenities add constraint fk_RoomTypeAmenities_AmenitiesID foreign key (AmenitiesID) references Amenities(AmenitiesID);*/

create table Customer (
CustomerID int(11) not null auto_increment,
FirstName varchar(30) not null,
LastName varchar(30) not null,
Phone varchar(30) not null,
Email varchar(128),
primary key(CustomerID)
);

create table Guest (
GuestID int(11) not null auto_increment,
FirstName varchar(30) not null,
LastName varchar(30) not null,
Age int(11) not null,
primary key(GuestID)
);

create table ReservationGuest (
ReservationID int(11) not null,
GuestID int(11) not null,
primary key(ReservationID,GuestID)
);
/*alter table ReservationGuest add constraint fk_ReservationGuest_ReservationID foreign key (ReservationID) references Reservation(ReservationID);
alter table ReservationGuest add constraint fk_ReservationGuest_GuestID foreign key (GuestID) references Guest(GuestID);*/

create table Bill (
BillID int(11) not null auto_increment,
TotalPrice int(11) not null,
TotalTax int(11) not null,
PromotionID int(11) default 1,
primary key(BillID)
);
/*alter table Bill add constraint fk_Bill_PromotionID foreign key (PromotionID) references Promotion(PromotionID);*/

create table Promotion (
PromotionID int(11) not null auto_increment,
StartDate date not null,
EndDate date not null,
DiscountID int(11) not null,
primary key(PromotionID)
);
/*alter table Promotion add constraint fk_Promotion_DiscountID foreign key (DiscountID) references Discount(DiscountID);*/

create table Discount (
DiscountID int(11) not null auto_increment,
DiscountType varchar(10) not null,
DiscountAmount int(11) not null,
primary key(DiscountID)
);

create table AddOns (
AddOnsID int(11) not null auto_increment,
AddOnType varchar(30) not null,
AddOnRate int(11) not null,
primary key(AddOnsID)
);

create table BillAddOns (
BillID int(11) not null,
AddOnsID int(11) not null,
primary key(BillID,AddOnsID)
);
/*alter table BillAddOns add constraint fk_BillAddOns_BillID foreign key (BillID) references Bill(BillID);
alter table BillAddOns add constraint fk_BillAddOns_AddOnsID foreign key (AddOnsID) references AddOns(AddOnsID);*/

create table ReservationBill (
ReservationID int(11) not null,
BillID int(11) not null,
primary key(ReservationID,BillID)
);
/*alter table ReservationBill add constraint fk_ReservationBill_ReservationID foreign key (ReservationID) references Reservation(ReservationID);
alter table ReservationBill add constraint fk_ReservationBill_BillID foreign key (BillID) references Bill(BillID);*/











