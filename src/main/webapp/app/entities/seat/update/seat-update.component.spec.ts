import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SeatFormService } from './seat-form.service';
import { SeatService } from '../service/seat.service';
import { ISeat } from '../seat.model';
import { IRoom } from 'app/entities/room/room.model';
import { RoomService } from 'app/entities/room/service/room.service';

import { SeatUpdateComponent } from './seat-update.component';

describe('Seat Management Update Component', () => {
  let comp: SeatUpdateComponent;
  let fixture: ComponentFixture<SeatUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let seatFormService: SeatFormService;
  let seatService: SeatService;
  let roomService: RoomService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SeatUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(SeatUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SeatUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    seatFormService = TestBed.inject(SeatFormService);
    seatService = TestBed.inject(SeatService);
    roomService = TestBed.inject(RoomService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Room query and add missing value', () => {
      const seat: ISeat = { id: 456 };
      const room: IRoom = { id: 11994 };
      seat.room = room;

      const roomCollection: IRoom[] = [{ id: 23235 }];
      jest.spyOn(roomService, 'query').mockReturnValue(of(new HttpResponse({ body: roomCollection })));
      const additionalRooms = [room];
      const expectedCollection: IRoom[] = [...additionalRooms, ...roomCollection];
      jest.spyOn(roomService, 'addRoomToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ seat });
      comp.ngOnInit();

      expect(roomService.query).toHaveBeenCalled();
      expect(roomService.addRoomToCollectionIfMissing).toHaveBeenCalledWith(
        roomCollection,
        ...additionalRooms.map(expect.objectContaining)
      );
      expect(comp.roomsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const seat: ISeat = { id: 456 };
      const room: IRoom = { id: 8633 };
      seat.room = room;

      activatedRoute.data = of({ seat });
      comp.ngOnInit();

      expect(comp.roomsSharedCollection).toContain(room);
      expect(comp.seat).toEqual(seat);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeat>>();
      const seat = { id: 123 };
      jest.spyOn(seatFormService, 'getSeat').mockReturnValue(seat);
      jest.spyOn(seatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: seat }));
      saveSubject.complete();

      // THEN
      expect(seatFormService.getSeat).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(seatService.update).toHaveBeenCalledWith(expect.objectContaining(seat));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeat>>();
      const seat = { id: 123 };
      jest.spyOn(seatFormService, 'getSeat').mockReturnValue({ id: null });
      jest.spyOn(seatService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seat: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: seat }));
      saveSubject.complete();

      // THEN
      expect(seatFormService.getSeat).toHaveBeenCalled();
      expect(seatService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeat>>();
      const seat = { id: 123 };
      jest.spyOn(seatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(seatService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareRoom', () => {
      it('Should forward to roomService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(roomService, 'compareRoom');
        comp.compareRoom(entity, entity2);
        expect(roomService.compareRoom).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
