package com.globallogic.seatreservation.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.globallogic.seatreservation.domain.Room} entity.
 */
public class RoomDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private FloorDto floor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FloorDto getFloor() {
        return floor;
    }

    public void setFloor(FloorDto floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomDto)) {
            return false;
        }

        RoomDto roomDto = (RoomDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, roomDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoomDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", floor=" + getFloor() +
            "}";
    }
}
