package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ApplicationWindow  extends Application {
	private static Stage stage;
	private static GameScene prevScene;
	private static GameScene scene;
	private static boolean updated = false;
	
 	public void launchWindow(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	this.setStage(stage);
    	GameScene connectScene = new ConnectScene(stage);
    	this.scene = connectScene;
        
        //Setting title to the Stag                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       e 
        this.getStage().setTitle("Pacman - The Game"); 
           
        //Adding scene to the stage 
        this.getStage().setScene(this.getScene().scene); 
           System.out.println(this.getScene());
        //Displaying the contents of the stage 
        this.getStage().show();
        
     // long running operation runs on different thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                    	gotoHostJoinScene();
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    if(updated==true) {
                        Platform.runLater(updater);
                        updated = false;
                    }
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        
    }

	private void gotoHostJoinScene() {
		prevScene.rootStage.setScene(scene.scene);
	}

	/**
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public static void setStage(Stage stage) {
		ApplicationWindow.stage = stage;
	}

	/**
	 * @return the scene
	 */
	public static GameScene getScene() {
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public static void setScene(GameScene scene) {
		ApplicationWindow.prevScene = ApplicationWindow.scene; 
		ApplicationWindow.scene = scene;
		ApplicationWindow.updated = true;
	}
		
}
