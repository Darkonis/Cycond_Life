package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.interfaces.AccountService;
import edu.se309.app.backend.service.interfaces.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;


public interface BaseControllerTest {

    @Test
    void count();

    @Test
    void deleteById();

    @Test
    void findAll();

    @Test
    void findById();
}