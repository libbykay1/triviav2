package com.libbi.trivia.team;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-21T10:55:10-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public Team requestDtoToEntity(TeamRequestDto teamRequestDto) {
        if ( teamRequestDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setTeamName( teamRequestDto.getTeamName() );

        return team;
    }

    @Override
    public TeamResponseDto entityToResponseDto(Team entity) {
        if ( entity == null ) {
            return null;
        }

        TeamResponseDto teamResponseDto = new TeamResponseDto();

        teamResponseDto.setId( entity.getId() );
        teamResponseDto.setTeamName( entity.getTeamName() );
        teamResponseDto.setPoints( entity.getPoints() );

        return teamResponseDto;
    }
}
