package Main;

import SAX.Fahrzeug;
import SAX.UserHandlerFahrzeug;
import org.xml.sax.SAXException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ImportFahrzeug {
    private static System props;

    static ArrayList<Fahrzeug> importFahrzeug() {

        ArrayList<Fahrzeug> Fahrzeuge = null;
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("application.properties"));
            String pathFahrzeug = props.getProperty("path1");
            //System.out.println(path1);
            File inputFileFahrzeug = new File(pathFahrzeug);
            //File inputFile = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Fahrzeug.xml");
            //File inputFile2 = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Kunde.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            UserHandlerFahrzeug userHandlerFahrzeug = new UserHandlerFahrzeug();


            saxParser.parse(inputFileFahrzeug, userHandlerFahrzeug);
            Fahrzeuge = userHandlerFahrzeug.getFList();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return Fahrzeuge;
    }


    public static String getHashImportFahrzeug() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("application.properties"));
        String pathFahrzeug = props.getProperty("path1");
        String xmlstring =readFile(pathFahrzeug);
        String hash2 = DigestUtils.sha256Hex( xmlstring);
        System.out.println("Apache : Sha256hash: "+ hash2);


        return hash2; }



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
