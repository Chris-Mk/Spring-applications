package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.PartRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartServiceImplTest {

    @Mock
    private PartRepository partRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PartServiceImpl partService;

    private PartServiceModel partServiceModel;
    private Part part;

    @Before
    public void setUp() {
        partServiceModel = new PartServiceModel() {{
            setName("Wheel");
            setPrice(new BigDecimal("100.00"));
            setSupplier(new SupplierServiceModel() {{
                setName("Pesho");
                setImporter(true);
            }});
        }};

        part = new Part() {{
            setName("Wheel");
            setPrice(new BigDecimal("100.00"));
            setSupplier(new Supplier() {{
                setName("Pesho");
                setImporter(true);
            }});
        }};

        when(modelMapper.map(partServiceModel, Part.class)).thenReturn(part);
        when(modelMapper.map(part, PartServiceModel.class)).thenReturn(partServiceModel);
    }

    @Test
    public void savePart_withCorrectData_worksFine() {
        final PartServiceModel expected = partService.savePart(partServiceModel);

        verify(partRepository).saveAndFlush(part);

        assertEquals(expected, partServiceModel);
    }

    @Test
    public void editPart_withCorrectData_worksFine() {
        PartServiceModel partServiceModel = new PartServiceModel() {{
            setId("id");
            setName("Wheel");
            setPrice(new BigDecimal("110.00"));
            setSupplier(new SupplierServiceModel() {{
                setName("Pesho");
                setImporter(true);
            }});
        }};

        final Part part = new Part() {{
            setId("id");
            setName("Wheel");
            setPrice(new BigDecimal("110.00"));
            setSupplier(new Supplier() {{
                setName("Pesho");
                setImporter(true);
            }});
        }};

        when(partRepository.findById(partServiceModel.getId())).thenReturn(Optional.ofNullable(this.part));
        when(partRepository.saveAndFlush(any(Part.class))).thenReturn(part);
        when(modelMapper.map(part, PartServiceModel.class)).thenReturn(partServiceModel);

        final PartServiceModel expected = partService.editPart(partServiceModel);

        verify(partRepository).saveAndFlush(any(Part.class));

        assertEquals(expected, partServiceModel);
    }

    @Test
    public void deletePart_withCorrectId_worksFine() {
        when(partRepository.findById("id")).thenReturn(Optional.ofNullable(part));

        final PartServiceModel expected = partService.deletePart("id");

        verify(partRepository).delete(any(Part.class));

        assertEquals(expected, partServiceModel);
    }

    @Test
    public void findById_withCorrectId_worksFine() {
        when(partRepository.findById("id")).thenReturn(Optional.ofNullable(part));

        final PartServiceModel expected = partService.findPartById("id");
        verify(partRepository).findById(anyString());

        assertEquals(expected, partServiceModel);
    }
}