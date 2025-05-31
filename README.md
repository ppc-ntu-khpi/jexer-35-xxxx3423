[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)]
# UI Lab 1
![](terminal-icon.png)
![](gui-icon.png)


В ході роботи виконав **наступні завдання**:
1. Завантажив jar-файл Jexer з цього ж репозиторію
2. Створив новий проект.
3. Додав до проекту бібліотеку Jexer.
4. Додав до проекту файл TUIdemo.java з цього репозиторію.

Зробив завдання на 4 бали, а саме:
1. Переписав метод ShowCustomerDetails з використанням класів Bank, Customer, Account та ін.

````java
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
 ````

Результат:
![image](https://github.com/ppc-ntu-khpi/35-tui-1-angelxdem/assets/113301385/94f01381-25f2-462c-8678-4b5b264d3f6b)
![image](https://github.com/ppc-ntu-khpi/35-tui-1-angelxdem/assets/113301385/bfe6d2e2-aaa9-4e39-ad68-a545c9a8a2c8)


![](https://img.shields.io/badge/Made%20with-JAVA-red.svg)
![](https://img.shields.io/badge/Made%20with-%20Netbeans-brightgreen.svg)
![](https://img.shields.io/badge/Made%20at-PPC%20NTU%20%22KhPI%22-blue.svg) 
