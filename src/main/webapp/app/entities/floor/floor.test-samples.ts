import { IFloor, NewFloor } from './floor.model';

export const sampleWithRequiredData: IFloor = {
  id: 27504,
  name: 'firewall',
  number: 22519,
};

export const sampleWithPartialData: IFloor = {
  id: 90383,
  name: 'index green',
  number: 48329,
};

export const sampleWithFullData: IFloor = {
  id: 34704,
  name: 'Buckinghamshire compress Incredible',
  number: 72766,
};

export const sampleWithNewData: NewFloor = {
  name: 'Plastic',
  number: 91437,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
