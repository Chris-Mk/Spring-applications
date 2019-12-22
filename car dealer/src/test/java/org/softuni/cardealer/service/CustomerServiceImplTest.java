package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerServiceModel customerServiceModel;
    private Customer customer;

    @Before
    public void setUp() {
        customerServiceModel = new CustomerServiceModel() {{
            setName("Chris");
            setBirthDate(LocalDate.of(1997, 10, 23));
            setYoungDriver(true);
        }};

        customer = new Customer() {{
            setName("Chris");
            setBirthDate(LocalDate.of(1997, 10, 23));
            setYoungDriver(true);
        }};

        when(modelMapper.map(customerServiceModel, Customer.class)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerServiceModel.class)).thenReturn(customerServiceModel);
    }

    @Test
    public void saveCustomer_withCorrectData_worksFine() {
        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(customer);

        final CustomerServiceModel expected = customerService.saveCustomer(customerServiceModel);

        assertEquals(expected, customerServiceModel);
    }

    @Test
    public void editCustomer_withCorrectData_worksFine() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel() {{
            setId("id");
            setName("Fred");
            setBirthDate(LocalDate.of(2001, 6, 9));
            setYoungDriver(true);
        }};

        Customer customer = new Customer() {{
            setName("Fred");
            setBirthDate(LocalDate.of(2001, 6, 9));
            setYoungDriver(true);
        }};

        when(customerRepository.findById(customerServiceModel.getId())).thenReturn(Optional.ofNullable(this.customer));
        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(customer);
        when(modelMapper.map(customer, CustomerServiceModel.class)).thenReturn(customerServiceModel);

        final CustomerServiceModel expected = customerService.editCustomer(customerServiceModel);

        assertEquals(expected, customerServiceModel);
    }

    @Test
    public void deleteCustomer_withCorrectId_worksFine() {
        when(customerRepository.findById("id")).thenReturn(Optional.ofNullable(customer));

        final CustomerServiceModel expected = customerService.deleteCustomer("id");

        verify(customerRepository).delete(customer);

        assertEquals(expected, customerServiceModel);
    }

    @Test
    public void findById_withCorrectId_worksFine() {
        when(customerRepository.findById("id")).thenReturn(Optional.ofNullable(customer));

        final CustomerServiceModel expected = customerService.findCustomerById("id");

        assertEquals(expected, customerServiceModel);
    }
}