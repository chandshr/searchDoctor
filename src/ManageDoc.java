import java.util.*;

/*
 * Created by chandani on 12/3/16.
 */
public class ManageDoc {

    private HashMap<String, HashSet<Doctor>> areaDocSet;
    private HashMap<String, HashSet<Doctor>> specialityDocSet;
    private HashMap<Integer, HashSet<Doctor>> reviewDocSet;

    public ManageDoc(){
        this.areaDocSet = new HashMap<>();
        this.specialityDocSet = new HashMap<>();
        this.reviewDocSet = new HashMap<>();
    }

    /**
     * Algorithm: Get list of similar doctors based on priority, here priority is defined by number of matching features
     * Step 1: Get all the features of the given input doctor {A, B, C}
     * Step 2: Create all possible subset of the feature of step 1 {A, AB, AC, B, C, BC, ABC}
     * Step 3: Arrange the subset of features so that element with largest number of feature is infront eg {ABC, AB, AC, A, B, C}
     * Step 4: Scan through all the subset of features and get list of doctors with all the features
     * Step 5: Arrange the doctors based on priority with doctor having all the features in front.
     */
    public ArrayList<Doctor> getSimilarDocByPriority(Doctor doctor){
        ArrayList<Doctor> similarDocPrior = new ArrayList<Doctor>();
        HashSet<Doctor> seenDoc = new HashSet<>();

        ArrayList<DocFeatureVal> docFeatureVals = getDocFeatures(doctor);
        ArrayList<ArrayList<DocFeatureVal>> subsetDoc = getSubset(docFeatureVals);
        sortSubSetByDesc(subsetDoc);

        for(ArrayList<DocFeatureVal> featureVals : subsetDoc){
            Set<Doctor> doctorWithFeatureVal = getDocWithFeature(featureVals);
            Set<Doctor> unseenDoc = getNewDoc(seenDoc, doctorWithFeatureVal);
            similarDocPrior.addAll(unseenDoc);
            seenDoc.addAll(unseenDoc);
        }

        System.out.println("check similarDocPriorList");

        return similarDocPrior;
    }

    //Step 1
    public ArrayList<DocFeatureVal> getDocFeatures(Doctor doctor){
        ArrayList<DocFeatureVal> featureList = new ArrayList<>();
        DocFeatureVal area = new DocFeatureVal("areaDocSet", doctor.getArea());
        DocFeatureVal review = new DocFeatureVal("reviewDocSet", doctor.getReview());
        DocFeatureVal speciality = new DocFeatureVal("specialityDocSet", doctor.getSpeciality());

        featureList.add(area);
        featureList.add(review);
        featureList.add(speciality);

        return featureList;
    }

    //Step 2
    /**
     * creates subset
     * e.g given input A, B, C
     * returns {A, B, C, AB, AC, BC, ABC}
     */
    public ArrayList<ArrayList<DocFeatureVal>> getSubset(ArrayList<DocFeatureVal> features){
        if(features == null) return null;

        ArrayList<ArrayList<DocFeatureVal>> subset = new ArrayList<>();
        ArrayList<DocFeatureVal> element = new ArrayList<>();

        DocFeatureVal curr = features.remove(0);
        element.add(curr);
        subset.add(element);

        if(!features.isEmpty()){
            ArrayList<ArrayList<DocFeatureVal>> subsetNotInclCurr = getSubset(features);
            if(subsetNotInclCurr!=null){
                subset.addAll(subsetNotInclCurr);

                ArrayList<ArrayList<DocFeatureVal>> tempList = getCopy(subsetNotInclCurr);

                addElementInInnerList(curr, tempList);
                subset.addAll(tempList);
            }
        }

        return subset;
    }

    //Step 3
    public void sortSubSetByDesc(ArrayList<ArrayList<DocFeatureVal>> subset){
        Collections.sort(subset, new Comparator<ArrayList>() {
            @Override
            public int compare(ArrayList o1, ArrayList o2) {
                return o2.size()-o1.size();
            }
        });
    }

