import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TeamResponseDto } from '../models/TeamResponseDto';
import { Round } from '../models/Round';
import { Question } from '../models/Question';
import { TeamService } from '../team.service';
import { RoundService } from '../round.service';

@Component({
  selector: 'app-q-and-a',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule],
  templateUrl: './q-and-a.component.html',
  styleUrl: './q-and-a.component.css'
})
export class QAndAComponent {

  team: TeamResponseDto | null = null;
  teamId: number | null = null;
  round: Round | null = null;
  questions: Question[] = [];
  submissionForm: FormGroup;

  constructor(private teamService: TeamService, private roundService: RoundService, private fb: FormBuilder) {
    this.submissionForm = this.fb.group({});
  }



  ngOnInit() {

    this.teamService.currentTeam.subscribe((team) => {
      this.teamId = team?.id ?? null;
    });
    this.roundService.getRound(1, 1).subscribe((round: Round) => {
      this.round = round;
      this.questions = round.questions;
      this.createFormControls();
    });


  }

  createFormControls() {
    this.questions.forEach((question, index) => {
      for (let i = 0; i < question.answer.numberOfAnswers; i++) {
        this.submissionForm.addControl(`answer_${index}_${i}`, new FormControl());
      }
    })
  }

  createArray(number: number): number[] {
    return new Array(number);
  }

  handleSubmit(event: Event) {
    event.preventDefault();
    if (this.submissionForm.valid) {
      if (window.confirm("Are you sure you're ready to submit your answers?")) {
        console.log(this.submissionForm.value);
        // Process the form submission here
      }
    }
  }
}
