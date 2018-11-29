package com.cafedelear.aksha.cafedelear.Model;

public class Category_model {

    private String cat_id;
    private String cat_name;
    private String url;
    private String cat_visible;
    private String delear_id;

    public Category_model() {
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCat_visible() {
        return cat_visible;
    }

    public void setCat_visible(String cat_visible) {
        this.cat_visible = cat_visible;
    }

    public String getDelear_id() {
        return delear_id;
    }

    public void setDelear_id(String delear_id) {
        this.delear_id = delear_id;
    }
}
