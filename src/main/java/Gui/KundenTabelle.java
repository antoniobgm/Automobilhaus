package Gui;

import SAX.Kunde;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KundenTabelle extends AbstractTableModel {
    private static final int COLUMN_NACHNAME=0;
    private static final int COLUMN_VORNAME=1;
    private static final int COLUMN_ANSCHRIFT=2;

    private String[] columnNames={"Nachname","Vorname","Anschrift"};
    private List<Kunde> Kunden;
    public KundenTabelle (List<Kunde> theKunden){
        Kunden=theKunden;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return Kunden.size();
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int col){
        Kunde tempKunde=Kunden.get(row);
        switch (col){
            case COLUMN_NACHNAME:
                return tempKunde.getNachname();
            case COLUMN_VORNAME:
                return tempKunde.getVorname();
            case COLUMN_ANSCHRIFT:
                return tempKunde.getAnschrift();
            default:
                return tempKunde.getNachname();}
        }


    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}



