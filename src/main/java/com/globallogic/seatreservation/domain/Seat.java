package com.globallogic.seatreservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Seat.
 */
@Entity
@Table(name = "seat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AvailabilityStatus status;

    @OneToMany(mappedBy = "seat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "seat" }, allowSetters = true)
    private Set<Equipment> equipments = new HashSet<>();

    @OneToMany(mappedBy = "seat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "seat" }, allowSetters = true)
    private Set<SeatReserved> seatReserveds = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "seats", "floor" }, allowSetters = true)
    private Room room;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Seat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Seat name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Seat description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AvailabilityStatus getStatus() {
        return this.status;
    }

    public Seat status(AvailabilityStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    public Set<Equipment> getEquipments() {
        return this.equipments;
    }

    public void setEquipments(Set<Equipment> equipment) {
        if (this.equipments != null) {
            this.equipments.forEach(i -> i.setSeat(null));
        }
        if (equipment != null) {
            equipment.forEach(i -> i.setSeat(this));
        }
        this.equipments = equipment;
    }

    public Seat equipments(Set<Equipment> equipment) {
        this.setEquipments(equipment);
        return this;
    }

    public Seat addEquipments(Equipment equipment) {
        this.equipments.add(equipment);
        equipment.setSeat(this);
        return this;
    }

    public Seat removeEquipments(Equipment equipment) {
        this.equipments.remove(equipment);
        equipment.setSeat(null);
        return this;
    }

    public Set<SeatReserved> getSeatReserveds() {
        return this.seatReserveds;
    }

    public void setSeatReserveds(Set<SeatReserved> seatReserveds) {
        if (this.seatReserveds != null) {
            this.seatReserveds.forEach(i -> i.setSeat(null));
        }
        if (seatReserveds != null) {
            seatReserveds.forEach(i -> i.setSeat(this));
        }
        this.seatReserveds = seatReserveds;
    }

    public Seat seatReserveds(Set<SeatReserved> seatReserveds) {
        this.setSeatReserveds(seatReserveds);
        return this;
    }

    public Seat addSeatReserved(SeatReserved seatReserved) {
        this.seatReserveds.add(seatReserved);
        seatReserved.setSeat(this);
        return this;
    }

    public Seat removeSeatReserved(SeatReserved seatReserved) {
        this.seatReserveds.remove(seatReserved);
        seatReserved.setSeat(null);
        return this;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Seat room(Room room) {
        this.setRoom(room);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Seat)) {
            return false;
        }
        return id != null && id.equals(((Seat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Seat{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
