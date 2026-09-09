package com.project.wonderboot.models.dto;

import java.math.BigDecimal;

public class StatisticsDTO {

    private int citiesVisited;
    private int daysTravelled;
    private BigDecimal moneySpent;
    private BigDecimal averageMoneyPerTrip;
    private BigDecimal averageMoneyPerYear;

    public int getCitiesVisited() {
        return citiesVisited;
    }

    public void setCitiesVisited(int citiesVisited) {
        this.citiesVisited = citiesVisited;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getDaysTravelled() {
        return daysTravelled;
    }

    public void setDaysTravelled(int daysTravelled) {
        this.daysTravelled = daysTravelled;
    }

    public BigDecimal getAverageMoneyPerYear() {
        return averageMoneyPerYear;
    }

    public void setAverageMoneyPerYear(BigDecimal averageMoneyPerYear) {
        this.averageMoneyPerYear = averageMoneyPerYear;
    }

    public BigDecimal getAverageMoneyPerTrip() {
        return averageMoneyPerTrip;
    }

    public void setAverageMoneyPerTrip(BigDecimal averageMoneyPerTrip) {
        this.averageMoneyPerTrip = averageMoneyPerTrip;
    }
}
