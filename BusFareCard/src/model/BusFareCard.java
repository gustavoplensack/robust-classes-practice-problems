package model;

import exceptions.IllegalAgeException;
import exceptions.IllegalAmountException;
import exceptions.MissingFareException;
import exceptions.NoBalanceException;

public class BusFareCard {

    private static final int AGE_CUTOFF = 18;
    public static final double ADULT_FARE = 2.75;
    public static final double CONCESSION_FARE = 1.75;
    private String ownerName;
    private int ownerAge;
    private double balance;
    private boolean fareLoaded;

    public BusFareCard(String nm, int age, double initialLoad) throws IllegalAmountException {
        if (initialLoad < 0) {
            throw new IllegalAmountException("$" + initialLoad + " is not a valid amount to load onto your card.");
        } else {
            ownerName = nm;
            ownerAge = age;
            balance = initialLoad;
            fareLoaded = false;
        }
    }

    // getters
    public String getName() { return ownerName; }
    public int getAge() { return ownerAge; }
    public double getBalance() { return balance; }
    public boolean isFareLoaded() { return fareLoaded; }

    public boolean isAdult(){
        return ownerAge > AGE_CUTOFF;
    }

    // MODIFIES: this
    // EFFECTS: if the age of this card < AGE_CUTOFF throws IllegalAgeException
    //          if balance < 0 throws NoBalanceException 
    //          otherwise subtract cost of the adult fare from the balance
    //          and set the fareLoaded field to true
    public void purchaseAdultFare() throws IllegalAgeException, NoBalanceException {
        if (!isAdult()){
            throw new IllegalAgeException("User is not an adult and is elligible for concession fare!");
        }

        if(balance<ADULT_FARE){
            throw new NoBalanceException("Not enough money to pay the fare");
        }

        balance -= ADULT_FARE;

        fareLoaded = true;

    }

    // MODIFIES: this
    // EFFECTS: if the age of this card is >  AGE_CUTOFF throws IllegalAgeException
    //          if the the balance on this card is < 0 throws NoBalanceException
    //          otherwise subtract cost of a concession fare from the balance
    //          and set the fareLoaded field to true
    public void purchaseConcessionTicket() throws IllegalAgeException, NoBalanceException {
        if (isAdult()){
            throw new IllegalAgeException("User is an adult and is not elligible for concession fare!");
        }

        if(balance<CONCESSION_FARE){
            throw new NoBalanceException("Not enough money to pay the fare");
        }

        balance -= CONCESSION_FARE;

        fareLoaded = true;

    }

    // MODIFIES: this
    // EFFECTS: if amount < 0, throws IllegalAmountException
    //          otherwise: loads the specified amount onto the card's balance field
    public void reloadBalance(double amount) throws IllegalAmountException {
        if(amount<=0){
            throw new IllegalAmountException("You must recharge at least $0.01");
        }

        balance += amount;

    }

    // MODIFIES: this
    // EFFECTS: if the fare is not loaded, throws MissingFareException
    //          otherwise, sets fareLoaded to false
    public void boardBus() throws MissingFareException {
        if (!fareLoaded){
            throw new MissingFareException();
        }

        fareLoaded = false;
    }


}
