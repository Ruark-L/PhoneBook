package model;


public abstract class PersonalContact extends Contact {
    private String personalNote;


    public PersonalContact(String contactname, String address, String number, String personalNote) {
        super(contactname, address, number);
        this.personalNote = personalNote;
    }


    public String getPersonalNote() { return personalNote; }
    public void setPersonalNote(String personalNote) { this.personalNote = personalNote; }


    @Override
    public String getExtraDetail() {
        return personalNote;
    }


    @Override
    public void setExtraDetail(String detail) {
        this.personalNote = detail;
    }
}
