import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';

import { RoundService } from '../round.service';
import { Round } from '../models/Round';

import { QAndAComponent } from '../q-and-a/q-and-a.component';

@Component({
  selector: 'app-display-round',
  standalone: true,
  imports: [HeaderComponent, QAndAComponent],
  templateUrl: './display-round.component.html',
  styleUrl: './display-round.component.css'
})
export class DisplayRoundComponent {
  round: Round | null = null;

  constructor(private roundService: RoundService) {}




  ngOnInit() {
    this.roundService.getRound(1, 1).subscribe((round: Round) => {
      this.round = round;
    });


  }



}
