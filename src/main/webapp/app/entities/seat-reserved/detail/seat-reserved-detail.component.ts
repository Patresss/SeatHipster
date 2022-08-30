import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeatReserved } from '../seat-reserved.model';

@Component({
  selector: 'jhi-seat-reserved-detail',
  templateUrl: './seat-reserved-detail.component.html',
})
export class SeatReservedDetailComponent implements OnInit {
  seatReserved: ISeatReserved | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ seatReserved }) => {
      this.seatReserved = seatReserved;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
