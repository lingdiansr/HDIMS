package Service;

import Entity.Supplier;
import DAO.SupplierDAO;
import DAO.DrugDAO;
import Util.DBUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdminService {
    public static void main(String[] args) {
//        AdminService as = new AdminService();
//        for(Supplier s:as.search("19号")){
//            System.out.println(s);
//        }
    }

    public Object[][] allSupplier() {
        return DBUtil.convertTo2DArray(SupplierDAO.selectAllSuppliers());
    }

    public Object[][] searchSupplier(String text) {
//        return SupplierDAO.fuzzySelectBy(text);
//        Supplier[] ss = SupplierDAO.fuzzySelectBy(text);
//        for(Supplier s:ss){
//            System.out.println(s);
//        }
//        return ss;
        Supplier[] searchResult = SupplierDAO.fuzzySelectBy(text);
        return DBUtil.convertTo2DArray(searchResult);
    }

    public boolean insertSupplier(Object[][] data) {
        Supplier[] suppliersToInsert = DBUtil.convertToSupplierArray(data);
        Supplier[] existingSuppliers = SupplierDAO.selectAllSuppliers();

        List<Supplier> newSuppliers = new ArrayList<>();

        for (Supplier supplier : suppliersToInsert) {
            if (supplier.getSno()==null||supplier.getSname()==null||supplier.getSaddr()==null||supplier.getSphone()==null)
            {
                return false;
            }
            boolean exists = false;
            for (Supplier existingSupplier : existingSuppliers) {
                if (supplier.getSno().equals(existingSupplier.getSno())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                newSuppliers.add(supplier);
            }
        }

        for (Supplier newSupplier : newSuppliers) {
            try {
                SupplierDAO.insertSupplier(newSupplier);
            } catch (Exception e) {
                System.out.println("Error inserting supplier: " + e.getMessage());
                return false; // Return false if any error occurs during insertion
            }
        }
        return true; // Return true if all insertions are successful
    }
    public boolean insertSupplier(Supplier s){
        try{
            if (SupplierDAO.insertSupplier(s)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteSupplier(Object[][] data) {
        Supplier[] suppliersToDelete = DBUtil.convertToSupplierArray(data);
        Supplier[] existingSuppliers = SupplierDAO.selectAllSuppliers();

        List<String> snoListToDelete = Arrays.stream(suppliersToDelete).map(Supplier::getSno).collect(Collectors.toList());
        List<String> existingSnoList = Arrays.stream(existingSuppliers).map(Supplier::getSno).collect(Collectors.toList());

        List<String> snoListToBeDeleted = existingSnoList.stream()
                .filter(sno -> !snoListToDelete.contains(sno))
                .collect(Collectors.toList());

        for (String sno : snoListToBeDeleted) {
            try {
                SupplierDAO.deleteSupplier(sno);
            } catch (Exception e) {
                System.out.println("Error deleting supplier: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
    public boolean deleteSupplier(Supplier supplier) {
        try {
            SupplierDAO.deleteSupplier(supplier.getSno());
        } catch (Exception e) {
            System.out.println("Error deleting supplier: " + e.getMessage());
            return false;
        }
        return true;
    }
//    public boolean deleteSupplier(Object[][] data) {
//        Supplier[] suppliersToDelete = DBUtil.convertToSupplierArray(data);
//
//        for (Supplier supplier : suppliersToDelete) {
//            try {
//                SupplierDAO.deleteSupplier(supplier.Sno);
//            } catch (Exception e) {
//                System.out.println("Error deleting supplier: " + e.getMessage());
//                return false;
//            }
//        }
//        return true;
//    }
    public boolean updateSupplier(Object[][] data) {
        Supplier[] newSuppliers = DBUtil.convertToSupplierArray(data);
        Supplier[] existingSuppliers = SupplierDAO.selectAllSuppliers();

        for (Supplier newSupplier : newSuppliers) {
            boolean found = false;
            for (Supplier existingSupplier : existingSuppliers) {
                if (newSupplier.Sno.equals(existingSupplier.Sno)) {
                    found = true;
                    if (!newSupplier.equals(existingSupplier)) {
                        try {
                            SupplierDAO.updateSupplier(newSupplier);
                            return true;
                        } catch (Exception e) {
                            System.out.println("Error updating supplier: " + e.getMessage());
                        }
                    }
                    break;
                }
            }
            if (!found) {
                try {
                    SupplierDAO.insertSupplier(newSupplier);
                    return true;
                } catch (Exception e) {
                    System.out.println("Error inserting new supplier: " + e.getMessage());
                }
            }
        }
        return false;
    }

    public Object[][] searchDrug(String text) {
        return DBUtil.convertTo2DArray(DrugDAO.fuzzySelectDrugBy(text));
    }
}
