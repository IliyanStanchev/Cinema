package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Role;
import bg.tuvarna.springboot.Cinema.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getByRoleName(String roleName) {
        return roleRepository.getByRoleName(roleName);
    }
}
