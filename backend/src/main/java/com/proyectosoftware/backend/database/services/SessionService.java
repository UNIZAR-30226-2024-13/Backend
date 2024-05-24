package com.proyectosoftware.backend.database.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Session;
import com.proyectosoftware.backend.database.repository.SessionRepository;

@Service
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;

    public Optional<Session> getSession(String sessionId){
        return sessionRepository.findBySessionId(sessionId);
    }

    public Optional<Session> resetSession(String userId){
        Optional<Session> sesion = sessionRepository.findById(userId);
        sesion.ifPresent(
            (s) -> {
                s.setSessionToken(s.generateSessionToken());
                sessionRepository.save(s);
            }
        );
        return sesion;
    }

    public void deleteSession(String sessionId){
        sessionRepository.delete(getSession(sessionId).orElseThrow());
    }
}
