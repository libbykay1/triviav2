import { Routes } from '@angular/router';
import { PlayerHomeComponent } from './player-home/player-home.component';
import { WaitingRoomComponent } from './waiting-room/waiting-room.component';
import { LoginComponent } from './login/login.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { CreateTeamComponent } from './create-team/create-team.component';
import { DisplayRoundComponent } from './display-round/display-round.component';

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
    {
        path: 'account/login',
        component: LoginComponent,
    },
    {
        path: 'account/home',
        component: UserHomeComponent,
    },
    {
        path: 'account/create',
        component: CreateAccountComponent,
    },
    {
        path: 'account/teamname',
        component: CreateTeamComponent,
    },
    {
        path: 'play/round',
        component: DisplayRoundComponent,
    },
];
