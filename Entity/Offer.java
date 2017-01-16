package Entity;


public class Offer {
    static private int k = 1;
    private int offerId;
    private String name;

    public Offer() {
        this.offerId = k;
        k++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffer_id(int offerId) {
        this.offerId = offerId;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getName() {
        return name;
    }

    public boolean hasOfferName(String desiredOfferName){
        return this.name.equals(desiredOfferName);
    }
}
