/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bashscriptbuilder;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author rickaguayo
 */
//@Lazy
@Component
//@Primary
public class Ls implements Command {
    
    private String cmd;

    @Override
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String getCmd() {
        return this.cmd;
    }
    
}
