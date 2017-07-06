
use HotelReservations;

-- This is the query for rooms that are available on a specific date
select RoomID, RoomSizeType
from Room 
inner join RoomType as rt
on Room.RoomTypeID = rt.RoomTypeID
inner join RoomSize as rs
on rt.RoomSizeID = rs.RoomSizeID
where RoomID not in
(select r.RoomID -- , RoomTypeID, RoomSizeType 
from Room as r 
left join RoomReservation as rr on r.RoomID = rr.RoomID 
left join Reservation as re on rr.ReservationID = re.ReservationID
where StartDate <= '2017/7/5' and EndDate >= '2017/7/10');

-- This query shows the rooms that have been reserved by a specific customer
select r.RoomID 
from Room as r
left join RoomReservation as rr on r.RoomID = rr.RoomID
left join Reservation as re on rr.ReservationID = re.ReservationID
left join Customer as c on re.CustomerID = c.CustomerID
where c.FirstName = 'Paul' and c.LastName = 'Sholar';

-- This query shows the rooms that have a specific amenity type
select r.RoomID, a.AmenityName, a.AmenityRate
from Room as r
left join RoomType as rt on r.RoomTypeID = rt.RoomTypeID
left join RoomTypeAmenities as rta on rt.RoomTypeID = rta.RoomTypeID
left join Amenities as a on rta.AmenitiesID = a.AmenitiesID
where a.AmenityName = 'Fridge';

-- This query shows the reservations that will be ending today including the room number's
select res.ReservationID, rr.RoomID
from Reservation as res
inner join RoomReservation as rr on res.ReservationID = rr.ReservationID
where EndDate = '2017/7/1';