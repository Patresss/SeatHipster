package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.repository.SeatRepository;
import com.globallogic.seatreservation.service.dto.SeatDto;
import com.globallogic.seatreservation.service.mapper.SeatMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Seat}.
 */
@Service
@Transactional
public class SeatService {

    private final Logger log = LoggerFactory.getLogger(SeatService.class);

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    /**
     * Save a seat.
     *
     * @param seatDto the entity to save.
     * @return the persisted entity.
     */
    public SeatDto save(SeatDto seatDto) {
        log.debug("Request to save Seat : {}", seatDto);
        Seat seat = seatMapper.toEntity(seatDto);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    /**
     * Update a seat.
     *
     * @param seatDto the entity to save.
     * @return the persisted entity.
     */
    public SeatDto update(SeatDto seatDto) {
        log.debug("Request to save Seat : {}", seatDto);
        Seat seat = seatMapper.toEntity(seatDto);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    /**
     * Partially update a seat.
     *
     * @param seatDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SeatDto> partialUpdate(SeatDto seatDto) {
        log.debug("Request to partially update Seat : {}", seatDto);

        return seatRepository
            .findById(seatDto.getId())
            .map(existingSeat -> {
                seatMapper.partialUpdate(existingSeat, seatDto);

                return existingSeat;
            })
            .map(seatRepository::save)
            .map(seatMapper::toDto);
    }

    /**
     * Get all the seats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SeatDto> findAll(Pageable pageable) {
        log.debug("Request to get all Seats");
        return seatRepository.findAll(pageable).map(seatMapper::toDto);
    }

    /**
     * Get one seat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SeatDto> findOne(Long id) {
        log.debug("Request to get Seat : {}", id);
        return seatRepository.findById(id).map(seatMapper::toDto);
    }

    /**
     * Delete the seat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Seat : {}", id);
        seatRepository.deleteById(id);
    }
}
