package lt.ca.javau11.gr.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDto {
    private Long id;
    private LocalDate date;
    private String description;
    private BigDecimal price;
    private Long vehicleId;
}
