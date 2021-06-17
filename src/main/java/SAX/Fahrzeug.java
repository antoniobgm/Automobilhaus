package SAX;

public class Fahrzeug {

    private String Fahrzeugtyp;
    private String Fahrzeugbezeichnung;
    private String Hersteller;
    private int Verkaufspreise;
    private int Leistung;
    private int ID;


public Fahrzeug(){};
public Fahrzeug(String fahrzeugtyp,String fahrzeugbezeichnung, String hersteller, int verkaufspreise, int leistung){
    this.Fahrzeugtyp=fahrzeugtyp;
    this.Fahrzeugbezeichnung=fahrzeugbezeichnung;
    this.Hersteller=hersteller;
    this.Verkaufspreise=verkaufspreise;
    this.Leistung=leistung;
}




    public void setFahrzeugtyp(String Fahrzeugtyp) {
        this.Fahrzeugtyp = Fahrzeugtyp;
    }
    public String getFahrzeugtyp(){return Fahrzeugtyp;}

    public void setFahrzeugbezeichnung(String Fahrzeugbezeichnung) {
        this.Fahrzeugbezeichnung = Fahrzeugbezeichnung;
    }
    public String getFahrzeugbezeichnung(){return Fahrzeugbezeichnung;}


    public void setVerkaufspreise(int Verkaufspreise) {
        this.Verkaufspreise = Verkaufspreise;
    }
    public int getVerkaufspreise(){return Verkaufspreise;}


    @Override
    public String toString() {
        return " Fahrzeugtyp=" + this.Fahrzeugtyp + " Verkaufspreise=" + this.Verkaufspreise + " Fahrzeugbezeichnung=" + this.Fahrzeugbezeichnung +
                " Leistung=" + this.Leistung+"Hersteller="+this.Hersteller;
    }



    public void setHersteller(String hersteller) {
        Hersteller = hersteller;
    }
    public String getHersteller(){return Hersteller;}



    public void setLeistung(int leistung) {
        Leistung = leistung;
    }
    public int getLeistung(){return Leistung;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

