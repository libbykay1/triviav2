package com.libbi.trivia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -8051973525610699359L;
	private String message;
}
