package e.inspiron.e_commercechallenge;

public class User {
    private String username, email;

    public User() {
    }
public User  (String email){
        this.email=email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, String username) {

        this.username = username;
        this.email = email;
    }



    public String getEmail() {
        return email;
    }

}
