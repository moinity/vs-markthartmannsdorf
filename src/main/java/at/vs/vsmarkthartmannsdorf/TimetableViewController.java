package at.vs.vsmarkthartmannsdorf;

import at.vs.vsmarkthartmannsdorf.data.SchoolClass;
import at.vs.vsmarkthartmannsdorf.data.Teacher;
import at.vs.vsmarkthartmannsdorf.data.TimetableDay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TimetableViewController implements Initializable {
    @FXML
    public ListView<SchoolClass> timeTableList;

    @FXML
    public BorderPane root;

    public MainController parent;

    private ObservableList<SchoolClass> classes;

    @FXML
    private TableView timeTableView;
    private TableColumn colTime;
    private TableColumn colMonday;
    private TableColumn colTuesday;
    private TableColumn colWednesday;
    private TableColumn colThursday;
    private TableColumn colFriday;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTime = new TableColumn("");
        colMonday = new TableColumn("Monday");
        colTuesday = new TableColumn("Tuesday");
        colWednesday = new TableColumn("Wednesday");
        colThursday = new TableColumn("Thursday");
        colFriday = new TableColumn("Friday");
        timeTableView.getColumns().addAll(colTime,colMonday, colTuesday,
                colWednesday, colThursday, colFriday);

        // List of Content
        final ObservableList<TimetableDay> data = FXCollections.observableArrayList(
                new TimetableDay("", "", "", "","", "", "")
        );

        colTime.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("time"));
        colMonday.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("monday"));
        colTuesday.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("tuesday"));
        colWednesday.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("wednesday"));
        colThursday.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("thursday"));
        colFriday.setCellValueFactory(new PropertyValueFactory<TimetableDay, String>("friday"));
        timeTableView.setItems(data);

    }

    public void setParent(MainController parent) {
        this.parent = parent;
    }

    public void setItems(ObservableList<SchoolClass> classes){
        timeTableList.setItems(classes);
        this.classes = timeTableList.getItems();
    }

    @FXML
    protected void addSubject(){
        if (((VBox) root.getCenter()).getChildren().size() == 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("timetable-dialog.fxml"));
                VBox vBox = fxmlLoader.load();
                ((TimetableViewController) fxmlLoader.getController()).setParent(this);
                ((TableView) root.getCenter()).getItems().add(vBox);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void removeSubject() {

    }

    private void setParent(TimetableViewController timetableViewController) {
    }

    /*public void dismountForm(){
        ((VBox) root.getCenter()).getChildren().clear();
    }

    public void submitForm(String classname, Teacher teacher){
        SchoolClass schoolClass = new SchoolClass(classname, teacher);

        dismountForm();
        parent.addClasses(schoolClass);
        timetableList.setItems(classes);
    }*/

    public ObservableList<SchoolClass> getClasses() {
        return classes;
    }

    public MainController getParent() {
        return parent;
    }

    public void onChangeTimeTable() {

    }
}
