package com.car.areas.user.models.viewModels;

import com.car.areas.user.entities.Role;

import java.util.Set;

/**
 * 11/04/2017
 */
public class UserViewModel {

    private long id;

    private String username;

    private boolean isEnabled;

    private Set<Role> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
