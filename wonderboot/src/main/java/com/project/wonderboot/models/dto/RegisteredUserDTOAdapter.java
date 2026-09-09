package com.project.wonderboot.models.dto;

import com.project.wonderboot.models.user.Profile;
import com.project.wonderboot.models.user.WonderUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserDTOAdapter {

    private final PasswordEncoder passwordEncoder;

    public RegisteredUserDTOAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public WonderUser createWonderUser(RegisteredUserDTO registeredUserDTO) {
        WonderUser wonderUser = new WonderUser();
        wonderUser.setAdmin(false);
        wonderUser.setPassword(passwordEncoder.encode(registeredUserDTO.getPassword()));
        wonderUser.setProfile(createProfile(registeredUserDTO));
        return wonderUser;
    }
    
    private Profile createProfile(RegisteredUserDTO registeredUserDTO) {
        return new Profile(registeredUserDTO.getUsername(),registeredUserDTO.getName(),registeredUserDTO.getSurname(),
                registeredUserDTO.getCountry(),registeredUserDTO.getEmail(),registeredUserDTO.getBirthDate());
    }

    public void updateWonderUser(WonderUser wonderUser, RegisteredUserDTO dto) {
        Profile profile = wonderUser.getProfile();

        profile.setUsername(dto.getUsername());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setCountry(dto.getCountry());
        profile.setEmail(dto.getEmail());
        profile.setBirthDate(dto.getBirthDate());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            wonderUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }
}
