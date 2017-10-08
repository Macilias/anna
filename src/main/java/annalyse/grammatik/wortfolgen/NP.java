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
public class NP extends Phrase {
    private Nomen head;

    /**
     * @return the head
     */
    @Override
    public Nomen getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Nomen head) {
        this.head = head;
    }
}
