package Entity;


public class Attr {
    static private int k = 1;
    private int attrId;
    private String name;

    public Attr() {
        this.attrId = k;
        k++;
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
