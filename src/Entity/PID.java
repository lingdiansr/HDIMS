package Entity;

public class PID {
    public int Pno;
    public String PDno;
    public short PDnum;

    public PID() {
    }

    public PID(int pno, String PDno, short PDnum) {
        Pno = pno;
        this.PDno = PDno;
        this.PDnum = PDnum;
    }

    @Override
    public String toString() {
        return "PID{" +
                "Pno=" + Pno +
                ", PDno='" + PDno + '\'' +
                ", PDnum=" + PDnum +
                '}';
    }

    public int getPno() {
        return Pno;
    }

    public void setPno(int pno) {
        Pno = pno;
    }

    public String getPDno() {
        return PDno;
    }

    public void setPDno(String PDno) {
        this.PDno = PDno;
    }

    public short getPDnum() {
        return PDnum;
    }

    public void setPDnum(short PDnum) {
        this.PDnum = PDnum;
    }
}
