import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse, IPatient } from '../pages/shared/models/Patient';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  apiurl = 'http://localhost:8000/api/v1/patients';
  constructor(private http: HttpClient) {}

  getAllPatient(): Observable<ApiResponse<IPatient[]>> {
    return this.http.get<ApiResponse<IPatient[]>>(`${this.apiurl+'?page=0&size=3'}`);
  }

  getPatient(id: string): Observable<ApiResponse<IPatient>> {
    return this.http.get<ApiResponse<IPatient>>(`${this.apiurl}/${id}`);
  }

  createPatient(patient: IPatient): Observable<any> {
    return this.http.post(`${this.apiurl}`, patient);
  }

  updatePatient(id: string, patient: IPatient): Observable<any> {
    return this.http.put(`${this.apiurl}/${id}`, patient);
  }

  deletePatient(id: string): Observable<ApiResponse<any>> {
    return this.http.delete<ApiResponse<any>>(`${this.apiurl}/${id}`);
  }
}
