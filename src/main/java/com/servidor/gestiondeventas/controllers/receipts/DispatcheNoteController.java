package com.servidor.gestiondeventas.controllers.receipts;

import com.servidor.gestiondeventas.entities.receipts.DispatcheNote;
import com.servidor.gestiondeventas.services.receipts.DispatcheNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dispatcheNote")
public class DispatcheNoteController {
    @Autowired
    DispatcheNoteService dispatcheNoteService;

    @GetMapping
    public ResponseEntity<List<DispatcheNote>> getDispatcheNote(){
        return new ResponseEntity<>(dispatcheNoteService.getDispatcheNote(),HttpStatus.OK);
    }

    @GetMapping("/{idDispatcheNote}")
    public ResponseEntity<Optional<DispatcheNote>> getDispatcheNoteById(@PathVariable Long idDispatcheNote){
        return new ResponseEntity<>(dispatcheNoteService.getDispatcheNoteById(idDispatcheNote),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DispatcheNote> createDispatcheNote(@RequestBody DispatcheNote dispatcheNote){
        return new ResponseEntity<>(dispatcheNoteService.createDispatcheNote(dispatcheNote),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<DispatcheNote> editDispatcheNote(@RequestBody DispatcheNote dispatcheNote){
        return new ResponseEntity<>(dispatcheNoteService.editDispatcheNote(dispatcheNote),HttpStatus.OK);
    }
    @DeleteMapping("/{idDispatcheNote}")
    public String deleteDispatcheNote(@PathVariable Long idDispatcheNote){
        boolean booleanDispatcheNote = dispatcheNoteService.deleteDispatcheNote(idDispatcheNote);
        if(booleanDispatcheNote){
            return "El Remito fue eliminado con éxito";
        }
        return "El remito no éxiste";
    }
}
