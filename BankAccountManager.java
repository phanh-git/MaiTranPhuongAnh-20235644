import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private Date date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date(); // Current date
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}

class BankAccount {
    private String accountHolder;
    private double balance;
    private double monthlyInterestRate;
    private List<Transaction> transactionHistory;
    private int withdrawalCount;

    public BankAccount(String accountHolder, double initialBalance, double monthlyInterestRate) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.monthlyInterestRate = monthlyInterestRate;
        this.transactionHistory = new ArrayList<>();
        this.withdrawalCount = 0;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposited: " + amount + " VND");
    }

    public void withdraw(double amount) {
        if (withdrawalCount < 3) {
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add(new Transaction("Withdrawal", amount));
                withdrawalCount++;
                System.out.println("Withdrawn: " + amount + " VND");
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("Maximum withdrawal limit exceeded for the month.");
        }
    }

    public void applyMonthlyInterest() {
        double interest = balance * monthlyInterestRate / 100;
        balance += interest;
        transactionHistory.add(new Transaction("Interest", interest));
        System.out.println("Interest applied: " + interest + " VND");
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + accountHolder + ":");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class BankAccountManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accountHolder = "Nguyen Van A";
        double initialBalance = 5000000; 
        double monthlyInterestRate = 1.0; 

        BankAccount account = new BankAccount(accountHolder, initialBalance, monthlyInterestRate);

        account.deposit(1000000);
        account.withdraw(500000);
        account.withdraw(500000);
        

        System.out.println("Current balance: " + account.getBalance() + " VND");
        
        account.applyMonthlyInterest();

        System.out.println("Current balance: " + account.getBalance() + " VND");

        account.printTransactionHistory();
    }
}
