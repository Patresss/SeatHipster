import dayjs from 'dayjs/esm';

import { ISeatReserved, NewSeatReserved } from './seat-reserved.model';

export const sampleWithRequiredData: ISeatReserved = {
  id: 9502,
  name: 'Palladium',
  fromDate: dayjs('2022-08-30T04:06'),
  createdDate: dayjs('2022-08-30T01:26'),
};

export const sampleWithPartialData: ISeatReserved = {
  id: 43420,
  name: 'indexing Somoni',
  fromDate: dayjs('2022-08-29T17:37'),
  toDate: dayjs('2022-08-29T23:44'),
  createdDate: dayjs('2022-08-29T16:13'),
};

export const sampleWithFullData: ISeatReserved = {
  id: 35622,
  name: 'Specialist Loan Mouse',
  fromDate: dayjs('2022-08-29T21:28'),
  toDate: dayjs('2022-08-30T04:40'),
  createdDate: dayjs('2022-08-30T02:59'),
};

export const sampleWithNewData: NewSeatReserved = {
  name: 'National Granite GB',
  fromDate: dayjs('2022-08-29T12:40'),
  createdDate: dayjs('2022-08-30T07:18'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
