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
public class AdjP extends Phrase{
    private Adjektiv head;

    /**
     * @return the head
     */
    @Override
    public Adjektiv getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Adjektiv head) {
        this.head = head;
    }
}
