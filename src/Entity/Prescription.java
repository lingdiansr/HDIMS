package Entity;

import java.util.Date;

public class Prescription {
    public int Pno;
    public String Pid;
    public String Dno;
    public Date Ptime;
    public String Nno;
    public Date Htime;
    public boolean Pstate;

    public Prescription() {
    }

    public Prescription(int pno, String pid, String dno, Date ptime, String nno, Date htime, boolean pstate) {
        Pno = pno;
        Pid = pid;
        Dno = dno;
        Ptime = ptime;
        Nno = nno;
        Htime = htime;
        Pstate = pstate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "Pno=" + Pno +
                ", Pid='" + Pid + '\'' +
                ", Dno='" + Dno + '\'' +
                ", Ptime=" + Ptime +
                ", Nno='" + Nno + '\'' +
                ", Htime=" + Htime +
                ", Pstate=" + Pstate +
                '}';
    }

    public int getPno() {
        return Pno;
    }

    public void setPno(int pno) {
        Pno = pno;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public Date getPtime() {
        return Ptime;
    }

    public void setPtime(Date ptime) {
        Ptime = ptime;
    }

    public String getNno() {
        return Nno;
    }

    public void setNno(String nno) {
        Nno = nno;
    }

    public Date getHtime() {
        return Htime;
    }

    public void setHtime(Date htime) {
        Htime = htime;
    }

    public boolean isPstate() {
        return Pstate;
    }

    public void setPstate(boolean pstate) {
        Pstate = pstate;
    }
}
