import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class CirclePane.
 */
public class CirclePane extends Pane {
   private Circle circle = new Circle(50);
   private ControlCircle gui;
   
   /**
    * Instantiates a new circle pane.
    */
   public CirclePane() {
	   getChildren().add(circle);
	   circle.setStroke(Color.BLACK);
	   circle.setFill(Color.WHITE);
	   circle.centerXProperty().bind(this.widthProperty().divide(2));
	   circle.centerYProperty().bind(this.heightProperty().divide(2));
	   
   }
   
   /**
    * Enlarge the circle by 2 units
    */
   public void enlarge() {
	   // increase the radius, but think about what error checking you might need
	   // TODO #1a: Write this method
	   double radius = circle.getRadius();
	   if (radius+2 < this.getHeight()/2 && radius+2 < this.getWidth()/2) {
		   circle.setRadius(radius+2);
	   }
   }
   
   /**
    * Shrink the circle by 2 units.
    */
   public void shrink() {
	   // decrease the radius, but think about what error checking you might need
	   // TODO #1b: Write this method...
	   if (circle.getRadius() > 2)
		   circle.setRadius(circle.getRadius()-2);
   }
}
