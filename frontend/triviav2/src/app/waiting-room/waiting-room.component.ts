import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-waiting-room',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet],
  templateUrl: './waiting-room.component.html',
  styleUrl: './waiting-room.component.css'
})
export class WaitingRoomComponent {

}
