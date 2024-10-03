package Four;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Observer interface for bidders
interface Observer {
    void update(String message);
}

// Observable class representing auction events
class AuctionEvent {
    private List<Observer> observers = new ArrayList<>();

    // Register a new bidder (observer)
    public void registerBidder(Observer bidder) {
        observers.add(bidder);
    }

    // Notify all registered bidders
    public void notifyBidders(String message) {
        for (Observer bidder : observers) {
            bidder.update(message);
        }
    }
}

// Abstract class Auction (Template Method Pattern)
abstract class Auction1 {
    protected AuctionEvent auctionEvent;
    protected String item;
    protected double currentBid;

    public Auction1(String item) {
        this.item = item;
        this.auctionEvent = new AuctionEvent();
    }

    // Template method defining the auction flow
    public final void startAuction() {
        auctionEvent.notifyBidders("Auction started for: " + item);
        onAuctionStart();
        processBidding();
        auctionEvent.notifyBidders("Auction ended for: " + item);
        onAuctionEnd();
    }

    // Hook method to customize auction start behavior
    protected void onAuctionStart() {}

    // Template method for handling bids
    protected void processBidding() {
        auctionEvent.notifyBidders("Processing bids...");
    }

    // Hook method to customize auction end behavior
    protected void onAuctionEnd() {}

    // Bid method to place bids
    public abstract void placeBid(double bidAmount, Observer bidder);

    // Register a bidder for auction updates
    public void registerBidder(Observer bidder) {
        auctionEvent.registerBidder(bidder);
    }
}

// Concrete class for Standard Auction (Template specialization)
class StandardAuction extends Auction1 {

    public StandardAuction(String item) {
        super(item);
        this.currentBid = 0.0;
    }

    @Override
    public void placeBid(double bidAmount, Observer bidder) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            auctionEvent.notifyBidders("New highest bid: $" + currentBid);
        } else {
            bidder.update("Bid of $" + bidAmount + " rejected, current bid is higher.");
        }
    }

    @Override
    protected void onAuctionEnd() {
        auctionEvent.notifyBidders("Auction finished. Final price for " + item + " is $" + currentBid);
    }
}

// Concrete class for Reserve Price Auction (Template specialization)
class ReservePriceAuction extends Auction1 {
    private double reservePrice;

    public ReservePriceAuction(String item, double reservePrice) {
        super(item);
        this.reservePrice = reservePrice;
        this.currentBid = 0.0;
    }

    @Override
    public void placeBid(double bidAmount, Observer bidder) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            auctionEvent.notifyBidders("New highest bid: $" + currentBid);
        } else {
            bidder.update("Bid of $" + bidAmount + " rejected, current bid is higher.");
        }
    }

    @Override
    protected void onAuctionEnd() {
        if (currentBid >= reservePrice) {
            auctionEvent.notifyBidders("Auction successful. " + item + " sold for $" + currentBid);
        } else {
            auctionEvent.notifyBidders("Auction failed. Reserve price of $" + reservePrice + " not met.");
        }
    }
}

// Concrete class for Bidder (Observer)
class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Bidder " + name + " received update: " + message);
    }

    public void bid(Auction1 auction, double amount) {
        auction.placeBid(amount, this);
    }
}

// Main class to test the Auction system
public class Auction {
    public static void main(String[] args) {
        // Create bidders
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");

        // Create a standard auction
        Auction1 standardAuction = new StandardAuction("Painting");
        standardAuction.registerBidder(bidder1);
        standardAuction.registerBidder(bidder2);

        // Start the auction and place bids
        standardAuction.startAuction();
        bidder1.bid(standardAuction, 100);
        bidder2.bid(standardAuction, 150);

        // Create a reserve price auction
        Auction1 reserveAuction = new ReservePriceAuction("Antique Vase", 200);
        reserveAuction.registerBidder(bidder1);
        reserveAuction.registerBidder(bidder2);

        // Start the auction and place bids
        reserveAuction.startAuction();
        bidder1.bid(reserveAuction, 180);
        bidder2.bid(reserveAuction, 220);
    }
}

