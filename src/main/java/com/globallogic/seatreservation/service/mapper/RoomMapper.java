package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Floor;
import com.globallogic.seatreservation.domain.Room;
import com.globallogic.seatreservation.service.dto.FloorDto;
import com.globallogic.seatreservation.service.dto.RoomDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Room} and its DTO {@link RoomDto}.
 */
@Mapper(componentModel = "spring")
public interface RoomMapper extends EntityMapper<RoomDto, Room> {
    @Mapping(target = "floor", source = "floor", qualifiedByName = "floorId")
    RoomDto toDto(Room s);

    @Named("floorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FloorDto toDtoFloorId(Floor floor);
}
