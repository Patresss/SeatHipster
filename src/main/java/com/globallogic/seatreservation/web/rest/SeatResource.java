package com.globallogic.seatreservation.web.rest;

import com.globallogic.seatreservation.repository.SeatRepository;
import com.globallogic.seatreservation.service.SeatService;
import com.globallogic.seatreservation.service.dto.SeatDto;
import com.globallogic.seatreservation.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.globallogic.seatreservation.domain.Seat}.
 */
@RestController
@RequestMapping("/api")
public class SeatResource {

    private final Logger log = LoggerFactory.getLogger(SeatResource.class);

    private static final String ENTITY_NAME = "seat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeatService seatService;

    private final SeatRepository seatRepository;

    public SeatResource(SeatService seatService, SeatRepository seatRepository) {
        this.seatService = seatService;
        this.seatRepository = seatRepository;
    }

    /**
     * {@code POST  /seats} : Create a new seat.
     *
     * @param seatDto the seatDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seatDto, or with status {@code 400 (Bad Request)} if the seat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/seats")
    public ResponseEntity<SeatDto> createSeat(@Valid @RequestBody SeatDto seatDto) throws URISyntaxException {
        log.debug("REST request to save Seat : {}", seatDto);
        if (seatDto.getId() != null) {
            throw new BadRequestAlertException("A new seat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeatDto result = seatService.save(seatDto);
        return ResponseEntity
            .created(new URI("/api/seats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /seats/:id} : Updates an existing seat.
     *
     * @param id the id of the seatDto to save.
     * @param seatDto the seatDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seatDto,
     * or with status {@code 400 (Bad Request)} if the seatDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seatDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/seats/{id}")
    public ResponseEntity<SeatDto> updateSeat(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SeatDto seatDto
    ) throws URISyntaxException {
        log.debug("REST request to update Seat : {}, {}", id, seatDto);
        if (seatDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seatDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SeatDto result = seatService.update(seatDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seatDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /seats/:id} : Partial updates given fields of an existing seat, field will ignore if it is null
     *
     * @param id the id of the seatDto to save.
     * @param seatDto the seatDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seatDto,
     * or with status {@code 400 (Bad Request)} if the seatDto is not valid,
     * or with status {@code 404 (Not Found)} if the seatDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the seatDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/seats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SeatDto> partialUpdateSeat(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SeatDto seatDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Seat partially : {}, {}", id, seatDto);
        if (seatDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seatDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SeatDto> result = seatService.partialUpdate(seatDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seatDto.getId().toString())
        );
    }

    /**
     * {@code GET  /seats} : get all the seats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of seats in body.
     */
    @GetMapping("/seats")
    public ResponseEntity<List<SeatDto>> getAllSeats(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Seats");
        Page<SeatDto> page = seatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /seats/:id} : get the "id" seat.
     *
     * @param id the id of the seatDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seatDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/seats/{id}")
    public ResponseEntity<SeatDto> getSeat(@PathVariable Long id) {
        log.debug("REST request to get Seat : {}", id);
        Optional<SeatDto> seatDto = seatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(seatDto);
    }

    /**
     * {@code DELETE  /seats/:id} : delete the "id" seat.
     *
     * @param id the id of the seatDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        log.debug("REST request to delete Seat : {}", id);
        seatService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
