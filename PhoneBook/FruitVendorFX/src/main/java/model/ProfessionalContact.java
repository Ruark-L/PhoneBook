package model;


public abstract class ProfessionalContact extends Contact {
    private String organization;


    public ProfessionalContact(String contactname, String address, String number, String organization) {
        super(contactname, address, number);
        this.organization = organization;
    }


    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }


    @Override
    public String getExtraDetail() {
        return organization;
    }


    @Override
    public void setExtraDetail(String detail) {
        this.organization = detail;
    }
}
