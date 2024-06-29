package com.libbi.trivia.team;

import com.libbi.trivia.user.User;
import com.libbi.trivia.user.User.UserBuilder;
import com.libbi.trivia.user.UserMapper;
import com.libbi.trivia.user.UserResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T12:34:36-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Autowired
    private UserMapper userMapper;

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
        teamResponseDto.setOwner( userMapper.userToUserResponseDto( entity.getOwner() ) );

        return teamResponseDto;
    }

    @Override
    public Team responseDtoToEntity(TeamResponseDto teamResponseDto) {
        if ( teamResponseDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( teamResponseDto.getId() );
        team.setTeamName( teamResponseDto.getTeamName() );
        team.setOwner( userResponseDtoToUser( teamResponseDto.getOwner() ) );
        team.setPoints( teamResponseDto.getPoints() );

        return team;
    }

    protected User userResponseDtoToUser(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userResponseDto.getId() );
        user.email( userResponseDto.getEmail() );

        return user.build();
    }
}
