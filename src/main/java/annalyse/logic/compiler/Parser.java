/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.logic.compiler;

import annalyse.grammatik.attribute.ressources.WAtt;
import annalyse.grammatik.wortarten.*;
import annalyse.logic.Anna;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JTree;

/**
 * Der Parser erstellt aus der Menge der annotierten Opportunitäten gültige Sätze
 * nach den Regeln der detuschen Grammatik. Nach den Button Up ansatz (LR-Parser)
 * werden zuerst Opportunitäten zusammengefasst (z.B. Verben mit ihren Hilfsverben
 * die Bildung von Futur oder die Zusammensätzung mehrteiliger Konjunktionen)
 * anschliessend ensprechende weitere Etapen durchlaufen.
 * Aufgabe: Tokenstromm -> Ableitungsbaum (Syntaxerkennung)
 * @author Maciej Niemczyk
 */
public class Parser {

    private Anna anna;
    private EntityManager em;
    private OntModel m;
    private String NS;
    private String TYPE="http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
    private String SUBTYPE="http://www.w3.org/2000/01/rdf-schema#subClassOf";
    private OntModel brain;
    private String BrainNS;
    private JTree JTree;

    //TODO DEBUG und raus damit
    public int count = 0;

    public String xmlDoctype = "<?xml version=\"1.0\"?>"+"\n"+
                               "<!DOCTYPE rdf:RDF ["    +"\n"+
                               "<!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >"+"\n"+
                                    "<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >"+"\n"+
                                    "<!ENTITY owl2xml \"http://www.w3.org/2006/12/owl2-xml#\" >"+"\n"+
                                    "<!ENTITY g \"http://www.analisa.eu/grammatik.owl#\" >"+"\n"+
                                    "<!ENTITY brain \"http://www.analisa.eu/brain.owl#\" >"+"\n"+
                                    "<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >"+"\n"+
                                    "<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >"+"\n"+
                               "]>"+"\n";
    private PrintWriter out;
    

    public Parser(Anna anna){
        this.anna = anna;
        this.m  = anna.getModel();
        this.NS = anna.NS;
        this.brain  = anna.getBrain();
        this.BrainNS = anna.BrainNS;
        this.brain.setNsPrefix( "brain", BrainNS ).setNsPrefix("g", NS);
        if(WAtt.getLanguage()==0){
            em = anna.getEMGER();
        }else{
            em = anna.getEMPOL();
        }
    }

    public void putIntoBrain(Individual a){
        count = 0;
        ExtendedIterator it = a.listOntClasses(true);
           while(it.hasNext()) {
               OntClass oclass = (OntClass)it.next();
               if(oclass!=null){
                   parentsToBrain(oclass);
                   System.out.println("-------------Einpflanzung neuen Individeums---------");
                   System.out.println("OntoClass des Individuals: "+oclass.toString()+" (breits zuvor angelegt)");
               }
           }
        ExtendedIterator it2 = a.listProperties();
           while(it2.hasNext()) {
               Statement s = (Statement)it2.next();
               brain.add(s);
               OntClass prop = m.getOntClass(s.getObject().toString());
               parentsToBrain(prop);
               System.out.println("Properties des Individuals: "+s.toString());
           }
    }

    public void parentsToBrain(OntClass o){
        count++;
        System.out.println(count+". Rekursionsaufruf auf der Classe: "+o.toString());
        brain.createClass(o.getURI());
        ExtendedIterator it = o.listSuperClasses(true);
           while(it.hasNext()){
               OntClass oclass = (OntClass)it.next();             
               brain.createClass(oclass.getURI());
//               brain.add(o, brain.createProperty(SUBTYPE), oclass);
               if (!o.equals(oclass)) {
                   brain.add(o, RDFS.subClassOf, oclass);
               } else {
                   System.out.println("HIER SCHWACHSINNVERSUCH UNTERBUNDEN: o war gelich oclass in parentsToBrain!");
               }
               System.out.println("OntoClass "+count+" Parent: "+oclass.toString());
               ExtendedIterator it2 = o.listProperties();
               while(it2.hasNext()) {
                   Statement s = (Statement)it2.next();
//                   System.out.println("DEBUG>>> s war: "+s.toString());
//                   System.out.println("DEBUG>>> Subject war: "+s.getSubject());
//                   System.out.println("DEBUG>>> Prädika war: "+s.getPredicate());
//                   System.out.println("DEBUG>>> Object  war: "+s.getObject());
                   if (!s.getSubject().toString().equals(s.getObject().toString())) {
                       brain.add(s);
                   } else {
//                       System.out.println("HIER in listProperies SCHWACHSINNVERSUCH UNTERBUNDEN: o war gelich oclass in parentsToBrain!");
                   }
                   System.out.println("Parent "+count+" Propertie: "+s.toString());
               }
               parentsToBrain(oclass);
           }
        System.out.println("----Ende des Rekursionsaufrufs auf der Classe: "+o.toString());
    }

