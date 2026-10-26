package rw.ikimina.ikimina_manager.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ikimina.ikimina_manager.domain.Repayment;
import rw.ikimina.ikimina_manager.service.RepaymentService;

@RestController
@RequestMapping("/api/repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam UUID loanId, @RequestBody Repayment repayment) {
        String returnedMessage = repaymentServ.saveRepayment(loanId, repayment);
        if (returnedMessage.equals("Repayment saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }
        if (returnedMessage.equals("The loan with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(repaymentServ.getAll(), HttpStatus.OK);
    }

    @GetMapping("/member/{memberNumber}")
    public ResponseEntity<?> byMember(@PathVariable String memberNumber) {
        return new ResponseEntity<>(repaymentServ.getByMemberNumber(memberNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = repaymentServ.deleteRepayment(id);
        if (returnedMessage.equals("Repayment deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }
}
