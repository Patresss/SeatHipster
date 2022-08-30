import dayjs from 'dayjs/esm';
import { ISeat } from 'app/entities/seat/seat.model';

export interface ISeatReserved {
  id: number;
  name?: string | null;
  fromDate?: dayjs.Dayjs | null;
  toDate?: dayjs.Dayjs | null;
  createdDate?: dayjs.Dayjs | null;
  seat?: Pick<ISeat, 'id'> | null;
}

export type NewSeatReserved = Omit<ISeatReserved, 'id'> & { id: null };
