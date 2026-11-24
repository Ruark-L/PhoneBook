package model;


public class WorkContact extends ProfessionalContact {
    public WorkContact(String contactname, String address, String number, String companyOrRole) {
        super(contactname, address, number, companyOrRole);
    }


    @Override
    public String getCategory() {
        return "Work";
    }
}
