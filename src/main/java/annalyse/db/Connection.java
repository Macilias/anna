/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.db;

import java.util.HashMap;
import java.util.Map;
//import oracle.toplink.essentials.config.TopLinkProperties;
import org.eclipse.persistence.config.PersistenceUnitProperties;
/**
 *
 * @author Maciej Niemczyk
 */
public class Connection {

    private static String user = "root";
    private static String password = "mac@mysql";
    private static String ip = "127.0.0.1";
    private static int port = 3306;
    private static String databaseGermanWords = "MNGermanWords";
    private static String databaseGermanRules = "MNGermanRules";
    private static String databasePolishWords = "MNPolishWords";
    private static String databasePolishRules = "MNPolishRules";
    private static String urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanWords;
    private static String urlRules = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanRules;
//useEncoding=true;characterEncoding=UTF-8;
    private static Map propertiesWordsGER = new HashMap();
    private static Map propertiesRulesGER = new HashMap();
    private static Map propertiesWordsPOL = new HashMap();
    private static Map propertiesRulesPOL = new HashMap();
//    private static Map propertiesWords = new HashMap();
//    private static Map propertiesRules = new HashMap();

    private static void setPropertiesWordsGER(){
//        Properties props = new Properties();
//        props.setProperty("characterEncoding", "utf-8");
//        DriverManager.g .getConnection(connectionString, props);
        propertiesWordsGER = new HashMap();
        propertiesWordsGER.put(PersistenceUnitProperties.JDBC_USER, user);
        propertiesWordsGER.put(PersistenceUnitProperties.JDBC_PASSWORD, password);
        urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanWords+"?useUnicode=true&amp;characterEncoding=UTF-8";
        propertiesWordsGER.put(PersistenceUnitProperties.JDBC_URL, urlWords);
//        propertiesWordsGER.put(TopLinkProperties.JDBC_USER, user);
//        propertiesWordsGER.put(TopLinkProperties.JDBC_PASSWORD, password);
//        urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanWords;
//        propertiesWordsGER.put(TopLinkProperties.JDBC_URL, urlWords);

    }

    public static Map getPropertiesWordsGER(){
        setPropertiesWordsGER();
        return propertiesWordsGER;
    }

    private static void setPropertiesRulesGER(){
        propertiesRulesGER = new HashMap();
//        propertiesRulesGER.put(TopLinkProperties.JDBC_USER, user);
//        propertiesRulesGER.put(TopLinkProperties.JDBC_PASSWORD, password);
//        urlRules = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanRules;
//        propertiesRulesGER.put(TopLinkProperties.JDBC_URL, urlRules);
    }

    public static Map getPropertiesRulesGER(){
        setPropertiesRulesGER();
        return propertiesRulesGER;
    }

    private static void setPropertiesWordsPOL(){
        propertiesWordsPOL = new HashMap();
        propertiesWordsPOL.put(PersistenceUnitProperties.JDBC_USER, user);
        propertiesWordsPOL.put(PersistenceUnitProperties.JDBC_PASSWORD, password);
        urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databasePolishWords+"?useUnicode=true&amp;characterEncoding=UTF-8";
        propertiesWordsPOL.put(PersistenceUnitProperties.JDBC_URL, urlWords);
//        propertiesWordsPOL = new HashMap();
//        propertiesWordsPOL.put(TopLinkProperties.JDBC_USER, user);
//        propertiesWordsPOL.put(TopLinkProperties.JDBC_PASSWORD, password);
//        urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databasePolishWords;
//        propertiesWordsPOL.put(TopLinkProperties.JDBC_URL, urlWords);
    }

    public static Map getPropertiesWordsPOL(){
        setPropertiesWordsPOL();
        return propertiesWordsPOL;
    }

    private static void setPropertiesRulesPOL(){
        propertiesRulesPOL = new HashMap();
//        propertiesRulesPOL.put(TopLinkProperties.JDBC_USER, user);
//        propertiesRulesPOL.put(TopLinkProperties.JDBC_PASSWORD, password);
//        urlRules = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databasePolishRules;
//        propertiesRulesPOL.put(TopLinkProperties.JDBC_URL, urlRules);
    }

    public static Map getPropertiesRulesPOL(){
        setPropertiesRulesPOL();
        return propertiesRulesPOL;
    }

//    private static void setPropertiesWords(){
//        propertiesWords = new HashMap();
//        propertiesWords.put(TopLinkProperties.JDBC_USER, user);
//        propertiesWords.put(TopLinkProperties.JDBC_PASSWORD, password);
//        if(WAtt.getLanguage()==0) databaseWords = "MNGermanWords";
//        if(WAtt.getLanguage()==1) databaseWords = "MNPolishWords";
//        urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseWords;
//        propertiesWords.put(TopLinkProperties.JDBC_URL, urlWords);
//    }
//
//    public static Map getPropertiesWords(){
//        setPropertiesWords();
//        return propertiesWords;
//    }
//
//    private static void setPropertiesRules(){
//        propertiesRules = new HashMap();
//        propertiesRules.put(TopLinkProperties.JDBC_USER, user);
//        propertiesRules.put(TopLinkProperties.JDBC_PASSWORD, password);
//        if(WAtt.getLanguage()==0) databaseRules = "MNGermanRules";
//        if(WAtt.getLanguage()==1) databaseRules = "MNPolishRules";
//        urlRules = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseRules;
//        propertiesRules.put(TopLinkProperties.JDBC_URL, urlRules);
//    }

    public static void setUser(String aUser) throws WrongConnectionParameterException{
        if(!aUser.equals("")){
            if(aUser.contains(".")){
                throw new WrongConnectionParameterException();
            }else{
                user = aUser;
            }
        }
    }

    public static void setPassword(String aPassword) throws WrongConnectionParameterException{
        if(!aPassword.equals("")){
            if(aPassword.contains(" ")){
                throw new WrongConnectionParameterException();
            }else{
                password = aPassword;
            }
        }
    }

    public static void setIP(String aIP) throws WrongConnectionParameterException{
        if(!aIP.equals("")){
            if(!aIP.contains(".")){
                throw new WrongConnectionParameterException();
            }else{
                ip = aIP;
            }
        }
    }

    public static void setPort(String aPort) throws WrongConnectionParameterException{
        if(!aPort.equals("")){
            int portint = Integer.parseInt(aPort);
            if(portint>9999||portint<0){
                throw new WrongConnectionParameterException();
            }else{
                port = portint;
            }
        }
    }
}
