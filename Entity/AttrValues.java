package Entity;


public class AttrValues {
    private int offerId;
    private int attrId;
    private String value;

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public boolean hasAttrId(int desiredAttrId){
        return this.attrId == desiredAttrId;
    }
    public boolean hasOfferId(int desiredOfferId){
        return this.offerId == desiredOfferId;
    }
    public boolean hasAttrValue(String desiredAttrValue){
        return this.value.equals(desiredAttrValue);
    }
}
