import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { SeatReservedFormService, SeatReservedFormGroup } from './seat-reserved-form.service';
import { ISeatReserved } from '../seat-reserved.model';
import { SeatReservedService } from '../service/seat-reserved.service';
import { ISeat } from 'app/entities/seat/seat.model';
import { SeatService } from 'app/entities/seat/service/seat.service';

@Component({
  selector: 'jhi-seat-reserved-update',
  templateUrl: './seat-reserved-update.component.html',
})
export class SeatReservedUpdateComponent implements OnInit {
  isSaving = false;
  seatReserved: ISeatReserved | null = null;

  seatsSharedCollection: ISeat[] = [];

  editForm: SeatReservedFormGroup = this.seatReservedFormService.createSeatReservedFormGroup();

  constructor(
    protected seatReservedService: SeatReservedService,
    protected seatReservedFormService: SeatReservedFormService,
    protected seatService: SeatService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareSeat = (o1: ISeat | null, o2: ISeat | null): boolean => this.seatService.compareSeat(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ seatReserved }) => {
      this.seatReserved = seatReserved;
      if (seatReserved) {
        this.updateForm(seatReserved);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const seatReserved = this.seatReservedFormService.getSeatReserved(this.editForm);
    if (seatReserved.id !== null) {
      this.subscribeToSaveResponse(this.seatReservedService.update(seatReserved));
    } else {
      this.subscribeToSaveResponse(this.seatReservedService.create(seatReserved));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISeatReserved>>): void {
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

  protected updateForm(seatReserved: ISeatReserved): void {
    this.seatReserved = seatReserved;
    this.seatReservedFormService.resetForm(this.editForm, seatReserved);

    this.seatsSharedCollection = this.seatService.addSeatToCollectionIfMissing<ISeat>(this.seatsSharedCollection, seatReserved.seat);
  }

  protected loadRelationshipsOptions(): void {
    this.seatService
      .query()
      .pipe(map((res: HttpResponse<ISeat[]>) => res.body ?? []))
      .pipe(map((seats: ISeat[]) => this.seatService.addSeatToCollectionIfMissing<ISeat>(seats, this.seatReserved?.seat)))
      .subscribe((seats: ISeat[]) => (this.seatsSharedCollection = seats));
  }
}
