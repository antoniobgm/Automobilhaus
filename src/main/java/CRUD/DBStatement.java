package CRUD;

import DB.DBConnection;
import SAX.Fahrzeug;
import SAX.Kunde;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;


public class DBStatement {
    private DBConnection dbConnection= new DBConnection();
    private Connection connection=null;
    private Statement statement=null;
    private PreparedStatement stmt=null;
    private ResultSet resultSet=null;
    public boolean checkDBConnection() throws SQLException {
        connection=dbConnection.getConnection();
        if (connection!=null){
            return true;
        }else{
            return false;
        }
    }

//Tabellen erstellen

    private final String TABLE_FAHRZEUG="Fahrzeug1234";
    private final String COLUMN_ID="ID";
    private final String COLUMN_FAHRZEUGTYP="Fahrzeugtyp";
    private final String COLUMN_FAHRZEUGBEZEICHNUNG="Fahrzeugbezeichnung";
    private final String COLUMN_HERSTELLER="Hersteller";
    private final String COLUMN_LEISTUNG="Leistung";
    private final String COLUMN_VERKAUFSPREISE="Verkaufspreise";

    private final String TABLE_KUNDE="Kunde123";
    private final String COLUMN_NACHNAME="Nachname";
    private final String COLUMN_VORNAME="Vorname";
    private final String COLUMN_ANSCHRIFT="Anschrift";

    public void createTable() throws SQLException {
        String createTable="CREATE TABLE IF NOT EXISTS "+ TABLE_FAHRZEUG +
                "( "
                + COLUMN_ID + " IDENTITY NOT NULL PRIMARY KEY, "
                + COLUMN_FAHRZEUGTYP + " VARCHAR(255), "
                + COLUMN_FAHRZEUGBEZEICHNUNG+ " VARCHAR(255), "
                + COLUMN_HERSTELLER+ " VARCHAR(255), "
                + COLUMN_LEISTUNG+ " INTEGER, "
                + COLUMN_VERKAUFSPREISE+ " INTEGER "
                +" )";
        executQuery(createTable);
        String createTable2="CREATE TABLE IF NOT EXISTS "+ TABLE_KUNDE +
                "( "
                + COLUMN_ID + " IDENTITY NOT NULL PRIMARY KEY, "
                + COLUMN_NACHNAME + " VARCHAR(255), "
                + COLUMN_VORNAME+ " VARCHAR(255), "
                + COLUMN_ANSCHRIFT+ " VARCHAR(255) "
                +" )";

        executQuery(createTable2);
    }
public void showtables() throws SQLException {
        try{
        connection=dbConnection.getConnection();
        statement=connection.createStatement();
        if (statement!= null){
            ResultSet rs = statement.executeQuery("SELECT * FROM "+TABLE_FAHRZEUG);
            ResultSetMetaData rsmd = rs.getMetaData();
            String name1 = rsmd.getColumnName(1);
            String name2 = rsmd.getColumnName(2);
            String name3 = rsmd.getColumnName(3);
            String name4 = rsmd.getColumnName(4);
            String name5 = rsmd.getColumnName(5);
            //System.out.println(name1+name2+name3+name4+name5);


            }
        }
         finally {
                statement.close();
                connection.close();

            }
}
public void executQuery(String sqlquery) throws SQLException{
    try {
        connection= dbConnection.getConnection();
        statement=connection.createStatement();
        if(statement!= null){
            statement.execute(sqlquery);
        }
    } finally {
        statement.close();
        connection.close();

    }

}
public void insertDataFahrzeug(String a, String b, String c, int d, int e)throws SQLException{
    connection = dbConnection.getConnection();
    Statement myStmt = null;
    ResultSet myRs = null;

    try {
        myStmt = connection.createStatement();
        myRs = myStmt.executeQuery("SELECT * FROM "+TABLE_FAHRZEUG +" WHERE "+COLUMN_FAHRZEUGBEZEICHNUNG+" = "+"'"+b+"'");
        if (myRs.next()){System.out.println("Fahrzeug already exists");JOptionPane.showMessageDialog(null, "FAHRZEUG MIT FAHRZEUGBEZEICHNUNG " + b +" EXISTIERT BEREITS. BITTE UPDATEN");}
        else{
            String insertDatafahrzeug= "INSERT INTO "+ TABLE_FAHRZEUG+" VALUES (null, '"+a+"', '"+b+"', '"+c+"', "+d+", "+e+" )";
            executQuery(insertDatafahrzeug);myRs.close();JOptionPane.showMessageDialog(null, "RECORD ADDED!!!!");}
    }finally {
        myStmt.close();

        connection.close();
    }


}

