package com.project.wonderboot.controller;

import com.project.wonderboot.models.Country;
import com.project.wonderboot.models.dto.RegisteredUserDTO;
import com.project.wonderboot.models.dto.RegisteredUserDTOAdapter;
import com.project.wonderboot.repository.WonderUserRepository;
import com.project.wonderboot.models.user.WonderUser;
import com.project.wonderboot.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegisterController {

    private final RegisteredUserDTOAdapter registeredUserDTOAdapter;
    private final WonderUserRepository wonderUserRepository;
    private final CountryService countryService;

    public RegisterController(RegisteredUserDTOAdapter registeredUserDTOAdapter, WonderUserRepository wonderUserRepository, CountryService countryService) {
        this.registeredUserDTOAdapter = registeredUserDTOAdapter;
        this.wonderUserRepository = wonderUserRepository;
        this.countryService = countryService;
    }

    @GetMapping("/register")
    String register(Model model) {
        Country[] countries = countryService.getCountries();
        /*List<String> countries = List.of(
                "España",
                "Portugal",
                "Francia",
                "Italia",
                "Alemania"
        );*/
        model.addAttribute("countries", countries);
        return "register";
    }

    @PostMapping("/register")
    String createUser(@ModelAttribute RegisteredUserDTO userDTO) {
        if (wonderUserRepository.findByProfileUsername(userDTO.getUsername()).isPresent()) {
            return "redirect:/register?error=username";
        }
        WonderUser wonderUser = registeredUserDTOAdapter.createWonderUser(userDTO);
        wonderUserRepository.save(wonderUser);
        return "redirect:/login";
    }
}
