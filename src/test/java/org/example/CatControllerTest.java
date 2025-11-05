package org.example;

import org.example.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.example.entities.Cat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest
//@Import(SecurityConfig.class)
class CatControllerTest {

    @MockitoBean
    CatRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "api", roles = {"API"})
    void givenRoleAPIShouldReturnAllCats() throws Exception {
        List<Cat> cats = List.of(new Cat("Misse",10,List.of()));
        //Mockito.when(repository.findCatsBy()).thenReturn(cats);

        mockMvc.perform(get("/api/cats")
                        .header("X-API-KEY", "secret"))
                .andExpect(status().isOk());
    }
}