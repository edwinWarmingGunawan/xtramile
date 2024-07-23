import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { ApiResponse,IPatient } from '../shared/models/Patient';
import { ModelComponent } from '../shared/ui/model/model.component';
import { PatientFormComponent } from '../patient-form/patient-form.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [ModelComponent, PatientFormComponent],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss',
})
export class PatientComponent implements OnInit {
  isModelOpen = false;
  Patients: IPatient[] = [];
  Patient!: IPatient;
  currentPage = 0;
  totalPages = 0;
  limit = 5; // Number of patients per page

  constructor(private patientService: PatientService,private toastr: ToastrService) {}

  ngOnInit(): void {
    this.getAllPatients();
  }

  getAllPatients(): void {
    this.patientService.getAllPatients(this.currentPage,this.limit).subscribe({
      next: (response) => {
        if (response.data) {
          this.Patients = response.data;
          this.totalPages = response.total;
        }
      },
    });
  }

  // Methods to handle page changes (next, previous, etc.)
  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.getAllPatients();
    }
  }

  previousPage(): void {
    console.log(this.currentPage)
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllPatients();
    }
  }

  loadPatient(Patient: IPatient) {
    this.Patient = Patient;
    this.openModel();
  }

  deletePatient(id: string) {
    this.patientService.deletePatient(id).subscribe({
      next: (response) => {
        this.toastr.success(response.message);
        this.getAllPatients();
      },
    });
  }

  openModel() {
    this.isModelOpen = true;
  }

  closeModel() {
    this.isModelOpen = false;
    this.getAllPatients();
  }

  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      let search=(event.target as HTMLInputElement).value;
      this.getAllPatients();
    }
  }
}