package Entity;


public class Attr {
    static private int k = 1;
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

    public boolean hasAttrName(String desiredAttrName){
        return this.name.equals(desiredAttrName);
    }
}
