/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bashscriptbuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public static void main(String[] args) throws IOException {
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
        createBashScript("commands.sh");
    }  
    
    private static void printScript() {
        System.out.println("#! /bin/bash");
        for(String strCmd : lstCmds) {
            System.out.println(strCmd);
        }
    }
    
    private static void createBashScript(String filename) throws IOException {
        File file = new File(filename);
        
        FileWriter writer = new FileWriter(file);
        writer.write("#! /bin/bash\n");
        for(String strCmd : lstCmds) {
            writer.write(strCmd + "\n");
        }        
        writer.close();
        
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(Paths.get(file.getPath()), perms);
    }
}
