package Main;

import java.io.FileOutputStream;
import java.util.List;

import CRUD.DBStatement;
import SAX.Fahrzeug;
import SAX.Kunde;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;


import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

public class CreatePdfA {



    public static void writeletter(String vornam, String nachnam, String anschrift) throws DocumentException {

        // Create a Document object
        Document document = new Document();

        try {
            String path="C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\sample.pdf";
            // Create a PdfWriter instance and use the document to write the pdf
            // to a specified location
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\sample.pdf"));

            // open the document
            document.open();

            // Add elements to the document
            document.add(new Paragraph(vornam+nachnam+"\n"+anschrift));
            document.add(new Paragraph("\n"+"\n"+"\n"+"\n"+"Guten Tag "+vornam+" "+nachnam+","+"\n"+"Hier ist die Liste der Autos: "+
                    "\n "));

            try {
                List<Fahrzeug> allefahrzeuge =null;
                DBStatement dbStatement=new DBStatement();
                allefahrzeuge=dbStatement.getAllFahrzeuge();
                System.out.println(allefahrzeuge.size());
                PdfPTable table = new PdfPTable(5);
                for(Fahrzeug emp : allefahrzeuge)
                    for(int aw = 0; aw < allefahrzeuge.size(); aw++){
                        table.addCell(emp.getFahrzeugbezeichnung());
                        table.addCell(emp.getFahrzeugtyp());
                        table.addCell(emp.getHersteller());
                        table.addCell(String.valueOf(emp.getVerkaufspreise()));
                        table.addCell(String.valueOf(emp.getLeistung()));}
                document.add(table);
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            // close the document


            System.out.println("PDF created at the location !!!");
        } catch (Exception e) {
            System.out.println("Exception occured :: " + e);
        }
    }
}