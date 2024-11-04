import java.util.*;

class Transaction {
    String id, description;
    Date date;
    double amountInDollars;

    Transaction(String id, String description, Date date, double amountInDollars) {
        this.id = id; this.description = description; this.date = date; this.amountInDollars = amountInDollars;
    }
}

class TransactionSystem {
    List<Transaction> transactions = new ArrayList<>();
    void addTransaction(Transaction t) { transactions.add(t); }
    List<Transaction> getTransactions() { return transactions; }
}

class ExchangeRateAPI {
    static double getExchangeRate(Date date, String targetCurrency) { return 1.0; } // Fixed rate for USD
}

class TransactionService {
    TransactionSystem system;

    TransactionService(TransactionSystem system) { this.system = system; }

    void retrieveTransaction(String id, String targetCurrency) {
        for (Transaction t : system.getTransactions()) {
            if (t.id.equals(id)) {
                double rate = ExchangeRateAPI.getExchangeRate(t.date, targetCurrency);
                double convertedAmount = t.amountInDollars * rate;
                System.out.printf("ID: %s, Description: %s, Date: %s, USD: %.2f, Rate: %.2f, Converted: %.2f%n", 
                                  t.id, t.description, t.date, t.amountInDollars, rate, convertedAmount);
                return;
            }
        }
        System.out.println("Transaction not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        TransactionSystem system = new TransactionSystem();
        system.addTransaction(new Transaction("1", "Sample Purchase", new Date(), 100.0));
        new TransactionService(system).retrieveTransaction("1", "USD");
    }
}
