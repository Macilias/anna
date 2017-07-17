/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic.compiler;

import annalyse.db.Connection;
import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import annalyse.grammatik.attribute.ressources.WAtt;
import annalyse.grammatik.wortarten.*;
import annalyse.logic.Anna;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Der Scanner zerlegt eine Nachricht in Tokens welche innerhalb der MNGeramWords DB
 * Identifiziert werden konnten. Diese werden pro Token zu einer Opportunitäts Liste
 * Zusammengefasst und bilden als Liste von Opportunitäten die Ausgabe des Scanners.
 * Aufgabe: String -> Tokenstrom (Tokenerkennung)
 * Zusätzlich werden simple Token reduziert wie: am + superlativ
 * @author Maciej Niemczyk
 */
public class Scanner {
    Anna anna;
    private EntityManager em;
    LinkedList<String> missing = new LinkedList<String>();

    public Scanner(Anna anna){
        this.anna = anna;
        if(WAtt.getLanguage()==0){
            em = anna.getEMGER();
        }else{
            em = anna.getEMPOL();
        }
    }


    public List<Opportunities> scan(String message){
        LinkedList<Opportunities> out = new LinkedList<Opportunities>();
        StringTokenizer st = new StringTokenizer(message);
        List<Interpunktion> interpunktionen = em.createNamedQuery("Interpunktion.findAll").getResultList();
        //für jeden Token besteht die Möglichkeit dass eine oder zwei Opportunitätslisten
        //der out Menge hinzugefügt werden müssen (eine wenn kein Interpunktion dirn war, zwei sonst)
        while (st.hasMoreTokens()) {
            Opportunities o = new Opportunities();
            Opportunities post = null;
            String token = st.nextToken();
            for(Interpunktion i : interpunktionen){
                if(token.startsWith(i.getLexem())){
                    token = token.replace(i.getLexem(), "");
                    Opportunities ointer = new Opportunities();
                    ointer.add(i);
                    out.add(ointer);
                    System.out.println("Interpunktion am Anfang: "+i.getLexem());
                    break;
                }
                if(token.endsWith(i.getLexem())){
                    token = token.replace(i.getLexem(), "");
                    post = new Opportunities();
                    post.add(i);
                    System.out.println("Interpunktion am Ende: "+i.getLexem());
                    break;
                }    
            }
            o = lookup(token);
            if(o.getOpportunities().size()==0){
                this.missing.add(token);
                System.out.println("Der Token war: "+token);
                System.out.println("Seine Ergebnissliste war "+o.getOpportunities().size()+" elementrig");
            }
            if(WAtt.getLanguage()==0){
                //Führe einfache Reduktionen aus
                //Reduziere Superlativ:
                if(reduceSuperlativ(out,o)) out.remove(out.size()-1);
                //Erkenne schwache oder gemischte Felxion
                if(reduceAdjektivFelxion(out,o)) out.remove(out.size()-1);
            }else{

            }
            out.add(o);
            if(post!=null){
                out.add(post);
            }
            post = null;
        }
        return out;
    }

    public boolean reduceSuperlativ(LinkedList<Opportunities> out, Opportunities o){
        boolean remove = false;
        boolean warAm = false;
        if(out.size()>0){
            Wort wPrä = null;
            Opportunities outo = out.getLast();
            if(outo.getOpportunities().size()>0) wPrä = outo.getOpportunities().get(0);
            if(wPrä!=null&&wPrä.getLexem().equalsIgnoreCase("am")){
                warAm = true;
            }
        }
        for (ListIterator it = o.getOpportunities().listIterator(); it.hasNext();){
            Wort w = (Wort)it.next();
            if(w.getWortart().equals("Adjektiv")&&((Adjektiv)w).getSteigerung().equals("supe")){
                if(((Adjektiv)w).getWortunterart().equals("präd")){
                    if(warAm){
                        w.setLexem("am "+w.getLexem());
                        remove = true;
                    }else{
                        it.remove();
                    }
                }else{
                    if(warAm) it.remove();
                }
            }
        }
        return remove;
    }

