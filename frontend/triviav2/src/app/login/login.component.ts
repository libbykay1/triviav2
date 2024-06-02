import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { catchError } from 'rxjs';
import { of } from 'rxjs';
import { LoginDto } from '../models/LoginDto';
import { UserService } from '../user.service';
import { TeamService } from '../team.service';
import { TeamResponseDto } from '../models/TeamResponseDto';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private userService: UserService, private teamService:TeamService, private router: Router) {

  }

  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  handleSubmit(event: Event) {
    event.preventDefault();

    const email = this.loginForm.value.email as string;
    const password = this.loginForm.value.password as string;
    const loginDto: LoginDto = {
      email: email,
      password: password
    }

    this.userService.login(loginDto).pipe(
      catchError(error => {
        console.error('Error logging in:', error);
        return of(null);
      })
    ).subscribe(response => {
      if (response) {
        const userId = this.userService.getUserId();
        if (userId !== null) {
          this.teamService.getTeamByUser(userId).subscribe((teamResponse: TeamResponseDto) => {
            this.userService.setTeamInfo(teamResponse);
            this.router.navigate(['account/home']);
          })
        } else {
        this.router.navigate(['account/home']);
        }
      }
    });
  }

}
