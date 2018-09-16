package main.java.com.airtickets.view.command;

public enum Commands {
    COMMAND("Enter command:"),
    LOGIN("Enter your login.For cancel enter \"cancel\""),
    PASSWORD("Enter password from more 8 symbols. For cancel enter \"cancel\""),
    NAME("Enter name. NAME mustn't have numerals. For cancel enter \"cancel\""),
    LAST_NAME("Enter Last name. Last name mustn't have numerals. For cancel enter \"cancel\""),
    CITY("Enter city. For cancel enter \"cancel\""),
    ROUTE("Enter route. For cancel enter \"cancel\""),
    DEPARTURE_CITY("Enter departure city. For cancel enter \"cancel\""),
    ARRIVAL_CITY("Enter arraival city. For cancel enter \"cancel\""),
    SEATS_ECONOMY("Enter numbers economy seats. For cancel enter \"cancel\""),
    SEATS_BUSINESS("Enter numbers business seats. For cancel enter \"cancel\""),
    SEATS_ECONOMY_PRICE("Enter price on economy seats. For cancel enter \"cancel\""),
    SEATS_BUSINESS_PRICE("Enter price on business seats. For cancel enter \"cancel\""),
    DATE("Enter date. Example \"01/01/2018\". For cancel enter \"cancel\""),
    SEARCH_TYPE("Enter search type to find (route or date). For cancel enter \"cancel\""),
    TYPE_TICKET("Enter type of ticket (economy or business). For cancel enter \"cancel\""),
    ADD_MONEY("Enter count of money. For cancel enter \"cancel\"");


    private String string;

    Commands(String string){
        this.string = string;
    }

    public String getString() {
        return "\u001B[37m" + string;
    }
}
