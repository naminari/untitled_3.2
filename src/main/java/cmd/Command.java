package cmd;

import exceptions.ValidException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface Command {
    ActionResult action(CmdArgs args) throws FileNotFoundException, ValidException, InvocationTargetException, IllegalAccessException;
    String getName();
    String getDescription();
    CmdType getCmdType();
}
