import { HttpClient } from "@angular/common/http";
import { Injectable, Inject, PLATFORM_ID } from "@angular/core";
import { isPlatformBrowser } from '@angular/common';
import { Observable, tap } from 'rxjs';
import { LoginDto } from "./models/LoginDto";
import { AuthResponse } from "./models/AuthResponse";
import { TeamResponseDto } from "./models/TeamResponseDto";

const baseUrl = 'http://localhost:8080/api/v1/auth';
const USER_ID_KEY = 'userId';
const TEAM_INFO_KEY = 'teamInfo';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userId: number | null = this.getStoredUserId();
  private teamInfo: TeamResponseDto | null = this.getStoredTeamInfo();

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  login(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/authenticate`, payload).pipe(
      tap(response => {
        this.userId = response.userId;
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem(USER_ID_KEY, JSON.stringify(this.userId));
        }
      })
    );
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${baseUrl}/logout`, {}).pipe(
      tap(() => {
        this.userId = null;
        this.teamInfo = null;
        if (isPlatformBrowser(this.platformId)) {
          localStorage.removeItem(USER_ID_KEY);
          localStorage.removeItem(TEAM_INFO_KEY);
        }
      })
    );
  }

  createAccount(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/register`, payload).pipe(
      tap(response => {
        this.userId = response.userId;
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem(USER_ID_KEY, JSON.stringify(this.userId));
        }
      })
    );
  }

  getUserId(): number | null {
    return this.userId;
  }

  setTeamInfo(teamInfo: TeamResponseDto): void {
    this.teamInfo = teamInfo;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(TEAM_INFO_KEY, JSON.stringify(this.teamInfo));
    }
  }

  getTeamInfo(): TeamResponseDto | null {
    return this.teamInfo;
  }

  private getStoredUserId(): number | null {
    if (isPlatformBrowser(this.platformId)) {
      const storedUserId = localStorage.getItem(USER_ID_KEY);
      return storedUserId ? JSON.parse(storedUserId) : null;
    }
    return null;
  }

  private getStoredTeamInfo(): TeamResponseDto | null {
    if (isPlatformBrowser(this.platformId)) {
      const storedTeamInfo = localStorage.getItem(TEAM_INFO_KEY);
      return storedTeamInfo ? JSON.parse(storedTeamInfo) : null;
    }
    return null;
  }
}
