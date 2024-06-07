import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { TeamRequestDto } from "./models/TeamRequestDto";
import { TeamResponseDto } from "./models/TeamResponseDto";

const baseUrl = 'http://localhost:8080/api/v1/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private teamSource = new BehaviorSubject<TeamResponseDto | null>(null);
  currentTeam = this.teamSource.asObservable();
  constructor(private http: HttpClient) { }

  createTeam(teamRequestDto: TeamRequestDto, gameId: number): Observable<TeamResponseDto> {
    const payload = { ...teamRequestDto, gameId };
    return this.http.post<TeamResponseDto>(`${baseUrl}/${gameId}/new`, payload).pipe(
      tap((team: TeamResponseDto) => this.setTeam(team))
    );
  }

  changeTeamName(teamRequestDto: TeamRequestDto, teamId: number): Observable<TeamResponseDto> {
    const payload = { ...teamRequestDto, teamId};
    return this.http.patch<TeamResponseDto>(`${baseUrl}/${teamId}/changename`, payload).pipe(
      tap((team: TeamResponseDto) => this.setTeam(team))
    );
  }

  joinGame(teamId: number, gameId: number): Observable<TeamResponseDto> {
    return this.http.patch<TeamResponseDto>(`${baseUrl}/${teamId}/${gameId}/join`, {}).pipe(
      tap((team: TeamResponseDto) => this.setTeam(team))
    );
  }

  getTeamByUser(userId: number): Observable<TeamResponseDto> {
    return this.http.get<TeamResponseDto>(`${baseUrl}/${userId}/team`).pipe(
      tap((team: TeamResponseDto) => this.setTeam(team))
    );
  }

  getAllGameTeams(gameId: number): Observable<TeamResponseDto[]> {
    return this.http.get<TeamResponseDto[]>(`${baseUrl}/${gameId}/teams`);
  }

  getTeamById(teamId: number): Observable<TeamResponseDto> {
    return this.http.get<TeamResponseDto>(`${baseUrl}/${teamId}`);
  }

  setTeam(team: TeamResponseDto) {
    this.teamSource.next(team);
  }
}
