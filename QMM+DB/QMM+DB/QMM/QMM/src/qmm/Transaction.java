
package qmm;


public class Transaction {
    private final int id;
    private final int userId;
    private final String type;
    private final double amount;
    private final int targetUserId;
    private final String time;

    public Transaction(int id, int userId, String type, double amount, int targetUserId, String time) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.targetUserId = targetUserId;
        this.time = time;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public int getTargetUserId() { return targetUserId; }
    public String getTime() { return time; }
}
