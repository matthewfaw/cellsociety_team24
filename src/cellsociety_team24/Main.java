package cellsociety_team24;

import java.util.ResourceBundle;

import controllers.AppController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.AppResources;

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
