package lnmiit.android.app.model;

/**
 * Created by dexter on 15/12/16.
 */
public class ImageDetail {
    private int id;
    private String title;

    ImageDetail() {

    }

    public ImageDetail(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }
    public String getTitle(){
        return title ;
    }
}
