import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { TeamRequestDto } from "./models/TeamRequestDto";
import { TeamResponseDto } from "./models/TeamResponseDto";

const baseUrl = 'http://localhost:8080/api/v1/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  constructor(private http: HttpClient) { }

  createTeam(teamRequestDto: TeamRequestDto, gameId: number): Observable<TeamResponseDto> {
    const payload = { ...teamRequestDto, gameId };
    return this.http.post<TeamResponseDto>(`${baseUrl}/${gameId}/new`, payload);
  }
}
