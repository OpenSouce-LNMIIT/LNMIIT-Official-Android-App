package lnmiit.android.app.model;

/**
 * Created by dexter on 21/8/16.
 */
public class FacultyDetails {

    /*
     Faculty Basic information
     */
    private String name , designation , email , phone , url ;


    /* Constructor */
    public FacultyDetails(String name , String designation , String url , String email , String phone){
        this.name = name;
        this.designation = designation;
        this.url = url ;
        this.email = email ;
        this.phone = phone ;
    }

    /*Getters
     */
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }
}
