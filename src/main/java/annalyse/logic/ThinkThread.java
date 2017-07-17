/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic;

import annalyse.grammatik.attribute.ressources.WAtt;
import annalyse.grammatik.wortarten.Artikel;
import annalyse.grammatik.wortarten.Nomen;
import annalyse.grammatik.wortarten.Wort;
import annalyse.logic.compiler.*;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JTree;
import org.jdesktop.application.Task;
import org.jdesktop.application.Application;

/**
 *
 * @author Maciej Niemczyk
 */
class ThinkThread extends Task {
  
    Anna caller;
    private EntityManager em;
    boolean cancelled = false;
    //Zuweisung von Keys wie: scanner, parser, semantik
    LinkedList<String> outprints  = new LinkedList<String>();
    int depth = 1;
    String out;
    String message;
    Scanner scan;
    Parser parse;

    public ThinkThread(Application app){
        super(app);
    }

    public void recive(Anna caller, String message, LinkedList<String> outprints){
        System.out.println("Anna recived following Message: "+message);
        this.caller = caller;
        this.message = message;
        this.outprints = outprints;
        em = caller.getEM();
        scan = new Scanner(caller);
        parse = new Parser(caller);
    }

    public void cancell(){
        this.cancelled = true;
    }

    @Override
    protected Object doInBackground() throws Exception {
        if(!message.equals("")){
            System.out.println("ThinkThread recived following Message: "+message);
            List<Opportunities> op = scan.scan(message);
            if(outprints.contains("scanner")) printOutScanner(op);
            JTree       syntaxbaum = parse.parse(op);
//            if(outprints.contains("parser")) printOutParser(syntaxbaum);
        }else{
            System.out.println("Welche Nachricht denn?");
        }
        return out;
    }

    public void printOutScanner(List<Opportunities> op){
        System.out.println("ANZAHL DER TOKEN: "+op.size());
        for(Opportunities o : op){
            List<Wort> wop = o.getOpportunities();
            if(wop.size()>0){
                if(WAtt.getLanguage()==0)this.caller.answer("Der Token: \""+wop.get(0).getLexem()+"\" könnte ");
                if(WAtt.getLanguage()==1)this.caller.answer("Tołken: \""+wop.get(0).getLexem()+"\" mógłby by być ");
                int i = 0;
                if(outprints.contains("verbous")){
                    for(Wort w : wop){
                        i++;
                        if(WAtt.getLanguage()==0)if(wop.size()==1) this.caller.answer(getArtikel(w.getWortart())+": "+w.toString()+" ");
                        if(WAtt.getLanguage()==1)if(wop.size()==1) this.caller.answer(w.toString()+" ");
                        if(WAtt.getLanguage()==0)if(i<wop.size()-1) this.caller.answer(getArtikel(w.getWortart())+": "+w.toString()+", ");
                        if(WAtt.getLanguage()==1)if(i<wop.size()-1) this.caller.answer(w.toString()+", ");
                        if(WAtt.getLanguage()==0)if(i==wop.size()-1) this.caller.answer(getArtikel(w.getWortart())+": "+w.toString()+" oder ");
                        if(WAtt.getLanguage()==1)if(i==wop.size()-1) this.caller.answer(w.toString()+" lub ");
                        if(WAtt.getLanguage()==0)if(i==wop.size()&&i!=1) this.caller.answer(getArtikel(w.getWortart())+": "+w.toString()+" ");
                        if(WAtt.getLanguage()==1)if(i==wop.size()&&i!=1) this.caller.answer(w.toString()+" ");
                    }
                }else{
                    for(Wort w : wop){
                        i++;
                        if(WAtt.getLanguage()==0)if(wop.size()==1) this.caller.answer(getArtikel(w.getWortart())+": ");
                        if(WAtt.getLanguage()==1)if(wop.size()==1) this.caller.answer(w.toString()+" ");
                        if(WAtt.getLanguage()==0)if(i<wop.size()-1) this.caller.answer(getArtikel(w.getWortart())+": ");
                        if(WAtt.getLanguage()==1)if(i<wop.size()-1) this.caller.answer(w.toString()+", ");
                        if(WAtt.getLanguage()==0)if(i==wop.size()-1) this.caller.answer(getArtikel(w.getWortart())+": ");
                        if(WAtt.getLanguage()==1)if(i==wop.size()-1) this.caller.answer(w.toString()+" lub ");
                        if(WAtt.getLanguage()==0)if(i==wop.size()&&i!=1) this.caller.answer(getArtikel(w.getWortart())+": ");
                        if(WAtt.getLanguage()==1)if(i==wop.size()&&i!=1) this.caller.answer(w.toString()+" ");
                    }
                }
                if(WAtt.getLanguage()==0)this.caller.answer("sein. \n");
                if(WAtt.getLanguage()==1)this.caller.answer(". \n");
            }
        }
        if(scan.getMissing().size()>0){
            if(WAtt.getLanguage()==0)this.caller.answer("Leider konnten follgende Token nicht erkann werden: ");
            if(WAtt.getLanguage()==1)this.caller.answer("Niestety następujące słowa niezostały rozpoznane: ");
            for(String s : scan.getMissing()) this.caller.answer(s+" ");
            this.caller.answer("\n");
        }
    }

    //Nur mal spasshalber
    public String getArtikel(String s){
        Nomen n = (Nomen) em.createNamedQuery("Nomen.findByLexemAndKasusAndNumeral").setParameter("lexem", s).setParameter("kasus", "nomi").setParameter("numerus", "sing").getSingleResult();
        return ((Artikel)em.createQuery("SELECT a FROM Artikel a WHERE a.wortunterart=\"unbe\" AND a.genus=\""
                +n.getGenus()+"\" AND a.kasus=\""+n.getKasus()+"\" AND a.numerus=\""+n.
                getNumerus()+"\"").getSingleResult()).getLexem();
    }

    public void setDepth(int depth){
        this.depth = depth;
    }
}
