package App;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Account {
    private final DoubleProperty balance = new SimpleDoubleProperty();
    private final IntegerProperty numberOfOrders = new SimpleIntegerProperty(0);
    private static int id = 0;
    private final int accountId;
    private static final List<Account> accountList = new ArrayList<>();
    private final List<Order> accountAssets = new ArrayList<>();


    public int getAccountId() {
        return this.accountId;
    }

    public int getNumberOfOrders() {
        return this.numberOfOrders.get();
    }

    public IntegerProperty numberOfOrdersProperty() {
        return this.numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders.set(numberOfOrders);
    }


    public Integer getAvailableStockAssets(String companySymbol) {
        for (Order order : this.accountAssets) {
            System.out.println(order.getOwnedCompanySymbols() + "23");
            if (order.getOwnedCompanySymbols().equals(companySymbol)) {
                return (order.getNumberOfOwnedStocks());
            }
        }
        //Never Reached
        return (0);
    }

    public void updateAccountAssets(Order order) {
        this.accountAssets.add(order);
    }

    public void updateAccountAssets(String companySymbol, Integer numberOfStocks) {
        for (Order ownedAsset : this.accountAssets) {
            if (companySymbol.equals(ownedAsset.getOwnedCompanySymbols())) {
                ownedAsset.setNumberOfOwnedStocks(ownedAsset.getNumberOfOwnedStocks() - numberOfStocks);
            }
        }
        updateAccountAssets();
    }

    public void updateAccountAssets() {
        OrderController.ownedAssetsObservableList.removeIf(ownedAsset -> ownedAsset.getNumberOfOwnedStocks().equals(0));
        this.accountAssets.removeIf(ownedAssets -> ownedAssets.getNumberOfOwnedStocks().equals(0));
    }

    public ObservableList<String> retreiveAccountAssets() {
        ObservableList<String> ownedSymbols = FXCollections.observableArrayList();
        for (Order order : this.accountAssets) {
            ownedSymbols.add(order.getOwnedCompanySymbols());
        }
        return (ownedSymbols);
    }


    public static void setUserBalanceUsingIndex(int id) {

        System.out.println(getUserBalanceUsingIndex(id));
        accountList.get(id - 1).setBalance(getUserBalanceUsingIndex(id) - 10);
        System.out.println(id );
        System.out.println(getUserBalanceUsingIndex(id));
    }

    public static double getUserBalanceUsingIndex(int id) {
       return  (accountList.get(id - 1).getBalance());
    }
    /**
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    /**
     * @return
     */
    public double getBalance() {
        return this.balance.get();
    }

    /**
     * @return
     */
    public DoubleProperty balanceProperty() {
        return this.balance;
    }

    public Account() {
        this.accountId = Account.id++;
        this.balance.setValue(0.0);
        Account.accountList.add(this);
        AccountController.account = this;
    }

    public Account(double balance) {
        this.accountId = Account.id++;
        this.balance.setValue(balance);
        Account.accountList.add(this);
        AccountController.account = this;
    }

    /**
     * @return
     * @throws IOException
     */
    public static Scene getAccountScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AccountController.class.getResource("Account.fxml"));
        return (new Scene(fxmlLoader.load()));
    }

    /**
     * @param userIndex
     */
    public static void setUserAccount(int userIndex) {
        try{
            AccountController.account = accountList.get(userIndex - 1);
        }
        catch (Exception e ){
            new Account(100);
        }
    }

}
