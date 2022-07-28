package BankingSystem;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BankMethods {
    private String name;
    private String lastname;
    private String password;
    private int id;
    private int customerAmount;
    private File empFile = new File("bankList.txt");
    private File savingsFile = new File("bankSavings.txt");
    private File transactionHistory = new File("transactionHistory.txt");
    private int empfileSize = 0;
    private String account;
    private boolean accExists = false;
    private double balance;
    private String[] customers;
    private int CustomerIdOnUseByEmployee;
    private String[]employees;


    public String[] getEmployees() {
        return employees;
    }

    public void setEmployees(String[] employees) {
        this.employees = employees;
    }

    public int getCustomerIdOnUseByEmployee() {
        return CustomerIdOnUseByEmployee;
    }

    public String[] getCustomers() {
        return customers;
    }

    public void setCustomerIdOnUseByEmployee(int customerIdOnUseByEmployee) {
        CustomerIdOnUseByEmployee = customerIdOnUseByEmployee;
    }

    public void setCustomers(String[] customers) {
        this.customers = customers;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public File getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(File transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public File getSavingsFile() {
        return savingsFile;
    }

    public void setSavingsFile(File savingsFile) {
        this.savingsFile = savingsFile;
    }

    public boolean isAccExists() {
        return accExists;
    }

    public void setAccExists(boolean accExists) {
        this.accExists = accExists;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getEmpfileSize() {
        return empfileSize;
    }

    public void setEmpfileSize(int empfileSize) {
        this.empfileSize = empfileSize;
    }

    public File getEmpFile() {
        return empFile;
    }

    public void setEmpFile(File empFile) {
        this.empFile = empFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(int customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
public void createFiles() throws IOException {

    if (!transactionHistory.exists()) {
        File tempfile = new File("transactionHistory.txt");
        FileWriter fw=new FileWriter(tempfile);
        fw.append("");
        fw.close();
    }

    if (!empFile.exists()) {
        File tempfile2 = new File("bankList.txt");
        FileWriter fw=new FileWriter(tempfile2);
        fw.append("");
        fw.close();
    }
    if (!savingsFile.exists()) {
        File tempfile1= new File("bankSavings.txt");
        FileWriter fw=new FileWriter(tempfile1);
        fw.append("0000 0");//the bank's profile that takes all the profit from the interest it gets on each transaction
        fw.close();
    }
    if (!messageFile.exists()) {
        File messageFile =new File("messages.txt");
        FileWriter fw=new FileWriter(messageFile);
        fw.close();
    }
    
}
    public void fileLength(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        empfileSize = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            empfileSize++;
        }
        sc.close();
    }

    public void registerEmp() throws IOException {
        fileLength(empFile);
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Name");
        name = scan.nextLine();
        System.out.println("Surname");
        lastname = scan.nextLine();
        System.out.println("Password");
        password = scan.nextLine();
        id = rand.nextInt(1000, 9999);
        Scanner sc = new Scanner(empFile);
        String temp1, temp2, temp3, temp4, temp5;
        int tempnr;
        int count = 0;
        boolean idEx = false;
        while (sc.hasNext()) {
            temp1 = sc.next();
            temp2 = sc.next();
            temp3 = sc.next();
            temp4 = sc.next();
            temp5 = sc.next();
            tempnr = Integer.parseInt(temp4);
            count++;
            if (tempnr == id) {
                idEx = true;
                id = rand.nextInt();
                if (tempnr != id) {
                    return;
                }
            }
            if (count == empfileSize && idEx) {
                return;
            }
        }
        sc.close();
        System.out.println("Your Id is: " + id);
        FileWriter writer = new FileWriter(empFile, true);
        writer.append(name + " " + lastname + " " + password + " " + id + " " + "employee" + "\n");
        writer.close();

        System.out.println("Account Created");
    }

    public void registerCustomer() throws IOException {
        int tempID=id;
        String tempName=name;
        String templastname=lastname;
        String tempPassword=password;
        fileLength(empFile);
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Name");
        name = scan.nextLine();
        System.out.println("Surname");
        lastname = scan.nextLine();
        System.out.println("Password");
        password = scan.nextLine();
        id = rand.nextInt(1000, 9999);
        Scanner sc = new Scanner(empFile);
        String temp1, temp2, temp3, temp4, temp5;
        int tempnr;
        int count = 0;
        boolean idEx = false;
        while (sc.hasNext()) {

            temp1 = sc.next();
            temp2 = sc.next();
            temp3 = sc.next();
            temp4 = sc.next();
            temp5 = sc.next();
            tempnr = Integer.parseInt(temp4);
            count++;
            if (tempnr == id) {
                idEx = true;
                id = rand.nextInt();

                if (tempnr != id) {
                    return;
                }
            }

            if (count == empfileSize && idEx) {
                return;
            }
        }
        sc.close();
        System.out.println("Your Id is: " + id);
        FileWriter writer = new FileWriter(empFile, true);
        writer.append(name + " " + lastname + " " + password + " " + id + " " + "customer" + "\n");
        writer.close();
        FileWriter writer1 = new FileWriter(savingsFile, true);
        writer1.append(id + " " + 0 + "\n");
        writer1.close();
        System.out.println("Account Created");
        id=tempID;
        name=tempName;
        lastname=templastname;
        password=tempPassword;
    }

    public void login() throws FileNotFoundException {
        fileLength(empFile);
        Scanner sc = new Scanner(System.in);
        System.out.println("Id");
        int theId = sc.nextInt();
        System.out.println("Password");
        String pass = sc.next();
        Scanner scan = new Scanner(empFile);
        String temp1, temp2, temp3, temp4, temp5;
        int tempnr = 0;

        while (scan.hasNext()) {
            temp1 = scan.next();
            temp2 = scan.next();
            temp3 = scan.next();
            temp4 = scan.next();
            temp5 = scan.next();
            tempnr = Integer.parseInt(temp4);
            if (tempnr == theId) {
                if (temp3.equals(pass)) {
                    accExists = true;
                    id = theId;
                    name = temp1;
                    lastname = temp2;
                    password = temp3;
                    if (temp5.equals("customer")) {
                        account = "customer";
                        break;
                    }
                    if (temp5.equals("employee")) {
                        account = "employee";
                        break;
                    }

                }
            }
        }
        scan.close();
        if (accExists) {
            System.out.println("You have logged in as a " + account);
            System.out.println("Welcome back " + name + " " + lastname);
        } else {
            System.out.println("Wrong Credentials");
        }
    }

    public void delTempVars() {
        id = 0;
        name = "";
        lastname = "";
        password = "";
        account = "";
        accExists = false;
        empfileSize = 0;
        balance = 0;
    }

    public void deposit() throws IOException {
        fileLength(savingsFile);
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the amount of money you want to deposit");
        double dep = sc.nextDouble();
        String[] arr = new String[empfileSize * 2];
        String temp1;
        int count = 0;
        Scanner scan = new Scanner(savingsFile);
        int tempnr = 0;
        while (scan.hasNext()) {
            temp1 = scan.next();
            arr[count] = temp1;
            //System.out.println(arr[count]);
            count++;

        }
        scan.close();
        PrintWriter writer = new PrintWriter(savingsFile);
        writer.print("");
        writer.close();
        FileWriter fw1 = new FileWriter(savingsFile, true);
        double tempDouble = 0;
        double tempDoublebank = 0;
        ;

        for (int i = 0; i < arr.length; i++) {
            tempnr = 0;
            if (i % 2 == 0) {
                tempnr = Integer.parseInt(arr[i]);
            }


            if (id == tempnr) {
                tempDouble = Double.parseDouble(arr[i + 1]);

                // int tempNr=Integer.parseInt(arr[i+1]);
                tempDouble += dep;
                balance = tempDouble;
                String conv = String.valueOf(tempDouble);
                arr[i + 1] = conv;
                FileWriter fw2 = new FileWriter(transactionHistory, true);
                fw2.append(id + " deposit " + dep + "\n");
                fw2.close();
            }
            if (i % 2 == 0) {
                if (i == 0) {
                    tempDoublebank = Double.parseDouble(arr[i]);
                    tempDoublebank = tempDoublebank + (dep * 5 / 100);
                    String dtemp = Double.toString(tempDoublebank);
                    arr[i + 1] = dtemp;
                    //  System.out.println("doub "+tempDoublebank);
                }
                fw1.append(arr[i] + " " + arr[i + 1] + "\n");
            }
        }
        fw1.close();
    }

    public void withdraw() throws IOException {
        fileLength(savingsFile);
        Scanner sc = new Scanner(System.in);
        System.out.println("Whats the amount of money you want to withdraw");
        double withdraw = sc.nextDouble();

        String[] arr = new String[empfileSize * 2];
        String temp1;
        int count = 0;
        Scanner scan = new Scanner(savingsFile);
        int tempnr = 0;
        double tempDouble = 0;
        double tempDoublebank = 0;
        ;
        while (scan.hasNext()) {
            temp1 = scan.next();
            arr[count] = temp1;
            // System.out.println(arr[count]);
            count++;

        }
        scan.close();
        PrintWriter writer = new PrintWriter(savingsFile);
        writer.print("");
        writer.close();
        FileWriter fw1 = new FileWriter(savingsFile, true);
        for (int i = 0; i < arr.length; i++) {
            tempnr = 0;
            if (i % 2 == 0) {
                tempnr = Integer.parseInt(arr[i]);
            }


            if (id == tempnr) {
                tempDouble = Double.parseDouble(arr[i + 1]);


                if (tempDouble >= withdraw) {
                    FileWriter fw2 = new FileWriter(transactionHistory, true);
                    tempDouble -= withdraw + tempDoublebank;
                    balance = tempDouble;
                    fw2.append(id + " withdraw " + withdraw + "\n");
                    fw2.close();
                } else {
                    System.out.println("You don't have sufficient funds for this process");
                }
                String conv = Double.toString(tempDouble);
                arr[i + 1] = conv;

            }
            if (i % 2 == 0) {
                if (i == 0) {
                    tempDoublebank = Double.parseDouble(arr[i]);
                    tempDoublebank = tempDoublebank + (withdraw * 5 / 100);
                    String dtemp = Double.toString(tempDoublebank);
                    arr[i + 1] = dtemp;
                    //  System.out.println("doub "+tempDoublebank);
                }
                fw1.append(arr[i] + " " + arr[i + 1] + "\n");
            }

        }
        fw1.close();
    }

    public void checkBalance() throws FileNotFoundException {
        Scanner sc = new Scanner(savingsFile);
        String temp1, temp2;
        double tempnr, tempnr1;
        while (sc.hasNext()) {
            temp1 = sc.next();
            temp2 = sc.next();
            tempnr = Double.parseDouble(temp1);
            tempnr1 = Double.parseDouble(temp2);
            if (id == tempnr) {
                balance = tempnr1;
            }
        }

    }

    public void checkCustomersAndEmployeesId(int make ) throws FileNotFoundException {
        Scanner scan = new Scanner(empFile);
        String temp1, temp2, temp3, temp4, temp5;
        int tempnr = 0;
        int count = 0;
        int count1 = 0 ;

        while (scan.hasNext()) {
            temp1 = scan.next();
            temp2 = scan.next();
            temp3 = scan.next();
            temp4 = scan.next();
            temp5 = scan.next();
            if (temp5.equals("customer")) {
                count++;
            }
            if (temp5.equals("employee")) {
                count1++;
            }
        }
        scan.close();
        customers = new String[count];
       employees = new String[count1];
        Scanner sc = new Scanner(empFile);
        String temp11, temp22, temp33, temp44, temp55;
        int tempnr1 = 0;
        count = 0;
        count1 = 0 ;


        while (sc.hasNext()) {
            temp11 = sc.next();
            temp22 = sc.next();
            temp33 = sc.next();
            temp44 = sc.next();
            temp55 = sc.next();
            if (temp55.equals("customer")&&make==1) {
                customers[count] = temp44;
                System.out.println(count + 1 + ". " + customers[count]);
                count++;
            }
            if (temp55.equals("employee")&&make==2) {
                employees[count1] = temp44;
                System.out.println(count1 + 1 + ". " + employees[count1]);
                count1++;
            }
        }
        sc.close();
    }
public int customersLength(){
        return customers.length;
}
    public void selectCustomer(int choice){

        for (int i = 0; i < customers.length; i++) {
            if (i + 1 == choice) {
                int temp = Integer.parseInt(customers[i]);
                CustomerIdOnUseByEmployee = temp;
            }
        }
    }

    public void checkCustomerAccount(int choice) throws FileNotFoundException {

        if (choice == 1) {
            Scanner sc = new Scanner(savingsFile);
            String temp1, temp2;
            double tempnr, tempnr1;
            while (sc.hasNext()) {
                temp1 = sc.next();
                temp2 = sc.next();
                tempnr = Double.parseDouble(temp1);
                tempnr1 = Double.parseDouble(temp2);
                if (CustomerIdOnUseByEmployee == tempnr) {
                    balance = tempnr1;
                    System.out.println(CustomerIdOnUseByEmployee+" current balance is "+balance);
                    System.out.println("---------------------------------------");
                }
            }
        }
        if (choice == 2) {
            Scanner sc = new Scanner(transactionHistory);
            String temp1, temp2,temp3;
            double tempnr, tempnr1;
            System.out.println("---------------------------------------");
            while (sc.hasNext()) {
                temp1 = sc.next();
                temp2 = sc.next();
                temp3=sc.next();
                tempnr=Integer.parseInt(temp1);
                if(temp2.equals("deposit")&&CustomerIdOnUseByEmployee==tempnr){
                    System.out.println(temp1+" has deposited "+temp3+"$");
                }
                if(temp2.equals("withdraw")&&CustomerIdOnUseByEmployee==tempnr){
                    System.out.println(temp1+" has withdrawn "+temp3+"$");
                }
                if(temp2.equals("recieved")&&CustomerIdOnUseByEmployee==tempnr){
                    System.out.println(temp1+" has recieved "+temp3+"$");
                }
                if(temp2.equals("transfered")&&CustomerIdOnUseByEmployee==tempnr){
                    System.out.println(temp1+" has transfered "+temp3+"$");
                }
            }
            System.out.println("---------------------------------------");
        }
    }
    public void transactionHistory() throws FileNotFoundException {
        Scanner sc= new Scanner(transactionHistory);
        while(sc.hasNext()){
            String temp;
            temp=sc.nextLine();
            System.out.println(temp+"$");
        }
    }
    public void informationPage() throws FileNotFoundException {
        Scanner sc = new Scanner(empFile);
        String temp11, temp22, temp33, temp44, temp55;
        int tempnr1 = 0;
       int count = 0;
       int count1=0;
        while (sc.hasNext()) {
            temp11 = sc.next();
            temp22 = sc.next();
            temp33 = sc.next();
            temp44 = sc.next();
            temp55 = sc.next();
            tempnr1=Integer.parseInt(temp44);
            if (temp55.equals("customer")) {
                count++;
            }
            if (temp55.equals("employee")) {
                count1++;
            }
        }
        sc.close();
        customers=new String[count];
        employees=new String[count1];
        System.out.println("Welcome to our Bank");
        System.out.println("Our bank has "+customers.length+ " customers and "+employees.length+" employees ");
        System.out.println("Current interest rate is 5%");
        System.out.println("Contact an employee to open a bank account");
        System.out.println("The default currency used on this bank is US Dollar");

    }
    public void transferMoney(int idC,double money) throws IOException {
        checkBalance();
fileLength(savingsFile);
        Scanner sc = new Scanner(savingsFile);
        customers= new String[empfileSize*2];
        boolean idExists=false;
        String temp1, temp2;
        int count=0;
        double tempnr, tempnr1;
        while (sc.hasNext()) {
            temp1 = sc.next();
            temp2 = sc.next();
            tempnr = Integer.parseInt(temp1);
            tempnr1 = Double.parseDouble(temp2);
            if(id==idC){
                System.out.println("You cant transfer from your account to your account!");
                return;
            }
            if(idC==0)
            {
                System.out.println("You can't transfer your funds to this account!");
                return;
            }
          if(idC==tempnr){
              FileWriter fw = new FileWriter(transactionHistory,true);

              idExists=true;
              double tempo1=tempnr1+money;
              fw.append(idC+" recieved "+tempo1+"\n");
              fw.close();
              customers[count]=temp1;
              customers[count+1]=tempo1+"";
              count=count+2;
          }
          if(id==tempnr&&balance>=money){
              FileWriter fw = new FileWriter(transactionHistory,true);
              double tempo1=tempnr1-money-(money * 5 / 100);
              fw.append(id+" transfered "+tempo1+"\n");
              fw.close();
              customers[count]=temp1;
              customers[count+1]=tempo1+"";
              count=count+2;
          }
          if(id==tempnr&&balance<money){
              System.out.println("The transaction's money exceed your balance!");
              return;
          }
       if(idC!=tempnr&&id!=tempnr){
           customers[count]=temp1;
           customers[count+1]=temp2;
           count=count+2;
       }
        }
        sc.close();
        if(!idExists){
            System.out.println("This Id does not exist!");
            return;
        }
        else{
            PrintWriter writer = new PrintWriter(savingsFile);
            writer.print("");
            writer.close();
            FileWriter fw = new FileWriter(savingsFile,true);
            double tempDoublebank=0;
            for(int i = 0 ; i <customers.length;i++){
                if(i%2==0){
                    if (i == 0) {
                        tempDoublebank = Double.parseDouble(customers[i]);
                        tempDoublebank = tempDoublebank + (money * 5 / 100);
                        String dtemp = Double.toString(tempDoublebank);
                        customers[i + 1] = dtemp;
                        //  System.out.println("doub "+tempDoublebank);
                    }
                    fw.append(customers[i]+" "+customers[i+1]+"\n");
                }
            }
            fw.close();
        }
    }
    private File messageFileID ;
    private File messageFile =new File("messages.txt");

    public File getMessageFile() {
        return messageFile;
    }

    public void setMessageFile(File messageFile) {
        this.messageFile = messageFile;
    }

    public File getMessageFileID() {
        return messageFileID;
    }

    public void setMessageFileID(File messageFileID) {
        this.messageFileID = messageFileID;
    }

    public void readMessageCustomer() throws IOException {
        messageFileID=new File(id+".txt");
        FileWriter fw = new FileWriter(messageFile,true);
        fw.close();
        FileWriter fw1 = new FileWriter(messageFileID,true);
        fw1.close();
        Scanner sc= new Scanner(messageFileID);
        String temp1,temp2;
        while(sc.hasNextLine()){
            temp1=sc.nextLine();
            temp2=sc.nextLine();
            if(temp2.equals("1")){
                System.out.println("You sent: "+temp1);
            }
            if(temp2.equals("2")){
                System.out.println("The bank support sent: "+temp1);
            }
        }
        sc.close();
    }
    public void readMessagesEmployee() throws IOException {
        int tempid= id;
        FileWriter fw = new FileWriter(messageFile,true);
        fw.close();
        Scanner sc = new Scanner(messageFile);
        String temp1,temp2;
        System.out.println("Choose the id to chat with!");
        int count=0;
        while(sc.hasNext()){
            temp1= sc.next();
            temp2= sc.next();
            System.out.println((count+1)+". "+temp1+" has sent "+temp2+" messages!");
            count++;
        }
        sc.close();
        Scanner sc1=new Scanner(System.in);
        int choice=sc1.nextInt();

        Scanner sc2 = new Scanner(messageFile);
        String temp11,temp22;
        count=0;
        System.out.println("Choose the id to chat with!");
        while(sc2.hasNext()){
            temp11= sc2.next();
            temp22= sc2.next();
            int temponr=Integer.parseInt(temp11);
            if(count+1==choice){

                id=temponr;
                break;
            }
            count++;
        }
        sc2.close();
       messageFileID =new File(id+".txt");
        Scanner scid= new Scanner(messageFileID);
        String tempp1,tempp2;
        while(scid.hasNextLine()){
            tempp1=scid.nextLine();
            tempp2=scid.nextLine();
            if(tempp2.equals("2")){
                System.out.println("You sent: "+tempp1);
            }
            if(tempp2.equals("1")){
                System.out.println("The customer sent: "+tempp1);
            }
        }
scid.close();
        FileWriter fw1 = new FileWriter(messageFileID,true);

        System.out.println("----------------------------------------");
        System.out.println("Send a message to "+id);
        count=0;
        Scanner sc11= new Scanner(System.in);
        while(true){
            String msg= sc11.nextLine();
            if(msg.equals("-1")&&count>0){

                break;

            }
            if(msg.equals("-1")){
                break;
            }
            fw1.append(msg+"\n2\n");
            count++;
        }
        count=0;

        fw1.close();
        id=tempid;
    }
    public void createMessageFile() throws IOException {
        System.out.println("-----------------------------------");
        System.out.println("Send a message to an employee");
        Scanner sc = new Scanner(System.in);
         messageFileID =new File(id+".txt");
         messageFile =new File("messages.txt");
        System.out.println("Press -1 to exit");

        FileWriter fw = new FileWriter(messageFileID,true);
        FileWriter fw1 = new FileWriter(messageFile,true);
        Scanner scan = new Scanner(messageFile);
        String temp1,temp2;
        int count=0;
        fileLength(messageFile);
        String []arr=new String[(empfileSize*2)];
        int tempnr;
        boolean ex=false;
        while(scan.hasNext()){
            temp1=scan.next();
            temp2=scan.next();
            tempnr=Integer.parseInt(temp1);
            if(tempnr==id){
                ex=true;
            }
            arr[count]=temp1;
            arr[count+1]=temp2;
            count=count+2;

        }
        PrintWriter writer = new PrintWriter(messageFile);
        writer.print("");
        writer.close();
        count=0;
        String tempoo = null;
        if(arr.length>0){
            for(int i = 0 ; i< arr.length;i++){
                if(i%2==0){
                    if(arr[i].equals(id+"")){
                        tempoo=arr[i+1];
                        i++;
                        continue;
                    }
                    fw1.append(arr[i]+" "+arr[i+1]+"\n");
                }
            }
        }
        int tempVal = 0;
        fileLength(messageFileID);
if(arr.length>0&&empfileSize>0&&messageFile.exists()&&messageFileID.exists()){
    tempVal=Integer.parseInt(tempoo);
}
      
        while(true){
            String msg=sc.nextLine();
            if(msg.equals("-1")&&count>0){
                if(ex||arr.length<=0){
                   
                    fw1.append(id+" "+(count+tempVal)+"\n");
                    break;
                }
                else{
                    fw1.append(id+" "+(count+tempVal)+"\n");
                    break;
                }

            }
            if(msg.equals("-1")){
                fw1.append(id+" "+(count+tempVal)+"\n");
                break;
            }
            fw.append(msg+"\n1\n");
            count++;
        }
count=0;
        fw.close();
        fw1.close();
    }

}
