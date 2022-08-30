package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Building;
import com.globallogic.seatreservation.domain.Floor;
import com.globallogic.seatreservation.service.dto.BuildingDto;
import com.globallogic.seatreservation.service.dto.FloorDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Floor} and its DTO {@link FloorDto}.
 */
@Mapper(componentModel = "spring")
public interface FloorMapper extends EntityMapper<FloorDto, Floor> {
    @Mapping(target = "building", source = "building", qualifiedByName = "buildingId")
    FloorDto toDto(Floor s);

    @Named("buildingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BuildingDto toDtoBuildingId(Building building);
}
