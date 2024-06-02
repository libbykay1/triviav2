import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { catchError } from 'rxjs';
import { of } from 'rxjs';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AccountDto } from '../models/AccountDto';
import { TeamService } from '../team.service';
import { TeamResponseDto } from '../models/TeamResponseDto';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule],
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {

  constructor(private userService: UserService, private teamService: TeamService, private router: Router) {

  }

  newAccountForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    teamName: new FormControl('')
  });

  handleSubmit(event: Event) {
    event.preventDefault();

    const email = this.newAccountForm.value.email as string;
    const password = this.newAccountForm.value.password as string;
    const teamName = this.newAccountForm.value.teamName as string;
    const loginDto: AccountDto = {
      email: email,
      password: password,
      teamName: teamName
    }

    this.userService.createAccount(loginDto).pipe(
      catchError(error => {
        console.error('Error creating account:', error);
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
