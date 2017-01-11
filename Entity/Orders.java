package Entity;


public class Orders {
    static private int k = 1;
    private int orderId;
    private int userId;
    private int offerId;
    private String startDate;

    public Orders(){
        this.orderId = k;
        k++;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean hasUserId(int desiredUserId){
        return this.userId == desiredUserId;
    }
    public boolean hasOfferId(int desiredOfferId){
        return this.offerId == desiredOfferId;
    }
    public boolean hasStartDate(String desiredStartDate){
        return this.startDate.equals(desiredStartDate);
    }

}
