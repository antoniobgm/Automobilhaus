package SAX;

public class Kunde {

    private String Nachname;
    private String Vorname;
    private String Anschrift;
    private int ID;
    public Kunde(){

    }

    public Kunde(String Nachname, String Vorname, String  Anschrift) {
        super();

        this.Nachname=Nachname;
        this.Vorname=Vorname;
        this.Anschrift=Anschrift;
    }
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID=id;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getAnschrift() {
        return Anschrift;
    }

    public void setAnschrift(String anschrift) {
        Anschrift = anschrift;
    }

    @Override
    public String toString() {
        return " Nachname=" + this.Nachname + " Vorname=" + this.Vorname + " Anschrift=" + this.Anschrift ;
    }
}