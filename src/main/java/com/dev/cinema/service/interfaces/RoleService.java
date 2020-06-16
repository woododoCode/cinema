package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.Role;

public interface RoleService {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
