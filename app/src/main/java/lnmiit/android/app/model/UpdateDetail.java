package lnmiit.android.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dexter on 22/9/16.
 */
public class UpdateDetail implements Parcelable {
    public UpdateDetail(String url, String title){
        this.url = url ;
        this.title = title ;

    }

    protected UpdateDetail(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<UpdateDetail> CREATOR = new Creator<UpdateDetail>() {
        @Override
        public UpdateDetail createFromParcel(Parcel in) {
            return new UpdateDetail(in);
        }

        @Override
        public UpdateDetail[] newArray(int size) {
            return new UpdateDetail[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(title);
    }

    @Override
    public String toString() {
        return url + " " + title;
    }
}

