import { IAddress } from 'app/entities/address/address.model';
import { ILocation } from 'app/entities/location/location.model';

export interface IBuilding {
  id: number;
  name?: string | null;
  address?: Pick<IAddress, 'id'> | null;
  location?: Pick<ILocation, 'id'> | null;
}

export type NewBuilding = Omit<IBuilding, 'id'> & { id: null };
