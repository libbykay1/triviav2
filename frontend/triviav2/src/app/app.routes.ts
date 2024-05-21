import { Routes } from '@angular/router';
import { PlayerHomeComponent } from './player-home/player-home.component';
import { WaitingRoomComponent } from './waiting-room/waiting-room.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'play',
        pathMatch: 'full'
      },
    {
        path: 'play',
        component: PlayerHomeComponent,
    },
    {
        path: 'play/wait',
        component: WaitingRoomComponent,
    },
];
