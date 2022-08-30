package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Room;
import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.service.dto.RoomDto;
import com.globallogic.seatreservation.service.dto.SeatDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Seat} and its DTO {@link SeatDto}.
 */
@Mapper(componentModel = "spring")
public interface SeatMapper extends EntityMapper<SeatDto, Seat> {
    @Mapping(target = "room", source = "room", qualifiedByName = "roomId")
    SeatDto toDto(Seat s);

    @Named("roomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RoomDto toDtoRoomId(Room room);
}
