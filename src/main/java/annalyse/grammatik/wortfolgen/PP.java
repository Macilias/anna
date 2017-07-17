/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortfolgen;

import annalyse.grammatik.wortarten.*;

/**
 *
 * @author Maciej Niemczyk
 */
public class PP extends Phrase{
    private Präposition head;

    /**
     * @return the head
     */
    @Override
    public Präposition getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Präposition head) {
        this.head = head;
    }
}
