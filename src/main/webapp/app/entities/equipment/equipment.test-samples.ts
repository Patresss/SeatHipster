import { EquipmentType } from 'app/entities/enumerations/equipment-type.model';

import { IEquipment, NewEquipment } from './equipment.model';

export const sampleWithRequiredData: IEquipment = {
  id: 1489,
  name: 'workforce',
  type: EquipmentType['NOTEBOOK'],
};

export const sampleWithPartialData: IEquipment = {
  id: 38488,
  name: 'sexy',
  type: EquipmentType['HEADPHONES'],
};

export const sampleWithFullData: IEquipment = {
  id: 42195,
  name: 'Personal Money Chair',
  type: EquipmentType['MONITOR'],
};

export const sampleWithNewData: NewEquipment = {
  name: 'Creative Markets',
  type: EquipmentType['HEADPHONES'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
