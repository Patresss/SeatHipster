import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SeatReservedComponent } from './list/seat-reserved.component';
import { SeatReservedDetailComponent } from './detail/seat-reserved-detail.component';
import { SeatReservedUpdateComponent } from './update/seat-reserved-update.component';
import { SeatReservedDeleteDialogComponent } from './delete/seat-reserved-delete-dialog.component';
import { SeatReservedRoutingModule } from './route/seat-reserved-routing.module';

@NgModule({
  imports: [SharedModule, SeatReservedRoutingModule],
  declarations: [SeatReservedComponent, SeatReservedDetailComponent, SeatReservedUpdateComponent, SeatReservedDeleteDialogComponent],
})
export class SeatReservedModule {}
