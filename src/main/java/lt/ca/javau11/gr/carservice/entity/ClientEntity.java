package lt.ca.javau11.gr.carservice.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<MaintenanceEntity> maintenance;


    public ClientEntity () {}

    public ClientEntity(Long id, String firstName, String lastName, String email, String phoneNumber, List<MaintenanceEntity> maintenance) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.maintenance = maintenance;
    }

    public ClientEntity(Long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public List<MaintenanceEntity> getMaintenance() {

        return maintenance;
    }

    public void setServices(List<MaintenanceEntity> maintenance) {

        this.maintenance = maintenance;
    }

}
