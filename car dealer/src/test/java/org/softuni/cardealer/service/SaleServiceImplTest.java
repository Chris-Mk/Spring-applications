package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.*;
import org.softuni.cardealer.domain.models.service.*;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartSaleRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceImplTest {

    @Mock
    private CarSaleRepository carSaleRepository;

    @Mock
    private PartSaleRepository partSaleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SaleServiceImpl saleService;

    private CarSaleServiceModel carSaleServiceModel;
    private CarSale carSale;

    private PartSaleServiceModel partSaleServiceModel;
    private PartSale partSale;

    @Before
    public void setUp() {
        carSaleServiceModel = new CarSaleServiceModel() {{
            setCar(new CarServiceModel() {{
                setId("id");
                setMake("Audi");
                setModel("Q4");
                setTravelledDistance(200000L);
                setParts(List.of(new PartServiceModel() {{
                    setName("Wheel");
                    setPrice(new BigDecimal("100.00"));
                    setSupplier(new SupplierServiceModel() {{
                        setName("Pesho");
                        setImporter(true);
                    }});
                }}, new PartServiceModel() {{
                    setName("Mirror");
                    setPrice(new BigDecimal("320.00"));
                    setSupplier(new SupplierServiceModel() {{
                        setName("Gosho");
                        setImporter(false);
                    }});
                }}));
            }});
        }};

        carSale = new CarSale() {{
            setCar(new Car() {{
                setMake("Audi");
                setModel("Q4");
                setTravelledDistance(200000L);
                setParts(List.of(new Part() {{
                    setName("Wheel");
                    setPrice(new BigDecimal("100.00"));
                    setSupplier(new Supplier() {{
                        setName("Pesho");
                        setImporter(true);
                    }});
                }}, new Part() {{
                    setName("Mirror");
                    setPrice(new BigDecimal("320.00"));
                    setSupplier(new Supplier() {{
                        setName("Gosho");
                        setImporter(false);
                    }});
                }}));
            }});
        }};

        partSaleServiceModel = new PartSaleServiceModel() {{
                setQuantity(20);
                setPart(new PartServiceModel() {{
                    setName("Wheel");
                    setPrice(new BigDecimal("100.00"));
                    setSupplier(new SupplierServiceModel() {{
                        setName("Pesho");
                        setImporter(true);
                    }});
                }});
            }};

        partSale = new PartSale() {{
            setQuantity(20);
            setPart(new Part() {{
                setName("Wheel");
                setPrice(new BigDecimal("100.00"));
                setSupplier(new Supplier() {{
                    setName("Pesho");
                    setImporter(true);
                }});
            }});
        }};

        when(modelMapper.map(carSaleServiceModel, CarSale.class)).thenReturn(carSale);
        when(modelMapper.map(carSale, CarSaleServiceModel.class)).thenReturn(carSaleServiceModel);
        when(modelMapper.map(partSaleServiceModel, PartSale.class)).thenReturn(partSale);
        when(modelMapper.map(partSale, PartSaleServiceModel.class)).thenReturn(partSaleServiceModel);
    }

    @Test
    public void saleCar_withCorrectData_worksFine() {
        when(carSaleRepository.saveAndFlush(carSale)).thenReturn(carSale);

        final CarSaleServiceModel expected = saleService.saleCar(this.carSaleServiceModel);

        assertEquals(expected, carSaleServiceModel);
    }

    @Test
    public void salePart_withCorrectData_worksFine() {
        when(partSaleRepository.saveAndFlush(partSale)).thenReturn(partSale);

        final PartSaleServiceModel expected = saleService.salePart(this.partSaleServiceModel);

        assertEquals(expected, partSaleServiceModel);
    }
}