package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.model.Session;
import com.pluralsight.conferencedemo.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping()
    public List<Session> getSessions() {
        return this.sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public Session getSession(@PathVariable("id") Long id) {
        return this.sessionRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session postSession(@RequestBody final Session session) {
        return this.sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping("{id}")
    public void deleteSession(@PathVariable("id") Long id) {
        sessionRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Session putSession(@PathVariable("id") Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getReferenceById(id);
        BeanUtils.copyProperties(session, existingSession, "id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
