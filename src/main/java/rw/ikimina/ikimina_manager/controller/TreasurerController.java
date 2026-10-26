package rw.ikimina.ikimina_manager.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ikimina.ikimina_manager.domain.Treasurer;
import rw.ikimina.ikimina_manager.service.TreasurerService;

@RestController
@RequestMapping("/api/treasurers")
public class TreasurerController {

    @Autowired
    private TreasurerService treasurerServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Treasurer treasurer) {
        String returnedMessage = treasurerServ.saveTreasurer(treasurer);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(treasurerServ.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Treasurer> found = treasurerServ.getById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("The treasurer with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Treasurer treasurer) {
        String returnedMessage = treasurerServ.updateTreasurer(id, treasurer);
        if (returnedMessage.equals("Treasurer updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = treasurerServ.deleteTreasurer(id);
        if (returnedMessage.equals("Treasurer deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }
}
