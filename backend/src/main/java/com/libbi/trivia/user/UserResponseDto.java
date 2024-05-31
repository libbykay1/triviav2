package com.libbi.trivia.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
	private Integer id;
	private String email;
	private Long teamId;
}
