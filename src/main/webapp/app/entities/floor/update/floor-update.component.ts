import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { FloorFormService, FloorFormGroup } from './floor-form.service';
import { IFloor } from '../floor.model';
import { FloorService } from '../service/floor.service';
import { IBuilding } from 'app/entities/building/building.model';
import { BuildingService } from 'app/entities/building/service/building.service';

@Component({
  selector: 'jhi-floor-update',
  templateUrl: './floor-update.component.html',
})
export class FloorUpdateComponent implements OnInit {
  isSaving = false;
  floor: IFloor | null = null;

  buildingsSharedCollection: IBuilding[] = [];

  editForm: FloorFormGroup = this.floorFormService.createFloorFormGroup();

  constructor(
    protected floorService: FloorService,
    protected floorFormService: FloorFormService,
    protected buildingService: BuildingService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareBuilding = (o1: IBuilding | null, o2: IBuilding | null): boolean => this.buildingService.compareBuilding(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ floor }) => {
      this.floor = floor;
      if (floor) {
        this.updateForm(floor);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const floor = this.floorFormService.getFloor(this.editForm);
    if (floor.id !== null) {
      this.subscribeToSaveResponse(this.floorService.update(floor));
    } else {
      this.subscribeToSaveResponse(this.floorService.create(floor));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFloor>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(floor: IFloor): void {
    this.floor = floor;
    this.floorFormService.resetForm(this.editForm, floor);

    this.buildingsSharedCollection = this.buildingService.addBuildingToCollectionIfMissing<IBuilding>(
      this.buildingsSharedCollection,
      floor.building
    );
  }

  protected loadRelationshipsOptions(): void {
    this.buildingService
      .query()
      .pipe(map((res: HttpResponse<IBuilding[]>) => res.body ?? []))
      .pipe(
        map((buildings: IBuilding[]) => this.buildingService.addBuildingToCollectionIfMissing<IBuilding>(buildings, this.floor?.building))
      )
      .subscribe((buildings: IBuilding[]) => (this.buildingsSharedCollection = buildings));
  }
}
