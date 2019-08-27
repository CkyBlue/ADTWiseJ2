package Implementations;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.SubCommands.Call;
import Utility.Algorithm.SubCommands.DoWhile;
import Utility.Algorithm.SubCommands.EndFunction;
import Utility.Algorithm.SubCommands.EndIf;
import Utility.Algorithm.SubCommands.EndWhile;
import Utility.Algorithm.SubCommands.Function;
import Utility.Algorithm.SubCommands.IfOrElse;
import Utility.Algorithm.SubCommands.Input;
import Utility.Algorithm.Tree.Content;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;

public class Queue {
    public static Content algorithmTree = new Content();

    static {
        {
            final Command cmd_1 = new Command("cmd_1") {
                @Override
                protected void onExecution() {
                    buildVarStack("Locals");
                    buildVarStack("return");
                    buildVarStack("args");

                    // Building Code Units
                    buildSourceCodeUnits(new String[]{"Pseudo", "CarbonCode"});
                    String pseudo_str = "FUNCTION Remove() RETURNS STRING  \n" +
                            "                IF Head_Pointer = -1 THEN \n" +
                            "                                OUTPUT \"Error: Removal could not be performed as the Queue only contains free nodes.\" \n" +
                            "                ELSE \n" +
                            "                                Current_Pointer <- Head_Pointer \n" +
                            "                                Output_From_Queue <- Node_Array[Current_Pointer].Item \n" +
                            "                                Head_Pointer <- Node_Array[Current_Pointer].Pointer \n" +
                            "                                IF Current_Pointer = Tail_Pointer THEN \n" +
                            "                                                Tail_Pointer <- -1 \n" +
                            "                                ENDIF \n" +
                            "                                Node_Array[Current_Pointer].Pointer <- Free_Pointer \n" +
                            "                                Free_Pointer <- Current_Pointer \n" +
                            "                                OUTPUT Output_From_Queue \n" +
                            "                ENDIF \n" +
                            "ENDFUNCTION \n" +
                            "";
                    getSourceCodeUnit("Pseudo").setText(pseudo_str);
                    String carboncode_str = "<~        directive_type = \"src\", \n" +
                            "        location = \"pseudocode.txt\", \n" +
                            "        name = \"Pseudo\", \n" +
                            "        handler = \"ps\" ~> \n" +
                            " \n" +
                            "[**__init__**:  \n" +
                            "                BUILD: p, Pointers \n" +
                            "                 \n" +
                            "                DIM: List @ STR Data | INT Pointer @ 10 \n" +
                            "                 \n" +
                            "                DIM: INT head_pointer @ p \n" +
                            "                DIM: INT tail_pointer @ p \n" +
                            "                DIM: INT free_Pointer @ p  \n" +
                            "                DIM: INT current_pointer @ p \n" +
                            " \n" +
                            "                SET: head_pointer @ p, -1 \n" +
                            "                SET: free_Pointer @ p, 0 \n" +
                            "                SET: tail_pointer @ p, -1 \n" +
                            "                SET: current_pointer @ p, -1 \n" +
                            " \n" +
                            "                EVAL: for (int count = 0; count \\< <^List>.getSize(); count++) { \n" +
                            "                SET: List[Pointer][count], count + 1  \n" +
                            "                EVAL: } \n" +
                            " \n" +
                            "                SET: List[Pointer][<^List>.getSize()], -1 \n" +
                            "                LIGHT: ps @ 12 \n" +
                            "                ] \n" +
                            "[IMPASSE:] \n" +
                            "                 \n" +
                            "[FUNC: VOID Search (STR search_item):] \n" +
                            "        [: DIM: BOOL item_found] \n" +
                            "        [: SET: current_pointer @ p, <head_pointer @ p>] \n" +
                            "        [: SET: item_found, false] \n" +
                            "        [WHILE: (<current_pointer @ p> != -1 && ! <item_found>) :] \n" +
                            "                [IF: (<^List[Data][<current_pointer @ p>]> == <search_item>):] \n" +
                            "                        [: SET: item_found, true] \n" +
                            "                [ELSE:] \n" +
                            "                        [: SET: current_pointer @ p, <^List[Pointer][<current_pointer @ p>]>] \n" +
                            "                [ENDIF:] \n" +
                            "        [ENDWHILE:] \n" +
                            "        [IF: (! <item_found>):] \n" +
                            "                [:] \n" +
                            "        [ELSE:] \n" +
                            "                [:] \n" +
                            "        [ENDIF:] \n" +
                            "[ENDFUNC:] \n" +
                            " \n" +
                            "[**Search**:DIM: STR search_item] \n" +
                            "[INPUT: STR search_item:] \n" +
                            "[CALL: Search (<search_item>):] \n" +
                            "[IMPASSE:] \n" +
                            " \n" +
                            " \n" +
                            "[FUNC: VOID Insert (STR item):] \n" +
                            "        [IF: (<free_Pointer @ p> == -1) :] \n" +
                            "                [: OUTPUT: Queue has no free nodes. Insertion could not be performed.] \n" +
                            "        [ELSE:] \n" +
                            "                [: SET: current_pointer @ p, <free_Pointer @ p>] \n" +
                            "                [: SET: List[Data][<current_pointer @ p>], <item>] \n" +
                            "                [: SET: free_Pointer @ p, <^List[Pointer][<current_pointer @ p>]>] \n" +
                            "                [IF: (<tail_pointer @ p> != -1):] \n" +
                            "                        [: SET: List[Pointer][<tail_pointer @ p>], <current_pointer @ p>] \n" +
                            "                [ENDIF:] \n" +
                            "                [: SET: List[Pointer][<current_pointer @ p>], -1] \n" +
                            "                [: SET: tail_pointer @ p, <current_pointer @ p>] \n" +
                            "                [IF: (<head_pointer @ p> == -1):] \n" +
                            "                        [: SET: head_pointer @ p, <current_pointer @ p>] \n" +
                            "                [ENDIF:] \n" +
                            "        [ENDIF:] \n" +
                            "[ENDFUNC:] \n" +
                            " \n" +
                            "[**Insert**: DIM: STR item_to_be_inserted] \n" +
                            "[INPUT: STR item_to_be_inserted:] \n" +
                            "[CALL: Insert (<item_to_be_inserted>):] \n" +
                            "[IMPASSE:] \n" +
                            " \n" +
                            "[FUNC : STR Remove (STR item):] \n" +
                            "        [IF: (<head_pointer @ p> == -1):] \n" +
                            "                [: LOG: Queue is empty] \n" +
                            "                [RETURN: :]  \n" +
                            "        [ELSE:] \n" +
                            "                [: DIM: STR output] \n" +
                            "                [: SET: current_pointer @ p, <head_pointer @ p>] \n" +
                            "                [: SET: output, <^List[Pointer][<current_pointer @ p>]>] \n" +
                            "                [: SET: head_pointer @ p, <^List[Pointer][<current_pointer @ p>]>] \n" +
                            "                [IF: (<current_pointer @ p> == <tail_pointer @ p>):] \n" +
                            "                        [: SET: tail_pointer @ p, -1] \n" +
                            "                [ENDIF:] \n" +
                            "                [: SET: List[Pointer][<current_pointer @ p>], <free_Pointer @ p>] \n" +
                            "                [RETURN: <output> :] \n" +
                            "        [ENDIF:] \n" +
                            "[ENDFUNC:] \n" +
                            " \n" +
                            "[**Remove**: DIM: STR item_to_be_removed] \n" +
                            "[INPUT: STR item_to_be_removed:] \n" +
                            "[CALL: Remove (<item_to_be_removed>):] \n" +
                            "[: LOG: <*RETURN>:] \n" +
                            "[IMPASSE:] \n" +
                            "";
                    getSourceCodeUnit("CarbonCode").setText(carboncode_str);

                    getSourceCodeUnit("CarbonCode").highlight(new int[]{16, 8, 11, 21, 17, 10, 14, 22, 6, 20, 15, 19, 23, 13, 24, 9, 18, 26, 7, 25, 27, 12});

                    buildVarStack("Pointers");

                    BluePrint listBluePrint = new BluePrint();
                    listBluePrint.addKey("Data", Type.STRING);
                    listBluePrint.addKey("Pointer", Type.INTEGER);
                    buildNodesStack("List", listBluePrint, 10);

                    variables("Pointers").declareVariable("head_pointer", Type.INTEGER);
                    variables("Pointers").declareVariable("tail_pointer", Type.INTEGER);
                    variables("Pointers").declareVariable("free_Pointer", Type.INTEGER);
                    variables("Pointers").declareVariable("current_pointer", Type.INTEGER);

                    variables("Pointers").set("head_pointer", -1);
                    variables("Pointers").set("free_Pointer", 0);
                    variables("Pointers").set("tail_pointer", -1);
                    variables("Pointers").set("current_pointer", -1);

                    for (int count = 0; count < nodes("List").getSize(); count++) {
                        ;
                        nodes("List").set("Pointer", count, count + 1);
                    }
                    ;

                    nodes("List").set("Pointer", nodes("List").getSize() - 1, -1);
                    getSourceCodeUnit("Pseudo").highlight(new int[]{12});

                }
            };

            algorithmTree.setInitializer(cmd_1);

            final Command impasse_2 = new Command("impasse_2") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_3 = new Function("func_3") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{30});

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("search_item", Type.STRING);

                    variables("Locals").set("search_item", variables("args").getStr("search_item"));

                }
            };

            func_3.setIdentifier("Search");

            final Command cmd_4 = new Command("cmd_4") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{31});

                    variables("Locals").declareVariable("item_found", Type.BOOLEAN);
                }
            };

            final Command cmd_5 = new Command("cmd_5") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{32});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                }
            };

            final Command cmd_6 = new Command("cmd_6") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{33});

                    variables("Locals").set("item_found", false);
                }
            };

            final DoWhile while_7 = new DoWhile("while_7") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{34});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") != -1 && !variables("Locals").getBool("item_found");
                }
            };

            final IfOrElse if_8 = new IfOrElse("if_8") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{35});

                }

                @Override
                protected boolean evaluate() {
                    return nodes("List").getStr("Data", variables("Pointers").getInt("current_pointer")).equals(variables("Locals").getStr("search_item"));
                }
            };

            final Command cmd_9 = new Command("cmd_9") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{36});

                    variables("Locals").set("item_found", true);
                }
            };

            final Command else_10 = new Command("else_10") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{37});

                }
            };

            final Command cmd_11 = new Command("cmd_11") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{38});

                    variables("Pointers").set("current_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                }
            };

            final EndIf endif_12 = new EndIf("endif_12") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{39});

                }
            };

            final EndWhile endwhile_13 = new EndWhile("endwhile_13") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{40});

                }
            };

            final IfOrElse if_14 = new IfOrElse("if_14") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{41});

                }

                @Override
                protected boolean evaluate() {
                    return !variables("Locals").getBool("item_found");
                }
            };

            final Command cmd_15 = new Command("cmd_15") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{42});

                }
            };

            final Command else_16 = new Command("else_16") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{43});

                }
            };

            final Command cmd_17 = new Command("cmd_17") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{44});

                }
            };

            final EndIf endif_18 = new EndIf("endif_18") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{45});

                }
            };

            final EndFunction endfunc_19 = new EndFunction("endfunc_19") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{46});

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Locals");
                    popVarStack("args");
                }
            };

            final Command cmd_20 = new Command("cmd_20") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{48});

                    variables("Locals").declareVariable("search_item", Type.STRING);
                }
            };

            algorithmTree.addAlgorithmHeader("Search", cmd_20);

            final Input input_21 = new Input("input_21", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{49});

                    variables("Locals").set("search_item", String.valueOf(getInputContent()));

                }
            };

            final Call call_22 = new Call("call_22") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{50});

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("args");
                    variables("args").declareVariable("search_item", Type.STRING);
                }

                @Override
                protected void passArgs() {
                    variables("args").set("search_item", variables("Locals").getStr("search_item"));
                }
            };

            call_22.setFunction(func_3);

            final Command impasse_23 = new Command("impasse_23") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_24 = new Function("func_24") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{54});

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("item", Type.STRING);

                    variables("Locals").set("item", variables("args").getStr("item"));

                }
            };

            func_24.setIdentifier("Insert");

            final IfOrElse if_25 = new IfOrElse("if_25") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{55});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("free_Pointer") == -1;
                }
            };

            final Command cmd_26 = new Command("cmd_26") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{56});

                    output("Queue has no free nodes. Insertion could not be performed.");
                }
            };

            final Command else_27 = new Command("else_27") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{57});

                }
            };

            final Command cmd_28 = new Command("cmd_28") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{58});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("free_Pointer"));
                }
            };

            final Command cmd_29 = new Command("cmd_29") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{59});

                    nodes("List").set("Data", variables("Pointers").getInt("current_pointer"), variables("Locals").getStr("item"));
                }
            };

            final Command cmd_30 = new Command("cmd_30") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{60});

                    variables("Pointers").set("free_Pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                }
            };

            final IfOrElse if_31 = new IfOrElse("if_31") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{61});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("tail_pointer") != -1;
                }
            };

            final Command cmd_32 = new Command("cmd_32") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{62});

                    nodes("List").set("Pointer", variables("Pointers").getInt("tail_pointer"), variables("Pointers").getInt("current_pointer"));
                }
            };

            final EndIf endif_33 = new EndIf("endif_33") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{63});

                }
            };

            final Command cmd_34 = new Command("cmd_34") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{64});

                    nodes("List").set("Pointer", variables("Pointers").getInt("current_pointer"), -1);
                }
            };

            final Command cmd_35 = new Command("cmd_35") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{65});

                    variables("Pointers").set("tail_pointer", variables("Pointers").getInt("current_pointer"));
                }
            };

            final IfOrElse if_36 = new IfOrElse("if_36") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{66});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("head_pointer") == -1;
                }
            };

            final Command cmd_37 = new Command("cmd_37") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{67});

                    variables("Pointers").set("head_pointer", variables("Pointers").getInt("current_pointer"));
                }
            };

            final EndIf endif_38 = new EndIf("endif_38") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{68});

                }
            };

            final EndIf endif_39 = new EndIf("endif_39") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{69});

                }
            };

            final EndFunction endfunc_40 = new EndFunction("endfunc_40") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{70});

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Locals");
                    popVarStack("args");
                }
            };

            final Command cmd_41 = new Command("cmd_41") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{72});

                    variables("Locals").declareVariable("item_to_be_inserted", Type.STRING);
                }
            };

            algorithmTree.addAlgorithmHeader("Insert", cmd_41);

            final Input input_42 = new Input("input_42", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{73});

                    variables("Locals").set("item_to_be_inserted", String.valueOf(getInputContent()));

                }
            };

            final Call call_43 = new Call("call_43") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{74});

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("args");
                    variables("args").declareVariable("item", Type.STRING);
                }

                @Override
                protected void passArgs() {
                    variables("args").set("item", variables("Locals").getStr("item_to_be_inserted"));
                }
            };

            call_43.setFunction(func_24);

            final Command impasse_44 = new Command("impasse_44") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_45 = new Function("func_45") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{77});

                    variables("return").set("Remove", "");

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("item", Type.STRING);

                    variables("Locals").set("item", variables("args").getStr("item"));

                }
            };

            func_45.setIdentifier("Remove");

            final IfOrElse if_46 = new IfOrElse("if_46") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{78});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("head_pointer") == -1;
                }
            };

            final Command cmd_47 = new Command("cmd_47") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{79});

                    log("Queue is empty");
                }
            };

            final Command return_48 = new Command("return_48") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{80});

                    variables("return").set("Remove", "");

                }
            };

            final Command else_49 = new Command("else_49") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{81});

                }
            };

            final Command cmd_50 = new Command("cmd_50") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{82});

                    variables("Locals").declareVariable("output", Type.STRING);
                }
            };

            final Command cmd_51 = new Command("cmd_51") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{83});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                }
            };

            final Command cmd_52 = new Command("cmd_52") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{84});

                    variables("Locals").set("output", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                }
            };

            final Command cmd_53 = new Command("cmd_53") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{85});

                    variables("Pointers").set("head_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                }
            };

            final IfOrElse if_54 = new IfOrElse("if_54") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{86});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") == variables("Pointers").getInt("tail_pointer");
                }
            };

            final Command cmd_55 = new Command("cmd_55") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{87});

                    variables("Pointers").set("tail_pointer", -1);
                }
            };

            final EndIf endif_56 = new EndIf("endif_56") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{88});

                }
            };

            final Command cmd_57 = new Command("cmd_57") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{89});

                    nodes("List").set("Pointer", variables("Pointers").getInt("current_pointer"), variables("Pointers").getInt("free_Pointer"));
                }
            };

            final Command return_58 = new Command("return_58") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{90});

                    variables("return").set("Remove", variables("Locals").getStr("output"));

                }
            };

            final EndIf endif_59 = new EndIf("endif_59") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{91});

                }
            };

            final EndFunction endfunc_60 = new EndFunction("endfunc_60") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{92});

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Locals");
                    popVarStack("args");
                }
            };

            final Command cmd_61 = new Command("cmd_61") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{94});

                    variables("Locals").declareVariable("item_to_be_removed", Type.STRING);
                }
            };

            algorithmTree.addAlgorithmHeader("Remove", cmd_61);

            final Input input_62 = new Input("input_62", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{95});

                    variables("Locals").set("item_to_be_removed", String.valueOf(getInputContent()));

                }
            };

            final Call call_63 = new Call("call_63") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{96});

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("args");
                    variables("args").declareVariable("item", Type.STRING);
                }

                @Override
                protected void passArgs() {
                    variables("args").set("item", variables("Locals").getStr("item_to_be_removed"));
                }
            };

            call_63.setFunction(func_45);

            final Command cmd_64 = new Command("cmd_64") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{97});

                    log("" + variables("return").getStr("Remove") + ":");
                }
            };

            final Command impasse_65 = new Command("impasse_65") {
                @Override
                protected void onExecution() {
                }
            };

            // Chaining
            func_3.chainTo(cmd_4);
            cmd_4.chainTo(cmd_5);
            cmd_5.chainTo(cmd_6);
            cmd_6.chainTo(while_7);
            while_7.chainTo(if_8);
            if_8.chainTo(cmd_9);

            if_8.elseChainTo(else_10);
            else_10.chainTo(cmd_11);

            if_8.endTo(endif_12);
            while_7.blockChainTo(if_14);
            while_7.endTo(endwhile_13);
            if_14.chainTo(cmd_15);

            if_14.elseChainTo(else_16);
            else_16.chainTo(cmd_17);

            if_14.endTo(endif_18);
            func_3.endTo(endfunc_19);

            func_24.chainTo(if_25);
            if_25.chainTo(cmd_26);

            if_25.elseChainTo(else_27);
            else_27.chainTo(cmd_28);
            cmd_28.chainTo(cmd_29);
            cmd_29.chainTo(cmd_30);
            cmd_30.chainTo(if_31);
            if_31.chainTo(cmd_32);

            if_31.endTo(endif_33);
            if_31.blockChainTo(cmd_34);
            cmd_34.chainTo(cmd_35);
            cmd_35.chainTo(if_36);
            if_36.chainTo(cmd_37);

            if_36.endTo(endif_38);
            if_25.endTo(endif_39);
            func_24.endTo(endfunc_40);

            func_45.chainTo(if_46);
            if_46.chainTo(cmd_47);
            cmd_47.chainTo(return_48);

            if_46.elseChainTo(else_49);
            else_49.chainTo(cmd_50);
            cmd_50.chainTo(cmd_51);
            cmd_51.chainTo(cmd_52);
            cmd_52.chainTo(cmd_53);
            cmd_53.chainTo(if_54);
            if_54.chainTo(cmd_55);

            if_54.endTo(endif_56);
            if_54.blockChainTo(cmd_57);
            cmd_57.chainTo(return_58);

            if_46.endTo(endif_59);
            func_45.endTo(endfunc_60);

            cmd_20.chainTo(input_21);
            input_21.chainTo(call_22);

            cmd_41.chainTo(input_42);
            input_42.chainTo(call_43);

            cmd_61.chainTo(input_62);
            input_62.chainTo(call_63);
            call_63.chainTo(cmd_64);
        }
    }
}
