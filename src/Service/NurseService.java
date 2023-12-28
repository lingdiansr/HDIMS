package Service;

import DAO.DrugDAO;
import DAO.InventoryDrugDAO;
import DAO.PrescriptionDAO;
import DAO.SupplierDAO;
import Entity.*;
import Util.DBUtil;

import java.util.Date;

public class NurseService {

    public Object[][] allPrescription(){ //所有处方
        Prescription[] allprescription = PrescriptionDAO.getAllPrescriptions();
        return DBUtil.convertTo2DArray(allprescription);
    }

//    public Prescription[] Tprescription(){
//        return PrescriptionDAO.getPrescriptions(true);
//    }
    public Object[][] Tprescription(){ //已处理处方的数组
        Prescription[] tprescriptions= PrescriptionDAO.getPrescriptions(true);
        return DBUtil.convertTo2DArray(tprescriptions);
    }


//    public Prescription[] Fprescription(){
//        return PrescriptionDAO.getPrescriptions(false);
//    }
    public Object[][] Fprescription(){ //未处理处方,
        Prescription[] tprescriptions= PrescriptionDAO.getPrescriptions(false);
        return DBUtil.convertTo2DArray(tprescriptions);
    }


//查看药品库存列表
    public Object[][] allInventoryDrug(){
        InventoryDrug[] inventoryDrugs = InventoryDrugDAO.getallInventoryDrug();
        return DBUtil.convertTo2DArray(inventoryDrugs);
    }

//处理处方
    public boolean handleInventoryDRug(int Pno){

        return PrescriptionDAO.updatePrescriptionPstate(Pno,new Date());
    }
    public Object[][] searchInventory(String text) {
//        for (DrugDoctor d : DrugDAO.fuzzySelectBy(text)) {
//            System.out.println(d);
//        }
        InventoryDrug[] inventoryDrugs = InventoryDrugDAO.fuzzySelectBy(text);
        return DBUtil.convertTo2DArray(inventoryDrugs);
    }
    public Object[][] searchPrescription(String text) {
        Prescription[] prescriptions = PrescriptionDAO.fuzzySelectBy(text);
        return DBUtil.convertTo2DArray(prescriptions);
    }
    public Object[][] searchPrescription(String text,boolean Pstate) {
        Prescription[] prescriptions = PrescriptionDAO.fuzzySelectBy(text,Pstate);
        return DBUtil.convertTo2DArray(prescriptions);
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
