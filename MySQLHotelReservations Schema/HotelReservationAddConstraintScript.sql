use HotelReservations;

alter table Reservation add constraint fk_Reservation_CustomerID foreign key (CustomerID) references Customer(CustomerID);
alter table Reservation add constraint fk_Reservation_BilledID foreign key (BilledID) references Billed(BilledID);


alter table RoomReservation add constraint fk_RoomReservation_RoomID foreign key (RoomID) references Room(RoomID);
alter table RoomReservation add constraint fk_RoomReservation_ReservationID foreign key (ReservationID) references Reservation(ReservationID);

alter table Room add constraint fk_Room_RoomTypeID foreign key (RoomTypeID) references RoomType(RoomTypeID);
alter table Room add constraint fk_Room_FloorID foreign key (FloorID) references FloorNum(FloorID);
alter table Room add constraint fk_Room_SeasonID foreign key (SeasonID) references Season(SeasonID);

alter table RoomType add constraint fk_RoomType_RoomSizeID foreign key (RoomSizeID) references RoomSize(RoomSizeID);

alter table RoomTypeAmenities add constraint fk_RoomTypeAmenities_RoomTypeID foreign key (RoomTypeID) references RoomType(RoomTypeID);
alter table RoomTypeAmenities add constraint fk_RoomTypeAmenities_AmenitiesID foreign key (AmenitiesID) references Amenities(AmenitiesID);

alter table ReservationGuest add constraint fk_ReservationGuest_ReservationID foreign key (ReservationID) references Reservation(ReservationID);
alter table ReservationGuest add constraint fk_ReservationGuest_GuestID foreign key (GuestID) references Guest(GuestID);

alter table Bill add constraint fk_Bill_PromotionID foreign key (PromotionID) references Promotion(PromotionID);

alter table Promotion add constraint fk_Promotion_DiscountID foreign key (DiscountID) references Discount(DiscountID);

alter table BillAddOns add constraint fk_BillAddOns_BillID foreign key (BillID) references Bill(BillID);
alter table BillAddOns add constraint fk_BillAddOns_AddOnsID foreign key (AddOnsID) references AddOns(AddOnsID);

alter table ReservationBill add constraint fk_ReservationBill_ReservationID foreign key (ReservationID) references Reservation(ReservationID);
alter table ReservationBill add constraint fk_ReservationBill_BillID foreign key (BillID) references Bill(BillID);