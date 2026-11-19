package model;


public class FriendContact extends PersonalContact {
    public FriendContact(String contactname, String address, String number, String birthdayOrNickname) {
        super(contactname, address, number, birthdayOrNickname);
    }


    @Override
    public String getCategory() {
        return "Friend";
    }
}
