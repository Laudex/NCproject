package Repository;

import Entity.Attr;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class AttrRepositoryTest {
    @Test
    public void addAttr() throws Exception {
        Attr attr = new Attr(5,"test");
        AttrRepository rep = new AttrRepository();
        rep.addAttr(attr);
        Attr testAttr = new Attr();
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/NCproject", "postgres", "1");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttr.setAttrId(rs.getInt("attr_id"));
                testAttr.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(attr.getAttrId(),testAttr.getAttrId());
        assertEquals(attr.getName(),testAttr.getName());
    }

    @Test
    public void removeAttr() throws Exception {
        Attr attr = new Attr(5,"test");
        AttrRepository rep = new AttrRepository();
        rep.removeAttr(attr);
        Attr testAttr = null;
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/NCproject", "postgres", "1");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttr = new Attr();
                testAttr.setAttrId(rs.getInt("attr_id"));
                testAttr.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertNull(testAttr);
    }

    @Test
    public void updateAttr() throws Exception {
        Attr attr = new Attr(4,"period");
        AttrRepository rep = new AttrRepository();
        rep.updateAttr(attr);
        Attr testAttr = new Attr();
        String sqlQuery = String.format("SELECT * FROM attr WHERE attr_id = %s",attr.getAttrId());
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/NCproject", "postgres", "1");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testAttr.setAttrId(rs.getInt("attr_id"));
                testAttr.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(attr.getAttrId(),testAttr.getAttrId());
        assertEquals(attr.getName(),testAttr.getName());
    }

}