package rw.ikimina.ikimina_manager.domain;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate loanDate;


    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private int principal;
    private String purpose;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Treasurer getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(Treasurer treasurer) {
        this.treasurer = treasurer;
    }

    @ManyToOne 
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne 
    @JoinColumn(name = "treasurer_id")
    private Treasurer treasurer;
}