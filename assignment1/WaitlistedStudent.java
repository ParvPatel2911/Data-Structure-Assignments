// Parv Patel 3137681
 
public class WaitlistedStudent extends Student {
   
    
    
    
    
    private Status status;
    private int daysLeftToRegister;

    public WaitlistedStudent(String studentNumber, String name) {// constructor for waitlisted student
        super(studentNumber, name);
        this.status = Status.Waitlisted;
        this.daysLeftToRegister = 0;
    }
    public WaitlistedStudent(String studentNumber, String name, int daysLeftToRegister) {// constructor for waitlisted student
        super(studentNumber, name);
        this.status = Status.Waitlisted;
        this.daysLeftToRegister = daysLeftToRegister;
    }
    public Status getStatus(){ return status;}
    public void setStatus(Status status) {
        this.status = status;
    }
    public int getDaysLeftToRegister() {
        return daysLeftToRegister;
    }

    public void setDaysLeftToRegister(int daysLeftToRegister) {
        this.daysLeftToRegister = daysLeftToRegister;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        WaitlistedStudent that = (WaitlistedStudent) obj;
        return status == that.status && daysLeftToRegister == that.daysLeftToRegister;
    }

public void updateDaysLeft(int days) {
    daysLeftToRegister += days;
}


public String toString() {
    return super.toString() + "Status: " + status + "Days left to register: " + daysLeftToRegister;
}
}
