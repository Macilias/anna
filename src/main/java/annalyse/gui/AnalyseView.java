/*
 * AnalyseView.java
 */

package annalyse.gui;

import org.jdesktop.application.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
//FIXME
//import owl2prefuse.Demo;

/**
 * The application's main frame.
 */
public class AnalyseView extends FrameView {

    public AnalyseView(SingleFrameApplication app) {
        super(app);

        initComponents();
        //NUR ZUR SCHNELLEN START DES WORTEDITORS
//        showWordEditor();

        // status bar initialization - message timeout, idle icon and busy animation, etc

        ApplicationContext context = Application.getInstance().getContext();
        ResourceMap resourceMap = context.getResourceMap(AnalyseView.class);
//        ResourceMap resourceMap = getResourceMap();
//        ResourceMap resourceMap = getContext().getResourceMap(AnalyseView.class);
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);
        outprints = new LinkedList<String>();
//        FIXME
//        ontologieView = new Demo(AnalyseApp.getApplication().getAnna().getModel(),
//                AnalyseApp.getApplication().getAnna().getBrain());
        //NUR TEMPORAIR:
        outprints.add("scanner");
        outprints.add("parser");
        outprints.add("semantic");
        outprints.add("verbous");

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    public void reciveAnswer(String answer){
        outPanel.setText(outPanel.getText()+answer);
        outPanel.setCaretPosition(outPanel.getDocument().getLength());
    }

    @Action
    public Task TestItBaby(){
        return AnalyseApp.getApplication().getAnna().recive(this, inPanel.getText(), outprints);
    }

    private class TestItBabyTask extends org.jdesktop.application.Task<Object, Void> {
        TestItBabyTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to TestItBabyTask fields, here.
            super(app);
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            return null;  // return your result
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
        }
    }

    @Action
    public void clearOutput(){
        outPanel.setText("");
    }

    @Action
    public void changeDepth(){
        int depth = Integer.valueOf(depthField.getText());
        AnalyseApp.getApplication().getAnna().changeDepth(depth);
    }

    @Action
    public void gnGraphViz(){
        if(graphVizCheckBox.isSelected()){
            AnalyseApp.getApplication().getAnna().performGraphViz(true);
        }else{
            AnalyseApp.getApplication().getAnna().performGraphViz(false);
        }
    }

    @Action
    public void showWordEditor() {
        if (wordEditor == null) {
            JFrame mainFrame = AnalyseApp.getApplication().getMainFrame();
            wordEditor = new CreateWortView(mainFrame);
            wordEditor.setLocationRelativeTo(mainFrame);
        }
        AnalyseApp.getApplication().show(wordEditor);
//        AnalyseApp.getApplication().show(this);
    }

    @Action
    public void showOptionView() {
        if (optionView == null) {
            optionView = new OptionenView(AnalyseApp.getApplication());
        }
        AnalyseApp.getApplication().show(optionView);
    }

