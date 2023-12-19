package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {


    public TuringMachine turing = new TuringMachine();

    public int index = 0 ;
    public String out = "";
    public int count = 0;

    HBox GCircles = new HBox(5);
    List<Circle> circles = new ArrayList<>();
    HBox tapeH = new HBox(10);
    List<Label> labels = new ArrayList<>();

    Font font = Font.font(Font.getFontNames().get(0), FontWeight.EXTRA_BOLD, 15);




    private Parent createContent(){



        Image image = new Image("file:background.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(1200);
        imageView.setFitHeight(800);


        BorderPane root = new BorderPane();

        root.getChildren().addAll(imageView);


        final ComboBox<String> combo1 = new ComboBox<>();
        for(int i = 0 ; i<turing.getStates().size() ; i++){
            combo1.getItems().add(turing.getStates().get(i).getName());
        }
        combo1.setValue(turing.getStates().get(0).getName());
        combo1.setStyle("-fx-background-color: #D7DBDD; ");



        final ComboBox<String> combo2 = new ComboBox<>();
        for(int i = 0 ; i<turing.getGamma().size() ; i++) {
            combo2.getItems().add(turing.getGamma().get(i));
        }
        combo2.getItems().add("⊡");
        combo2.setValue(turing.getGamma().get(0));
        combo2.setStyle("-fx-background-color: #D7DBDD; ");


        final ComboBox<String> combo3 = new ComboBox<>();
        for(int i = 0 ; i<turing.getStates().size() ; i++){
            combo3.getItems().add(turing.getStates().get(i).getName());
        }
        combo3.setValue(turing.getStates().get(0).getName());
        combo3.setStyle("-fx-background-color: #D7DBDD; ");


        final ComboBox<String> combo4 = new ComboBox<String>();
        for(int i = 0 ; i<turing.getGamma().size() ; i++) {
            combo4.getItems().add(turing.getGamma().get(i));
        }
        combo4.getItems().add("⊡");
        combo4.setValue(turing.getGamma().get(0));
        combo4.setStyle("-fx-background-color: #D7DBDD; ");


        final ComboBox<String> combo5 = new ComboBox<>();
        combo5.getItems().addAll(
                "R",
                "L"
        );
        combo5.setValue("R");
        combo5.setStyle("-fx-background-color: #D7DBDD; ");





        HBox hBox5 = new HBox(5);
        Label label1 = new Label("δ");
        label1.setTextFill(Color.rgb(23, 32, 42 ));
        label1.setFont(font);
        Label label2 = new Label("(");
        label2.setTextFill(Color.rgb(23, 32, 42 ));
        label2.setFont(font);
        Label label3 = new Label(",");
        label3.setTextFill(Color.rgb(23, 32, 42 ));
        label3.setFont(font);
        Label label4 = new Label(")");
        label4.setTextFill(Color.rgb(23, 32, 42 ));
        label4.setFont(font);
        Label label5 = new Label("=");
        label5.setTextFill(Color.rgb(23, 32, 42 ));
        label5.setFont(font);
        Label label6 = new Label("(");
        label6.setTextFill(Color.rgb(23, 32, 42 ));
        label6.setFont(font);
        Label label7 = new Label(",");
        label7.setTextFill(Color.rgb(23, 32, 42 ));
        label7.setFont(font);
        Label label8 = new Label(",");
        label8.setTextFill(Color.rgb(23, 32, 42 ));
        label8.setFont(font);
        Label label9 = new Label(")");
        label9.setTextFill(Color.rgb(23, 32, 42 ));
        label9.setFont(font);
        hBox5.getChildren().addAll(label1,label2,combo1,label3,combo2,label4,label5,label6,combo3,label7,combo4,label8,combo5,label9);

        hBox5.setPrefSize(100,50);


        Button add_btn = new Button("add state");
        add_btn.setStyle("-fx-background-color: #5D6D7E; ");
        add_btn.setTextFill(Color.WHITE);
        add_btn.setFont(font);

        add_btn.setOnAction(e->{

            String state = combo1.getValue().toString();
            int index = turing.getState(state);
            if(index!=-1){
                Transition t = new Transition(combo1.getValue(),combo3.getValue(),combo2.getValue(),combo4.getValue(),combo5.getValue());
                System.out.println("transition added : " + t);
                turing.getStates().get(index).addTransition(t);
            }
        });


        HBox hBox6 = new HBox(10);
        Button input_btn = new Button("INPUT");
        input_btn.setStyle("-fx-background-color: #5D6D7E; ");
        input_btn.setTextFill(Color.WHITE);
        input_btn.setFont(font);
        TextField input_txt = new TextField();
        hBox6.getChildren().addAll(input_btn,input_txt);

        Button play_btn = new Button("play");
        play_btn.setStyle("-fx-background-color: #283747; ");
        play_btn.setTextFill(Color.WHITE);
        play_btn.setFont(font);
        play_btn.setMaxSize(300,100);

        play_btn.setOnAction(e->{
            turing.setInput(input_txt.getText());
            turing.createTape();
            runPage();
        });



        hBox5.setAlignment(Pos.CENTER);
        hBox6.setAlignment(Pos.CENTER);


        VBox vBox = new VBox(25);
        vBox.getChildren().addAll(hBox5,add_btn,hBox6,play_btn);
        vBox.setAlignment(Pos.CENTER);
        root.setCenter(vBox);



        return root;

    }

    public void startPage(){


        Image image = new Image("file:background.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(1000);


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Turing Simulator");

        BorderPane borderPane = new BorderPane();

        borderPane.getChildren().addAll(imageView);


        HBox hBox1 = new HBox(12);
        Button sigma_btn = new Button("Σ");
        sigma_btn.setFont(font);
        sigma_btn.setStyle("-fx-background-color: #5D6D7E; ");
        sigma_btn.setTextFill(Color.WHITE);
        TextField sigma_txt = new TextField();
        sigma_txt.setDisable(true);
        Label sigma_label = new Label("enter sigma values (separate with comma ',')");
        sigma_label.setTextFill(Color.rgb(23, 32, 42 ));
        sigma_label.setFont(font);
        hBox1.getChildren().addAll(sigma_btn,sigma_txt,sigma_label);
        sigma_btn.setOnAction(e->{
            sigma_txt.setDisable(false);
        });


        HBox hBox2 = new HBox(12);
        Button gamma_btn = new Button("Γ");
        gamma_btn.setFont(font);
        gamma_btn.setStyle("-fx-background-color: #5D6D7E; ");
        gamma_btn.setTextFill(Color.WHITE);
        TextField gamma_txt = new TextField();
        gamma_txt.setDisable(true);
        Label gamma_label = new Label("enter gamma values (separate with comma ',' )");
        gamma_label.setFont(font);
        gamma_label.setTextFill(Color.rgb(23, 32, 42 ));
        hBox2.getChildren().addAll(gamma_btn,gamma_txt,gamma_label);
        gamma_btn.setOnAction(e->{
            gamma_txt.setDisable(false);
        });


        HBox hBox3 = new HBox(10);
        Button states_btn = new Button("NUMBER OF STATES");
        states_btn.setFont(font);
        states_btn.setStyle("-fx-background-color: #5D6D7E; ");
        states_btn.setTextFill(Color.WHITE);
        TextField states_txt = new TextField();
        states_txt.setDisable(true);
        Label states_label = new Label("enter number of states (just a number)");
        states_label.setTextFill(Color.rgb(23, 32, 42 ));
        states_label.setFont(font);
        hBox3.getChildren().addAll(states_btn,states_txt,states_label);
        states_btn.setOnAction(e->{
            states_txt.setDisable(false);

        });


        HBox hBox4 = new HBox(10);
        Button final_btn = new Button("FINAL STATE");
        final_btn.setFont(font);
        final_btn.setStyle("-fx-background-color: #5D6D7E; ");
        final_btn.setTextFill(Color.WHITE);
        TextField final_txt = new TextField();
        final_txt.setDisable(true);
        Label final_label = new Label("enter number of final state ");
        final_label.setFont(font);
        final_label.setTextFill(Color.rgb(23, 32, 42 ));
        hBox4.getChildren().addAll(final_btn,final_txt,final_label);
        final_btn.setOnAction(e->{
            final_txt.setDisable(false);
        });

        Button tran_btn = new Button("go to transitions");
        tran_btn.setStyle("-fx-background-color: #283747; ");
        tran_btn.setTextFill(Color.WHITE);
        tran_btn.setFont(font);
        tran_btn.setMaxSize(300,100);


        tran_btn.setOnAction(e->{

            turing.setGamma(gamma_txt.getText());
            turing.setSigma(sigma_txt.getText());
            turing.setStates(states_txt.getText());
            turing.setFinalStates(final_txt.getText());
            window.close();
        });

        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(25);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,tran_btn);


        borderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane,800,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void runPage(){


        Image image = new Image("file:background.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(1200);
        imageView.setFitHeight(800);



        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Turing Simulator");

        BorderPane borderPane = new BorderPane();

        borderPane.getChildren().addAll(imageView);

        VBox vBox = new VBox(25);


        Label transition_label = new Label("transitions : ");
        transition_label.setTextFill(Color.rgb(23, 32, 42 ));
        transition_label.setFont(font);



        Label output_lb = new Label("output : ");
        output_lb.setTextFill(Color.rgb(23, 32, 42 ));
        output_lb.setFont(font);


        Button next_btn = new Button("next");
        next_btn.setStyle("-fx-background-color: #5D6D7E; ");
        next_btn.setTextFill(Color.WHITE);
        next_btn.setFont(font);


        Label result_lb = new Label("result : ");
        result_lb.setTextFill(Color.rgb(23, 32, 42 ));
        result_lb.setFont(font);




        next_btn.setOnAction(e->{

            count++;

            if(count%5==0){
                out += "(" + turing.printTape() + ")" + " ⊢ " + "\n";
            }
            else
                out += "(" + turing.printTape() + ")" + " ⊢ ";


            output_lb.setText(out);



            if(index > turing.getTape().length-2){

                Transition t = turing.search(turing.getTape()[index] , turing.getBlank());


                if(turing.isFinalState(turing.getTape()[index])){

                    if(t!=null){
                        GraphicCircles(t.getLast().substring(1));
                        GraphicTape();
                    }

                    output_lb.setText(out.substring(0,out.length()-2));
                    result_lb.setText("ACCEPTED");
                    next_btn.setDisable(true);
                }



                else if(t!= null){

                    GraphicCircles(t.getLast().substring(1));
                    GraphicTape();

                    transition_label.setText(t.toString());
                    String destination = t.getLast();
                    String replace = t.getReplace();
                    String direction = t.getDirection();


                    if (direction.equals("R")) {
                        turing.shiftToRight(index, destination);
                        index++;
                    } else {
                        turing.shiftToLeft(index, destination);
                        index--;
                    }

                }


                else {

                    result_lb.setText("NOT ACCEPTED");
                    output_lb.setText(out.substring(0,out.length()-2));
                    next_btn.setDisable(true);
                }



            }

            else {
                String state = turing.getTape()[index];
                String next_char = turing.getTape()[index+1];

                Transition t = turing.search(state,next_char);


                GraphicCircles(state.substring(1));

                GraphicTape();


                if(t==null){
                    transition_label.setText("no transition exist");

                    if(turing.isFinalState(state)){
                        result_lb.setText("ACCEPTED");
                        output_lb.setText(out.substring(0,out.length()-2));
                    }
                    else {
                        result_lb.setText("ACCEPTED");
                    }
                    next_btn.setDisable(true);

                }

                else {
                    transition_label.setText(t.toString());
                    String destination = t.getLast();
                    String replace = t.getReplace();
                    String direction = t.getDirection();


                    turing.replace(index, replace);


                    if (direction.equals("R")) {
                        turing.shiftToRight(index, destination);
                        index++;
                    } else {
                        turing.shiftToLeft(index, destination);
                        index--;
                    }
                }
            }

        });

        for(int i = 0 ; i<turing.getStates().size() ; i++){
            circles.add(new Circle(25));
        }

        for(int i = 0 ; i<turing.getStates().size() ; i++){
            VBox h = new VBox();
            for(int j = 0 ; j<1 ; j++){

                Circle circle = circles.get(i);

                if(i == 0){
                    circle.setFill(Color.rgb(136, 78, 160));
                }
                else
                    circle.setFill(Color.rgb(215, 189, 226));

                Label label = new Label("Q" + i);
                label.setTextFill(Color.rgb(74, 35, 90));
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(circle,label);
                GCircles.getChildren().add(stackPane);
            }

            h.getChildren().add(GCircles);
        }



        for(int j = 0 ; j < turing.getTape().length ; j++){
            labels.add(new Label(turing.getTape()[j]));
        }




        for(int i = 0 ; i < turing.getTape().length ; i++){
            Rectangle rec = new Rectangle(30,30);
            rec.setFill(Color.LIGHTGRAY);
            rec.setStroke(Color.BLACK);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rec,labels.get(i));
            tapeH.getChildren().add(stackPane);
        }




        vBox.setAlignment(Pos.CENTER);
        GCircles.setAlignment(Pos.CENTER);
        tapeH.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(transition_label,GCircles,tapeH,output_lb,next_btn,result_lb);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane,1000,500);

        window.setScene(scene);
        window.showAndWait();


    }


    public void GraphicCircles(String str){

        for(int i  = 0 ; i<turing.getStates().size() ; i++){
            Circle ci = circles.get(i);
            if(i == Integer.parseInt(str)){
                ci.setFill(Color.rgb(136, 78, 160));
            }
            else {
                ci.setFill(Color.rgb(215, 189, 226));
            }
        }

    }

    public void GraphicTape(){

        for(int j = 0 ; j < turing.getTape().length ; j++){
            Label l = labels.get(j);
            l.setText(turing.getTape()[j]);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        startPage();
        Scene scene = new Scene(createContent(), 800,500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
