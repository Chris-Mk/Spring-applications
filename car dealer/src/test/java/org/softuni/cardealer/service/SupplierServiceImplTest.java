package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    private Supplier supplier;
    private SupplierServiceModel supplierServiceModel;

    @Before
    public void setUp() {
        supplier = new Supplier() {{
            setName("Pesho");
            setImporter(true);
        }};

        supplierServiceModel = new SupplierServiceModel() {{
            setName("Pesho");
            setImporter(true);
        }};

        when(modelMapper.map(supplierServiceModel, Supplier.class)).thenReturn(supplier);
        when(modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(supplierServiceModel);
    }

    @Test
    public void saveSupplier_withCorrectData_worksFine() {
        final SupplierServiceModel expected = supplierService.saveSupplier(supplierServiceModel);

        verify(supplierRepository).saveAndFlush(supplier);

        assertEquals(expected, supplierServiceModel);
    }

    @Test
    public void editSupplier_withCorrectData_worksFine() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel() {{
            setId("1l");
            setName("Gosho");
            setImporter(false);
        }};

        Supplier supplier = new Supplier() {{
            setName("Gosho");
            setImporter(false);
        }};

        when(supplierRepository.findById(supplierServiceModel.getId())).thenReturn(Optional.of(this.supplier));
        when(supplierRepository.saveAndFlush(any(Supplier.class))).thenReturn(supplier);
        when(modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(supplierServiceModel);

        final SupplierServiceModel expected = supplierService.editSupplier(supplierServiceModel);

        verify(supplierRepository).saveAndFlush(any(Supplier.class));

        assertEquals(expected, supplierServiceModel);
    }

    @Test
    public void deleteSupplier_withCorrectId_worksFine() {
        when(supplierRepository.findById("id")).thenReturn(Optional.of(supplier));

        final SupplierServiceModel expected = supplierService.deleteSupplier("id");

        verify(supplierRepository).delete(supplier);

        assertEquals(expected, supplierServiceModel);
    }

    @Test
    public void findById_withCorrectId_worksFine() {
        when(supplierRepository.findById("id")).thenReturn(Optional.of(supplier));

        final SupplierServiceModel expected = supplierService.findSupplierById("id");

        verify(supplierRepository).findById(anyString());

        assertEquals(expected, supplierServiceModel);
    }
}