package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.BaseItem;
import edu.se309.app.backend.rest.service.interfaces.BaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base Item Controller
 */
@RestController
@RequestMapping("/api/items")
public class BaseItemController extends BaseController<BaseItem, Integer, BaseItemService> {

    /**
     * Constructor for this Controller
     *
     * @param baseItemService Service associated with this controller
     */
    @Autowired
    public BaseItemController(BaseItemService baseItemService) {
        super(baseItemService);
    }
}