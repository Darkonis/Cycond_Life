package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.BaseItem;
import edu.se309.app.backend.rest.repository.BaseItemRepository;
import edu.se309.app.backend.rest.service.interfaces.BaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Base Item Service
 */
@Service
public class BaseItemServiceImplementation extends
        BaseServiceImplementation<BaseItem, Integer, BaseItemRepository> implements BaseItemService {

    /**
     * Constructor for BaseItemServiceImplementation
     * @param baseItemRepository repository associated with this service
     */
    @Autowired
    public BaseItemServiceImplementation(BaseItemRepository baseItemRepository) {
        super(baseItemRepository);
    }
}
