package org.softuni.cardealer.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.binding.AddSupplierBindingModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletContext;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SuppliersController.class)
@WithMockUser("spring")
public class SuppliersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private List<SupplierServiceModel> supplierServiceModels;

    @Before
    public void setUp() {
        supplierServiceModels =
                List.of(
                        new SupplierServiceModel() {{
                            setId("id1");
                            setName("Pesho");
                            setIsImporter(true);
                        }},

                        new SupplierServiceModel() {{
                            setId("id2");
                            setName("Gosho");
                            setIsImporter(false);
                        }});

        when(supplierService.findAll()).thenReturn(supplierServiceModels);
    }

    @Test
    public void allSuppliers_addSuppliersToModel_andRendersAllSuppliersView() throws Exception {
        mockMvc.perform(get("/suppliers/all"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("suppliers"))
                .andExpect(model().attribute("suppliers", hasItems(
                        allOf(
                                hasProperty("id", is("id1")),
                                hasProperty("name", is("Pesho")),
                                hasProperty("isImporter", is(true))
                        ),
                        allOf(
                                hasProperty("id", is("id2")),
                                hasProperty("name", is("Gosho")),
                                hasProperty("isImporter", is(false))
                        )
                )))
                .andExpect(view().name("all-suppliers"));

        verify(supplierService).findAll();
    }

    @Test
    public void addSupplier_redirectsToAllPage() throws Exception {
        AddSupplierBindingModel bindingModel = new AddSupplierBindingModel() {{
            setName("Pesho");
            setIsImporter(true);
        }};

        when(modelMapper.map(bindingModel, SupplierServiceModel.class))
                .thenReturn(supplierServiceModels.get(0));

//        doNothing().when(supplierService).saveSupplier(supplierServiceModels.get(0));

        mockMvc.perform(
                post("/suppliers/add")
                        .content(objectMapper.writeValueAsString(bindingModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("all"));

        verify(supplierService).saveSupplier(any());
    }

    @Test
    public void addSupplier_withInvalidData_throwsException() throws Exception {
        AddSupplierBindingModel bindingModel = new AddSupplierBindingModel() {{
            setName(null);
            setIsImporter(true);
        }};

//        ReflectionTestUtils.getField()
//        MockHttpServletRequest mockHttpServletRequest =new MockHttpServletRequest();
//        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
//        MockHttpSession mockHttpSession = new MockHttpSession();
//        mockHttpSession.

        mockMvc.perform(
                post("/suppliers/add")
                        .content(objectMapper.writeValueAsString(bindingModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editSupplier_redirectToAllSuppliers() throws Exception {
        mockMvc.perform(post("/suppliers/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/suppliers/all"));
    }

    @Test
    public void fetchSupplier_returnsAListOfAllSupplier_asResponseBody() throws Exception {
        mockMvc.perform(get("/suppliers/fetch")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("id1")))
                .andExpect(jsonPath("$[0].name", is("Pesho")))
                .andExpect(jsonPath("$[0].isImporter", is(true)))
                .andExpect(jsonPath("$[1].id", is("id2")))
                .andExpect(jsonPath("$[1].name", is("Gosho")))
                .andExpect(jsonPath("$[1].isImporter", is(false)));

        verify(supplierService).findAll();
    }
}