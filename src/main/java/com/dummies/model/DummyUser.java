package com.dummies.model;


import com.dummies.util.Utils;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * @author taumal
 * @since 1/31/18 6:50 PM
 */

@SuppressWarnings({"UnusedDeclaration"})
@Entity
@Table(name = "`dummy_user`")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class DummyUser extends Persistent{
    public enum Status {
        ACTIVE(1), INACTIVE(0);
        private Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20, name = "full_name")
    private String fullName;

    private String email;

    @Column(nullable = false, length = 20, name = "username")
    private String userName;

    @Transient
    private String password;

    @Transient
    private String password2;

    @Column(nullable = false, length = 40, name = "password_hash")
    private String passwordHash;

    @Column(nullable = false, length = 10, name = "password_salt")
    private String passwordSalt;

    private String country;

    @Column(nullable = false)
    private int status;

    public DummyUser() {
    }

    public DummyUser(long id, long id1) {
        super(id);
        this.id = id1;
    }

    public DummyUser(long id, long id1, String fullName) {
        super(id);
        this.id = id1;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DummyUser user = (DummyUser) o;

        if (id != user.id) return false;
        if (status != user.status) return false;
        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + status;
        return result;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        if (Utils.isNotEmpty(password)) {
            this.password = password;
        }
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return status == 1 ? "Active" : "Inactive";
    }

    public boolean isStrongPassword() {
        final String password = this.getPassword() == null ? "" : this.getPassword();
        return password.matches("(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
    }
}
