/*
 * AnalyseAboutBox.java
 */
package annalyse.gui;

import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import annalyse.grammatik.attribute.ressources.WAtt;
import annalyse.grammatik.wortarten.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.jdesktop.application.Action;

/**
 * 
 * @author Maciej Niemczyk
 */
public class CreateWortView extends javax.swing.JDialog {
    private EntityManager emGER;
    private EntityManager emPOL;
    private EntityManager em;
    //Die ausgewählten Wörter werden gespeichert um durch das anklicken der
    //auswahlCoboBox erneut bearbeitet werden zu können ohne wieder in der
    //Tabelle angeklickt werden zu müssen
    private Adjektiv adjektivTemp;
    private Adverb adverbTemp;
    private Artikel artikelTemp;
    private Interjektion interjektionTemp;
    private Konjunktion konjunktionTemp;
    private Konjunktion andereKonjunktion;
    private Nomen nomenTemp;
    private Numeral numeralTemp;
    private Postposition postpositionTemp;
    private Präposition präpositionTemp;
    private Pronomen pronomenTemp;
    private Verb verbTemp;
    private Verb hilfsVerb;

    public CreateWortView(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(refreshButton);
        emGER = AnalyseApp.getApplication().getAnna().getEMGER();
        emPOL = AnalyseApp.getApplication().getAnna().getEMPOL();
        initComboBoxen();
        updateTable();
    }

