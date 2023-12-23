package com.servidor.gestiondeventas.repository.receipts;

import com.servidor.gestiondeventas.entities.receipts.DispatcheNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcheNoteRepository extends JpaRepository<DispatcheNote, Long> {
}
