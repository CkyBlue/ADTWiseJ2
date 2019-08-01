package Implementations;

import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.Iterator;
import Utility.Algorithm.Tree.Content;
import Utility.Data.Type;

public class Tests {
    public Content tree = new Content();
    public Utility.Algorithm.Algorithm.Content algorithm_1 = new Utility.Algorithm.Algorithm.Content();

    {
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

        this.algorithm_1.setHead("cmd1");

        this.algorithm_1.addCommand(cmd1);
        this.algorithm_1.addCommand(cmd2);
        this.algorithm_1.addCommand(cmd3);

        this.tree.addAlgorithm();
    }
}
