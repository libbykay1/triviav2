package com.libbi.trivia.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(source = "team.id", target = "teamId")
    UserResponseDto userToUserResponseDto(User user);

}