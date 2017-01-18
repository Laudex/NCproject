package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.Attr;
import Interfaces.Repository.IAttrRepository;
import Interfaces.Specification.AttrSpecification;
import java.util.ArrayList;
import java.util.List;


public class AttrRepository implements IAttrRepository {
    private List<Attr> attrs = new ArrayList<>();
    @Override
    public void addAttr(Attr attr) {
        String sqlQuery = String.format("INSERT INTO attr (attr_id, name) VALUES (%s,\'%s\');", attr.getAttrId(),attr.getName());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void removeAttr(Attr attr) {
        String sqlQuery = String.format("DELETE FROM attr WHERE attr_id = %s;",attr.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void updateAttr(Attr attr) {
        String sqlQuery = String.format("UPDATE attr SET name = \'%s\' WHERE attr_id = %s;",attr.getName(),attr.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public List query(AttrSpecification specification) {
        List<Attr> specificAttrs = new ArrayList<>();
        String sql = "SELECT * FROM attr WHERE " + specification.toSqlClauses();
        DBConnection.selectAttrs(sql,specificAttrs);
        return specificAttrs;
    }
}
