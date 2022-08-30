package com.globallogic.seatreservation.web.rest;

import com.globallogic.seatreservation.repository.FloorRepository;
import com.globallogic.seatreservation.service.FloorService;
import com.globallogic.seatreservation.service.dto.FloorDto;
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
 * REST controller for managing {@link com.globallogic.seatreservation.domain.Floor}.
 */
@RestController
@RequestMapping("/api")
public class FloorResource {

    private final Logger log = LoggerFactory.getLogger(FloorResource.class);

    private static final String ENTITY_NAME = "floor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FloorService floorService;

    private final FloorRepository floorRepository;

    public FloorResource(FloorService floorService, FloorRepository floorRepository) {
        this.floorService = floorService;
        this.floorRepository = floorRepository;
    }

    /**
     * {@code POST  /floors} : Create a new floor.
     *
     * @param floorDto the floorDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new floorDto, or with status {@code 400 (Bad Request)} if the floor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/floors")
    public ResponseEntity<FloorDto> createFloor(@Valid @RequestBody FloorDto floorDto) throws URISyntaxException {
        log.debug("REST request to save Floor : {}", floorDto);
        if (floorDto.getId() != null) {
            throw new BadRequestAlertException("A new floor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FloorDto result = floorService.save(floorDto);
        return ResponseEntity
            .created(new URI("/api/floors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /floors/:id} : Updates an existing floor.
     *
     * @param id the id of the floorDto to save.
     * @param floorDto the floorDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated floorDto,
     * or with status {@code 400 (Bad Request)} if the floorDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the floorDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/floors/{id}")
    public ResponseEntity<FloorDto> updateFloor(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FloorDto floorDto
    ) throws URISyntaxException {
        log.debug("REST request to update Floor : {}, {}", id, floorDto);
        if (floorDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, floorDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!floorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FloorDto result = floorService.update(floorDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, floorDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /floors/:id} : Partial updates given fields of an existing floor, field will ignore if it is null
     *
     * @param id the id of the floorDto to save.
     * @param floorDto the floorDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated floorDto,
     * or with status {@code 400 (Bad Request)} if the floorDto is not valid,
     * or with status {@code 404 (Not Found)} if the floorDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the floorDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/floors/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FloorDto> partialUpdateFloor(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FloorDto floorDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Floor partially : {}, {}", id, floorDto);
        if (floorDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, floorDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!floorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FloorDto> result = floorService.partialUpdate(floorDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, floorDto.getId().toString())
        );
    }

    /**
     * {@code GET  /floors} : get all the floors.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of floors in body.
     */
    @GetMapping("/floors")
    public ResponseEntity<List<FloorDto>> getAllFloors(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Floors");
        Page<FloorDto> page = floorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /floors/:id} : get the "id" floor.
     *
     * @param id the id of the floorDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the floorDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/floors/{id}")
    public ResponseEntity<FloorDto> getFloor(@PathVariable Long id) {
        log.debug("REST request to get Floor : {}", id);
        Optional<FloorDto> floorDto = floorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(floorDto);
    }

    /**
     * {@code DELETE  /floors/:id} : delete the "id" floor.
     *
     * @param id the id of the floorDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/floors/{id}")
    public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
        log.debug("REST request to delete Floor : {}", id);
        floorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
