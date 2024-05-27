import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginDto } from '../models/LoginDto';
import { catchError } from 'rxjs';
import { of } from 'rxjs';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule],
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {

  constructor(private userService: UserService, private router: Router) {

  }

  newAccountForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  handleSubmit(event: Event) {
    event.preventDefault();

    const email = this.newAccountForm.value.email as string;
    const password = this.newAccountForm.value.password as string;
    const loginDto: LoginDto = {
      email: email,
      password: password
    }

    this.userService.createAccount(loginDto).pipe(
      catchError(error => {
        console.error('Error creating account:', error);
        return of(null);
      })
    ).subscribe(response => {
      if (response) {
        this.router.navigate(['account/home']);
      }
    });
  }

}
