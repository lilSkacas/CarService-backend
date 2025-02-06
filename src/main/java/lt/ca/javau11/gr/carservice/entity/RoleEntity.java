package lt.ca.javau11.gr.carservice.entity;


import jakarta.persistence.*;
import lt.ca.javau11.gr.carservice.enums.ERole;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public RoleEntity() {}

    public RoleEntity(ERole name) {
        this.name = name;

    }

    public void setName(ERole name) {
        this.name = name;
    }

    public String getName() {
        return name.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
