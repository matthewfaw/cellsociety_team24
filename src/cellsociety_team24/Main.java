package cellsociety_team24;

import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The purpose of this class is simply to launch the project.
 * It is intended to be as simple as possible.  As such, this file should not
 * be changed.
 * All initialization set up is passed to the App Controller.
 * @author matthewfaw and Guhan
 *
 */
public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		AppController appController = new AppController();
    	appController.init(s);
	}

}
