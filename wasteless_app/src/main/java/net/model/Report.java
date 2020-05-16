package net.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Report {
    public List<Item> getItems(List<Item> itemList, int numberOfDays) {
        int days;
        List<Item> itemsPurchasedInTheGivenTimePeriod = new ArrayList<>();
        Date today = new Date();

        for (Item item : itemList) {

            if (item.getConsumptionDate() != null) {
                days = (int) Duration.between(item.getConsumptionDate().toInstant(), today.toInstant()).toDays();

            } else {
                days = (int) Duration.between(item.getExpirationDate().toInstant(), today.toInstant()).toDays();
            }

            if (days <= numberOfDays) {
                itemsPurchasedInTheGivenTimePeriod.add(item);
            }
        }

        return itemsPurchasedInTheGivenTimePeriod;
    }

    public abstract CreatedReport sendReport(List<Item> itemList);

    public CreatedReport createdReport(List<Item> itemList) {
        Date date = new Date();
        List<Item> eatenFood = new ArrayList<>();
        List<Item> expiredFood = new ArrayList<>();
        int wastedCalories = 0;
        int eatenCalories = 0;

        for (Item item : itemList) {
            if (item.getConsumptionDate() != null) { //consumed

                eatenFood.add(item);
                eatenCalories += item.getCalories() * item.getQuantity();

            } else if (item.getExpirationDate().before(date)) { //expired

                expiredFood.add(item);
                wastedCalories += item.getCalories() * item.getQuantity();
            }
        }

        CreatedReport createdReport = new CreatedReport(eatenFood, eatenCalories, expiredFood, wastedCalories);
        return createdReport;
    }
}
