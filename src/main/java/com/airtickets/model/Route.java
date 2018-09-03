package main.java.com.airtickets.model;

public class Route extends NamedEntity {
    private Integer economy;
    private Integer business;
    private Double economyPrice;
    private Double businessPrice;
    public Route(Long id, String name, Integer economy, Integer business, Double economyPrice, Double businessPrice) {
        super(id, name);
        this.economy = economy;
        this.business = business;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
    }

    public Integer getEconomy() {
        return economy;
    }

    public Integer getBusiness() {
        return business;
    }

    public Double getEconomyPrice() {
        return economyPrice;
    }

    public Double getBusinessPrice() {
        return businessPrice;
    }
}
