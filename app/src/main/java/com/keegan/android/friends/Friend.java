package com.keegan.android.friends;

public class Friend {
    private int id;
    private String first_name;
    private String last_name;
    private String email;

    public Friend(int newID, String newFname, String newLname, String newEmail){
        setId(newID);
        setFirst_name(newFname);
        setLast_name(newLname);
        setEmail(newEmail);
    }

    public void setId(int newID){id = newID;}
    public void setFirst_name(String newFname){first_name = newFname;}
    public void setLast_name(String newLname){last_name = newLname;}
    public void setEmail(String newEmail){email = newEmail;}

    public int getId(){return id;}
    public String getFirst_name(){return first_name;}
    public String getLast_name(){return last_name;}
    public String getEmail(){return email;}
}
