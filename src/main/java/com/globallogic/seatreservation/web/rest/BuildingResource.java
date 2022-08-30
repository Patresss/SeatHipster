package com.globallogic.seatreservation.web.rest;

import com.globallogic.seatreservation.repository.BuildingRepository;
import com.globallogic.seatreservation.service.BuildingService;
import com.globallogic.seatreservation.service.dto.BuildingDto;
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
 * REST controller for managing {@link com.globallogic.seatreservation.domain.Building}.
 */
@RestController
@RequestMapping("/api")
public class BuildingResource {

    private final Logger log = LoggerFactory.getLogger(BuildingResource.class);

    private static final String ENTITY_NAME = "building";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuildingService buildingService;

    private final BuildingRepository buildingRepository;

    public BuildingResource(BuildingService buildingService, BuildingRepository buildingRepository) {
        this.buildingService = buildingService;
        this.buildingRepository = buildingRepository;
    }

    /**
     * {@code POST  /buildings} : Create a new building.
     *
     * @param buildingDto the buildingDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buildingDto, or with status {@code 400 (Bad Request)} if the building has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buildings")
    public ResponseEntity<BuildingDto> createBuilding(@Valid @RequestBody BuildingDto buildingDto) throws URISyntaxException {
        log.debug("REST request to save Building : {}", buildingDto);
        if (buildingDto.getId() != null) {
            throw new BadRequestAlertException("A new building cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuildingDto result = buildingService.save(buildingDto);
        return ResponseEntity
            .created(new URI("/api/buildings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buildings/:id} : Updates an existing building.
     *
     * @param id the id of the buildingDto to save.
     * @param buildingDto the buildingDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buildingDto,
     * or with status {@code 400 (Bad Request)} if the buildingDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buildingDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buildings/{id}")
    public ResponseEntity<BuildingDto> updateBuilding(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BuildingDto buildingDto
    ) throws URISyntaxException {
        log.debug("REST request to update Building : {}, {}", id, buildingDto);
        if (buildingDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, buildingDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!buildingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BuildingDto result = buildingService.update(buildingDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, buildingDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /buildings/:id} : Partial updates given fields of an existing building, field will ignore if it is null
     *
     * @param id the id of the buildingDto to save.
     * @param buildingDto the buildingDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buildingDto,
     * or with status {@code 400 (Bad Request)} if the buildingDto is not valid,
     * or with status {@code 404 (Not Found)} if the buildingDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the buildingDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/buildings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BuildingDto> partialUpdateBuilding(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BuildingDto buildingDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Building partially : {}, {}", id, buildingDto);
        if (buildingDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, buildingDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!buildingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BuildingDto> result = buildingService.partialUpdate(buildingDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, buildingDto.getId().toString())
        );
    }

    /**
     * {@code GET  /buildings} : get all the buildings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buildings in body.
     */
    @GetMapping("/buildings")
    public ResponseEntity<List<BuildingDto>> getAllBuildings(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Buildings");
        Page<BuildingDto> page = buildingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /buildings/:id} : get the "id" building.
     *
     * @param id the id of the buildingDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buildingDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingDto> getBuilding(@PathVariable Long id) {
        log.debug("REST request to get Building : {}", id);
        Optional<BuildingDto> buildingDto = buildingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buildingDto);
    }

    /**
     * {@code DELETE  /buildings/:id} : delete the "id" building.
     *
     * @param id the id of the buildingDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        log.debug("REST request to delete Building : {}", id);
        buildingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
