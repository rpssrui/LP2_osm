package edu.ufp.inf.project;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static final double GROUP_MARGIN = 30.0;
    private static final double radius = 20.0;
    public Button userSearchTOP5;
    public TextField userSearchID;
    public Button userSearchVISITED;
    public Button userSearchNOTVISTED;
    public TextField userSearchPOI;
    public Button poiSearchTOP5;
    public Button poiSearchVISITED;
    private EdgeWeightedDigraphNew graph;

    public Tab sysGraph;
    public Group graphGroup;
    public TextField addFromField;
    public TextField addToField;
    public TextField addDistanceField;
    public TextField addTimeField;
    public Button sysAddEdge;
    public TextField removeFromField;
    public TextField removeToField;
    public Button sysRemoveEdge;
    public TextField editFromField;
    public TextField editToField;
    public TextField editDistanceField;
    public TextField editTimeField;
    public Button sysEditEdge;
    public TextField shortestPathFromField;
    public TextField shortestPathToField;
    public TextField avoidField;
    public Button sysShortestPath;

    public Tab sysUsers;
    public TableView usersTable;
    public TableColumn sysUserId;
    public TableColumn sysUserName;
    public TableColumn sysUserType;
    public TextField userNameAddField;
    public TextField userTypeAddField;
    public Button sysAddUser;
    public Button sysRemoveUser;
    public TextField userIdRemoveField;
    public TextField userIdEditField;
    public TextField userNameEditField;
    public TextField userTypeEditField;
    public Button sysEditUser;

    public Tab sysPOI;
    public TableView poisTable;
    public TableColumn sysPoiId;
    public TableColumn sysPoiName;
    public TableColumn sysPoiLatitude;
    public TableColumn sysPoiLongitude;
    public TableColumn sysPoiCategory;
    public TableColumn sysPoiNetwork;
    public TextField poiIDField;
    public TextField poiNamefield;
    public TextField poiLatitudeField;
    public TextField poiLongitudeField;
    public TextField poiCategoryField;
    public Button sysAddPoi;
    public Button sysRemovePoi;
    public TextField poiNetworkEditField;
    public TextField poiNetworkField;
    public TextField poiIDRemoveField;
    public TextField poiIDEditField;
    public TextField poiNameEditfield;
    public TextField poiLatitudeEditField;
    public TextField poiLongitudeEditField;
    public TextField poiCategoryEditField;
    public Button sysEditPoi;

    public Tab sysLogs;
    public TableView logsTable;
    public TableColumn sysLogDate;
    public TableColumn sysLogMessage;
    public TextField logDateField;
    public TextField logMessageField;
    public Button sysAddLogs;

    public Tab sysUserSearch;
    public Button searchUser;
    public TextArea resultUser;
    public ComboBox selectUser;
    public MenuButton selectUserSearch;
    public MenuItem vistedPOIS;
    public MenuItem nonVisitedPOIS;
    public MenuItem top5POIS;

    public Tab sysPoiSearch;
    public Button searchPOI;
    public TextArea resulPOI;
    public ComboBox selectPOI;
    public MenuButton selectPoiSearch;
    public MenuItem userWhoVisited;
    public MenuItem top5Users;

    public MenuItem sysOpenTextFile;
    public MenuItem sysOpenBinFile;
    public MenuItem sysSaveToTextFile;
    public MenuItem sysSaveToBinFile;
    public MenuItem sysAbout;
    public Button sysAddEdge1;

    public String poiSaveSearch;
    public String userSaveSearch;


    private static final String PATH_BINUtilizador = ".//data//usersBinFile.bin";
    private static final String PATH_BINPOIs = ".//data//poiBinFile.bin";
    private static final String PATH_BINGrafo = ".//data//grafoBinFile.bin";

    ObservableList<User> usersObs = FXCollections.observableArrayList();
    ObservableList<POI> poisObs = FXCollections.observableArrayList();
    ObservableList<Log> logsObs = FXCollections.observableArrayList();
    ObservableList<Way> wayObs = FXCollections.observableArrayList();
    ArrayList<User> arrayListUsers = new ArrayList<>(usersObs);
    ArrayList<POI> arrayListPois = new ArrayList<>(poisObs);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        logsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        poisTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Users
        sysUserId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sysUserName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        sysUserType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        sysUserId.setCellFactory(TextFieldTableCell.<User, Integer>forTableColumn(new IntegerStringConverter()));
        sysUserName.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        //sysUserType.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        usersTable.setEditable(true);


        //Logs
        sysLogDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        sysLogMessage.setCellValueFactory(new PropertyValueFactory<>("Message"));
        logsTable.setEditable(true);

        //POIS
        sysPoiId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sysPoiName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        sysPoiLatitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        sysPoiLongitude.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        sysPoiCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        sysPoiNetwork.setCellValueFactory(new PropertyValueFactory<>("Local"));
        sysPoiId.setCellFactory(TextFieldTableCell.<POI, Integer>forTableColumn(new IntegerStringConverter()));
        sysPoiName.setCellFactory(TextFieldTableCell.<POI>forTableColumn());
        logsTable.setEditable(true);

    }

    /**
     * Vai buscar o input das ways ao ficheiro
     *
     * @throws IOException
     */
    private void getInputWaysFromFile() throws IOException {
        for (int id : Way.waysST.keys()) {
            wayObs.add(Way.waysST.get(id));
            System.out.println(Way.waysST.get(id));
        }
        System.out.println(poisObs);
    }

    /**
     * Obtém informação da ST de users e insere-a na tabela
     *
     * @throws IOException
     */
    private void getInputPOISFromFile() throws IOException {
        for (int id : POI.poisST.keys()) {
            poisObs.add(POI.poisST.get(id));
            System.out.println(POI.poisST.get(id));
        }
        System.out.println(poisObs);
        poisTable.getItems().addAll(poisObs);

    }

    /**
     * Obtém informação acerca dos utilizadores
     *
     * @throws IOException
     */
    private void getInputUtilizadorFromFile() throws IOException {
        for (int id : User.usersST.keys()) {
            usersObs.add(User.usersST.get(id));
            System.out.println(User.usersST.get(id));
        }
        System.out.println(usersObs);
        usersTable.getItems().addAll(usersObs);
    }

    /**
     * handler adicionar edges ao grafo a partir do ficheiro
     *
     * @param actionEvent
     */
    public void handleAddEdgesFromFileAction(ActionEvent actionEvent) {
        for (int i : Way.waysST.keys()) {
            this.graph.addEdge(Way.waysST.get(i));
        }

        for (DirectedEdgeNew de : graph.edges()) {
            int from = de.from();
            int to = de.to();
            StackPane spFrom = (StackPane) graphGroup.getChildren().get(from);
            Circle cFrom = (Circle) spFrom.getChildren().get(0);
            StackPane spTo = (StackPane) graphGroup.getChildren().get(to);
            Circle cTo = (Circle) spTo.getChildren().get(0);
            Line line = new Line(cFrom.getCenterX(), cFrom.getCenterY(), cTo.getCenterX(), cTo.getCenterY());
            graphGroup.getChildren().add(line);
        }

    }

    /**
     * handler adicionar ao grafo
     *
     * @param actionEvent
     */

    public void handleAddEdgesToGraphAction(ActionEvent actionEvent) {
        Way way = new Way(Integer.parseInt(addFromField.getText()) - 1, Integer.parseInt(addToField.getText()) - 1, Double.parseDouble(addDistanceField.getText()), Integer.parseInt(addTimeField.getText()));
        way.addWaytoWaysST(way);
        this.graph.addEdge(way);

        for (DirectedEdgeNew de : graph.edges()) {
            int from = de.from();
            int to = de.to();
            StackPane spFrom = (StackPane) graphGroup.getChildren().get(from);
            Circle cFrom = (Circle) spFrom.getChildren().get(0);
            StackPane spTo = (StackPane) graphGroup.getChildren().get(to);
            Circle cTo = (Circle) spTo.getChildren().get(0);
            Line line = new Line(cFrom.getCenterX(), cFrom.getCenterY(), cTo.getCenterX(), cTo.getCenterY());
            graphGroup.getChildren().add(line);
        }

        addFromField.setText("");
        addToField.setText("");
        addDistanceField.setText("");
        addTimeField.setText("");
    }

    public void handleRemoveEdgesToGraphAction(ActionEvent actionEvent) {
    }

    public void handleEditEdgesToGraphAction(ActionEvent actionEvent) {
    }

    /**
     * handler que calcula menor caminho
     *
     * @param actionEvent
     */
    public void handleShortestPathAction(ActionEvent actionEvent) {
        int from = Integer.parseInt(shortestPathFromField.getText()) - 1;
        int to = Integer.parseInt(shortestPathToField.getText()) - 1;

        DijkstraSPNew dijkstra = new DijkstraSPNew(this.graph, from);
        if (dijkstra.pathTo(to) != null) {
            for (DirectedEdgeNew ed : dijkstra.pathTo(to)) {
                System.out.println(ed);
                StackPane spFrom = (StackPane) graphGroup.getChildren().get(ed.from());
                Circle cFrom = (Circle) spFrom.getChildren().get(0);
                StackPane spTo = (StackPane) graphGroup.getChildren().get(ed.to());
                Circle cTo = (Circle) spTo.getChildren().get(0);
                Line line = new Line(cFrom.getCenterX(), cFrom.getCenterY(), cTo.getCenterX(), cTo.getCenterY());
                line.setStroke(Color.HOTPINK);
                line.setStrokeWidth(3);
                graphGroup.getChildren().add(line);
            }
            System.out.println("Total Distance: " + dijkstra.distTo(to));
        } else
            System.out.println("Edge inexistente.");


    }

    /**
     * handler para editar utilizador
     *
     * @param utilizadorStringCellEditEvent
     */
    public void handleEditUtilizadorAction(TableColumn.CellEditEvent<User, String> utilizadorStringCellEditEvent) {

        User u = (User) usersTable.getSelectionModel().getSelectedItem();
        int col = utilizadorStringCellEditEvent.getTablePosition().getColumn();
        switch (col) {
            case 0 -> u.setId(Integer.parseInt(utilizadorStringCellEditEvent.getNewValue()));
            case 1 -> u.setName(utilizadorStringCellEditEvent.getNewValue());
        }
        usersObs.add(u);
        u.editUser(utilizadorStringCellEditEvent.getNewValue(), Type.fromString(utilizadorStringCellEditEvent.getNewValue()));

    }

    /**
     * handler para adicionar utilizador
     *
     * @param actionEvent
     */
    public void handleAddUtilizadorAction(ActionEvent actionEvent) {
        User u = new User(userNameAddField.getText(), Type.fromString(userTypeAddField.getText()));
        u.insertUsersST();
        usersTable.getItems().add(u);
        usersObs.add(u);
        userNameAddField.setText("");
        userTypeAddField.setText("");
        u.printUsersST();
    }

    /**
     * handler para remover utilizador
     *
     * @param actionEvent
     */
    public void handleDeleteUtilizadorAction(ActionEvent actionEvent) {
        if (!poiIDRemoveField.getText().equals(null)) {
            User u = User.usersST.get(Integer.parseInt(userIdRemoveField.getText()));
            if (u != null) {
                u.removerUserST();
                usersTable.getItems().remove(u);
                usersObs.remove(u);
            } else
                System.out.println("Invalid Id.");
        } else
            System.out.println("Insira um Id para remover!");
    }

    /**
     * handler para adicionar POI
     *
     * @param actionEvent
     */
    public void handleAddPOIAction(ActionEvent actionEvent) {
        POICategory category;

        //"ChargerStation" ->1
        //"Restaurante" ->2
        //"Hotelaria" ->3
        //"Parque" ->4
        //"BocaIncendio" ->5
        //"Semaforo"->6
        Location location = new Location(poiNetworkField.getText(), Double.parseDouble(poiLatitudeField.getText()), Double.parseDouble(poiLongitudeField.getText()));

        if (poiCategoryField.getText().equals("ChargerStation")) {
            category = new POICategory(1, "ChargerStation");
            EstacaoCarregamento poi = new EstacaoCarregamento(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertEstacoesCarregamentoST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");

        } else if (poiCategoryField.getText().equals("Restaurante")) {
            category = new POICategory(2, "Restaurante");
            Restauracao poi = new Restauracao(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertRestaurantesST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");
        } else if (poiCategoryField.getText().equals("Hotelaria")) {
            category = new POICategory(3, "Hotelaria");
            Hotelaria poi = new Hotelaria(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertHotelST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");
        } else if (poiCategoryField.getText().equals("Parque")) {
            category = new POICategory(4, "Parque");
            Park poi = new Park(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertParkST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");
        } else if (poiCategoryField.getText().equals("BocaIncendio")) {
            category = new POICategory(5, "BocaIncendio");
            BocaIncendio poi = new BocaIncendio(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertBocaIncendioST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");
        } else if (poiCategoryField.getText().equals("Semaforo")) {
            category = new POICategory(6, "Semaforo");
            Semaforo poi = new Semaforo(poiNamefield.getText(), category, location);
            poi.insertPOISsT();
            poi.insertSemaforoST();
            poisTable.getItems().add(poi);
            poisObs.add(poi);
            poiNamefield.setText("");
            poiCategoryField.setText("");
            poiLatitudeField.setText("");
            poiLongitudeField.setText("");
            poiNetworkField.setText("");
        }


    }

    /**
     * handler para apagar POI
     *
     * @param actionEvent
     */
    public void handleDeletePOIAction(ActionEvent actionEvent) {
        if (!poiIDRemoveField.getText().equals(null)) {
            POI poi = POI.poisST.get(Integer.parseInt(poiIDRemoveField.getText()));
            if (poi != null) {
                poi.removePOIsST();
                poisTable.getItems().remove(poi);
                poisObs.remove(poi);
            } else
                System.out.println("Invalid Id!");
        } else
            System.out.println("Insira um Id para remover!");
    }

    /**
     * handler para editar POI
     *
     * @param poisStringCellEditEvent
     */
    public void handleEditPOIAction(TableColumn.CellEditEvent<POI, String> poisStringCellEditEvent) {
        POI poi = (POI) poisTable.getSelectionModel().getSelectedItem();
        int col = poisStringCellEditEvent.getTablePosition().getColumn();
        switch (col) {
            case 0 -> poi.setId(Integer.parseInt(poisStringCellEditEvent.getNewValue()));
            case 1 -> poi.setName(poisStringCellEditEvent.getNewValue());
        }
        poisObs.add(poi);
        poi.editPOIsSTJFX(poisStringCellEditEvent.getNewValue(), null, null);
    }

    /**
     * handler para adicionar Logs
     *
     * @param actionEvent
     */
    public void handleAddLogAction(ActionEvent actionEvent) {
        String[] date = logDateField.getText().split("/");
        Date d = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        Log log = new Log(d, logMessageField.getText());

        logsTable.getItems().add(log);
        logsObs.add(log);
        logDateField.setText("");
        logMessageField.setText("");
    }

    /**
     * handler para criar grafos
     *
     * @param actionEvent
     */
    public void handleCreateGraph(ActionEvent actionEvent) {
        graphGroup.getChildren().clear();
        for (int id : POI.poisST.keys()) {
            System.out.println(POI.poisST.get(id).getClass());
            double x = GROUP_MARGIN + (POI.poisST.get(id).getLocation().getLatitude() - (int) (POI.poisST.get(id).getLocation().getLatitude())) * (800 - GROUP_MARGIN * 2);
            double y = (GROUP_MARGIN + (POI.poisST.get(id).getLocation().getLongitude() - (int) (POI.poisST.get(id).getLocation().getLongitude())) * (400 - GROUP_MARGIN * 2) * (-1));
            System.out.println("x: " + x + " y: " + y);
            Circle circle = new Circle(x, y, radius);

            if (POI.poisST.get(id) instanceof Restauracao) {
                circle.setFill(Color.LIGHTBLUE);
            } else if (POI.poisST.get(id) instanceof BocaIncendio) {
                circle.setFill(Color.RED);
            } else if (POI.poisST.get(id) instanceof Semaforo) {
                circle.setFill(Color.BEIGE);
            } else if (POI.poisST.get(id) instanceof EstacaoCarregamento) {
                circle.setFill(Color.CORAL);
            } else if (POI.poisST.get(id) instanceof Hotelaria) {
                circle.setFill(Color.YELLOW);
            } else if (POI.poisST.get(id) instanceof Park) {
                circle.setFill(Color.GREEN);
            }
            circle.setId("" + id);
            Text text = new Text("" + id);

            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(x - radius);
            stackPane.setLayoutY(y - radius);
            stackPane.getChildren().addAll(circle, text);
            graphGroup.getChildren().add(stackPane);
        }

    }


    /**
     * handler para ler do ficheiro
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleReadFileAction(ActionEvent actionEvent) throws IOException {
        getInputUtilizadorFromFile();
        getInputPOISFromFile();
        this.graph = new EdgeWeightedDigraphNew(POI.poisST.size());
    }

    /**
     * handler para ler o ficheiro binario
     *
     * @param actionEvent
     */
    public void handleReadBinFileAction(ActionEvent actionEvent) {
        usersTable.getItems().clear();
        arrayListUsers.clear();
        readUsersFromBinFile();

        poisTable.getItems().clear();
        arrayListPois.clear();
        readPOIsFromBinFile();

        this.graph = readGrafoFromBinFile();


        usersTable.getItems().addAll(usersObs);
        poisTable.getItems().addAll(poisObs);


        System.out.println("Leu do Ficheiro Binário");
    }

    /**
     * Lê users apartir do ficheiro binario
     */
    private void readUsersFromBinFile() {
        File f = new File(PATH_BINUtilizador);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayListUsers = (ArrayList<User>) ois.readObject();
            usersTable.getItems().addAll(arrayListUsers);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lê Pois apartir do ficheiro binario
     */
    private void readPOIsFromBinFile() {
        File f = new File(PATH_BINPOIs);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayListPois = (ArrayList<POI>) ois.readObject();
            poisTable.getItems().addAll(arrayListPois);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * le o grafo do ficheiro binario
     *
     * @return
     */
    private static EdgeWeightedDigraphNew readGrafoFromBinFile() {
        EdgeWeightedDigraphNew graph = null;
        File f = new File(PATH_BINGrafo);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            graph = (EdgeWeightedDigraphNew) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

    /**
     * handler guarda o ficheiro
     *
     * @param actionEvent
     */
    public void handleSaveFileAction(ActionEvent actionEvent) {
        Node.writeNodesToFile();
        User.writeUsersFile();
    }

    /**
     * handler que guarda ficheiro binario
     *
     * @param actionEvent
     */
    public void handleSaveBinFileAction(ActionEvent actionEvent) {
        saveGrafoBinFile(this.graph);
        savePOIsToBinFile();
        saveUsersToBinFile();
    }

    /**
     * handler que guarda users para ficheiro binario
     */
    private void saveUsersToBinFile() {
        ArrayList<User> userArrayList = new ArrayList<>(usersObs);

        File f = new File(PATH_BINUtilizador);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que guarda pois para ficheiro  binario
     */
    private void savePOIsToBinFile() {
        ArrayList<POI> poiArrayList = new ArrayList<>(poisObs);

        File f = new File(PATH_BINPOIs);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(poiArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que guarda grafos para ficheiro binario
     *
     * @param graph
     */
    private static void saveGrafoBinFile(EdgeWeightedDigraphNew graph) {
        File f = new File(PATH_BINGrafo);

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleAboutAction(ActionEvent actionEvent) {
        System.out.println("Trabalho realizado por:\n \tRui Silva 38170 && T    iago Silva 38217");

    }

    /**
     * handler para procurar top5
     *
     * @param actionEvent
     */
    public void handleUserSearchTOP5(ActionEvent actionEvent) {
        resultUser.clear();
        SeparateChainingHashST<Integer, User> usersReturnados = User.top5UsersWithMostPOIS();
        StringBuilder string = new StringBuilder();
        for (int id : usersReturnados.keys()) {
            string.append(usersReturnados.get(id).getName());
            string.append("\n");
        }
        resultUser.setText(string.toString());
    }

    /**
     * handler que procura por users que visitaram
     *
     * @param actionEvent
     */
    public void handleUserSearchVISTED(ActionEvent actionEvent) {
        resultUser.clear();
        int id = Integer.parseInt(userSearchID.getText());
        User user = User.usersST.get(id);
        if (user != null) {
            Date date1 = new Date(1960, 4, 14);
            Date date9 = new Date(2030, 11, 18);
            StringBuilder string = new StringBuilder();
            ST<Integer, POI> poisReturnados = user.visetedPoisByDate(date1, date9);
            for (int i : poisReturnados.keys()) {
                string.append(poisReturnados.get(i).getName());
                string.append("\n");
            }
            if (!string.isEmpty())
                resultUser.setText(string.toString());
            else
                resultUser.setText("User sem visitas.");
        } else
            resultUser.setText("User inexistente.");
    }

    /**
     * handler que procura por users que nao visitaram
     *
     * @param actionEvent
     */
    public void handleUserSearchNOTVISITED(ActionEvent actionEvent) {
        resultUser.clear();
        int id = Integer.parseInt(userSearchID.getText());
        User user = User.usersST.get(id);
        if (user != null) {
            Date date1 = new Date(1960, 4, 14);
            Date date9 = new Date(2030, 11, 18);
            StringBuilder string = new StringBuilder();

            ST<Integer, POI> poisReturnados = user.nonVisitedPoisByLocal("global", date1, date9);
            for (int i : poisReturnados.keys()) {
                string.append(poisReturnados.get(i).getName());
                string.append("\n");
            }
            if (!string.isEmpty())
                resultUser.setText(string.toString());
            else
                resultUser.setText("User visitou todos os POIS, que campeao!.");
        } else
            resultUser.setText("User inexistente.");
    }

    /**
     * handler  procura users que visitaram Pois
     *
     * @param actionEvent
     */
    public void handlePOISearchVISITS(ActionEvent actionEvent) {
        resulPOI.clear();
        int id = Integer.parseInt(userSearchPOI.getText());
        POI poi = POI.poisST.get(id);
        if (poi != null) {
            Date date1 = new Date(1960, 4, 14);
            Date date9 = new Date(2030, 11, 18);
            StringBuilder string = new StringBuilder();

            ST<Integer, User> usersReturnados = poi.usersWhoVisited(date1, date9);
            for (int i : usersReturnados.keys()) {
                string.append(usersReturnados.get(i).getName());
                string.append("\n");
            }
            if (!string.isEmpty())
                resulPOI.setText(string.toString());
            else
                resulPOI.setText("POI sem visitas.");
        } else
            resulPOI.setText("POI inexistente");
    }

    /**
     * handler que procura top5 pois
     *
     * @param actionEvent
     */
    public void handlePOISearchTOP5(ActionEvent actionEvent) {
        RedBlackBST<Integer, POI> poisReturnados = POI.top5POIsWithMostVisits();
        StringBuilder string = new StringBuilder();
        for (int id : poisReturnados.keys()) {
            string.append(poisReturnados.get(id).getName());
            string.append("\n");
        }
        resulPOI.setText(string.toString());
    }
}