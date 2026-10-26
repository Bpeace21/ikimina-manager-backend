package rw.ikimina.ikimina_manager.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.LoanStatus;
import rw.ikimina.ikimina_manager.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Loan loan) {
        String returnedMessage = loanServ.saveLoan(loan);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loanServ.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> byStatus(@RequestParam LoanStatus status) {
        return new ResponseEntity<>(loanServ.getByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<?> between(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(loanServ.getBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Loan> found = loanServ.getById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("The loan with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Loan loan) {
        String returnedMessage = loanServ.updateLoan(id, loan);
        if (returnedMessage.equals("Loan updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = loanServ.deleteLoan(id);
        if (returnedMessage.equals("Loan deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }
}
