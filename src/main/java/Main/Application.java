package Main;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import CRUD.DBStatement;
import Guinew.FahrzeugCrudUi;
import Guinew.KundeCrudUi;
import SAX.Fahrzeug;
import SAX.Kunde;
import SAX.UserHandler;
import SAX.UserHandler2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Application {

    public static void main(String[] args) {

        try {
            Properties props= new Properties();
            props.load(new FileInputStream("application.properties"));
            String path1=props.getProperty("path1");
            String path2=props.getProperty("path2");
            //System.out.println(path1);

            File inputFile = new File(path1);
            File inputFile2 = new File(path2);
            //File inputFile = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Fahrzeug.xml");
            //File inputFile2 = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Kunde.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            UserHandler2 userhandler2 = new UserHandler2();

            saxParser.parse(inputFile, userhandler);
            saxParser.parse(inputFile2,userhandler2);
            ArrayList<Fahrzeug> Fahrzeuge = userhandler.getFList();
            ArrayList<Kunde> Kunden =userhandler2.getKList();
            DBStatement dbStatement=new DBStatement();


            try {

                dbStatement.checkDBConnection();
                System.out.println("Verbindung aufgebaut");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try{
                dbStatement.createTable();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                dbStatement.showtables();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for(Fahrzeug emp : Fahrzeuge)
                try {
                    dbStatement.insertDataFahrzeug(emp.getFahrzeugtyp(), emp.getFahrzeugbezeichnung(), emp.getHersteller(), emp.getLeistung(), emp.getVerkaufspreise());
                } catch(Exception e){
                    e.printStackTrace();
                }



            for (Kunde emp: Kunden)
                try {
                    dbStatement.insertDataKunde(emp.getNachname(), emp.getVorname(), emp.getAnschrift());
                } catch(Exception e){
                    e.printStackTrace();
                }
        /*

        try {
				dbStatement.getDataFahrzeug();
			} catch (Exception e) {
				e.printStackTrace();
			}
            try {
                dbStatement.getDataKunde();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                dbStatement.searchKunde("jas");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                dbStatement.getAllKunden();
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
            try {KundeCrudUi kundeCrudUi=new KundeCrudUi();
                kundeCrudUi.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                FahrzeugCrudUi fahrzeugCrudUi=new FahrzeugCrudUi();
                fahrzeugCrudUi.start1();
            } catch (Exception e) {
                e.printStackTrace();
            }






        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}