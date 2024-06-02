package com.libbi.trivia.auth;

import com.libbi.trivia.config.JwtService;
import com.libbi.trivia.team.Team;
import com.libbi.trivia.team.TeamMapper;
import com.libbi.trivia.team.TeamRequestDto;
import com.libbi.trivia.team.TeamResponseDto;
import com.libbi.trivia.team.TeamService;
import com.libbi.trivia.user.Role;
import com.libbi.trivia.user.User;
import com.libbi.trivia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
	private final TeamService teamService;
	private final TeamMapper teamMapper;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PLAYER)
                .deleted(false)
                .build();
        repository.save(user);
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Team team = null;
        if (request.getTeamName() != null) {
        	TeamRequestDto teamRequestDto = new TeamRequestDto(request.getTeamName());
            TeamResponseDto teamResponseDto = teamService.createTeamLoggedIn(teamRequestDto); 
            team = teamMapper.responseDtoToEntity(teamResponseDto); 
        }
        if (team != null) {
            user.setTeam(team);
            repository.save(user);
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        		.token(jwtToken)
        		.userId(user.getId())
        		.build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        		.token(jwtToken)
        		.userId(user.getId())
        		.build();
    }
}
