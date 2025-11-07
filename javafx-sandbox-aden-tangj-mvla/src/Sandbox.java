import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sandbox extends Application {

	private Pane pane;
	int count = 0;
	
	@Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    pane = new Pane();
	// TODO #3- You are going to create a variety of shapes with different attributes...
    Circle circle = new Circle(200,200,100);
    circle.setFill(Color.BLUE);
    Rectangle rectangle = new Rectangle(700,500,200,200);
    Text t = new Text(500,500,"HELLO");
    Ellipse f = new Ellipse(750,400,100,200);
    f.setFill(Color.ALICEBLUE);
	// TODO #5 - How about a couple of buttons??? remember to add them to the pane!
	Button b = new Button("Press Me");
	b.setLayoutX(300);
	b.setLayoutY(600);
	Button delete = new Button("Delete First");
	delete.setLayoutX(500);
	delete.setLayoutY(500);
	// TODO #6 - Let's make these buttons useful by defining event handlers using
	//           the setOnAction method().
	b.setOnAction(e -> incButtonCount());
	delete.setOnAction(e -> deleteFirstButton());
	// TODO #1 - now need a place to put them - how about the pane?
    
	pane.getChildren().addAll(circle,rectangle,t,f,b,delete);
	// TODO #4 - add the children to the pane....
	
	
	// TODO #2 - create the scene with a size of 1000x800
    Scene scene = new Scene(pane,1000,800);
    primaryStage.setTitle("JavaFX Sandbox"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
	
	private void incButtonCount() {
		System.out.println("Press Me button ahs been pressed " + (++count)+" times");
	}
	
	private void deleteFirstButton() {
		pane.getChildren().remove(0);
	}
  
  public static void main(String[] args) { 
    launch(args);
  }
}

