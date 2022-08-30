import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SeatReservedFormService } from './seat-reserved-form.service';
import { SeatReservedService } from '../service/seat-reserved.service';
import { ISeatReserved } from '../seat-reserved.model';
import { ISeat } from 'app/entities/seat/seat.model';
import { SeatService } from 'app/entities/seat/service/seat.service';

import { SeatReservedUpdateComponent } from './seat-reserved-update.component';

describe('SeatReserved Management Update Component', () => {
  let comp: SeatReservedUpdateComponent;
  let fixture: ComponentFixture<SeatReservedUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let seatReservedFormService: SeatReservedFormService;
  let seatReservedService: SeatReservedService;
  let seatService: SeatService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SeatReservedUpdateComponent],
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
      .overrideTemplate(SeatReservedUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SeatReservedUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    seatReservedFormService = TestBed.inject(SeatReservedFormService);
    seatReservedService = TestBed.inject(SeatReservedService);
    seatService = TestBed.inject(SeatService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Seat query and add missing value', () => {
      const seatReserved: ISeatReserved = { id: 456 };
      const seat: ISeat = { id: 82307 };
      seatReserved.seat = seat;

      const seatCollection: ISeat[] = [{ id: 34713 }];
      jest.spyOn(seatService, 'query').mockReturnValue(of(new HttpResponse({ body: seatCollection })));
      const additionalSeats = [seat];
      const expectedCollection: ISeat[] = [...additionalSeats, ...seatCollection];
      jest.spyOn(seatService, 'addSeatToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ seatReserved });
      comp.ngOnInit();

      expect(seatService.query).toHaveBeenCalled();
      expect(seatService.addSeatToCollectionIfMissing).toHaveBeenCalledWith(
        seatCollection,
        ...additionalSeats.map(expect.objectContaining)
      );
      expect(comp.seatsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const seatReserved: ISeatReserved = { id: 456 };
      const seat: ISeat = { id: 2713 };
      seatReserved.seat = seat;

      activatedRoute.data = of({ seatReserved });
      comp.ngOnInit();

      expect(comp.seatsSharedCollection).toContain(seat);
      expect(comp.seatReserved).toEqual(seatReserved);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeatReserved>>();
      const seatReserved = { id: 123 };
      jest.spyOn(seatReservedFormService, 'getSeatReserved').mockReturnValue(seatReserved);
      jest.spyOn(seatReservedService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seatReserved });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: seatReserved }));
      saveSubject.complete();

      // THEN
      expect(seatReservedFormService.getSeatReserved).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(seatReservedService.update).toHaveBeenCalledWith(expect.objectContaining(seatReserved));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeatReserved>>();
      const seatReserved = { id: 123 };
      jest.spyOn(seatReservedFormService, 'getSeatReserved').mockReturnValue({ id: null });
      jest.spyOn(seatReservedService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seatReserved: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: seatReserved }));
      saveSubject.complete();

      // THEN
      expect(seatReservedFormService.getSeatReserved).toHaveBeenCalled();
      expect(seatReservedService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISeatReserved>>();
      const seatReserved = { id: 123 };
      jest.spyOn(seatReservedService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ seatReserved });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(seatReservedService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareSeat', () => {
      it('Should forward to seatService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(seatService, 'compareSeat');
        comp.compareSeat(entity, entity2);
        expect(seatService.compareSeat).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
