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
public class VN extends Phrase{
    private Verb head;

    /**
     * @return the head
     */
    @Override
    public Verb getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Verb head) {
        this.head = head;
    }
}
