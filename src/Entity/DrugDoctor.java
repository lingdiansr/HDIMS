package Entity;

public class DrugDoctor extends Drug{
    public int PDnum;

    public DrugDoctor() {
    }

    public DrugDoctor(String PDno, String PDname, int PDlife, int PDnum) {
        super(PDno, PDname, PDlife);
        this.PDnum = PDnum;
    }

    public int getPDnum() {
        return PDnum;
    }

    public void setPDnum(int PDnum) {
        this.PDnum = PDnum;
    }
}
