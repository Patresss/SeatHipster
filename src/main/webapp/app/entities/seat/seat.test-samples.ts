import { AvailabilityStatus } from 'app/entities/enumerations/availability-status.model';

import { ISeat, NewSeat } from './seat.model';

export const sampleWithRequiredData: ISeat = {
  id: 43988,
  name: 'Sleek auxiliary wireless',
  status: AvailabilityStatus['UNAVAILABLE'],
};

export const sampleWithPartialData: ISeat = {
  id: 80095,
  name: 'Intelligent Missouri',
  status: AvailabilityStatus['FREE'],
};

export const sampleWithFullData: ISeat = {
  id: 29578,
  name: 'Developer streamline ivory',
  description: 'Ranch transmitter XSS',
  status: AvailabilityStatus['FREE'],
};

export const sampleWithNewData: NewSeat = {
  name: 'architect',
  status: AvailabilityStatus['FREE'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
