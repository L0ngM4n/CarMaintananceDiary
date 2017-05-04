package com.car.areas.cars.controllers;

import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.car.areas.user.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 04/05/2017
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(SecurityContextHolder.class)
@WebMvcTest(CarController.class)
@ActiveProfiles("test")

public class CarControllerTest {
public static final long CAR_ID = 1;
    private static final String CAR_MAKE = "make";
    private static final String CARS_ID_URL = "/cars/1";
    public static final String CAR_REPAIRS_FRAGMENT = "/fragments/car-repairs";
    private static final long USER_ID = 1;
    public static final Set<CarViewModel> USER_CARS = new HashSet<>();

    @Autowired
    private MockMvc mvc;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @MockBean
    private CarService carService;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        //Arrange
        CarViewModel carViewModel = new CarViewModel();
        carViewModel.setId(CAR_ID);
        carViewModel.setMake(CAR_MAKE);
        USER_CARS.add(carViewModel);
        when(carService.getById(CAR_ID)).thenReturn(carViewModel);
        when(user.getId()).thenReturn(USER_ID);
        when(this.securityContext.getAuthentication()).thenReturn(authentication);
        when(this.securityContext.getAuthentication().getPrincipal()).thenReturn(user);
        when(this.carService.getAllCarsByUser(USER_ID)).thenReturn(USER_CARS);
    }

    @Test
    public void userCars() throws Exception {
        assertEquals(1, USER_CARS.size());

    }

    @Test
    public void getCarById() throws Exception {
        //Act
        this.mvc.perform(get(CARS_ID_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("base-layout"))
                .andExpect(model().attribute("car", hasProperty("id", is(CAR_ID))))
                .andExpect(model().attribute("car", hasProperty("make", is(CAR_MAKE))))
                .andExpect(model().attribute("view", CAR_REPAIRS_FRAGMENT));
    }

}