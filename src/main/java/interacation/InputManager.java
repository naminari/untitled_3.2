package interacation;

import cmd.*;
import common.CmdRequest;
import common.CmdResponse;
import common.Response;
import exceptions.CmdArgsAmountException;
import exceptions.ExecuteException;
import exceptions.ValidException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class InputManager {
    private final CmdHandler cmdHandler;
    private final Scanner scanner = new Scanner(System.in);

    public InputManager(CmdHandler cmdHandler) {
        this.cmdHandler = cmdHandler;
    }

    private String[] getUserInput() {
        String[] input = null;
        while (Objects.isNull(input)) {
            System.out.println("Ведите команду: \n > ");
            String line = null;
            try {
                line = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            if (!line.isEmpty()) {
                input = line.split(" ", 2);
            } else {
                System.out.println("Uncorrected input");
            }
        }
        return input;
    }

    public void run() throws CmdArgsAmountException, FileNotFoundException, ExecuteException, ValidException, InvocationTargetException, IllegalAccessException, NullPointerException {
        while (true) {
//            try {

            String[] args = getUserInput();
            Command command = cmdHandler.getCmds().get(args[0]);
            List<String> list = new ArrayList<>(Arrays.asList(args));
            list.remove(0);
            if (Objects.isNull(command)) {
                System.err.print("Такой комманды нет \n");
            } else {
                Response result;
                try {
                    //result = command.action(new CmdArgs(list.toArray(new String[0])));
                    result = cmdHandler.executeCmd(new CmdRequest(command, new CmdArgs(list.toArray(new String[0]))));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Введите аргументы");
                    continue;
                }
                boolean isSuccess = result.getActionResult().isSuccess();
                if (isSuccess) {
                    String message = result.getActionResult().getMessage();
                    System.out.println(message);

                }
            }
        }
    }
}