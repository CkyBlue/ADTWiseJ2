package Implementations;

import Utility.Algorithm.Algorithm.TreeContent;
import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.Iterator;
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
            }
        };

        Iterator cmd2 = new Iterator("cmd2") {
            @Override
            protected boolean evaluate() {
                return variables("Locals").getInt("x") <= 10;
            }

            @Override
            protected void onExecution() {
            }
        };

        Command cmd3 = new Command("cmd3") {
            @Override
            protected void onExecution() {
                output(variables("Locals").getStrEqv("x"));
            }
        };

//        Tests.algorithm_1.setHead("cmd1");

        cmd1.chainTo(cmd2);
        cmd2.chainTo(cmd3);

        Tests.algorithm_1.setHead(cmd1);

        Tests.tree.setHeader(new Command("tree header") {
            @Override
            protected void onExecution() {
                getDataLayer().buildVariablesStack("Locals");
                output("Tree Header Executing");
            }
        });
        Tests.tree.addAlgorithm("Algorithm 1", algorithm_1);
    }
}
