package lnmiit.android.app.model;

/**
 * Created by chanpreet on 26/11/16.
 */

public class EmergencyDetails {
    private String emergencyName;
    private String phone;

    public EmergencyDetails(String name, String phone) {
        this.emergencyName = name;
        this.phone = phone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String name) {
        this.emergencyName = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
