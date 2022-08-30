import { IFloor } from 'app/entities/floor/floor.model';

export interface IRoom {
  id: number;
  name?: string | null;
  floor?: Pick<IFloor, 'id'> | null;
}

export type NewRoom = Omit<IRoom, 'id'> & { id: null };
