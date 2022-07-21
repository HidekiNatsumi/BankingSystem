package BankingSystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("First Page\nRegister\nLog in\nExit");
    }
    public static void menuEmployee(){
        System.out.println("Add a customer\nSearch and check customer's account\nCheck transaction history\nLog out");
    }
    public static void menuCustomer(){
        System.out.println("Deposit funds\nWithdraw money\nCheck Balance\nTransfer Money\nLog out");
    }
    public static void main(String[] args) throws IOException {
        Main main =new Main();
        BankMethods bank = new BankMethods();
        bank.createFiles();
        boolean runApp=true;
while(runApp){

    Main.menu();
bank.createFiles();//for first time use
    Scanner choice = new Scanner(System.in);
    int ch =choice.nextInt();

    switch (ch) {
        case 1 -> {
            System.out.println("---------------------------------------");
            System.out.println("Information\nExchange Rate and calculations");
            System.out.println("---------------------------------------");
            int decide=choice.nextInt();
            if(decide==1){
                bank.informationPage();
                System.out.println("---------------------------------------");
            }
        if(decide==2){
            double euro=1.05;
            double dollar=1;
            double lek=110.5;
            double yen=137;
            System.out.println("1 USDollar is converted to "+euro+" Euros , "+lek+" ALLek, "+yen+" Yen ");
            System.out.println("---------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("Calculate exchange rates\nexit ");
            System.out.println("---------------------------------------");
            int decide1=sc.nextInt();
            if(decide1==1){
                System.out.println("---------------------------------------");
                System.out.println("Exchange USDollar with\nEuro\nALLek\nYen");
                System.out.println("---------------------------------------");
                double choose = sc.nextDouble();
                if(choose==1){
                    System.out.println("1 USDollar is converted to "+euro+" Euros ");
                    double choose1 = sc.nextDouble();
                    choose1*=euro;
                    System.out.println(choose1+" Euros");
                    System.out.println("---------------------------------------");
                }
                if(choose==2){
                    System.out.println("1 USDollar is converted to "+lek+" ALLek ");
                    double choose1 = sc.nextDouble();
                    choose1*=lek;
                    System.out.println(choose1+" Lek");
                    System.out.println("---------------------------------------");
                }
                if(choose==3){
                    System.out.println("---------------------------------------");
                    System.out.println("1 USDollar is converted to "+yen+" Yen ");
                    double choose1 = sc.nextDouble();
                    choose1*=yen;
                    System.out.println(choose1+" Yen");
                    System.out.println("---------------------------------------");
                }
            }
            if(decide1==2){
                break;
            }
            System.out.println("---------------------------------------");
        }
        }
        case 2 -> {

            bank.registerEmp();
            System.out.println("---------------------------------------");
        }
        case 3 -> {
            System.out.println("log in");

            bank.login();
            if (!bank.getEmpFile().exists()) {
                System.out.println("You haven't signed up!");
                System.out.println("---------------------------------------");
                break;
            }
            while(true) {
                if(!bank.isAccExists()){
                    break;
                }
                if (bank.isAccExists()) {
                    if(bank.getAccount().equals("employee")){
                        System.out.println();
                        Main.menuEmployee();

                        Scanner emp=new Scanner(System.in);
                        int decide =emp.nextInt();
                        if(decide==1) {
                            bank.registerCustomer();
                            System.out.println("---------------------------------------");
                            continue;
                        }

                        if(decide==2) {
                            System.out.println("Select the customer");
                            bank.checkCustomersId();
                            int c=emp.nextInt();
                            bank.selectCustomer(c);

                            System.out.println("Choose an option");
                            System.out.println("Check Balance\nCheck Transactions");
                            int c1=emp.nextInt();
                            bank.checkCustomerAccount(c1);
                            continue;
                        }
                        if(decide==3) {
                            bank.transactionHistory();
                            continue;
                        }
                        if(decide==4){
                            System.out.println("You have logged out " + bank.getName()+" "+bank.getLastname());
                            System.out.println("---------------------------------------");
                            bank.delTempVars();
                            break;
                        }
                    }
                    if(bank.getAccount().equals("customer")){
                        System.out.println("---------------------------------------");

                        Main.menuCustomer();
                        Scanner sc=new Scanner(System.in);
                        int cus=sc.nextInt();
                        if(cus==1){
                            bank.deposit();
                            continue;
                        }
                        if(cus==2){
                            bank.withdraw();
                            continue;
                        }
                        if(cus==3){
                            bank.checkBalance();
                            System.out.println("Your balance is "+bank.getBalance()+"$");
                            continue;
                        }
                        if(cus==4){
                            System.out.println("Transfer money to other people\n(id of the person and the amount of money you want to transfer");
                            Scanner sc1= new Scanner(System.in);
                            int idd=sc1.nextInt();
                            double money=sc1.nextDouble();
                           bank. transferMoney(idd,money);
                            continue;
                        }
                        if(cus==5){
                            System.out.println("You have logged out " + bank.getName()+" "+bank.getLastname());
                            System.out.println("---------------------------------------");
                            bank.delTempVars();
                            break;
                        }

                        break;
                    }
                }
                break;
            }

        }
        case 4 -> {
            System.out.println("Goodbye");
           runApp=false;
        }
    }
}
    }
}
/*
-information
-Register (employee) ->name last name password
-login(logins as customer/employee)->id code and password(customers)/name and password for employees
---------------------------------------
when logged in as an employee you have the option to:
-add a customer account
-see the transaction history of a customer
-see the deposits of a customer
-confirm bank loans(future)
when logged in as a customer you have the option to:
-add funds (deposit)money in your account
-withdraw money from your account
-request bank loans(future)
-convert money(future)
 */
