package ru.entity;


public class AttrValues {
    private int offerId;
    private int attrId;
    private String value;

    public AttrValues() {
    }

    public AttrValues(int offerId, int attrId, String value) {
        this.offerId = offerId;
        this.attrId = attrId;
        this.value = value;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getAttrId() {
        return attrId;
    }

    public String getValue() {
        return value;
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
