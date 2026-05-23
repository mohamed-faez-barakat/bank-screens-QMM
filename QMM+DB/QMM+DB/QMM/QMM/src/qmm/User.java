
package qmm;


public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String country;
    private String role;
    private double balance;
    private int points;
private String lastLogin;
    public User(int id, String firstName, String lastName, String email,
                String username,String password, String country, String role,
                double balance, int points,String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password= password;
        this.country = country;
        this.role = role;
        this.balance = balance;
        this.points = points;
        this.lastLogin = lastLogin;
    }

    
    
    
    public User(int id, String firstName, String lastName,String username, String email,
                double balance, int points) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.balance = balance;
        this.points = points;
       }

    
    
    
    
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCountry() { return country; }
    public String getRole() { return role; }
    public double getBalance() { return balance; }
    public int getPoints() { return points; }
  public String getLastLogin() {return lastLogin;}

   public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
  public void setPoints(int points){
      this.points=points;
  }
  
}
