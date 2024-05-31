import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet } from '@angular/router';
import { TeamService } from '../team.service';
@Component({
  selector: 'app-waiting-room',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet],
  templateUrl: './waiting-room.component.html',
  styleUrl: './waiting-room.component.css'
})
export class WaitingRoomComponent {
  teamName: string = '';

  constructor(private teamService: TeamService) {}

  ngOnInit() {
    this.teamService.currentTeamName.subscribe((name) => {
      this.teamName = name;
    });
  }
}
