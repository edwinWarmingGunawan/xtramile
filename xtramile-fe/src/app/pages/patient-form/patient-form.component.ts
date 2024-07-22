import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
} from '@angular/core';
import { IPatient } from '../shared/models/Patient';
import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule, formatDate } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PatientService } from '../../services/patient.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-Patient-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './Patient-form.component.html',
  styleUrl: './Patient-form.component.scss',
})
export class PatientFormComponent implements OnChanges {
  @Input() data: IPatient | null = null;
  @Output() onCloseModel = new EventEmitter();

  PatientForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private toastr: ToastrService
  ) {
    this.PatientForm = this.fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      phoneNo: new FormControl('', [Validators.required]),
      dob: new FormControl('', [Validators.required]),
      postCode: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      suburb: new FormControl('', [Validators.required]),
    });
  }

  onClose() {
    this.onCloseModel.emit(false);
  }

  ngOnChanges(): void {
    if (this.data) {
      this.PatientForm.patchValue({
        firstName: this.data.firstName,
        lastName: this.data.lastName,
        phoneNo: this.data.phoneNo,
        dob: formatDate(this.data.dob, 'yyyy-MM-dd', 'en'),
        postCode: this.data.postCode,
        address: this.data.address,
        state: this.data.state,
        suburb: this.data.suburb,
      });
    }
  }

  onSubmit() {
    
    if (this.PatientForm.valid) {
      if (this.data) {
        this.patientService
          .updatePatient(this.data.id as string, this.PatientForm.value)
          .subscribe({
            next: (response: any) => {
              this.resetPatientForm();
              this.toastr.success(response.message);
            },
          });
      } else {
        
        this.patientService.createPatient(this.PatientForm.value).subscribe({
          next: (response: any) => {
            this.resetPatientForm();
            this.toastr.success(response.message);
          },
        });
      }
    } else {
      this.PatientForm.markAllAsTouched();
    }
  }

  resetPatientForm() {
    this.PatientForm.reset();
    this.onClose();
  }
}
