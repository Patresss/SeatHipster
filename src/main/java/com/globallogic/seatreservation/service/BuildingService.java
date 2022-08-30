package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Building;
import com.globallogic.seatreservation.repository.BuildingRepository;
import com.globallogic.seatreservation.service.dto.BuildingDto;
import com.globallogic.seatreservation.service.mapper.BuildingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Building}.
 */
@Service
@Transactional
public class BuildingService {

    private final Logger log = LoggerFactory.getLogger(BuildingService.class);

    private final BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    public BuildingService(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }

    /**
     * Save a building.
     *
     * @param buildingDto the entity to save.
     * @return the persisted entity.
     */
    public BuildingDto save(BuildingDto buildingDto) {
        log.debug("Request to save Building : {}", buildingDto);
        Building building = buildingMapper.toEntity(buildingDto);
        building = buildingRepository.save(building);
        return buildingMapper.toDto(building);
    }

    /**
     * Update a building.
     *
     * @param buildingDto the entity to save.
     * @return the persisted entity.
     */
    public BuildingDto update(BuildingDto buildingDto) {
        log.debug("Request to save Building : {}", buildingDto);
        Building building = buildingMapper.toEntity(buildingDto);
        building = buildingRepository.save(building);
        return buildingMapper.toDto(building);
    }

    /**
     * Partially update a building.
     *
     * @param buildingDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BuildingDto> partialUpdate(BuildingDto buildingDto) {
        log.debug("Request to partially update Building : {}", buildingDto);

        return buildingRepository
            .findById(buildingDto.getId())
            .map(existingBuilding -> {
                buildingMapper.partialUpdate(existingBuilding, buildingDto);

                return existingBuilding;
            })
            .map(buildingRepository::save)
            .map(buildingMapper::toDto);
    }

    /**
     * Get all the buildings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BuildingDto> findAll(Pageable pageable) {
        log.debug("Request to get all Buildings");
        return buildingRepository.findAll(pageable).map(buildingMapper::toDto);
    }

    /**
     * Get one building by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuildingDto> findOne(Long id) {
        log.debug("Request to get Building : {}", id);
        return buildingRepository.findById(id).map(buildingMapper::toDto);
    }

    /**
     * Delete the building by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Building : {}", id);
        buildingRepository.deleteById(id);
    }
}
