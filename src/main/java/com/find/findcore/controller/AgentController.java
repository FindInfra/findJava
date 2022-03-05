package com.find.findcore.controller;

import java.util.List;

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
	public ResponseEntity<?> registerUser(@Valid @RequestBody Agent agentReq) {
		try {
//			log.info(agentReq.toString());
//			log.info(agentService + "");
//
//			if (agentService.agentExists(agentReq.getMobileno())) {
//				return ResponseEntity.badRequest().body(new MessageResponse("Error: Mobile no. is already taken!"));
//			}

			// Optional<Agency> agency =
			// agencyService.getAgencyById(agentReq.getAgency().getId().longValue()).get();
			// Agency agency = new Agency();
			// agency.setId(1L);

			// Agent agent = new Agent(agentReq.getFullname(), agentReq.getMobileno(),
			// agency,
			// agentReq.getLicenseno(), encoder.encode(agentReq.getPassword()));

			agentReq.setPassword(encoder.encode(agentReq.getPassword()));
			Agent savedAgent = agentService.agentSignUp(agentReq);
			if (savedAgent != null) {
				return ResponseEntity.ok(new MessageResponse("Signup successfully!"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new MessageResponse("Error: Error occurred during signup. Please try again! " + ex.getMessage()));
		}
		return ResponseEntity.ok(
				new MessageResponse("User registered successfully!. Please check your email to verify your account."));
	}

	@PostMapping({ "/agent-signin" })
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Agent agentReq) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(agentReq.getMobileno(), agentReq.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			 AgentAuthDetailsImpl authDetailsImpl = (AgentAuthDetailsImpl)
			 authentication.getPrincipal();
			String jwt = jwtUtils.generateJwtTokenForAgent(authentication);
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//		RefreshToken refreshToken = refreshTokenService.createRefreshToken(agentReq.getId());
			return ResponseEntity.ok(new JwtResponse(jwt, agentReq.getId(), agentReq.getMobileno()));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new MessageResponse("Error: Error occurred during signin. Please try again! " + ex.getMessage()));
		}
	}

	/*
	 * @PostMapping({ "/signIn" }) public ResponseEntity<MessageResponse>
	 * agentSignUpIn(@RequestBody Agent agent) { try { if
	 * (!agentService.agentExists(agent.getMobileno())) { return
	 * ResponseEntity.badRequest() .body(new
	 * MessageResponse("Error: User not available. Please signup.")); }
	 * agentService.agentSignUpIn(agent); } catch (Exception e) {
	 * log.error(e.getMessage()); } return null; }
	 */
	@PostMapping({ "/agent-verify" })
	public ResponseEntity<MessageResponse> agentVerify() {
		try {
			agentService.agentVerify();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@GetMapping({ "/agents" })
	public List<Agent> getAllAgents() {

		try {
			return agentService.getAllAgents();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	};
}
