package com.car.areas.cars.servicesImpl;

import com.car.areas.cars.entities.Car;
import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.repositories.CarsRepository;
import com.car.areas.cars.services.CarService;
import com.car.exceptions.CarNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 03/05/2017
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class CarServiceImplTest {

    public static final long ID = 1;
    public static final long INVALID_ID = -1;
    public static final String MAKE = "make";
    public static final String MODEL = "model";
    public static final int YEAR = 2017;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private CarsRepository carsRepository;

    @Autowired
    private CarService carService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        Car car = new Car();
        car.setId(ID);
        car.setMake(MAKE);
        car.setModel(MODEL);
        car.setYear(YEAR);
        when(this.carsRepository.findOne(ID)).thenReturn(car);
    }

    @Test
    public void getByIdGivenCar_ShouldReturnValidViewModel() throws Exception {
        //Act
        CarViewModel carViewModel = this.carService.getById(ID);


        //Assert id
        assertEquals(ID, carViewModel.getId());

        //Assert make
        assertEquals(MAKE, carViewModel.getMake());
        //Assert model
        assertEquals(MODEL, carViewModel.getModel());
        //Assert year
        assertEquals(YEAR, carViewModel.getYear());
    }

    @Test
    public void getByIdGivenValidId_ShouldCallRepositoryFindOneById() throws Exception {
        this.carService.getById(ID);

        verify(this.carsRepository, times(1)).findOne(ID);
    }

    @Test(expected = CarNotFoundException.class)
    public void getByIdInvalidCar_ShouldThrowException() throws Exception {
        this.carService.getById(INVALID_ID);
    }
}