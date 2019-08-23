package me.kazaff.tools.Utils;

public class CipherData {
    public String aes_part;
    public String rsa_part;

    public CipherData() {}

    public CipherData(String aes_part, String rsa_part) {
        this.aes_part = aes_part;
        this.rsa_part = rsa_part;
    }

    public String getAesPart() {
        return aes_part;
    }

    public void setAesPart(String aes_part) {
        this.aes_part = aes_part;
    }

    public String getRsaPart() {
        return rsa_part;
    }

    public void setRsaPart(String rsa_part) {
        this.rsa_part = rsa_part;
    }
}
