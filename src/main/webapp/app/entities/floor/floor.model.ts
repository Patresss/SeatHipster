import { IBuilding } from 'app/entities/building/building.model';

export interface IFloor {
  id: number;
  name?: string | null;
  number?: number | null;
  building?: Pick<IBuilding, 'id'> | null;
}

export type NewFloor = Omit<IFloor, 'id'> & { id: null };
