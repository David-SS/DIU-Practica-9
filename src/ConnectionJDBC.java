import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;


public class ConnectionJDBC {
    
    private String server, DBName, user, password;
    public ConnectionJDBC(String user, String password) {
        
        this.server = "mozart.dis.ulpgc.es";
        this.DBName = "PracticaDIU";    
        
        /*//Uestudiante-DIU
        this.user = user;
        //diU-17-18
        this.password = password;
        */
        
        this.user = "estudiante-DIU";
        this.password = "diU-17-18";        
    }

    public void connection() {
        try {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

