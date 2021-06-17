package Guinew;

import CRUD.DBStatement;
import DB.DBConnection;
import Gui.FahrzeugTabelle;
import Gui.KundenTabelle;
import SAX.Fahrzeug;
import SAX.Kunde;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FahrzeugCrudUi {
    private JLabel Fahrzeugregistrierung;
    private JPanel MainCrud;
    private JTextField textFahrzeugtyp;
    private JTextField textFahrzeugbezeichnung;
    private JTextField textFahrzeughersteller;
    private JTextField textLeisung;
    private JTextField textVerkaufspreise;
    private JButton saveButton;
    private JTable table2;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField fahrzeugtyptext;
    private JScrollPane table;
    private DBConnection dbConnection= new DBConnection();
    private Connection connection=null;




        public void start1() throws Exception {
            JFrame frame = new JFrame("FahrzeugCrudUi");
            frame.setContentPane(new FahrzeugCrudUi().MainCrud);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
        void tabellenload1() throws Exception {
            try {
                DBStatement dbStatement=new DBStatement();
                List<Fahrzeug> fahrze = null;

                fahrze = dbStatement.getAllFahrzeuge();
                FahrzeugTabelle model2 = new FahrzeugTabelle(fahrze);
                table2.setModel(model2);
                for(Fahrzeug at:fahrze)
                    System.out.println(at);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }





    public FahrzeugCrudUi() throws Exception {
            tabellenload1();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBStatement dbStatement=new DBStatement();
                    dbStatement.deleteFromDBfahrzeug(fahrzeugtyptext.getText());
                    JOptionPane.showMessageDialog(null, "RECORD DELETED");
                    tabellenload1();
                    textFahrzeugtyp.setText("");
                    textFahrzeugbezeichnung.setText("");
                    textFahrzeughersteller.setText("");
                    textLeisung.setText("");
                    textVerkaufspreise.setText("");
                    textFahrzeugtyp.requestFocus();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }






            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{DBStatement dbStatement=new DBStatement();
                    String fahrzeugbezeichnung=fahrzeugtyptext.getText();
                    List<Fahrzeug> Fahrzeuge =null;
                    if(fahrzeugbezeichnung!=null && fahrzeugbezeichnung.trim().length()>0){
                        Fahrzeuge= dbStatement.searchFahrzeug(fahrzeugbezeichnung);
                    }else{
                        Fahrzeuge=dbStatement.getAllFahrzeuge();
                    }
                    FahrzeugTabelle model=new FahrzeugTabelle(Fahrzeuge);
                    table2.setModel(model);
                    //for(Kunde at:Kunden)
                    //System.out.println(at);

                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBStatement dbStatement=new DBStatement();
                try{dbStatement.insertDataFahrzeug(textFahrzeugtyp.getText(),textFahrzeugbezeichnung.getText(),textFahrzeughersteller.getText(),Integer.parseInt(textVerkaufspreise.getText()),Integer.parseInt(textVerkaufspreise.getText()));


                    //table_load();
                    textFahrzeugtyp.setText("");textFahrzeugbezeichnung.setText("");textFahrzeughersteller.setText("");
                    textVerkaufspreise.setText("");textLeisung.setText("");textFahrzeugbezeichnung.requestFocus();
                    tabellenload1();
                }
                catch (Exception e1){e1.printStackTrace();
                }
            }
        });


    /*public void start1() {
        JFrame frame = new JFrame("FahrzeugCrudUi");
        frame.setContentPane(new FahrzeugCrudUi().MainCrud);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    void tabellenload1() throws Exception {
        try {
            DBStatement dbStatement=new DBStatement();
            List<Fahrzeug> fahrze = null;

            fahrze = dbStatement.getAllFahrzeuge();
            FahrzeugTabelle model = new FahrzeugTabelle(fahrze);
            table2.setModel(model);
            for(Fahrzeug at:fahrze)
            System.out.println(at);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{DBStatement dbStatement=new DBStatement();
                    dbStatement.updateDataFahrzeug(textFahrzeugtyp.getText(),textFahrzeugbezeichnung.getText(),textFahrzeughersteller.getText(),Integer.parseInt(textVerkaufspreise.getText()),Integer.parseInt(textLeisung.getText()));
                    JOptionPane.showMessageDialog(null,"RECORD UPDATED!!");
                    tabellenload1();
                    textFahrzeugtyp.setText("");
                    textFahrzeugbezeichnung.setText("");
                    textFahrzeughersteller.setText("");
                    textVerkaufspreise.setText("");
                    textLeisung.setText("");
                    textFahrzeugbezeichnung.requestFocus();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }}
