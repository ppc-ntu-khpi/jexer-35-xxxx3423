package com.mybank.tui;

import com.mybank.domain.Account;
import com.mybank.domain.Bank;
import com.mybank.domain.Customer;
import com.mybank.domain.SavingsAccount;
import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TText;
import jexer.TWindow;
import jexer.event.TMenuEvent;
import jexer.menu.TMenu;

public class TUIDemo extends TApplication  {
    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;

    public static void main(String[] args) throws Exception {
        TUIDemo tdemo = new TUIDemo();
        (new Thread(tdemo)).start();
    }


    public TUIDemo() throws Exception {
        super(TApplication.BackendType.SWING);

        addToolMenu();
        //custom 'File' menu
        TMenu fileMenu = addMenu("&File");
        fileMenu.addItem(CUST_INFO, "&Customer Info");
        fileMenu.addDefaultItem(TMenu.MID_SHELL);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(TMenu.MID_EXIT);
        //end of 'File' menu

        addWindowMenu();

        //custom 'Help' menu
        TMenu helpMenu = addMenu("&Help");
        helpMenu.addItem(ABOUT_APP, "&About...");
        //end of 'Help' menu

        setFocusFollowsMouse(true);

        Bank.addCustomer("Liker", "Don");
        Bank.getCustomer(0).addAccount(new SavingsAccount(4234.0, 0.04));
        Bank.addCustomer("Kilet", "Jame");
        Bank.getCustomer(1).addAccount(new SavingsAccount(2339.0, 0.05));
        Bank.addCustomer("Robin", "Elas");
        Bank.getCustomer(2).addAccount(new SavingsAccount(7984.0, 0.01));
        //Customer window
        ShowCustomerDetails();
    }

    @Override
    protected boolean onMenu(TMenuEvent menu) {
        if (menu.getId() == ABOUT_APP) {
            messageBox("About", "\t\t\t\t\t   Just a simple Jexer demo.\n\nCopyright \u00A9 2023 Angel").show();
            return true;
        }
        if (menu.getId() == CUST_INFO) {
            ShowCustomerDetails();
            return true;
        }
        return super.onMenu(menu);
    }

    private void ShowCustomerDetails() {
        TWindow custWin = addWindow("Customer Window", 2, 1, 40, 10, TWindow.NOZOOMBOX);
        custWin.newStatusBar("Enter valid customer number and press Show...");

        custWin.addLabel("Enter customer number: ", 2, 2);
        TField custNo = custWin.addField(24, 2, 3, false);
        TText details = custWin.addText("", 2, 4, 38, 8);

        custWin.addButton("&Show", 28, 2, new TAction() {
            @Override
            public void DO() {
                try {
                    int custNum = Integer.parseInt(custNo.getText());
                    if (custNum >= 0 && custNum < Bank.getNumberOfCustomers()) {
                        Customer customer = Bank.getCustomer(custNum);

                        String ownerName = customer.getFirstName() + " " + customer.getLastName();
                        String accountType = "";
                        double balance = 0.0;

                        if (customer.getNumberOfAccounts() > 0) {
                            Account account = customer.getAccount(0);
                            accountType = account.getClass().getSimpleName();
                            balance = account.getBalance();
                        }

                        details.setText("Owner Name: " + ownerName + " (id=" + custNum + ")\n"
                                + "Account Type: '" + accountType + "'\n"
                                + "Account Balance: $" + balance);

                    } else {
                        messageBox("Error", "Invalid customer number!").show();
                    }
                } catch (NumberFormatException e) {
                    messageBox("Error", "You must provide a valid customer number!").show();
                }
            }
        });
    }
}

