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
        return DBUtil.convertTo2DArray(DrugDAO.fuzzySelectBy(text));
    }

    public static void main(String[] args) {
        DoctorService ds = new DoctorService();
        ds.Search("");
    }
}
