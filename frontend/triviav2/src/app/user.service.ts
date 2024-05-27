import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { LoginDto } from "./models/LoginDto";
import { AuthResponse } from "./models/AuthResponse";

const baseUrl = 'http://localhost:8080/api/v1/auth';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  login(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/authenticate`, payload);
  }

  createAccount(loginDto: LoginDto): Observable<AuthResponse> {
    const payload = { ...loginDto };
    return this.http.post<AuthResponse>(`${baseUrl}/register`, payload);
  }
}
