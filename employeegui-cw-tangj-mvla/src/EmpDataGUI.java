/**
 * 
 */


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Scott
 *
 */
public class EmpDataGUI extends Application {
	private BorderPane main = new BorderPane();
    private GridPane grid = new GridPane();	
    private VBox buttons = new VBox(10);
    private HBox hbox = new HBox(10);
    private ListController controller = new ListController();
    private BorderPane view = new BorderPane();
    private ScrollPane scroll = new ScrollPane();
    private Alert alert = new Alert(AlertType.WARNING);
    private ToggleGroup toggle = new ToggleGroup();
    private RadioButton engineering, marketing, manufacturing,finance,humanResources,customerSupport,management;
    // TODO #1:
    // create private TextField variables for Name, SSN, Salary and Years
    // so that they can be accessed directly by methods inside this class.
	private TextField lastName,firstName,ssn,age,pronouns,salary,years;
    @Override
    public void start(Stage primaryStage) {
    
    	Scene scene = new Scene(main,400,500);
    	Scene scene2 = new Scene(view,400,400);
    	main.setCenter(grid);
    	main.setBottom(hbox);
	// TODO #2:
    	// create Labels for Name, SSN, Salary and Years
    	Label title = new Label("EMPLOYEE DATA ENTRY");
    	Label lastNameLabel = new Label("Last Name: ");
    	Label firstNameLabel = new Label("First Name: ");
    	Label ssnLabel = new Label("SSN: ");
    	Label ageLabel = new Label("Age: ");
    	Label pronounsLabel = new Label("Pronouns: ");
    	Label salaryLabel = new Label("Salary: ");
    	Label yearsLabel = new Label("Years: ");
    	Label deptLabel = new Label("Dept: ");
    	
    	
    	main.setTop(title);
    	
	// TODO #4
    	// instantiate (new) TextFields (already declared above) for Name, SSN, Salary and Years
    	lastName = new TextField();
    	firstName = new TextField();
    	ssn = new TextField();
    	age = new TextField();
    	pronouns = new TextField();
    	salary = new TextField();
    	years = new TextField();
	// TODO #5
        // Create Add Employee Button, and write the setOnAction handler to call the controller
    	// to add the new Employee data
    	
    	engineering = new RadioButton("Engineering");
    	marketing = new RadioButton("Marketing/Sales");
    	manufacturing = new RadioButton("Manufacturing");
    	finance = new RadioButton("Finance");
    	humanResources = new RadioButton("Human Resources");
    	customerSupport = new RadioButton("Customer Support");
    	management = new RadioButton("Management");
    	buttons.getChildren().addAll(engineering,marketing,manufacturing,finance,humanResources,customerSupport,management);
    	toggle.getToggles().addAll(engineering,marketing,manufacturing,finance,humanResources,customerSupport,management);
    	engineering.setSelected(true);
    	alert.setHeaderText("Add Employee Failed");
    	Button addEmployee = new Button("Add Employee Button");
    	addEmployee.setOnAction(ev -> addEmployee());
	// TODO #6
    	// add all the labels, textfields and button to gridpane main. refer to the slide
    	// for ordering...
    	grid.add(lastNameLabel, 0, 0);
    	grid.add(firstNameLabel, 0, 1);
    	grid.add(ssnLabel, 0, 2);
    	grid.add(ageLabel, 0, 3);
    	grid.add(pronounsLabel, 0, 4);
    	grid.add(salaryLabel, 0, 5);
    	grid.add(yearsLabel, 0, 6);
    	grid.add(deptLabel, 0, 7);
    	grid.add(lastName, 1, 0);
    	grid.add(firstName, 1, 1);
    	grid.add(ssn, 1, 2);
    	grid.add(age, 1, 3);
    	grid.add(pronouns, 1, 4);
    	grid.add(salary, 1, 5);
    	grid.add(years, 1, 6);
    	grid.add(buttons, 1, 7);
    	
    	Label data = new Label("Employee Data");
    	view.setTop(data);
    	Button back = new Button("Back");
    	back.setOnAction(ev -> primaryStage.setScene(scene));
    	view.setCenter(scroll);
    	
    	Button viewEmployees = new Button("View Employees");
    	viewEmployees.setOnAction(ev -> {primaryStage.setScene(scene2);
    		viewEmployeeDB();
    	});
    	
    	Button saveDB = new Button("Save Employee DB");
    	saveDB.setOnAction(ev -> controller.saveEmployeeData());
    	
    	hbox.getChildren().addAll(addEmployee,viewEmployees,saveDB);
    	
    	Button sortByName = new Button("Sort By Name");
    	Button sortByID = new Button("Sort By ID");
    	Button sortBySalary = new Button("Sort By Salary");
    	sortByName.setOnAction(ev -> {controller.sortByName();
    		viewEmployeeDB();});
    	sortByID.setOnAction(ev -> {controller.sortByID();
    		viewEmployeeDB();});
    	sortBySalary.setOnAction(ev -> {controller.sortBySalary();
    		viewEmployeeDB();});
    	
    	HBox sortButtons = new HBox(10);
    	sortButtons.getChildren().addAll(back,sortByName,sortByID,sortBySalary);
    	view.setBottom(sortButtons);
    	
    	
    	primaryStage.setTitle("Employees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // don't worry about this yet - part of part2
    private void viewEmployeeDB() {
    	String[] empDataStr = controller.getEmployeeDataStr();
    	ListView lv = new ListView<>(FXCollections.observableArrayList(empDataStr));
    	lv.setPrefWidth(400);
    	scroll.setContent(lv);
    }
    
    public void addEmployee() {
    	String msg = controller.addEmployee(firstName.getText(),lastName.getText(), ssn.getText(),age.getText(),pronouns.getText(), salary.getText(), years.getText(),((RadioButton)toggle.getSelectedToggle()).getText());
    	if (msg.equals("")) {
    		lastName.clear();
    		firstName.clear();
    		ssn.clear();
    		age.clear();
    		pronouns.clear();
    		salary.clear();
    		years.clear();
    		engineering.setSelected(true);
    	}
    	else {
    		alert.setContentText(msg);
        	alert.showAndWait();
    	}
    }
    
  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

} ;
