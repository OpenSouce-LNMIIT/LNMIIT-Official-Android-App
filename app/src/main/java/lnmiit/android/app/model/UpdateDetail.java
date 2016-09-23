package lnmiit.android.app.model;

/**
 * Created by dexter on 22/9/16.
 */
public class UpdateDetail {
    public UpdateDetail(String url, String title){
        this.url = url ;
        this.title = title ;

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String url ,title ;
}

