package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Location;
import com.globallogic.seatreservation.service.dto.LocationDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDto}.
 */
@Mapper(componentModel = "spring")
public interface LocationMapper extends EntityMapper<LocationDto, Location> {}
