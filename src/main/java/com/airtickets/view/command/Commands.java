package main.java.com.airtickets.view.command;

public enum Commands {
    Command("Enter command"),
    Login("Enter your login.For cancel enter \"cancel\""),
    Password("Enter password from more 8 symbols. For cancel enter \"cancel\""),
    Name("Enter name. Name mustn't have numerals. For cancel enter \"cancel\""),
    LastName("Enter Last name. Last name mustn't have numerals. For cancel enter \"cancel\""),
    RouteName("Enter route name. Example \"Moscow - Kiev\". For cancel enter \"cancel\""),
    SeatsEconomy("Enter numbers economy seats. For cancel enter \"cancel\""),
    SeatsBusiness("Enter numbers business seats. For cancel enter \"cancel\""),
    SeatsEconomyPrice("Enter price on economy seats. For cancel enter \"cancel\""),
    SeatsBusinessPrice("Enter price on business seats. For cancel enter \"cancel\""),
    Date("Enter date. Example \"01/01/2018\". For cancel enter \"cancel\""),
    SearchType("Enter search type to find (route or date). For cancel enter \"cancel\""),
    AddMoney("Enter count of money. For cancel enter \"cancel\"");
    private String string;

    Commands(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
