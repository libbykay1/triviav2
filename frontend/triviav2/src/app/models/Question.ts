import {Answer} from './Answer.model'

export interface Question {
    id: number;
    text: string;
    correctAnswer: string;
    type: string;
    imageUrl: string;
    answer: Answer;
}
