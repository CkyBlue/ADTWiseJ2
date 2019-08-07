package Implementations;

import java.util.HashSet;
import java.util.Set;

import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.DoWhile;
import Utility.Algorithm.Tree.Content;
import Utility.Data.Type;

public class Tests {
    public static Content tree = new Content();
    public static Utility.Algorithm.Algorithm.Content algorithm_1 = new Utility.Algorithm.Algorithm.Content();
    public static Utility.Algorithm.Algorithm.Content algorithm_2 = new Utility.Algorithm.Algorithm.Content();

    static {
        Tests.tree.setInitializer(new Command("tree header") {
            @Override
            protected void onExecution() {
                getDataLayer().buildVariablesStack("Locals");
                output("Tree Header Executing");
            }
        });
    }

    static {
        Command cmd1 = new Command("cmd1") {
            @Override
            protected void onExecution() {
                log("Beginning algorithm");

                Set<String> units = new HashSet<>();
                units.add("Pseudocode");

                getSourceCodeLayer().buildUnits(units);

                StringBuilder pseudocode = new StringBuilder();

                pseudocode.append("DECLARE x : Integer\n");
                pseudocode.append("WHILE x <= 10:\n");
                pseudocode.append("  OUTPUT x\n");
                pseudocode.append("  x = x + 1\n");
                pseudocode.append("ENDWHILE");

                getSourceCodeLayer().getUnitFeed("Pseudocode").getContent().setText(pseudocode.toString());

                variables("Locals").declareVariable("x", Type.INTEGER);
                variables("Locals").set("x", 0);

                getSourceCodeLayer().getUnitFeed("Pseudocode").getContent().highlight(new int[]{1});
            }
        };

        DoWhile cmd2 = new DoWhile("cmd2") {
            @Override
            protected boolean evaluate() {
                return variables("Locals").getInt("x") <= 10;
            }

            @Override
            protected void onExecution() {
                getSourceCodeLayer().getUnitFeed("Pseudocode").getContent().highlight(new int[]{2});
            }
        };

        Command cmd3 = new Command("cmd3") {
            @Override
            protected void onExecution() {
                output(variables("Locals").getStrEqv("x"));
                variables("Locals").set("x", variables("Locals").getInt("x") + 1);

                getSourceCodeLayer().getUnitFeed("Pseudocode").getContent().highlight(new int[]{3, 4});
            }
        };

//        Tests.algorithm_1.setHead("cmd1");

        cmd1.chainTo(cmd2);
        cmd2.chainTo(cmd3);

        Tests.algorithm_1.setHead(cmd1);
        Tests.tree.addAlgorithm("Algorithm 1", algorithm_1);
    }

    static {
        final String py = "Python";
        final String ps = "Pseudocode";

        Command cmd1 = new Command("cmd1") {
            @Override
            protected void onExecution() {

                Set<String> sourceCodeKeys = new HashSet<>();
                sourceCodeKeys.add(py);
                sourceCodeKeys.add(ps);

                getSourceCodeLayer().buildUnits(sourceCodeKeys);

                String pythonCode = "x = 1\n" +
                        "while x <= 10:\n" +
                        "    print(\"2 x \" + String.valueOf(x) + \" = \" +  String.valueOf(x * 2))\n" +
                        "    x = x + 1";

                String pseudoCode = "DECLARE x : INTEGER\n" +
                        "x <= 1\n" +
                        "\n" +
                        "WHILE x <= 10:\n" +
                        "    OUTPUT \"2 x \" + String.valueOf(x) + \" = \" +  String.valueOf(x * 2)\n" +
                        "    x <= x + 1\n" +
                        "ENDWHILE";

                getSourceCodeLayer().getUnitFeed(py).getContent().setText(pythonCode);
                getSourceCodeLayer().getUnitFeed(ps).getContent().setText(pseudoCode);

                getSourceCodeLayer().getUnitFeed(py).getContent().highlight(new int[]{1});
                getSourceCodeLayer().getUnitFeed(ps).getContent().highlight(new int[]{1, 2});

                variables("Locals").declareVariable("x", Type.INTEGER);
                variables("Locals").set("x", 1);
            }
        };

        DoWhile cmd2 = new DoWhile("cmd2") {
            @Override
            protected boolean evaluate() {
                return variables("Locals").getInt("x") <= 10;
            }

            @Override
            protected void onExecution() {
                getSourceCodeLayer().getUnitFeed(py).getContent().highlight(new int[]{2});
                getSourceCodeLayer().getUnitFeed(ps).getContent().highlight(new int[]{4});
            }
        };

        Command cmd3 = new Command("cmd3") {
            @Override
            protected void onExecution() {
                output("2 x " + String.valueOf(variables("Locals").getInt("x")) + " = " + String.valueOf(variables("Locals").getInt("x") * 2));
                variables("Locals").set("x", variables("Locals").getInt("x") + 1);

                getSourceCodeLayer().getUnitFeed(py).getContent().highlight(new int[]{3, 4});
                getSourceCodeLayer().getUnitFeed(ps).getContent().highlight(new int[]{5, 6});

            }
        };

        Command cmd4 = new Command("cmd4") {
            @Override
            protected void onExecution() {
                getSourceCodeLayer().getUnitFeed(ps).getContent().highlight(new int[]{7});

                output("After iterator finished");
            }
        };

        cmd1.chainTo(cmd2);
        cmd2.chainTo(cmd3);
        cmd2.blockChainTo(cmd4);

        Tests.algorithm_2.setHead(cmd1);
        Tests.tree.addAlgorithm("Algorithm 2", algorithm_2);
    }
}
