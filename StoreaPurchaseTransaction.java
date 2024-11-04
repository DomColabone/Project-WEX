import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String description;
    private LocalDate date;
    private double amount;
    private int id;

    public Transaction(String description, LocalDate date, double amount, int id) {
        this.description = description; this.date = date; this.amount = amount; this.id = id;
    }
    
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public int getId() { return id; }
}

class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private int idCounter = 1;

    public Transaction storeTransaction(String desc, String dateStr, double amount) {
        if (desc.length() > 50 || amount <= 0 || Math.round(amount * 100) != amount * 100) {
            throw new IllegalArgumentException("Dados inválidos");
        }
        LocalDate date;
        try { date = LocalDate.parse(dateStr); }
        catch (DateTimeParseException e) { throw new IllegalArgumentException("Data inválida"); }
        Transaction transaction = new Transaction(desc, date, amount, idCounter++);
        transactions.add(transaction);
        return transaction;
    }
}

public class Main {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        try {
            Transaction t = manager.storeTransaction("Compra de supermercado", "2024-10-31", 25.99);
            System.out.println("Transação: " + t.getDescription() + ", Data: " + t.getDate() +
                               ", Valor: " + t.getAmount() + ", ID: " + t.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
