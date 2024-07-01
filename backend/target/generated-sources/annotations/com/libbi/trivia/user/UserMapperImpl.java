package com.libbi.trivia.user;

import com.libbi.trivia.team.Team;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-01T00:54:12-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setTeamId( userTeamId( user ) );
        userResponseDto.setId( user.getId() );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }

    private Long userTeamId(User user) {
        if ( user == null ) {
            return null;
        }
        Team team = user.getTeam();
        if ( team == null ) {
            return null;
        }
        Long id = team.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
