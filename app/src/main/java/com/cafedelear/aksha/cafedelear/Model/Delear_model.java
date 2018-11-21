package com.cafedelear.aksha.cafedelear.Model;

public class Delear_model {

    private String delear_id;
    private String delear_name;
    private String delear_phoneno;
    private String delear_email;
    private String delear_password;
    private String gender;
    private String profile;
    private String delear_registerdate;


    public Delear_model() {

    }

    public Delear_model(String delear_id, String delear_name, String delear_email) {
        this.delear_id = delear_id;
        this.delear_name = delear_name;
        this.delear_email = delear_email;
    }

    public String getDelear_id() {
        return delear_id;
    }

    public void setDelear_id(String delear_id) {
        this.delear_id = delear_id; }

    public String getDelear_name() {
        return delear_name;
    }

    public void setDelear_name(String delear_name) {
        this.delear_name = delear_name;
    }

    public String getDelear_phoneno() {
        return delear_phoneno;
    }

    public void setDelear_phoneno(String delear_phoneno) {
        this.delear_phoneno = delear_phoneno;
    }

    public String getDelear_email() {
        return delear_email;
    }

    public void setDelear_email(String delear_email) {
        this.delear_email = delear_email;
    }

    public String getDelear_password() {
        return delear_password;
    }

    public void setDelear_password(String delear_password) {
        this.delear_password = delear_password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDelear_registerdate() {
        return delear_registerdate;
    }

    public void setDelear_registerdate(String delear_registerdate) {
        this.delear_registerdate = delear_registerdate;
    }

}
