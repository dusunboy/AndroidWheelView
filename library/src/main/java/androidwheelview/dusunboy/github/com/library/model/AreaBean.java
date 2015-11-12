
package androidwheelview.dusunboy.github.com.library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AreaBean {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("area")
    @Expose
    private List<AreaBean> area = new ArrayList<AreaBean>();

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The area
     */
    public List<AreaBean> getArea() {
        return area;
    }

    /**
     *
     * @param area
     *     The area
     */
    public void setArea(List<AreaBean> area) {
        this.area = area;
    }

}
