package cellsociety_team24;

import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

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
