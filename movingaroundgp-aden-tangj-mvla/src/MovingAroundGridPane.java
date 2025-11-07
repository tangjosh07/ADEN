import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class MovingAroundGridPane. This class provides an example of how
 * to move items around in a GridPane
 */
public class MovingAroundGridPane extends Application {
	
	/** The t 4. */
	private Polygon t1, t2, t3, t4;   // t1 up, t2 right, t3 down, t4 left
	
	/** The pane. */
	private BorderPane pane;
	
	/** The sp. */
	private StackPane sp;
	
	/** The gp. */
	private GridPane gp;
	
	/** The Constant MAX_X_CELLS. */
	private static final int MAX_X_CELLS = 8;
	
	/** The Constant MAX_Y_CELLS. */
	private static final int MAX_Y_CELLS = 10;
	
	/** The t. */
	private Timeline t;
	
	/** The time. */
	private int time = 0;
	
	/** The duration. */
	private int duration = 1000;  
	
	/** The label to display duration. */
	private Label lblDuration = new Label();
	
	private int cellX, cellY = 0;
	
	/**
	 * Starts the GUI. Initializes the various containers and shapes.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new BorderPane();
		sp = new StackPane();
		// Create the triangles
		makeTriangles();	
		sp.getChildren().addAll(t1,t2,t3,t4);
		gp = new GridPane();
		
		setGridPaneConstraints();
		
		gp.add(sp,0,0);
		pane.setCenter(gp);
		
		HBox btnBox = new HBox(15);
		Button stop = new Button("Stop");
		Button run = new Button("Run");
		Button speedup = new Button("Faster");
		Button slowdown = new Button("Slower");
		stop.setOnAction(e -> t.pause());
		run.setOnAction(e -> {t.setCycleCount(Animation.INDEFINITE); t.play();});
		speedup.setOnAction(e -> changeSpeed(true));
		slowdown.setOnAction(e -> changeSpeed(false));
        btnBox.getChildren().addAll(run,stop,speedup, slowdown, lblDuration);
        pane.setBottom(btnBox);  
        initTimeline();
 		Scene scene = new Scene(pane,400,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Moving Around A GridPane");
		primaryStage.show();
	}
	
	/**
	 * ChangeSpeed. Actions:
	 * 1) pause the current timeline if running. Compare status of timeline to
	 *    Animation.Status.RUNNING
	 * 2) if faster, divide the duration by 2 (USE BITWISE OPERATORS!)
	 *    is not faster, multiply the duration by 2 (USE BITWISE OPERATORS!)
	 * 3) initialize the timeline
	 * 4) if the timeline was running, restart it
	 */
	private void changeSpeed(boolean faster) {
		//TODO: Code this method
		boolean wasRunning = false;
		if (t.getStatus() == Animation.Status.RUNNING) {
			t.pause();
			wasRunning = true;
		}
		if (faster) {
			duration = duration >> 1;
		}
		else {
			duration = duration << 1;
		}
		initTimeline();
		if (wasRunning)
			t.play();
	}
	
	/**
	 * Creates a new timeline. Configure the timeline as follows:
	 *      create a new KeyFrame with Duration = duration and
	 *      	                       stepSim() as the event handler
	 *      set the timeline cycle count to Animation.INDEFINITE
	 *      set the text in lblDuration to reflect the current duration
	 */
	private void initTimeline() {
		//TODO: Code this method
		t = new Timeline(new KeyFrame(Duration.millis(duration), ae -> stepSim()));
		t.setCycleCount(Animation.INDEFINITE);
		lblDuration.setText("Duration = " + String.valueOf(duration));
	}
	
	/**
	 * Sets the grid pane constraints. Each cell will be set to 50x50 pixels
	 */
	public void setGridPaneConstraints() {
		for (int i = 0; i < MAX_X_CELLS; i ++) 
			gp.getColumnConstraints().add(new ColumnConstraints(50));

		for (int i = 0; i < MAX_Y_CELLS; i ++) 
			gp.getRowConstraints().add(new RowConstraints(50));
	}
	
	/**
	 * Make Equilateral Triangles using Polygons. Make one, then use those
	 * points and rotation to create 3 other orientations that are multiples of
	 * 90 degrees apart (to represent up, down, left and right movement). 
	 * The default triangle will be placed in 0,0 (top left corner) so will
	 * be moving right - make this one visible and all others invisible
	 */
	public void makeTriangles() {
		t1 = new Polygon();  //Up
		t1.getPoints().addAll(5.0,20.0,25.0,20.0,15.0,20-10*Math.pow(3,0.5));
		t1.setStroke(Color.RED);
		t1.setStrokeWidth(2);
		t1.setFill(Color.RED);
		t2 = new Polygon();  // Right
		t2.getPoints().addAll(t1.getPoints());
		t2.setRotate(90);
		t2.setStroke(Color.CYAN);
		t2.setStrokeWidth(2);
		t2.setFill(Color.CYAN);
		t3 = new Polygon(); // Down
		t3.getPoints().addAll(t1.getPoints());
		t3.setRotate(180);
		t3.setStroke(Color.PURPLE);
		t3.setStrokeWidth(2);
		t3.setFill(Color.PURPLE);
		t4 = new Polygon();  //Left
		t4.getPoints().addAll(t1.getPoints());
		t4.setRotate(270);
		t4.setStroke(Color.GREEN);
		t4.setStrokeWidth(2);
		t4.setFill(Color.GREEN);
		t1.setVisible(false);
		t2.setVisible(true);
		t3.setVisible(false);
		t4.setVisible(false);
	}
	
	/**
	 * Step sim. The cell and direction will be calculated 
	 * based on the current time after incrementing by 1 tick;
	 * Since the directions are known, this is a pretty simple 
	 * implementation. The triangles will "move" around the perimeter
	 * of a gridpane that is MAX_X_CELLS by MAX_Y_CELLS.
	 */
	private void stepSim() {
		gp.setGridLinesVisible(true);
		time ++;
		int modTime = time%(2*(MAX_X_CELLS-1 + MAX_Y_CELLS-1));
		// TODO: Code this method. Assume that at time == 0, triangle is
		//       in top left GridPane cell, facing right.
		//       need to calculate next cell, direction change and the
		//       corresponding triangle to display
		System.out.println(modTime);
		gp.getChildren().removeAll(sp);
		t1.setVisible(false);
		t2.setVisible(false);
		t3.setVisible(false);
		t4.setVisible(false);
		if (modTime > 0 && modTime < 8) {
			t2.setVisible(true);
			cellX++;
		}
		else if (modTime > 7 && modTime < 17) {
			t3.setVisible(true);
			cellY++;
		}
		else if (modTime > 16 && modTime < 24) {
			t4.setVisible(true);
			cellX--;
		}
		else {
			t1.setVisible(true);
			cellY--;
		}
		gp.add(sp, cellX, cellY);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	

}
