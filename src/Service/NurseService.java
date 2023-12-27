package Service;

import DAO.DrugDAO;
import DAO.InventoryDrugDAO;
import DAO.PrescriptionDAO;
import DAO.SupplierDAO;
import Entity.Drug;
import Entity.InventoryDrug;
import Entity.Prescription;
import Entity.Supplier;
import Util.DBUtil;

import java.util.Date;

public class NurseService {
    //已处理处方的数组
//    public Prescription[] Tprescription(){
//        return PrescriptionDAO.getPrescriptions(true);
//    }
    public Object[][] Tprescription(){
        Prescription[] tprescriptions= PrescriptionDAO.getPrescriptions(true);
        return DBUtil.convertTo2DArray(tprescriptions);
    }

    //未处理处方,还为处理所以不需要处理时间
//    public Prescription[] Fprescription(){
//        return PrescriptionDAO.getPrescriptions(false);
//    }
    public Object[][] Fprescription(){
        Prescription[] tprescriptions= PrescriptionDAO.getPrescriptions(false);
        return DBUtil.convertTo2DArray(tprescriptions);
    }


//查看药品库存列表
    public Object[][] allInventoryDrug(){
        InventoryDrug[] inventoryDrugs = InventoryDrugDAO.getallInventoryDrug();
        return DBUtil.convertTo2DArray(inventoryDrugs);
    }

//处理药品,
    public boolean handleInventoryDRug(int Pno){
        return PrescriptionDAO.updatePrescriptionPstate(Pno,new Date());
    }
    public static void main(String[] args){
        NurseService nurseService=new NurseService();
       Object[][] object = nurseService.Tprescription();
       for (int i=0;i< object.length;i++) {
           for (int j = 0; j < 6; j++) {
               System.out.print(object[i][j] + "\t");
           }
         System.out.println();
       }
        System.out.println(nurseService.handleInventoryDRug(2));
     //   System.out.println(new Date());
    }
}
