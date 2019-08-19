/*
package Implementations;

import java.util.HashSet;
import java.util.Set;

import Utility.Algorithm.Commands.Call;
import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Commands.DoWhile;
import Utility.Algorithm.Commands.EndFunction;
import Utility.Algorithm.Commands.EndIf;
import Utility.Algorithm.Commands.EndWhile;
import Utility.Algorithm.Commands.Function;
import Utility.Algorithm.Commands.IfOrElse;
import Utility.Algorithm.Commands.Input;
import Utility.Algorithm.Commands.Return;
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
                getDataLayer().buildVarStack("Locals");
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

    {
*/
/*        Call call = new Call() {
            @Override
            protected void buildArgs() {

            }

            @Override
            protected void passArgs() {

            }

            @Override
            protected Object fetchReturn() {

            }

            @Override
            protected void onExecution() {

            }
        };

        Command command = new Command() {
            @Override
            protected void onExecution() {

            }
        };

        DoWhile doWhile = new DoWhile() {
            @Override
            protected boolean evaluate() {
                return false;
            }

            @Override
            protected void onExecution() {

            }
        };

        EndFunction endFunction = new EndFunction() {
            @Override
            protected void destroyLocalData() {

            }

            @Override
            protected void onExecution() {

            }
        };

        EndIf endIf = new EndIf() {
            @Override
            protected void onExecution() {

            }
        };

        EndWhile endWhile = new EndWhile() {
            @Override
            protected void onExecution() {

            }
        };

        Function function = new Function() {
            @Override
            protected void buildLocalData() {

            }

            @Override
            protected void onExecution() {

            }
        }

        IfOrElse ifOrElse = new IfOrElse() {
            @Override
            protected boolean evaluate() {
                return false;
            }

            @Override
            protected void onExecution() {

            }
        };

        Input input = new Input() {
            @Override
            protected void onExecution() {

            }
        };

        Return re = new Return() {
            @Override
            protected void postReturn() {

            }
        };*//*



    }
}
*/
