package com.car.areas.cars.repositories;

import com.car.areas.cars.entities.CarModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 03/05/2017
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CarModelsRepositoryTest {
    public static final int EXPECTED_COUNT = 2;
    public static final int NUMBER_OF_ENTITIES = 4;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarModelsRepository carModelsRepository;

    @Before
    public void setUp() throws Exception {
        //Arrange
        for (int i = 0; i < NUMBER_OF_ENTITIES; i++) {
        CarModel carModel = new CarModel();
        carModel.setMake(i <= 1 ? "One" : "Two");
        carModel.setModel(i <= 1 ? "TestModel" : "TestModelTwo");
        carModel.setYear(2017);

        testEntityManager.persist(carModel);

        }
    }

    @Test
    public void getMakersAndCheckForUniquenessOfMake() throws Exception {
        //Act
        Set<String> makers = this.carModelsRepository.getMakers();

        //Assert
        assertEquals(EXPECTED_COUNT, makers.size());

    }

}