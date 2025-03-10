// Base class: BankAccount
class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method (Overridable)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    // Check balance
    public void checkBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: " + balance);
    }

    // Getter for balance (for subclasses)
    protected double getBalance() {
        return balance;
    }

    // Setter for balance (for subclasses)
    protected void setBalance(double balance) {
        this.balance = balance;
    }
}

// Subclass: SavingsAccount (inherits from BankAccount)
class SavingsAccount extends BankAccount {
    private double interestRate;

    // Constructor
    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        super(accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    // Method to add interest
    public void addInterest() {
        double interest = (interestRate / 100) * getBalance();
        deposit(interest);
        System.out.println("Interest added: " + interest);
    }
}

// Subclass: CurrentAccount (inherits from BankAccount)
class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountHolder, double initialBalance, double overdraftLimit) {
        super(accountHolder, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    // Overriding withdraw method
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (getBalance() + overdraftLimit)) {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn successfully.");
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

    public static void main(String[] args) {
        // Creating a Savings Account
        SavingsAccount savings = new SavingsAccount("Alice", 1000, 5);
        savings.deposit(500);
        savings.checkBalance();
        savings.addInterest();
        savings.checkBalance();

        System.out.println("---------------------------");

        // Creating a Current Account
        CurrentAccount current = new CurrentAccount("Bob", 2000, 1000);
        current.withdraw(2500);
        current.checkBalance();
    }
}
