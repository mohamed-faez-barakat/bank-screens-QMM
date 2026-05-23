
package qmm;



public class Feedback {
    private final int id;
    private final String username;
    private final String message;
    private final String submittedAt;

    public Feedback(int id, String username, String message, String submittedAt) {
        this.id = id;
        this.username =username;
        this.message =message;
        this.submittedAt =submittedAt;
    }

    public int getId() { return id;}
    public String getUsername() { return username; }
    public String getMessage() { return message; }
    public String getSubmittedAt() { return submittedAt; }
}

