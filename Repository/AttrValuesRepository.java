package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.AttrValues;
import Interfaces.Repository.IAttrValuesRepository;
import Interfaces.Specification.AttrValuesSpecification;

import java.util.ArrayList;
import java.util.List;


public class AttrValuesRepository implements IAttrValuesRepository {
    private List<AttrValues> attrsValues = new ArrayList<>();
    @Override
    public void addAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("INSERT INTO attr_values (offer_id, attr_id, value) VALUES (%s,%s,\'%s\');", attrValues.getOfferId(),attrValues.getAttrId(),attrValues.getValue());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void removeAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("DELETE FROM attr_values WHERE attr_id = %s AND offer_id = %s;",attrValues.getAttrId(),attrValues.getOfferId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public void updateAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("UPDATE attr_values SET value = \'%s\' WHERE offer_id = %s AND attr_id = %s;",attrValues.getValue(),attrValues.getOfferId(),attrValues.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    @Override
    public List query(AttrValuesSpecification specification) {
        List<AttrValues> specificAttrsValues = new ArrayList<>();
        String sql = "SELECT * FROM attr_values WHERE " + specification.toSqlClauses();
        DBConnection.selectAttrsValues(sql,specificAttrsValues);
        return specificAttrsValues;
    }
}
