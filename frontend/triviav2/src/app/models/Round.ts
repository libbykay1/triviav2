import {Question} from './Question'

export interface Round {
    id: number;
    roundNumber: number;
    title: string;
    prompt: string;
    questions: Question[];
    type: Type;
}

export enum Type {
    QUESTION_AND_ANSWER = 'QUESTION_AND_ANSWER',
}
