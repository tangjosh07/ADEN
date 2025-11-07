import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControlCircle extends Application {
	private CirclePane circlePane = new CirclePane();
	
	public void start(Stage primaryStage) {
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		Button btEnlarge = new Button("Enlarge");
		Button btShrink = new Button("Shrink");
		// TODO #2: register the button handlers - want to call the correct methods in circlePane
		btEnlarge.setOnAction(ev -> circlePane.enlarge());
		btShrink.setOnAction(ev -> circlePane.shrink());
		
		hBox.getChildren().addAll(btEnlarge,btShrink);
		
		BorderPane borderPane = new BorderPane();  // border pane - center for circle, bottom for buttons
		borderPane.setCenter(circlePane);
		borderPane.setBottom(hBox);
		BorderPane.setAlignment(hBox,Pos.CENTER);
		
		Scene scene = new Scene(borderPane,200,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Control Circle Size");
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
		

	}

}
