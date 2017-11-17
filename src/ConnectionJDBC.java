import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class ConnectionJDBC {
    
    private final String server, DBName, user, password;
    private Connection con;
    
    public ConnectionJDBC(String user, String password) {        
        this.server = "mozart.dis.ulpgc.es";
        this.DBName = "PracticaDIU";   
        //this.user = user;
        //this.password = password;
        this.user = "estudiante-DIU";
        this.password = "diU-17-18";
    }

    
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection("jdbc:mysql://" + 
                this.server + "/" + this.DBName + "?useSSL=true",
                this.user,
                this.password);
    }
    
    public void disconnect() throws SQLException {
        this.con.close();
    }
    
    public List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData md = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
        while(rs.next()) 
            tables.add(rs.getString("TABLE_NAME"));
        return tables;
    }
    
    public List<String> getFields(List<String> tables) throws SQLException {        
        List<String> fields = new ArrayList<>();
        DatabaseMetaData md = con.getMetaData();
        for (String table : tables) {
            ResultSet rs2 = md.getColumns(null, null, table, null);
            while (rs2.next()) 
                fields.add(table + "." + rs2.getString("COLUMN_NAME"));
        }
        return fields;
    }
    
    /*    METODO ORIGINAL
    
    public void connect() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://" + 
                this.server + "/" + this.DBName + "?useSSL=true",
                this.user,
                this.password);
        DatabaseMetaData md = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
        while(rs.next()){
                String nombreTabla = rs.getString("TABLE_NAME");
                System.out.println("Tabla: " + nombreTabla);
                ResultSet rs2 = md.getColumns(null, null, nombreTabla, null);
                while (rs2.next()) {
                    String nombreCampo = rs2.getString("COLUMN_NAME");
                    System.out.println("   Campo: " + nombreCampo);
                }
        }
        con.close();
    }*/
}

