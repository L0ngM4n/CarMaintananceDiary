package com.car.areas.user.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * 03/05/2017
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserTest {

    public static final String EXPECTED_ROLE_NAME = "ROLE_USER";

    private User user;

    @Mock
    private Role role;

    @Before
    public void setUp() throws Exception {
        //Arange
        this.user = new BasicUser();
        when(this.role.getAuthority()).thenReturn(EXPECTED_ROLE_NAME);


    }

    @Test
    public void addRoleWhenRoleGiven_ShouldReturnCorrectRoleName() throws Exception {
        //Act
        this.user.addRole(this.role);
        //Assert
        String actualRoleName = this.user
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();


        assertEquals(EXPECTED_ROLE_NAME, actualRoleName);
    }

    @Test
    public void addGarage() throws Exception {
    }

}