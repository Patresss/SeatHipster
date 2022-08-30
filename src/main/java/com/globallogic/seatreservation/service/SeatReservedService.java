package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.repository.SeatReservedRepository;
import com.globallogic.seatreservation.service.dto.SeatReservedDto;
import com.globallogic.seatreservation.service.mapper.SeatReservedMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SeatReserved}.
 */
@Service
@Transactional
public class SeatReservedService {

    private final Logger log = LoggerFactory.getLogger(SeatReservedService.class);

    private final SeatReservedRepository seatReservedRepository;

    private final SeatReservedMapper seatReservedMapper;

    public SeatReservedService(SeatReservedRepository seatReservedRepository, SeatReservedMapper seatReservedMapper) {
        this.seatReservedRepository = seatReservedRepository;
        this.seatReservedMapper = seatReservedMapper;
    }

    /**
     * Save a seatReserved.
     *
     * @param seatReservedDto the entity to save.
     * @return the persisted entity.
     */
    public SeatReservedDto save(SeatReservedDto seatReservedDto) {
        log.debug("Request to save SeatReserved : {}", seatReservedDto);
        SeatReserved seatReserved = seatReservedMapper.toEntity(seatReservedDto);
        seatReserved = seatReservedRepository.save(seatReserved);
        return seatReservedMapper.toDto(seatReserved);
    }

    /**
     * Update a seatReserved.
     *
     * @param seatReservedDto the entity to save.
     * @return the persisted entity.
     */
    public SeatReservedDto update(SeatReservedDto seatReservedDto) {
        log.debug("Request to save SeatReserved : {}", seatReservedDto);
        SeatReserved seatReserved = seatReservedMapper.toEntity(seatReservedDto);
        seatReserved = seatReservedRepository.save(seatReserved);
        return seatReservedMapper.toDto(seatReserved);
    }

    /**
     * Partially update a seatReserved.
     *
     * @param seatReservedDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SeatReservedDto> partialUpdate(SeatReservedDto seatReservedDto) {
        log.debug("Request to partially update SeatReserved : {}", seatReservedDto);

        return seatReservedRepository
            .findById(seatReservedDto.getId())
            .map(existingSeatReserved -> {
                seatReservedMapper.partialUpdate(existingSeatReserved, seatReservedDto);

                return existingSeatReserved;
            })
            .map(seatReservedRepository::save)
            .map(seatReservedMapper::toDto);
    }

    /**
     * Get all the seatReserveds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SeatReservedDto> findAll(Pageable pageable) {
        log.debug("Request to get all SeatReserveds");
        return seatReservedRepository.findAll(pageable).map(seatReservedMapper::toDto);
    }

    /**
     * Get one seatReserved by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SeatReservedDto> findOne(Long id) {
        log.debug("Request to get SeatReserved : {}", id);
        return seatReservedRepository.findById(id).map(seatReservedMapper::toDto);
    }

    /**
     * Delete the seatReserved by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SeatReserved : {}", id);
        seatReservedRepository.deleteById(id);
    }
}
