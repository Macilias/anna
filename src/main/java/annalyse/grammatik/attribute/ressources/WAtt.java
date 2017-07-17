/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.attribute.ressources;

import java.util.HashMap;


/**
 *
 * @author Maciej Niemczyk
 */
public class WAtt {
    //0: deutsch 1: polnisch
    private static short language = 0;

    static HashMap<String,String> wortunterartenGERAdjektiv;
    static HashMap<String,String> wortunterartenPOLAdjektiv;    //Przymiotnik
    static HashMap<String,String> wortunterartenGERAdverb;
    static HashMap<String,String> wortunterartenPOLAdverb;      //Przysłówek
    static HashMap<String,String> wortunterartenGERArtikel;
    static HashMap<String,String> wortunterartenPOLArtikel;     //Niema w Polskim
    static HashMap<String,String> wortunterartenGERKonjunktion;
    static HashMap<String,String> wortunterartenPOLKonjunktion; //Spójnik (Syntax)
    static HashMap<String,String> wortunterartenGERKonjunktionSemantisch;
    static HashMap<String,String> wortunterartenPOLKonjunktionSemantisch; //Spójnik (Semantyka)
    static HashMap<String,String> wortunterartenGERNomen;
    static HashMap<String,String> wortunterartenPOLNomen;    //Rzeczownik
    static HashMap<String,String> wortunterartenGERNumeral;
    static HashMap<String,String> wortunterartenPOLNumeral;  //Liczebnikß
    static HashMap<String,String> wortunterartenGERPronomen;
    static HashMap<String,String> wortunterartenPOLPronomen; //Zaimek
    static HashMap<String,String> wortunterartenGERVerb;
    static HashMap<String,String> wortunterartenPOLVerb;     //Czasownik
    static HashMap<String,String> kasuseGER;
    static HashMap<String,String> kasusePOL; //Przypadki
    static HashMap<String,String> genuseGER;
    static HashMap<String,String> genusePOL; //Rodzyj
    //TODO Bring diese noch rein:
    static HashMap<String,String> genuseVebiGER;
    static HashMap<String,String> genuseVerbiPOL; //Aktiv oder Passiv
    //END TODO
    static HashMap<String,String> moduseGER;
    static HashMap<String,String> modusePOL; //Tryb
    static HashMap<String,String> tempuseGER;
    static HashMap<String,String> tempusePOL;//Czas
    static HashMap<String,String> altuseGER;
    static HashMap<String,String> altusePOL; //Nowy lub stary
    static HashMap<String,String> steigerungenGER;
    static HashMap<String,String> steigerungenPOL; //Stopniwanie
    static HashMap<String,String> personenGER;
    static HashMap<String,String> personenPOL; //Osoba
    static HashMap<String,String> numeruseGER;
    static HashMap<String,String> numerusePOL; //Liczebniki
    static HashMap<String,String> umständeGER;
    static HashMap<String,String> umständePOL; //Okolicznik

    public static String getWortunterart(String key, String wortart) throws UnknownKeyException{
            if(language==0) return getWortunterartGER(key, wortart);
            if(language==1) return getWortunterartPOL(key, wortart);
            return getWortunterartGER(key, wortart);
    }