    @Action
    public void initComboBoxen(){
        altuseVerbComboBox.removeAllItems();
        Iterator ait = WAtt.getAltuse().keySet().iterator();
        while (ait.hasNext()) {
            String key = (String) ait.next();
            try { 
                altuseVerbComboBox.addItem(key + " :: " + WAtt.getAltus(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        genuseAdjektivComboBox.removeAllItems();
        genuseArtikelComboBox.removeAllItems();
        genuseNomenComboBox.removeAllItems();
        genuseNumeralComboBox.removeAllItems();
        genusePronomenComboBox.removeAllItems();
        genuseVerbComboBox.removeAllItems();
        Iterator git = WAtt.getGenuse().keySet().iterator();
        while (git.hasNext()) {           
            try {
                String key = (String) git.next();
                genuseAdjektivComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                genuseArtikelComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                genuseNomenComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                genuseNumeralComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                genusePronomenComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                if(WAtt.getLanguage()==0){
                    genuseVerbComboBox.setEnabled(false);
                }else{
                    genuseVerbComboBox.setEnabled(true);
                    genuseVerbComboBox.addItem(key + " :: " + WAtt.getGenus(key));
                }
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        kasuseAdjektivComboBox.removeAllItems();
        kasuseArtikelComboBox.removeAllItems();
        kasuseNomenComboBox.removeAllItems();
        kasuseNumeralComboBox.removeAllItems();
        kasusePronomenComboBox.removeAllItems();
        kasusePräpositionComboBox.removeAllItems();
        kasusePostpositionComboBox.removeAllItems();
        Iterator kit = WAtt.getKasuse().keySet().iterator();
        while (kit.hasNext()) {
            try {
                String key = (String) kit.next();
                kasuseAdjektivComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasuseArtikelComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasuseNomenComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasuseNumeralComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasusePronomenComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasusePräpositionComboBox.addItem(key + " :: " + WAtt.getKasus(key));
                kasusePostpositionComboBox.addItem(key + " :: " + WAtt.getKasus(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        moduseVerbComboBox.removeAllItems();
        Iterator mit = WAtt.getModuse().keySet().iterator();
        while (mit.hasNext()) {
            try {
                String key = (String) mit.next();
                moduseVerbComboBox.addItem(key + " :: " + WAtt.getModus(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        numeruseAdjektivComboBox.removeAllItems();
        numeruseArtikelComboBox.removeAllItems();
        numeruseNomenComboBox.removeAllItems();
        numerusePronomenComboBox.removeAllItems();
        numeruseVerbComboBox.removeAllItems();
        Iterator nit = WAtt.getNumeruse().keySet().iterator();
        while (nit.hasNext()) {
            String key = (String) nit.next();
            try {
                numeruseAdjektivComboBox.addItem(key + " :: " + WAtt.getNumerus(key));
                numeruseArtikelComboBox.addItem(key + " :: " + WAtt.getNumerus(key));
                numeruseNomenComboBox.addItem(key + " :: " + WAtt.getNumerus(key));
                numerusePronomenComboBox.addItem(key + " :: " + WAtt.getNumerus(key));
                numeruseVerbComboBox.addItem(key + " :: " + WAtt.getNumerus(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        personenArtikelComboBox.removeAllItems();
        personenPronomenComboBox.removeAllItems();
        personenVerbComboBox.removeAllItems();
        Iterator pit = WAtt.getPersonen().keySet().iterator();
        while (pit.hasNext()) {
            String key = (String) pit.next();
            try {
                personenArtikelComboBox.addItem(key + " :: " + WAtt.getPerson(key));
                personenPronomenComboBox.addItem(key + " :: " + WAtt.getPerson(key));
                personenVerbComboBox.addItem(key + " :: " + WAtt.getPerson(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        steigerungenAdjektivComboBox.removeAllItems();
        Iterator sit = WAtt.getSteigerungen().keySet().iterator();
        while (sit.hasNext()) {
            String key = (String) sit.next();
            try {
                steigerungenAdjektivComboBox.addItem(key + " :: " + WAtt.getSteigerung(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        tempuseVerbComboBox.removeAllItems();
        Iterator tit = WAtt.getTempuse().keySet().iterator();
        while (tit.hasNext()) {
            String key = (String) tit.next();
            try {
                tempuseVerbComboBox.addItem(key + " :: " + WAtt.getTempus(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        umstaendeAdverbComboBox.removeAllItems();
        umstaendePräpositionComboBox.removeAllItems();
        umstaendePostpositionComboBox.removeAllItems();
        Iterator uit = WAtt.getUmstände().keySet().iterator();
        while (uit.hasNext()) {
            String key = (String) uit.next();
            try {
                umstaendeAdverbComboBox.addItem(key + " :: " + WAtt.getUmstand(key));
                umstaendePräpositionComboBox.addItem(key + " :: " + WAtt.getUmstand(key));
                umstaendePostpositionComboBox.addItem(key + " :: " + WAtt.getUmstand(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenAdjektivComboBox.removeAllItems();
        Iterator wit = WAtt.getWortunterartenAdjektiv().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenAdjektivComboBox.addItem(key + " :: " + WAtt.getWortunterartAdjektiv(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenAdverbComboBox.removeAllItems();
        wit = WAtt.getWortunterartenAdverb().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenAdverbComboBox.addItem(key + " :: " + WAtt.getWortunterartAdverb(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenArtikelComboBox.removeAllItems();
        wit = WAtt.getWortunterartenArtikel().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenArtikelComboBox.addItem(key + " :: " + WAtt.getWortunterartArtikel(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenKonjunktionComboBox.removeAllItems();
        wit = WAtt.getWortunterartenKonjunktion().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenKonjunktionComboBox.addItem(key + " :: " + WAtt.getWortunterartKonjunktion(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenNomenComboBox.removeAllItems();
        wit = WAtt.getWortunterartenNomen().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenNomenComboBox.addItem(key + " :: " + WAtt.getWortunterartNomen(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenNumeralComboBox.removeAllItems();
        wit = WAtt.getWortunterartenNumeral().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenNumeralComboBox.addItem(key + " :: " + WAtt.getWortunterartNumeral(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenPronomenComboBox.removeAllItems();
        wit = WAtt.getWortunterartenPronomen().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenPronomenComboBox.addItem(key + " :: " + WAtt.getWortunterartPronomen(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wortunterartenVerbComboBox.removeAllItems();
        wit = WAtt.getWortunterartenVerb().keySet().iterator();
        while (wit.hasNext()) {
            String key = (String) wit.next();
            try {
                wortunterartenVerbComboBox.addItem(key + " :: " + WAtt.getWortunterartVerb(key));
            } catch (UnknownKeyException ex) {
                Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Action
    public void saveWord() {
        System.out.println("na dann versuchen wir mal zu speichern");
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adjektivPanel")) {
            if (!lexemAdjektivTextField.getText().equals("") && !infiAdjektivTextField.getText().equals("")) {
                boolean ok = true;
                Adjektiv a;
                if (adjektivTemp != null && auswahlCheckBox.isSelected()) {
                    a = adjektivTemp;
                    auswahlCheckBox.setSelected(false);
                    adjektivTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                    if(WAtt.getLanguage()==1) emPOL.merge(a);
                } else {
                    a = new Adjektiv();
                }
                a.setLexem(lexemAdjektivTextField.getText());
                a.setGrundform(infiAdjektivTextField.getText());
                String wortkey = (String) wortunterartenAdjektivComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String steikey = (String) steigerungenAdjektivComboBox.getSelectedItem();
                steikey = steikey.substring(0, steikey.indexOf(" ::"));
                String kasukey = (String) kasuseAdjektivComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String genukey = (String) genuseAdjektivComboBox.getSelectedItem();
                genukey = genukey.substring(0, genukey.indexOf(" ::"));
                String numekey = (String) numeruseAdjektivComboBox.getSelectedItem();
                numekey = numekey.substring(0, numekey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setSteigerung(steikey);
                    a.setKasus(kasukey);
                    a.setGenus(genukey);
                    a.setNumerus(numekey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateAdjektivTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adverbPanel")) {
            if (!lexemAdverbTextField.getText().equals("")) {
                boolean ok = true;
                Adverb a;
                if (adverbTemp != null && auswahlCheckBox.isSelected()) {
                    a = adverbTemp;
                    auswahlCheckBox.setSelected(false);
                    adverbTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Adverb();
                }
                a.setLexem(lexemAdverbTextField.getText());
                String wortkey = (String) wortunterartenAdverbComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String umstkey = (String) umstaendeAdverbComboBox.getSelectedItem();
                umstkey = umstkey.substring(0, umstkey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setUmstand(umstkey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateAdverbTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("artikelPanel")) {
            if (!lexemArtikelTextField.getText().equals("")) {
                boolean ok = true;
                Artikel a;
                if (artikelTemp != null && auswahlCheckBox.isSelected()) {
                    a = artikelTemp;
                    auswahlCheckBox.setSelected(false);
                    artikelTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Artikel();
                }
                a.setLexem(lexemArtikelTextField.getText());
                String wortkey = (String) wortunterartenArtikelComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String genukey = (String) genuseArtikelComboBox.getSelectedItem();
                genukey = genukey.substring(0, genukey.indexOf(" ::"));
                String kasukey = (String) kasuseArtikelComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String perskey = (String) personenArtikelComboBox.getSelectedItem();
                perskey = perskey.substring(0, perskey.indexOf(" ::"));
                String numekey = (String) numeruseArtikelComboBox.getSelectedItem();
                numekey = numekey.substring(0, numekey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setGenus(genukey);
                    a.setKasus(kasukey);
                    a.setPerson(perskey);
                    a.setNumerus(numekey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateArtikelTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("interjektionPanel")) {
            if (!lexemInterjektionTextField.getText().equals("") && !ausdruckInterjektionTextField.getText().equals("")) {
                boolean ok = true;
                Interjektion a;
                if (interjektionTemp != null && auswahlCheckBox.isSelected()) {
                    a = interjektionTemp;
                    auswahlCheckBox.setSelected(false);
                    interjektionTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Interjektion();
                }
                a.setLexem(lexemInterjektionTextField.getText());
                a.setAusdruck(ausdruckInterjektionTextField.getText());
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateInterjektionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("konjunktionPanel")) {
            if (!lexemKonjunktionTextField.getText().equals("")) {
                boolean ok = true;
                Konjunktion a;
                if (konjunktionTemp != null && auswahlCheckBox.isSelected() && !aTeilToggleButton.isSelected()) {
                    a = konjunktionTemp;
                    auswahlCheckBox.setSelected(false);
                    konjunktionTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Konjunktion();
                }
                a.setLexem(lexemKonjunktionTextField.getText());
                if(andereKonjunktion!=null) a.setAndererTeilID(andereKonjunktion.getId());
                String konjkey = (String) wortunterartenKonjunktionComboBox.getSelectedItem();
                konjkey = konjkey.substring(0, konjkey.indexOf(" ::"));
                try {
                    a.setWortunterart(konjkey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateKonjunktionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("nomenPanel")) {
            if (!lexemNomenTextField.getText().equals("")) {
                boolean ok = true;
                Nomen a;
                if (nomenTemp != null && auswahlCheckBox.isSelected()) {
                    a = nomenTemp;
                    auswahlCheckBox.setSelected(false);
                    nomenTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Nomen();
                }
                a.setLexem(lexemNomenTextField.getText());
                String wortkey = (String) wortunterartenNomenComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String kasukey = (String) kasuseNomenComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String genukey = (String) genuseNomenComboBox.getSelectedItem();
                genukey = genukey.substring(0, genukey.indexOf(" ::"));
                String numekey = (String) numeruseNomenComboBox.getSelectedItem();
                numekey = numekey.substring(0, numekey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setKasus(kasukey);
                    a.setGenus(genukey);
                    a.setNumerus(numekey);
                    a.setBelebt(belebtNomenCheckBox.isSelected());
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateNomenTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("numeralPanel")) {
            if (!lexemNumeralTextField.getText().equals("") && !wertNumeralTextField.getText().equals("")) {
                boolean ok = true;
                Numeral a;
                if (numeralTemp != null && auswahlCheckBox.isSelected()) {
                    a = numeralTemp;
                    auswahlCheckBox.setSelected(false);
                    numeralTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Numeral();
                }
                a.setLexem(lexemNumeralTextField.getText());
                a.setWert(Integer.parseInt(wertNumeralTextField.getText()));
                String wortkey = (String) wortunterartenNumeralComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String kasukey = (String) kasuseNumeralComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String genukey = (String) genuseNumeralComboBox.getSelectedItem();
                genukey = genukey.substring(0, genukey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setKasus(kasukey);
                    a.setGenus(genukey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updateNumeralTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("präpositionPanel")) {
            if (!lexemPräpositionTextField.getText().equals("")) {
                boolean ok = true;
                Präposition a;
                if (präpositionTemp != null && auswahlCheckBox.isSelected()) {
                    a = präpositionTemp;
                    auswahlCheckBox.setSelected(false);
                    präpositionTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Präposition();
                }
                a.setLexem(lexemPräpositionTextField.getText());
                String kasukey = (String) kasusePräpositionComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String umstkey = (String) umstaendePräpositionComboBox.getSelectedItem();
                umstkey = umstkey.substring(0, umstkey.indexOf(" ::"));
                try {
                    a.setKasus(kasukey);
                    a.setUmstand(umstkey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updatePräpositionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("pronomenPanel")) {
            if (!lexemPronomenTextField.getText().equals("")) {
                boolean ok = true;
                Pronomen a;
                if (pronomenTemp != null && auswahlCheckBox.isSelected()) {
                    a = pronomenTemp;
                    auswahlCheckBox.setSelected(false);
                    pronomenTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Pronomen();
                }
                a.setLexem(lexemPronomenTextField.getText());
                String wortkey = (String) wortunterartenPronomenComboBox.getSelectedItem();
                wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                String kasukey = (String) kasusePronomenComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String genukey = (String) genusePronomenComboBox.getSelectedItem();
                genukey = genukey.substring(0, genukey.indexOf(" ::"));
                String perskey = (String) personenPronomenComboBox.getSelectedItem();
                perskey = perskey.substring(0, perskey.indexOf(" ::"));
                String numekey = (String) numerusePronomenComboBox.getSelectedItem();
                numekey = numekey.substring(0, numekey.indexOf(" ::"));
                try {
                    a.setWortunterart(wortkey);
                    a.setKasus(kasukey);
                    a.setGenus(genukey);
                    a.setPerson(perskey);
                    a.setNumerus(numekey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updatePronomenTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("postpositionPanel")) {
            if (!lexemPostpositionTextField.getText().equals("")) {
                boolean ok = true;
                Postposition a;
                if (postpositionTemp != null && auswahlCheckBox.isSelected()) {
                    a = postpositionTemp;
                    auswahlCheckBox.setSelected(false);
                    postpositionTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Postposition();
                }
                a.setLexem(lexemPostpositionTextField.getText());
                String kasukey = (String) kasusePostpositionComboBox.getSelectedItem();
                kasukey = kasukey.substring(0, kasukey.indexOf(" ::"));
                String umstkey = (String) umstaendePostpositionComboBox.getSelectedItem();
                umstkey = umstkey.substring(0, umstkey.indexOf(" ::"));
                try {
                    a.setKasus(kasukey);
                    a.setUmstand(umstkey);
                } catch (UnknownKeyException ex) {
                    Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                    ok = false;
                }
                if (ok) {
                    AnalyseApp.getApplication().getAnna().learnWord(a);
                    auswahlCheckBox.setSelected(false);
                }
                updatePostpositionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("verbPanel")) {
            if (!lexemVerbTextField.getText().equals("") && !infiVerbTextField.getText().equals("")) {
                boolean ok = true;
                Verb a;
                if (verbTemp != null && auswahlCheckBox.isSelected() && !hilfsVerbToggleButton.isSelected()) {
                    a = verbTemp;
                    auswahlCheckBox.setSelected(false);
                    verbTemp = null;
                    if(WAtt.getLanguage()==0) emGER.merge(a);
                } else {
                    a = new Verb();
                }
                a.setLexem(lexemVerbTextField.getText());
                a.setInfinitiv(infiVerbTextField.getText());
                String wortkey = (String) wortunterartenVerbComboBox.getSelectedItem();
                if (wortkey.startsWith("rege")) {
                    String infi = infiVerbTextField.getText();
                    String stamm = infi.substring(0, infi.length() - 2);
                    Verb a1psIndiPrae = new Verb();
                    a1psIndiPrae.setLexem(stamm + "e");
                    a1psIndiPrae.setInfinitiv(infi);
                    try {
                        a1psIndiPrae.setWortunterart("rege");
                        a1psIndiPrae.setModus("indi");
                        a1psIndiPrae.setTempus("präs");
                        a1psIndiPrae.setAltus("unde");
                        a1psIndiPrae.setPerson("1p");
                        a1psIndiPrae.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1psIndiPrae);
                    }

                    Verb a2psIndiPrae = new Verb();
                    a2psIndiPrae.setLexem(stamm + "st");
                    a2psIndiPrae.setInfinitiv(infi);
                    try {
                        a2psIndiPrae.setWortunterart("rege");
                        a2psIndiPrae.setModus("indi");
                        a2psIndiPrae.setTempus("präs");
                        a2psIndiPrae.setAltus("unde");
                        a2psIndiPrae.setPerson("2p");
                        a2psIndiPrae.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2psIndiPrae);
                    }


                    Verb a3psIndiPrae = new Verb();
                    a3psIndiPrae.setLexem(stamm + "t");
                    a3psIndiPrae.setInfinitiv(infi);
                    try {
                        a3psIndiPrae.setWortunterart("rege");
                        a3psIndiPrae.setModus("indi");
                        a3psIndiPrae.setTempus("präs");
                        a3psIndiPrae.setAltus("unde");
                        a3psIndiPrae.setPerson("3p");
                        a3psIndiPrae.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a3psIndiPrae);
                    }

                    Verb a1ppIndiPrae = new Verb();
                    a1ppIndiPrae.setLexem(stamm + "en");
                    a1ppIndiPrae.setInfinitiv(infi);
                    try {
                        a1ppIndiPrae.setWortunterart("rege");
                        a1ppIndiPrae.setModus("indi");
                        a1ppIndiPrae.setTempus("präs");
                        a1ppIndiPrae.setAltus("unde");
                        a1ppIndiPrae.setPerson("1p");
                        a1ppIndiPrae.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1ppIndiPrae);
                    }

                    Verb a2ppIndiPrae = new Verb();
                    a2ppIndiPrae.setLexem(stamm + "t");
                    a2ppIndiPrae.setInfinitiv(infi);
                    try {
                        a2ppIndiPrae.setWortunterart("rege");
                        a2ppIndiPrae.setModus("indi");
                        a2ppIndiPrae.setTempus("präs");
                        a2ppIndiPrae.setAltus("unde");
                        a2ppIndiPrae.setPerson("2p");
                        a2ppIndiPrae.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2ppIndiPrae);
                    }


                    Verb a3ppIndiPrae = new Verb();
                    a3ppIndiPrae.setLexem(stamm + "en");
                    a3ppIndiPrae.setInfinitiv(infi);
                    try {
                        a3ppIndiPrae.setWortunterart("rege");
                        a3ppIndiPrae.setModus("indi");
                        a3ppIndiPrae.setTempus("präs");
                        a3ppIndiPrae.setAltus("unde");
                        a3ppIndiPrae.setPerson("3p");
                        a3ppIndiPrae.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a3ppIndiPrae);
                    }

                    Verb a123psPartPrae = new Verb();
                    a123psPartPrae.setLexem(stamm + "end");
                    a123psPartPrae.setInfinitiv(infi);
                    try {
                        a123psPartPrae.setWortunterart("rege");
                        a123psPartPrae.setModus("papr");
                        a123psPartPrae.setTempus("präs");
                        a123psPartPrae.setAltus("unde");
                        a123psPartPrae.setPerson("123");
                        a123psPartPrae.setNumerus("unde");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a123psPartPrae);
                    }

                    Verb a1psPPPraet = new Verb();
                    a1psPPPraet.setLexem(stamm + "te");
                    a1psPPPraet.setInfinitiv(infi);
                    try {
                        a1psPPPraet.setWortunterart("rege");
                        a1psPPPraet.setModus("papr");
                        a1psPPPraet.setTempus("prät");
                        a1psPPPraet.setAltus("unde");
                        a1psPPPraet.setPerson("1p");
                        a1psPPPraet.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1psPPPraet);
                    }

                    Verb a2psPPPraet = new Verb();
                    a2psPPPraet.setLexem(stamm + "test");
                    a2psPPPraet.setInfinitiv(infi);
                    try {
                        a2psPPPraet.setWortunterart("rege");
                        a2psPPPraet.setModus("papr");
                        a2psPPPraet.setTempus("prät");
                        a2psPPPraet.setAltus("unde");
                        a2psPPPraet.setPerson("2p");
                        a2psPPPraet.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2psPPPraet);
                    }

                    Verb a3psPPPraet = new Verb();
                    a3psPPPraet.setLexem(stamm + "te");
                    a3psPPPraet.setInfinitiv(infi);
                    try {
                        a3psPPPraet.setWortunterart("rege");
                        a3psPPPraet.setModus("papr");
                        a3psPPPraet.setTempus("prät");
                        a3psPPPraet.setAltus("unde");
                        a3psPPPraet.setPerson("3p");
                        a3psPPPraet.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a3psPPPraet);
                    }

                    Verb a1ppPPPraet = new Verb();
                    a1ppPPPraet.setLexem(stamm + "ten");
                    a1ppPPPraet.setInfinitiv(infi);
                    try {
                        a1ppPPPraet.setWortunterart("rege");
                        a1ppPPPraet.setModus("papr");
                        a1ppPPPraet.setTempus("prät");
                        a1ppPPPraet.setAltus("unde");
                        a1ppPPPraet.setPerson("1p");
                        a1ppPPPraet.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1ppPPPraet);
                    }

                    Verb a2ppPPPraet = new Verb();
                    a2ppPPPraet.setLexem(stamm + "tet");
                    a2ppPPPraet.setInfinitiv(infi);
                    try {
                        a2ppPPPraet.setWortunterart("rege");
                        a2ppPPPraet.setModus("papr");
                        a2ppPPPraet.setTempus("prät");
                        a2ppPPPraet.setAltus("unde");
                        a2ppPPPraet.setPerson("2p");
                        a2ppPPPraet.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2ppPPPraet);
                    }

                    Verb a3ppPPPraet = new Verb();
                    a3ppPPPraet.setLexem(stamm + "ten");
                    a3ppPPPraet.setInfinitiv(infi);
                    try {
                        a3ppPPPraet.setWortunterart("rege");
                        a3ppPPPraet.setModus("papr");
                        a3ppPPPraet.setTempus("prät");
                        a3ppPPPraet.setAltus("unde");
                        a3ppPPPraet.setPerson("3p");
                        a3ppPPPraet.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a3ppPPPraet);
                    }

                    Verb a123psP2PP = new Verb();
                    a123psP2PP.setLexem("ge" + stamm + "t");
                    a123psP2PP.setInfinitiv(infi);
                    try {
                        a123psP2PP.setWortunterart("rege");
                        a123psP2PP.setModus("par2");
                        a123psP2PP.setTempus("pepl");
                        a123psP2PP.setAltus("unde");
                        a123psP2PP.setPerson("123");
                        a123psP2PP.setNumerus("unde");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a123psP2PP);
                    }

                    Verb a2psimerPP = new Verb();
                    a2psimerPP.setLexem(stamm);
                    a2psimerPP.setInfinitiv(infi);
                    try {
                        a2psimerPP.setWortunterart("rege");
                        a2psimerPP.setModus("impe");
                        a2psimerPP.setTempus("pepl");
                        a2psimerPP.setAltus("unde");
                        a2psimerPP.setPerson("2p");
                        a2psimerPP.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2psimerPP);
                    }

                    Verb a2ppimerPP = new Verb();
                    a2ppimerPP.setLexem(stamm + "et");
                    a2ppimerPP.setInfinitiv(infi);
                    try {
                        a2ppimerPP.setWortunterart("rege");
                        a2ppimerPP.setModus("impe");
                        a2ppimerPP.setTempus("pepl");
                        a2ppimerPP.setAltus("unde");
                        a2ppimerPP.setPerson("2p");
                        a2ppimerPP.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2ppimerPP);
                    }

                    Verb a1psK2PP = new Verb();
                    a1psK2PP.setLexem(stamm + "e");
                    a1psK2PP.setInfinitiv(infi);
                    try {
                        a1psK2PP.setWortunterart("rege");
                        a1psK2PP.setModus("kon2");
                        a1psK2PP.setTempus("pepl");
                        a1psK2PP.setAltus("unde");
                        a1psK2PP.setPerson("1&3");
                        a1psK2PP.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1psK2PP);
                    }

                    Verb a2psK2PP = new Verb();
                    a2psK2PP.setLexem(stamm + "est");
                    a2psK2PP.setInfinitiv(infi);
                    try {
                        a2psK2PP.setWortunterart("rege");
                        a2psK2PP.setModus("kon2");
                        a2psK2PP.setTempus("pepl");
                        a2psK2PP.setAltus("unde");
                        a2psK2PP.setPerson("2p");
                        a2psK2PP.setNumerus("sing");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2psK2PP);
                    }

                    Verb a1ppK2PP = new Verb();
                    a1ppK2PP.setLexem(stamm + "en");
                    a1ppK2PP.setInfinitiv(infi);
                    try {
                        a1ppK2PP.setWortunterart("rege");
                        a1ppK2PP.setModus("kon2");
                        a1ppK2PP.setTempus("pepl");
                        a1ppK2PP.setAltus("unde");
                        a1ppK2PP.setPerson("1&3");
                        a1ppK2PP.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a1ppK2PP);
                    }

                    Verb a2ppK2PP = new Verb();
                    a2ppK2PP.setLexem(stamm + "et");
                    a2ppK2PP.setInfinitiv(infi);
                    try {
                        a2ppK2PP.setWortunterart("rege");
                        a2ppK2PP.setModus("kon2");
                        a2ppK2PP.setTempus("pepl");
                        a2ppK2PP.setAltus("unde");
                        a2ppK2PP.setPerson("2p");
                        a2ppK2PP.setNumerus("plur");
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a2ppK2PP);
                    }


                } else {
                    wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
                    String modukey = (String) moduseVerbComboBox.getSelectedItem();
                    modukey = modukey.substring(0, modukey.indexOf(" ::"));
                    String tempkey = (String) tempuseVerbComboBox.getSelectedItem();
                    tempkey = tempkey.substring(0, tempkey.indexOf(" ::"));
                    String altukey = (String) altuseVerbComboBox.getSelectedItem();
                    altukey = altukey.substring(0, altukey.indexOf(" ::"));
                    String perskey = (String) personenVerbComboBox.getSelectedItem();
                    perskey = perskey.substring(0, perskey.indexOf(" ::"));
                    String numekey = (String) numeruseVerbComboBox.getSelectedItem();
                    numekey = numekey.substring(0, numekey.indexOf(" ::"));
                    String genukey = "mwu";
                    if(WAtt.getLanguage()==1){
                        genukey = (String) genuseVerbComboBox.getSelectedItem();
                        genukey = genukey.substring(0, genukey.indexOf(" ::"));
                    }
                    try {
                        if (hilfsVerb != null) {
                            a.setHilfsverb(hilfsVerb.getId());
                        }
                        a.setWortunterart(wortkey);
                        a.setModus(modukey);
                        a.setTempus(tempkey);
                        a.setAltus(altukey);
                        a.setPerson(perskey);
                        a.setNumerus(numekey);
//                        if(WAtt.getLanguage()==1){
                            a.setGenus(genukey);
//                        }
                    } catch (UnknownKeyException ex) {
                        Logger.getLogger(CreateWortView.class.getName()).log(Level.SEVERE, null, ex);
                        ok = false;
                    }
                    if (ok) {
                        AnalyseApp.getApplication().getAnna().learnWord(a);
                        auswahlCheckBox.setSelected(false);
                    }
                }
                updateVerbTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("wortPanel")) {
        }
    }

    @Action
    public void removeWort() {
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        System.out.println("na dann versuchen wir mal zu löschen: ");
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adjektivPanel")) {
            if (adjektivTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) adjektivTable.getValueAt(adjektivTable.getSelectedRow(), 0));                
                Adjektiv a = em.find(Adjektiv.class, key);
                System.out.println("Wird gelöscht " + a.getLexem() + " in der Grundform: " + a.getGrundform());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateAdjektivTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adverbPanel")) {
            if (adverbTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) adverbTable.getValueAt(adverbTable.getSelectedRow(), 0));                
                Adverb a = em.find(Adverb.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateAdverbTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("artikelPanel")) {
            if (artikelTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) artikelTable.getValueAt(artikelTable.getSelectedRow(), 0));                
                Artikel a = em.find(Artikel.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateArtikelTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("interjektionPanel")) {
            if (interjektionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) interjektionTable.getValueAt(interjektionTable.getSelectedRow(), 0));               
                Interjektion a = em.find(Interjektion.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateInterjektionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("konjunktionPanel")) {
            if (konjunktionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) konjunktionTable.getValueAt(konjunktionTable.getSelectedRow(), 0));      
                Konjunktion a = em.find(Konjunktion.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateKonjunktionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("nomenPanel")) {
            if (nomenTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) nomenTable.getValueAt(nomenTable.getSelectedRow(), 0));                
                Nomen a = em.find(Nomen.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateNomenTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("numeralPanel")) {
            if (numeralTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) numeralTable.getValueAt(numeralTable.getSelectedRow(), 0));                
                Numeral a = em.find(Numeral.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateNumeralTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("präpositionPanel")) {
            if (präpositionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) präpositionTable.getValueAt(präpositionTable.getSelectedRow(), 0));                
                Präposition a = em.find(Präposition.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updatePräpositionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("pronomenPanel")) {
            if (pronomenTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) pronomenTable.getValueAt(pronomenTable.getSelectedRow(), 0));                
                Pronomen a = em.find(Pronomen.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updatePronomenTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("postpositionPanel")) {
            if (postpositionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) postpositionTable.getValueAt(postpositionTable.getSelectedRow(), 0));                
                Postposition a = em.find(Postposition.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updatePostpositionTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("verbPanel")) {
            if (verbTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) verbTable.getValueAt(verbTable.getSelectedRow(), 0));                
                Verb a = em.find(Verb.class, key);
                System.out.println("Wird gelöscht " + a.getLexem()+" sprache "+a.getLanguage());
                AnalyseApp.getApplication().getAnna().forgettWord(a);
                updateVerbTable();
            }
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("wortPanel")) {
//            if(wortTable.getSelectedRow()>=0){
//                int key = Integer.parseInt((String) wortTable.getValueAt(wortTable.getSelectedRow(), 0));              
//                Wort a = em.find(Wort.class, key);
//                System.out.println("Wird gelöscht "+a.getLexem());
//                AnalyseApp.getApplication().getAnna().forgettWord(a);
//                updateWortTable();
//            }
        }
        auswahlCheckBox.setSelected(false);
    }

    @Action
    public void updateTable() {
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adjektivPanel")) {
            updateAdjektivTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("adverbPanel")) {
            updateAdverbTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("artikelPanel")) {
            updateArtikelTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("interjektionPanel")) {
            updateInterjektionTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("konjunktionPanel")) {
            updateKonjunktionTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("nomenPanel")) {
            updateNomenTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("numeralPanel")) {
            updateNumeralTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("präpositionPanel")) {
            updatePräpositionTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("postpositionPanel")) {
            updatePostpositionTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("pronomenPanel")) {
            updatePronomenTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("verbPanel")) {
            updateVerbTable();
        }
        if ((jTabbedPane.getSelectedComponent().getName()).equals("wortPanel")) {
//                updateWortTable();
        }
    }

    @Action
    public void erweitereRegelmaessigeVerben() {
        if(wortunterartenVerbComboBox.getSelectedItem()!=null){
            String wortkey = (String) wortunterartenVerbComboBox.getSelectedItem();
            if (wortkey.startsWith("rege")) {
                lexemVerbTextField.setEnabled(false);
                moduseVerbComboBox.setEnabled(false);
                tempuseVerbComboBox.setEnabled(false);
                altuseVerbComboBox.setEnabled(false);
                personenVerbComboBox.setEnabled(false);
                numeruseVerbComboBox.setEnabled(false);
            } else {
                lexemVerbTextField.setEnabled(true);
                moduseVerbComboBox.setEnabled(true);
                tempuseVerbComboBox.setEnabled(true);
                altuseVerbComboBox.setEnabled(true);
                personenVerbComboBox.setEnabled(true);
                numeruseVerbComboBox.setEnabled(true);
            }
        }
    }

    @Action
    public void updateAdjektivTable() {
        String headers[] = {"ID", "Lexem","Grundform","Flexionsart","Steigerung", "Kasus", "Genus", "Numerus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection adjektive = em.createQuery("SELECT a FROM Adjektiv a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[adjektive.size()][8];
        if (adjektive.size() > 0) {
            int r = 0;
            for (Iterator i = adjektive.iterator(); i.hasNext();) {
                Adjektiv a = (Adjektiv) i.next();
                boolean suche = false;
                if(!suchenAdjektivTextField.getText().equals("")) suche = true;               
                if(!suche || a.getLexem().startsWith(suchenAdjektivTextField.getText())){                  
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getGrundform();
                    rows[r][3] = a.getWortunterart();
                    rows[r][4] = a.getSteigerung();
                    rows[r][5] = a.getKasus();
                    rows[r][6] = a.getGenus();
                    rows[r][7] = a.getNumerus();
                    r++;
                }
            }
        }
        adjektivTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        adjektivTable.getColumnModel().getColumn(0).setMaxWidth(30);
        adjektivTable.setColumnSelectionAllowed(false);
    }

    @Action
    public void updateAdverbTable() {
        String headers[] = {"ID", "Lexem", "Wortunterart","Umstand"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection adverbe = em.createQuery("SELECT a FROM Adverb a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[adverbe.size()][4];
        if (adverbe.size() > 0) {
            int r = 0;
            for (Iterator i = adverbe.iterator(); i.hasNext();) {
                Adverb a = (Adverb) i.next();
                boolean suche = false;
                if(!suchenAdverbTextField.getText().equals("")) suche = true;               
                if(!suche || a.getLexem().startsWith(suchenAdverbTextField.getText())){            
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getWortunterart();
                    rows[r][3] = a.getUmstand();
                    r++;
                }
            }
        }
        adverbTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        adverbTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateArtikelTable() {
        String headers[] = {"ID", "Lexem", "Wortunterart", "Genus", "Kasus", "Person", "Numerus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection artikele = em.createQuery("SELECT a FROM Artikel a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[artikele.size()][7];
        if (artikele.size() > 0) {
            int r = 0;
            for (Iterator i = artikele.iterator(); i.hasNext();) {
                Artikel a = (Artikel) i.next();
                boolean suche = false;
                if(!suchenArtikelTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenArtikelTextField.getText())){                    
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getWortunterart();
                    rows[r][3] = a.getGenus();
                    rows[r][4] = a.getKasus();
                    rows[r][5] = a.getPerson();
                    rows[r][6] = a.getNumerus();
                    r++;
                }
            }
        }
        artikelTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        artikelTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateInterjektionTable() {
        String headers[] = {"ID", "Lexem", "Ausdruck"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection interjektionen = em.createQuery("SELECT a FROM Interjektion a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[interjektionen.size()][3];
        if (interjektionen.size() > 0) {
            int r = 0;
            for (Iterator i = interjektionen.iterator(); i.hasNext();) {
                Interjektion a = (Interjektion) i.next();
                boolean suche = false;
                if(!suchenInterjektionTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenInterjektionTextField.getText())){
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getAusdruck();
                    r++;
                }
            }
        }
        interjektionTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        interjektionTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateKonjunktionTable() {
        String headers[] = {"ID", "Lexem","Anderer Teil ID", "Wortunterart"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection konjunktionen = em.createQuery("SELECT a FROM Konjunktion a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[konjunktionen.size()][4];
        if (konjunktionen.size() > 0) {
            int r = 0;
            for (Iterator i = konjunktionen.iterator(); i.hasNext();) {
                Konjunktion a = (Konjunktion) i.next();
                Konjunktion b = null;
                boolean suche = false;
                if(!suchenKonjunktionTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenKonjunktionTextField.getText())){
                    if (a.getAndererTeilID() > 0) {
                        b = em.find(Konjunktion.class, a.getAndererTeilID());
                    }
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    if(b!=null) rows[r][2] = b.getLexem();
                    rows[r][3] = a.getWortunterart();
                    r++;
                }
            }
        }
        konjunktionTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        konjunktionTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateNomenTable() {
        String headers[] = {"ID", "Lexem", "Belebt","Wortunterart", "Genus", "Kasus", "Numerus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection nomenen = em.createQuery("SELECT a FROM Nomen a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[nomenen.size()][7];
        if (nomenen.size() > 0) {
            int r = 0;
            for (Iterator i = nomenen.iterator(); i.hasNext();) {
                Nomen a = (Nomen) i.next();
                boolean suche = false;
                if(!suchenNomenTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenNomenTextField.getText())){
                    String belebt ="";
                    if(a.isBelebt()) belebt = "X";
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = belebt;
                    rows[r][3] = a.getWortunterart();
                    rows[r][4] = a.getGenus();
                    rows[r][5] = a.getKasus();
                    rows[r][6] = a.getNumerus();
                    r++;
                }
            }
        }
        nomenTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        nomenTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateNumeralTable() {
        String headers[] = {"ID", "Lexem", "Wert", "Wortunterart", "Genus", "Kasus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection numerale = em.createQuery("SELECT a FROM Numeral a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[numerale.size()][6];
        if (numerale.size() > 0) {
            System.out.println("Länge: " + numerale.size());
            int r = 0;
            for (Iterator i = numerale.iterator(); i.hasNext();) {
                Numeral a = (Numeral) i.next();
                boolean suche = false;
                if(!suchenNumeralTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenNumeralTextField.getText())){
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = String.valueOf(a.getWert());
                    rows[r][3] = a.getWortunterart();
                    rows[r][4] = a.getGenus();
                    rows[r][5] = a.getKasus();
                    r++;
                }
            }
        }
        numeralTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        numeralTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updatePräpositionTable() {
        String headers[] = {"ID", "Lexem", "Kasus","Umstand"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection Präpositione = em.createQuery("SELECT a FROM Präposition a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[Präpositione.size()][4];
        if (Präpositione.size() > 0) {
            System.out.println("Länge: " + Präpositione.size());
            int r = 0;
            for (Iterator i = Präpositione.iterator(); i.hasNext();) {
                Präposition a = (Präposition) i.next();
                boolean suche = false;
                if(!suchenPräpositionTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenPräpositionTextField.getText())){
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getKasus();
                    rows[r][3] = a.getUmstand();
                    r++;
                }
            }
        }
        präpositionTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        präpositionTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updatePronomenTable() {
        String headers[] = {"ID", "Lexem", "Wortunterart", "Genus", "Kasus", "Person", "Numerus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection pronomenen = em.createQuery("SELECT a FROM Pronomen a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[pronomenen.size()][7];
        if (pronomenen.size() > 0) {
            int r = 0;
            for (Iterator i = pronomenen.iterator(); i.hasNext();) {
                Pronomen a = (Pronomen) i.next();
                boolean suche = false;
                if(!suchenPronomenTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenPronomenTextField.getText())){
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getWortunterart();
                    rows[r][3] = a.getGenus();
                    rows[r][4] = a.getKasus();
                    rows[r][5] = a.getPerson();
                    rows[r][6] = a.getNumerus();
                    r++;
                }
            }
        }
        pronomenTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        pronomenTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updatePostpositionTable() {
        String headers[] = {"ID", "Lexem", "Kasus","Umstand"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection Postpositione = em.createQuery("SELECT a FROM Postposition a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[Postpositione.size()][4];
        if (Postpositione.size() > 0) {
            System.out.println("Länge: " + Postpositione.size());
            int r = 0;
            for (Iterator i = Postpositione.iterator(); i.hasNext();) {
                Postposition a = (Postposition) i.next();
                boolean suche = false;
                if(!suchenPostpositionTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenPostpositionTextField.getText())){
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getKasus();
                    rows[r][3] = a.getUmstand();
                    r++;
                }
            }
        }
        postpositionTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        postpositionTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void updateVerbTable() {
        String headers[] = {"ID", "Lexem", "Infinitv", "Wortunterart", "Hilfsverb"," Genus", "Modus", "Tempus", "Altus", "Person", "Numerus"};
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        Collection verben = em.createQuery("SELECT a FROM Verb a ORDER BY a.lexem").getResultList();
        String rows[][] = new String[verben.size()][11];
        if (verben.size() > 0) {
            int r = 0;
            for (Iterator i = verben.iterator(); i.hasNext(); ) {
                Verb a = (Verb) i.next();
                boolean suche = false;
                if(!suchenVerbTextField.getText().equals("")) suche = true;
                if(!suche || a.getLexem().startsWith(suchenVerbTextField.getText())){
                    Verb b = null;
                    if (a.getHilfsverb() > 0) {
                        b = em.find(Verb.class, a.getHilfsverb());
                    }
                    rows[r][0] = String.valueOf(a.getId());
                    rows[r][1] = a.getLexem();
                    rows[r][2] = a.getInfinitiv();
                    rows[r][3] = a.getWortunterart();
                    if (b != null) rows[r][4] = b.getLexem();
                    if (WAtt.getLanguage()==1) rows[r][5] = a.getGenus();
                    rows[r][6] = a.getModus();
                    rows[r][7] = a.getTempus();
                    rows[r][8] = a.getAltus();
                    rows[r][9] = a.getPerson();
                    rows[r][10] = a.getNumerus();
                    r++;
                }
            }
        }
        verbTable.setModel(new javax.swing.table.DefaultTableModel(rows, headers));
        verbTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Action
    public void hilfsVerbWahl() {
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (verbTable.getSelectedRow() >= 0) {
            int key = Integer.parseInt((String) verbTable.getValueAt(verbTable.getSelectedRow(), 0));
            Verb a = em.find(Verb.class, key);
            hilfsVerb = a;
            if (a != null) {
                hilfsVerbTextField.setText(a.getLexem());
                infiHilfsVerbTextField.setText(a.getInfinitiv());
            }
        } else {
            hilfsVerb = null;
        }
    }

    @Action
    public void hilfsVerbSuche() {
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        hilfsVerbComboBox.removeAllItems();
        String modukey = (String) moduseVerbComboBox.getSelectedItem();
        modukey = modukey.substring(0, modukey.indexOf(" ::"));
        String tempkey = (String) tempuseVerbComboBox.getSelectedItem();
        tempkey = tempkey.substring(0, tempkey.indexOf(" ::"));
        String altukey = (String) altuseVerbComboBox.getSelectedItem();
        altukey = altukey.substring(0, altukey.indexOf(" ::"));
        String perskey = (String) personenVerbComboBox.getSelectedItem();
        perskey = perskey.substring(0, perskey.indexOf(" ::"));
        String numekey = (String) numeruseVerbComboBox.getSelectedItem();
        numekey = numekey.substring(0, numekey.indexOf(" ::"));
        List<Verb> verbe = em.createNamedQuery("Verb.findByInfinitiv").setParameter("infinitiv", infiHilfsVerbTextField.getText()).getResultList();
        if (verbe.size() == 1) {
            hilfsVerb = (Verb) verbe.get(0);
            hilfsVerbTextField.setText(hilfsVerb.getLexem());
        } else {
            for (Verb v : verbe) {
                if (v.getModusAA().equals(modukey) && v.getTempusAA().
                        equals(tempkey) && v.getAltusAA().equals(altukey) &&
                        v.getPersonAA().equals(perskey) && v.getNumerusAA().equals(numekey)) {
                    hilfsVerbComboBox.addItem(v);
                }
            }
        }
    }

    @Action
    public void hilfsVerbSetzen() {
        System.out.println("Die Anzahl von Elementen in der HilfverbenComboBox: " + hilfsVerbComboBox.getItemCount());
        if (hilfsVerbComboBox.getItemCount() > 0) {
            hilfsVerb = (Verb) hilfsVerbComboBox.getSelectedItem();
            hilfsVerbTextField.setText(hilfsVerb.getLexem());
        }
    }

    @Action
    public void hilfsVerbEntfernen() {
        if (verbTemp != null) {
            verbTemp.setHilfsverb(0);
            hilfsVerb = null;
            saveWord();
        }
    }

    @Action
    public void anderenTeilDesKonjunktivsSetzten(){
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (konjunktionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) konjunktionTable.getValueAt(konjunktionTable.getSelectedRow(), 0));
                Konjunktion a = em.find(Konjunktion.class, key);
                andereKonjunktion = a;
                if (a != null) {
                    andreLexemKonjunktionTextField.setText(a.getLexem());
                }
            } else {
                andereKonjunktion = null;
        }
    }

    @Action
    public void andererTeilEntfernen() {
        if (andereKonjunktion != null) {
            konjunktionTemp.setAndererTeilID(0);
            andereKonjunktion = null;
            saveWord();
        }
    }

    @Action
    public void auswahlEditieren() {
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (auswahlCheckBox.isSelected()) {
            if ((jTabbedPane.getSelectedComponent().getName()).equals("adjektivPanel")) {
                if (adjektivTemp != null) {
                    if (adjektivTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) adjektivTable.getValueAt(adjektivTable.getSelectedRow(), 0));
                        Adjektiv a = em.find(Adjektiv.class, key);
                        if (a != null) {
                            adjektivTemp = a;
                            lexemAdjektivTextField.setText(a.getLexem());
                            infiAdjektivTextField.setText(a.getGrundform());
                            wortunterartenAdjektivComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            steigerungenAdjektivComboBox.setSelectedItem(a.getSteigerungAA().getKey() + " :: " + a.getSteigerungAA().getName());
                            kasuseAdjektivComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            genuseAdjektivComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                            numeruseAdjektivComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("adverbPanel")) {
                if (adverbTemp != null) {
                    if (adverbTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) adverbTable.getValueAt(adverbTable.getSelectedRow(), 0));
                        
                        Adverb a = em.find(Adverb.class, key);
                        if (a != null) {
                            adverbTemp = a;
                            lexemAdverbTextField.setText(a.getLexem());
                            wortunterartenAdverbComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            umstaendeAdverbComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("artikelPanel")) {
                if (artikelTemp != null) {
                    if (artikelTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) artikelTable.getValueAt(artikelTable.getSelectedRow(), 0));
                        Artikel a = em.find(Artikel.class, key);
                        if (a != null) {
                            artikelTemp = a;
                            lexemArtikelTextField.setText(a.getLexem());
                            wortunterartenArtikelComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            genuseArtikelComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                            kasuseArtikelComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            personenArtikelComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                            numeruseArtikelComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("interjektionPanel")) {
                if (interjektionTemp != null) {
                    if (interjektionTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) interjektionTable.getValueAt(interjektionTable.getSelectedRow(), 0));
                        Interjektion a = em.find(Interjektion.class, key);
                        if (a != null) {
                            interjektionTemp = a;
                            lexemInterjektionTextField.setText(a.getLexem());
                            ausdruckInterjektionTextField.setText(a.getAusdruck());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("konjunktionPanel")) {
                if (konjunktionTemp != null&&!aTeilToggleButton.isSelected()) {
                    if (konjunktionTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) konjunktionTable.getValueAt(konjunktionTable.getSelectedRow(), 0));
                        Konjunktion a = em.find(Konjunktion.class, key);
                        if (a != null) {
                            konjunktionTemp = a;
                            lexemKonjunktionTextField.setText(a.getLexem());
                            wortunterartenKonjunktionComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("nomenPanel")) {
                if (nomenTemp != null) {
                    if (nomenTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) nomenTable.getValueAt(nomenTable.getSelectedRow(), 0));                        
                        Nomen a = em.find(Nomen.class, key);
                        if (a != null) {
                            nomenTemp = a;
                            lexemNomenTextField.setText(a.getLexem());
                            wortunterartenNomenComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            genuseNomenComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                            kasuseNomenComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            numeruseNomenComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                            belebtNomenCheckBox.setSelected(a.isBelebt());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("numeralPanel")) {
                if (numeralTemp != null) {
                    if (numeralTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) numeralTable.getValueAt(numeralTable.getSelectedRow(), 0));
                        Numeral a = em.find(Numeral.class, key);
                        if (a != null) {
                            numeralTemp = a;
                            lexemNumeralTextField.setText(a.getLexem());
                            wertNumeralTextField.setText(String.valueOf(a.getWert()));
                            wortunterartenNumeralComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            genuseNumeralComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                            kasuseNumeralComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("präpositionPanel")) {
                if (präpositionTemp != null) {
                    if (präpositionTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) präpositionTable.getValueAt(präpositionTable.getSelectedRow(), 0));
                        
                        Präposition a = em.find(Präposition.class, key);
                        if (a != null) {
                            präpositionTemp = a;
                            lexemPräpositionTextField.setText(a.getLexem());
                            kasusePräpositionComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            umstaendePräpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("pronomenPanel")) {
                if (pronomenTemp != null) {
                    if (pronomenTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) pronomenTable.getValueAt(pronomenTable.getSelectedRow(), 0));
                        Pronomen a = em.find(Pronomen.class, key);
                        if (a != null) {
                            pronomenTemp = a;
                            lexemPronomenTextField.setText(a.getLexem());
                            wortunterartenPronomenComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            genusePronomenComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                            kasusePronomenComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            personenPronomenComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                            numerusePronomenComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("postpositionPanel")) {
                if (postpositionTemp != null) {
                    if (postpositionTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) postpositionTable.getValueAt(postpositionTable.getSelectedRow(), 0));
                        
                        Postposition a = em.find(Postposition.class, key);
                        if (a != null) {
                            postpositionTemp = a;
                            lexemPostpositionTextField.setText(a.getLexem());
                            kasusePostpositionComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                            umstaendePostpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("verbPanel")) {
                if (verbTemp != null && !hilfsVerbToggleButton.isSelected()) {
                    if (verbTable.getSelectedRow() >= 0) {
                        int key = Integer.parseInt((String) verbTable.getValueAt(verbTable.getSelectedRow(), 0));
                        Verb a = em.find(Verb.class, key);
                        if (a != null) {
                            verbTemp = a;
                            lexemVerbTextField.setText(a.getLexem());
                            infiVerbTextField.setText(a.getInfinitiv());
                            wortunterartenVerbComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                            moduseVerbComboBox.setSelectedItem(a.getModusAA().getKey() + " :: " + a.getModusAA().getName());
                            tempuseVerbComboBox.setSelectedItem(a.getTempusAA().getKey() + " :: " + a.getTempusAA().getName());
                            altuseVerbComboBox.setSelectedItem(a.getAltusAA().getKey() + " :: " + a.getAltusAA().getName());
                            personenVerbComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                            numeruseVerbComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                            if(WAtt.getLanguage()==1) genuseVerbComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                        }
                    }
                }
            }
            if ((jTabbedPane.getSelectedComponent().getName()).equals("wortPanel")) {
            }
        } else {
            adjektivTemp = null;
            adverbTemp = null;
            artikelTemp = null;
            interjektionTemp = null;
            konjunktionTemp = null;
            nomenTemp = null;
            präpositionTemp = null;
            pronomenTemp = null;
            verbTemp = null;
        }
    }
    public void redrowLabels(){
        jTabbedPane.setTitleAt(0, Adjektiv.getNationalClassNeme());
        jTabbedPane.setTitleAt(1, Adverb.getNationalClassNeme());
        jTabbedPane.setTitleAt(2, Artikel.getNationalClassNeme());
        jTabbedPane.setTitleAt(3, Interjektion.getNationalClassNeme());
        jTabbedPane.setTitleAt(4, Konjunktion.getNationalClassNeme());
        jTabbedPane.setTitleAt(5, Nomen.getNationalClassNeme());
        jTabbedPane.setTitleAt(6, Numeral.getNationalClassNeme());
        jTabbedPane.setTitleAt(7, Präposition.getNationalClassNeme());
        jTabbedPane.setTitleAt(8, Pronomen.getNationalClassNeme());
        jTabbedPane.setTitleAt(9, Postposition.getNationalClassNeme());
        jTabbedPane.setTitleAt(10, Verb.getNationalClassNeme());
    }

    @Action
    public void switchLanguage(){
        short ger = 0;
        short pol = 1;
        if(deutschRadioButton.isSelected()) WAtt.setLanguage(ger);
        if(polnischRadioButton.isSelected()) WAtt.setLanguage(pol);
        AnalyseApp.getApplication().getAnna().changeConnection();
        this.em = null;
        this.em = AnalyseApp.getApplication().getAnna().getEM();
        initComboBoxen();
        redrowLabels();
    }

    @Action
    public void closeAboutBox() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languageButtonGroup = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        adjektivPanel = new javax.swing.JPanel();
        lexemAdjektivTextField = new javax.swing.JTextField();
        infiAdjektivTextField = new javax.swing.JTextField();
        suchenAdjektivTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        steigerungenAdjektivComboBox = new javax.swing.JComboBox();
        genuseAdjektivComboBox = new javax.swing.JComboBox();
        numeruseAdjektivComboBox = new javax.swing.JComboBox();
        kasuseAdjektivComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        adjektivTable = new javax.swing.JTable();
        wortunterartenAdjektivComboBox = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        adverbPanel = new javax.swing.JPanel();
        lexemAdverbTextField = new javax.swing.JTextField();
        suchenAdverbTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        wortunterartenAdverbComboBox = new javax.swing.JComboBox();
        umstaendeAdverbComboBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        adverbTable = new javax.swing.JTable();
        artikelPanel = new javax.swing.JPanel();
        lexemArtikelTextField = new javax.swing.JTextField();
        suchenArtikelTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        wortunterartenArtikelComboBox = new javax.swing.JComboBox();
        personenArtikelComboBox = new javax.swing.JComboBox();
        numeruseArtikelComboBox = new javax.swing.JComboBox();
        genuseArtikelComboBox = new javax.swing.JComboBox();
        kasuseArtikelComboBox = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        artikelTable = new javax.swing.JTable();
        interjektionPanel = new javax.swing.JPanel();
        lexemInterjektionTextField = new javax.swing.JTextField();
        ausdruckInterjektionTextField = new javax.swing.JTextField();
        suchenInterjektionTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        interjektionTable = new javax.swing.JTable();
        konjunktionPanel = new javax.swing.JPanel();
        aTeilToggleButton = new javax.swing.JToggleButton();
        resetATeilButton = new javax.swing.JButton();
        lexemKonjunktionTextField = new javax.swing.JTextField();
        andreLexemKonjunktionTextField = new javax.swing.JTextField();
        suchenKonjunktionTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        wortunterartenKonjunktionComboBox = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        konjunktionTable = new javax.swing.JTable();
        nomenPanel = new javax.swing.JPanel();
        lexemNomenTextField = new javax.swing.JTextField();
        suchenNomenTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        wortunterartenNomenComboBox = new javax.swing.JComboBox();
        genuseNomenComboBox = new javax.swing.JComboBox();
        numeruseNomenComboBox = new javax.swing.JComboBox();
        kasuseNomenComboBox = new javax.swing.JComboBox();
        belebtNomenCheckBox = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        nomenTable = new javax.swing.JTable();
        numeralPanel = new javax.swing.JPanel();
        lexemNumeralTextField = new javax.swing.JTextField();
        wertNumeralTextField = new javax.swing.JTextField();
        suchenNumeralTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        wortunterartenNumeralComboBox = new javax.swing.JComboBox();
        genuseNumeralComboBox = new javax.swing.JComboBox();
        kasuseNumeralComboBox = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        numeralTable = new javax.swing.JTable();
        präpositionPanel = new javax.swing.JPanel();
        lexemPräpositionTextField = new javax.swing.JTextField();
        suchenPräpositionTextField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        kasusePräpositionComboBox = new javax.swing.JComboBox();
        umstaendePräpositionComboBox = new javax.swing.JComboBox();
        jScrollPane8 = new javax.swing.JScrollPane();
        präpositionTable = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        pronomenPanel = new javax.swing.JPanel();
        lexemPronomenTextField = new javax.swing.JTextField();
        suchenPronomenTextField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        wortunterartenPronomenComboBox = new javax.swing.JComboBox();
        genusePronomenComboBox = new javax.swing.JComboBox();
        personenPronomenComboBox = new javax.swing.JComboBox();
        numerusePronomenComboBox = new javax.swing.JComboBox();
        kasusePronomenComboBox = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        pronomenTable = new javax.swing.JTable();
        postpositionPanel = new javax.swing.JPanel();
        lexemPostpositionTextField = new javax.swing.JTextField();
        suchenPostpositionTextField = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        kasusePostpositionComboBox = new javax.swing.JComboBox();
        umstaendePostpositionComboBox = new javax.swing.JComboBox();
        jScrollPane11 = new javax.swing.JScrollPane();
        postpositionTable = new javax.swing.JTable();
        verbPanel = new javax.swing.JPanel();
        hilfsVerbToggleButton = new javax.swing.JToggleButton();
        resetHilfsVerbButton = new javax.swing.JButton();
        suchenVerbButton = new javax.swing.JButton();
        lexemVerbTextField = new javax.swing.JTextField();
        infiVerbTextField = new javax.swing.JTextField();
        hilfsVerbTextField = new javax.swing.JTextField();
        infiHilfsVerbTextField = new javax.swing.JTextField();
        suchenVerbTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        wortunterartenVerbComboBox = new javax.swing.JComboBox();
        moduseVerbComboBox = new javax.swing.JComboBox();
        tempuseVerbComboBox = new javax.swing.JComboBox();
        altuseVerbComboBox = new javax.swing.JComboBox();
        personenVerbComboBox = new javax.swing.JComboBox();
        numeruseVerbComboBox = new javax.swing.JComboBox();
        hilfsVerbComboBox = new javax.swing.JComboBox();
        genuseVerbComboBox = new javax.swing.JComboBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        verbTable = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        speichernButton = new javax.swing.JButton();
        entferenenButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        auswahlCheckBox = new javax.swing.JCheckBox();
        deutschRadioButton = new javax.swing.JRadioButton();
        polnischRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(annalyse.gui.AnalyseApp.class).getContext().getResourceMap(CreateWortView.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);

        jTabbedPane.setMaximumSize(new java.awt.Dimension(123, 256));
        jTabbedPane.setName("jTabbedPane"); // NOI18N
        jTabbedPane.setPreferredSize(new java.awt.Dimension(123, 256));

        adjektivPanel.setName("adjektivPanel"); // NOI18N

        lexemAdjektivTextField.setText(resourceMap.getString("lexemAdjektivTextField.text")); // NOI18N
        lexemAdjektivTextField.setName("lexemAdjektivTextField"); // NOI18N
        lexemAdjektivTextField.setNextFocusableComponent(infiAdjektivTextField);

        infiAdjektivTextField.setText(resourceMap.getString("infiAdjektivTextField.text")); // NOI18N
        infiAdjektivTextField.setName("infiAdjektivTextField"); // NOI18N
        infiAdjektivTextField.setNextFocusableComponent(wortunterartenAdjektivComboBox);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(annalyse.gui.AnalyseApp.class).getContext().getActionMap(CreateWortView.class, this);
        suchenAdjektivTextField.setAction(actionMap.get("adjektivSuchen")); // NOI18N
        suchenAdjektivTextField.setName("suchenAdjektivTextField"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel59.setText(resourceMap.getString("jLabel59.text")); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N

        steigerungenAdjektivComboBox.setName("steigerungenAdjektivComboBox"); // NOI18N
        steigerungenAdjektivComboBox.setNextFocusableComponent(speichernButton);

        genuseAdjektivComboBox.setName("genuseAdjektivComboBox"); // NOI18N
        genuseAdjektivComboBox.setNextFocusableComponent(kasuseAdjektivComboBox);

        numeruseAdjektivComboBox.setName("numeruseAdjektivComboBox"); // NOI18N
        numeruseAdjektivComboBox.setNextFocusableComponent(steigerungenAdjektivComboBox);

        kasuseAdjektivComboBox.setName("kasuseAdjektivComboBox"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        adjektivTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Infinitiv", "Infinitiv", "Modus", "Kasus", "Person", "Person", "Numerus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        adjektivTable.setColumnSelectionAllowed(true);
        adjektivTable.setName("adjektivTable"); // NOI18N
        adjektivTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                adjektivTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(adjektivTable);
        adjektivTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        adjektivTable.getColumnModel().getColumn(0).setResizable(false);
        adjektivTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        adjektivTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        adjektivTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        adjektivTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N
        adjektivTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title3")); // NOI18N
        adjektivTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title4")); // NOI18N
        adjektivTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        adjektivTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title8")); // NOI18N
        adjektivTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title7")); // NOI18N

        wortunterartenAdjektivComboBox.setName("wortunterartenAdjektivComboBox"); // NOI18N
        wortunterartenAdjektivComboBox.setNextFocusableComponent(genuseAdjektivComboBox);

        jLabel65.setText(resourceMap.getString("jLabel65.text")); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N

        org.jdesktop.layout.GroupLayout adjektivPanelLayout = new org.jdesktop.layout.GroupLayout(adjektivPanel);
        adjektivPanel.setLayout(adjektivPanelLayout);
        adjektivPanelLayout.setHorizontalGroup(
            adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, adjektivPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel7)
                    .add(jLabel4)
                    .add(jLabel65)
                    .add(jLabel6)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, adjektivPanelLayout.createSequentialGroup()
                        .add(jLabel59)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenAdjektivTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(284, 284, 284))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, adjektivPanelLayout.createSequentialGroup()
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, numeruseAdjektivComboBox, 0, 177, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, steigerungenAdjektivComboBox, 0, 177, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, kasuseAdjektivComboBox, 0, 177, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, genuseAdjektivComboBox, 0, 177, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, wortunterartenAdjektivComboBox, 0, 177, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, infiAdjektivTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .add(lexemAdjektivTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 594, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        adjektivPanelLayout.setVerticalGroup(
            adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(adjektivPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(adjektivPanelLayout.createSequentialGroup()
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemAdjektivTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(infiAdjektivTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenAdjektivComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel65))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genuseAdjektivComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasuseAdjektivComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numeruseAdjektivComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(steigerungenAdjektivComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3)))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adjektivPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(suchenAdjektivTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel59)))
        );

        jTabbedPane.addTab(Adjektiv.getNationalClassNeme(), adjektivPanel);

        adverbPanel.setName("adverbPanel"); // NOI18N
        adverbPanel.setRequestFocusEnabled(false);

        lexemAdverbTextField.setName("lexemAdverbTextField"); // NOI18N
        lexemAdverbTextField.setNextFocusableComponent(wortunterartenAdverbComboBox);

        suchenAdverbTextField.setName("suchenAdverbTextField"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel49.setText(resourceMap.getString("jLabel49.text")); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N

        jLabel58.setText(resourceMap.getString("jLabel58.text")); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N

        wortunterartenAdverbComboBox.setName("wortunterartenAdverbComboBox"); // NOI18N
        wortunterartenAdverbComboBox.setNextFocusableComponent(speichernButton);

        umstaendeAdverbComboBox.setName("umstaendeAdverbComboBox"); // NOI18N
        umstaendeAdverbComboBox.setNextFocusableComponent(speichernButton);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        adverbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lexem", "Umstand"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        adverbTable.setName("adverbTable"); // NOI18N
        adverbTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                adverbTablePropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(adverbTable);
        adverbTable.getColumnModel().getColumn(0).setResizable(false);
        adverbTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adverbTable.columnModel.title0")); // NOI18N
        adverbTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adverbTable.columnModel.title1")); // NOI18N
        adverbTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("adverbTable.columnModel.title2")); // NOI18N

        org.jdesktop.layout.GroupLayout adverbPanelLayout = new org.jdesktop.layout.GroupLayout(adverbPanel);
        adverbPanel.setLayout(adverbPanelLayout);
        adverbPanelLayout.setHorizontalGroup(
            adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, adverbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel8)
                    .add(jLabel10)
                    .add(jLabel49))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, umstaendeAdverbComboBox, 0, 224, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, wortunterartenAdverbComboBox, 0, 224, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lexemAdverbTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(adverbPanelLayout.createSequentialGroup()
                        .add(jLabel58)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenAdverbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 573, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        adverbPanelLayout.setVerticalGroup(
            adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(adverbPanelLayout.createSequentialGroup()
                .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(adverbPanelLayout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(suchenAdverbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel58)))
                    .add(adverbPanelLayout.createSequentialGroup()
                        .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemAdverbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel8))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenAdverbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel10))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adverbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(umstaendeAdverbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel49))))
                .addContainerGap())
        );

        jTabbedPane.addTab(Adverb.getNationalClassNeme(), adverbPanel);

        artikelPanel.setName("artikelPanel"); // NOI18N

        lexemArtikelTextField.setName("lexemArtikelTextField"); // NOI18N
        lexemArtikelTextField.setNextFocusableComponent(wortunterartenArtikelComboBox);

        suchenArtikelTextField.setName("suchenArtikelTextField"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel47.setText(resourceMap.getString("jLabel47.text")); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N

        jLabel57.setText(resourceMap.getString("jLabel57.text")); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N

        wortunterartenArtikelComboBox.setName("wortunterartenArtikelComboBox"); // NOI18N
        wortunterartenArtikelComboBox.setNextFocusableComponent(genuseArtikelComboBox);

        personenArtikelComboBox.setName("personenArtikelComboBox"); // NOI18N
        personenArtikelComboBox.setNextFocusableComponent(numeruseArtikelComboBox);

        numeruseArtikelComboBox.setName("numeruseArtikelComboBox"); // NOI18N
        numeruseArtikelComboBox.setNextFocusableComponent(speichernButton);

        genuseArtikelComboBox.setName("genuseArtikelComboBox"); // NOI18N
        genuseArtikelComboBox.setNextFocusableComponent(kasuseArtikelComboBox);

        kasuseArtikelComboBox.setName("kasuseArtikelComboBox"); // NOI18N
        kasuseArtikelComboBox.setNextFocusableComponent(personenArtikelComboBox);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        artikelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Wortunterart", "Genus", "Kasus", "Person", "Numerus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        artikelTable.setColumnSelectionAllowed(true);
        artikelTable.setName("artikelTable"); // NOI18N
        artikelTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                artikelTablePropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(artikelTable);
        artikelTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        artikelTable.getColumnModel().getColumn(0).setResizable(false);
        artikelTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        artikelTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        artikelTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("artikelTable.columnModel.title5")); // NOI18N
        artikelTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        artikelTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        artikelTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("artikelTable.columnModel.title5")); // NOI18N
        artikelTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("artikelTable.columnModel.title6")); // NOI18N

        org.jdesktop.layout.GroupLayout artikelPanelLayout = new org.jdesktop.layout.GroupLayout(artikelPanel);
        artikelPanel.setLayout(artikelPanelLayout);
        artikelPanelLayout.setHorizontalGroup(
            artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, artikelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel15)
                    .add(jLabel14)
                    .add(jLabel16)
                    .add(jLabel13)
                    .add(jLabel9)
                    .add(jLabel47))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, kasuseArtikelComboBox, 0, 200, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lexemArtikelTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, numeruseArtikelComboBox, 0, 200, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, genuseArtikelComboBox, 0, 200, Short.MAX_VALUE)
                    .add(wortunterartenArtikelComboBox, 0, 200, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, personenArtikelComboBox, 0, 200, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(artikelPanelLayout.createSequentialGroup()
                        .add(jLabel57)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenArtikelTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 612, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        artikelPanelLayout.setVerticalGroup(
            artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(artikelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(artikelPanelLayout.createSequentialGroup()
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemArtikelTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel9))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenArtikelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel13))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genuseArtikelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel16))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasuseArtikelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel47))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(personenArtikelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel14))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numeruseArtikelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel15)))
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(artikelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel57)
                    .add(suchenArtikelTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane.addTab(Artikel.getNationalClassNeme(), artikelPanel);

        interjektionPanel.setName("interjektionPanel"); // NOI18N

        lexemInterjektionTextField.setName("lexemInterjektionTextField"); // NOI18N
        lexemInterjektionTextField.setNextFocusableComponent(ausdruckInterjektionTextField);

        ausdruckInterjektionTextField.setName("ausdruckInterjektionTextField"); // NOI18N
        ausdruckInterjektionTextField.setNextFocusableComponent(speichernButton);

        suchenInterjektionTextField.setName("suchenInterjektionTextField"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel56.setText(resourceMap.getString("jLabel56.text")); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        interjektionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Interjektion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        interjektionTable.setName("interjektionTable"); // NOI18N
        interjektionTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                interjektionTablePropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(interjektionTable);
        interjektionTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        interjektionTable.getColumnModel().getColumn(0).setResizable(false);
        interjektionTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        interjektionTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        interjektionTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N

        org.jdesktop.layout.GroupLayout interjektionPanelLayout = new org.jdesktop.layout.GroupLayout(interjektionPanel);
        interjektionPanel.setLayout(interjektionPanelLayout);
        interjektionPanelLayout.setHorizontalGroup(
            interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, interjektionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel11)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(ausdruckInterjektionTextField)
                    .add(lexemInterjektionTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, interjektionPanelLayout.createSequentialGroup()
                        .add(jLabel56)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenInterjektionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        interjektionPanelLayout.setVerticalGroup(
            interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(interjektionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(interjektionPanelLayout.createSequentialGroup()
                        .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemInterjektionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(ausdruckInterjektionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel12)))
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(interjektionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel56)
                    .add(suchenInterjektionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab(Interjektion.getNationalClassNeme(), interjektionPanel);

        konjunktionPanel.setName("konjunktionPanel"); // NOI18N

        aTeilToggleButton.setAction(actionMap.get("anderenTeilDesKonjunktivsSetzten")); // NOI18N
        aTeilToggleButton.setText(resourceMap.getString("aTeilToggleButton.text")); // NOI18N
        aTeilToggleButton.setName("aTeilToggleButton"); // NOI18N

        resetATeilButton.setAction(actionMap.get("andererTeilEntfernen")); // NOI18N
        resetATeilButton.setText(resourceMap.getString("resetATeilButton.text")); // NOI18N
        resetATeilButton.setName("resetATeilButton"); // NOI18N

        lexemKonjunktionTextField.setName("lexemKonjunktionTextField"); // NOI18N
        lexemKonjunktionTextField.setNextFocusableComponent(wortunterartenKonjunktionComboBox);

        andreLexemKonjunktionTextField.setText(resourceMap.getString("andreLexemKonjunktionTextField.text")); // NOI18N
        andreLexemKonjunktionTextField.setName("andreLexemKonjunktionTextField"); // NOI18N

        suchenKonjunktionTextField.setName("suchenKonjunktionTextField"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel55.setText(resourceMap.getString("jLabel55.text")); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N

        wortunterartenKonjunktionComboBox.setName("wortunterartenKonjunktionComboBox"); // NOI18N
        wortunterartenKonjunktionComboBox.setNextFocusableComponent(speichernButton);
        wortunterartenKonjunktionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wortunterartenKonjunktionComboBoxActionPerformed(evt);
            }
        });

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        konjunktionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Des Anderen ID", "Wortunterart"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        konjunktionTable.setColumnSelectionAllowed(true);
        konjunktionTable.setName("konjunktionTable"); // NOI18N
        konjunktionTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                konjunktionTablePropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(konjunktionTable);
        konjunktionTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        konjunktionTable.getColumnModel().getColumn(0).setResizable(false);
        konjunktionTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        konjunktionTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        konjunktionTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("konjunktionTable.columnModel.title3")); // NOI18N
        konjunktionTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N

        org.jdesktop.layout.GroupLayout konjunktionPanelLayout = new org.jdesktop.layout.GroupLayout(konjunktionPanel);
        konjunktionPanel.setLayout(konjunktionPanelLayout);
        konjunktionPanelLayout.setHorizontalGroup(
            konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, konjunktionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(konjunktionPanelLayout.createSequentialGroup()
                        .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel17)
                            .add(jLabel18))
                        .add(6, 6, 6)
                        .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(andreLexemKonjunktionTextField)
                            .add(aTeilToggleButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(wortunterartenKonjunktionComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(lexemKonjunktionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 222, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(resetATeilButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(konjunktionPanelLayout.createSequentialGroup()
                        .add(jLabel55)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenKonjunktionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 590, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        konjunktionPanelLayout.setVerticalGroup(
            konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(konjunktionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(konjunktionPanelLayout.createSequentialGroup()
                        .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemKonjunktionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel17))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel18)
                            .add(wortunterartenKonjunktionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(aTeilToggleButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(andreLexemKonjunktionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(resetATeilButton))
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(konjunktionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel55)
                    .add(suchenKonjunktionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab(Konjunktion.getNationalClassNeme(), konjunktionPanel);

        nomenPanel.setName("nomenPanel"); // NOI18N

        lexemNomenTextField.setName("lexemNomenTextField"); // NOI18N
        lexemNomenTextField.setNextFocusableComponent(wortunterartenNomenComboBox);

        suchenNomenTextField.setName("suchenNomenTextField"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel54.setText(resourceMap.getString("jLabel54.text")); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N

        wortunterartenNomenComboBox.setName("wortunterartenNomenComboBox"); // NOI18N
        wortunterartenNomenComboBox.setNextFocusableComponent(genuseNomenComboBox);

        genuseNomenComboBox.setName("genuseNomenComboBox"); // NOI18N
        genuseNomenComboBox.setNextFocusableComponent(kasuseNomenComboBox);

        numeruseNomenComboBox.setName("numeruseNomenComboBox"); // NOI18N
        numeruseNomenComboBox.setNextFocusableComponent(speichernButton);

        kasuseNomenComboBox.setName("kasuseNomenComboBox"); // NOI18N
        kasuseNomenComboBox.setNextFocusableComponent(numeruseNomenComboBox);

        belebtNomenCheckBox.setText(resourceMap.getString("belebtNomenCheckBox.text")); // NOI18N
        belebtNomenCheckBox.setName("belebtNomenCheckBox"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        nomenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Infinitiv", "Genus", "Kasus", "Person", "Numerus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nomenTable.setColumnSelectionAllowed(true);
        nomenTable.setName("nomenTable"); // NOI18N
        nomenTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                nomenTablePropertyChange(evt);
            }
        });
        jScrollPane6.setViewportView(nomenTable);
        nomenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        nomenTable.getColumnModel().getColumn(0).setResizable(false);
        nomenTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        nomenTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        nomenTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N
        nomenTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        nomenTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title4")); // NOI18N
        nomenTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        nomenTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("nomenTable.columnModel.title6")); // NOI18N

        org.jdesktop.layout.GroupLayout nomenPanelLayout = new org.jdesktop.layout.GroupLayout(nomenPanel);
        nomenPanel.setLayout(nomenPanelLayout);
        nomenPanelLayout.setHorizontalGroup(
            nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, nomenPanelLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel19)
                    .add(jLabel25)
                    .add(jLabel22)
                    .add(jLabel21)
                    .add(jLabel24))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(belebtNomenCheckBox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, kasuseNomenComboBox, 0, 202, Short.MAX_VALUE)
                    .add(genuseNomenComboBox, 0, 202, Short.MAX_VALUE)
                    .add(wortunterartenNomenComboBox, 0, 202, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, numeruseNomenComboBox, 0, 202, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lexemNomenTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .add(12, 12, 12)
                .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(nomenPanelLayout.createSequentialGroup()
                        .add(jLabel54)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenNomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(302, 302, 302))
                    .add(nomenPanelLayout.createSequentialGroup()
                        .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 592, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        nomenPanelLayout.setVerticalGroup(
            nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(nomenPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(nomenPanelLayout.createSequentialGroup()
                        .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemNomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel19))
                        .add(7, 7, 7)
                        .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenNomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel21))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genuseNomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel22))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasuseNomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel25))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numeruseNomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel24))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(belebtNomenCheckBox))
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(nomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel54)
                    .add(suchenNomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab(Nomen.getNationalClassNeme(), nomenPanel);

        numeralPanel.setName("numeralPanel"); // NOI18N
        numeralPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        lexemNumeralTextField.setName("lexemNumeralTextField"); // NOI18N
        lexemNumeralTextField.setNextFocusableComponent(wertNumeralTextField);

        wertNumeralTextField.setName("wertNumeralTextField"); // NOI18N
        wertNumeralTextField.setNextFocusableComponent(wortunterartenNumeralComboBox);

        suchenNumeralTextField.setName("suchenNumeralTextField"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jLabel53.setText(resourceMap.getString("jLabel53.text")); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N

        wortunterartenNumeralComboBox.setName("wortunterartenNumeralComboBox"); // NOI18N

        genuseNumeralComboBox.setName("genuseNumeralComboBox"); // NOI18N
        genuseNumeralComboBox.setNextFocusableComponent(kasuseNumeralComboBox);

        kasuseNumeralComboBox.setName("kasuseNumeralComboBox"); // NOI18N
        kasuseNumeralComboBox.setNextFocusableComponent(speichernButton);

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        numeralTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Wert", "Infinitiv", "Wortunterunterart", "Genus", "Kasus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        numeralTable.setColumnSelectionAllowed(true);
        numeralTable.setName("numeralTable"); // NOI18N
        numeralTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                numeralTablePropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(numeralTable);
        numeralTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        numeralTable.getColumnModel().getColumn(0).setResizable(false);
        numeralTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        numeralTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        numeralTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("numeralTable.columnModel.title6")); // NOI18N
        numeralTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N
        numeralTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        numeralTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        numeralTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title4")); // NOI18N

        org.jdesktop.layout.GroupLayout numeralPanelLayout = new org.jdesktop.layout.GroupLayout(numeralPanel);
        numeralPanel.setLayout(numeralPanelLayout);
        numeralPanelLayout.setHorizontalGroup(
            numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, numeralPanelLayout.createSequentialGroup()
                .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel27)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel30)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel29)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel20)
                    .add(jLabel26))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(lexemNumeralTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .add(wertNumeralTextField)
                    .add(wortunterartenNumeralComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(genuseNumeralComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(kasuseNumeralComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(numeralPanelLayout.createSequentialGroup()
                        .add(jLabel53)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenNumeralTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                .addContainerGap())
        );
        numeralPanelLayout.setVerticalGroup(
            numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(numeralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(numeralPanelLayout.createSequentialGroup()
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemNumeralTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel20))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wertNumeralTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel29))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenNumeralComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel26))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genuseNumeralComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel27))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasuseNumeralComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel30)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, numeralPanelLayout.createSequentialGroup()
                        .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numeralPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel53)
                            .add(suchenNumeralTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTabbedPane.addTab(Numeral.getNationalClassNeme(), numeralPanel);

        präpositionPanel.setName("präpositionPanel"); // NOI18N
        präpositionPanel.setOpaque(false);
        präpositionPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        lexemPräpositionTextField.setName("lexemPräpositionTextField"); // NOI18N
        lexemPräpositionTextField.setNextFocusableComponent(umstaendePräpositionComboBox);

        suchenPräpositionTextField.setName("suchenPräpositionTextField"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jLabel52.setText(resourceMap.getString("jLabel52.text")); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N

        kasusePräpositionComboBox.setName("kasusePräpositionComboBox"); // NOI18N

        umstaendePräpositionComboBox.setName("umstaendePräpositionComboBox"); // NOI18N
        umstaendePräpositionComboBox.setNextFocusableComponent(speichernButton);

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        präpositionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lexem", "Umstand"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        präpositionTable.setName("präpositionTable"); // NOI18N
        präpositionTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                präpositionTablePropertyChange(evt);
            }
        });
        jScrollPane8.setViewportView(präpositionTable);

        jLabel60.setText(resourceMap.getString("jLabel60.text")); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N

        org.jdesktop.layout.GroupLayout präpositionPanelLayout = new org.jdesktop.layout.GroupLayout(präpositionPanel);
        präpositionPanel.setLayout(präpositionPanelLayout);
        präpositionPanelLayout.setHorizontalGroup(
            präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, präpositionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel31)
                    .add(jLabel60)
                    .add(jLabel32))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(kasusePräpositionComboBox, 0, 246, Short.MAX_VALUE)
                    .add(umstaendePräpositionComboBox, 0, 246, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lexemPräpositionTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(präpositionPanelLayout.createSequentialGroup()
                        .add(jLabel52)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenPräpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 589, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        präpositionPanelLayout.setVerticalGroup(
            präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(präpositionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .add(präpositionPanelLayout.createSequentialGroup()
                        .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemPräpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel31))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasusePräpositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(umstaendePräpositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel32))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(präpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel52)
                    .add(suchenPräpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane.addTab(Präposition.getNationalClassNeme(), präpositionPanel);

        pronomenPanel.setName("pronomenPanel"); // NOI18N
        pronomenPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        lexemPronomenTextField.setName("lexemPronomenTextField"); // NOI18N
        lexemPronomenTextField.setNextFocusableComponent(wortunterartenPronomenComboBox);

        suchenPronomenTextField.setName("suchenPronomenTextField"); // NOI18N

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N

        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N

        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N

        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        jLabel51.setText(resourceMap.getString("jLabel51.text")); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N

        wortunterartenPronomenComboBox.setAction(actionMap.get("erweiterPzuReflexiPronomen")); // NOI18N
        wortunterartenPronomenComboBox.setName("wortunterartenPronomenComboBox"); // NOI18N
        wortunterartenPronomenComboBox.setNextFocusableComponent(genusePronomenComboBox);

        genusePronomenComboBox.setName("genusePronomenComboBox"); // NOI18N
        genusePronomenComboBox.setNextFocusableComponent(kasusePronomenComboBox);

        personenPronomenComboBox.setName("personenPronomenComboBox"); // NOI18N
        personenPronomenComboBox.setNextFocusableComponent(numerusePronomenComboBox);

        numerusePronomenComboBox.setName("numerusePronomenComboBox"); // NOI18N
        numerusePronomenComboBox.setNextFocusableComponent(speichernButton);

        kasusePronomenComboBox.setName("kasusePronomenComboBox"); // NOI18N
        kasusePronomenComboBox.setNextFocusableComponent(personenPronomenComboBox);

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        pronomenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Infinitiv", "Genus", "Kasus", "Person", "Numerus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pronomenTable.setName("pronomenTable"); // NOI18N
        pronomenTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pronomenTablePropertyChange(evt);
            }
        });
        jScrollPane9.setViewportView(pronomenTable);
        pronomenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        pronomenTable.getColumnModel().getColumn(0).setResizable(false);
        pronomenTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        pronomenTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        pronomenTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N
        pronomenTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        pronomenTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title4")); // NOI18N
        pronomenTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        pronomenTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("nomenTable.columnModel.title6")); // NOI18N

        org.jdesktop.layout.GroupLayout pronomenPanelLayout = new org.jdesktop.layout.GroupLayout(pronomenPanel);
        pronomenPanel.setLayout(pronomenPanelLayout);
        pronomenPanelLayout.setHorizontalGroup(
            pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pronomenPanelLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel33)
                    .add(jLabel37)
                    .add(jLabel36)
                    .add(jLabel38)
                    .add(jLabel35)
                    .add(jLabel34))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, numerusePronomenComboBox, 0, 219, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, kasusePronomenComboBox, 0, 219, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, personenPronomenComboBox, 0, 219, Short.MAX_VALUE)
                    .add(genusePronomenComboBox, 0, 219, Short.MAX_VALUE)
                    .add(wortunterartenPronomenComboBox, 0, 219, Short.MAX_VALUE)
                    .add(lexemPronomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(pronomenPanelLayout.createSequentialGroup()
                        .add(jLabel51)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenPronomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(278, 278, 278))
                    .add(pronomenPanelLayout.createSequentialGroup()
                        .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 574, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pronomenPanelLayout.setVerticalGroup(
            pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pronomenPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pronomenPanelLayout.createSequentialGroup()
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemPronomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel33))
                        .add(7, 7, 7)
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenPronomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel34))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genusePronomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel35))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasusePronomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel38))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(personenPronomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel36))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numerusePronomenComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel37)))
                    .add(jScrollPane9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pronomenPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel51)
                    .add(suchenPronomenTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane.addTab(Pronomen.getNationalClassNeme(), pronomenPanel);

        postpositionPanel.setName("postpositionPanel"); // NOI18N
        postpositionPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        lexemPostpositionTextField.setName("lexemPostpositionTextField"); // NOI18N
        lexemPostpositionTextField.setNextFocusableComponent(umstaendePräpositionComboBox);

        suchenPostpositionTextField.setName("suchenPostpositionTextField"); // NOI18N

        jLabel61.setText(resourceMap.getString("jLabel61.text")); // NOI18N
        jLabel61.setName("jLabel61"); // NOI18N

        jLabel62.setText(resourceMap.getString("jLabel62.text")); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N

        jLabel63.setText(resourceMap.getString("jLabel63.text")); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N

        jLabel64.setText(resourceMap.getString("jLabel64.text")); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N

        kasusePostpositionComboBox.setName("kasusePostpositionComboBox"); // NOI18N

        umstaendePostpositionComboBox.setName("umstaendePostpositionComboBox"); // NOI18N
        umstaendePostpositionComboBox.setNextFocusableComponent(speichernButton);

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        postpositionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lexem", "Kasus", "Umstand"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        postpositionTable.setName("postpositionTable"); // NOI18N
        postpositionTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                postpositionTablePropertyChange(evt);
            }
        });
        jScrollPane11.setViewportView(postpositionTable);
        postpositionTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("postpositionTable.columnModel.title0")); // NOI18N
        postpositionTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("postpositionTable.columnModel.title1")); // NOI18N
        postpositionTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("postpositionTable.columnModel.title3")); // NOI18N
        postpositionTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("postpositionTable.columnModel.title2")); // NOI18N

        org.jdesktop.layout.GroupLayout postpositionPanelLayout = new org.jdesktop.layout.GroupLayout(postpositionPanel);
        postpositionPanel.setLayout(postpositionPanelLayout);
        postpositionPanelLayout.setHorizontalGroup(
            postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, postpositionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel64)
                    .add(jLabel62)
                    .add(jLabel61))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(umstaendePostpositionComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(kasusePostpositionComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(lexemPostpositionTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, postpositionPanelLayout.createSequentialGroup()
                        .add(jLabel63)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenPostpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                .addContainerGap())
        );
        postpositionPanelLayout.setVerticalGroup(
            postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(postpositionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(postpositionPanelLayout.createSequentialGroup()
                        .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemPostpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel61))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(kasusePostpositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel64))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(umstaendePostpositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel62)))
                    .add(jScrollPane11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(postpositionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel63)
                    .add(suchenPostpositionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane.addTab(Postposition.getNationalClassNeme(), postpositionPanel);

        verbPanel.setName("verbPanel"); // NOI18N

        hilfsVerbToggleButton.setAction(actionMap.get("hilfsVerbWahl")); // NOI18N
        hilfsVerbToggleButton.setText(resourceMap.getString("hilfsVerbToggleButton.text")); // NOI18N
        hilfsVerbToggleButton.setName("hilfsVerbToggleButton"); // NOI18N

        resetHilfsVerbButton.setAction(actionMap.get("hilfsVerbEntfernen")); // NOI18N
        resetHilfsVerbButton.setText(resourceMap.getString("resetHilfsVerbButton.text")); // NOI18N
        resetHilfsVerbButton.setName("resetHilfsVerbButton"); // NOI18N

        suchenVerbButton.setAction(actionMap.get("hilfsVerbSuche")); // NOI18N
        suchenVerbButton.setText(resourceMap.getString("suchenVerbButton.text")); // NOI18N
        suchenVerbButton.setName("suchenVerbButton"); // NOI18N

        lexemVerbTextField.setName("lexemVerbTextField"); // NOI18N
        lexemVerbTextField.setNextFocusableComponent(infiVerbTextField);

        infiVerbTextField.setName("infiVerbTextField"); // NOI18N
        infiVerbTextField.setNextFocusableComponent(wortunterartenVerbComboBox);

        hilfsVerbTextField.setEditable(false);
        hilfsVerbTextField.setText(resourceMap.getString("hilfsVerbTextField.text")); // NOI18N
        hilfsVerbTextField.setName("hilfsVerbTextField"); // NOI18N

        infiHilfsVerbTextField.setText(resourceMap.getString("infiHilfsVerbTextField.text")); // NOI18N
        infiHilfsVerbTextField.setName("infiHilfsVerbTextField"); // NOI18N

        suchenVerbTextField.setText(resourceMap.getString("suchenVerbTextField.text")); // NOI18N
        suchenVerbTextField.setName("suchenVerbTextField"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        jLabel40.setText(resourceMap.getString("jLabel40.text")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        jLabel41.setText(resourceMap.getString("jLabel41.text")); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N

        jLabel42.setText(resourceMap.getString("jLabel42.text")); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N

        jLabel43.setText(resourceMap.getString("jLabel43.text")); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N

        jLabel44.setText(resourceMap.getString("jLabel44.text")); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        jLabel46.setText(resourceMap.getString("jLabel46.text")); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N

        jLabel48.setText(resourceMap.getString("jLabel48.text")); // NOI18N
        jLabel48.setName("jLabel48"); // NOI18N

        jLabel50.setText(resourceMap.getString("jLabel50.text")); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N

        wortunterartenVerbComboBox.setAction(actionMap.get("erweitereRegelmaessigeVerben")); // NOI18N
        wortunterartenVerbComboBox.setName("wortunterartenVerbComboBox"); // NOI18N
        wortunterartenVerbComboBox.setNextFocusableComponent(moduseVerbComboBox);

        moduseVerbComboBox.setName("moduseVerbComboBox"); // NOI18N
        moduseVerbComboBox.setNextFocusableComponent(tempuseVerbComboBox);

        tempuseVerbComboBox.setName("tempuseVerbComboBox"); // NOI18N
        tempuseVerbComboBox.setNextFocusableComponent(altuseVerbComboBox);

        altuseVerbComboBox.setName("altuseVerbComboBox"); // NOI18N
        altuseVerbComboBox.setNextFocusableComponent(personenVerbComboBox);

        personenVerbComboBox.setName("personenVerbComboBox"); // NOI18N
        personenVerbComboBox.setNextFocusableComponent(numeruseVerbComboBox);

        numeruseVerbComboBox.setName("numeruseVerbComboBox"); // NOI18N
        numeruseVerbComboBox.setNextFocusableComponent(speichernButton);

        hilfsVerbComboBox.setName("hilfsVerbComboBox"); // NOI18N
        hilfsVerbComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hilfsVerbComboBoxActionPerformed(evt);
            }
        });

        genuseVerbComboBox.setName("genuseVerbComboBox"); // NOI18N
        genuseVerbComboBox.setNextFocusableComponent(speichernButton);

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        verbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lexem", "Infinitiv", "Hilfsverb", "Wortunterart", "Modus", "Tempus", "Alt/Neu", "Person", "Numerus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        verbTable.setColumnSelectionAllowed(true);
        verbTable.setName("verbTable"); // NOI18N
        verbTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                verbTablePropertyChange(evt);
            }
        });
        jScrollPane10.setViewportView(verbTable);
        verbTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        verbTable.getColumnModel().getColumn(0).setResizable(false);
        verbTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title0")); // NOI18N
        verbTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title1")); // NOI18N
        verbTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("verbTable.columnModel.title7")); // NOI18N
        verbTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("verbTable.columnModel.title9")); // NOI18N
        verbTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title2")); // NOI18N
        verbTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title5")); // NOI18N
        verbTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title4")); // NOI18N
        verbTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("adjektivTable.columnModel.title6")); // NOI18N
        verbTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("nomenTable.columnModel.title6")); // NOI18N
        verbTable.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("verbTable.columnModel.title8")); // NOI18N

        jLabel66.setText(resourceMap.getString("jLabel66.text")); // NOI18N
        jLabel66.setName("jLabel66"); // NOI18N

        org.jdesktop.layout.GroupLayout verbPanelLayout = new org.jdesktop.layout.GroupLayout(verbPanel);
        verbPanel.setLayout(verbPanelLayout);
        verbPanelLayout.setHorizontalGroup(
            verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, verbPanelLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel39)
                    .add(jLabel43)
                    .add(jLabel42)
                    .add(jLabel44)
                    .add(jLabel41)
                    .add(jLabel40)
                    .add(jLabel46)
                    .add(jLabel48)
                    .add(jLabel45)
                    .add(jLabel23)
                    .add(jLabel66))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, genuseVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, hilfsVerbTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(hilfsVerbToggleButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(verbPanelLayout.createSequentialGroup()
                        .add(suchenVerbButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 27, Short.MAX_VALUE)
                        .add(resetHilfsVerbButton))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, numeruseVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, personenVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, altuseVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tempuseVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, moduseVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, wortunterartenVerbComboBox, 0, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, infiVerbTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lexemVerbTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, infiHilfsVerbTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .add(hilfsVerbComboBox, 0, 194, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(verbPanelLayout.createSequentialGroup()
                        .add(jLabel50)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(suchenVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 579, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        verbPanelLayout.setVerticalGroup(
            verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, verbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .add(verbPanelLayout.createSequentialGroup()
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lexemVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel39))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(infiVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel45))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(wortunterartenVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel40))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(moduseVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel41))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(tempuseVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel44))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(altuseVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel46))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(personenVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel42))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numeruseVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel43))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(genuseVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel66))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(hilfsVerbToggleButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(hilfsVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel23))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(infiHilfsVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel48))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(resetHilfsVerbButton)
                            .add(suchenVerbButton))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(verbPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(hilfsVerbComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel50)
                    .add(suchenVerbTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab(Verb.getNationalClassNeme(), verbPanel);

        speichernButton.setAction(actionMap.get("saveWord")); // NOI18N
        speichernButton.setText(resourceMap.getString("speichernButton.text")); // NOI18N
        speichernButton.setName("speichernButton"); // NOI18N
        speichernButton.setNextFocusableComponent(entferenenButton);

        entferenenButton.setAction(actionMap.get("removeWort")); // NOI18N
        entferenenButton.setText(resourceMap.getString("entferenenButton.text")); // NOI18N
        entferenenButton.setName("entferenenButton"); // NOI18N
        entferenenButton.setNextFocusableComponent(lexemAdjektivTextField);

        refreshButton.setAction(actionMap.get("updateTable")); // NOI18N
        refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
        refreshButton.setName("refreshButton"); // NOI18N

        auswahlCheckBox.setAction(actionMap.get("auswahlEditieren")); // NOI18N
        auswahlCheckBox.setText(resourceMap.getString("auswahlCheckBox.text")); // NOI18N
        auswahlCheckBox.setName("auswahlCheckBox"); // NOI18N

        deutschRadioButton.setAction(actionMap.get("switchLanguage")); // NOI18N
        languageButtonGroup.add(deutschRadioButton);
        deutschRadioButton.setSelected(WAtt.getLanguage()==0);
        deutschRadioButton.setText(resourceMap.getString("deutschRadioButton.text")); // NOI18N
        deutschRadioButton.setName("deutschRadioButton"); // NOI18N

        polnischRadioButton.setAction(actionMap.get("switchLanguage")); // NOI18N
        languageButtonGroup.add(polnischRadioButton);
        polnischRadioButton.setSelected(WAtt.getLanguage()==1);
        polnischRadioButton.setText(resourceMap.getString("polnischRadioButton.text")); // NOI18N
        polnischRadioButton.setName("polnischRadioButton"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(deutschRadioButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(polnischRadioButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(auswahlCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(refreshButton)
                        .add(172, 172, 172)
                        .add(entferenenButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(speichernButton)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 936, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 534, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(speichernButton)
                    .add(entferenenButton)
                    .add(auswahlCheckBox)
                    .add(refreshButton)
                    .add(polnischRadioButton)
                    .add(deutschRadioButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adjektivTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_adjektivTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (adjektivTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) adjektivTable.getValueAt(adjektivTable.getSelectedRow(), 0));
            System.out.println(key);
            Adjektiv a = em.find(Adjektiv.class, key);
            if (a != null) {
                adjektivTemp = a;
                lexemAdjektivTextField.setText(a.getLexem());
                infiAdjektivTextField.setText(a.getGrundform());
                wortunterartenAdjektivComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                steigerungenAdjektivComboBox.setSelectedItem(a.getSteigerungAA().getKey() + " :: " + a.getSteigerungAA().getName());
                kasuseAdjektivComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                if(WAtt.getLanguage()==1)genuseAdjektivComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                numeruseAdjektivComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
            }
        }
    }//GEN-LAST:event_adjektivTablePropertyChange

    private void adverbTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_adverbTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (adverbTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) adverbTable.getValueAt(adverbTable.getSelectedRow(), 0));
            System.out.println(key);
            Adverb a = em.find(Adverb.class, key);
            if (a != null) {
                adverbTemp = a;
                lexemAdverbTextField.setText(a.getLexem());
                wortunterartenAdverbComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                umstaendeAdverbComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
            }
        }
}//GEN-LAST:event_adverbTablePropertyChange

    private void artikelTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_artikelTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (artikelTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) artikelTable.getValueAt(artikelTable.getSelectedRow(), 0));
            System.out.println(key);
            Artikel a = em.find(Artikel.class, key);
            if (a != null) {
                artikelTemp = a;
                lexemArtikelTextField.setText(a.getLexem());
                wortunterartenArtikelComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                genuseArtikelComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                kasuseArtikelComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                personenArtikelComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                numeruseArtikelComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
            }
        }
}//GEN-LAST:event_artikelTablePropertyChange

    private void interjektionTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_interjektionTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (interjektionTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) interjektionTable.getValueAt(interjektionTable.getSelectedRow(), 0));
            System.out.println(key);
            Interjektion a = em.find(Interjektion.class, key);
            if (a != null) {
                interjektionTemp = a;
                lexemInterjektionTextField.setText(a.getLexem());
                ausdruckInterjektionTextField.setText(a.getAusdruck());
            }
        }
}//GEN-LAST:event_interjektionTablePropertyChange

    private void konjunktionTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_konjunktionTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if(aTeilToggleButton.isSelected()){
            if (konjunktionTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) konjunktionTable.getValueAt(konjunktionTable.getSelectedRow(), 0));
                Konjunktion a = em.find(Konjunktion.class, key);
                andereKonjunktion = a;
                if (a != null) {
                    andreLexemKonjunktionTextField.setText(a.getLexem());
                }
            } else {
                andereKonjunktion = null;
            }           
        }else{
            if (konjunktionTable.getSelectedRow() >= 0) {
                auswahlCheckBox.setSelected(true);
                int key = Integer.parseInt((String) konjunktionTable.getValueAt(konjunktionTable.getSelectedRow(), 0));
                System.out.println(key);
                Konjunktion a = em.find(Konjunktion.class, key);
                if (a != null) {
                    konjunktionTemp = a;
                    lexemKonjunktionTextField.setText(a.getLexem());
                    wortunterartenKonjunktionComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                }
            }
        }
}//GEN-LAST:event_konjunktionTablePropertyChange

    private void nomenTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_nomenTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (nomenTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) nomenTable.getValueAt(nomenTable.getSelectedRow(), 0));          
            Nomen a = em.find(Nomen.class, key);
            if (a != null) {
                nomenTemp = a;
                lexemNomenTextField.setText(a.getLexem());
                wortunterartenNomenComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                genuseNomenComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                kasuseNomenComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                numeruseNomenComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                belebtNomenCheckBox.setSelected(a.isBelebt());
            }
        }
}//GEN-LAST:event_nomenTablePropertyChange

    private void numeralTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_numeralTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (numeralTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) numeralTable.getValueAt(numeralTable.getSelectedRow(), 0));
            
            Numeral a = em.find(Numeral.class, key);
            if (a != null) {
                numeralTemp = a;
                lexemNumeralTextField.setText(a.getLexem());
                wertNumeralTextField.setText(String.valueOf(a.getWert()));
                wortunterartenNumeralComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                genuseNumeralComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                kasuseNumeralComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
            }
        }
}//GEN-LAST:event_numeralTablePropertyChange

    private void PräpositionTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_PräpositionTablePropertyChange
        //Raus mit den Scheiss
}//GEN-LAST:event_PräpositionTablePropertyChange

