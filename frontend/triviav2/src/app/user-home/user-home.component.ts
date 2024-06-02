import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { TeamResponseDto } from '../models/TeamResponseDto';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-home',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css'
})
export class UserHomeComponent {
  teamInfo: TeamResponseDto | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.teamInfo = this.userService.getTeamInfo();
  }

}
