import { Component, OnInit } from '@angular/core';
import { ModelComponent } from '../shared/ui/model/model.component';
import { PatientFormComponent } from '../patient-form/patient-form.component';
import { ToastrService } from 'ngx-toastr';
import { PatientService } from '../../services/patient.service';
import { IPatient } from '../shared/models/Patient';
@Component({
  selector: 'app-Patient',
  standalone: true,
  imports: [ModelComponent, PatientFormComponent],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss',
})
export class PatientComponent implements OnInit {
  isModelOpen = false;
  Patients: IPatient[] = [];
  Patient!: IPatient;

  constructor(
    private PatientService: PatientService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllPatient();
  }

  getAllPatient() {
    this.PatientService.getAllPatient().subscribe({
      next: (response) => {
        console.log('response',response.data)
        if (response.data) {
          this.Patients = response.data;
        }
      },
    });
  }

  loadPatient(Patient: IPatient) {
    this.Patient = Patient;
    this.openModel();
  }

  deletePatient(id: string) {
    this.PatientService.deletePatient(id).subscribe({
      next: (response) => {
        this.toastr.success(response.message);
        this.getAllPatient();
      },
    });
  }

  openModel() {
    this.isModelOpen = true;
  }

  closeModel() {
    this.isModelOpen = false;
    this.getAllPatient();
  }
}
