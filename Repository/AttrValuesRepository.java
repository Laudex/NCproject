package Repository;

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
        attrsValues.add(attrValues);
    }

    @Override
    public void removeAttrValues(AttrValues attrValues) {

    }

    @Override
    public void updateAttrValues(AttrValues attrValues) {

    }

    @Override
    public List query(AttrValuesSpecification specification) {
        List<AttrValues> specificAttrsValues = new ArrayList<>();
        String sql = "SELECT * FROM attr_values WHERE " + specification.toSqlClauses();
        DBConnection.selectAttrsValues(sql,specificAttrsValues);
        return specificAttrsValues;
    }
}
