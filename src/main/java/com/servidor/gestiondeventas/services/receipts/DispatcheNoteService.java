package com.servidor.gestiondeventas.services.receipts;

import com.servidor.gestiondeventas.entities.receipts.DispatcheNote;

import java.util.List;
import java.util.Optional;

public interface DispatcheNoteService {
    public List<DispatcheNote> getDispatcheNote();

    public Optional<DispatcheNote> getDispatcheNoteById(Long idDispatcheNote);

    public DispatcheNote createDispatcheNote(DispatcheNote dispatcheNote);

    public DispatcheNote editDispatcheNote(DispatcheNote dispatcheNote);

    public boolean deleteDispatcheNote(Long idDispatcheNote);
}
