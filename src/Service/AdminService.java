package Service;
import Entity.Supplier;
import DAO.SupplierDAO;
public class AdminService {
    public Supplier[] allSupplier(){
        return SupplierDAO.selectAllSuppliers();
    }
    public Supplier[] search(String text){
        return SupplierDAO.fuzzySelectBy(text);
    }
    public static void main(String[] args) {
        AdminService as = new AdminService();
        for(Supplier s:as.search("19号")){
            System.out.println(s);
        }
    }
}
