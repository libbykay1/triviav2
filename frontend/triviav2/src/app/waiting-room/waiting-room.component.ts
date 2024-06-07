import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet } from '@angular/router';
import { TeamService } from '../team.service';
import { TeamResponseDto } from '../models/TeamResponseDto';
@Component({
  selector: 'app-waiting-room',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet],
  templateUrl: './waiting-room.component.html',
  styleUrl: './waiting-room.component.css'
})
export class WaitingRoomComponent {
  team: TeamResponseDto | null = null;

  constructor(private teamService: TeamService) {}

  ngOnInit() {
    this.teamService.currentTeam.subscribe((team) => {
      this.team = team;
    });
  }
}
