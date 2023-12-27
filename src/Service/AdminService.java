package Service;
import Entity.Supplier;
import DAO.SupplierDAO;
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
        Object[][] data = new Object[searchResult.length][4];
        for (int i = 0; i < searchResult.length; i++) {
            data[i][0] = searchResult[i].getSno();
            data[i][1] = searchResult[i].getSname();
            data[i][2] = searchResult[i].getSaddr();
            data[i][3] = searchResult[i].getSphone();
        }
        return data;
    }

    public static void main(String[] args) {
//        AdminService as = new AdminService();
//        for(Supplier s:as.search("19号")){
//            System.out.println(s);
//        }
    }
}
