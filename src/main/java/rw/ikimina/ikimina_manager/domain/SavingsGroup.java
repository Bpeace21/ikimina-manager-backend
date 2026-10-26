package rw.ikimina.ikimina_manager.domain;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "savings_group")
public class SavingsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int groupNumber;

    private String name;
}