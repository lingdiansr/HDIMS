package Entity;

import java.util.Date;

public class Doctor {
    public String Dno;
    public String Dname;
    public boolean Dsex;
    public int Dage;
    private String Dpwd;

    public Doctor() {
    }

    public Doctor(String dno, String dname, boolean dsex, int dage, String dpwd) {
        Dno = dno;
        Dname = dname;
        Dsex = dsex;
        Dage = dage;
        Dpwd = dpwd;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Dno='" + Dno + '\'' +
                ", Dname='" + Dname + '\'' +
                ", Dsex=" + Dsex +
                ", Dage=" + Dage +
                ", Dpwd='" + Dpwd + '\'' +
                '}';
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public boolean isDsex() {
        return Dsex;
    }

    public void setDsex(boolean dsex) {
        Dsex = dsex;
    }

    public int getDage() {
        return Dage;
    }

    public void setDage(int dage) {
        Dage = dage;
    }

    public String getDpwd() {
        return Dpwd;
    }

    public void setDpwd(String dpwd) {
        Dpwd = dpwd;
    }
}
