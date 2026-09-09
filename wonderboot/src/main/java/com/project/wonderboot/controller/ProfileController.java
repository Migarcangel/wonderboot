package com.project.wonderboot.controller;

import com.project.wonderboot.models.Country;
import com.project.wonderboot.models.dto.RegisteredUserDTO;
import com.project.wonderboot.models.dto.RegisteredUserDTOAdapter;
import com.project.wonderboot.models.dto.StatisticsDTO;
import com.project.wonderboot.models.user.Profile;
import com.project.wonderboot.models.user.WonderUser;
import com.project.wonderboot.repository.WonderUserRepository;
import com.project.wonderboot.service.CountryService;
import com.project.wonderboot.service.StatisticsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private final WonderUserRepository wonderUserRepository;
    private final StatisticsService statisticsService;
    private final RegisteredUserDTOAdapter registeredUserDTOAdapter;
    private final CountryService countryService;

    ProfileController(WonderUserRepository wonderUserRepository, StatisticsService statisticsService,
                      RegisteredUserDTOAdapter registeredUserDTOAdapter, CountryService countryService) {
        this.wonderUserRepository = wonderUserRepository;
        this.statisticsService = statisticsService;
        this.registeredUserDTOAdapter = registeredUserDTOAdapter;
        this.countryService = countryService;
    }

    @GetMapping("/profile")
    String viewProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        Profile profile = wonderUserRepository.findByProfileUsername(username).orElseThrow().getProfile();

        StatisticsDTO statistics = statisticsService.getStatistics(profile);

        model.addAttribute("profile", profile);
        model.addAttribute("statistics", statistics);

        return "profile";
    }

    @GetMapping("/profile/edit")
    String viewFormProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        Profile profile = wonderUserRepository.findByProfileUsername(username).orElseThrow().getProfile();
        Country[] countries = countryService.getCountries();
        /*List<String> countries = List.of(
                "España",
                "Portugal",
                "Francia",
                "Italia",
                "Alemania"
        );*/

        model.addAttribute("countries", countries);
        model.addAttribute("profile",profile);
        return "editProfile";
    }

    @PostMapping("/profile/edit")
    String editProfile(@ModelAttribute RegisteredUserDTO userDTO, Authentication authentication,
                       HttpServletRequest request, HttpServletResponse response) {

        String username = authentication.getName();

        WonderUser wonderUser = wonderUserRepository
                .findByProfileUsername(username)
                .orElseThrow();

        Optional<WonderUser> userWithUsername =
                wonderUserRepository.findByProfileUsername(userDTO.getUsername());

        if (userWithUsername.isPresent()
                && !userWithUsername.get().getProfile().getUsername().equals(username)) {
            return "redirect:/profile/edit?error=username";
        }

        registeredUserDTOAdapter.updateWonderUser(wonderUser, userDTO);
        wonderUserRepository.save(wonderUser);

        new SecurityContextLogoutHandler()
                .logout(request, response, authentication);

        return "redirect:/login";
    }
}
