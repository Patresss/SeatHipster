package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.service.dto.SeatDto;
import com.globallogic.seatreservation.service.dto.SeatReservedDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SeatReserved} and its DTO {@link SeatReservedDto}.
 */
@Mapper(componentModel = "spring")
public interface SeatReservedMapper extends EntityMapper<SeatReservedDto, SeatReserved> {
    @Mapping(target = "seat", source = "seat", qualifiedByName = "seatId")
    SeatReservedDto toDto(SeatReserved s);

    @Named("seatId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SeatDto toDtoSeatId(Seat seat);
}
