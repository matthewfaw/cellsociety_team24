package cellsociety_team24;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {
	public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private int SIZE=800; ///TEMPORARY. SIZE WILL ACTUALLY BE TAKEN FROM XML PARSER
	private Scene splashScreen;
    private Group splashroot= new Group();
    private String simulationPath=new String();
    private Simulation mySimulation;
    
	 /**
     * Sets up the initial Splash Screen
	 * @return 
     */
    public Scene makeSplashScreen(int width, int height, Stage s) {
		splashScreen= new Scene(splashroot,width, height, Color.BLACK);
		Button fileButton= new Button("Select File");
		fileButton.setLayoutX(width*0.5);
		fileButton.setLayoutY(height*0.5);
		splashroot.getChildren().add(fileButton);
		fileButton.setOnAction(e->fileButtonHandler(s));
		return splashScreen;
	}

	private Object fileButtonHandler(Stage s) {
		FileChooser filexmlChooser=new FileChooser();
		File file =filexmlChooser.showOpenDialog(s);
		if (file!=null){
			simulationPath=file.getPath();
			//Open XML Parser
			OpenSimulator(s);
		}
		return null;
	}
	
	private void OpenSimulator(Stage s) {
		
    	// create your own game here
        mySimulation = new Simulation();
        s.setTitle(mySimulation.getTitle());

        // sets the game's loop
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> mySimulation.step(SECOND_DELAY,SIZE,SIZE));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        
        // attach game to the stage and display it
        Scene scene = mySimulation.init(SIZE, SIZE,animation,s);// init(XMLParse.getSize(),XMLParse.getSize()) 
        s.setScene(scene);
        s.show();

        animation.play();
		
	}

	public String getSimulationPath(){
		return simulationPath;
	}
	
}
