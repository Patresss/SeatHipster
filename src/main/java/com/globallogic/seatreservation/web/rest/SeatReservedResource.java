package com.globallogic.seatreservation.web.rest;

import com.globallogic.seatreservation.repository.SeatReservedRepository;
import com.globallogic.seatreservation.service.SeatReservedService;
import com.globallogic.seatreservation.service.dto.SeatReservedDto;
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
 * REST controller for managing {@link com.globallogic.seatreservation.domain.SeatReserved}.
 */
@RestController
@RequestMapping("/api")
public class SeatReservedResource {

    private final Logger log = LoggerFactory.getLogger(SeatReservedResource.class);

    private static final String ENTITY_NAME = "seatReserved";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeatReservedService seatReservedService;

    private final SeatReservedRepository seatReservedRepository;

    public SeatReservedResource(SeatReservedService seatReservedService, SeatReservedRepository seatReservedRepository) {
        this.seatReservedService = seatReservedService;
        this.seatReservedRepository = seatReservedRepository;
    }

    /**
     * {@code POST  /seat-reserveds} : Create a new seatReserved.
     *
     * @param seatReservedDto the seatReservedDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seatReservedDto, or with status {@code 400 (Bad Request)} if the seatReserved has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/seat-reserveds")
    public ResponseEntity<SeatReservedDto> createSeatReserved(@Valid @RequestBody SeatReservedDto seatReservedDto)
        throws URISyntaxException {
        log.debug("REST request to save SeatReserved : {}", seatReservedDto);
        if (seatReservedDto.getId() != null) {
            throw new BadRequestAlertException("A new seatReserved cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeatReservedDto result = seatReservedService.save(seatReservedDto);
        return ResponseEntity
            .created(new URI("/api/seat-reserveds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /seat-reserveds/:id} : Updates an existing seatReserved.
     *
     * @param id the id of the seatReservedDto to save.
     * @param seatReservedDto the seatReservedDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seatReservedDto,
     * or with status {@code 400 (Bad Request)} if the seatReservedDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seatReservedDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/seat-reserveds/{id}")
    public ResponseEntity<SeatReservedDto> updateSeatReserved(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SeatReservedDto seatReservedDto
    ) throws URISyntaxException {
        log.debug("REST request to update SeatReserved : {}, {}", id, seatReservedDto);
        if (seatReservedDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seatReservedDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seatReservedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SeatReservedDto result = seatReservedService.update(seatReservedDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seatReservedDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /seat-reserveds/:id} : Partial updates given fields of an existing seatReserved, field will ignore if it is null
     *
     * @param id the id of the seatReservedDto to save.
     * @param seatReservedDto the seatReservedDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seatReservedDto,
     * or with status {@code 400 (Bad Request)} if the seatReservedDto is not valid,
     * or with status {@code 404 (Not Found)} if the seatReservedDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the seatReservedDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/seat-reserveds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SeatReservedDto> partialUpdateSeatReserved(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SeatReservedDto seatReservedDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update SeatReserved partially : {}, {}", id, seatReservedDto);
        if (seatReservedDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, seatReservedDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!seatReservedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SeatReservedDto> result = seatReservedService.partialUpdate(seatReservedDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seatReservedDto.getId().toString())
        );
    }

    /**
     * {@code GET  /seat-reserveds} : get all the seatReserveds.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of seatReserveds in body.
     */
    @GetMapping("/seat-reserveds")
    public ResponseEntity<List<SeatReservedDto>> getAllSeatReserveds(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SeatReserveds");
        Page<SeatReservedDto> page = seatReservedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /seat-reserveds/:id} : get the "id" seatReserved.
     *
     * @param id the id of the seatReservedDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seatReservedDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/seat-reserveds/{id}")
    public ResponseEntity<SeatReservedDto> getSeatReserved(@PathVariable Long id) {
        log.debug("REST request to get SeatReserved : {}", id);
        Optional<SeatReservedDto> seatReservedDto = seatReservedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(seatReservedDto);
    }

    /**
     * {@code DELETE  /seat-reserveds/:id} : delete the "id" seatReserved.
     *
     * @param id the id of the seatReservedDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/seat-reserveds/{id}")
    public ResponseEntity<Void> deleteSeatReserved(@PathVariable Long id) {
        log.debug("REST request to delete SeatReserved : {}", id);
        seatReservedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
