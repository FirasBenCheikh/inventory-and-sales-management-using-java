
package Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;


public class ResultSetTableModel extends AbstractTableModel{
    //déclaration des attributs
    private ResultSet rs;
    
    public ResultSetTableModel(ResultSet rs){
        this.rs = rs;
        fireTableDataChanged();  //Notifie à tous les écouteurs que toutes les valeurs de cellule dans les lignes de la table peuvent avoir changé
    }
    
    //fonction pour obtenir le nombre de colonnes
    public int getColumnCount(){
        try {
            if (rs == null){
                return 0;
            }else {
                return rs.getMetaData().getColumnCount();
            }
        } catch (SQLException e) {
            System.out.println("obtenir le résultat du nombre de colonnes a générer une erreur lors de l'obtention du nombre de colonnes");
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    
    //fonction pour obtenir le nombre de lignes
    public int getRowCount(){
        try {
             if (rs == null) {
                return 0;
            }else {
                rs.last();
                return rs.getRow();
                
            }
        } catch (SQLException e) {
            System.out.println("obtenir le résultat du nombre de lignes a générer une erreur lors de l'obtention du nombre de lignes");
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    //fonction pour  obtenir une valeur à l'ensemble de résultats
    public Object getValueAt(int rowIndex, int columunIndex){
        if (rowIndex <0 || rowIndex > getRowCount() || columunIndex < 0 || columunIndex > getColumnCount()){
            return null;
        }
        try {
            if (rs == null){
                return null;
            }else {
                rs.absolute(rowIndex + 1);
                return rs.getObject(columunIndex + 1);
            }
        } catch (SQLException e) {
            System.out.println("obtenir une valeur à l'ensemble de résultats à génerer une erreur lors de l'extraction de lignes");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    //fonction pour obtenir le nom de la colonne
    public String getColumnName(int columnIndex){
        try {
            return rs.getMetaData().getColumnName(columnIndex + 1);
        } catch (SQLException e) {
            System.out.println("obtenir le jeu de résultats du nom de la colonne a géneré une erreur lors de la récupération du nom de la colonne");
            System.out.println(e.getMessage());
        }
        return super.getColumnName(columnIndex);
    }
}
