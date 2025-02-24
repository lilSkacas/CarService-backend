package lt.ca.javau11.gr.carservice.repository;

import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
    List<MaintenanceEntity> findByVehicle(VehicleEntity vehicle);
    List<MaintenanceEntity> findByVehicleAndDateBetween(VehicleEntity vehicle, LocalDate startDate, LocalDate endDate);
}
