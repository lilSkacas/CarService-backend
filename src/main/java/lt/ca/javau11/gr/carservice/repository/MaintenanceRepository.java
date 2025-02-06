package lt.ca.javau11.gr.carservice.repository;

import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
}
