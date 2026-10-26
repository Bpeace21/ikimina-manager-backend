package rw.ikimina.ikimina_manager.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
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
        if (returnedMessage.equals("Loan saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }
        if (returnedMessage.equals("Treasurer already processes a loan on that date")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
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

    @GetMapping("/stats/by-status")
    public ResponseEntity<?> stats() {
        return new ResponseEntity<>(loanServ.getStatsByStatus(), HttpStatus.OK);
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<?> cancelDay(@RequestParam UUID treasurerId, @RequestParam String date) {
        String returnedMessage = loanServ.cancelDay(treasurerId, LocalDate.parse(date));
        return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> page(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
        String[] parts = sort.split(",", -1);
        if (page < 0 || size <= 0 || parts.length != 2 || parts[0].isBlank()) {
            return new ResponseEntity<>("Expected page >= 0, size > 0, and sort in field,direction format",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Sort.Direction direction = Sort.Direction.fromString(parts[1]);
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));
            return new ResponseEntity<>(loanServ.getPage(pageable), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Invalid sort direction; use ASC or DESC", HttpStatus.BAD_REQUEST);
        }
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

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, @RequestParam LoanStatus status) {
        String returnedMessage = loanServ.updateStatus(id, status);
        if (returnedMessage.equals("Loan status updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        if (returnedMessage.equals("The loan with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = loanServ.deleteLoan(id);
        if (returnedMessage.equals("Loan deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        if (returnedMessage.equals("The loan with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
    }
}
