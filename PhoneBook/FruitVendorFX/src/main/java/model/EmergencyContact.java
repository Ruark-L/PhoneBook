package model;


public class EmergencyContact extends ProfessionalContact {
    public EmergencyContact(String contactname, String address, String number, String serviceOrRelation) {
        super(contactname, address, number, serviceOrRelation);
    }


    @Override
    public String getCategory() {
        return "Emergency";
    }
}
