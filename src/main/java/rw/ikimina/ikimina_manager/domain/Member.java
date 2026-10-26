package rw.ikimina.ikimina_manager.domain;
import java.util.UUID;
import jakarta.persistence.*;
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String memberNumber;
    private String phone;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMemberNumber() {
        return memberNumber;
    }
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
