package Application;

//import java.beans.Statement;
//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.*;


import java.net.Socket;

public class BDD {
    //partie déclaration
    Connection connection;
    Statement statement;
    String SQL;
    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;
    
    //Constructeur
    public BDD(String url,String username,String password,String Host,int Port){
        this.url = url;
        this.username = username;
        this.password = password;
        this.Host = Host;
        this.Port = Port;
    }
    
    //fonction pour faire la connexion à la base de donnée
    public Connection  connexionDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);  
        } catch (Exception e) {
          System.err.println(e.getMessage());//e.getMessage est pour afficher ou se trouve le probleme exactement
        }//err c'est pour afficher l'erreur
        return connection;
    
}
    
    //fonction pour fermer la base de donnée
    public Connection closeconnexion(){
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return connection;
    }
    
    //Pour faire l'éxécution de réquettes
    public ResultSet exécutionQuery(String sql){
        connexionDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return resultSet;
    }
    
    //fonction pour l'éxécution de réquettes update
    public String exécutionUpdate(String sql){
        connexionDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;
        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;
    }
    
    //fonction de requette pour afficher tous les données du tableau
    public ResultSet querySelectAll(String nomTable){
        connexionDatabase();
        SQL = "SELECT * FROM " +nomTable;
        System.out.println(SQL);
        return this.exécutionQuery(SQL);
    }
    
    //fonction pour afficher tous avec de parametre ("etat")
    public ResultSet querySelectAll(String nomTable,String etat){
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + etat;
        return this.exécutionQuery(SQL);
    }
    
    //
    public ResultSet querySelect(String[] nomColonne, String nomTable){
        connexionDatabase();
        int i;
        SQL = "SELECT ";
        for (i =0;i <=nomColonne.length -1; i++){
            SQL += nomColonne[i];
            if(i< nomColonne.length -1){
                SQL += ",";
            }
        }
        SQL += " FROM " + nomTable;
        return this.exécutionQuery(SQL);
    }
    
    //
    public ResultSet forSelectCommand(String[] nomColonne, String nomTable, String etat){
        connexionDatabase();
        int i;
        SQL = "SELECT ";
        for (i =0;i <=nomColonne.length -1; i++){
            SQL += nomColonne[i];
            if(i< nomColonne.length -1){
                SQL += ",";
            }
        }
        SQL += " FROM "+ nomTable + " WHERE " + etat;
        return this.exécutionQuery(SQL);
    }
    
    //fonction pour insertion de données dans la table
    public String queryInsert(String nomTable, String[] contenuTableau){
        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable +" VALUES(";
        
        for(i = 0; i<= contenuTableau.length -1; i++){
            SQL += "'" + contenuTableau[i] + "'";
            if(i < contenuTableau.length - 1){
                SQL += ",";
            }
        }
        SQL += ")";
        return this.exécutionUpdate(SQL);
    }
    
    //insertion de données par colonnes
    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau){
        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable +"(";
        
        for(i = 0; i<= nomColonne.length -1; i++){
            SQL += nomColonne[i] ;
            if(i < nomColonne.length - 1){
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for(i = 0; i<= contenuTableau.length -1; i++){
            SQL += "'" + contenuTableau[i] + "'";
            if(i < contenuTableau.length - 1){
                SQL += ",";
            }
        }
        SQL += ")";
        return this.exécutionUpdate(SQL);
    }
    
    //fonction qui fait lupdate d'une donné dans une colonne selon l'etat
    public String queryUpdate(String nomTable,String[] nomColonne,String[] contenuTableau,String etat){
        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";
        
        for (i=0; i<= nomColonne.length -1; i++){
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length -1) {
                SQL += ",";
            }
        }
        SQL += " WHERE " + etat;
        return this.exécutionUpdate(SQL);
    }
    
    //fonction pour la requete supprimer sans parametre
    public String queryDelete(String nomtable){
        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.exécutionUpdate(SQL);
    }
    
    //fonction pour la requete supprimer avec parametre
    public String queryDelete(String nomTable, String etat){
        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + etat;
        return this.exécutionUpdate(SQL);
    }

}


