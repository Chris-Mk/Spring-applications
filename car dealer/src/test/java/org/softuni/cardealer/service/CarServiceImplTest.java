package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private CarServiceModel carServiceModel;

    private Car car;

    @Before
    public void setUp() {
        carServiceModel = new CarServiceModel() {{
            setMake("Audi");
            setModel("Q4");
            setTravelledDistance(20000L);
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
        }};

        car = new Car() {{
            setMake("Audi");
            setModel("Q4");
            setTravelledDistance(20000L);
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
        }};

        when(modelMapper.map(carServiceModel, Car.class)).thenReturn(car);
        when(modelMapper.map(car, CarServiceModel.class)).thenReturn(carServiceModel);
    }

    @Test
    public void saveCar_withCorrectData_worksFine() {
        when(carRepository.saveAndFlush(car)).thenReturn(car);

        final CarServiceModel expected = carService.saveCar(carServiceModel);

        verify(carRepository).saveAndFlush(car);

        assertEquals(expected, carServiceModel);
    }

    @Test
    public void editCar_withCorrectData_worksFine() {
        CarServiceModel carServiceModel = new CarServiceModel() {{
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
        }};

        Car car = new Car() {{
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
        }};

        when(carRepository.findById(carServiceModel.getId())).thenReturn(Optional.ofNullable(this.car));
        when(carRepository.saveAndFlush(any(Car.class))).thenReturn(car);
        when(modelMapper.map(car, CarServiceModel.class)).thenReturn(carServiceModel);

        final CarServiceModel expected = carService.editCar(carServiceModel);

        verify(carRepository).saveAndFlush(any(Car.class));

        assertEquals(expected, carServiceModel);
    }

    @Test
    public void deleteCar_withCorrectId_worksFine() {
        when(carRepository.findById("id")).thenReturn(Optional.ofNullable(car));

        final CarServiceModel expected = carService.deleteCar("id");

        verify(carRepository).delete(car);

        assertEquals(expected, carServiceModel);
    }

    @Test
    public void findCarById_withCorrectId_worksFine() {
        when(carRepository.findById("id")).thenReturn(Optional.ofNullable(car));

        final CarServiceModel expected = carService.findCarById("id");

        assertEquals(expected, carServiceModel);
    }
}