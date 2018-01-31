package com.dummies.util;

/**
 * @author taumal
 * @since 12/16/17 3:53 PM
 */
public class EncryptionPair {
    private String hashValue;
    private String salt;

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
