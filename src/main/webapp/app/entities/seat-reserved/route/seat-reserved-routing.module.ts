import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SeatReservedComponent } from '../list/seat-reserved.component';
import { SeatReservedDetailComponent } from '../detail/seat-reserved-detail.component';
import { SeatReservedUpdateComponent } from '../update/seat-reserved-update.component';
import { SeatReservedRoutingResolveService } from './seat-reserved-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const seatReservedRoute: Routes = [
  {
    path: '',
    component: SeatReservedComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SeatReservedDetailComponent,
    resolve: {
      seatReserved: SeatReservedRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SeatReservedUpdateComponent,
    resolve: {
      seatReserved: SeatReservedRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SeatReservedUpdateComponent,
    resolve: {
      seatReserved: SeatReservedRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(seatReservedRoute)],
  exports: [RouterModule],
})
export class SeatReservedRoutingModule {}