    public JTree parse(List<Opportunities> op){
        JTree = new JTree();
        int i = 0;
        for(Opportunities o:op){
            i++;
            List<Wort> wop = o.getOpportunities();
            if(wop.size()>0){
                for(Wort w : wop){
                    Individual wo = pullOutOfGramma(w);
                    com.hp.hpl.jena.rdf.model.Property posi = m.getProperty(NS+"position_in_Satz");
//                    wo.addProperty(posi, String.valueOf(i)); - DAS WAR EIN FALSCHER ANSATZ
                    wo.addLiteral(posi, i);
//                    putIntoBrain(wo);
                }
            }
        }
        printSomething();
        saveModels();
        return JTree;
    }

    private void printSomething(){
        StmtIterator i = m.listStatements(null, m.createProperty(NS+"steht_vor"), (RDFNode)null);
//        StmtIterator i = m.listStatements(null, m.createProperty(NS+"position_in_Satz"), (RDFNode)null);
//        StmtIterator i = m.listStatements(null, null, (RDFNode) m.createClass(NS+"Aktant"));
            System.out.println(i.toString());
            while( i.hasNext() ) {
                Statement s = i.nextStatement();
                System.out.println("Statement is " + s);
                for (Iterator id = m.getDerivation(s); id.hasNext(); ) {
                    Derivation deriv = (Derivation) id.next();
                    deriv.printTrace(out, true);
                }
            }
        printSomethingElse();
    }

//    private void printSomethingElse(){
////        StmtIterator i = m.listStatements(null, m.createProperty(NS+"steht_vor"), (RDFNode)null);
//        StmtIterator i = m.listStatements(null, m.createProperty(NS+"position_in_Satz"), (RDFNode)null);
////        StmtIterator i = m.listStatements(null, null, (RDFNode) m.createClass(NS+"Aktant"));
//            System.out.println(i.toString());
//            while( i.hasNext() ) {
//                Statement s = i.nextStatement();
//                System.out.println("Statement is " + s);
//                for (Iterator id = m.getDerivation(s); id.hasNext(); ) {
//                    Derivation deriv = (Derivation) id.next();
//                    deriv.printTrace(out, true);
//                }
//            }
//    }
    private void printSomethingElse(){
        StmtIterator i = m.listStatements(null, m.createProperty(NS+"position_in_Satz"), (RDFNode)null);
            System.out.println(i.toString());
            while( i.hasNext() ) {
                Statement s = i.nextStatement();
                System.out.println("Statement is " + s);
                for (Iterator id = m.getDerivation(s); id.hasNext(); ) {
                    Derivation deriv = (Derivation) id.next();
                    deriv.printTrace(out, true);
                }
            }
    }
    
