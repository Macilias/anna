/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic;


import annalyse.grammatik.attribute.ressources.WAtt;
import annalyse.grammatik.wortarten.*;
import annalyse.gui.AnalyseApp;
import annalyse.gui.AnalyseView;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.FBRuleReasoner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jdesktop.application.Task;


/**
 * @author Maciej Niemczyk
 */
public class Anna {
    //Connection
    EntityManagerFactory efGER = javax.persistence.Persistence.createEntityManagerFactory("AnaylsePUMNGerman");
    EntityManagerFactory efPOL = javax.persistence.Persistence.createEntityManagerFactory("AnaylsePUMNPolish");
    private EntityManager emGER = efGER.createEntityManager();
    private EntityManager emPOL = efPOL.createEntityManager();
    private EntityManager em = emPOL;
    //Ontologie   OWL_DL_MEM_RULE_INF
    //Ontologie   OWL_DL_MEM
    private OntModel base = ModelFactory.createOntologyModel(OntModelSpec.
            OWL_MEM);
    public OntModel m = ModelFactory.createOntologyModel(OntModelSpec.
            OWL_MEM_MICRO_RULE_INF, base);
    public OntModel brain = ModelFactory.createOntologyModel(OntModelSpec.
            OWL_MEM_MICRO_RULE_INF);
    public String SOURCE = "http://www.analisa.eu/grammatik.owl";
    public String NS = SOURCE + "#";
    public String BrainNS = "http://www.analisa.eu/brain.owl#";

    LinkedList<ThinkThread> thinkThreads = new LinkedList<ThinkThread>();
    AnalyseView caller;
    AnalyseApp app;
    int depth = 1;
    boolean apiuse = false;

    public Anna(AnalyseApp app) {
        this.app = app;
        if (WAtt.getLanguage() == 0) {
            em = efGER.createEntityManager();
        } else {
            em = efPOL.createEntityManager();
        }
        try {
            ClassLoader cl = AnalyseApp.class.getClassLoader();
//            FileResource fileResource = new URLClassLoaderFileResource(cl, resource);
            InputStream stream = cl.getResourceAsStream("Ontologie/grammatik.owl");
            DataInputStream in = new DataInputStream(new BufferedInputStream(stream));
            base.read(in, SOURCE);
            base.setNsPrefix("brain", BrainNS).setNsPrefix("g", NS);
            FBRuleReasoner reasoner = (FBRuleReasoner) m.getReasoner();
            // FIXME
//            reasoner.loadAdditionalRules("g", NS, "Ontologie/grammatik.rules");
        } catch (Exception e) {
            System.out.println("UPS, DA GING WAS SCHIEFF");
            System.out.println(e.toString());
        }

//        if(m.isEmpty()){
//            System.out.println("KACKE NIX DA");
//        }else{
//            if(false){
//                System.out.println("Die Ontologie beinhaltet die Properies:");
//                for(ExtendedIterator<OntProperty> i =
//                        m.listAllOntProperties();i.hasNext();){
//                    OntProperty r = i.next();
//                    System.out.println("OntoPropery: "+r);
//                }
//            }
//        }

    }

    public Task recive(AnalyseView caller, String massage,
                       LinkedList<String> outprint) {
        this.caller = caller;
        if (!apiuse) {
            ThinkThread tt = new ThinkThread(app);
            tt.setDepth(depth);
            thinkThreads.add(tt);
            tt.recive(this, massage, outprint);
            return tt;
        } else {
            GraphVizThread gt = new GraphVizThread(app);
            gt.setDepth(depth);
            gt.recive(this, massage);
            return gt;
        }
    }

    public void answer(String massage) {
        this.caller.reciveAnswer(massage);
    }

    public void performGraphViz(boolean b) {
        this.apiuse = b;
    }

    public void changeDepth(int depth) {
        System.out.println("neue tiefe: " + depth);
        this.depth = depth;
        if (thinkThreads.size() > 0) {
            ThinkThread tt = thinkThreads.getLast();
            tt.setDepth(depth);
        }
    }

    public void changeConnection() {
        System.out.println("CONECCTION WIRD BEI ANNA GEÄNDERT");
        if (WAtt.getLanguage() == 0) {
            this.em = emGER;
        } else {
            this.em = emPOL;
        }
    }

    private void saveWord(Wort w) {
        if (w.getLanguage() == 0) em = emGER;
        if (w.getLanguage() == 1) em = emPOL;
        try {
            this.em.getTransaction().begin();
            this.em.persist(w);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
        }
    }

    public void learnWord(Wort w) {
        System.out.println("ANNA LERNT ein " + w.getWortart() + ": " + w.getLexem());
        saveWord(w);
    }

    public void forgettWord(Wort w) {
        if (w.getLanguage() == 0) em = emGER;
        if (w.getLanguage() == 1) em = emPOL;
        try {
            System.out.println("Das Wort " + w.getLexem() + "wird gelöscht");
            em.getTransaction().begin();
            em.remove(w);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("irgend so ein scheiss fehler beim löschen:");
            System.out.println(e.toString());
            em.getTransaction().rollback();
        }
    }

    public EntityManager getEM() {
        return em;
    }

    public OntModel getModel() {
        return this.m;
    }

    public OntModel getBrain() {
        return this.brain;
    }

    public EntityManager getEMGER() {
        return this.emGER;
    }

    public EntityManager getEMPOL() {
        return this.emPOL;
    }

    public void goodby() {
        for (ThinkThread tt : thinkThreads) {
            tt.cancell();
        }
    }
}
