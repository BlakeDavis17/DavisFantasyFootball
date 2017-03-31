package davis.ff;

import java.io.IOException;

import davis.ff.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	// Constructor
		public Main() {
			
			
		}	

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("The Mecca of Fantasy Football");
		
		//set the application icon
		this.primaryStage.getIcons().add(new Image("file:footballfire.jpg"));
		
		initRootLayout();
		
	}

	//returns the main stage
	//@return
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// Initializes root layout
	public void initRootLayout() {
		try{
			// Load root layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/FFRootLayout.fxml"));
			rootLayout = loader.load();

			// Show the scene containing the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			RootLayoutController controller = loader.getController();
			controller.setMain(this);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

		launch(args);
	}

	


}
