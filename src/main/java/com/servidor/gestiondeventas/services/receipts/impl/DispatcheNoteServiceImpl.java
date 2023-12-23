package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.receipts.DispatcheNote;
import com.servidor.gestiondeventas.repository.receipts.DispatcheNoteRepository;
import com.servidor.gestiondeventas.services.receipts.DispatcheNoteService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class DispatcheNoteServiceImpl implements DispatcheNoteService {
    DispatcheNoteRepository dispatcheNoteRepository;

    @Override
    public List<DispatcheNote> getDispatcheNote() {

        return dispatcheNoteRepository.findAll();
    }

    @Override
    public Optional<DispatcheNote> getDispatcheNoteById(Long idDispatcheNote) {

        return dispatcheNoteRepository.findById(idDispatcheNote);
    }

    @Override
    public DispatcheNote createDispatcheNote(DispatcheNote dispatcheNote) {

        return dispatcheNoteRepository.save(dispatcheNote);
    }

    @Override
    public DispatcheNote editDispatcheNote(DispatcheNote dispatcheNote) {

        return dispatcheNoteRepository.save(dispatcheNote);
    }

    @Override
    public boolean deleteDispatcheNote(Long idDispatcheNote) {
        Optional<DispatcheNote> dispatcheNote = dispatcheNoteRepository.findById(idDispatcheNote);
        if(dispatcheNote.isPresent()){
            dispatcheNoteRepository.deleteById(idDispatcheNote);
            return true;
        }
        return false;
    }
}
