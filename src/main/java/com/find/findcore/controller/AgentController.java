package com.find.findcore.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.payload.response.JwtResponse;
import com.find.findcore.model.payload.response.MessageResponse;
import com.find.findcore.model.payload.response.Response;
import com.find.findcore.security.jwt.JwtUtils;
import com.find.findcore.service.AgencyService;
import com.find.findcore.service.AgentService;
import com.find.findcore.service.impl.AgentAuthDetailsImpl;
import com.find.findcore.service.impl.RefreshTokenServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class AgentController {
	private static final Logger log = LoggerFactory.getLogger(AgentController.class);

	@Autowired
	AgentService agentService;

	@Autowired
	AgencyService agencyService;

	@Autowired
	RefreshTokenServiceImpl refreshTokenService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping({ "/agent-signup" })
	public Response registerUser(@Valid @RequestBody Agent agentReq) {
		Response response = new Response();
		try {
			if (agentService.agentExists(agentReq.getMobileno())) {
				response.markFailed(HttpStatus.BAD_REQUEST, "Error: Mobile no. is already taken!");
				return response;
			}

			agentReq.setPassword(encoder.encode(agentReq.getPassword()));
			Agent savedAgent = agentService.agentSignUp(agentReq);
			if (savedAgent != null) {
				response.markSuccessful("Signup successfully!");
				return response;
			} else {
				response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Signup Failed");
				return response;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error: Error occurred during signup. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/agent-signin" })
	public Response authenticateUser(@Valid @RequestBody Agent agentReq) {
		Response response = new Response();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(agentReq.getMobileno(), agentReq.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			AgentAuthDetailsImpl authDetails = (AgentAuthDetailsImpl) authentication.getPrincipal();
			String jwt = jwtUtils.generateJwtTokenForAgent(authentication);
			// RefreshToken refreshToken =
			// refreshTokenService.createRefreshToken(agentReq.getId());
			response.setJwt(jwt);
			response.setData(authDetails);
			response.markSuccessful("Agent Verified!");
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error: Error occurred during signin. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/agent-verify" })
	public Response agentVerify(@RequestBody Agent agentReq) {
		Response response = new Response();

		try {
			if (!agentService.agentExists(agentReq.getMobileno())) {
				response.markFailed(HttpStatus.BAD_REQUEST, "Error: User not available. Please signup.");
				return response;
			} else {
				Agent approvedAgent = agentService.agentVerify(agentReq);

				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(approvedAgent.getMobileno(),
								approvedAgent.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				AgentAuthDetailsImpl authDetails = (AgentAuthDetailsImpl) authentication.getPrincipal();
				String jwt = jwtUtils.generateJwtTokenForAgent(authentication);

				response.setJwt(jwt);
				response.setData(authDetails);
				response.markSuccessful("Agent Verified!");
				return response;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error: Error occurred during verification. Please try again!");
			return response;
		}
	}

	@GetMapping({ "/agents" })
	public Response getAllAgents() {
		Response response = new Response();
		try {
			response.markSuccessful("Agency Fetched.");
			response.setData(agentService.getAllAgents());
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

}
