package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.SeatReserved;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SeatReserved entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeatReservedRepository extends JpaRepository<SeatReserved, Long> {}
