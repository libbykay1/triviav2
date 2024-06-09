import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TeamResponseDto } from '../models/TeamResponseDto';
import { TeamService } from '../team.service';
import { RoundService } from '../round.service';
import { Round } from '../models/Round';
import { Question } from '../models/Question';

@Component({
  selector: 'app-display-round',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule],
  templateUrl: './display-round.component.html',
  styleUrl: './display-round.component.css'
})
export class DisplayRoundComponent {
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
    this.roundService.currentRound.subscribe((round) => {
      this.round = round;
    });
    this.roundService.currentQuestions.subscribe((questions) => {
      this.questions = questions;
    })
  }

  handleSubmit(event: Event) {
    event.preventDefault();
  }

}
