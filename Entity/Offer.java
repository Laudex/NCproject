package Entity;


public class Offer {
    static private int k = 1;
    private int offer_id;
    private String name;

    public Offer() {
        this.offer_id = k;
        k++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasOfferName(String desiredOfferName){
        return this.name.equals(desiredOfferName);
    }
}
