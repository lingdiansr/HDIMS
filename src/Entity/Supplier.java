package Entity;

public class Supplier {
    public String Sno;
    public String Sname;
    public String Saddr;
    public String Sphone;

    public Supplier() {
    }

    public Supplier(String sno, String sname, String saddr, String sphone) {
        Sno = sno;
        Sname = sname;
        Saddr = saddr;
        Sphone = sphone;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Saddr='" + Saddr + '\'' +
                ", Sphone='" + Sphone + '\'' +
                '}';
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSaddr() {
        return Saddr;
    }

    public void setSaddr(String saddr) {
        Saddr = saddr;
    }

    public String getSphone() {
        return Sphone;
    }

    public void setSphone(String sphone) {
        Sphone = sphone;
    }
}
