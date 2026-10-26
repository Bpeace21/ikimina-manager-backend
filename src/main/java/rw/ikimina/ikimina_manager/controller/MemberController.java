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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ikimina.ikimina_manager.domain.Member;
import rw.ikimina.ikimina_manager.service.MemberService;
import rw.ikimina.ikimina_manager.service.TreasurerService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberServ;

    @Autowired
    private TreasurerService treasurerServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Member member) {
        String returnedMessage = memberServ.saveMember(member);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(memberServ.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<?> byLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(memberServ.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/of-treasurer/{treasurerId}")
    public ResponseEntity<?> ofTreasurer(@PathVariable UUID treasurerId) {
        if (treasurerServ.getById(treasurerId).isEmpty()) {
            return new ResponseEntity<>("The treasurer with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberServ.getMembersOfTreasurer(treasurerId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Member> found = memberServ.getById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("The member with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Member member) {
        String returnedMessage = memberServ.updateMember(id, member);
        if (returnedMessage.equals("Member updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String returnedMessage = memberServ.deleteMember(id);
        if (returnedMessage.equals("Member deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
    }
}
