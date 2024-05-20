package com.libbi.trivia.team;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamResponseDto {
	
	private Long id;
	private String teamName;	
	private Double points;

}
