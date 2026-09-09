package com.project.wonderboot.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TripResumeDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Integer citiesCount;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull
    @Min(1)
    private Integer totalDays;

    @NotNull
    @Min(0)
    private BigDecimal expenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCitiesCount() {
        return citiesCount;
    }

    public void setCitiesCount(Integer citiesCount) {
        this.citiesCount = citiesCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

