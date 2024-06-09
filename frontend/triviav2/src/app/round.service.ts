import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Round } from "./models/Round";
import { Question } from "./models/Question";

const baseUrl = 'http://localhost:8080/api/v1/round';

@Injectable({
  providedIn: 'root'
})
export class RoundService {
    private roundSource = new BehaviorSubject<Round | null>(null);
    currentRound = this.roundSource.asObservable();
    private questionsSource = new BehaviorSubject<Question[]>([]);
    currentQuestions = this.questionsSource.asObservable();

    constructor(private http: HttpClient) { }
    gameId: number = 1;


    getRound(gameId: number, roundNumber: number):Observable<Round> {
        return this.http.get<Round>(`${baseUrl}/${gameId}/${roundNumber}`).pipe(
            tap((round: Round) => this.setCurrentRound(round))
        );
    }

    setCurrentRound(round: Round) {
        this.roundSource.next(round);
    }

    setQuestions(questions: Question[]) {
        this.questionsSource.next(questions);
    }
}
