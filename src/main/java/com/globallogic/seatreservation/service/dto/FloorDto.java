package com.globallogic.seatreservation.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.globallogic.seatreservation.domain.Floor} entity.
 */
public class FloorDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer number;

    private BuildingDto building;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BuildingDto getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDto building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FloorDto)) {
            return false;
        }

        FloorDto floorDto = (FloorDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, floorDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FloorDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            ", building=" + getBuilding() +
            "}";
    }
}
