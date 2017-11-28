package duckydebug.project.repository;

import duckydebug.project.entity.DatabaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseStatusRepository extends JpaRepository<DatabaseStatus,Integer> {
}
