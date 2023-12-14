export interface IPolicyMaster {
  id?: number;
  purpuseName?: string | null;
  policyName?: string | null;
  chargesType?: string | null;
  interestRate?: number | null;
  numberOfInstallments?: number | null;
  penaltyRateOfInterest?: number | null;
  installmentDuration?: number | null;
}

export const defaultValue: Readonly<IPolicyMaster> = {};
