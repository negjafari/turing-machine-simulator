package sample;

import java.util.ArrayList;
import java.util.List;

public class State {

    private String name;
    private boolean isFinal;
    private List<Transition> transitions;


    public State(String name , boolean isFinal){
        this.name = name;
        this.isFinal = isFinal;
        this.transitions = new ArrayList<>();
    }

    public void addTransition(Transition t){
        this.transitions.add(t);
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", isFinal=" + isFinal +
                ", transitions=" + transitions +
                '}';
    }

}
