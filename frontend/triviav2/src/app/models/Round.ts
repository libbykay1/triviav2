import {Question} from './Question'

export interface Round {
    id: number;
    title: string;
    prompt: string;
    questions: Question[];
}
