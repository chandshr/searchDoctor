/**
 * Created by chandani on 12/3/16.
 */
public class Doctor {

    private String name;
    private String speciality;
    private String area;
    private int reviewScore;

    public Doctor(String name, String speciality, String area, int review){
        this.name = name;
        this.speciality = speciality;
        this.area = area;
        this.reviewScore = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getReview() {
        return reviewScore;
    }

    public void setReview(int review) {
        this.reviewScore = review;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
