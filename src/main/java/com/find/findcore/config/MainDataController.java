package com.find.findcore.config;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.find.findcore.model.entity.Profile;
import com.find.findcore.model.enumeration.Provider;
import com.find.findcore.model.payload.request.ProfileRequest;
import com.find.findcore.repository.ProfileRepository;
import com.find.findcore.repository.UserRepository;
import com.find.findcore.security.jwt.JwtUtils;
//import com.find.findcore.service.impl.DocumentManagementServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class MainDataController {
	private static final Logger log = LoggerFactory.getLogger(MainDataController.class);
	@Autowired
	ProfileRepository profileRepository;

	// @Autowired
	// DocumentManagementServiceImpl documentManagementService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping(value = "/updateProfile", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Profile> saveProfileData(@RequestBody ProfileRequest profileRequest,
			@RequestHeader("Authorization") String token) {
		String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
		Profile profile = profileRepository.findByEmailAndProvider(tempEmail,
				Provider.valueOf(profileRequest.getProvider().toUpperCase()));

		profile.setDisplayEmail(profileRequest.getDisplayEmail());
		profile.setFullName(profileRequest.getFullName());
		profile.setPhone(profileRequest.getPhone());
		profile.setGender(profileRequest.getGender());
		profile.setDateOfBirth(profileRequest.getDateOfBirth());
		profile.setAvatarImage(profileRequest.getAvatarImage());
		profile.setLastModified(LocalDateTime.now());

		profileRepository.save(profile);

		return new ResponseEntity<>(profile, HttpStatus.CREATED);
	}

	@GetMapping({ "/getProfileByEmail" })
	public ResponseEntity<Profile> getProfileByEmail(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(profileRepository.findByEmailAndProvider(getTempEmail(token),
				Provider.valueOf(getTempProvider(token).toUpperCase())), HttpStatus.OK);
	}

	public String getTempEmail(String token) {
		String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
		return tempEmail;
	}

	public String getTempProvider(String token) {
		String tempEmail = this.getTempEmail(token);
		String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
		String tempProvider = str.replace(".com", "");
		if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider)
				|| "APPLE".equalsIgnoreCase(tempProvider))) {
			tempProvider = "LOCAL";
		}
		return tempProvider;
	}
	/*
	 * @PostMapping(value = "/addPreferences", produces = "application/json",
	 * consumes = "application/json") public ResponseEntity<PreferenceRequest>
	 * addPreferences(@RequestBody PreferenceRequest preferenceRequest) { User user
	 * = userRepository.findByEmailAndProvider(preferenceRequest.getEmail(),
	 * Provider.valueOf(preferenceRequest.getProvider().toUpperCase())).get();
	 * 
	 * Set<String> movieGenres = preferenceRequest.getMovieGenres(); Set<String>
	 * movieLanguages = preferenceRequest.getMovieLanguages(); Set<String>
	 * eventGenres = preferenceRequest.getEventGenre();
	 * 
	 * Set<MovieGenre> movieGenresSet = new HashSet<>(); Set<MovieLanguage>
	 * movieLanguageSet = new HashSet<>(); Set<EventGenre> eventGenresSet = new
	 * HashSet<>();
	 * 
	 * movieGenres.forEach(movieG -> { MovieGenre movieGenre =
	 * movieGenreRepository.findByMovieGenre(EMovieGenre.valueOf(movieG.toUpperCase(
	 * ))) .orElseThrow(() -> new
	 * RuntimeException("Error: Movie Genre(s) are not found."));
	 * movieGenresSet.add(movieGenre); }); movieLanguages.forEach(movieL -> {
	 * MovieLanguage movieLanguage = movieLanguageRepository
	 * .findByMovieLanguage(EMovieLanguage.valueOf(movieL.toUpperCase()))
	 * .orElseThrow(() -> new
	 * RuntimeException("Error: Movie Language(s) are not found."));
	 * movieLanguageSet.add(movieLanguage); }); eventGenres.forEach(eventG -> {
	 * EventGenre eventGenre =
	 * eventGenreRepository.findByEventGenre(EEventGenre.valueOf(eventG.toUpperCase(
	 * ))) .orElseThrow(() -> new
	 * RuntimeException("Error: Event Genre(s) are not found."));
	 * eventGenresSet.add(eventGenre); });
	 * 
	 * user.setMovieGenres(movieGenresSet); user.setMovieLanguage(movieLanguageSet);
	 * user.setEventGenres(eventGenresSet);
	 * 
	 * userRepository.save(user);
	 * 
	 * PreferenceRequest preferenceRequest1 = new PreferenceRequest();
	 * preferenceRequest1.setEmail(preferenceRequest.getEmail());
	 * preferenceRequest1.setProvider(preferenceRequest.getProvider());
	 * preferenceRequest1.setMovieGenres(movieGenresSet.stream().map(Object::
	 * toString).collect(Collectors.toSet())); preferenceRequest1
	 * .setMovieLanguages(movieLanguageSet.stream().map(Object::toString).collect(
	 * Collectors.toSet()));
	 * preferenceRequest1.setEventGenre(eventGenresSet.stream().map(Object::toString
	 * ).collect(Collectors.toSet()));
	 * 
	 * return new ResponseEntity<>(preferenceRequest1, HttpStatus.OK); }
	 * 
	 * @GetMapping(value = "/getPreferences", produces = "application/json") public
	 * ResponseEntity<PreferenceRequest>
	 * getPreferences(@RequestHeader("Authorization") String token) { String
	 * tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7)); String str
	 * = tempEmail.substring(tempEmail.lastIndexOf("@") + 1); String tempProvider =
	 * str.replace(".com", ""); if (!("GOOGLE".equalsIgnoreCase(tempProvider) ||
	 * "FACEBOOK".equalsIgnoreCase(tempProvider) ||
	 * "APPLE".equalsIgnoreCase(tempProvider))) { tempProvider = "LOCAL"; } User
	 * user = userRepository.findByEmailAndProvider(tempEmail,
	 * Provider.valueOf(tempProvider.toUpperCase())) .get();
	 * 
	 * Set<MovieGenre> movieGenresSet =
	 * movieGenreRepository.findAllByUserId(user.getId()); Set<MovieLanguage>
	 * movieLanguageSet = movieLanguageRepository.findAllByUserId(user.getId());
	 * Set<EventGenre> eventGenresSet =
	 * eventGenreRepository.findAllByUserId(user.getId());
	 * 
	 * PreferenceRequest preferenceRequest1 = new PreferenceRequest();
	 * preferenceRequest1.setEmail(tempEmail);
	 * preferenceRequest1.setProvider(tempProvider);
	 * preferenceRequest1.setMovieGenres(movieGenresSet.stream().map(Object::
	 * toString).collect(Collectors.toSet())); preferenceRequest1
	 * .setMovieLanguages(movieLanguageSet.stream().map(Object::toString).collect(
	 * Collectors.toSet()));
	 * preferenceRequest1.setEventGenre(eventGenresSet.stream().map(Object::toString
	 * ).collect(Collectors.toSet()));
	 * 
	 * return new ResponseEntity<>(preferenceRequest1, HttpStatus.OK); }
	 */
}