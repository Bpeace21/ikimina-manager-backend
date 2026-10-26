package rw.ikimina.ikimina_manager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_product")
public class LoanProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "loanProducts")
    private List<Treasurer> treasurers = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Treasurer> getTreasurers() {
        return treasurers;
    }

    public void setTreasurers(List<Treasurer> treasurers) {
        this.treasurers = treasurers;
    }
}