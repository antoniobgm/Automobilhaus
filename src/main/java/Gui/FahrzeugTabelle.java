package Gui;

import SAX.Fahrzeug;
import SAX.Kunde;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FahrzeugTabelle extends AbstractTableModel {
    private static final int COLUMN_FAHRZEUGTYP=0;
    private static final int COLUMN_FAHRZEUGBEZEICHNUNG=1;
    private static final int COLUMN_HERSTELLER=2;
    private static final int COLUMN_VERKAUFSPREISE=3;
    private static final int COLUMN_LEISTUNG=4;



    private String[] columnNames={"Fahrzeugtyp","Fahrzeugbezeichnung","Hersteller","Verkaufpreise","Leistung"};
    private List<Fahrzeug> Fahrzeuge;
    public FahrzeugTabelle (List<Fahrzeug> theFahrzeug){
        Fahrzeuge=theFahrzeug;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return Fahrzeuge.size();
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int col){
        Fahrzeug tempKunde=Fahrzeuge.get(row);
        switch (col){
            case COLUMN_FAHRZEUGTYP:
                return tempKunde.getFahrzeugtyp();
            case COLUMN_FAHRZEUGBEZEICHNUNG:
                return tempKunde.getFahrzeugbezeichnung();
            case COLUMN_HERSTELLER:
                return tempKunde.getHersteller();
            case COLUMN_VERKAUFSPREISE:
                return tempKunde.getVerkaufspreise();
            case COLUMN_LEISTUNG:
                return tempKunde.getLeistung();
            default:
                return tempKunde.getFahrzeugtyp();}
    }


    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}



