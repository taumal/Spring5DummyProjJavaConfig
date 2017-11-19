package com.dummies.model;

import java.io.Serializable;

/**
 * @author taumal
 * @since 11/14/17 7:31 PM
 */
public class Dummy implements Serializable{

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    private String country;

    public Dummy() {
    }

    public Dummy(Integer id) {
        this.id = id;
    }

    public Dummy(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Dummy(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dummy dummy = (Dummy) o;

        if (!id.equals(dummy.id)) return false;
        if (firstName != null ? !firstName.equals(dummy.firstName) : dummy.firstName != null) return false;
        if (lastName != null ? !lastName.equals(dummy.lastName) : dummy.lastName != null) return false;
        if (!email.equals(dummy.email)) return false;
        if (!userName.equals(dummy.userName)) return false;
        if (!password.equals(dummy.password)) return false;
        return country != null ? country.equals(dummy.country) : dummy.country == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Dummy{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
