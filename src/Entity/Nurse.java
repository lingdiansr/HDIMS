package Entity;

import java.util.Date;

public class Nurse {
    public String Nno;
    public String Nname;
    public boolean Nsex;
    public int Nage;
    private String Npwd;

    public Nurse() {
    }

    public Nurse(String nno, String nname, boolean nsex, int nage, String npwd) {
        Nno = nno;
        Nname = nname;
        Nsex = nsex;
        Nage = nage;
        Npwd = npwd;
    }

    public String getNno() {
        return Nno;
    }

    public void setNno(String nno) {
        Nno = nno;
    }

    public String getNname() {
        return Nname;
    }

    public void setNname(String nname) {
        Nname = nname;
    }

    public boolean isNsex() {
        return Nsex;
    }

    public void setNsex(boolean nsex) {
        Nsex = nsex;
    }

    public int getNage() {
        return Nage;
    }

    public void setNage(int nage) {
        Nage = nage;
    }

    public String getNpwd() {
        return Npwd;
    }

    public void setNpwd(String npwd) {
        Npwd = npwd;
    }
}
