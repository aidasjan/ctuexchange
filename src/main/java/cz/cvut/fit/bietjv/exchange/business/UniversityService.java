package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityCreditsDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService extends AbstractCrudService<University, UniversityDto, UniversityRepository> {

    public List<UniversityCreditsDto> getUniversityCredits() {
        return repository.getUniversityCredits();
    }
}
