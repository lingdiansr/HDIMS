package Entity;


/**
 * @author lingdianshiren
 * @version jdk11.0.19
 */
public class Admin {
    public String Ano;
    public String Aname;
    public boolean Asex;
    public int Aage;
    private String Apwd;

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    public String getAname() {
        return Aname;
    }


    public void setAname(String Aname) {
        this.Aname = Aname;
    }


    public void setAsex(boolean Asex) {
        this.Asex = Asex;
    }

    public boolean getAsex() {
        return Asex;
    }


    public int getAage() {
        return Aage;
    }

    public void setAage(int Aage) {
        this.Aage = Aage;
    }

    public String getApwd() {
        return Apwd;
    }

    public void setApwd(String Apwd) {
        this.Apwd = Apwd;
    }
}
