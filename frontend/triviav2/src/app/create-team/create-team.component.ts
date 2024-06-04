import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { TeamService } from '../team.service';
import { Router, RouterOutlet, RouterLink } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TeamRequestDto } from '../models/TeamRequestDto';
import { catchError, of } from 'rxjs';
import { TeamResponseDto } from '../models/TeamResponseDto';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-team',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, RouterLink, RouterOutlet],
  templateUrl: './create-team.component.html',
  styleUrl: './create-team.component.css'
})
export class CreateTeamComponent {
  teamInfo: TeamResponseDto | null = null;

  constructor(private teamService: TeamService, private userService: UserService, private router: Router) {

  }
  ngOnInit(): void {
    this.teamInfo = this.userService.getTeamInfo();

  }

  teamNameForm = new FormGroup({
    teamName: new FormControl('', Validators.required),
  });

  handleSubmit(event: Event) {
    event.preventDefault();

    const newTeamName = this.teamNameForm.value.teamName as string;
    if (this.teamInfo) {
      const teamId = this.teamInfo.id;
      const teamRequestDto: TeamRequestDto = {
        teamName: newTeamName
      };


    this.teamService.changeTeamName(teamRequestDto, teamId).pipe(
      catchError(error => {
        console.error('Error changing name:', error);
        return of(null);
      })
    ).subscribe(response => {
      if (response) {
        this.teamInfo = response;
        this.userService.setTeamInfo(this.teamInfo);
        this.router.navigate(['account/home']);
      }
    });
  }
}}
