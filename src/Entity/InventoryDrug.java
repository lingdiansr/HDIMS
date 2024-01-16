package Entity;

import java.util.Date;

public class InventoryDrug {
    public String PDno;
    public Date PDbatch;
    public int PDnum;
    public String Sno;
    public String SAno;
    public Date Stime;

    public InventoryDrug() {
    }

    public InventoryDrug(String PDno, Date PDbatch, int PDnum, String sno, String SAno, Date stime) {
        this.PDno = PDno;
        this.PDbatch = PDbatch;
        this.PDnum = PDnum;
        Sno = sno;
        this.SAno = SAno;
        Stime = stime;
    }

    @Override
    public String toString() {
        return "InventoryDrug{" +
                "PDno='" + PDno + '\'' +
                ", PDbatch=" + PDbatch +
                ", PDnum=" + PDnum +
                ", Sno='" + Sno + '\'' +
                ", SAno='" + SAno + '\'' +
                ", Stime=" + Stime +
                '}';
    }

    public String getPDno() {
        return PDno;
    }

    public void setPDno(String PDno) {
        this.PDno = PDno;
    }

    public Date getPDbatch() {
        return PDbatch;
    }

    public void setPDbatch(Date PDbatch) {
        this.PDbatch = PDbatch;
    }

    public int getPDnum() {
        return PDnum;
    }

    public void setPDnum(int PDnum) {
        this.PDnum = PDnum;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSAno() {
        return SAno;
    }

    public void setSAno(String SAno) {
        this.SAno = SAno;
    }

    public Date getStime() {
        return Stime;
    }

    public void setStime(Date stime) {
        Stime = stime;
    }
}
