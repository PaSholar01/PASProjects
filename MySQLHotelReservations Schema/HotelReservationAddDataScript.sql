
use HotelReservations;

insert into FloorNum (Floor) values (1) , (2) , (3) , (4) , (5);

insert into Guest (FirstName, LastName, Age) values ('Bree' , 'Burrell' , 27) , ('Kade' , 'Sholar' , 2) , ('John' , 'Sholar' , 27) , ('Bill' , 'Bunyon' , 38) , ('Kale' , 'Salado' , 21) , ('John' , 'Shuarr' , 27) , ('Bree' , 'Braegen' , 34) , ('Kade' , 'Kalamazoo' , 29) , ('Bruenor' , 'Battlehammer' , 279);

insert into AddOns (AddOnType, AddOnRate) values ('TV Package' , 5) , ('Luxury Service' , 10) , ('Valet Parking' , 5);

insert into Customer (FirstName, LastName, Phone, Email) values ('Paul' , 'Sholar' , '502-526-7577' , 'pasholar01@gmail.com') , ('Drizzt' , 'DoUrden', '502-543-8998' , 'drizzt01@gmai.com') , ('Jimmy' , 'John' , '502-594-1844' , 'jimmy01123@gmail.com');

insert into Amenities (AmenityName, AmenityRate) values ('Fridge' , 2) , ('Microwave' , 2) , ('Mini Bar' , 10) , ('Spa Bath' , 2);

insert into RoomSize (RoomSizeType,RoomOccupancyLimit, RoomSizeBaseRate) values ('single' , 2 , 80) , ('double' , 4 , 110) , ('king' , 5 , 150);

insert into RoomType (RoomSizeID) values (1) , (2) , (3);

insert into Season (StartDate, EndDate, RateMultiplierPercent) values ('2017/10/1' , '2018/4/28', NULL) , ('2018/4/29' , '2018/9/30' , 20);

insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (2 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (2 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (2 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 1 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (3 , 2 , 1);
insert into Room (RoomTypeID, FloorID, SeasonID) values (1 , 2 , 1);

insert into Discount (DiscountType, DiscountAmount) values ('Percent' , 10) , ('FlatRate' , 100);

insert into Promotion (StartDate, EndDate, DiscountID) values ('2017/6/25' , '2017/7/15' , 1);

insert into Bill (TotalPrice, TotalTax, PromotionID) values (1000, 230, 1) , (950, 200, 1) , (2000, 200, 1);

insert into Billed (isBilled) values (0) , (1);

insert into BillAddOns (BillID, AddOnsID) values (1,1) , (1,2) , (1,3) , (2,1) , (2,2) , (3,1);

insert into RoomTypeAmenities (RoomTypeID,AmenitiesID) values (3,1) , (3,2) , (3,3) , (3,4) , (1,1) , (1,2) , (2,1) , (2,2) , (2,4);

insert into Reservation (CustomerID, StartDate, EndDate, BilledID) values (1, '2017/6/30', '2017/7/1', 1) , (2, '2017/7/5' , '2017/7/11' , 1) , (3, '2017/7/12' , '2017/7/23' , 1);

insert into ReservationBill (ReservationID, BillID) values (1, 1) , (2,2) , (3,3);

insert into ReservationGuest (ReservationID, GuestID) values (1,1) , (1,2) , (1,3) , (2,4) , (2,6) , (2,7) , (2,9) , (2,2) , (3,8) , (3,9) , (3,5);

insert into RoomReservation (RoomID, ReservationID) values (2,1) , (1,1) , (19,2) , (20,2) , (12,2) , (4,2) , (2,2) , (16,3) , (17,3);






