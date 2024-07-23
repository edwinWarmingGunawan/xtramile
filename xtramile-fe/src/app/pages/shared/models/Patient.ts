export interface ApiResponse<T> {
  message?: string;
  data: T;
  total: number; // Add this property
}

export interface IPatient {
  id?: string;
  firstName: string;
  lastName: string;
  phoneNo: string;
  dob: string;
  address: string;
  state: string;
  suburb: string;
  postCode: string;
}