    private void pronomenTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pronomenTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (pronomenTable.getSelectedRow() >= 0) {
            if(pronomenTable.getSelectedColumnCount()>1) System.out.println("Anzahl der gewählten Zeilen: "+pronomenTable.getSelectedColumnCount());
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) pronomenTable.getValueAt(pronomenTable.getSelectedRow(), 0));
            
            Pronomen a = em.find(Pronomen.class, key);
            if (a != null) {
                pronomenTemp = a;
                lexemPronomenTextField.setText(a.getLexem());
                wortunterartenPronomenComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                genusePronomenComboBox.setSelectedItem(a.getGenusAA().getKey() + " :: " + a.getGenusAA().getName());
                kasusePronomenComboBox.setSelectedItem(a.getKasusAA().getKey() + " :: " + a.getKasusAA().getName());
                personenPronomenComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                numerusePronomenComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
            }
        }
}//GEN-LAST:event_pronomenTablePropertyChange

    private void verbTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_verbTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (hilfsVerbToggleButton.isSelected()) {
            if (verbTable.getSelectedRow() >= 0) {
                int key = Integer.parseInt((String) verbTable.getValueAt(verbTable.getSelectedRow(), 0));
                Verb a = em.find(Verb.class, key);
                hilfsVerb = a;
                if (a != null) {
                    hilfsVerbTextField.setText(a.getLexem());
                    infiHilfsVerbTextField.setText(a.getInfinitiv());
                }
            }
        }else{
            if (verbTable.getSelectedRow() >= 0) {
                auswahlCheckBox.setSelected(true);
                int key = Integer.parseInt((String) verbTable.getValueAt(verbTable.getSelectedRow(), 0));
                Verb a = em.find(Verb.class, key);
                if (a != null) {
                    verbTemp = a;
                    lexemVerbTextField.setText(a.getLexem());
                    infiVerbTextField.setText(a.getInfinitiv());
                    wortunterartenVerbComboBox.setSelectedItem(a.getWortunterartAA().getKey() + " :: " + a.getWortunterartAA().getName());
                    moduseVerbComboBox.setSelectedItem(a.getModusAA().getKey() + " :: " + a.getModusAA().getName());
                    tempuseVerbComboBox.setSelectedItem(a.getTempusAA().getKey() + " :: " + a.getTempusAA().getName());
                    altuseVerbComboBox.setSelectedItem(a.getAltusAA().getKey() + " :: " + a.getAltusAA().getName());
                    personenVerbComboBox.setSelectedItem(a.getPersonAA().getKey() + " :: " + a.getPersonAA().getName());
                    numeruseVerbComboBox.setSelectedItem(a.getNumerusAA().getKey() + " :: " + a.getNumerusAA().getName());
                }
            }
        }
}//GEN-LAST:event_verbTablePropertyChange

    private void hilfsVerbComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hilfsVerbComboBoxActionPerformed
        hilfsVerbSetzen();
    }//GEN-LAST:event_hilfsVerbComboBoxActionPerformed

    private void präpositionTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_präpositionTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (präpositionTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) präpositionTable.getValueAt(präpositionTable.getSelectedRow(), 0));    
            Präposition a = em.find(Präposition.class, key);
            if (a != null) {
                präpositionTemp = a;
                lexemPräpositionTextField.setText(a.getLexem());
                kasusePräpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
                umstaendePräpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
            }
        }
    }//GEN-LAST:event_präpositionTablePropertyChange

    private void wortunterartenKonjunktionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wortunterartenKonjunktionComboBoxActionPerformed
        String wortkey = (String) wortunterartenKonjunktionComboBox.getSelectedItem();
        if(wortkey!=null){
            wortkey = wortkey.substring(0, wortkey.indexOf(" ::"));
            if(wortkey.equals("mehr")||wortkey.equals("infi")){
                aTeilToggleButton.setEnabled(true);
                andreLexemKonjunktionTextField.setEnabled(true);
            }else{
                aTeilToggleButton.setEnabled(false);
                aTeilToggleButton.setSelected(false);
                andreLexemKonjunktionTextField.setEnabled(false);
                andreLexemKonjunktionTextField.setText("");
            }
        }
    }//GEN-LAST:event_wortunterartenKonjunktionComboBoxActionPerformed

    private void postpositionTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_postpositionTablePropertyChange
        if(WAtt.getLanguage()==0) em = emGER;
        if(WAtt.getLanguage()==1) em = emPOL;
        if (postpositionTable.getSelectedRow() >= 0) {
            auswahlCheckBox.setSelected(true);
            int key = Integer.parseInt((String) postpositionTable.getValueAt(postpositionTable.getSelectedRow(), 0));       
            Postposition a = em.find(Postposition.class, key);
            if (a != null) {
                postpositionTemp = a;
                lexemPostpositionTextField.setText(a.getLexem());
                kasusePostpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
                umstaendePostpositionComboBox.setSelectedItem(a.getUmstandAA().getKey() + " :: " + a.getUmstandAA().getName());
            }
        }
}//GEN-LAST:event_postpositionTablePropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton aTeilToggleButton;
    private javax.swing.JPanel adjektivPanel;
    private javax.swing.JTable adjektivTable;
    private javax.swing.JPanel adverbPanel;
    private javax.swing.JTable adverbTable;
    private javax.swing.JComboBox altuseVerbComboBox;
    private javax.swing.JTextField andreLexemKonjunktionTextField;
    private javax.swing.JPanel artikelPanel;
    private javax.swing.JTable artikelTable;
    private javax.swing.JTextField ausdruckInterjektionTextField;
    private javax.swing.JCheckBox auswahlCheckBox;
    private javax.swing.JCheckBox belebtNomenCheckBox;
    private javax.swing.JRadioButton deutschRadioButton;
    private javax.swing.JButton entferenenButton;
    private javax.swing.JComboBox genuseAdjektivComboBox;
    private javax.swing.JComboBox genuseArtikelComboBox;
    private javax.swing.JComboBox genuseNomenComboBox;
    private javax.swing.JComboBox genuseNumeralComboBox;
    private javax.swing.JComboBox genusePronomenComboBox;
    private javax.swing.JComboBox genuseVerbComboBox;
    private javax.swing.JComboBox hilfsVerbComboBox;
    private javax.swing.JTextField hilfsVerbTextField;
    private javax.swing.JToggleButton hilfsVerbToggleButton;
    private javax.swing.JTextField infiAdjektivTextField;
    private javax.swing.JTextField infiHilfsVerbTextField;
    private javax.swing.JTextField infiVerbTextField;
    private javax.swing.JPanel interjektionPanel;
    private javax.swing.JTable interjektionTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JComboBox kasuseAdjektivComboBox;
    private javax.swing.JComboBox kasuseArtikelComboBox;
    private javax.swing.JComboBox kasuseNomenComboBox;
    private javax.swing.JComboBox kasuseNumeralComboBox;
    private javax.swing.JComboBox kasusePostpositionComboBox;
    private javax.swing.JComboBox kasusePronomenComboBox;
    private javax.swing.JComboBox kasusePräpositionComboBox;
    private javax.swing.JPanel konjunktionPanel;
    private javax.swing.JTable konjunktionTable;
    private javax.swing.ButtonGroup languageButtonGroup;
    private javax.swing.JTextField lexemAdjektivTextField;
    private javax.swing.JTextField lexemAdverbTextField;
    private javax.swing.JTextField lexemArtikelTextField;
    private javax.swing.JTextField lexemInterjektionTextField;
    private javax.swing.JTextField lexemKonjunktionTextField;
    private javax.swing.JTextField lexemNomenTextField;
    private javax.swing.JTextField lexemNumeralTextField;
    private javax.swing.JTextField lexemPostpositionTextField;
    private javax.swing.JTextField lexemPronomenTextField;
    private javax.swing.JTextField lexemPräpositionTextField;
    private javax.swing.JTextField lexemVerbTextField;
    private javax.swing.JComboBox moduseVerbComboBox;
    private javax.swing.JPanel nomenPanel;
    private javax.swing.JTable nomenTable;
    private javax.swing.JPanel numeralPanel;
    private javax.swing.JTable numeralTable;
    private javax.swing.JComboBox numeruseAdjektivComboBox;
    private javax.swing.JComboBox numeruseArtikelComboBox;
    private javax.swing.JComboBox numeruseNomenComboBox;
    private javax.swing.JComboBox numerusePronomenComboBox;
    private javax.swing.JComboBox numeruseVerbComboBox;
    private javax.swing.JComboBox personenArtikelComboBox;
    private javax.swing.JComboBox personenPronomenComboBox;
    private javax.swing.JComboBox personenVerbComboBox;
    private javax.swing.JRadioButton polnischRadioButton;
    private javax.swing.JPanel postpositionPanel;
    private javax.swing.JTable postpositionTable;
    private javax.swing.JPanel pronomenPanel;
    private javax.swing.JTable pronomenTable;
    private javax.swing.JPanel präpositionPanel;
    private javax.swing.JTable präpositionTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton resetATeilButton;
    private javax.swing.JButton resetHilfsVerbButton;
    private javax.swing.JButton speichernButton;
    private javax.swing.JComboBox steigerungenAdjektivComboBox;
    private javax.swing.JTextField suchenAdjektivTextField;
    private javax.swing.JTextField suchenAdverbTextField;
    private javax.swing.JTextField suchenArtikelTextField;
    private javax.swing.JTextField suchenInterjektionTextField;
    private javax.swing.JTextField suchenKonjunktionTextField;
    private javax.swing.JTextField suchenNomenTextField;
    private javax.swing.JTextField suchenNumeralTextField;
    private javax.swing.JTextField suchenPostpositionTextField;
    private javax.swing.JTextField suchenPronomenTextField;
    private javax.swing.JTextField suchenPräpositionTextField;
    private javax.swing.JButton suchenVerbButton;
    private javax.swing.JTextField suchenVerbTextField;
    private javax.swing.JComboBox tempuseVerbComboBox;
    private javax.swing.JComboBox umstaendeAdverbComboBox;
    private javax.swing.JComboBox umstaendePostpositionComboBox;
    private javax.swing.JComboBox umstaendePräpositionComboBox;
    private javax.swing.JPanel verbPanel;
    private javax.swing.JTable verbTable;
    private javax.swing.JTextField wertNumeralTextField;
    private javax.swing.JComboBox wortunterartenAdjektivComboBox;
    private javax.swing.JComboBox wortunterartenAdverbComboBox;
    private javax.swing.JComboBox wortunterartenArtikelComboBox;
    private javax.swing.JComboBox wortunterartenKonjunktionComboBox;
    private javax.swing.JComboBox wortunterartenNomenComboBox;
    private javax.swing.JComboBox wortunterartenNumeralComboBox;
    private javax.swing.JComboBox wortunterartenPronomenComboBox;
    private javax.swing.JComboBox wortunterartenVerbComboBox;
    // End of variables declaration//GEN-END:variables
}
