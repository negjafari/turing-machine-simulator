package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TuringMachine {

    private String input;
    private String [] tape;
    private List<State> states;
    private List<String> sigma;
    private List<String> gamma;
    private String blank = "⊡";
    private List<String> finalStates;


    public TuringMachine(){

    }


    public void setStates(String number) {

        List<State> list = new ArrayList<>();

        int count = Integer.parseInt(number);

        for(int i = 0 ; i<count ; i++){
            String state = "Q" + i;
            list.add(new State(state,false));
        }

        this.states = list;

    }

    public void setSigma(String str) {
        List<String> sigma = new ArrayList<>();
        String[] arr = str.split(",");
        sigma = Arrays.asList(arr);
        this.sigma = sigma;
    }

    public void setGamma(String str) {
        List<String> gamma = new ArrayList<>();
        String[] arr = str.split(",");
        gamma = Arrays.asList(arr);
        this.gamma = gamma;
    }

    public void setFinalStates(String str){
        List<String> fStates = new ArrayList<>();
        String[] arr = str.split(",");
        fStates = Arrays.asList(arr);
        this.finalStates = fStates;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getState(String state){
        for(int i = 0 ; i<states.size() ; i++){
            if(states.get(i).getName().equals(state)){
                return i;
            }
        }
        return -1;
    }


    public List<State> getStates() {
        return states;
    }

    public List<String> getGamma() {
        return gamma;
    }

    public void createTape(){
        String[] arr = new String[this.input.length()+1];
        arr[0] = "Q0";
        for(int i = 0 ; i< input.length() ; i++){
            arr[i+1] = input.charAt(i)+"";
        }
        this.tape = arr;
    }


    public String[] getTape() {
        return tape;
    }

    public String printTape(){
        String res = "";
        for (String s : tape) {
            res += s;
        }

        return res;

    }

    public void shiftToRight(int index , String newState){

        String temp = this.tape[index + 1];
        this.tape[index + 1] = newState;
        this.tape[index] = temp;
    }

    public void shiftToLeft(int index , String newState){

        String temp = this.tape[index-1];
        this.tape[index-1] = newState;
        this.tape[index] = temp;
    }

    public void replace(int index , String newChar){

            this.tape[index + 1] = newChar;



    }

    public  Transition search(String state , String next){
        // q0 a => q1 x R

        for (State value : this.states) {
            String cur_state = value.getName();
            if (cur_state.equals(state)) {
                List<Transition> t = value.getTransitions();
                for (Transition transition : t) {
                    String search_for = transition.getPrev();
                    if (search_for.equals(next)) {
                        return transition;
                    }
                }
            }
        }


        return null;
    }


    public boolean isFinalState(String s){

        for (String finalState : finalStates) {
            String str = "Q" + finalState;
            if (str.equals(s)) {
                return true;
            }
        }

        return false;

    }



    public String getBlank() {
        return blank;
    }



    @Override
    public String toString() {
        return "TuringMachine{" +
                "input='" + input + '\'' +
                ", states=" + states +
                ", sigma=" + sigma +
                ", gamma=" + gamma +
                '}';
    }
}