    public static String getWortunterartGER(String key, String wortart) throws UnknownKeyException{
        if(wortart.equals("Adjektiv")){
            if(wortunterartenGERAdjektiv==null){
               initWortunterartenGERAdjektiv();
            }
            if(wortunterartenGERAdjektiv.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERAdjektiv.get(key);
        }
        if(wortart.equals("Adverb")){
            if(wortunterartenGERAdverb==null){
               initWortunterartenGERAdverb();
            }
            if(wortunterartenGERAdverb.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERAdverb.get(key);
        }
        if(wortart.equals("Artikel")){
            if(wortunterartenGERArtikel==null){
               initWortunterartenGERArtikel();
            }
            if(wortunterartenGERArtikel.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERArtikel.get(key);
        }
        if(wortart.equals("Konjunktion")){
            if(wortunterartenGERKonjunktion==null){
               initWortunterartenGERKonjunktion();
            }
            if(wortunterartenGERKonjunktion.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERKonjunktion.get(key);
        }
        if(wortart.equals("Nomen")){
            if(wortunterartenGERNomen==null){
               initWortunterartenGERNomen();
            }
            if(wortunterartenGERNomen.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERNomen.get(key);
        }
        if(wortart.equals("Numeral")){
            if(wortunterartenGERNumeral==null){
               initWortunterartenGERNumeral();
            }
            if(wortunterartenGERNumeral.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERNumeral.get(key);
        }
        if(wortart.equals("Pronomen")){
            if(wortunterartenGERPronomen==null){
               initWortunterartenGERPronomen();
            }
            if(wortunterartenGERPronomen.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERPronomen.get(key);
        }
        if(wortart.equals("Verb")){
            if(wortunterartenGERVerb==null){
               initWortunterartenGERVerb();
            }
            if(wortunterartenGERVerb.get(key)==null) throw new UnknownKeyException();
            return wortunterartenGERVerb.get(key);
        }
        else{
            return null;
        }
    }

    public static String getWortunterartPOL(String key, String wortart) throws UnknownKeyException{
        if(wortart.equals("Adjektiv")){
            if(wortunterartenPOLAdjektiv==null){
               initWortunterartenPOLAdjektiv();
            }
            if(wortunterartenPOLAdjektiv.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLAdjektiv.get(key);
        }
        if(wortart.equals("Adverb")){
            if(wortunterartenPOLAdverb==null){
               initWortunterartenPOLAdverb();
            }
            if(wortunterartenPOLAdverb.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLAdverb.get(key);
        }
        if(wortart.equals("Artikel")){
            if(wortunterartenPOLArtikel==null){
               initWortunterartenPOLArtikel();
            }
            if(wortunterartenPOLArtikel.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLArtikel.get(key);
        }
        if(wortart.equals("Konjunktion")){
            if(wortunterartenPOLKonjunktion==null){
               initWortunterartenPOLKonjunktion();
            }
            if(wortunterartenPOLKonjunktion.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLKonjunktion.get(key);
        }
        if(wortart.equals("Nomen")){
            if(wortunterartenPOLNomen==null){
               initWortunterartenPOLNomen();
            }
            if(wortunterartenPOLNomen.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLNomen.get(key);
        }
        if(wortart.equals("Numeral")){
            if(wortunterartenPOLNumeral==null){
               initWortunterartenPOLNumeral();
            }
            if(wortunterartenPOLNumeral.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLNumeral.get(key);
        }
        if(wortart.equals("Pronomen")){
            if(wortunterartenPOLPronomen==null){
               initWortunterartenPOLPronomen();
            }
            if(wortunterartenPOLPronomen.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLPronomen.get(key);
        }
        if(wortart.equals("Verb")){
            if(wortunterartenPOLVerb==null){
               initWortunterartenPOLVerb();
            }
            if(wortunterartenPOLVerb.get(key)==null) throw new UnknownKeyException();
            return wortunterartenPOLVerb.get(key);
        }
        else{
            return null;
        }
    }

    public static String getWortunterartAdjektiv(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERAdjektiv(key);
            if(language==1) return getWortunterartPOLAdjektiv(key);
            return getWortunterartGERAdjektiv(key);
    }

    public static HashMap<String,String> getWortunterartenAdjektiv(){
            if(language==0) return getWortunterartenGERAdjektiv();
            if(language==1) return getWortunterartenPOLAdjektiv();
            return getWortunterartenGERAdjektiv();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERAdjektiv(){
        wortunterartenGERAdjektiv = new HashMap<String,String>();
        wortunterartenGERAdjektiv.put("stfl", "starke Flexion (ohne Artikel)");
        wortunterartenGERAdjektiv.put("scfl", "schwache Flexion (mit bestimmten Artikel)");
        wortunterartenGERAdjektiv.put("gefl", "gemischte Flexion (mit ein, klein, etc.)");
        wortunterartenGERAdjektiv.put("präd", "Prädikativ"); //mit (am im superlativ) flektierten PersPro. und "sein"
    }

    public static String getWortunterartGERAdjektiv(String key) throws UnknownKeyException{
        if(wortunterartenGERAdjektiv==null){
           initWortunterartenGERAdjektiv();
        }
        if(wortunterartenGERAdjektiv.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERAdjektiv.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERAdjektiv(){
        if(wortunterartenGERAdjektiv==null){
           initWortunterartenGERAdjektiv();
        }
        return wortunterartenGERAdjektiv;
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenPOLAdjektiv(){
        wortunterartenPOLAdjektiv = new HashMap<String,String>();
        wortunterartenPOLAdjektiv.put("stfl", "forma z genusem");
        wortunterartenPOLAdjektiv.put("scfl", "określony - tylko w niemieckim");
        wortunterartenPOLAdjektiv.put("gefl", "nieokreślony - tylko w niemieckim");
        wortunterartenPOLAdjektiv.put("präd", "forma bez genusa");
    }

    public static String getWortunterartPOLAdjektiv(String key) throws UnknownKeyException{
        if(wortunterartenPOLAdjektiv==null){
           initWortunterartenPOLAdjektiv();
        }
        if(wortunterartenPOLAdjektiv.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLAdjektiv.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLAdjektiv(){
        if(wortunterartenPOLAdjektiv==null){
           initWortunterartenPOLAdjektiv();
        }
        return wortunterartenPOLAdjektiv;
    }

    public static String getWortunterartAdverb(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERAdverb(key);
            if(language==1) return getWortunterartPOLAdverb(key);
            return getWortunterartGERAdverb(key);
    }

    public static HashMap<String,String> getWortunterartenAdverb(){
            if(language==0) return getWortunterartenGERAdverb();
            if(language==1) return getWortunterartenPOLAdverb();
            return getWortunterartenGERAdverb();
    }

    //Initialisierung beim ersten Aufruf
    //TODO bei Gradpratikel noch die Stuffe des Grades ausdruecken
    public static void initWortunterartenGERAdverb(){
        wortunterartenGERAdverb = new HashMap<String,String>();
        wortunterartenGERAdverb.put("umst", "Umstandswort"); //Also Adverb im engeren Sinne
        wortunterartenGERAdverb.put("grad", "Gradpartikel");  //"sehr", "wenig", "einigermaßen", "überaus".
        wortunterartenGERAdverb.put("moda", "Modalpartikel"); //(auch Abtönungspartikeln oder Füllwörter) sehr, freilich, halt, eben .. sie werden verwendet, um die Haltung des Sprechers zur Satzaussage kenntlich zu machen
        wortunterartenGERAdverb.put("foku", "Fokusparikel"); //eben (auch modalpartikel)
        wortunterartenGERAdverb.put("nega", "Negationspartikel (im polnischen Pronomen)"); //nicht
        wortunterartenGERAdverb.put("pron", "Pronomialadverb"); //dazu", "hierzu", wozu" und "darüber", "worüber"
        wortunterartenGERAdverb.put("inte", "Interrogativadverb"); //"wo", "woher", wozu", "worüber" wann
        wortunterartenGERAdverb.put("życz", "Wunsch (PL)");
    }

    public static String getWortunterartGERAdverb(String key) throws UnknownKeyException{
        if(wortunterartenGERAdverb==null){
           initWortunterartenGERAdverb();
        }
        if(wortunterartenGERAdverb.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERAdverb.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERAdverb(){
        if(wortunterartenGERAdverb==null){
           initWortunterartenGERAdverb();
        }
        return wortunterartenGERAdverb;
    }

    //Initialisierung beim ersten Aufruf (Polisch - Przysłówek)
    public static void initWortunterartenPOLAdverb(){
        wortunterartenPOLAdverb = new HashMap<String,String>();
        wortunterartenPOLAdverb.put("umst", "cechy czynnosści"); //Przysłówek naturalny
        wortunterartenPOLAdverb.put("grad", "stopńowane cechy czynnosści"); // w niemieckim partykuła tutaj naszczęście i tak zaliczane do przysłówków
        wortunterartenPOLAdverb.put("moda", "modalny");
        wortunterartenPOLAdverb.put("foku", "partykuła wyróżnieniająca");
        wortunterartenPOLAdverb.put("nega", "partykuła przecząca"); //nie ani (mi się śni)
        wortunterartenPOLAdverb.put("pron", "partykuła osobista - nima w polskim");
        wortunterartenPOLAdverb.put("inte", "partykuła pytająca - patrz też zaimki"); //czy, -li (Znaszli ten kraj? Czy muszę to robić?)
        wortunterartenPOLAdverb.put("życz", "partykuła życząca"); //oby
    }

    public static String getWortunterartPOLAdverb(String key) throws UnknownKeyException{
        if(wortunterartenPOLAdverb==null){
           initWortunterartenPOLAdverb();
        }
        if(wortunterartenPOLAdverb.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLAdverb.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLAdverb(){
        if(wortunterartenPOLAdverb==null){
           initWortunterartenPOLAdverb();
        }
        return wortunterartenPOLAdverb;
    }

    public static String getWortunterartArtikel(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERArtikel(key);
            if(language==1) return getWortunterartPOLArtikel(key);
            return getWortunterartGERArtikel(key);
    }

    public static HashMap<String,String> getWortunterartenArtikel(){
            if(language==0) return getWortunterartenGERArtikel();
            if(language==1) return getWortunterartenPOLArtikel();
            return getWortunterartenGERArtikel();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERArtikel(){
        wortunterartenGERArtikel = new HashMap<String,String>();
        wortunterartenGERArtikel.put("best", "bestimmt");
        wortunterartenGERArtikel.put("unbe", "unbestimmt");
    }

    public static String getWortunterartGERArtikel(String key) throws UnknownKeyException{
        if(wortunterartenGERArtikel==null){
           initWortunterartenGERArtikel();
        }
        if(wortunterartenGERArtikel.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERArtikel.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERArtikel(){
        if(wortunterartenGERArtikel==null){
           initWortunterartenGERArtikel();
        }
        return wortunterartenGERArtikel;
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenPOLArtikel(){
        wortunterartenPOLArtikel = new HashMap<String,String>();
        wortunterartenPOLArtikel.put("best", "określony Rodzajnik");
        wortunterartenPOLArtikel.put("unbe", "nieokreślony Rodzajnik");
    }

    public static String getWortunterartPOLArtikel(String key) throws UnknownKeyException{
        if(wortunterartenPOLArtikel==null){
           initWortunterartenPOLArtikel();
        }
        if(wortunterartenPOLArtikel.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLArtikel.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLArtikel(){
        if(wortunterartenPOLArtikel==null){
           initWortunterartenPOLArtikel();
        }
        return wortunterartenPOLArtikel;
    }

    public static String getWortunterartKonjunktion(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERKonjunktion(key);
            if(language==1) return getWortunterartPOLKonjunktion(key);
            return getWortunterartGERKonjunktion(key);
    }

    public static HashMap<String,String> getWortunterartenKonjunktion(){
            if(language==0) return getWortunterartenGERKonjunktion();
            if(language==1) return getWortunterartenPOLKonjunktion();
            return getWortunterartenGERKonjunktion();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERKonjunktion(){
        wortunterartenGERKonjunktion = new HashMap<String,String>();
        wortunterartenGERKonjunktion.put("nebo", "nebenordnend");
        wortunterartenGERKonjunktion.put("unto", "unterordnend");
        wortunterartenGERKonjunktion.put("satz", "Satzteil");
        wortunterartenGERKonjunktion.put("mehr", "mehrteilig");
    }

    public static String getWortunterartGERKonjunktion(String key) throws UnknownKeyException{
        if(wortunterartenGERKonjunktion==null){
           initWortunterartenGERKonjunktion();
        }
        if(wortunterartenGERKonjunktion.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERKonjunktion.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERKonjunktion(){
        if(wortunterartenGERKonjunktion==null){
           initWortunterartenGERKonjunktion();
        }
        return wortunterartenGERKonjunktion;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Spójnik (Łącznik))
    public static void initWortunterartenPOLKonjunktion(){
        wortunterartenPOLKonjunktion = new HashMap<String,String>();
        wortunterartenPOLKonjunktion.put("nebo", "współrzędne");
        wortunterartenPOLKonjunktion.put("unto", "podrzędne");
        wortunterartenPOLKonjunktion.put("satz", "część zdania");
        wortunterartenPOLKonjunktion.put("mehr", "wieloczęstny");
    }

    public static String getWortunterartPOLKonjunktion(String key) throws UnknownKeyException{
        if(wortunterartenPOLKonjunktion==null){
           initWortunterartenPOLKonjunktion();
        }
        if(wortunterartenPOLKonjunktion.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLKonjunktion.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLKonjunktion(){
        if(wortunterartenPOLKonjunktion==null){
           initWortunterartenPOLKonjunktion();
        }
        return wortunterartenPOLKonjunktion;
    }

    public static String getWortunterartKonjunktionSemantisch(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERKonjunktionSemantisch(key);
            if(language==1) return getWortunterartPOLKonjunktionSemantisch(key);
            return getWortunterartGERKonjunktionSemantisch(key);
    }

    public static HashMap<String,String> getWortunterartenKonjunktionSemantisch(){
            if(language==0) return getWortunterartenGERKonjunktionSemantisch();
            if(language==1) return getWortunterartenPOLKonjunktionSemantisch();
            return getWortunterartenGERKonjunktionSemantisch();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERKonjunktionSemantisch(){
        wortunterartenGERKonjunktionSemantisch = new HashMap<String,String>();
        wortunterartenGERKonjunktionSemantisch.put("addi", "additiv (Anreihung a∩b)"); //und, weder – noch
        wortunterartenGERKonjunktionSemantisch.put("disj", "disjunktiv (Alternative a∪b)"); //entweder – oder, nicht nur
        wortunterartenGERKonjunktionSemantisch.put("kaus", "kausal (Grund b⇒a)"); //denn, nämlich, um, dass, weil: Er ist glücklich, denn er wird bald heiraten.
        wortunterartenGERKonjunktionSemantisch.put("expl", "explikativ (Erklärung a⇔b)"); //das heißt
        wortunterartenGERKonjunktionSemantisch.put("adve", "adversativ (Gegensatz a≠b)"); //allerdings, aber: Sie fragte ihn, aber er war ahnungslos.
        wortunterartenGERKonjunktionSemantisch.put("konz", "konzessiv (Einräumung ab∩ac)"); //wenn auch, obwohl: Es ist ein trauriger, wenn auch ein aufschlussreicher Tag.
        wortunterartenGERKonjunktionSemantisch.put("komp", "komparativ (Vergleich a==b)"); //als: Er mag sein Auto lieber als seine Frau.
    }

    public static String getWortunterartGERKonjunktionSemantisch(String key) throws UnknownKeyException{
        if(wortunterartenGERKonjunktionSemantisch==null){
           initWortunterartenGERKonjunktionSemantisch();
        }
        if(wortunterartenGERKonjunktionSemantisch.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERKonjunktionSemantisch.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERKonjunktionSemantisch(){
        if(wortunterartenGERKonjunktionSemantisch==null){
           initWortunterartenGERKonjunktionSemantisch();
        }
        return wortunterartenGERKonjunktionSemantisch;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Spójnik (Łącznik))
    public static void initWortunterartenPOLKonjunktionSemantisch(){
        wortunterartenPOLKonjunktionSemantisch = new HashMap<String,String>();
        wortunterartenPOLKonjunktionSemantisch.put("addi", "łączne (a∩b)"); //np. a, i, oraz, tudzież
        wortunterartenPOLKonjunktionSemantisch.put("disj", "rozłączne (a∪b)"); //np. albo, bądź, czy, lub
        wortunterartenPOLKonjunktionSemantisch.put("kaus", "wynikowe (a⇒b)"); //np. dlatego, i, przeto, tedy, więc, zatem
        wortunterartenPOLKonjunktionSemantisch.put("expl", "wyjaśniające (a⇔b)"); //np. czyli, mianowicie
        wortunterartenPOLKonjunktionSemantisch.put("adve", "przeciwstawne (a≠b)"); //np. a, aczkolwiek, ale, jednak, lecz, natomiast, zaś
        wortunterartenPOLKonjunktionSemantisch.put("konz", "przeciwstawne2 (ab∩ac)"); //ale, aczkolwiek, lecz
        wortunterartenPOLKonjunktionSemantisch.put("komp", "porównawcze (a==b)"); //jak
    }
    public static String getWortunterartPOLKonjunktionSemantisch(String key) throws UnknownKeyException{
        if(wortunterartenPOLKonjunktionSemantisch==null){
           initWortunterartenPOLKonjunktionSemantisch();
        }
        if(wortunterartenPOLKonjunktionSemantisch.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLKonjunktionSemantisch.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLKonjunktionSemantisch(){
        if(wortunterartenPOLKonjunktionSemantisch==null){
           initWortunterartenPOLKonjunktionSemantisch();
        }
        return wortunterartenPOLKonjunktionSemantisch;
    }

    public static String getWortunterartNomen(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERNomen(key);
            if(language==1) return getWortunterartPOLNomen(key);
            return getWortunterartGERNomen(key);
    }

    public static HashMap<String,String> getWortunterartenNomen(){
            if(language==0) return getWortunterartenGERNomen();
            if(language==1) return getWortunterartenPOLNomen();
            return getWortunterartenGERNomen();
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Rrzeczownik)
    public static void initWortunterartenGERNomen(){
        wortunterartenGERNomen = new HashMap<String,String>();
        wortunterartenGERNomen.put("norm", "normal");
        wortunterartenGERNomen.put("name", "Name");
        wortunterartenGERNomen.put("kürz", "Kürzel");
    }

    public static String getWortunterartGERNomen(String key) throws UnknownKeyException{
        if(wortunterartenGERNomen==null){
           initWortunterartenGERNomen();
        }
        if(wortunterartenGERNomen.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERNomen.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERNomen(){
        if(wortunterartenGERNomen==null){
           initWortunterartenGERNomen();
        }
        return wortunterartenGERNomen;
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenPOLNomen(){
        wortunterartenPOLNomen = new HashMap<String,String>();
        wortunterartenPOLNomen.put("norm", "normalny");
        wortunterartenPOLNomen.put("name", "nazwa");
        wortunterartenPOLNomen.put("kürz", "skrót");
    }

    public static String getWortunterartPOLNomen(String key) throws UnknownKeyException{
        if(wortunterartenPOLNomen==null){
           initWortunterartenPOLNomen();
        }
        if(wortunterartenPOLNomen.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLNomen.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLNomen(){
        if(wortunterartenPOLNomen==null){
           initWortunterartenPOLNomen();
        }
        return wortunterartenPOLNomen;
    }

    public static String getWortunterartNumeral(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERNumeral(key);
            if(language==1) return getWortunterartPOLNumeral(key);
            return getWortunterartGERNumeral(key);
    }

    public static HashMap<String,String> getWortunterartenNumeral(){
            if(language==0) return getWortunterartenGERNumeral();
            if(language==1) return getWortunterartenPOLNumeral();
            return getWortunterartenGERNumeral();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERNumeral(){
        wortunterartenGERNumeral = new HashMap<String,String>();
        wortunterartenGERNumeral.put("kard", "Kardinalzahl");
        wortunterartenGERNumeral.put("ordi", "Ordinalzahl");
        wortunterartenGERNumeral.put("dist", "Verteilungszahl (Distributiva)"); //zB je eines oder zum ersten
        wortunterartenGERNumeral.put("wied", "Wiederholungszahl (*mal *fach)"); //einmal, viermal, zwölfmal manchmal, mehrmals, vielmals zweifach, vierfach usw., unbestimmt mehrfach, vielfach
        wortunterartenGERNumeral.put("koll", "Gattungs&Sammelzahl (Kollektiva)"); //zB mehrerlei, mancherlei, vielerlei, allerelei..
        wortunterartenGERNumeral.put("part", "Bruchzahl (Partiva)");
        wortunterartenGERNumeral.put("unbe", "unbestimmte Zahlwörter");  //zB einige Ritter, manche Hühner, alle Menschen, eine ganze Stadt
    }

    public static String getWortunterartGERNumeral(String key) throws UnknownKeyException{
        if(wortunterartenGERNumeral==null){
           initWortunterartenGERNumeral();
        }
        if(wortunterartenGERNumeral.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERNumeral.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERNumeral(){
        if(wortunterartenGERNumeral==null){
           initWortunterartenGERNumeral();
        }
        return wortunterartenGERNumeral;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Liczebnik)
    public static void initWortunterartenPOLNumeral(){
        wortunterartenPOLNumeral = new HashMap<String,String>();
        wortunterartenPOLNumeral.put("kard", "główny"); //jeden, dwa, trzy, cztery, pięć,...
        wortunterartenPOLNumeral.put("ordi", "porządkowy"); //pierwszy, setny, tysięczny...
        wortunterartenPOLNumeral.put("dist", "zbiorowe"); //dwoje, troje, czworo, pięcioro, sześcioro, siedmioro, ośmioro, dziewięcioro, dziesięcioro
        wortunterartenPOLNumeral.put("wied", "mnożne lub wielokrotne"); // podwójny, potrójny, poczwórny lub trzykroć, dwakroć
        wortunterartenPOLNumeral.put("koll", "wielorakie"); //dwojaki, trojaki
        wortunterartenPOLNumeral.put("part", "ułamkowe"); //ćwierć, pół, półtora, jedna druga, trzy czwarte,
        wortunterartenPOLNumeral.put("unbe", "nieokreślony liczebnik"); //niewiele, kilka, kilkadziesiąt, kilkaset, wiele, trochę, dużo, mało
    }

    public static String getWortunterartPOLNumeral(String key) throws UnknownKeyException{
        if(wortunterartenPOLNumeral==null){
           initWortunterartenPOLNumeral();
        }
        if(wortunterartenPOLNumeral.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLNumeral.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLNumeral(){
        if(wortunterartenPOLNumeral==null){
           initWortunterartenPOLNumeral();
        }
        return wortunterartenPOLNumeral;
    }

    public static String getWortunterartPronomen(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERPronomen(key);
            if(language==1) return getWortunterartPOLPronomen(key);
            return getWortunterartGERPronomen(key);
    }

    public static HashMap<String,String> getWortunterartenPronomen(){
            if(language==0) return getWortunterartenGERPronomen();
            if(language==1) return getWortunterartenPOLPronomen();
            return getWortunterartenGERPronomen();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERPronomen(){
        wortunterartenGERPronomen = new HashMap<String,String>();
        wortunterartenGERPronomen.put("demo", "Demonstrativpronomen");
        wortunterartenGERPronomen.put("inde", "Indefinitpronomen");
        wortunterartenGERPronomen.put("inte", "Interrogativpronomen");
        wortunterartenGERPronomen.put("pers", "Personalpronomen");
        wortunterartenGERPronomen.put("poss", "Possesivpronomen");
        wortunterartenGERPronomen.put("refl", "Reflexivpronomen");
        wortunterartenGERPronomen.put("rela", "Relativpronomen");
        wortunterartenGERPronomen.put("prze", "Negationspartikel (im deutschen Adverb)");
        wortunterartenGERPronomen.put("upow", "Allgemeinpronomen (im deutschen Adverb)");
    }

    public static String getWortunterartGERPronomen(String key) throws UnknownKeyException{
        if(wortunterartenGERPronomen==null){
           initWortunterartenGERPronomen();
        }
        if(wortunterartenGERPronomen.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERPronomen.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERPronomen(){
        if(wortunterartenGERPronomen==null){
           initWortunterartenGERPronomen();
        }
        return wortunterartenGERPronomen;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Zaimek)
    public static void initWortunterartenPOLPronomen(){
        wortunterartenPOLPronomen = new HashMap<String,String>();
        wortunterartenPOLPronomen.put("demo", "wskazujący"); // (np. ten, ta, to, tamten, tam, tu, ów, tędy, taki, ci, tamci, owi)
        wortunterartenPOLPronomen.put("inde", "nieokreślony zaimek"); //(np. ktoś, coś, jakiś, gdzieś, kiedyś, cokolwiek)
        wortunterartenPOLPronomen.put("pers", "osobowy"); //(np. ja, ty, my, wy, oni, one)
        wortunterartenPOLPronomen.put("poss", "dzierżawczy"); // (np. mój, twój, nasz, wasz, ich, jego, jej)
        wortunterartenPOLPronomen.put("refl", "zwrotny"); // (np. se, się, siebie, sobie)
        wortunterartenPOLPronomen.put("rela", "względny"); //(np. kto, co, komu - bez znaku zapytania; łączą zdanie nadrzędne z podrzędnym)
        wortunterartenPOLPronomen.put("inte", "pytający"); //(np. kto? co? jaki? który? gdzie? kiedy? jak? komu? czemu? kogo?)
        wortunterartenPOLPronomen.put("prze", "przeczący"); // (np. nic, nikt, żaden, nigdy, nigdzie)
        wortunterartenPOLPronomen.put("upow", "upowszechniający"); //(np. wszyscy, zawsze)
    }

    public static String getWortunterartPOLPronomen(String key) throws UnknownKeyException{
        if(wortunterartenPOLPronomen==null){
           initWortunterartenPOLPronomen();
        }
        if(wortunterartenPOLPronomen.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLPronomen.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLPronomen(){
        if(wortunterartenPOLPronomen==null){
           initWortunterartenPOLPronomen();
        }
        return wortunterartenPOLPronomen;
    }

    public static String getWortunterartVerb(String key) throws UnknownKeyException{
            if(language==0) return getWortunterartGERVerb(key);
            if(language==1) return getWortunterartPOLVerb(key);
            return getWortunterartGERVerb(key);
    }

    public static HashMap<String,String> getWortunterartenVerb(){
            if(language==0) return getWortunterartenGERVerb();
            if(language==1) return getWortunterartenPOLVerb();
            return getWortunterartenGERVerb();
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenGERVerb(){
        wortunterartenGERVerb = new HashMap<String,String>();
        wortunterartenGERVerb.put("rege", "regelmässig");
        wortunterartenGERVerb.put("unre", "unregelmässig");
    }

    public static String getWortunterartGERVerb(String key) throws UnknownKeyException{
        if(wortunterartenGERVerb==null){
           initWortunterartenGERVerb();
        }
        if(wortunterartenGERVerb.get(key)==null) throw new UnknownKeyException();
        return wortunterartenGERVerb.get(key);
    }

    public static HashMap<String,String> getWortunterartenGERVerb(){
        if(wortunterartenGERVerb==null){
           initWortunterartenGERVerb();
        }
        return wortunterartenGERVerb;
    }

    //Initialisierung beim ersten Aufruf
    public static void initWortunterartenPOLVerb(){
        wortunterartenPOLVerb = new HashMap<String,String>();
        wortunterartenPOLVerb.put("rege", "regularny");
        wortunterartenPOLVerb.put("unre", "nie regularny");
    }

    public static String getWortunterartPOLVerb(String key) throws UnknownKeyException{
        if(wortunterartenPOLVerb==null){
           initWortunterartenPOLVerb();
        }
        if(wortunterartenPOLVerb.get(key)==null) throw new UnknownKeyException();
        return wortunterartenPOLVerb.get(key);
    }

    public static HashMap<String,String> getWortunterartenPOLVerb(){
        if(wortunterartenPOLVerb==null){
           initWortunterartenPOLVerb();
        }
        return wortunterartenPOLVerb;
    }
    //===================================================================
    //ENDE DER WORTUNTERARTEN - ANFANG VOM REST
    //===================================================================
    public static String getKasus(String key) throws UnknownKeyException{
            if(language==0) return getKasusGER(key);
            if(language==1) return getKasusPOL(key);
            return getKasusGER(key);
    }

    public static HashMap<String,String> getKasuse(){
            if(language==0) return getKasuseGER();
            if(language==1) return getKasusePOL();
            return getKasuseGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initKasuseGER(){
        kasuseGER = new HashMap<String,String>();
        kasuseGER.put("nomi", "nominativ");
        kasuseGER.put("nosi", "nominalisiert");
        kasuseGER.put("geni", "genitiv");
        kasuseGER.put("dati", "dativ");
        kasuseGER.put("akku", "akkusativ");
        kasuseGER.put("inst", "(Instrumentalis) - nicht in GER");
        kasuseGER.put("loca", "(Locativus) - nicht in GER");
        kasuseGER.put("geda", "genitiv oder dativ");
        kasuseGER.put("gda",  "genitiv dativ oder akkusativ");
        kasuseGER.put("woła", "wołacz - nicht in GER");
        kasuseGER.put("unde", "undefiniert");
    }

    public static String getKasusGER(String key) throws UnknownKeyException{
        if(kasuseGER==null){
           initKasuseGER();
        }
        if(kasuseGER.get(key)==null) throw new UnknownKeyException();
        return kasuseGER.get(key);
    }

    public static HashMap<String,String> getKasuseGER(){
        if(kasuseGER==null){
           initKasuseGER();
        }
        return kasuseGER;
    }

    //Initialisierung beim ersten Aufruf (Polnisch-Przypadek) 
    public static void initKasusePOL(){
        kasusePOL = new HashMap<String,String>();
        kasusePOL.put("nomi", "mianownik");
        kasusePOL.put("nosi", "rzeczownikowany");
        kasusePOL.put("geni", "dopełniacz");
        kasusePOL.put("dati", "celownik");
        kasusePOL.put("akku", "biernik - żadki w polkim");
        kasusePOL.put("inst", "narzędnik (Instrumentalis)");
        kasusePOL.put("loca", "miejscownik (Locativus)");
        kasusePOL.put("geda", "dopełniacz albo celownik");
        kasusePOL.put("gda",  "dopełniacz, celownik albo biernik");
        kasusePOL.put("woła", "wołacz");
        kasusePOL.put("unde", "nieokreślony");
    }

    public static String getKasusPOL(String key) throws UnknownKeyException{
        if(kasusePOL==null){
           initKasusePOL();
        }
        if(kasusePOL.get(key)==null) throw new UnknownKeyException();
        return kasusePOL.get(key);
    }

    public static HashMap<String,String> getKasusePOL(){
        if(kasusePOL==null){
           initKasusePOL();
        }
        return kasusePOL;
    }

    public static String getGenus(String key) throws UnknownKeyException{
            if(language==0) return getGenusGER(key);
            if(language==1) return getGenusPOL(key);
            return getGenusGER(key);
    }

    public static HashMap<String,String> getGenuse(){
            if(language==0) return getGenuseGER();
            if(language==1) return getGenusePOL();
            return getGenuseGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initGenuseGER(){
        genuseGER = new HashMap<String,String>();
        genuseGER.put("männ", "männlich");
        genuseGER.put("weib", "weiblich");
        genuseGER.put("unde", "sächlich");
        //In diesen fall ist undefiniert eindeutig "ES" und
        //nicht ersetztbar durh DONT CARE mwu
        genuseGER.put("mwu", "männlich, weiblich oder undefiniert");
        genuseGER.put("m&u", "männlich oder undefiniert");
    }

    public static String getGenusGER(String key) throws UnknownKeyException{
        if(genuseGER==null){
           initGenuseGER();
        }
        if(genuseGER.get(key)==null) throw new UnknownKeyException();
        return genuseGER.get(key);
    }

    public static HashMap<String,String> getGenuseGER(){
        if(genuseGER==null){
           initGenuseGER();
        }
        return genuseGER;
    }

    //Initialisierung beim ersten Aufruf (Polnisch-Rodzaj)
    public static void initGenusePOL(){
        genusePOL = new HashMap<String,String>();
        genusePOL.put("männ", "męski");
        genusePOL.put("weib", "żeński");
        genusePOL.put("unde", "nijaki");
        //In diesen fall ist undefiniert eindeutig "ES" und
        //nicht ersetztbar durh DONT CARE mwu
        genusePOL.put("mwu", "męski, żeński albo nijaki");
        genusePOL.put("m&u", "męski albo nijaki");
    }

    public static String getGenusPOL(String key) throws UnknownKeyException{
        if(genusePOL==null){
           initGenusePOL();
        }
        if(genusePOL.get(key)==null) throw new UnknownKeyException();
        return genusePOL.get(key);
    }

    public static HashMap<String,String> getGenusePOL(){
        if(genusePOL==null){
           initGenusePOL();
        }
        return genusePOL;
    }

    public static String getModus(String key) throws UnknownKeyException{
            if(language==0) return getModusGER(key);
            if(language==1) return getModusPOL(key);
            return getModusGER(key);
    }

    public static HashMap<String,String> getModuse(){
            if(language==0) return getModuseGER();
            if(language==1) return getModusePOL();
            return getModuseGER();
    }
    //TODO INFO: bis hier hin wurden die keys in der ontologie gesetzt
    //es ist sinnvolll sich zu entscheiden ob diese beim parsen gesetzt
    //werden und dannach wahrscheinlich keine instanzen mehr erstellt werden,
    //oder diese dort dierekt unter zu birngen (performance vs übersicht)
    //Initialisierung beim ersten Aufruf
    public static void initModuseGER(){
        moduseGER = new HashMap<String,String>();
        moduseGER.put("indi", "Indikativ");
        moduseGER.put("part", "Partizip");
        moduseGER.put("impe", "Imperativ");
        moduseGER.put("kon1", "Konjunktiv 1");
        moduseGER.put("kon2", "Konjunktiv 2");
        //Hierbei ist der Tempus für das jeweilige Modus entscheidend
        moduseGER.put("ko12", "Konjunktiv 1 oder 2");
    }

    public static String getModusGER(String key) throws UnknownKeyException{
        if(moduseGER==null){
           initModuseGER();
        }
        if(moduseGER.get(key)==null) throw new UnknownKeyException();
        return moduseGER.get(key);
    }

    public static HashMap<String,String> getModuseGER(){
        if(moduseGER==null){
           initModuseGER();
        }
        return moduseGER;
    }

    //Initialisierung beim ersten Aufruf (Polnisch- Tryby)
    public static void initModusePOL(){
        modusePOL = new HashMap<String,String>();
        modusePOL.put("indi", "orzekający (oznajmujący)");
        modusePOL.put("part", "imiesłów");
        modusePOL.put("impe", "rozkazujący");
        modusePOL.put("kon1", "przypuszczający/potencjalny niedokonany (teraz)");
        modusePOL.put("kon2", "przypuszczający/potencjalny dokonany (kiedyś)");
        //Hierbei ist der Tempus für das jeweilige Modus entscheidend
        modusePOL.put("ko12", "przypuszczający");
    }

    public static String getModusPOL(String key) throws UnknownKeyException{
        if(modusePOL==null){
           initModusePOL();
        }
        if(modusePOL.get(key)==null) throw new UnknownKeyException();
        return modusePOL.get(key);
    }

    public static HashMap<String,String> getModusePOL(){
        if(modusePOL==null){
           initModusePOL();
        }
        return modusePOL;
    }

    public static String getTempus(String key) throws UnknownKeyException{
            if(language==0) return getTempusGER(key);
            if(language==1) return getTempusPOL(key);
            return getTempusGER(key);
    }

    public static HashMap<String,String> getTempuse(){
            if(language==0) return getTempuseGER();
            if(language==1) return getTempusePOL();
            return getTempuseGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initTempuseGER(){
        tempuseGER = new HashMap<String,String>();
        tempuseGER.put("plus", "Plusquamperfekt");
        tempuseGER.put("prät", "Präteritum");
        tempuseGER.put("perf", "Perfekt");
        tempuseGER.put("pepl", "Perfekt oder Plusquamperfekt");
        tempuseGER.put("präs", "Präsens");
        tempuseGER.put("fut1", "Futur 1");
        tempuseGER.put("fut2", "Futur 2");
    }

    public static String getTempusGER(String key) throws UnknownKeyException{
        if(tempuseGER==null){
           initTempuseGER();
        }
        if(tempuseGER.get(key)==null) throw new UnknownKeyException();
        return tempuseGER.get(key);
    }

    public static HashMap<String,String> getTempuseGER(){
        if(tempuseGER==null){
           initTempuseGER();
        }
        return tempuseGER;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Czas+Aspekt(dokonany/niedomonany))
    //dokładne Info: http://pl.wikipedia.org/wiki/Aspekt_(językoznawstwo)
    public static void initTempusePOL(){
        tempusePOL = new HashMap<String,String>();
        tempusePOL.put("plus", "przeszły dawno dokonany");
        tempusePOL.put("prät", "przeszły dokonany (lub grzeczny bezokolicznik)");
        tempusePOL.put("perf", "przeszły dokonany");
        tempusePOL.put("pepl", "przeszły (dawno) dokonany");
        tempusePOL.put("präs", "terażniejszy niedokonany");
//        tempusePOL.put("pple", "dwuaspektowe (występujące w aspekcie dokonanym i niedokonanym)");
        tempusePOL.put("fut1", "przyszły niedokonany");
        tempusePOL.put("fut2", "przysyłz dokonany");
    }

    public static String getTempusPOL(String key) throws UnknownKeyException{
        if(tempusePOL==null){
           initTempusePOL();
        }
        if(tempusePOL.get(key)==null) throw new UnknownKeyException();
        return tempusePOL.get(key);
    }

    public static HashMap<String,String> getTempusePOL(){
        if(tempusePOL==null){
           initTempusePOL();
        }
        return tempusePOL;
    }

    public static String getAltus(String key) throws UnknownKeyException{        
            if(language==0) return getAltusGER(key);
            if(language==1) return getAltusPOL(key);
            return getAltusGER(key);
    }
    
    public static HashMap<String,String> getAltuse(){        
            if(language==0) return getAltuseGER();
            if(language==1) return getAltusePOL();
            return getAltuseGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initAltuseGER(){
        altuseGER = new HashMap<String,String>();
        altuseGER.put("alt", "alt");
        altuseGER.put("neu", "neu");
        altuseGER.put("unde", "undefiniert");
    }

    public static String getAltusGER(String key) throws UnknownKeyException{
        if(altuseGER==null){
           initAltuseGER();
        }
        if(altuseGER.get(key)==null) throw new UnknownKeyException();
        return altuseGER.get(key);
    }

    public static HashMap<String,String> getAltuseGER(){
        if(altuseGER==null){
           initAltuseGER();
        }
        return altuseGER;
    }

        //Initialisierung beim ersten Aufruf
    public static void initAltusePOL(){
        altusePOL = new HashMap<String,String>();
        altusePOL.put("alt", "staroświeckie");
        altusePOL.put("neu", "nowe");
        altusePOL.put("unde", "aktualne");
    }

    public static String getAltusPOL(String key) throws UnknownKeyException{
        if(altusePOL==null){
           initAltusePOL();
        }
        if(altusePOL.get(key)==null) throw new UnknownKeyException();
        return altusePOL.get(key);
    }

    public static HashMap<String,String> getAltusePOL(){
        if(altusePOL==null){
           initAltusePOL();
        }
        return altusePOL;
    }

    public static String getSteigerung(String key) throws UnknownKeyException{
            if(language==0) return getSteigerungGER(key);
            if(language==1) return getSteigerungPOL(key);
            return getSteigerungGER(key);
    }

    public static HashMap<String,String> getSteigerungen(){
            if(language==0) return getSteigerungenGER();
            if(language==1) return getSteigerungenPOL();
            return getSteigerungenGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initSteigerungenGER(){
        steigerungenGER = new HashMap<String,String>();
        steigerungenGER.put("posi", "positiv");
        steigerungenGER.put("komp", "komperativ");
        steigerungenGER.put("supe", "superlativ");
        steigerungenGER.put("elat", "elativ");
    }

    public static String getSteigerungGER(String key) throws UnknownKeyException{
        if(steigerungenGER==null){
           initSteigerungenGER();
        }
        if(steigerungenGER.get(key)==null) throw new UnknownKeyException();
        return steigerungenGER.get(key);
    }

    public static HashMap<String,String> getSteigerungenGER(){
        if(steigerungenGER==null){
           initSteigerungenGER();
        }
        return steigerungenGER;
    }

    //Initialisierung beim ersten Aufruf
    public static void initSteigerungenPOL(){
        steigerungenPOL = new HashMap<String,String>();
        steigerungenPOL.put("posi", "równy (equativus)");
        steigerungenPOL.put("komp", "wyższy (comparativus)");
        steigerungenPOL.put("supe", "najwyższy (superlativus)");
        steigerungenPOL.put("elat", "najnajwyższy (elativus) - nierozróżniany w polskim");
    }

    public static String getSteigerungPOL(String key) throws UnknownKeyException{
        if(steigerungenPOL==null){
           initSteigerungenPOL();
        }
        if(steigerungenPOL.get(key)==null) throw new UnknownKeyException();
        return steigerungenPOL.get(key);
    }

    public static HashMap<String,String> getSteigerungenPOL(){
        if(steigerungenPOL==null){
           initSteigerungenPOL();
        }
        return steigerungenPOL;
    }

    public static String getPerson(String key) throws UnknownKeyException{
            if(language==0) return getPersonGER(key);
            if(language==1) return getPersonPOL(key);
            return getPersonGER(key);
    }

    public static HashMap<String,String> getPersonen(){
            if(language==0) return getPersonenGER();
            if(language==1) return getPersonenPOL();
            return getPersonenGER();
    }

    //Initialisierung beim ersten Aufruf
    //hier entspricht 123 undefiniert
    public static void initPersonenGER(){
        personenGER = new HashMap<String,String>();
        personenGER.put("1p", "erste Person");
        personenGER.put("2p", "zweite Person");
        personenGER.put("3p", "dritte Person");
        personenGER.put("1&2", "erste und zweite Person");
        personenGER.put("1&3", "erste und dritte Person");
        personenGER.put("2&3", "zweite und dritte Person");
        personenGER.put("123", "erste zweite und dritte Person");
    }

    public static String getPersonGER(String key) throws UnknownKeyException{
        if(personenGER==null){
           initPersonenGER();
        }
        if(personenGER.get(key)==null) throw new UnknownKeyException();
        return personenGER.get(key);
    }

    public static HashMap<String,String> getPersonenGER(){
        if(personenGER==null){
           initPersonenGER();
        }
        return personenGER;
    }

        //Initialisierung beim ersten Aufruf
    //hier entspricht 123 undefiniert
    public static void initPersonenPOL(){
        personenPOL = new HashMap<String,String>();
        personenPOL.put("1p", "pierwsza osoba");
        personenPOL.put("2p", "druga osoba");
        personenPOL.put("3p", "trzecia osoba");
        personenPOL.put("1&2", "pierwsza i druga osoba");
        personenPOL.put("1&3", "pierwsza i trzecia osoba");
        personenPOL.put("2&3", "druga i trzecia osoba");
        personenPOL.put("123", "pierwsza druga i trzecia osoba");
    }

    public static String getPersonPOL(String key) throws UnknownKeyException{
        if(personenPOL==null){
           initPersonenPOL();
        }
        if(personenPOL.get(key)==null) throw new UnknownKeyException();
        return personenPOL.get(key);
    }

    public static HashMap<String,String> getPersonenPOL(){
        if(personenPOL==null){
           initPersonenPOL();
        }
        return personenPOL;
    }

    public static String getNumerus(String key) throws UnknownKeyException{
            if(language==0) return getNumerusGER(key);
            if(language==1) return getNumerusPOL(key);
            return getNumerusGER(key);
    }

    public static HashMap<String,String> getNumeruse(){
            if(language==0) return getNumeruseGER();
            if(language==1) return getNumerusePOL();
            return getNumeruseGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initNumeruseGER(){
        numeruseGER = new HashMap<String,String>();
        numeruseGER.put("sing", "Singular");
        numeruseGER.put("plur", "Plural 1");
        numeruseGER.put("plu2", "Plural 2");
        numeruseGER.put("unde", "undefiniert");
    }

    public static String getNumerusGER(String key) throws UnknownKeyException{
        if(numeruseGER==null){
           initNumeruseGER();
        }
        if(numeruseGER.get(key)==null) throw new UnknownKeyException();
        return numeruseGER.get(key);
    }

    public static HashMap<String,String> getNumeruseGER(){
        if(numeruseGER==null){
           initNumeruseGER();
        }
        return numeruseGER;
    }

        //Initialisierung beim ersten Aufruf
    public static void initNumerusePOL(){
        numerusePOL = new HashMap<String,String>();
        numerusePOL.put("sing", "liczba pojedyncza");
        numerusePOL.put("plur", "liczba mnoga");
        numerusePOL.put("plu2", "liczba w polskim nieznana");
        numerusePOL.put("unde", "nieokreślony");
    }

    public static String getNumerusPOL(String key) throws UnknownKeyException{
        if(numerusePOL==null){
           initNumerusePOL();
        }
        if(numerusePOL.get(key)==null) throw new UnknownKeyException();
        return numerusePOL.get(key);
    }

    public static HashMap<String,String> getNumerusePOL(){
        if(numerusePOL==null){
           initNumerusePOL();
        }
        return numerusePOL;
    }

    public static String getUmstand(String key) throws UnknownKeyException{
            if(language==0) return getUmstandGER(key);
            if(language==1) return getUmstandPOL(key);
            return getUmstandGER(key);
    }

    public static HashMap<String,String> getUmstände(){
            if(language==0) return getUmständeGER();
            if(language==1) return getUmständePOL();
            return getUmständeGER();
    }

    //Initialisierung beim ersten Aufruf
    public static void initUmständeGER(){
        umständeGER = new HashMap<String,String>();
        umständeGER.put("loka", "lokale");
        umständeGER.put("temp", "temporale");
        umständeGER.put("moda", "modale");
        umständeGER.put("kaus", "kausale");
        umständeGER.put("unde", "undefiniert");
        umständeGER.put("ort" , "Ort");
        umständeGER.put("zeit", "Zeit");
        umständeGER.put("ursa", "Ursache");
        umständeGER.put("a&w" , "Art und Weise");
        umständeGER.put("zwec", "Zweck");
    }

    public static String getUmstandGER(String key) throws UnknownKeyException{
        if(umständeGER==null){
           initUmständeGER();
        }
        if(umständeGER.get(key)==null) throw new UnknownKeyException();
        return umständeGER.get(key);
    }

    public static HashMap<String,String> getUmständeGER(){
        if(umständeGER==null){
           initUmständeGER();
        }
        return umständeGER;
    }

    //Initialisierung beim ersten Aufruf (Polnisch - Okolicznik)
    public static void initUmständePOL(){
        umständePOL = new HashMap<String,String>();
        umständePOL.put("loka", "(miejsca) lokatywny miejsca"); //Maria stała przed domem
        umständePOL.put("lok2", "(miejsca) ablatwywny miejsca");//Marta wróciła z Francji.
        umständePOL.put("lok3", "(miejsca) allatywny miejsca"); //Płynę do Szwecji.
        umständePOL.put("lok4", "(miejsca) perlatywny miejsca");//Biegliśmy przez ogród.
        umständePOL.put("temp", "(miary) czasu");               //Jutro zrobię to.
        umständePOL.put("tem2", "(miary) częstotliwości");      //Rzadko zapominam zmieniać wodę u kwiatków
        umständePOL.put("tem3", "(miary) prędkości");           //Jechał wolno.
        umständePOL.put("tem4", "(miary) dystrybutywny");       //Dostaliśmy po dwadzieścia złotych.
        umständePOL.put("moda", "względu (jakość)");            //Jakościowo jest on tragiczny.
        umständePOL.put("kaus", "z powodu");                    //Ponieważ on to i ja
        umständePOL.put("unde", "undefiniert");
        umständePOL.put("ort" , "(miejsca) lokatywny miejsca"); //Maria stała przed domem JAK LOKA
        umständePOL.put("zeit", "(miary) czasu");               //Jutro zrobię to.        JAK TEMP
        umständePOL.put("ursa", "przyczyny");                   //Zmęczył się od jazdy.
        umständePOL.put("a&w" , "sposobu");                     //Mówił z zaangażowaniem.
        umständePOL.put("zwec", "celu");                        //Idę po truskawki.
        umständePOL.put("sktu", "skutku");                      //Bezskutecznie ratował psa.
        umständePOL.put("poró", "porównawczy");                 //Uparł się jak osioł.
        umständePOL.put("stop", "stopnia");                     //Bardzo podobał mi się ten film.
        umständePOL.put("waru", "warunku");                     //Przy szczęściu może to uczynię.
        umständePOL.put("towa", "czynnika towarzyszącego");     //Rozstaliśmy się z żalem.
    }

    public static String getUmstandPOL(String key) throws UnknownKeyException{
        if(umständePOL==null){
           initUmständePOL();
        }
        if(umständePOL.get(key)==null) throw new UnknownKeyException();
        return umständePOL.get(key);
    }

    public static HashMap<String,String> getUmständePOL(){
        if(umständePOL==null){
           initUmständePOL();
        }
        return umständePOL;
    }

    /**
     * @return the language
     */
    public static short getLanguage() {
        return language;
    }

    /**
     * @param aLanguage the language to set
     */
    public static void setLanguage(short aLanguage) {
        System.out.println("Die sprache worde geändert auf: "+aLanguage);
        language = aLanguage;
    }

}
