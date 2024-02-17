package highton.team2.repository;

import highton.team2.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodosRepository extends JpaRepository<Todos, Long> {
    Optional<Todos> findByUserId(String userId);
    List<Todos> findAllByUserId(String userId);
}
