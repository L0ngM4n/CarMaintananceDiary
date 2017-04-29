package com.car.areas.user.servicesImpl;

import com.car.areas.user.entities.SocialUser;
import com.car.areas.user.repositories.SocialUserRepository;
import com.car.areas.user.services.SocialUserService;
import com.car.areas.user.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 03/04/2017
 */
@Service
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RoleService roleService;


    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);

        if (socialUser == null) {
            socialUser = this.registerUser(email);
        }

        this.loginUser(socialUser);
    }

    private SocialUser registerUser(String email) {
        SocialUser user = new SocialUser();
        user.setProvider("FACEBOOK");
        user.setUsername(email);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCreationDate(new Date());
        user.addRole(this.roleService.getDefaultRole());
        user  = this.socialUserRepository.save(user);

        return user;
    }

    private void loginUser(SocialUser socialUser) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser.getId(),null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


    }
}

