    //Die Methode erkännt schwache oder gemischte Flexion eines Adjektives und fügt
    //den Artikel mit den Adjektiv zusammen. Darüberhinaus werden Opportunitäten einer
    //schwachen oder gemischten Flexion, beim nicht Vorhandensein eines Arikels entfernt.
    public boolean reduceAdjektivFelxion(LinkedList<Opportunities> out, Opportunities o){
        boolean warBestimmterArtikel = false;
        boolean warGemischt = false;
        boolean remove= false;
        boolean fitts = false;
        if(out.size()>0){            
            for(ListIterator op = out.getLast().getOpportunities().listIterator(); op.hasNext();){
                Wort w = (Wort)op.next();
                if(w.getWortart().equals("Artikel")&&((Artikel)w).getWortunterart().equals("best")){
                    warBestimmterArtikel = true;
                }else{
                    if(w.getWortart().equals("Artikel")&&((Artikel)w).getWortunterart().equals("unbe")){
                        warGemischt = true;
                    }
                }
            }
        }
        for (ListIterator it = o.getOpportunities().listIterator(); it.hasNext();){
            Wort w = (Wort)it.next();
            fitts  = false;
            if(w.getWortart().equals("Adjektiv")){
                if(warBestimmterArtikel||warGemischt){
                    for(ListIterator op = out.getLast().getOpportunities().listIterator(); op.hasNext()&&!fitts;){
                        Wort wPrä = (Wort)op.next();
                        Artikel a = null;
                        if(wPrä.getWortart().equals("Artikel")) a = (Artikel)wPrä;
                        if(a!=null){
                            if(((Adjektiv)w).getWortunterart().equals("scfl")&&warBestimmterArtikel){
                                if(a.fitts((Adjektiv)w)){
                                    System.out.println("ES HAT GEPASST Bestimmt");
                                    w.setLexem(a.getLexem()+" "+w.getLexem());
                                    fitts = true;
                                    remove = true;
                                }
                            }else{
                                if(((Adjektiv)w).getWortunterart().equals("gefl")&&warGemischt){
                                    if(a.fitts((Adjektiv)w)){
                                        System.out.println("ES HAT GEPASST Unbestimmt");
                                        w.setLexem(a.getLexem()+" "+w.getLexem());
                                        fitts = true;
                                        remove = true;
                                    }
                                }
                            }
                        }
                    }
                    if(!fitts) it.remove();
                }else{
                    if(((Adjektiv)w).getWortunterart().equals("scfl")||((Adjektiv)w).getWortunterart().equals("gefl")){
                        it.remove();
                    }
                }
            }
        }
        return remove;
    }

    public Opportunities lookup(String token){
        System.out.println("lookup "+token+" in der Sprache "+WAtt.getLanguage());
        if(WAtt.getLanguage()==0) em = anna.getEMGER();
        if(WAtt.getLanguage()==1) em = anna.getEMPOL();
        Opportunities o = new Opportunities();
        //vorübergehend threadlose Implementierung:
        //Bei der Suche innerhalb jeder Wortart können mehrere Opportunitäten vorhanden sein
        //All diese Opportunitenen müssen zu einer Menge zusammengefasst werden um das Token
        //zu representieren
        List<Adjektiv> adjektive = em.createNamedQuery("Adjektiv.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : adjektive){
            System.out.println("Adjektiv war: "+w.getLexem()+" "+w.getId());
            o.add(w);
        }

        List<Adverb> adverbe = em.createNamedQuery("Adverb.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : adverbe) o.add(w);

        List<Artikel> artikel = em.createNamedQuery("Artikel.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : artikel) o.add(w);

        List<Interjektion> interjektionen = em.createNamedQuery("Interjektion.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : interjektionen) o.add(w);

        List<Konjunktion> konjunktionen = em.createNamedQuery("Konjunktion.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : konjunktionen) o.add(w);

        List<Nomen> nomen = em.createNamedQuery("Nomen.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : nomen) o.add(w);

        List<Präposition> Präpositionen = em.createNamedQuery("Präposition.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : Präpositionen) o.add(w);

        List<Postposition> Postpositionen = em.createNamedQuery("Postposition.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : Postpositionen) o.add(w);

        List<Pronomen> pronomen = em.createNamedQuery("Pronomen.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : pronomen) o.add(w);

        List<Verb> verbe = em.createNamedQuery("Verb.findByLexem")
                .setParameter("lexem", token)
                .getResultList();
        for(Wort w : verbe) o.add(w);
        //Beachtung der Infinitivform des Verbs
        List<Verb> verbeinfi = em.createNamedQuery("Verb.findByInfinitiv")
                .setParameter("infinitiv", token)
                .getResultList();
        Verb infi = null;
        if(verbeinfi.size()>0){
            infi = verbeinfi.get(0);
            try {
                infi.setId(0);
                infi.setLexem(token);
                infi.setAltus("unde");
                infi.setModus("indi");
                infi.setTempus("präs");
                infi.setPerson("123");
                infi.setNumerus("unde");
            } catch (UnknownKeyException ex) {
                Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
            }
            o.add(infi);
        }
        return o;
    }

    /**
     * @return the missing
     */
    public LinkedList<String> getMissing() {
        return missing;
    }

}
