/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic.compiler;

import annalyse.grammatik.wortarten.Wort;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Maciej Niemczyk
 */
public class Opportunities {
    private LinkedList<Wort> opportunities;

    public Opportunities(){
        opportunities = new LinkedList<Wort>();
    }
    /**
     * @return the opportunities
     */
    public List<Wort> getOpportunities() {
        return opportunities;
    }

    /**
     * @param opportunities the opportunities to set
     */
    public void setOpportunities(LinkedList<Wort> opportunities) {
        this.opportunities = opportunities;
    }

    public void add(Wort w){
        opportunities.add(w);
    }

    public void addAll(List<Wort> o){
        opportunities.addAll(o);
    }
}
