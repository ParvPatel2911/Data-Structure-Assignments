//Parv Patel 3137681
public class Peg {
    private String color;

    public Peg(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Peg peg = (Peg) obj;
        return color.equals(peg.color);
    }
    
}
