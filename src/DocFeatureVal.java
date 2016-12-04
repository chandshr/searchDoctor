/**
 * Created by chandani on 12/4/16.
 */
public class DocFeatureVal {
    private String key;
    private Object val;

    public DocFeatureVal(String k, Object v){
        this.key = k;
        this.val = v;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
