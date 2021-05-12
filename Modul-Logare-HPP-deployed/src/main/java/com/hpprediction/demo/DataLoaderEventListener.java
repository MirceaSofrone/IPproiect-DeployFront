package com.hpprediction.demo;

import com.hpprediction.demo.datamodels.UserRoleEnum;
import com.hpprediction.demo.entity.Role;
import com.hpprediction.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class DataLoaderEventListener {
    private RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void startApp() {
        System.out.println("App started");

        loadRoleData();
    }

    private void loadRoleData() {
        List<Role> currentRoles = roleRepository.getAll();
        Role roleUser = new Role(1, UserRoleEnum.ROLE_USER);
        Role roleMod = new Role(2, UserRoleEnum.ROLE_MODERATOR);
        Role roleAdmin = new Role(3, UserRoleEnum.ROLE_ADMIN);

        if(!currentRoles.contains(roleUser)){
            roleRepository.save(roleUser);
        }
        if(!currentRoles.contains(roleMod)){
            roleRepository.save(roleMod);
        }
        if(!currentRoles.contains(roleAdmin)){
            roleRepository.save(roleAdmin);
        }
    }

}
