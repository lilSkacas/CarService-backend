package lt.ca.javau11.gr.carservice.repository;

import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByUser(UserEntity user);
    Optional<ClientEntity> findByEmail(String email);
    Optional<ClientEntity> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
