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
import Utility.Algorithm.SubCommands.Return;
import Utility.Algorithm.Tree.Content;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;

public class Queue {
    public static Content algorithmTree;

    //Queue definition
    {
//        static {
//        //Commands
//        final Command cmd_1 = new Command("cmd_1") {
//            @Override
//            protected void onExecution() {
//                buildVarStack("Locals");
//                buildVarStack("Pointers");
//                buildVarStack("args");
//                buildVarStack("return");
//
//                BluePrint listBluePrint = new BluePrint();
//                listBluePrint.addKey("Data", Type.STRING);
//                listBluePrint.addKey("Pointer", Type.INTEGER);
//                buildNodesStack("List", listBluePrint, 10);
//
//                variables("Pointers").declareVariable("head_pointer", Type.INTEGER);
//                variables("Pointers").declareVariable("tail_pointer", Type.INTEGER);
//                variables("Pointers").declareVariable("current_pointer", Type.INTEGER);
//
//                variables("Pointers").set("head_pointer", -1);
//                variables("Pointers").set("tail_pointer", -1);
//                variables("Pointers").set("current_pointer", -1);
//
//                String carbonCode = "**__init__**[CMD: {BUILD: l, Locals \n " +
//                        "		BUILD: p, Pointers \n " +
//                        "		BUILD: a, args \n " +
//                        "		BUILD: r, return \n " +
//                        " \n " +
//                        "		DIM: List @ STR Data | INT Pointer @ 10 \n " +
//                        "		DIM: INT head_pointer @ p \n " +
//                        "		DIM: INT tail_pointer @ p  \n " +
//                        "		DIM: INT current_pointer @ p \n " +
//                        " \n " +
//                        "		SET: head_pointer @ p, -1 \n " +
//                        "		SET: tail_pointer @ p, -1 \n " +
//                        "		SET: current_pointer @ p, -1} \n " +
//                        " \n " +
//                        "		/*Building variables and data structure to be used to work a Queue ADT.*/ :] \n " +
//                        "[FUNC: VOID Search: (STR search_item):] \n " +
//                        "	[CMD: {DIM: BOOL item_found}:] \n " +
//                        "	[CMD: {SET: current_pointer @ p, <head_pointer @ p>}:] \n " +
//                        "	[CMD: {SET: item_found, false}:] \n " +
//                        "	[WHILE: (<current_pointer @ p> != -1 && not <item_found>):] \n " +
//                        "		[IF: (<^List[Data][<current_pointer @ p>]> == <search_item>):] \n " +
//                        "			[CMD: {SET: item_found, true}:] \n " +
//                        "		[ELSE::] \n " +
//                        "			[CMD: {SET: current_pointer @ p, <^List[Pointer][<current_pointer @ p>]>}:] \n " +
//                        "		[ENDIF::] \n " +
//                        "	[ENDWHILE::] \n " +
//                        "	[IF: (not <item_found>):] \n " +
//                        "		[CMD: \\*No matching item was found.*\\:] \n " +
//                        "	[ELSE::] \n " +
//                        "		[CMD: \\*Matching item was found at index <current_pointer @ p>*\\:] \n " +
//                        "	[ENDIF::] \n " +
//                        "[ENDFUNC::] \n " +
//                        " \n " +
//                        "**Search**[: {DIM: STR search_item}:] \n " +
//                        "[INPUT: STR: (search_item):] \n " +
//                        "[CALL: Search: (<search_item>):] \n " +
//                        "";
//
//
//                buildSourceCodeUnits(new String[]{"Carbon Code"});
//                getSourceCodeUnit("Carbon Code").setText(carbonCode);
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
//            }
//        };
//
//        final Function func_2 = new Function("func_2") {
//            @Override
//            protected void buildLocalData() {
//                pushVarStack("Locals");
//                variables("Locals").declareVariable("search_item", Type.STRING);
//
//                variables("Locals").set("search_item", variables("args").getStr("search_item"));
//
//
//            }
//
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{16});
//            }
//        };
//        func_2.setIdentifier("Search");
//
//        final Command cmd_3 = new Command("cmd_3") {
//            @Override
//            protected void onExecution() {
//                variables("Locals").declareVariable("item_found", Type.BOOLEAN);
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{17});
//            }
//        };
//
//        final Command cmd_4 = new Command("cmd_4") {
//            @Override
//            protected void onExecution() {
//                variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{18});
//            }
//        };
//
//        final Command cmd_5 = new Command("cmd_5") {
//            @Override
//            protected void onExecution() {
//                variables("Locals").set("item_found", false);
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{19});
//            }
//        };
//
//        final DoWhile while_6 = new DoWhile("while_6") {
//            @Override
//            protected boolean evaluate() {
//                return variables("Pointers").getInt("current_pointer") != -1 && !variables("Locals").getBool("item_found");
//            }
//
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{20});
//            }
//        };
//
//        final IfOrElse if_7 = new IfOrElse("if_7") {
//            @Override
//            protected boolean evaluate() {
//                return nodes("List").getStr("Data", variables("Pointers").getInt("current_pointer")).equals(variables("Locals").getStr("search_item"));
//            }
//
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{21});
//            }
//        };
//
//        final Command cmd_8 = new Command("cmd_8") {
//            @Override
//            protected void onExecution() {
//                variables("Locals").set("item_found", true);
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{22});
//            }
//        };
//
//        final Command else_9 = new Command("else_9") {
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{23});
//            }
//        };
//
//        final Command cmd_10 = new Command("cmd_10") {
//            @Override
//            protected void onExecution() {
//                variables("Pointers").set("current_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{24});
//            }
//        };
//
//        final EndIf endif_11 = new EndIf("endif_11") {
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{25});
//            }
//        };
//
//        final EndWhile endwhile_12 = new EndWhile("endwhile_12") {
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{26});
//            }
//        };
//
//        final IfOrElse if_13 = new IfOrElse("if_13") {
//            @Override
//            protected boolean evaluate() {
//                return !variables("Locals").getBool("item_found");
//            }
//
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{27});
//            }
//        };
//
//        final Command cmd_14 = new Command("cmd_14") {
//            @Override
//            protected void onExecution() {
//
//                log("No matching item was found.");
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{28});
//            }
//        };
//
//        final Command else_15 = new Command("else_15") {
//            @Override
//            protected void onExecution() {
//
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{23});
//            }
//        };
//
//        final Command cmd_16 = new Command("cmd_16") {
//            @Override
//            protected void onExecution() {
//
//                log("Matching item was found at index " + variables("Pointers").getInt("current_pointer"));
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{30});
//            }
//        };
//
//        final EndIf endif_17 = new EndIf("endif_17") {
//            @Override
//            protected void onExecution() {
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{30});
//            }
//        };
//
//        final EndFunction endfunc_18 = new EndFunction("endfunc_18") {
//            @Override
//            protected void destroyLocalData() {
//                popVarStack("Locals");
//                popVarStack("args");
//
//            }
//
//            @Override
//            protected void onExecution() {
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{32});
//            }
//        };
//
//        final Command cmd_19 = new Command("cmd_19") {
//            @Override
//            protected void onExecution() {
//                variables("Locals").declareVariable("search_item", Type.STRING);
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{34});
//            }
//        };
//
//        final Input input_20 = new Input("input_20", Type.STRING) {
//            @Override
//            protected void onExecution() {
//                variables("Locals").set("search_item", String.valueOf(getInputContent()));
//
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{35});
//            }
//        };
//
//        final Call call_21 = new Call("call_21") {
//            @Override
//            protected void buildArgs() {
//                pushVarStack("args");
//                variables("args").declareVariable("search_item", Type.STRING);
//
//            }
//
//            @Override
//            protected void passArgs() {
//                variables("args").set("search_item", variables("Locals").getStr("search_item"));
//
//            }
//
//            @Override
//            protected void onExecution() {
//                getSourceCodeUnit("Carbon Code").highlight(new int[]{36});
//            }
//        };
//        call_21.setFunction(func_2);
//
//        //Chaining
//        func_2.chainTo(cmd_3);
//        cmd_3.chainTo(cmd_4);
//        cmd_4.chainTo(cmd_5);
//        cmd_5.chainTo(while_6);
//        while_6.chainTo(if_7);
//        if_7.chainTo(cmd_8);
//
//        if_7.elseChainTo(else_9);
//        else_9.chainTo(cmd_10);
//
//        if_7.endTo(endif_11);
//        while_6.blockChainTo(if_13);
//        while_6.endTo(endwhile_12);
//        if_13.chainTo(cmd_14);
//
//        if_13.elseChainTo(else_15);
//        else_15.chainTo(cmd_16);
//
//        if_13.endTo(endif_17);
//        func_2.endTo(endfunc_18);
//
//        cmd_19.chainTo(input_20);
//        input_20.chainTo(call_21);
//
//        //Tree Binding
//        algorithmTree = new Content() {
//            {
//                this.setInitializer(cmd_1);
//                this.addAlgorithmHeader("Search", cmd_19);
//            }
//        };
//
//    }
    }

    //Queue def 2
    static {
        final Command cmd_1 = new Command("cmd_1") {
            @Override
            protected void onExecution() {
                buildVarStack("Locals");
                buildVarStack("Pointers");
                buildVarStack("args");
                buildVarStack("return");


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
                    nodes("List").set("Pointer", count, count + 1);
                }

                nodes("List").set("Pointer", nodes("List").getSize() - 1, -1);

                String carbonCode = "**__init__**[CMD: {BUILD: l, Locals \n " +
                        "		BUILD: p, Pointers \n " +
                        "		BUILD: a, args \n " +
                        "		BUILD: r, return \n " +
                        " \n " +
                        "		DIM: List @ STR Data | INT Pointer @ 10 \n " +
                        "		DIM: INT head_pointer @ p \n " +
                        "		DIM: INT tail_pointer @ p \n " +
                        "		DIM: INT free_Pointer @ p  \n " +
                        "		DIM: INT current_pointer @ p \n " +
                        " \n " +
                        "		SET: head_pointer @ p, -1 \n " +
                        "		SET: free_Pointer @ p, 0 \n " +
                        "		SET: tail_pointer @ p, -1 \n " +
                        "		SET: current_pointer @ p, -1 \n " +
                        " \n " +
                        "		EVAL: for (int count = 0; count < List.getSize(); count++)  \n " +
                        "		EVAL:	 List[Pointer][count] <- count + 1  \n " +
                        " \n " +
                        "		EVAL: List[Pointer][List.getSize()] <- -1 \n " +
                        " \n " +
                        "		} \n " +
                        "		/*Building variables and data structure to be used to work a Queue ADT.*/ :] \n " +
                        " \n " +
                        "[FUNC: VOID Search: (STR search_item):] \n " +
                        "	[CMD: {DIM: BOOL item_found}:] \n " +
                        "	[CMD: {SET: current_pointer @ p, <head_pointer @ p>}:] \n " +
                        "	[CMD: {SET: item_found, false}:] \n " +
                        "	[WHILE: (<current_pointer @ p> != -1 && not <item_found>):] \n " +
                        "		[IF: (<^List[Data][<current_pointer @ p>]> == <search_item>):] \n " +
                        "			[CMD: {SET: item_found, true}:] \n " +
                        "		[ELSE::] \n " +
                        "			[CMD: {SET: current_pointer @ p, <^List[Pointer][<current_pointer @ p>]>}:] \n " +
                        "		[ENDIF::] \n " +
                        "	[ENDWHILE::] \n " +
                        "	[IF: (not <item_found>):] \n " +
                        "		[CMD: \\*No matching item was found.*\\:] \n " +
                        "	[ELSE::] \n " +
                        "		[CMD: \\*Matching item was found at index <current_pointer @ p>*\\:] \n " +
                        "	[ENDIF::] \n " +
                        "[ENDFUNC::] \n " +
                        " \n " +
                        "**Search**[: {DIM: STR search_item}:] \n " +
                        "[INPUT: STR: (search_item):] \n " +
                        "[CALL: Search: (<search_item>):] \n " +
                        " \n " +
                        "[FUNC: VOID Insert: (STR item):] \n " +
                        "	[IF: (<free_Pointer @ p> == -1):] \n " +
                        "		[CMD: \\*Queue has no free nodes. Insertion could not be performed.*\\:] \n " +
                        "	[ELSE::] \n " +
                        "		[CMD: {SET: current_pointer @ p, <free_Pointer @ p>}:] \n " +
                        "		[CMD: {SET: List[Data][<current_pointer @ p>], <item>}:] \n " +
                        "		[CMD: {SET: free_Pointer @ p, <^List[Pointer][<current_pointer @ p>]>}:] \n " +
                        "		[IF: (<tail_pointer @ p> != -1):] \n " +
                        "			[CMD: {SET: List[Pointer][<tail_pointer @ p>], <current_pointer @ p>}:] \n " +
                        "		[ENDIF::] \n " +
                        "		[CMD: {SET: List[Pointer][<current_pointer @ p>], -1}:] \n " +
                        "		[CMD: {SET: tail_pointer @ p, <current_pointer @ p>}:] \n " +
                        "		[IF: (<head_pointer @ p> == -1):] \n " +
                        "			[CMD: {SET: head_pointer @ p, <current_pointer @ p>}:] \n " +
                        "		[ENDIF::] \n " +
                        "	[ENDIF::] \n " +
                        "[ENDFUNC::] \n " +
                        " \n " +
                        "**Insert**[: {DIM: STR item_to_be_inserted}:] \n " +
                        "[INPUT: STR: (item_to_be_inserted):] \n " +
                        "[CALL: Insert: (<item_to_be_inserted>):] \n " +
                        " \n " +
                        "[FUNC : STR Remove : (STR item):] \n " +
                        "	[IF: (<head_pointer @ p> == -1):] \n " +
                        "		[CMD: \\*Queue is empty*\\ :] \n " +
                        "		[RETURN: ():]  \n " +
                        "	[ELSE::] \n " +
                        "		[CMD: {DIM: STR output}:] \n " +
                        "		[CMD: {SET: current_pointer @ p, <head_pointer @ p>}:] \n " +
                        "		[CMD: {SET: output, <^List[Pointer][<current_pointer @ p>]>}:] \n " +
                        "		[CMD: {SET: head_pointer @ p, <^List[Pointer][<current_pointer @ p>]}:] \n " +
                        " \n " +
                        " \n " +
                        "		[IF: (<current_pointer @ p> == <tail_pointer @ p>):] \n " +
                        "			[CMD: {SET: tail_pointer @ p, -1}:] \n " +
                        "		[ENDIF::] \n " +
                        " \n " +
                        "		[CMD: {SET: List[Pointer][<current_pointer @ p>], <free_Pointer @ p>}:] \n " +
                        "		[RETURN: (<output>):] \n " +
                        "	[ENDIF::] \n " +
                        "[ENDFUNC::] \n " +
                        " \n " +
                        "**Remove**[: {DIM: STR item_to_be_removed}:] \n " +
                        "[INPUT: STR: (item_to_be_removed):] \n " +
                        "[CALL: Remove: (<item_to_be_removed>):] \n " +
                        "[CMD: \\* *RETURN *\\:] \n " +
                        "";

                buildSourceCodeUnits(new String[]{"Carbon Code"});
                getSourceCodeUnit("Carbon Code").setText(carbonCode);

                getSourceCodeUnit("Carbon Code").highlight(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22});
            }
        };

        final Function func_2 = new Function("func_2") {
            @Override
            protected void buildLocalData() {
                pushVarStack("Locals");
                variables("Locals").declareVariable("search_item", Type.STRING);

                variables("Locals").set("search_item", variables("args").getStr("search_item"));


            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{25});
            }
        };
        func_2.setIdentifier("Search");

        final Command cmd_3 = new Command("cmd_3") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("item_found", Type.BOOLEAN);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{26});
            }
        };

        final Command cmd_4 = new Command("cmd_4") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{27});
            }
        };

        final Command cmd_5 = new Command("cmd_5") {
            @Override
            protected void onExecution() {
                variables("Locals").set("item_found", false);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{28});
            }
        };

        final DoWhile while_6 = new DoWhile("while_6") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("current_pointer") != -1 && !variables("Locals").getBool("item_found");
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{29});
            }
        };

        final IfOrElse if_7 = new IfOrElse("if_7") {
            @Override
            protected boolean evaluate() {
                return nodes("List").getStr("Data", variables("Pointers").getInt("current_pointer")) == variables("Locals").getStr("search_item");
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{30});
            }
        };

        final Command cmd_8 = new Command("cmd_8") {
            @Override
            protected void onExecution() {
                variables("Locals").set("item_found", true);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{31});
            }
        };

        final Command else_9 = new Command("else_9") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{32});
            }
        };

        final Command cmd_10 = new Command("cmd_10") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("current_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{33});
            }
        };

        final EndIf endif_11 = new EndIf("endif_11") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{34});
            }
        };

        final EndWhile endwhile_12 = new EndWhile("endwhile_12") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{35});
            }
        };

        final IfOrElse if_13 = new IfOrElse("if_13") {
            @Override
            protected boolean evaluate() {
                return !variables("Locals").getBool("item_found");
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{36});
            }
        };

        final Command cmd_14 = new Command("cmd_14") {
            @Override
            protected void onExecution() {

                log("No matching item was found.");

                getSourceCodeUnit("Carbon Code").highlight(new int[]{37});
            }
        };

        final Command else_15 = new Command("else_15") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{38});
            }
        };

        final Command cmd_16 = new Command("cmd_16") {
            @Override
            protected void onExecution() {
                log("Matching item was found at index " + variables("Pointers").getInt("current_pointer"));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{39});
            }
        };

        final EndIf endif_17 = new EndIf("endif_17") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{40});
            }
        };

        final EndFunction endfunc_18 = new EndFunction("endfunc_18") {
            @Override
            protected void destroyLocalData() {
                popVarStack("Locals");
                popVarStack("args");

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{41});
            }
        };

        final Command cmd_19 = new Command("cmd_19") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("search_item", Type.STRING);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{43});
            }
        };

        final Input input_20 = new Input("input_20", Type.STRING) {
            @Override
            protected void onExecution() {
                variables("Locals").set("search_item", String.valueOf(getInputContent()));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{44});
            }
        };

        final Call call_21 = new Call("call_21") {
            @Override
            protected void buildArgs() {
                pushVarStack("args");
                variables("args").declareVariable("search_item", Type.STRING);

            }

            @Override
            protected void passArgs() {
                variables("args").set("search_item", variables("Locals").getStr("search_item"));

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{45});
            }
        };
        call_21.setFunction(func_2);

        final Function func_22 = new Function("func_22") {
            @Override
            protected void buildLocalData() {
                pushVarStack("Locals");
                variables("Locals").declareVariable("item", Type.STRING);

                variables("Locals").set("item", variables("args").getStr("item"));


            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{47});
            }
        };
        func_22.setIdentifier("Insert");

        final IfOrElse if_23 = new IfOrElse("if_23") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("free_Pointer") == -1;
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{48});
            }
        };

        final Command cmd_24 = new Command("cmd_24") {
            @Override
            protected void onExecution() {

                log("Queue has no free nodes. Insertion could not be performed.");

                getSourceCodeUnit("Carbon Code").highlight(new int[]{49});
            }
        };

        final Command else_25 = new Command("else_25") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{50});
            }
        };

        final Command cmd_26 = new Command("cmd_26") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("current_pointer", variables("Pointers").getInt("free_Pointer"));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{51});
            }
        };

        final Command cmd_27 = new Command("cmd_27") {
            @Override
            protected void onExecution() {
                nodes("List").set("Data", variables("Pointers").getInt("current_pointer"), variables("Locals").getStr("item"));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{52});
            }
        };

        final Command cmd_28 = new Command("cmd_28") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("free_Pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{53});
            }
        };

        final IfOrElse if_29 = new IfOrElse("if_29") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("tail_pointer") != -1;
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{54});
            }
        };

        final Command cmd_30 = new Command("cmd_30") {
            @Override
            protected void onExecution() {
                nodes("List").set("Pointer", variables("Pointer").getInt("current_pointer"), variables("Pointers").getInt("current_pointer"));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{55});
            }
        };

        final EndIf endif_31 = new EndIf("endif_31") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{56});
            }
        };

        final Command cmd_32 = new Command("cmd_32") {
            @Override
            protected void onExecution() {
                nodes("List").set("Pointer", variables("Pointer").getInt("current_pointer"), -1);

                getSourceCodeUnit("Carbon Code").highlight(new int[]{57});
            }
        };

        final Command cmd_33 = new Command("cmd_33") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("tail_pointer", variables("Pointers").getInt("current_pointer"));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{58});
            }
        };

        final IfOrElse if_34 = new IfOrElse("if_34") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("head_pointer") == -1;
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{59});
            }
        };

        final Command cmd_35 = new Command("cmd_35") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("head_pointer", variables("Pointers").getInt("current_pointer"));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{60});
            }
        };

        final EndIf endif_36 = new EndIf("endif_36") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{61});
            }
        };

        final EndIf endif_37 = new EndIf("endif_37") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{62});
            }
        };

        final EndFunction endfunc_38 = new EndFunction("endfunc_38") {
            @Override
            protected void destroyLocalData() {
                popVarStack("Locals");
                popVarStack("args");

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{63});
            }
        };

        final Command cmd_39 = new Command("cmd_39") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("item_to_be_inserted", Type.STRING);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{65});
            }
        };

        final Input input_40 = new Input("input_40", Type.STRING) {
            @Override
            protected void onExecution() {
                variables("Locals").set("item_to_be_inserted", String.valueOf(getInputContent()));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{66});
            }
        };

        final Call call_41 = new Call("call_41") {
            @Override
            protected void buildArgs() {
                pushVarStack("args");
                variables("args").declareVariable("item", Type.STRING);

            }

            @Override
            protected void passArgs() {
                variables("args").set("item", variables("Locals").getStr("item_to_be_inserted"));

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{67});
            }
        };
        call_41.setFunction(func_22);

        final Function func_42 = new Function("func_42") {
            @Override
            protected void buildLocalData() {
                pushVarStack("Locals");
                variables("Locals").declareVariable("item", Type.STRING);

                variables("Locals").set("item", variables("args").getStr("item"));
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{69});
            }
        };
        func_42.setIdentifier("Remove");

        final IfOrElse if_43 = new IfOrElse("if_43") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("head_pointer") == -1;
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{70});
            }
        };

        final Command cmd_44 = new Command("cmd_44") {
            @Override
            protected void onExecution() {

                log("Queue is empty");

                getSourceCodeUnit("Carbon Code").highlight(new int[]{71});
            }
        };

        final Return return_45 = new Return("return_45") {
            @Override
            protected void onExecution() {
                variables("return").set("Remove", "");

                getSourceCodeUnit("Carbon Code").highlight(new int[]{72});
            }
        };

        final Command else_46 = new Command("else_46") {
            @Override
            protected void onExecution() {
                getSourceCodeUnit("Carbon Code").highlight(new int[]{73});
            }
        };

        final Command cmd_47 = new Command("cmd_47") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("output", Type.STRING);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{74});
            }
        };

        final Command cmd_48 = new Command("cmd_48") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{75});
            }
        };

        final Command cmd_49 = new Command("cmd_49") {
            @Override
            protected void onExecution() {
                variables("Locals").set("output", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));


                getSourceCodeUnit("Carbon Code").highlight(new int[]{76});
            }
        };

        final Command cmd_50 = new Command("cmd_50") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("head_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{77});
            }
        };

        final IfOrElse if_51 = new IfOrElse("if_51") {
            @Override
            protected boolean evaluate() {
                return variables("Pointers").getInt("current_pointer") == variables("Pointers").getInt("tail_pointer");
            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{80});
            }
        };

        final Command cmd_52 = new Command("cmd_52") {
            @Override
            protected void onExecution() {
                variables("Pointers").set("tail_pointer", -1);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{81});
            }
        };

        final EndIf endif_53 = new EndIf("endif_53") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{82});
            }
        };

        final Command cmd_54 = new Command("cmd_54") {
            @Override
            protected void onExecution() {
                nodes("List").set("Pointer", variables("Pointer").getInt("current_pointer"), variables("Pointers").getInt("free_Pointer"));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{84});
            }
        };

        final Return return_55 = new Return("return_55") {
            @Override
            protected void onExecution() {
                variables("return").set("Remove", variables("Locals").getStr("output"));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{85});
            }
        };

        final EndIf endif_56 = new EndIf("endif_56") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{86});
            }
        };

        final EndFunction endfunc_57 = new EndFunction("endfunc_57") {
            @Override
            protected void destroyLocalData() {
                popVarStack("Locals");
                popVarStack("args");

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{87});
            }
        };

        final Command cmd_58 = new Command("cmd_58") {
            @Override
            protected void onExecution() {
                variables("Locals").declareVariable("item_to_be_removed", Type.STRING);


                getSourceCodeUnit("Carbon Code").highlight(new int[]{89});
            }
        };

        final Input input_59 = new Input("input_59", Type.STRING) {
            @Override
            protected void onExecution() {
                variables("Locals").set("item_to_be_removed", String.valueOf(getInputContent()));

                getSourceCodeUnit("Carbon Code").highlight(new int[]{90});
            }
        };

        final Call call_60 = new Call("call_60") {
            @Override
            protected void buildArgs() {
                pushVarStack("args");
                variables("args").declareVariable("item", Type.STRING);

            }

            @Override
            protected void passArgs() {
                variables("args").set("item", variables("Locals").getStr("item_to_be_removed"));

            }

            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{91});
            }
        };
        call_60.setFunction(func_42);

        final Command cmd_61 = new Command("cmd_61") {
            @Override
            protected void onExecution() {


                getSourceCodeUnit("Carbon Code").highlight(new int[]{92});
            }
        };

        func_2.chainTo(cmd_3);
        cmd_3.chainTo(cmd_4);
        cmd_4.chainTo(cmd_5);
        cmd_5.chainTo(while_6);
        while_6.chainTo(if_7);
        if_7.chainTo(cmd_8);

        if_7.elseChainTo(else_9);
        else_9.chainTo(cmd_10);

        if_7.endTo(endif_11);
        while_6.blockChainTo(if_13);
        while_6.endTo(endwhile_12);
        if_13.chainTo(cmd_14);

        if_13.elseChainTo(else_15);
        else_15.chainTo(cmd_16);

        if_13.endTo(endif_17);
        func_2.endTo(endfunc_18);

        cmd_19.chainTo(input_20);
        input_20.chainTo(call_21);

        func_22.chainTo(if_23);
        if_23.chainTo(cmd_24);

        if_23.elseChainTo(else_25);
        else_25.chainTo(cmd_26);
        cmd_26.chainTo(cmd_27);
        cmd_27.chainTo(cmd_28);
        cmd_28.chainTo(if_29);
        if_29.chainTo(cmd_30);

        if_29.endTo(endif_31);
        if_29.blockChainTo(cmd_32);
        cmd_32.chainTo(cmd_33);
        cmd_33.chainTo(if_34);
        if_34.chainTo(cmd_35);

        if_34.endTo(endif_36);
        if_23.endTo(endif_37);
        func_22.endTo(endfunc_38);

        cmd_39.chainTo(input_40);
        input_40.chainTo(call_41);

        func_42.chainTo(if_43);
        if_43.chainTo(cmd_44);
        cmd_44.chainTo(return_45);
        if_43.elseChainTo(else_46);
        else_46.chainTo(cmd_47);
        cmd_47.chainTo(cmd_48);
        cmd_48.chainTo(cmd_49);
        cmd_49.chainTo(cmd_50);
        cmd_50.chainTo(if_51);
        if_51.chainTo(cmd_52);

        if_51.endTo(endif_53);
        if_51.blockChainTo(cmd_54);
        cmd_54.chainTo(return_55);
        if_43.endTo(endif_56);
        func_42.endTo(endfunc_57);

        cmd_58.chainTo(input_59);
        input_59.chainTo(call_60);
        call_60.chainTo(cmd_61);

        algorithmTree = new Content() {
            {
                this.setInitializer(cmd_1);
                this.addAlgorithmHeader("Search", cmd_19);
                this.addAlgorithmHeader("Insert", cmd_39);
                this.addAlgorithmHeader("Remove", cmd_58);
            }
        };
    }
}
