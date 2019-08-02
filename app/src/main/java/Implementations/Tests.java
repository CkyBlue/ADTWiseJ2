package Implementations;

import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.Iterator;
import Utility.Algorithm.Algorithm.TreeContent;
import Utility.Data.Type;

public class Tests {
    public static TreeContent tree = new TreeContent();
    public static Utility.Algorithm.Algorithm.Content algorithm_1 = new Utility.Algorithm.Algorithm.Content();

    static {
        Command cmd1 = new Command("cmd1") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("x", Type.INTEGER);
                variables("Locals").set("x",  0);

                chainTo("cmd2");
            }
        };

        Iterator cmd2 = new Iterator("cmd2") {
            @Override
            protected boolean evaluate() {
                return variables("Locals").getInt("x") <= 10;
            }

            @Override
            protected void onExecution() {
                chainTo("cmd3");
            }
        };

        Command cmd3 = new Command("cmd3") {
            @Override
            protected void onExecution() {
                output(variables("Locals").getStrEqv("x"));
            }
        };

        Tests.algorithm_1.setHead("cmd1");

        Tests.algorithm_1.addCommand(cmd1);
        Tests.algorithm_1.addCommand(cmd2);
        Tests.algorithm_1.addCommand(cmd3);

        Tests.tree.addAlgorithm("Algorithm 1", algorithm_1);
    }
}
