package api;

import java.util.Arrays;
import java.util.Scanner;

/**
 * this is a implementation if the java.util.Scanner that is capable of getting a date from a pattern,
 * and is also able to handle the implementaiton of {@Link IStopable}.
 */
public class InternalScanner {
    private final Scanner scanner;
    private final IProgram IProgram;

    public InternalScanner(Scanner scanner, IProgram IProgram) {
        this.scanner = scanner;
        this.IProgram = IProgram;
    }

    /**
     * @param invalidInputMessage message that gets sent if the enterd value is invalid.
     * @return returns the next Double that is entered.
     */
    public Double nextDouble(String invalidInputMessage) {
        while (true) {
            var r = scanner.next();
            if (IProgram instanceof IStopable && r.equalsIgnoreCase("stop")) {
                IStopable program = (IStopable) this.IProgram;
                program.stop();
                break;
            }

            try {
                return Double.parseDouble(r);
            }catch (NumberFormatException ex) {
                System.out.println(invalidInputMessage + "\n" + ex.getMessage());
            }
        }
        return null;
    }

    /**
     * if the IProgram class implements the IStopable Interface is true, "stop" can not be used as a input string because it is used to stop the IProgram.
     * @return the next String that the User put in the Console.
     */
    public String next() {
        String next = scanner.next();
        if (IProgram instanceof IStopable) {
            if (next.equalsIgnoreCase("stop")) {
                IStopable program = (IStopable) this.IProgram;
                program.stop();
                return null;
            }
        }
        return next;
    }


    /**
     * if the IProgram class implements the IStopable Interface is true, "stop" can not be used as a input string because it is used to stop the IProgram.
     * @return the next String that the User put in the Console.
     */
    public String next(String invalidInputMessage, String... enumValues) {
        while (true) {
            var r = scanner.next();
            if (IProgram instanceof IStopable && r.equalsIgnoreCase("stop")) {
                IStopable program = (IStopable) this.IProgram;
                program.stop();
                break;
            }

            if (Arrays.asList(enumValues).contains(r)) {
                return r;
            }else {
                System.out.println(invalidInputMessage);
            }
        }
        return null;
    }
}