    public Individual pullOutOfGramma(Wort w){
       final boolean out = false;
       String wortArt = w.getWortart();
       OntClass wc = m.createClass(NS+wortArt);
       Individual a = m.createIndividual(BrainNS+w.getLexem().replace(" ", "_")+"_"+w.getId(),wc);
       OntClass pc = m.createClass(NS+"Wortphrase");
       //TODO (auch mittel zum nicht ausführen) Sollte true initialisiert werden
       boolean such= true;
       if(such&&wortArt.equals("Adjektiv")){
           Individual phrase = m.createIndividual(BrainNS+wortArt+"phrase"+"_"+w.getId(), pc);
           OntClass kernc = m.createClass(NS+"Kern");
           Individual kern   = m.createIndividual(BrainNS+"kern"+"_"+w.getId(), kernc);
           kern.addProperty(m.createProperty(NS+"wort"), a);
           phrase.addProperty(m.createProperty(NS+"kern"), kern);
           Adjektiv adj = (Adjektiv)w;
           /* Attribute werden bezogen:
            * */
           //Genus
           String lexGenus= ((String)adj.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Kasus
           String lexKasus= ((String)adj.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Numerus
           String lexNumerus= ((String)adj.getNumerusAA().getName()).replace(" ", "_");
           OntClass nc  = m.createClass(NS+lexNumerus);
           Individual numerus = m.createIndividual(NS+lexNumerus,nc);
           if(out) System.out.println(">>"+lexNumerus);
           //Steigerung
           String lexSteigerung= ((String)adj.getSteigerungAA().getName()).replace(" ", "_");
           OntClass sc  = m.createClass(NS+lexSteigerung);
           Individual steigerung = m.createIndividual(NS+lexSteigerung,sc);
           if(out) System.out.println(">>"+lexSteigerung);
           //Wortunterart
           String lexWort= ((String)adj.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty numerusProp = m.createObjectProperty(NS+"numerus");
               a.addProperty(numerusProp, numerus);
               ObjectProperty steigerungProp = m.createObjectProperty(NS+"steigerung");
               a.addProperty(steigerungProp, steigerung);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Adverb")){
           Individual phrase = m.createIndividual(BrainNS+wortArt+"phrase"+"_"+w.getId(), pc);
           OntClass kernc = m.createClass(NS+"Kern");
           Individual kern   = m.createIndividual(BrainNS+"kern"+"_"+w.getId(), kernc);
           kern.addProperty(m.createProperty(NS+"wort"), a);
           phrase.addProperty(m.createProperty(NS+"kern"), kern);
           Adverb adv = (Adverb)w;
           /* Attribute werden bezogen:
            * */
           //Umstand
           String lexUmstand= ((String)adv.getUmstandAA().getName()).replace(" ", "_");
           OntClass uc  = m.createClass(NS+lexUmstand);
           Individual umstand = m.createIndividual(NS+lexUmstand,uc);
           if(out) System.out.println(">>"+lexUmstand);
           //Wortunterart
           String lexWort= ((String)adv.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty umstandProp = m.createObjectProperty(NS+"umstand");
               a.addProperty(umstandProp, umstand);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Artikel")){
           //{"WORTUNTERART","GENUS","KASUS","PERSON","NUMERUS"};
           Artikel art = (Artikel)w;
           /* Attribute werden bezogen:
            * */
           //Genus
           String lexGenus= ((String)art.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Kasus
           String lexKasus= ((String)art.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Numerus
           String lexNumerus= ((String)art.getNumerusAA().getName()).replace(" ", "_");
           OntClass nc  = m.createClass(NS+lexNumerus);
           Individual numerus = m.createIndividual(NS+lexNumerus,nc);
           if(out) System.out.println(">>"+lexNumerus);
           //Person
           String lexPerson= ((String)art.getPersonAA().getName()).replace(" ", "_");
           OntClass sc  = m.createClass(NS+lexPerson);
           Individual person = m.createIndividual(NS+lexPerson,sc);
           if(out) System.out.println(">>"+lexPerson);
           //Wortunterart
           String lexWort= ((String)art.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty numerusProp = m.createObjectProperty(NS+"numerus");
               a.addProperty(numerusProp, numerus);
               ObjectProperty personProp = m.createObjectProperty(NS+"person");
               a.addProperty(personProp, person);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Interjektion")){
           such = false;
       }
       if(such&&wortArt.equals("Interpunktion")){
           such = false;
       }
       if(such&&wortArt.equals("Konjunktion")){
           Konjunktion kon = (Konjunktion)w;
           /* Attribute werden bezogen:
            * */
           //AndererTeilID
           //TODO ist noch suboptimal
           String lexAndererTeilID= (String.valueOf(kon.getAndererTeilID()));
           if(!lexAndererTeilID.equals("")){
               OntClass ac  = m.createClass(NS+"andererTeil");
               Individual andererTeilID = m.createIndividual(NS+lexAndererTeilID,ac);
               if(out) System.out.println(">>"+lexAndererTeilID);
               ObjectProperty andererTeilIDProp = m.createObjectProperty(NS+"andererTeilID");
               a.addProperty(andererTeilIDProp, andererTeilID);
           }
           //Wortunterart
           String lexWort= ((String)kon.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Nomen")){
           Individual phrase = m.createIndividual(BrainNS+wortArt+"phrase"+"_"+w.getId(), pc);
           OntClass kernc = m.createClass(NS+"Kern");
           Individual kern   = m.createIndividual(BrainNS+"kern"+"_"+w.getId(), kernc);
           kern.addProperty(m.createProperty(NS+"wort"), a);
           phrase.addProperty(m.createProperty(NS+"kern"), kern);
           //{"WORTUNTERART","GENUS","KASUS","NUMERUS"};
           Nomen nom = (Nomen)w;
           /* Attribute werden bezogen:
            * */
           //Belebt oder nicht?
           boolean lexLeb = nom.isBelebt();
           //Genus
           String lexGenus= ((String)nom.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Kasus
           String lexKasus= ((String)nom.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Numerus
           String lexNumerus= ((String)nom.getNumerusAA().getName()).replace(" ", "_");
           OntClass nc  = m.createClass(NS+lexNumerus);
           Individual numerus = m.createIndividual(NS+lexNumerus,nc);
           if(out) System.out.println(">>"+lexNumerus);
           //Wortunterart
           String lexWort= ((String)nom.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               DatatypeProperty belebtPro = m.createDatatypeProperty(NS+"belebt");
               a.addLiteral(belebtPro, lexLeb);// addProperty(belebtPro, (XSD.xboolean.lexLeb); war falsch
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty numerusProp = m.createObjectProperty(NS+"numerus");
               a.addProperty(numerusProp, numerus);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Numeral")){
           //{"WORTUNTERART","GENUS","KASUS"};
           Numeral num = (Numeral)w;
           /* Attribute werden bezogen:
            * */
           //Genus
           String lexGenus= ((String)num.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Kasus
           String lexKasus= ((String)num.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Wortunterart
           String lexWort= ((String)num.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Postposition")){
           //{"KASUS","UMSTAND"};
           Postposition pos = (Postposition)w;
           /* Attribute werden bezogen:
            * */
           //Kasus
           String lexKasus= ((String)pos.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Umstand
           String lexUmstand= ((String)pos.getUmstandAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexUmstand);
           Individual umstand = m.createIndividual(NS+lexUmstand,woc);
           if(out) System.out.println(">>"+lexUmstand);
           try{
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty umstandProp = m.createObjectProperty(NS+"umstand");
               a.addProperty(umstandProp, umstand);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Pronomen")){
           //{"WORTUNTERART","GENUS","KASUS","PERSON","NUMERUS"};
           Pronomen pro = (Pronomen)w;
           /* Attribute werden bezogen:
            * */
           //Genus
           String lexGenus= ((String)pro.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Kasus
           String lexKasus= ((String)pro.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Numerus
           String lexNumerus= ((String)pro.getNumerusAA().getName()).replace(" ", "_");
           OntClass nc  = m.createClass(NS+lexNumerus);
           Individual numerus = m.createIndividual(NS+lexNumerus,nc);
           if(out) System.out.println(">>"+lexNumerus);
           //Person
           String lexPerson= ((String)pro.getPersonAA().getName()).replace(" ", "_");
           OntClass sc  = m.createClass(NS+lexPerson);
           Individual person = m.createIndividual(NS+lexPerson,sc);
           if(out) System.out.println(">>"+lexPerson);
           //Wortunterpro
           String lexWort= ((String)pro.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty numerusProp = m.createObjectProperty(NS+"numerus");
               a.addProperty(numerusProp, numerus);
               ObjectProperty personProp = m.createObjectProperty(NS+"person");
               a.addProperty(personProp, person);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Präposition")){
           Individual phrase = m.createIndividual(BrainNS+wortArt+"phrase"+"_"+w.getId(), pc);
           OntClass kernc = m.createClass(NS+"Kern");
           Individual kern   = m.createIndividual(BrainNS+"kern"+"_"+w.getId(), kernc);
           kern.addProperty(m.createProperty(NS+"wort"), a);
           phrase.addProperty(m.createProperty(NS+"kern"), kern);
           //{"KASUS","UMSTAND"};
           Präposition prä = (Präposition)w;
           /* Attribute werden bezogen:
            * */
           //Kasus
           String lexKasus= ((String)prä.getKasusAA().getName()).replace(" ", "_");
           OntClass kc  = m.createClass(NS+lexKasus);
           Individual kasus = m.createIndividual(NS+lexKasus,kc);
           if(out) System.out.println(">>"+lexKasus);
           //Umstand
           String lexUmstand= ((String)prä.getUmstandAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexUmstand);
           Individual umstand = m.createIndividual(NS+lexUmstand,woc);
           if(out) System.out.println(">>"+lexUmstand);
           try{
               ObjectProperty kasusProp = m.createObjectProperty(NS+"kasus");
               a.addProperty(kasusProp, kasus);
               ObjectProperty umstandProp = m.createObjectProperty(NS+"umstand");
               a.addProperty(umstandProp, umstand);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Verb")){
           Individual phrase = m.createIndividual(BrainNS+wortArt+"phrase"+"_"+w.getId(), pc);
           OntClass kernc = m.createClass(NS+"Kern");
           Individual kern   = m.createIndividual(BrainNS+"kern"+"_"+w.getId(), kernc);
           kern.addProperty(m.createProperty(NS+"wort"), a);
           phrase.addProperty(m.createProperty(NS+"kern"), kern);
           //{"WORTUNTERART>","MODUS>","TEMPUS>","ALTUS>","PERSON>","NUMERUS>","GENUS>"};
           Verb ver = (Verb)w;
           /* Attribute werden bezogen:
            * */
           //Altus
           String lexAltus= ((String)ver.getAltusAA().getName()).replace(" ", "_");
           OntClass ac  = m.createClass(NS+lexAltus);
           Individual altus = m.createIndividual(NS+lexAltus,ac);
           if(out) System.out.println(">>"+lexAltus);
           //Tempus
           String lexTempus= ((String)ver.getTempusAA().getName()).replace(" ", "_");
           OntClass tc  = m.createClass(NS+lexTempus);
           Individual tempus = m.createIndividual(NS+lexTempus,tc);
           if(out) System.out.println(">>"+lexTempus);
           //Genus
           String lexGenus= ((String)ver.getGenusAA().getName()).replace(" ", "_");
           OntClass gc  = m.createClass(NS+lexGenus);
           Individual genus = m.createIndividual(NS+lexGenus,gc);
           if(out) System.out.println(">>"+lexGenus);
           //Modus
           String lexModus= ((String)ver.getModusAA().getName()).replace(" ", "_");
           OntClass mc  = m.createClass(NS+lexModus);
           Individual modus = m.createIndividual(NS+lexModus,mc);
           if(out) System.out.println(">>"+lexModus);
           //Numerus
           String lexNumerus= ((String)ver.getNumerusAA().getName()).replace(" ", "_");
           OntClass nc  = m.createClass(NS+lexNumerus);
           Individual numerus = m.createIndividual(NS+lexNumerus,nc);
           if(out) System.out.println(">>"+lexNumerus);
           //Person
           String lexPerson= ((String)ver.getPersonAA().getName()).replace(" ", "_");
           OntClass sc  = m.createClass(NS+lexPerson);
           Individual person = m.createIndividual(NS+lexPerson,sc);
           if(out) System.out.println(">>"+lexPerson);
           //Wortunterart
           String lexWort= ((String)ver.getWortunterartAA().getName()).replace(" ", "_");
           OntClass woc = m.createClass(NS+lexWort);
           Individual wortunterart = m.createIndividual(NS+lexWort,woc);
           if(out) System.out.println(">>"+lexWort);
           try{
               ObjectProperty altusProp = m.createObjectProperty(NS+"altus");
               a.addProperty(altusProp, altus);
               ObjectProperty tempusProp = m.createObjectProperty(NS+"tempus");
               a.addProperty(tempusProp, tempus);
               ObjectProperty genusProp = m.createObjectProperty(NS+"genus");
               a.addProperty(genusProp, genus);
               ObjectProperty modusProp = m.createObjectProperty(NS+"modus");
               a.addProperty(modusProp, modus);
               ObjectProperty numerusProp = m.createObjectProperty(NS+"numerus");
               a.addProperty(numerusProp, numerus);
               ObjectProperty personProp = m.createObjectProperty(NS+"person");
               a.addProperty(personProp, person);
               ObjectProperty wortunterartProp = m.createObjectProperty(NS+"wortunterart");
               a.addProperty(wortunterartProp, wortunterart);
           }catch(Exception e){
               System.out.println("Fehler: ");
               e.printStackTrace();
           }
           such = false;
       }
       if(such&&wortArt.equals("Wort")){
           such = false;
       }
       if(such&&wortArt.equals("Zirkumposition")){

       }
       return a;
    }


    public void saveModels(){
        DataOutputStream out1;
        DataOutputStream out2;
        try {
          out1  = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Ontologie/grammatik1.owl")));
          out2  = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Ontologie/grammatik2(brain).owl")));
          out1.writeBytes(xmlDoctype);
          out2.writeBytes(xmlDoctype);
//          m.writeAll(out1,null,"g");
          m.write(out1);
          brain.write(out2);

        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
