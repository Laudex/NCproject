package ru.repository;

import ru.dbclasses.DBChanger;
import ru.dbclasses.DBConnection;
import ru.entity.AttrValues;
import ru.interfaces.repository.IAttrValuesRepository;
import ru.interfaces.specification.Specification;

import java.util.ArrayList;
import java.util.List;


public class AttrValuesRepository implements IAttrValuesRepository {
    public void addAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("INSERT INTO attr_values (offer_id, attr_id, value) VALUES (%s,%s,\'%s\');", attrValues.getOfferId(),attrValues.getAttrId(),attrValues.getValue());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }


    public void removeAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("DELETE FROM attr_values WHERE attr_id = %s AND offer_id = %s;",attrValues.getAttrId(),attrValues.getOfferId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    public void updateAttrValues(AttrValues attrValues) {
        String sqlQuery = String.format("UPDATE attr_values SET value = \'%s\' WHERE offer_id = %s AND attr_id = %s;",attrValues.getValue(),attrValues.getOfferId(),attrValues.getAttrId());
        System.out.println(sqlQuery);
        DBChanger.changeEntity(sqlQuery);
    }

    public List query(Specification specification) {
        List<AttrValues> specificAttrsValues = new ArrayList<AttrValues>();
        String sql = "SELECT * FROM attr_values " + specification.toSqlClauses();
        DBConnection.selectAttrsValues(sql,specificAttrsValues);
        return specificAttrsValues;
    }
}
