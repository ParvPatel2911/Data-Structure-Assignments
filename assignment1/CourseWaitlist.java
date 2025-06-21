
// Parv Patel 3137681

import java.util.Random;
import java.util.Scanner;



public class CourseWaitlist {
    private CircularDoublyLinkedList<WaitlistedStudent> waitlist;

    public CourseWaitlist() {
        waitlist = new CircularDoublyLinkedList<>();
        // Adding initial waitlisted students
        waitlist.addFirst(new WaitlistedStudent("1111111", "Jeremy"));
        waitlist.addLast(new WaitlistedStudent("2222222", "Anne"));
        waitlist.addLast(new WaitlistedStudent("3333333", "Jacob"));
        waitlist.addLast(new WaitlistedStudent("4444444", "Melissa"));
        // Granting permission to the first student
        WaitlistedStudent firstStudent = waitlist.removeFirst();
        firstStudent.setStatus(Status.PermittedToRegister);
        firstStudent.setDaysLeftToRegister(2);
        waitlist.addFirst(firstStudent);
    }

    public void simulateOperations() {
        Random rand = new Random();
        int operations = 0;
        while (!waitlist.isEmpty() && operations < 20) {
            int operation = rand.nextInt(4) + 1; // 1 to 4
            System.out.println("System chose option " + operation + ":");
            switch (operation) {
                case 1:
                    operationNoAddition();
                    break;
                case 2:
                    operationNewStudent();
                    break;
                case 3:
                    operationFirstStudentRegisters();
                    break;
                case 4:
                    operationCheckStudent();
                    break;
            }
            displayWaitlist();
            operations++;
        }
        System.out.println("Reached 20 days/operations!");
        System.out.println("Final waitlist:");
        displayWaitlist();
    }

    private void operationNoAddition(){// method to check how many days student have to register for course
        WaitlistedStudent firstStudent = waitlist.first();
        if(firstStudent.getStatus()== Status.PermittedToRegister){
            if (firstStudent.getDaysLeftToRegister() == 0) {
            firstStudent.setStatus(Status.Waitlisted);
            waitlist.rotate();
        }
        else{
            firstStudent.setDaysLeftToRegister(firstStudent.getDaysLeftToRegister()-1);
        }
    }
    else{
        firstStudent.setStatus(Status.PermittedToRegister);
        firstStudent.setDaysLeftToRegister(2);

    }
     
}
private void operationNewStudent(){ //  method to add new students in waitlist
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter student name:");
    String studentName = sc.nextLine();
    System.out.println("Enter student number:");
    String studentNumber = sc.nextLine();
    WaitlistedStudent newStudent = new WaitlistedStudent(studentNumber, studentName);
    waitlist.addLast(newStudent);
    

} 

private void operationFirstStudentRegisters(){ // method to check if student have successfully registered or not.
    WaitlistedStudent firstStudent = waitlist.first();
    if(firstStudent.getStatus() == Status.PermittedToRegister){
        System.out.println(firstStudent.getName() +" registers");
        waitlist.removeFirst();
    }
    else{
        firstStudent.setStatus(Status.PermittedToRegister);
        firstStudent.setDaysLeftToRegister(2);
    }
    
}
 private void operationCheckStudent(){ // method to check whwther student is in waitlist or not
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter student name:");
    String studentName = sc.nextLine();
    System.out.println("Enter student number:");
    String studentNumber = sc.nextLine();
    WaitlistedStudent check = new WaitlistedStudent(studentNumber, studentName);
    
    
    if (waitlist.contains(check)) {
        System.out.println("Student found in waitlist");
    }
    else{
        System.out.println("Student not found in waitlist");

    }
    
 }
 private void displayWaitlist() {// method to display our waitlist
    System.out.println("Waitlist:");
    WaitlistedStudent currentStudent = waitlist.first();
    for (int i = 0; i < waitlist.size(); i++) {
        System.out.println(currentStudent.getStudentNumber() + " : " + currentStudent.getName() +
                ", Status: " + currentStudent.getStatus() +
                (currentStudent.getStatus() == Status.PermittedToRegister ? " Days left: " + currentStudent.getDaysLeftToRegister() : ""));
        waitlist.rotate();
        currentStudent = waitlist.first();
    }
}


    
  
    
    }
    
    



