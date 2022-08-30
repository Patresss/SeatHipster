package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Equipment;
import com.globallogic.seatreservation.repository.EquipmentRepository;
import com.globallogic.seatreservation.service.dto.EquipmentDto;
import com.globallogic.seatreservation.service.mapper.EquipmentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Equipment}.
 */
@Service
@Transactional
public class EquipmentService {

    private final Logger log = LoggerFactory.getLogger(EquipmentService.class);

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    /**
     * Save a equipment.
     *
     * @param equipmentDto the entity to save.
     * @return the persisted entity.
     */
    public EquipmentDto save(EquipmentDto equipmentDto) {
        log.debug("Request to save Equipment : {}", equipmentDto);
        Equipment equipment = equipmentMapper.toEntity(equipmentDto);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    /**
     * Update a equipment.
     *
     * @param equipmentDto the entity to save.
     * @return the persisted entity.
     */
    public EquipmentDto update(EquipmentDto equipmentDto) {
        log.debug("Request to save Equipment : {}", equipmentDto);
        Equipment equipment = equipmentMapper.toEntity(equipmentDto);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(equipment);
    }

    /**
     * Partially update a equipment.
     *
     * @param equipmentDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EquipmentDto> partialUpdate(EquipmentDto equipmentDto) {
        log.debug("Request to partially update Equipment : {}", equipmentDto);

        return equipmentRepository
            .findById(equipmentDto.getId())
            .map(existingEquipment -> {
                equipmentMapper.partialUpdate(existingEquipment, equipmentDto);

                return existingEquipment;
            })
            .map(equipmentRepository::save)
            .map(equipmentMapper::toDto);
    }

    /**
     * Get all the equipment.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EquipmentDto> findAll(Pageable pageable) {
        log.debug("Request to get all Equipment");
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toDto);
    }

    /**
     * Get one equipment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EquipmentDto> findOne(Long id) {
        log.debug("Request to get Equipment : {}", id);
        return equipmentRepository.findById(id).map(equipmentMapper::toDto);
    }

    /**
     * Delete the equipment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Equipment : {}", id);
        equipmentRepository.deleteById(id);
    }
}
