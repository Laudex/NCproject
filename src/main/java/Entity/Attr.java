package Entity;


public class Attr {
    private int attrId;
    private String name;

    public Attr() {

    }

    public Attr(int attrId, String name) {
        this.attrId = attrId;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public int getAttrId() {
        return attrId;
    }

    public String getName() {
        return name;
    }


}