    //Step 4
    public Set<Doctor> getDocWithFeature(ArrayList<DocFeatureVal> featurelist){
        Set<Doctor> intersection = new HashSet<>();
        for(int i=0; i<featurelist.size(); i++){
            DocFeatureVal feature = featurelist.get(i);
            String key = feature.getKey();
            Object val = feature.getVal();

            Set<Doctor> doctorSetWithFeature = getDocListOfFeature(key, val);
            if(i==0){
                intersection.addAll(doctorSetWithFeature);
            }else {
                intersection.retainAll(doctorSetWithFeature);
            }
        }

        return intersection;
    }

    //Step 5
    public Set<Doctor> getNewDoc(Set<Doctor> seenDoc, Set<Doctor> allDoc){
        Set<Doctor> newDoc = new HashSet<>();

        for(Doctor doctor : allDoc){
            if(!seenDoc.contains(doctor)){
                newDoc.add(doctor);
            }
        }

        return newDoc;
    }

    /**
     * Algorithm: When given a list of Doctors categorize them based on area, review score, speciality
     */
    public void categorizeDoc(ArrayList<Doctor> doctorList) {
        for (Doctor doctor : doctorList) {
            addInAreaCategory(doctor);
            addInReviewCategory(doctor);
            addInSpecialityCategory(doctor);
        }
    }

    /**
     * create all posible subset of Doctor quality
     */
    public ArrayList<ArrayList<DocFeatureVal>> getSubsetofQuality(Doctor doctor){
        ArrayList<DocFeatureVal> featureList = getDocFeatures(doctor);
        return getSubset(featureList);
    }

    public ArrayList<ArrayList<DocFeatureVal>> getCopy(ArrayList<ArrayList<DocFeatureVal>> originalList){
        ArrayList<ArrayList<DocFeatureVal>> newList = new ArrayList<>();

        for(ArrayList<DocFeatureVal> subList : originalList){
            ArrayList tempList = new ArrayList(subList);
            newList.add(tempList);
        }

        return newList;
    }

    /**
     * adds a single feature to each sublist in a given subset of feature
     * e.g given input A and {B, BC} returns {A, AB, ABC}
     */

    public ArrayList<ArrayList<DocFeatureVal>> addElementInInnerList(DocFeatureVal feature, ArrayList<ArrayList<DocFeatureVal>> subsetOfFeature){
        for(ArrayList<DocFeatureVal> subList : subsetOfFeature){
            subList.add(feature);
        }

        return subsetOfFeature;
    }


    public Set<Doctor> getDocListOfFeature(String key, Object val){
        if(key.equals("specialityDocSet")){
            return this.specialityDocSet.get(val.toString());
        }else if(key.equals("areaDocSet")){
            return this.areaDocSet.get(val.toString());
        }else if(key.equals("reviewDocSet")){
            String str = val.toString();
            Integer v = Integer.valueOf(str);
            return this.reviewDocSet.get(v);
        }
        return null;
    }

    public void addInReviewCategory(Doctor doctor){
        int review = doctor.getReview();

        HashSet docSet;
        if(!reviewDocSet.containsKey(review)){
            docSet = new HashSet();
        }else{
            docSet = reviewDocSet.get(review);
        }
        docSet.add(doctor);
        reviewDocSet.put(review, docSet);
    }

    public void addInSpecialityCategory(Doctor doctor){
        String speciality = doctor.getSpeciality();
        if(speciality != null || speciality.length()>0){
            HashSet docSet;
            if(!specialityDocSet.containsKey(speciality)){
                docSet = new HashSet();
            }else{
                docSet = specialityDocSet.get(speciality);
            }
            docSet.add(doctor);
            specialityDocSet.put(speciality, docSet);
        }
    }

    public void addInAreaCategory(Doctor doctor){
        String area = doctor.getArea();
        if(area != null || area.length()>0){
            HashSet docSet;
            if(!areaDocSet.containsKey(area)){
                docSet = new HashSet();
            }else{
                docSet = areaDocSet.get(area);
            }
            docSet.add(doctor);
            areaDocSet.put(area, docSet);
        }
    }

    public HashSet<Doctor> getDocOfSpeciality(String speciality){
        return this.specialityDocSet.get(speciality);
    }

    public HashSet<Doctor> getDocOfArea(String area){
        return this.areaDocSet.get(area);
    }

    public HashSet<Doctor> getDocOfSpeciality(int reviewScore){
        return this.reviewDocSet.get(reviewScore);
    }

}
