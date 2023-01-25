package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.model.Speaker;
import com.pluralsight.conferencedemo.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    private final SpeakerRepository speakerRepository;

    @Autowired
    public SpeakersController(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @GetMapping()
    public List<Speaker> getSpeakers() {
        return speakerRepository.findAll();
    }

    @GetMapping("{id}")
    public Speaker getSpeaker(@PathVariable("id") Long id) {
        return speakerRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker postSpeaker(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping("{id}")
    public void deleteSpeaker(@PathVariable("id") Long id) {
        speakerRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Speaker putSpeaker(@PathVariable("id") Long id, @RequestBody final Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
