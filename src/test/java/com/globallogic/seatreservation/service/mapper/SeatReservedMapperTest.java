package com.globallogic.seatreservation.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatReservedMapperTest {

    private SeatReservedMapper seatReservedMapper;

    @BeforeEach
    public void setUp() {
        seatReservedMapper = new SeatReservedMapperImpl();
    }
}
