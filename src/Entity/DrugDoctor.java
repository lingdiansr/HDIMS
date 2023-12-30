package Entity;

public class DrugDoctor extends Drug{
    public int PDnum;

    public DrugDoctor() {
    }

    public DrugDoctor(String PDno, String PDname, String PDlife, int PDnum) {
        super(PDno, PDname, PDlife);
        this.PDnum = PDnum;
    }

    @Override
    public String toString() {
        return "DrugDoctor{" +
                "PDnum=" + PDnum +
                ", PDno='" + PDno + '\'' +
                ", PDname='" + PDname + '\'' +
                ", PDlife=" + PDlife +
                '}';
    }

    public int getPDnum() {
        return PDnum;
    }

    public void setPDnum(int PDnum) {
        this.PDnum = PDnum;
    }
}
