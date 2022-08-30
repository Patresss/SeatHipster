package com.globallogic.seatreservation.service.dto;

import com.globallogic.seatreservation.domain.enumeration.EquipmentType;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.globallogic.seatreservation.domain.Equipment} entity.
 */
public class EquipmentDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private EquipmentType type;

    private SeatDto seat;

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

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public SeatDto getSeat() {
        return seat;
    }

    public void setSeat(SeatDto seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipmentDto)) {
            return false;
        }

        EquipmentDto equipmentDto = (EquipmentDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, equipmentDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", seat=" + getSeat() +
            "}";
    }
}
