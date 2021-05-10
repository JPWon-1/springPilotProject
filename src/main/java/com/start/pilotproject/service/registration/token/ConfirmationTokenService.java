package com.start.pilotproject.service.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import com.start.pilotproject.domain.registration.token.ConfirmationToken;
import com.start.pilotproject.repository.registration.token.ConfirmationTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return confirmationTokenRepository.updateConfirmedAt(
            token, LocalDateTime.now()
        );
    }
}
