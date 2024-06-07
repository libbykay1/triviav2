import { Component, Input } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  @Input() showLoginButton="true";
  @Input() showLogoutButton="true";

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  logout() {
    this.userService.logout().subscribe(() => {
      this.router.navigate(['/play']);
    });
  }

}
