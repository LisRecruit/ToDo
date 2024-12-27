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



@Controller
@RequestMapping("/note")
public class NoteController {
    public static final String REDIRECT_TO_LIST = "redirect:/note/list";


    private NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/list")
    public String listNotes(Model model) {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("notes", noteService.listAll(pageRequest));
        return "note_list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        noteService.deleteById(id);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit")
    public String editNoteForm(@RequestParam("id") Long id, Model model) {
        NoteResponse note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note_edit";
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam("id") Long id, @ModelAttribute NoteCreateRequest request) {
        noteService.update(id, request);
        return REDIRECT_TO_LIST;
    }


    @GetMapping("/add")
    public String showAddNoteForm(Model model){
        model.addAttribute("note", new Note());
        return "add_note";
    }

@PostMapping("/add")
public String addNote(@Valid @ModelAttribute("note") NoteCreateRequest request,
                      BindingResult bindingResult,
                      Model model) {
    if ((request.getTitle() == null || request.getTitle().isBlank()) &&
            (request.getContent() == null || request.getContent().isBlank())) {
        bindingResult.reject("contentOrTitle", "Please provide at least Title or Content.");
    }

    if (bindingResult.hasErrors()) {
        return "add_note";
    }

    noteService.add(request);
    return REDIRECT_TO_LIST;
}

}
