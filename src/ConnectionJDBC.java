import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class ConnectionJDBC {
    
    private final String server, DBName, user, password;
    private Connection con;
    
    public ConnectionJDBC(String user, String password) {        
        this.server = "mozart.dis.ulpgc.es";
        this.DBName = "PracticaDIU";       
        this.user = "estudiante-DIU";
        this.password = "diU-17-18";      
        /*//Uestudiante-DIU
        this.user = user;
        //diU-17-18
        this.password = password;
        */
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
        tables.add("tabla1");
        tables.add("tabla2");
        tables.add("tabla3");
        /*DatabaseMetaData md = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
        while(rs.next()) 
            tables.add(rs.getString("TABLE_NAME"));*/
        return tables;
    }
    
    public List<String> getFields(List<String> tables) throws SQLException {        
        List<String> fields = new ArrayList<>();
        fields.add("tabla1.campo1");
        fields.add("tabla1.campo2");
        fields.add("tabla1.campo3");
        fields.add("tabla1.campo4");
        fields.add("tabla2.campo1");
        fields.add("tabla2.campo2");
        fields.add("tabla3.campo1");
        fields.add("tabla3.campo2");
        /*DatabaseMetaData md = con.getMetaData();
        for (String table : tables) {
            ResultSet rs2 = md.getColumns(null, null, table, null);
            while (rs2.next()) 
                fields.add(table + rs2.getString("COLUMN_NAME"));
        }*/
        return fields;
    }
    
    
    
    
    
    /*    METODO ORIGINAL + METODO SIMULACION (NECESARIO 2 CAMPOS LIST Y SUS GETTERS)
    
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

        exampleTables();
    }

    private void exampleTables() {
        tables.add("Tabla 1");
        String[] campos = {"campo1","campo2"};
        fields.add(campos);
        
        tables.add("Tabla 2");
        String[] campos2 = {"campo3","campo4","campo5"};
        fields.add(campos2);
        
        tables.add("Tabla 3");
        String[] campos3 = {"campo6","campo7","campo8","campo9","campo10"};
        fields.add(campos3);
        
        tables.add("Tabla 4");
        String[] campos4 = {"campo11","campo14","campo15"};
        fields.add(campos4);
        
        tables.add("Tabla 5");
        String[] campos5 = {"campo43","campo34","campo125"};
        fields.add(campos5);
    }
    */
}

