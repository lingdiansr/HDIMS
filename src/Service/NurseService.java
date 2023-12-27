package Service;

import DAO.PrescriptionDAO;
import Entity.Prescription;

public class NurseService {
    //已处理处方的数组
    public Prescription[] Tprescription(){
        return PrescriptionDAO.getPrescriptions(true);
    }

    //未处理处方
    public Prescription[] Fprescription(){
        return PrescriptionDAO.getPrescriptions(false);
    }
    public static void main(String[] args){
        NurseService nurseService=new NurseService();
        for (Prescription p:nurseService.Fprescription()){
            System.out.println(p.Pno+"\t"+p.Pid+"\t");
        }
    }
}
