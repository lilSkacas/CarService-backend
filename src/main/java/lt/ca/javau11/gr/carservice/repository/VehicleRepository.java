package lt.ca.javau11.gr.carservice.repository;

import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    List<VehicleEntity> findByClient(ClientEntity client);
    Optional<VehicleEntity> findByLicensePlate(String licensePlate);
    boolean existsByLicensePlate(String licensePlate);
}
