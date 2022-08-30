package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Location;
import com.globallogic.seatreservation.repository.LocationRepository;
import com.globallogic.seatreservation.service.dto.LocationDto;
import com.globallogic.seatreservation.service.mapper.LocationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Location}.
 */
@Service
@Transactional
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    /**
     * Save a location.
     *
     * @param locationDto the entity to save.
     * @return the persisted entity.
     */
    public LocationDto save(LocationDto locationDto) {
        log.debug("Request to save Location : {}", locationDto);
        Location location = locationMapper.toEntity(locationDto);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    /**
     * Update a location.
     *
     * @param locationDto the entity to save.
     * @return the persisted entity.
     */
    public LocationDto update(LocationDto locationDto) {
        log.debug("Request to save Location : {}", locationDto);
        Location location = locationMapper.toEntity(locationDto);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    /**
     * Partially update a location.
     *
     * @param locationDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LocationDto> partialUpdate(LocationDto locationDto) {
        log.debug("Request to partially update Location : {}", locationDto);

        return locationRepository
            .findById(locationDto.getId())
            .map(existingLocation -> {
                locationMapper.partialUpdate(existingLocation, locationDto);

                return existingLocation;
            })
            .map(locationRepository::save)
            .map(locationMapper::toDto);
    }

    /**
     * Get all the locations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LocationDto> findAll(Pageable pageable) {
        log.debug("Request to get all Locations");
        return locationRepository.findAll(pageable).map(locationMapper::toDto);
    }

    /**
     * Get one location by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LocationDto> findOne(Long id) {
        log.debug("Request to get Location : {}", id);
        return locationRepository.findById(id).map(locationMapper::toDto);
    }

    /**
     * Delete the location by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Location : {}", id);
        locationRepository.deleteById(id);
    }
}
