package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Address;
import com.globallogic.seatreservation.domain.Building;
import com.globallogic.seatreservation.domain.Location;
import com.globallogic.seatreservation.service.dto.AddressDto;
import com.globallogic.seatreservation.service.dto.BuildingDto;
import com.globallogic.seatreservation.service.dto.LocationDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Building} and its DTO {@link BuildingDto}.
 */
@Mapper(componentModel = "spring")
public interface BuildingMapper extends EntityMapper<BuildingDto, Building> {
    @Mapping(target = "address", source = "address", qualifiedByName = "addressId")
    @Mapping(target = "location", source = "location", qualifiedByName = "locationId")
    BuildingDto toDto(Building s);

    @Named("addressId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AddressDto toDtoAddressId(Address address);

    @Named("locationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LocationDto toDtoLocationId(Location location);
}
