package me.w1992wishes.webgrowth.chapter04.model;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public class Customer {
    private long id;
    private String name;
    private String contact;
    private String telephone;
    private String email;
    private String remark;
    private String photo;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
