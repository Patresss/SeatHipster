import { IRoom, NewRoom } from './room.model';

export const sampleWithRequiredData: IRoom = {
  id: 302,
  name: 'repurpose RSS',
};

export const sampleWithPartialData: IRoom = {
  id: 3705,
  name: 'bandwidth-monitored Account',
};

export const sampleWithFullData: IRoom = {
  id: 91197,
  name: 'Bypass',
};

export const sampleWithNewData: NewRoom = {
  name: 'SAS Concrete Triple-buffered',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
