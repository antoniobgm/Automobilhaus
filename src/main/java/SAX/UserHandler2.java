package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class UserHandler2 extends DefaultHandler {
    private StringBuilder data2=null;
    Kunde kunde;
    boolean bNachname = false;
    boolean bVorname = false;
    boolean bAnschrift = false;

    public ArrayList <Kunde> Kunden = new ArrayList<>();

    public ArrayList<Kunde> getKList() {
        return Kunden;}


    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Kunden")) {
            Kunden=new ArrayList<>();
            System.out.println("Fahrzeuge wurde init. ");
        } else if (qName.equalsIgnoreCase("Kunde")) {
            kunde= new Kunde();
        } else if (qName.equalsIgnoreCase("Nachname")) {
            bNachname = true;
        } else if (qName.equalsIgnoreCase("Vorname")) {
            bVorname = true;
        }

        data2 = new StringBuilder();
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Kunde")) {
            Kunden.add(kunde);

        }
        else if (qName.equalsIgnoreCase("Nachname")) {
            kunde.setNachname(data2.toString());
            bNachname = false;
        } else if (qName.equalsIgnoreCase("Vorname")) {
            kunde.setVorname(data2.toString());
            bVorname = false;
        }
        else if (qName.equalsIgnoreCase("Anschrift")) {
            kunde.setAnschrift(data2.toString());
            bAnschrift = false;
        }

    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data2.append(new String(ch, start, length));
    }
}