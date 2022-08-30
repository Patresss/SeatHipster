package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Equipment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Equipment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {}
