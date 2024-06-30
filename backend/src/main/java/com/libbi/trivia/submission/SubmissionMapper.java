package com.libbi.trivia.submission;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

	Submission requestDtoToEntity(SubmissionRequestDto submissionRequestDto);

	@Mappings({
		@Mapping(source = "round.id", target = "roundId"),
		@Mapping(source = "team.id", target = "teamId")
	})
	SubmissionResponseDto entityToDto(Submission submission);

}
