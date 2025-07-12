package com.group4.data.model;

public class Partner {
    int partnerLogo;
    String partnerName;

    // Constructor
    public Partner(int partnerLogo, String partnerName) {
        this.partnerLogo = partnerLogo;
        this.partnerName = partnerName;
    }

    //Getter & Setter
    public int getPartnerLogo() {
        return partnerLogo;
    }

    public void setPartnerLogo(int partnerLogo) {
        this.partnerLogo = partnerLogo;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}
