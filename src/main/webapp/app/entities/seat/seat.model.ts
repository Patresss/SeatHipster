import { IRoom } from 'app/entities/room/room.model';
import { AvailabilityStatus } from 'app/entities/enumerations/availability-status.model';

export interface ISeat {
  id: number;
  name?: string | null;
  description?: string | null;
  status?: AvailabilityStatus | null;
  room?: Pick<IRoom, 'id'> | null;
}

export type NewSeat = Omit<ISeat, 'id'> & { id: null };
