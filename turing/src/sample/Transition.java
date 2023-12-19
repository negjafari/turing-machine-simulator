package sample;

public class Transition {

    // q0 a => q1 x R

    private String first; //q0
    private String last; //q1

    private String prev; //a
    private String replace; //x
    private String direction; //R


    public Transition(String first , String last ,String prev, String replace , String direction){
        this.first = first;
        this.last = last;
        this.prev = prev;
        this.replace = replace;
        this.direction = direction;
    }

    public String getPrev() {
        return prev;
    }

    public String getLast() {
        return last;
    }

    public String getReplace() {
        return replace;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {

        return "δ ( " + first + " , " + prev + " ) " + " = " + " ( " + last + " , " + replace + " , " + direction   + " ) ";

    }
}
