package pl.coderslab.model;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean superadmin;
    private boolean enable;
    public Admin(){

    }
    public Admin(int id, String firstName, String lastName, String email, String password, boolean superadmin, boolean enable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", superadmin=" + superadmin +
                ", enable=" + enable +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuperadmin(boolean superadmin) {
        this.superadmin = superadmin;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuperadmin() {
        return superadmin;
    }

    public boolean isEnable() {
        return enable;
    }
}
