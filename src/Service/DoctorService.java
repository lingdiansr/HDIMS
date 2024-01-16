package Service;
import DAO.DrugDAO;
import Entity.Drug;
import Entity.DrugDoctor;
import Util.DBUtil;

public class DoctorService {
    public Object[][] allDrug(){
        Drug[] resultDrug=DrugDAO.getAllDrug();

        return DBUtil.convertTo2DArray(resultDrug);
    }
    public Object[][] Search(String text){
        for (DrugDoctor d : DrugDAO.fuzzySelectBy(text)){
            System.out.println(d);
        }
//        DrugDoctor[] tt= DrugDAO.fuzzySelectBy(text);
       return DBUtil.convertTo2DArray(DrugDAO.fuzzySelectBy(text));
//        for (int i = 0; i < t.length; i++) {
//            for (int j = 0; j < t[i].length; j++) {
//                System.out.print(t[i][j] + " ");
//            }
//            System.out.println(); // 在每行结束时换行
//        }
//        return null;
    }

    public static void main(String[] args) {
        DoctorService ds = new DoctorService();
        ds.Search("");
    }
}
