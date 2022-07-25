package edu.ufp.inf.project;

import edu.princeton.cs.algs4.StdOut;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Projeto LP2 & AED2");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Files
        //User.writeUsersFile();
        //User.readUsersFile();
        Node.readNodesFromFile();
        Way.readWaysFromFile();
        //Node.writeNodesToFileRand();

        //Criar Users
        User user1 = new User("Joao", Type.ADMIN);
        user1.insertUsersST();
        User user2 = new User("Tiago", Type.BASIC);
        user2.insertUsersST();
        User user3 = new User("Rui", Type.BASIC);
        user3.insertUsersST();
        User user4 = new User("Ricardo", Type.ADMIN);
        user4.insertUsersST();
        User user5 = new User("Manuel", Type.BASIC);
        user5.insertUsersST();
        User user6 = new User("Nelson", Type.BASIC);
        user6.insertUsersST();

        //Criar Datas
        Date date1 = new Date(2022, 4, 14);
        Date date2 = new Date(2022, 4, 16);
        Date date3 = new Date(2022, 5, 30);
        Date date4 = new Date(2022, 6, 20);
        Date date5 = new Date(2022, 7, 21);
        Date date6 = new Date(2022, 8, 10);
        Date date7 = new Date(2022, 9, 8);
        Date date8 = new Date(2022, 10, 13);
        Date date9 = new Date(2022, 11, 18);


        //Adicionar Visitas
        user1.addPOI(1, date2);
        user1.addPOI(2, date3);
        user1.addPOI(6, date4);
        user1.addPOI(4, date5);
        user1.addPOI(5, date6);


        user2.addPOI(1, date2);
        user2.addPOI(6, date3);
        user2.addPOI(5, date4);
        user2.addPOI(7, date6);

        user3.addPOI(6, date2);
        user3.addPOI(2, date3);

        user4.addPOI(1, date2);

        user5.addPOI(1, date2);
        user5.addPOI(3, date3);
        user5.addPOI(4, date4);
        user5.addPOI(5, date5);

        user6.addPOI(1, date2);
        user6.addPOI(5, date4);
        user6.addPOI(6, date5);


        launch(args);
        Tests test = new Tests();

        //Node.writeNodesToFile();
        test.now();

    }
}

