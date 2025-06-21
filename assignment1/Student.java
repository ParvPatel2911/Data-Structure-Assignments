// Parv Patel 3137681
public class Student {
    private String studentNumber;
    private String name;

    public Student(String studentNumber , String name){
        this.studentNumber = studentNumber;
        this.name = name;
    }
    public String getStudentNumber(){ return studentNumber;// returns Student number
    }

    public String getName(){return name; // returns student name
    }

    public boolean equals( Object o){
        if(studentNumber == o) return true;
        if(o == null || getClass()!= o.getClass()) return false;
        Student student = (Student) o;
        return studentNumber.equals(student.studentNumber)&& name.equals(student.name);

    }
    public String toString() {
        // Return a string representation of the student
        return studentNumber + " : " + name;
    }
    
}
