package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class Item {
    private String name;
    private int price;

    // constructor
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // accessors
    public String getName() { return name; }
    public int getPrice() { return price; }

    // mutators
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
}

public class CheckoutSteps {

    private Checkout checkout = new Checkout();
    private ArrayList<Item> itemList = new ArrayList<Item>();

    @Given("^the (?:cost|price) of an? \"(.*?)\" is (\\d+)c$")
    public void thePriceOfAIsC(String itemName, int price) throws Throwable {
        Item currentItem = new Item(itemName, price);
        itemList.add(currentItem);//this does put in duplicates, if there are any
    }

    @When("^I checkout (\\d+) \"(.*?)\"$")
    public void iCheckout(int itemCount, String itemName) throws Throwable {
        for(Item item : itemList){
            if(item.getName().equals(itemName)) {
                checkout.add(itemCount, item.getPrice());
            }
        }
    }

    @Then("^the total price should be (\\d+)c$")
    public void theTotalPriceShouldBeC(int total) throws Throwable {
        assertEquals(total, checkout.total());
    }
}
