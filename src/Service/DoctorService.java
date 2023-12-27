package Service;
import DAO.DrugDAO;
import Entity.Drug;
import Util.DBUtil;

public class DoctorService {
    public Object[][] allDrug(){
        Drug[] resultDrug=DrugDAO.getAllDrug();
        return DBUtil.convertTo2DArray(resultDrug);
    }
    public Object[][] Search(String text){
        return DBUtil.convertTo2DArray(DrugDAO.fuzzySelectBy(text));
    }
}
