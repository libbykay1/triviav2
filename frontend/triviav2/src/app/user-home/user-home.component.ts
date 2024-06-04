import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { TeamResponseDto } from '../models/TeamResponseDto';
import { UserService } from '../user.service';
import { RouterLink, RouterOutlet, Router } from '@angular/router';
import { TeamService } from '../team.service';
import { catchError, of } from 'rxjs';


@Component({
  selector: 'app-user-home',
  standalone: true,
  imports: [HeaderComponent, RouterLink, RouterOutlet],
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css'
})
export class UserHomeComponent {
  teamInfo: TeamResponseDto | null = null;

  constructor(private userService: UserService, private teamService: TeamService, private router: Router) {}

  ngOnInit(): void {
    this.teamInfo = this.userService.getTeamInfo();

  }

  saveTeamInfo(teamInfo: TeamResponseDto): void {
    this.userService.setTeamInfo(teamInfo);
  }

  // Call this method when you receive new team info, for example, after a login
  updateTeamInfo(newTeamInfo: TeamResponseDto): void {
    this.teamInfo = newTeamInfo;
    this.saveTeamInfo(newTeamInfo);
  }

  playAsThisTeam(event: Event): void {
    event.preventDefault();

    if (this.teamInfo) {
      const teamId = this.teamInfo.id;
      const gameId = 1;
      this.teamService.setTeamName(this.teamInfo.teamName);
      this.teamService.joinGame(teamId, gameId).pipe(
        catchError(error => {
          console.error('Error joining game:', error);
          return of(null);
        })
      ).subscribe(response => {
        if (response) {
          this.router.navigate(['/play/wait'])
        }
      });
    } else {
      console.error('Team information is not available.');
    }
  }

}
