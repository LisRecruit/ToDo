package com.example.ToDo.service;


import com.example.ToDo.mapper.NoteMapper;
import com.example.ToDo.model.Note;
import com.example.ToDo.model.dto.NoteCreateRequest;
import com.example.ToDo.model.dto.NoteResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ToDo.repository.NoteRepository;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class NoteService {
    private final NoteMapper noteMapper;
    private final NoteRepository noteRepository;

    public Page<NoteResponse> listAll(PageRequest pageRequest) {
        return noteRepository.findAll(pageRequest)
                .map(noteMapper::toNoteResponse);
    }

    @Transactional
    public NoteResponse add(NoteCreateRequest request) {

        if ((request.getTitle() == null || request.getTitle().isBlank()) &&
                (request.getContent() == null || request.getContent().isBlank())) {
            throw new IllegalArgumentException("Either title or content must be provided.");
        }
       Note note = noteMapper.toNote(request);
        note=noteRepository.save(note);
//       noteRepository.save(note);
        return noteMapper.toNoteResponse(note);
    }

    @Transactional
    public void deleteById(Long id){
        noteRepository.deleteById(id);
    }
    @Transactional
    public void update(Long id, NoteCreateRequest request) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + id));
        existingNote.setTitle(request.title());
        existingNote.setContent(request.content());

        noteRepository.save(existingNote);
    }

    public NoteResponse getById (Long id){
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new  EntityNotFoundException("Note with id " + id + " not found"));
        return noteMapper.toNoteResponse(note);
    }

}
