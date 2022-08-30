package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Floor;
import com.globallogic.seatreservation.repository.FloorRepository;
import com.globallogic.seatreservation.service.dto.FloorDto;
import com.globallogic.seatreservation.service.mapper.FloorMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Floor}.
 */
@Service
@Transactional
public class FloorService {

    private final Logger log = LoggerFactory.getLogger(FloorService.class);

    private final FloorRepository floorRepository;

    private final FloorMapper floorMapper;

    public FloorService(FloorRepository floorRepository, FloorMapper floorMapper) {
        this.floorRepository = floorRepository;
        this.floorMapper = floorMapper;
    }

    /**
     * Save a floor.
     *
     * @param floorDto the entity to save.
     * @return the persisted entity.
     */
    public FloorDto save(FloorDto floorDto) {
        log.debug("Request to save Floor : {}", floorDto);
        Floor floor = floorMapper.toEntity(floorDto);
        floor = floorRepository.save(floor);
        return floorMapper.toDto(floor);
    }

    /**
     * Update a floor.
     *
     * @param floorDto the entity to save.
     * @return the persisted entity.
     */
    public FloorDto update(FloorDto floorDto) {
        log.debug("Request to save Floor : {}", floorDto);
        Floor floor = floorMapper.toEntity(floorDto);
        floor = floorRepository.save(floor);
        return floorMapper.toDto(floor);
    }

    /**
     * Partially update a floor.
     *
     * @param floorDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FloorDto> partialUpdate(FloorDto floorDto) {
        log.debug("Request to partially update Floor : {}", floorDto);

        return floorRepository
            .findById(floorDto.getId())
            .map(existingFloor -> {
                floorMapper.partialUpdate(existingFloor, floorDto);

                return existingFloor;
            })
            .map(floorRepository::save)
            .map(floorMapper::toDto);
    }

    /**
     * Get all the floors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FloorDto> findAll(Pageable pageable) {
        log.debug("Request to get all Floors");
        return floorRepository.findAll(pageable).map(floorMapper::toDto);
    }

    /**
     * Get one floor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FloorDto> findOne(Long id) {
        log.debug("Request to get Floor : {}", id);
        return floorRepository.findById(id).map(floorMapper::toDto);
    }

    /**
     * Delete the floor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Floor : {}", id);
        floorRepository.deleteById(id);
    }
}
