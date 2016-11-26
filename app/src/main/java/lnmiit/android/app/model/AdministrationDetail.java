package lnmiit.android.app.model;

/**
 * Created by chanpreet on 25/11/16.
 */

public class AdministrationDetail {
    private String adminName;
    private String designation;
    private String mail;

    public AdministrationDetail(String name, String designation, String mail) {
        this.adminName = name;
        this.designation = designation;
        this.mail = mail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String name) {
        this.adminName = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
