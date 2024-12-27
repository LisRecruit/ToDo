package com.example.ToDo.controllers;

import com.example.ToDo.model.Note;
import com.example.ToDo.model.dto.NoteCreateRequest;
import com.example.ToDo.model.dto.NoteResponse;
import com.example.ToDo.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/note")
public class NoteController {
    public static final String REDIRECT_TO_LIST = "redirect:/note/list";


    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public List<NoteResponse> listNotes(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return noteService.listAll(pageRequest).getContent();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable("id") Long id) {
        noteService.deleteById(id);
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable("id") Long id) {
        return noteService.getById(id);
    }

    @PostMapping("/add")
    public NoteResponse addNote(@Valid @RequestBody NoteCreateRequest request) {
        return noteService.add(request);
    }

    @PutMapping("/edit/{id}")
    public void editNote(@PathVariable("id") Long id, @RequestBody NoteCreateRequest request) {
        noteService.update(id, request);

    }
}
