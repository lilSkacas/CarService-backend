package lt.ca.javau11.gr.carservice.repository;

import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
