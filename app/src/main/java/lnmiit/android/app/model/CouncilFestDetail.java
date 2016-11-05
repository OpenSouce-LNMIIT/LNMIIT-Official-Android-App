package lnmiit.android.app.model;

/**
 * Created by Chanpreet on 16-09-2016.
 */
public class CouncilFestDetail {

    private String name;
    private String description;

    public CouncilFestDetail(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
