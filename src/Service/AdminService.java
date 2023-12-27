package Service;
import Entity.Supplier;
import DAO.SupplierDAO;
import Util.DBUtil;

public class AdminService {
    public Supplier[] allSupplier(){
        return SupplierDAO.selectAllSuppliers();
    }
    public Object[][] search(String text){
//        return SupplierDAO.fuzzySelectBy(text);
//        Supplier[] ss = SupplierDAO.fuzzySelectBy(text);
//        for(Supplier s:ss){
//            System.out.println(s);
//        }
//        return ss;
        Supplier[] searchResult = SupplierDAO.fuzzySelectBy(text);
        return DBUtil.convertTo2DArray(searchResult);
    }

    public static void main(String[] args) {
//        AdminService as = new AdminService();
//        for(Supplier s:as.search("19号")){
//            System.out.println(s);
//        }
    }
}
