package model;


public abstract class Contact {
    private String contactname;
    private String address;
    private String number;


    public Contact(String contactname, String address, String number) {
        this.contactname = contactname;
        this.address = address;
        this.number = number;
    }


    public String getContactname() { return contactname; }
    public void setContactname(String contactname) { this.contactname = contactname; }


    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }


    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }


    // Abstract methods implemented across subclasses
    public abstract String getCategory();
    public abstract String getExtraDetail();
    public abstract void setExtraDetail(String detail);


    @Override
    public String toString() {
        return String.format("[%s] %-12s | %-15s | %-13s | %s",
                getCategory(), contactname, address, number, getExtraDetail());
    }
}
