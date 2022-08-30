package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Equipment;
import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.service.dto.EquipmentDto;
import com.globallogic.seatreservation.service.dto.SeatDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDto}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper extends EntityMapper<EquipmentDto, Equipment> {
    @Mapping(target = "seat", source = "seat", qualifiedByName = "seatId")
    EquipmentDto toDto(Equipment s);

    @Named("seatId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SeatDto toDtoSeatId(Seat seat);
}
