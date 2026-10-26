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

import rw.ikimina.ikimina_manager.domain.SavingsGroup;
import rw.ikimina.ikimina_manager.service.SavingsGroupService;

@RestController
@RequestMapping ("/api/savings-groups")
public class SavingsGroupController {
   @Autowired
    private SavingsGroupService savingsGroupServ;
    
     @PostMapping("/save")
     public ResponseEntity<?> save(@RequestBody SavingsGroup savingsGroup) {
          String returnedMessage = savingsGroupServ.saveSavingsGroup(savingsGroup);
          return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
     }
    
     @GetMapping("/all")
     public ResponseEntity<?> getAll() {
          return new ResponseEntity<>(savingsGroupServ.getAll(), HttpStatus.OK);
     }
     @GetMapping("/{id}")
     public ResponseEntity<?> getById(@PathVariable UUID id) {
          Optional<SavingsGroup> found = savingsGroupServ.getById(id);
          if (found.isEmpty()) {
                return new ResponseEntity<>("The savings group with that id does not exist", HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>(found.get(), HttpStatus.OK);
     }
     @PutMapping("/update/{id}")
     public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SavingsGroup savingsGroup) {
          String returnedMessage = savingsGroupServ.updateSavingsGroup(id, savingsGroup);
          if (returnedMessage.equals("Savings group updated successfully")) {
                return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
          }
          return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
     }
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> delete(@PathVariable UUID id) {
          String returnedMessage = savingsGroupServ.deleteSavingsGroup(id);
          if (returnedMessage.equals("Savings group deleted successfully")) {
                return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
          }
          return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
     } 
}
