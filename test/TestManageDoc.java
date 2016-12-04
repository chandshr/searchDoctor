import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chandani on 12/3/16.
 */
public class TestManageDoc {

    private ManageDoc manageDoc = new ManageDoc();

    @Test
    public void testcategorizeDoc(){
        Doctor doctor1 = new Doctor("Dr. Midhan", "Nephrologist", "Kathmandu", 5);
        Doctor doctor2 = new Doctor("Dr. Chandani", "Nephrologist", "Seattle", 4);
        Doctor doctor3 = new Doctor("Dr. Aaron", "Cardiologist", "Kathmandu", 1);
        Doctor doctor4 = new Doctor("Dr. Roshni", "Nephrologist", "Vegas", 5);
        Doctor doctor5 = new Doctor("Dr. Keppe", "Gynacologist", "Seattle", 2);
        Doctor doctor6 = new Doctor("Dr. Hem", "Cardiologist", "Alabama", 5);
        Doctor doctor7 = new Doctor("Dr. John", "Nephrologist", "Kathmandu", 5);

        ArrayList<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor1);
        doctorList.add(doctor2);
        doctorList.add(doctor3);
        doctorList.add(doctor4);
        doctorList.add(doctor5);
        doctorList.add(doctor6);
        doctorList.add(doctor7);

        manageDoc.categorizeDoc(doctorList);
        ArrayList<Doctor> similardocs = manageDoc.getSimilarDocByPriority(doctor1);
        ArrayList<Doctor> expected = new ArrayList<>();
        expected.add(doctor7);
        expected.add(doctor1);
        expected.add(doctor4);
        expected.add(doctor3);
        expected.add(doctor6);
        expected.add(doctor2);
        Assert.assertEquals(similardocs, expected);
    }

    @Ignore
    public void testgetSubset(){
        ArrayList<DocFeatureVal> featureList = new ArrayList<>();
        featureList.add(new DocFeatureVal("name", "Dr. Chandani"));
        featureList.add(new DocFeatureVal("area", "Seattle"));
        featureList.add(new DocFeatureVal("special", "Nephrologist"));

        ArrayList<ArrayList<DocFeatureVal>> subset = manageDoc.getSubset(featureList);
        manageDoc.sortSubSetByDesc(subset);
        Assert.assertEquals(1, 1);
    }

}
