/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortfolgen;

import annalyse.grammatik.wortarten.*;

/**
 * @Polski Wypowiedzenie
 * @author Maciej Niemczyk
 */
public class Phrase {
    private Wort head;
    private Phrase phrase;

    /**
     * @return the head
     */
    public Wort getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Wort head) {
        this.head = head;
    }

    /**
     * @return the phrase
     */
    public Phrase getPhrase() {
        return phrase;
    }

    /**
     * @param phrase the phrase to set
     */
    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }
}
