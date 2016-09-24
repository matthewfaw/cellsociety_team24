package cellsociety_team24;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	public static final int SIZE =800;
	public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    private Simulation mySimulation;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		// create your own game here
        SplashScreen mysplashscreen = new SplashScreen();
        Scene myScene=mysplashscreen.makeSplashScreen(SIZE,SIZE, s);
        s.setScene(myScene);
        s.show();
		
	}

}
