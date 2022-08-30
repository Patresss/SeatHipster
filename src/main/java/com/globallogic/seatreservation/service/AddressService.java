package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Address;
import com.globallogic.seatreservation.repository.AddressRepository;
import com.globallogic.seatreservation.service.dto.AddressDto;
import com.globallogic.seatreservation.service.mapper.AddressMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Address}.
 */
@Service
@Transactional
public class AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    /**
     * Save a address.
     *
     * @param addressDto the entity to save.
     * @return the persisted entity.
     */
    public AddressDto save(AddressDto addressDto) {
        log.debug("Request to save Address : {}", addressDto);
        Address address = addressMapper.toEntity(addressDto);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    /**
     * Update a address.
     *
     * @param addressDto the entity to save.
     * @return the persisted entity.
     */
    public AddressDto update(AddressDto addressDto) {
        log.debug("Request to save Address : {}", addressDto);
        Address address = addressMapper.toEntity(addressDto);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    /**
     * Partially update a address.
     *
     * @param addressDto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AddressDto> partialUpdate(AddressDto addressDto) {
        log.debug("Request to partially update Address : {}", addressDto);

        return addressRepository
            .findById(addressDto.getId())
            .map(existingAddress -> {
                addressMapper.partialUpdate(existingAddress, addressDto);

                return existingAddress;
            })
            .map(addressRepository::save)
            .map(addressMapper::toDto);
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AddressDto> findAll(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable).map(addressMapper::toDto);
    }

    /**
     * Get one address by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddressDto> findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findById(id).map(addressMapper::toDto);
    }

    /**
     * Delete the address by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.deleteById(id);
    }
}
