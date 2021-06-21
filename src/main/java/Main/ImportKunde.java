package Main;

import SAX.Kunde;
import SAX.UserHandlerKunde;
import org.apache.commons.codec.digest.DigestUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ImportKunde {


    public  ArrayList<Kunde> importKundeArray(String pathKunde) {

        ArrayList<Kunde> Kunden = null;
        try {


            //System.out.println(path1);
            File inputFileKunde = new File(pathKunde);
            //File inputFile = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Fahrzeug.xml");
            //File inputFile2 = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Kunde.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            UserHandlerKunde userhandlerKunde = new UserHandlerKunde();


            saxParser.parse(inputFileKunde, userhandlerKunde);
            Kunden = userhandlerKunde.getKList();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return Kunden;
    }

    public static String getHashImportKunde(String pathKunde) throws IOException {

        String xmlstring =readFile(pathKunde);
        String hash = DigestUtils.sha256Hex( xmlstring);
        System.out.println("Apache : Sha256hash: "+ hash);


   return hash; }

    public static String readFile(String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        }

        return sb.toString();
    }


}