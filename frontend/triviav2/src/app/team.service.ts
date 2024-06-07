import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from 'rxjs';
import { TeamRequestDto } from "./models/TeamRequestDto";
import { TeamResponseDto } from "./models/TeamResponseDto";

const baseUrl = 'http://localhost:8080/api/v1/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private teamNameSource = new BehaviorSubject<string>('');
  currentTeamName = this.teamNameSource.asObservable();
  constructor(private http: HttpClient) { }

  createTeam(teamRequestDto: TeamRequestDto, gameId: number): Observable<TeamResponseDto> {
    const payload = { ...teamRequestDto, gameId };
    return this.http.post<TeamResponseDto>(`${baseUrl}/${gameId}/new`, payload);
  }

  changeTeamName(teamRequestDto: TeamRequestDto, teamId: number): Observable<TeamResponseDto> {
    const payload = { ...teamRequestDto, teamId};
    return this.http.patch<TeamResponseDto>(`${baseUrl}/${teamId}/changename`, payload);
  }

  joinGame(teamId: number, gameId: number): Observable<TeamResponseDto> {
    return this.http.patch<TeamResponseDto>(`${baseUrl}/${teamId}/${gameId}/join`, {});
  }

  getTeamByUser(userId: number): Observable<TeamResponseDto> {
    return this.http.get<TeamResponseDto>(`${baseUrl}/${userId}/team`);
  }

  getAllGameTeams(gameId: number): Observable<TeamResponseDto[]> {
    return this.http.get<TeamResponseDto[]>(`${baseUrl}/${gameId}/teams`);
  }

  setTeamName(name: string) {
    this.teamNameSource.next(name);
  }
}
