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

    private static String user = "mac";
    private static String password = "!QAYse4rfv";
    private static String ip = "127.0.0.1";
    private static int port = 3306;
    private static String databaseGermanWords = "MNGermanWords";
    private static String databaseGermanRules = "MNGermanRules";
    private static String databasePolishWords = "MNPolishWords";
    private static String databasePolishRules = "MNPolishRules";
    private static String urlWords = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanWords;
    private static String urlRules = "jdbc:mysql://"+ip+":"+String.valueOf(port)+"/"+databaseGermanRules;
    private static Map propertiesWordsGER = new HashMap();
    private static Map propertiesRulesGER = new HashMap();
    private static Map propertiesWordsPOL = new HashMap();
    private static Map propertiesRulesPOL = new HashMap();

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
