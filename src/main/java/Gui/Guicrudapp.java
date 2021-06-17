package Gui;

import CRUD.DBStatement;
import DB.DBConnection;
import SAX.Kunde;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class Guicrudapp extends JFrame {
    private JPanel Guicruddappui;
    private JTextArea nachnameTextarea;
    private JButton Searchbutton;
    private JScrollBar scrollBar1;
    private JTable table1;
    private JButton addKundeButton;
    private DBConnection dbConnection= new DBConnection();
    private Connection connection=null;
    private DBStatement dbStatement=new DBStatement();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Guicrudapp");
        frame.setContentPane(new Guicrudapp().Guicruddappui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Guicrudapp(){
        Searchbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nachname=nachnameTextarea.getText();
                    List<Kunde> Kunden =null;
                    if(nachname!=null && nachname.trim().length()>0){
                        Kunden= dbStatement.searchKunde(nachname);
                    }else{
                        Kunden=dbStatement.getAllKunden();
                    }
                    KundenTabelle model=new KundenTabelle(Kunden);
                    table1.setModel(model);
                    //for(Kunde at:Kunden)
                        //System.out.println(at);

                }catch (Exception exc){
                    JOptionPane.showMessageDialog(Guicrudapp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            });

    }

    public void refreshKundenView() {
        try{
            List<Kunde> Kunden=dbStatement.getAllKunden();
            KundenTabelle model=new KundenTabelle(Kunden);
            table1.setModel(model);

        }catch(Exception exc){JOptionPane.showMessageDialog(this,"ERROR: "+exc,
                "error", JOptionPane.ERROR_MESSAGE);}
    }
}
