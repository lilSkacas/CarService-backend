package lt.ca.javau11.gr.carservice.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class MaintenanceDto {

    private Long id;

    private LocalDate date;
    private String description;
    private BigDecimal price;

    public MaintenanceDto () {}

    public MaintenanceDto(Long id, LocalDate date, String description, BigDecimal price) {
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

    @Override
    public String toString() {
        return "MaintenanceDto{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
