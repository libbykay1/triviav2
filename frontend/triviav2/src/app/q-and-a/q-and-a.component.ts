import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
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

  constructor(private teamService: TeamService, private roundService: RoundService) {}

  submissionForm = new FormGroup({

  })

  ngOnInit() {

    this.teamService.currentTeam.subscribe((team) => {
      this.teamId = team?.id ?? null;
    });
    this.roundService.getRound(1, 1).subscribe((round: Round) => {
      this.round = round;
      this.questions = round.questions;
    });


  }

  createArray(number: number): number[] {
    return new Array(number);
  }

  handleSubmit(event: Event) {
    event.preventDefault();
  }
}
