/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bashscriptbuilder;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author rickaguayo
 */
public class BashScriptBuilder {
    private static List<String> lstCmds;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
               
        Command cmdPwd = context.getBean(Pwd.class);    
        Command cmdCd = context.getBean(Cd.class);
        Command cmdLs = context.getBean(Ls.class);
        Command cmdEcho = context.getBean(Echo.class);
        Command cmdTouch = context.getBean(Touch.class);
        Command cmdCat = context.getBean(Cat.class);
        
        lstCmds = new ArrayList<>();
        
        cmdPwd.setCmd("pwd");   
        lstCmds.add(cmdPwd.getCmd());
        
        cmdCd.setCmd("cd ../");       
        lstCmds.add(cmdCd.getCmd());

        cmdLs.setCmd("ls -la");     
        lstCmds.add(cmdLs.getCmd());
     
        cmdCd.setCmd("cd /etc");  
        lstCmds.add(cmdCd.getCmd());  
        
        cmdEcho.setCmd("echo Thanks Spring for managing my beans!");
        lstCmds.add(cmdEcho.getCmd());
        
        cmdTouch.setCmd("touch /tmp/exfiltrate.txt");
        lstCmds.add(cmdTouch.getCmd());
        
        cmdCat.setCmd("cat passwd");
        lstCmds.add(cmdCat.getCmd());

        printScript();
    }  
    
    private static void printScript() {
        System.out.println("#! /bin/bash");
        for(String strCmd : lstCmds) {
            System.out.println(strCmd);
        }
    }
}
