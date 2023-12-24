package Entity;

public class Drug {
    public String PDno;
    public String PDname;
    public int PDlife;

    public Drug() {
    }

    public Drug(String PDno, String PDname, int PDlife) {
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

    public int getPDlife() {
        return PDlife;
    }

    public void setPDlife(int PDlife) {
        this.PDlife = PDlife;
    }
}
