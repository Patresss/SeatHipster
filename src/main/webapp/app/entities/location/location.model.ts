export interface ILocation {
  id: number;
  name?: string | null;
}

export type NewLocation = Omit<ILocation, 'id'> & { id: null };
