package Guinew;

import CRUD.DBStatement;
import DB.DBConnection;
import GuiTabellen.KundenTabelle;
import Main.CreatePdfA;
import SAX.Kunde;
import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class KundeCrudUi {
    private JPanel Maincrud;
    private JTextField textNachname;
    private JTextField textVorname;
    private JTextField textAnschrift;
    private JTable table1;
    private JButton SAVEButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField nachnametext;
    private JScrollPane table;
    private JButton letter;
    private DBConnection dbConnection= new DBConnection();
    private Connection connection=null;


    public void start() throws Exception {


        JFrame frame = new JFrame("KundeCrudUi");
        frame.setContentPane(new KundeCrudUi().Maincrud);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    void tabellenload() throws Exception {
        try {
            DBStatement dbStatement=new DBStatement();
            List<Kunde> Kunden = null;

            Kunden = dbStatement.getAllKunden();
            KundenTabelle model = new KundenTabelle(Kunden);
            table1.setModel(model);
            //for(Kunde at:Kunden)
            //System.out.println(at);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public KundeCrudUi() throws Exception {
        tabellenload();
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBStatement dbStatement=new DBStatement();
                try{dbStatement.insertDataKunde(textNachname.getText(),textVorname.getText(),textAnschrift.getText(), "EXTERNER EINTRAG");


                //table_load();
                textNachname.setText("");textVorname.setText("");textAnschrift.setText("");
                textNachname.requestFocus();
                tabellenload();
                }
                catch (Exception e1){e1.printStackTrace();
                }

            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{DBStatement dbStatement=new DBStatement();
                    String nachname=nachnametext.getText();
                    List<Kunde> Kunden =null;
                    if(nachname!=null && nachname.trim().length()>0){
                        Kunden= dbStatement.searchKunde(nachname);
                        textNachname.setText(nachname);
                    }else{
                        Kunden=dbStatement.getAllKunden();
                    }
                    KundenTabelle model=new KundenTabelle(Kunden);
                    table1.setModel(model);
                    //for(Kunde at:Kunden)
                    //System.out.println(at);

                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try{DBStatement dbStatement=new DBStatement();
                dbStatement.updateDataKunde(textNachname.getText(),textVorname.getText(),textAnschrift.getText());
                JOptionPane.showMessageDialog(null,"RECORD UPDATED!!");
                tabellenload();
                textNachname.setText("");
                textVorname.setText("");
                textAnschrift.setText("");
                textNachname.requestFocus();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                DBStatement dbStatement=new DBStatement();
                dbStatement.deleteFromDBkunde(nachnametext.getText());
                JOptionPane.showMessageDialog(null, "RECORD DELETED");
                tabellenload();
                textNachname.setText("");
                textAnschrift.setText("");
                textVorname.setText("");
                textNachname.requestFocus();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            }
        });


        letter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBStatement dbStatement=new DBStatement();
                    String nachname=nachnametext.getText();
                    List<Kunde> Kunden =null;
                    if(nachname!=null && nachname.trim().length()>0){
                        Kunden= dbStatement.searchKunde(nachname);
                        KundenTabelle model=new KundenTabelle(Kunden);
                        textNachname.setText(nachname);
                        table1.setModel(model);
                        JOptionPane.showMessageDialog(null, "Bitte geben sie für eine Anfrage passende Daten ein und drücken Sie Generate:");
                        CreatePdfA.writeletter(textVorname.getText(), nachname, textAnschrift.getText());
                    }else{
                        textNachname.setText("");
                        textAnschrift.setText("");
                        textVorname.setText("");
                        JOptionPane.showMessageDialog(null, "Bitte geben sie gültige Daten");
                    }


                } catch (DocumentException documentException) {
                    documentException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Letter geschrieben");
                    try {
                        tabellenload();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    textNachname.setText("");
                    textAnschrift.setText("");
                    textVorname.setText("");
                    textNachname.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }


}
