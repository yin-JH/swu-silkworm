package cn.edu.swu.entity;

public class Account {
    private Long id;
    private String accountName;
    private String accountPassword;

    public Account() {
    }

    public Account(Long id, String accountName, String accountPassword) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                '}';
    }
}
