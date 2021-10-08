package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.TagDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService extends AbstractCrudService<Tag, TagDto, TagRepository> {

}
