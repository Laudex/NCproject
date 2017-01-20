package Repository;

import DBclasses.DBConnectionFactory;
import Entity.AttrValues;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by ааааааааааеееееееее on 18.01.2017.
 */
public class AttrValuesRepositoryTest {
    @Test
    public void addAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"30 days");
        AttrValuesRepository rep = new AttrValuesRepository();
        rep.addAttrValues(attrValues);
        AttrValues testAttrValues = new AttrValues();
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttrValues.setOfferId(rs.getInt("offer_id"));
                testAttrValues.setAttrId(rs.getInt("attr_id"));
                testAttrValues.setValue(rs.getString("value"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(attrValues.getOfferId(),testAttrValues.getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.getValue());
    }

    @Test
    public void removeAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"30 days");
        AttrValuesRepository rep = new AttrValuesRepository();
        rep.removeAttrValues(attrValues);
        AttrValues testAttrValues = null;
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttrValues = new AttrValues();
                testAttrValues.setOfferId(rs.getInt("offer_id"));
                testAttrValues.setAttrId(rs.getInt("attr_id"));
                testAttrValues.setValue(rs.getString("value"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertNull(testAttrValues);
    }

    @Test
    public void updateAttrValues() throws Exception {
        AttrValues attrValues = new AttrValues(5,4,"30 days");
        AttrValuesRepository rep = new AttrValuesRepository();
        rep.updateAttrValues(attrValues);
        AttrValues testAttrValues = new AttrValues();
        String sqlQuery = String.format("SELECT * FROM attr_values WHERE offer_id = %s AND attr_id = %s",attrValues.getOfferId(),attrValues.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttrValues.setOfferId(rs.getInt("offer_id"));
                testAttrValues.setAttrId(rs.getInt("attr_id"));
                testAttrValues.setValue(rs.getString("value"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(attrValues.getOfferId(),testAttrValues.getOfferId());
        assertEquals(attrValues.getAttrId(),testAttrValues.getAttrId());
        assertEquals(attrValues.getValue(),testAttrValues.getValue());
    }

}