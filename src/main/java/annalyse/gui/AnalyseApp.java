/*
 * AnalyseApp.java
 */

package annalyse.gui;

import annalyse.logic.Anna;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class AnalyseApp extends SingleFrameApplication {

    Anna anna = new Anna(this);

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new AnalyseView(this));
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("file.decoding", "UTF-8");
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AnalyseApp
     */
    public static AnalyseApp getApplication() {
        return Application.getInstance(AnalyseApp.class);
    }

    public Anna getAnna(){
        return this.anna;
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(AnalyseApp.class, args);
    }
}
