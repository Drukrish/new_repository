package com.employee;
 
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner; 
import com.employee.entity.Employee;
import com.employee.services.EmployeeServices;
import com.employee.utils.ConnectionFactory;
 
public class App {
    
    public static void main( String[] args ){
        Connection connection=ConnectionFactory.getMySqlConnection();
        EmployeeServices services=new EmployeeServices(connection);
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("Employee Management Application\n");
            System.out.println("A**Add Employee Details\nB>>Display List of Employees by the Firstname\nC**Display List of Employees with Firstname and Phone number\nD>>Update the email and phone number of a particular employee\nE:>Delete details of a particular employee by Firstname\nF>>List of employees with firstname and email address whose birthday falls on the given date\nG:>Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date\nH)-->Exit\n");
            char choice='A';
            System.out.println("Enter Value from A to H");
            choice=scanner.nextLine().charAt(0);
            switch (choice) {
                case 'A','a':{
                    System.out.println("**********A)Add Employee Details**********");
                    System.out.println("Enter the first name : ");
                    String firstName=scanner.nextLine();
                    System.out.println("Enter the last name : ");
                    String lastName=scanner.nextLine();
                    System.out.println("Enter the address : ");
                    String address=scanner.nextLine();
                    System.out.println("Enter the mail id : ");
                    String emailAddress=scanner.nextLine();
                    System.out.println("Enter the phone number : ");
                    long phoneNumber=Long.parseLong(scanner.nextLine());
                    System.out.println("Enter the Date of Birth in (YYYY-MM-DD) Format : ");
                    LocalDate dateOfBirth=LocalDate.parse(scanner.nextLine());
                    LocalDate weddingDate=null;
                    System.out.println("Married/UnMarried - Y/N : ");
                    char isMarried=scanner.nextLine().charAt(0);
                    switch (isMarried) {
                        case 'Y':
                        case 'y':
                            weddingDate = LocalDate.parse(scanner.nextLine());
                            break;
                        case 'N':
                        case 'n':
                            break;
                        default:
                            System.out.println("Invalid");
                            break;
                    }
                    services.addEmployee(new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate));
                    break;
                }    
                case 'B','b':{
                    System.out.println("::::::::::>B)Display employees by their firstName<:::::::::");
                    System.out.println("Enter the first name : ");
                    String firstName=scanner.nextLine();
                    services.findByName(firstName);
                    break;
                }
                case 'C','c':{
                    System.out.println("*********C)Display employees with FirstName and Phone Number********");
                    services.displayNameAndPhoneNumber();
                    break;
                }    
                case 'D','d':{
                    System.out.println(":::::::::::>D)Update the email and phoneNumber of a particular employee<:::::::::");
                    System.out.println("Enter the mail id : ");
                    String emailAddress=scanner.nextLine();
                    System.out.println("Enter the new mail id : ");
                    String newEmailAddress=scanner.nextLine();
                    System.out.println("Enter the new phone number : ");
                    long phoneNumber=Long.parseLong(scanner.nextLine());
                    services.updateEmailAndPhoneNumber(emailAddress,newEmailAddress,phoneNumber);
                    break;
                }
                case 'E','e':{
                    System.out.println("**********E)Delete Details of A specific employee by firstName********");
                    System.out.println("Enter the first name : ");
                    String firstName=scanner.nextLine();
                    System.out.println("Enter the email : ");
                    String emailAddress=scanner.nextLine();
                    services.deleteEmployeeByFirstName(firstName,emailAddress);
                    break;
                }
                case 'F','f':{
                    System.out.println("::::::::::>F)Display employees of their firstName and emailAddress  whose Birthday falls on the particular date<::::::::");
                    System.out.println("Enter the Date in (YYYY-MM-DD) Format : ");
                    LocalDate date=LocalDate.parse(scanner.nextLine());
                    services.displayNameAndMail(date);
                    break;
                }
                case 'G','g':{
                    System.out.println("**********G)Display employees of their firstName and phone Number whose Wedding Anniversary falls on the given date**********");
                    System.out.println("Enter the Date in (YYYY-MM-DD) Format : ");
                    LocalDate date=LocalDate.parse(scanner.nextLine());
                    services.displayNameAndPhoneNumber(date);
                    break;
                }
                case 'H','h':{
                    System.out.println("Exit");
                    scanner.close();
                    System.exit(0);
                }
                default:{
                    System.err.println("Give a valid choice");
                    break;
                }
            }
            
        }    
        
    }
}
 
