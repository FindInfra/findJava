package com.find.findcore.controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.find.findcore.model.entity.AgencySubscription;
import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.AgentProfile;
import com.find.findcore.model.entity.NeedHelp;
import com.find.findcore.model.entity.Subscription;
import com.find.findcore.model.payload.response.Response;
import com.find.findcore.security.jwt.JwtUtils;
import com.find.findcore.service.AWSS3Service;
import com.find.findcore.service.AgencyService;
import com.find.findcore.service.AgentService;
import com.find.findcore.service.NeedHelpService;
import com.find.findcore.service.impl.RefreshTokenServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class AgentController {
	private static final Logger log = LoggerFactory.getLogger(AgentController.class);

	@Autowired
	AgentService agentService;

	@Autowired
	NeedHelpService needHelpService;

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

	@Autowired
	AWSS3Service awss3Service;

	@Autowired
	JavaMailSender mailSender;

	@PostMapping({ "/agent-signup" })
	public Response registerAgent(@Valid @RequestBody Agent agentReq) {
		Response response = new Response();
		Agent agent = new Agent();

		try {
			if (agentService.enableAgentExists(agentReq.getMobileno())) {
				response.markFailed(HttpStatus.BAD_REQUEST, "Mobile no. is already taken!");
				return response;
			} else if (agentService.agentExists(agentReq.getMobileno())) {
				agentService.deleteAgent(agentReq.getMobileno());
			}

			agent.setPassword(encoder.encode(agentReq.getPassword()));
			agent.setFullname(agentReq.getFullname());
			agent.setLicenseno(agentReq.getLicenseno());
			agent.setMobileno(agentReq.getMobileno());
			agent.setAgency(agentReq.getAgency());

			AgentProfile agentProfile = new AgentProfile();
			agentProfile.setMobileno(agentReq.getMobileno());
			agentProfile.setFullName(agentReq.getFullname());
			agentProfile.setLicenseno(agentReq.getLicenseno());

			agentProfile = agentService.saveProfile(agentProfile);
			agent.setProfile(agentProfile);
			log.info(agent.toString());
			Agent savedAgent = agentService.agentSignUp(agent);
			if (savedAgent != null) {
				response.markSuccessful("Signup successfully!");
				return response;
			} else {
				response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Signup Failed");
				return response;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during signup. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/agent-signin" })
	public Response authenticateAgent(@Valid @RequestBody Agent agentReq) {
		Response response = new Response();
		try {
			if (!agentService.enableAgentExists(agentReq.getMobileno())) {
				response.markFailed(HttpStatus.BAD_REQUEST, "User not available. Please signup.");
				return response;
			} else {
				try {
					Authentication authentication = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(agentReq.getMobileno(), agentReq.getPassword()));
				} catch (Exception e) {
					log.error(e.getMessage());
					response.markFailed(HttpStatus.UNAUTHORIZED, "Wrong Credentials. Please try again!");
					return response;
				}
				Agent approvedAgent = agentService.agentSignIn(agentReq);

				String jwt = jwtUtils.generateJwtTokenForAgent(approvedAgent);
				// RefreshToken refreshToken =
				// refreshTokenService.createRefreshToken(agentReq.getId());
				response.setToken(jwt);
				response.setData(approvedAgent);
				response.markSuccessful("Signin Successfully!");
				return response;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during signin. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/agent-verify" })
	public Response agentVerify(@RequestBody Agent agentReq) {
		Response response = new Response();

		try {
			if (!agentService.agentExists(agentReq.getMobileno())) {
				response.markFailed(HttpStatus.BAD_REQUEST, "User not available. Please signup.");
				return response;
			} else {
				Agent approvedAgent = agentService.agentVerify(agentReq);

//				AgentAuthDetailsImpl authDetails = (AgentAuthDetailsImpl) authentication.getPrincipal();
				String jwt = jwtUtils.generateJwtTokenForAgent(approvedAgent);

				response.setToken(jwt);
				response.setData(approvedAgent);
				response.markSuccessful("Agent Verified!");
				return response;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred during verification. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/agent-exists" })
	public Response agentExists(@RequestBody Agent agentReq) {
		Response response = new Response();

		try {
			Agent agent = agentService.getEnableAgentByMobile(agentReq.getMobileno());

			if (agent != null) {
				response.setData(agent);
				response.markSuccessful("Agent Exists!");
			} else {
				response.markFailed(HttpStatus.BAD_REQUEST, "User not available. Please signup.");
			}
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/change-password" })
	public Response changePassword(@RequestBody Agent agentReq) {
		Response response = new Response();

		try {
			Agent agent = agentService.getEnableAgentById(agentReq.getId());

			if (agent != null) {
				agent.setPassword(encoder.encode(agentReq.getPassword()));
				Agent savedAgent = agentService.agentSignUp(agent);
				response.setData(savedAgent);
				response.markSuccessful("Password changed!");
			} else {
				response.markFailed(HttpStatus.BAD_REQUEST, "User not available. Please signup.");
			}

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred during password change. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/subscribe-agency" })
	public Response subscribeAgent(@RequestBody Subscription subscription,
			@RequestHeader("Authorization") String token) {
		Response response = new Response();

		try {
			AgencySubscription agencySubscription = agentService.agentSubscribe(subscription, token);
			response.setData(agencySubscription);
			response.markSuccessful("Agency subscribed!");
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred during agency subscribtion. Please try again!");
			return response;
		}
	}

	@GetMapping({ "/agency-subscriptions" })
	public Response getAgencySubscription(@RequestHeader("Authorization") String token) {
		Response response = new Response();

		try {
			AgencySubscription agencySubscription = agentService.getAgencySubscription(token);
			response.setData(agencySubscription);
			response.markSuccessful("Agency subscribed!");
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred during agency subscribtion. Please try again!");
			return response;
		}
	}

	@GetMapping({ "/check-agency-subscription" })
	public Response checkAgencySubscription(@RequestHeader("Authorization") String token) {
		Response response = new Response();

		try {
			AgencySubscription agencySubscription = agentService.checkAgencySubscription(token);
			response.setData(agencySubscription);
			response.markSuccessful("Agency subscribed!");
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred during agency subscribtion. Please try again!");
			return response;
		}
	}

	@PostMapping({ "/add-agent-connection" })
	public Response addAgentConnection(@RequestHeader("Authorization") String token,
			@RequestParam(name = "mob") String mob) {
		Response response = new Response();
		try {
			String agentmob = jwtUtils.getUserNameFromJwtToken(token);
			if (agentmob.equalsIgnoreCase(mob)) {
				response.markSuccessful("Please enter other agent mobile no!");
			} else if (agentService.checkAgentConnection(mob, agentmob)) {
				response.markSuccessful("Agent Connection added!");
				agentService.addAgentConnection(mob, agentmob);
			} else {
				response.markSuccessful("Agent is already in your network.");
			}
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/agent-connections" })
	public Response getAgentConnections(@RequestHeader("Authorization") String token) {
		Response response = new Response();
		try {
			response.markSuccessful("Agent Connections Fetched");
			response.setData(agentService.getAgentConnections(token));
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/agents" })
	public Response getAllAgents() {
		Response response = new Response();
		try {
			response.markSuccessful("Agent Fetched.");
			response.setData(agentService.getAllAgents());
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@PostMapping({ "/remove-agent" })
	public Response removeAgent(@RequestBody Agent agentReq) {
		Response response = new Response();

		try {
			agentService.deleteAgent(agentReq.getMobileno());
			response.markSuccessful("Agent removed!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}
	}

	@GetMapping({ "/agent-profile" })
	public Response getAgentProfile(@RequestHeader("Authorization") String token) {
		Response response = new Response();
		try {

			String mobileno = jwtUtils.getUserNameFromJwtToken(token);
			response.setData(agentService.getAgentProfileByMobileno(mobileno));
			response.markSuccessful("Agency Profile Fetched.");

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@PostMapping({ "/change-avatar" })
	public Response updateAvatar(@RequestHeader("Authorization") String token, @RequestBody AgentProfile agentProfile) {
		Response response = new Response();

		try {
			String mobileno = jwtUtils.getUserNameFromJwtToken(token);
			agentService.changeAvatar(agentProfile, mobileno);
			response.markSuccessful("Avatar Changed!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}

	}

	@PostMapping("/upload-image-video")
	public Response uploadProfileImage(@RequestHeader("Authorization") String token,
			@RequestPart(value = "file") MultipartFile agentVideo) {
		Response response = new Response();
		String url = "https://findimagevideo.s3.ap-southeast-1.amazonaws.com/";
		String finalUrl = "";
		try {
			try {
				log.info("[" + url + agentVideo.getOriginalFilename() + "] uploaded successfully.");
				String fileName = awss3Service.uploadFile(agentVideo);
				finalUrl = url + fileName;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			response.setData(finalUrl);
			response.markSuccessful("Profile Updated!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!" + e.getMessage());
			return response;
		}
	}

	@PostMapping("/update-profile")
	public Response updateProfile(@RequestHeader("Authorization") String token,@RequestBody AgentProfile agentProfile) {
		Response response = new Response();

		try {
			String mobileno = jwtUtils.getUserNameFromJwtToken(token);
			Agent agent = agentService.updateProfile(agentProfile, mobileno);

			String jwt = jwtUtils.generateJwtTokenForAgent(agent);
			response.setToken(jwt);
			response.markSuccessful("Profile Updated!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}
	}

	@PostMapping({ "/need-help" })
	public Response needHelp(@RequestBody NeedHelp helpReq) {
		Response response = new Response();

		try {
			needHelpService.addHelpRequest(helpReq);
			response.markSuccessful("Request Taken!");

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
			mailMessage.setTo(helpReq.getEmailaddress());
			mailMessage.setSubject("Thank you for contacting Find");
			mailMessage.setFrom(new InternetAddress("infrafind@gmail.com", "FIND"));
			mailMessage.setText(
					"We have received your request and is under process. We will get back to you within 48 hours. For any immediate help, please feel free to contact us at +852 12345678.",
					true);
			mailSender.send(mimeMessage);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}
	}

	@GetMapping({ "/show-help-requests" })
	public Response showNeedHelpRequest() {
		Response response = new Response();

		try {
			response.setData(needHelpService.showNeedHelpRequest());
			response.markSuccessful("List Of All Help Requested!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}
	}

	@GetMapping({ "/remove-help-requests" })
	public Response removeNeedHelpRequest() {
		Response response = new Response();

		try {
			needHelpService.removeNeedHelpRequests();
			response.markSuccessful("Removed All Help Requests!");

			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
			return response;
		}
	}

}
