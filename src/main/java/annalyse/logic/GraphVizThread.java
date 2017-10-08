/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic;

import java.util.StringTokenizer;
//import germanet.HyperonymGraph;
import org.jdesktop.application.Task;
import org.jdesktop.application.Application;

/**
 *
 * @author Maciej Niemczyk
 */
class GraphVizThread extends Task {
    Anna caller;
    boolean cancelled = false;
    int depth = 1;
    String out;
    String message;

    public GraphVizThread(Application app){
        super(app);
    }

    public void recive(Anna caller, String message){
        System.out.println("Anna recived following Message: "+message);
        this.caller = caller;
        this.message = message;
    }

    public void cancell(){
        this.cancelled = true;
    }

    @Override
    protected Object doInBackground() throws Exception {
        if(!message.equals("")){
                System.out.println("ThinkThread recived following Message: "+message);
                StringTokenizer st = new StringTokenizer(message);
                int count = 0;
                while (st.hasMoreTokens()&&!cancelled) {
                    count ++;
                    String dateipfad = new String("out"+count+".dot");
//                    out = HyperonymGraph.apiWork(st.nextToken(),depth,dateipfad);
                    //System.out.println(out);
                    this.caller.answer(out);
                }
            }else{
                System.out.println("Welche Nachricht denn?");
            }
        return out;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }
}
