import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'location',
        data: { pageTitle: 'seatReservationApp.location.home.title' },
        loadChildren: () => import('./location/location.module').then(m => m.LocationModule),
      },
      {
        path: 'building',
        data: { pageTitle: 'seatReservationApp.building.home.title' },
        loadChildren: () => import('./building/building.module').then(m => m.BuildingModule),
      },
      {
        path: 'floor',
        data: { pageTitle: 'seatReservationApp.floor.home.title' },
        loadChildren: () => import('./floor/floor.module').then(m => m.FloorModule),
      },
      {
        path: 'room',
        data: { pageTitle: 'seatReservationApp.room.home.title' },
        loadChildren: () => import('./room/room.module').then(m => m.RoomModule),
      },
      {
        path: 'seat',
        data: { pageTitle: 'seatReservationApp.seat.home.title' },
        loadChildren: () => import('./seat/seat.module').then(m => m.SeatModule),
      },
      {
        path: 'equipment',
        data: { pageTitle: 'seatReservationApp.equipment.home.title' },
        loadChildren: () => import('./equipment/equipment.module').then(m => m.EquipmentModule),
      },
      {
        path: 'seat-reserved',
        data: { pageTitle: 'seatReservationApp.seatReserved.home.title' },
        loadChildren: () => import('./seat-reserved/seat-reserved.module').then(m => m.SeatReservedModule),
      },
      {
        path: 'address',
        data: { pageTitle: 'seatReservationApp.address.home.title' },
        loadChildren: () => import('./address/address.module').then(m => m.AddressModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
