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

import rw.ikimina.ikimina_manager.domain.LoanProduct;
import rw.ikimina.ikimina_manager.service.LoanProductService;

@RestController
@RequestMapping("/api/loan-products")
public class LoanProductController {

    @Autowired
    private LoanProductService loanProductServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LoanProduct loanProduct) {
        String returnedMessage = loanProductServ.saveLoanProduct(loanProduct);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loanProductServ.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<LoanProduct> found = loanProductServ.getById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("The loan product with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody LoanProduct loanProduct) {
        String returnedMessage = loanProductServ.updateLoanProduct(id, loanProduct);
        if (returnedMessage.equals("Loan product updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = loanProductServ.deleteLoanProduct(id);
        if (returnedMessage.equals("Loan product deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }
}