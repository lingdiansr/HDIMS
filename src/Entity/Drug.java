package Entity;

public class Drug {
    public String PDno;
    public String PDname;
    public String PDlife;

    public Drug() {
    }

    public Drug(String PDno, String PDname, String PDlife) {
        this.PDno = PDno;
        this.PDname = PDname;
        this.PDlife = PDlife;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "PDno='" + PDno + '\'' +
                ", PDname='" + PDname + '\'' +
                ", PDlife=" + PDlife +
                '}';
    }

    public String getPDno() {
        return PDno;
    }

    public void setPDno(String PDno) {
        this.PDno = PDno;
    }

    public String getPDname() {
        return PDname;
    }

    public void setPDname(String PDname) {
        this.PDname = PDname;
    }

    public String getPDlife() {
        return (String) PDlife;
    }

    public void setPDlife(String PDlife) {
        this.PDlife = PDlife;
    }
}
