package com.car.areas.user.servicesImpl;

import com.car.areas.user.entities.Role;
import com.car.areas.user.entities.SocialUser;
import com.car.areas.user.repositories.SocialUserRepository;
import com.car.areas.user.services.RoleService;
import com.car.areas.user.services.SocialUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.social.facebook.api.User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

/**
 * 03/05/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SocialUserServiceImplTest {

    public static final String EXPECTED_EMAIL = "expected@email.com";

    @Mock
    private User fbUser;

    @MockBean
    private SocialUserRepository socialUserRepository;

    @MockBean
    private RoleService roleService;

    @Autowired
    private SocialUserService socialUserService;

    @Captor
    private ArgumentCaptor<SocialUser> captor;

    @Before
    public void setUp() throws Exception {

        //Arrange
        Role role = new Role();
        role.setAuthority("ROLE_USER");

        when(this.roleService.getDefaultRole()).thenReturn(role);
        when(this.fbUser.getEmail()).thenReturn(EXPECTED_EMAIL);
        when(this.socialUserRepository.findOneByUsername(EXPECTED_EMAIL)).thenReturn(null);
    }

    @Test
    public void registerOrLogin() throws Exception {
        //Act
        this.socialUserService.registerOrLogin(this.fbUser);

        //Assert database call
        verify(this.socialUserRepository, times(1)).save(captor.capture());
    }

}