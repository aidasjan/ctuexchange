package cz.cvut.fit.bietjv.exchange.persistence.repositories;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityCreditsDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UniversityRepository extends CrudRepository<University, Integer> {
    @Query("SELECT NEW cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityCreditsDto(u.name, SUM(c.credits)) " +
            "FROM cz.cvut.fit.bietjv.exchange.persistence.entities.Course c " +
            "JOIN c.students s " +
            "JOIN s.university u " +
            "GROUP BY u.name")
    List<UniversityCreditsDto> getUniversityCredits();
}
