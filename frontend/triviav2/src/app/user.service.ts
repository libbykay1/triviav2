import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from 'rxjs';
import { LoginDto } from "./models/LoginDto";
import { AuthResponse } from "./models/AuthResponse";
import { TeamResponseDto } from "./models/TeamResponseDto";

const baseUrl = 'http://localhost:8080/api/v1/auth';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userId: number | null = null;
  private teamInfo: TeamResponseDto | null = null;
  constructor(private http: HttpClient) { }

  login(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/authenticate`, payload).pipe(
      tap(response => {
        this.userId = response.userId;
      })
    );
  }

  createAccount(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/register`, payload).pipe(
      tap(response => {
        this.userId = response.userId;
      })
    );
  }

  getUserId(): number | null {
    return this.userId;
  }

  setTeamInfo(teamInfo: TeamResponseDto): void {
    this.teamInfo = teamInfo;
  }

  getTeamInfo(): TeamResponseDto | null {
    return this.teamInfo;
  }
}
