package com.find.findcore.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.find.findcore.exception.RefreshTokenException;
import com.find.findcore.model.entity.RefreshToken;
import com.find.findcore.repository.AgentRepository;
import com.find.findcore.repository.RefreshTokenRepository;
import com.find.findcore.repository.UserRepository;

@Service("RefreshTokenService")
public class RefreshTokenServiceImpl {
	@Value("${find.app.jwtRefreshExpirationMs}")
	private int jwtRefreshExpirationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgentRepository agentRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	public RefreshToken createRefreshTokenForAgent(Long agentId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setAgent(agentRepository.findById(agentId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RefreshTokenException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}
		return token;
	}

	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}
	
	@Transactional
	public int deleteByAgentId(Long agentId) {
		return refreshTokenRepository.deleteByAgent(agentRepository.findById(agentId).get());
	}
}