//FIXME
//    /**
//     *
//     */
//    @Action
//    public void showOntologieView() {
//        if (!ontologieView.isVisible()) {
//            ontologieView.refreshModel();
//            ontologieView.setVisible(true);
//        }
//        else{
//            ontologieView.setVisible(false);
//        }
//    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AnalyseApp.getApplication().getMainFrame();
            aboutBox = new AnalyseAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AnalyseApp.getApplication().show(aboutBox);
    }

    @Action
    public void scannerOutput(){
        if(scannerCheckBox.isSelected()){
            if(!outprints.contains("scanner")) outprints.add("scanner");
        }else{
            if(outprints.contains("scanner"))  outprints.remove("scanner");
        }
    }

    @Action
    public void parserOutput(){
        if(parserCheckBox.isSelected()){
            if(!outprints.contains("parser")) outprints.add("parser");
        }else{
            if(outprints.contains("parser"))  outprints.remove("parser");
        }
    }

    @Action
    public void semanticOutput(){
        if(semanticCheckBox.isSelected()){
            if(!outprints.contains("semantic")) outprints.add("semantic");
        }else{
            if(outprints.contains("semantic"))  outprints.remove("semantic");
        }
    }

    @Action
    public void verbousOutput(){
        if(verbousCheckBox.isSelected()){
            if(!outprints.contains("verbous")) outprints.add("verbous");
        }else{
            if(outprints.contains("verbous"))  outprints.remove("verbous");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inPanel = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        outPanel = new javax.swing.JTextPane();
        okButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        depthField = new javax.swing.JTextField();
        graphVizCheckBox = new javax.swing.JCheckBox();
        clearButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        extrasMenu = new javax.swing.JMenu();
        wordEditorMenuItem = new javax.swing.JMenuItem();
        optionsMenuItem = new javax.swing.JMenuItem();
        prefuseMenuItem = new javax.swing.JMenuItem();
        Ausgabe = new javax.swing.JMenu();
        scannerCheckBox = new javax.swing.JCheckBoxMenuItem();
        parserCheckBox = new javax.swing.JCheckBoxMenuItem();
        semanticCheckBox = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        verbousCheckBox = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        inPanel.setName("inPanel"); // NOI18N
        inPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inPanelKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(inPanel);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        outPanel.setDragEnabled(false);
        outPanel.setName("outPanel"); // NOI18N
        jScrollPane2.setViewportView(outPanel);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(annalyse.gui.AnalyseApp.class).getContext().getActionMap(AnalyseView.class, this);
        okButton.setAction(actionMap.get("TestItBaby")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(annalyse.gui.AnalyseApp.class).getContext().getResourceMap(AnalyseView.class);
        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        depthField.setText(resourceMap.getString("depthField.text")); // NOI18N
        depthField.setAction(actionMap.get("changeDepth")); // NOI18N
        depthField.setName("depthField"); // NOI18N

        graphVizCheckBox.setAction(actionMap.get("gnGraphViz")); // NOI18N
        graphVizCheckBox.setText(resourceMap.getString("graphVizCheckBox.text")); // NOI18N
        graphVizCheckBox.setName("graphVizCheckBox"); // NOI18N

        clearButton.setAction(actionMap.get("clearOutput")); // NOI18N
        clearButton.setText(resourceMap.getString("clearButton.text")); // NOI18N
        clearButton.setName("clearButton"); // NOI18N

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(depthField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(graphVizCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 151, Short.MAX_VALUE)
                        .add(clearButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(graphVizCheckBox)
                    .add(depthField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(clearButton))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        extrasMenu.setText(resourceMap.getString("toolMenu.text")); // NOI18N
        extrasMenu.setName("toolMenu"); // NOI18N

        wordEditorMenuItem.setAction(actionMap.get("showWordEditor")); // NOI18N
        wordEditorMenuItem.setText(resourceMap.getString("wordEditorMenuItem.text")); // NOI18N
        wordEditorMenuItem.setName("wordEditorMenuItem"); // NOI18N
        extrasMenu.add(wordEditorMenuItem);

        optionsMenuItem.setAction(actionMap.get("showOptionView")); // NOI18N
        optionsMenuItem.setText(resourceMap.getString("optionsMenuItem.text")); // NOI18N
        optionsMenuItem.setName("optionsMenuItem"); // NOI18N
        extrasMenu.add(optionsMenuItem);

        prefuseMenuItem.setAction(actionMap.get("showOntologieView")); // NOI18N
        prefuseMenuItem.setText(resourceMap.getString("prefuseMenuItem.text")); // NOI18N
        prefuseMenuItem.setName("prefuseMenuItem"); // NOI18N
        extrasMenu.add(prefuseMenuItem);

        menuBar.add(extrasMenu);

        Ausgabe.setText(resourceMap.getString("Ausgabe.text")); // NOI18N
        Ausgabe.setName("Ausgabe"); // NOI18N

        scannerCheckBox.setAction(actionMap.get("scannerOutput")); // NOI18N
        scannerCheckBox.setSelected(true);
        scannerCheckBox.setText(resourceMap.getString("scannerCheckBox.text")); // NOI18N
        scannerCheckBox.setName("scannerCheckBox"); // NOI18N
        Ausgabe.add(scannerCheckBox);

        parserCheckBox.setAction(actionMap.get("parserOutput")); // NOI18N
        parserCheckBox.setSelected(true);
        parserCheckBox.setText(resourceMap.getString("parserCheckBox.text")); // NOI18N
        parserCheckBox.setName("parserCheckBox"); // NOI18N
        Ausgabe.add(parserCheckBox);

        semanticCheckBox.setAction(actionMap.get("semanticOutput")); // NOI18N
        semanticCheckBox.setSelected(true);
        semanticCheckBox.setText(resourceMap.getString("semanticCheckBox.text")); // NOI18N
        semanticCheckBox.setName("semanticCheckBox"); // NOI18N
        Ausgabe.add(semanticCheckBox);

        jSeparator1.setName("jSeparator1"); // NOI18N
        Ausgabe.add(jSeparator1);

        verbousCheckBox.setAction(actionMap.get("verbousOutput")); // NOI18N
        verbousCheckBox.setSelected(true);
        verbousCheckBox.setText(resourceMap.getString("verbousCheckBox.text")); // NOI18N
        verbousCheckBox.setName("verbousCheckBox"); // NOI18N
        Ausgabe.add(verbousCheckBox);

        menuBar.add(Ausgabe);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .add(121, 121, 121)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, statusPanelLayout.createSequentialGroup()
                        .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(statusAnimationLabel)
                        .addContainerGap())))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(statusPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
                        .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(statusMessageLabel)
                            .add(statusAnimationLabel))
                        .add(3, 3, 3))
                    .add(statusPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void inPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inPanelKeyPressed
        //System.out.println("passiert: "+evt.toString());
        if(evt.getKeyCode()==10){
            //actionMap.get("TestItBaby")
            okButton.doClick();
//            annalyse.grammatik.wortarten.Personalpronomen v = new annalyse.grammatik.wortarten.Personalpronomen();
//            outPanel.setText(v.getWortart());
            //AnalyseApp.getApplication().reciveMassage(inPanel.getText());
        }
        if(evt.getKeyCode()==16&&evt.getKeyLocation()==evt.KEY_LOCATION_RIGHT){
            TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
            outPanel.setText(taskMonitor.getTasks().toString());
        }
    }//GEN-LAST:event_inPanelKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Ausgabe;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField depthField;
    private javax.swing.JMenu extrasMenu;
    private javax.swing.JCheckBox graphVizCheckBox;
    private javax.swing.JTextPane inPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton okButton;
    private javax.swing.JMenuItem optionsMenuItem;
    private javax.swing.JTextPane outPanel;
    private javax.swing.JCheckBoxMenuItem parserCheckBox;
    private javax.swing.JMenuItem prefuseMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JCheckBoxMenuItem scannerCheckBox;
    private javax.swing.JCheckBoxMenuItem semanticCheckBox;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JCheckBoxMenuItem verbousCheckBox;
    private javax.swing.JMenuItem wordEditorMenuItem;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    LinkedList<String> outprints;

    private JDialog wordEditor;
    private JDialog aboutBox;
    private FrameView optionView;
//    FIXME
//    private Demo ontologieView;

}
