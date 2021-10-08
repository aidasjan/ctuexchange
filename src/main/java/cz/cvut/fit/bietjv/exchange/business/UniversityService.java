package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityService extends AbstractCrudService<University, UniversityDto, UniversityRepository> {

}
