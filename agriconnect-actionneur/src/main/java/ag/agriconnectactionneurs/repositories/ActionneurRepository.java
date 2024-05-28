package ag.agriconnectactionneurs.repositories;

import ag.agriconnectactionneurs.entities.Actionneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionneurRepository extends JpaRepository<Actionneur, Long> {
}
