package Repository;

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
        attrs.add(attr);
    }

    @Override
    public void removeAttr(Attr attr) {

    }

    @Override
    public void updateAttr(Attr attr) {

    }

    @Override
    public List query(AttrSpecification specification) {
        List<Attr> specificAttrs = new ArrayList<>();
        String sql = "SELECT * FROM attr WHERE " + specification.toSqlClauses();
        DBConnection.selectAttrs(sql,specificAttrs);
        return specificAttrs;
    }
}
