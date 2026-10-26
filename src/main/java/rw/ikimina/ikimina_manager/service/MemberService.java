package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.Member;
import rw.ikimina.ikimina_manager.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    public String saveMember(Member member) {
        memberRepo.save(member);
        return "Member saved successfully";
    }

    public List<Member> getAll() {
        return memberRepo.findAll();
    }

    public List<Member> getByLastName(String lastName) {
        return memberRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Member> getMembersOfTreasurer(UUID treasurerId) {
        return memberRepo.findMembersOfTreasurer(treasurerId);
    }

    public Optional<Member> getById(UUID id) {
        return memberRepo.findById(id);
    }

    public String updateMember(UUID id, Member newData) {
        Optional<Member> found = memberRepo.findById(id);
        if (found.isEmpty()) {
            return "The member with that id does not exist";
        }
        Member old = found.get();
        old.setFirstName(newData.getFirstName());
        old.setLastName(newData.getLastName());
        old.setMemberNumber(newData.getMemberNumber());
        old.setPhone(newData.getPhone());
        memberRepo.save(old);
        return "Member updated successfully";
    }

    public String deleteMember(UUID id) {
        Optional<Member> found = memberRepo.findById(id);
        if (found.isEmpty()) {
            return "The member with that id does not exist";
        }
        memberRepo.delete(found.get());
        return "Member deleted successfully";
    }
}