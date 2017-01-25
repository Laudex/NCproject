package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.Attr;
import Interfaces.Repository.IAttrRepository;
import Interfaces.Specification.Specification;

import java.util.ArrayList;
import java.util.List;


public class AttrRepository implements IAttrRepository {


    public void addAttr(Attr attr) {
        String sqlQuery = String.format("INSERT INTO attr (attr_id, name) VALUES (%s,\'%s\');", attr.getAttrId(),attr.getName());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }


    public void removeAttr(Attr attr) {
        String sqlQuery = String.format("DELETE FROM attr WHERE attr_id = %s;",attr.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }


    public void updateAttr(Attr attr) {
        String sqlQuery = String.format("UPDATE attr SET name = \'%s\' WHERE attr_id = %s;",attr.getName(),attr.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }


    public List query(Specification specification) {
        List<Attr> specificAttrs = new ArrayList<Attr>();
        String sql = "SELECT * FROM attr " + specification.toSqlClauses();
        DBConnection.selectAttrs(sql,specificAttrs);
        return specificAttrs;
    }
}
