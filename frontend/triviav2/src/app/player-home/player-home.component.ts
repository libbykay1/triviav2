import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { Router, RouterOutlet } from '@angular/router';
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

  constructor(private teamService: TeamService, private router: Router) {

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
        return of(null);
      })
    ).subscribe(response => {
      if (response) {
        this.teamService.setTeamName(newTeamName);
        this.router.navigate(['play/wait']);
      }
    });
  }

}
