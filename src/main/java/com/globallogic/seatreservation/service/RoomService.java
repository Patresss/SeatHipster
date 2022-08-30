package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Room;
import com.globallogic.seatreservation.repository.RoomRepository;
import com.globallogic.seatreservation.service.dto.RoomDto;
import com.globallogic.seatreservation.service.mapper.RoomMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Room}.
 */
@Service
@Transactional
public class RoomService {

    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    /**
     * Save a room.
     *
     * @param roomDto the entity to save.
     * @return the persisted entity.
     */
    public RoomDto save(RoomDto roomDto) {
        log.debug("Request to save Room : {}", roomDto);
        Room room = roomMapper.toEntity(roomDto);
        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    /**
     * Update a room.
     *
     * @param roomDto the entity to save.
     * @return the persisted entity.
     */
    public RoomDto update(RoomDto roomDto) {
        log.debug("Request to save Room : {}", roomDto);
        Room room = roomMapper.toEntity(roomDto);
        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    /**
     * Partially update a room.
     *
     * @param roomDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RoomDto> partialUpdate(RoomDto roomDto) {
        log.debug("Request to partially update Room : {}", roomDto);

        return roomRepository
            .findById(roomDto.getId())
            .map(existingRoom -> {
                roomMapper.partialUpdate(existingRoom, roomDto);

                return existingRoom;
            })
            .map(roomRepository::save)
            .map(roomMapper::toDto);
    }

    /**
     * Get all the rooms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RoomDto> findAll(Pageable pageable) {
        log.debug("Request to get all Rooms");
        return roomRepository.findAll(pageable).map(roomMapper::toDto);
    }

    /**
     * Get one room by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RoomDto> findOne(Long id) {
        log.debug("Request to get Room : {}", id);
        return roomRepository.findById(id).map(roomMapper::toDto);
    }

    /**
     * Delete the room by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Room : {}", id);
        roomRepository.deleteById(id);
    }
}
