package cmd;

import common.CmdResponse;
import common.Request;
import common.Response;
import exceptions.CmdArgsAmountException;
import exceptions.ExecuteException;
import exceptions.ValidException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class
CmdHandler {
    private final HashMap<String, Command> cmds;
    private final LinkedList<Command> cmdHistory;

    public CmdHandler(){
        this.cmds =new HashMap<>();
        this.cmdHistory = new LinkedList<>();
        for (int i=0; i<5; i++){
            cmdHistory.add(null);
        }
    }
    public void addComm(Command c){
        final String name = c.getName();
        if (!isInCmds(name)){
            this.cmds.put(name, c);
        }
    }
    public boolean isInCmds(String name){
        return this.cmds.containsKey(name);
    }
    public void addCmds(Command ... comms){
        for (Command c: comms){
            addComm(c);
        }
    }
    public HashMap<String, Command> getCmds(){
        return cmds;
    }
    public LinkedList<Command> getCmdHistory(){
        return cmdHistory;
    }
    public CmdResponse executeCmd(Request request) throws CmdArgsAmountException, ExecuteException, FileNotFoundException, ValidException, InvocationTargetException, IllegalAccessException {
        Command cmd = request.getCmd();
        CmdArgs cmdArgs = request.getCmdArgs();
        if (cmd.getCmdType() == CmdType.NO_ARGS && !Objects.equals(cmdArgs.getArgs()[0], "")){
            throw new CmdArgsAmountException("Command \""+cmd.getName()+"\" does not need arguments!");
        } else if (cmd.getCmdType() == CmdType.SIMPLE_ARG){
            throw new CmdArgsAmountException(cmd.getName()+" needs an argument!");
        }
        ActionResult result = cmd.action(cmdArgs);
        cmdHistory.addLast(cmd);
        cmdHistory.removeFirst();
        return new CmdResponse(result);
    }
    
}