   public void insertDataKunde(String a, String b, String c)throws SQLException{
        connection = dbConnection.getConnection();
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = connection.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM "+TABLE_KUNDE +" WHERE "+COLUMN_NACHNAME+" = "+"'"+a+"'");
            if (myRs.next())
            {System.out.println("Kunde already exists");JOptionPane.showMessageDialog(null, "KUNDE MIT NACHNAMEN " + a +" EXISTIERT BEREITS. ");}
            else{
            String insertDatakunde= "INSERT INTO "+ TABLE_KUNDE+" VALUES (null, '"+a+"', '"+b+"', '"+c+"' )";
            executQuery(insertDatakunde);myRs.close();JOptionPane.showMessageDialog(null, "RECORD ADDED!!!!");}
            }finally {
            myStmt.close();

            connection.close();
        }

    }




    public void getDataFahrzeug() throws SQLException {
        String getData = "SELECT * FROM " + TABLE_FAHRZEUG;

        createQueryFahrzeug(getData);
    }

    public void getDataKunde() throws SQLException {
        String getData = "SELECT * FROM " + TABLE_KUNDE;

        createQueryKunde(getData);
    }
    private void showDataFahrzeug(ResultSet resultSet) throws SQLException{
        while(resultSet.next()){
            System.out.print(resultSet.getString((COLUMN_ID)));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_FAHRZEUGBEZEICHNUNG));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_FAHRZEUGTYP));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_HERSTELLER));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_LEISTUNG));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_VERKAUFSPREISE));
            System.out.print("\n");


        }
    }
    private void showDataKunde(ResultSet resultSet) throws SQLException{
        while(resultSet.next()){
            System.out.print(resultSet.getString((COLUMN_ID)));
            System.out.print("|");
            System.out.print(resultSet.getString((COLUMN_NACHNAME)));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_VORNAME));
            System.out.print("|");
            System.out.print(resultSet.getString(COLUMN_ANSCHRIFT));
            System.out.print("\n");


        }
    }
    private void createQueryFahrzeug(String query) throws SQLException {
        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            if (statement != null) {
                resultSet = statement.executeQuery(query);
                showDataFahrzeug(resultSet);
            }
        } finally {
            statement.close();
            resultSet.close();
            connection.close();
        }
    }

    private void createQueryKunde(String query) throws SQLException {
        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            if (statement != null) {
                resultSet = statement.executeQuery(query);
                showDataKunde(resultSet);
            }
        } finally {
            statement.close();
            resultSet.close();
            connection.close();
        }
    }


    public List<Kunde> getAllKunden() throws Exception {
        List<Kunde> list = new ArrayList<>();
        connection = dbConnection.getConnection();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = connection.createStatement();
            myRs = myStmt.executeQuery("select * from KUNDE123 order by VORNAME");

            while (myRs.next()) {
                Kunde tempEmployee = convertRowToKunde(myRs);
                list.add(tempEmployee);
                //System.out.println((tempEmployee));
            }

            return list;
        }
        finally {
            myStmt.close();
            myRs.close();
            connection.close();
        }
    }

    public List<Fahrzeug> getAllFahrzeuge() throws Exception {
        List<Fahrzeug> list = new ArrayList<>();
        connection = dbConnection.getConnection();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = connection.createStatement();
            myRs = myStmt.executeQuery("select * from FAHRZEUG1234 order by Fahrzeugtyp");

            while (myRs.next()) {
                Fahrzeug tempFahrzeug = convertRowToFahrzeug(myRs);
                list.add(tempFahrzeug);
               // System.out.println((tempFahrzeug));
            }

            return list;
        }
        finally {
            myStmt.close();
            myRs.close();
            connection.close();
        }
    }



    public List<Kunde> searchKunde(String Nachname) throws Exception{

        List<Kunde> list = new ArrayList<>();
        PreparedStatement myStmt=null;
        ResultSet myRs=null;
        try {connection = dbConnection.getConnection();
            Nachname += "%";
            myStmt = connection.prepareStatement("select * from "+TABLE_KUNDE+ " where "+ COLUMN_NACHNAME+" like ?  order by "+COLUMN_NACHNAME);

            myStmt.setString(1, Nachname);
            if(myStmt!=null){
            myRs = myStmt.executeQuery(); }

            if (myRs.next()==true) {
                Kunde tempEmployee = convertRowToKunde(myRs);
                list.add(tempEmployee);
               // System.out.println(tempEmployee);
            }else {
                System.out.println("FEHLER");
                JOptionPane.showMessageDialog(null,"Invalid Nachname");
            }
            return list;

    }
    finally {
            myStmt.close();
            myRs.close();
            connection.close();

        }
    }


    public List<Fahrzeug> searchFahrzeug(String Fahrzeug) throws Exception{

        List<Fahrzeug> list = new ArrayList<>();
        PreparedStatement myStmt=null;
        ResultSet myRs=null;
        try {connection = dbConnection.getConnection();
            Fahrzeug += "%";
            myStmt = connection.prepareStatement("select * from "+TABLE_FAHRZEUG+" where "+COLUMN_FAHRZEUGBEZEICHNUNG+" like ?  order by "+ COLUMN_FAHRZEUGBEZEICHNUNG);

            myStmt.setString(1, Fahrzeug);
            if(myStmt!=null){
                myRs = myStmt.executeQuery(); }

            if (myRs.next()==true) {
                Fahrzeug tempFahrzeug = convertRowToFahrzeug(myRs);
                list.add(tempFahrzeug);
                System.out.println(tempFahrzeug);
            }else {
                System.out.println("FEHLER");
                JOptionPane.showMessageDialog(null,"Invalid Fahrzeug");
            }
            return list;

        }
        finally {
            myStmt.close();
            myRs.close();
            connection.close();

        }
    }




        private Kunde convertRowToKunde(ResultSet myRs) throws SQLException {


            String nachname = myRs.getString(COLUMN_NACHNAME);
            String vorname = myRs.getString(COLUMN_VORNAME);
            String anschrift = myRs.getString(COLUMN_ANSCHRIFT);


            Kunde tempEmployee = new Kunde(nachname, vorname, anschrift);

            return tempEmployee;
        }


    private Fahrzeug convertRowToFahrzeug(ResultSet myRs) throws SQLException {


        String fahrzeugtyp = myRs.getString(COLUMN_FAHRZEUGTYP);
        String fahrzeugbezeichnung = myRs.getString(COLUMN_FAHRZEUGBEZEICHNUNG);
        String hersteller = myRs.getString(COLUMN_HERSTELLER);
        int verkaufspreise=myRs.getInt(COLUMN_VERKAUFSPREISE);
        int leistung=myRs.getInt(COLUMN_LEISTUNG);


        Fahrzeug tempFahrzeug = new Fahrzeug(fahrzeugtyp, fahrzeugbezeichnung,hersteller,verkaufspreise,leistung);

        return tempFahrzeug;
    }







    public void deleteFromDBkunde(String nachna) throws SQLException {
        String deleteQuery = "DELETE FROM " + TABLE_KUNDE + " WHERE " + COLUMN_NACHNAME + " = "+"'" + nachna+"'";

        System.out.println(deleteQuery);

        executQuery(deleteQuery);
    }

    public void deleteFromDBfahrzeug(String fahrzeugb) throws SQLException {
        String deleteQuery = "DELETE FROM " + TABLE_FAHRZEUG + " WHERE " + COLUMN_FAHRZEUGBEZEICHNUNG + " = "+"'" + fahrzeugb+"'";

        System.out.println(deleteQuery);

        executQuery(deleteQuery);
    }


    public void updateDataKunde(String nachnam, String vornam, String anschrif) throws SQLException {
        String updateQuery = "UPDATE " + TABLE_KUNDE + " SET "  + COLUMN_VORNAME + " = " +"'"+ vornam+"'"+", "+ COLUMN_ANSCHRIFT+ "="+"'"+anschrif+"'"
                + " WHERE " + COLUMN_NACHNAME + " = "+"'" + nachnam+"'";

        System.out.println(updateQuery);

        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            if (statement != null) {
                statement.executeUpdate(updateQuery);
            }
        } finally {
            statement.close();
            connection.close();
        }
    }


    public void updateDataFahrzeug(String fahrzeugty, String fahrzeugbez, String h, int v1, int v2) throws SQLException {
        String updateQuery = "UPDATE " + TABLE_FAHRZEUG + " SET "  + COLUMN_FAHRZEUGTYP + " = " +"'"+ fahrzeugty+"'"+", "+ COLUMN_HERSTELLER+ " = "+"'"+h+"'"+", "+COLUMN_VERKAUFSPREISE+" = "
                + v1 +", "+COLUMN_LEISTUNG+" = "+v2+ " WHERE " + COLUMN_FAHRZEUGBEZEICHNUNG + " = "+"'" + fahrzeugbez+"'";

        System.out.println(updateQuery);

        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            if (statement != null) {
                statement.executeUpdate(updateQuery);
            }
        } finally {
            statement.close();
            connection.close();
        }
    }








}
