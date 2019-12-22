package org.softuni.cardealer.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.service.SupplierService;
import org.softuni.cardealer.web.SupplierController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIndex_returnsCorrectView() throws Exception {
        mockMvc.perform(get("/supplier"))
                .andExpect(view().name("index"));
    }
}