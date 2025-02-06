package lt.ca.javau11.gr.carservice.repository;


import lt.ca.javau11.gr.carservice.enums.ERole;
import lt.ca.javau11.gr.carservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName (ERole name);
}
