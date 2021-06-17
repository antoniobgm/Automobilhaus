package SAX;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class UserHandler extends DefaultHandler {
    private StringBuilder data=null;
    Fahrzeug fahrzeug;
    boolean bFahrzeugtyp = false;
    boolean bFahrzeugbezeichnung = false;
    boolean bHersteller = false;
    boolean bLeistung = false;
    boolean bVerkaufspreise=false;
    public ArrayList <Fahrzeug> Fahrzeuge = new ArrayList<>();

    public ArrayList<Fahrzeug> getFList() {
        return Fahrzeuge;}


    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Fahrzeuge")) {
            Fahrzeuge=new ArrayList<>();
            System.out.println("Fahrzeuge wurde init. ");
        } else if (qName.equalsIgnoreCase("Fahrzeug")) {
            fahrzeug= new Fahrzeug();
        } else if (qName.equalsIgnoreCase("Fahrzeugtyp")) {
            bFahrzeugtyp = true;
        } else if (qName.equalsIgnoreCase("Fahrzeugbezeichnung")) {
            bFahrzeugbezeichnung = true;
        }
        else if (qName.equalsIgnoreCase("Hersteller")) {
            bHersteller = true;
        }
        else if (qName.equalsIgnoreCase("Leistung")) {
            bLeistung = true;
        }
        else if (qName.equalsIgnoreCase("Verkaufspreise")) {
            bVerkaufspreise = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Fahrzeug")) {
            Fahrzeuge.add(fahrzeug);

        }
        else if (qName.equalsIgnoreCase("Fahrzeugtyp")) {
            fahrzeug.setFahrzeugtyp(data.toString());
            bFahrzeugtyp = false;
        } else if (qName.equalsIgnoreCase("Fahrzeugbezeichnung")) {
            fahrzeug.setFahrzeugbezeichnung(data.toString());
            bFahrzeugbezeichnung = false;
        }
        else if (qName.equalsIgnoreCase("Hersteller")) {
            fahrzeug.setHersteller(data.toString());
            bHersteller = false;
        }
        else if (qName.equalsIgnoreCase("Leistung")) {
            fahrzeug.setLeistung(Integer.parseInt(data.toString()));
            bLeistung = false;
        }
        else if (qName.equalsIgnoreCase("Verkaufspreise")) {
            fahrzeug.setVerkaufspreise(Integer.parseInt(data.toString()));
            bVerkaufspreise = false;
        }
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}