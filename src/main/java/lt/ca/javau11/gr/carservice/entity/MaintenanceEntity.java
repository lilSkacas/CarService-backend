package lt.ca.javau11.gr.carservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class MaintenanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private String description;
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    public MaintenanceEntity() {}

    public MaintenanceEntity(Long id, LocalDate date, String description, BigDecimal price, VehicleEntity vehicle, ClientEntity client) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.vehicle = vehicle;
        this.client = client;
    }

    public MaintenanceEntity(Long id, LocalDate date, String description, BigDecimal price) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "MaintenanceEntity{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vehicle=" + vehicle +
                ", client=" + client +
                '}';
    }
}

