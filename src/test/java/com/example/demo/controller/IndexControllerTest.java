package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/detailInfo").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("detailInfo"));
    }
}