import { IAddress, NewAddress } from './address.model';

export const sampleWithRequiredData: IAddress = {
  id: 88754,
  country: 'Philippines',
  street: 'Hegmann Ridges',
  postalCode: 'wireless Fresh Court',
  city: 'Waltham',
  stateProvince: 'bleeding-edge Colorado Buckinghamshire',
};

export const sampleWithPartialData: IAddress = {
  id: 42632,
  country: 'Mexico',
  street: 'Fadel Manor',
  postalCode: 'Bedfordshire Outdoors',
  city: 'Davenport',
  stateProvince: 'Car',
};

export const sampleWithFullData: IAddress = {
  id: 28809,
  country: 'Liberia',
  street: 'Jacinthe Common',
  postalCode: 'Swiss',
  city: 'Freeport',
  stateProvince: 'hacking networks',
};

export const sampleWithNewData: NewAddress = {
  country: 'Bangladesh',
  street: 'Valentina Haven',
  postalCode: 'hack',
  city: 'Hermannbury',
  stateProvince: 'Birr experiences Re-contextualized',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
