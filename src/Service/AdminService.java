package Service;
import Entity.Supplier;
import DAO.SupplierDAO;
import DAO.DrugDAO;
import Util.DBUtil;

public class AdminService {
    public Object[][] allSupplier(){
        return DBUtil.convertTo2DArray(SupplierDAO.selectAllSuppliers());
    }
    public Object[][] searchSupplier(String text){
//        return SupplierDAO.fuzzySelectBy(text);
//        Supplier[] ss = SupplierDAO.fuzzySelectBy(text);
//        for(Supplier s:ss){
//            System.out.println(s);
//        }
//        return ss;
        Supplier[] searchResult = SupplierDAO.fuzzySelectBy(text);
        return DBUtil.convertTo2DArray(searchResult);
    }
    public Object[][] searchDrug(String text){
        return DBUtil.convertTo2DArray(DrugDAO.fuzzySelectDrugBy(text));
    }
    public static void main(String[] args) {
//        AdminService as = new AdminService();
//        for(Supplier s:as.search("19号")){
//            System.out.println(s);
//        }
    }
}
