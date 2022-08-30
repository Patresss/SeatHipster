package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Address;
import com.globallogic.seatreservation.service.dto.AddressDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDto}.
 */
@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDto, Address> {}
