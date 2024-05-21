import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TeamService } from '../team.service';
import { TeamRequestDto } from '../models/TeamRequestDto';
import { catchError } from 'rxjs';
import { of } from 'rxjs';



@Component({
  selector: 'app-player-home',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet, ReactiveFormsModule],
  templateUrl: './player-home.component.html',
  styleUrl: './player-home.component.css'
})
export class PlayerHomeComponent {

  constructor(private teamService: TeamService) {

  }

  teamNameForm = new FormGroup({
    teamName: new FormControl('', Validators.required),
  });

  handleSubmit(event: Event) {
    event.preventDefault();

    const newTeamName = this.teamNameForm.value.teamName as string;
    const teamRequestDto: TeamRequestDto = {
      teamName: newTeamName
    };
    const gameId = 1;

    this.teamService.createTeam(teamRequestDto, gameId).pipe(
      catchError(error => {
        console.error('Error creating team:', error);
        return of(null); // Handle the error and return a fallback value
      })
    ).subscribe(response => {
      if (response) {
        console.log('Team created:', response);
      }
    });
  }

}
