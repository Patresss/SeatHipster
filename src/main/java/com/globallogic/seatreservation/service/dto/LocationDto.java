package com.globallogic.seatreservation.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.globallogic.seatreservation.domain.Location} entity.
 */
public class LocationDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocationDto)) {
            return false;
        }

        LocationDto locationDto = (LocationDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, locationDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocationDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
