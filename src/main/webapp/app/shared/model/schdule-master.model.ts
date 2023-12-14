export interface ISchduleMaster {
  id?: number;
  installmentNumber?: number | null;
  reducingBalance?: number | null;
  principleAmount?: number | null;
  interest?: number | null;
  totalInstallment?: number | null;
  dueDate?: number | null;
  remarks?: string | null;
}

export const defaultValue: Readonly<ISchduleMaster> = {};
