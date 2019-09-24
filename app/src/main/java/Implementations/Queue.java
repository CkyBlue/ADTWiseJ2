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
import Utility.SourceCode.Unit.Content.HighLightingLevel;

public class Queue {
    static Content algorithmTree = new Content();

    static {
        {

            final Command cmd_1 = new Command("cmd_1") {
                @Override
                protected void onExecution() {
                    buildVarStack("Variables");
                    buildVarStack("__return", false);
                    buildVarStack("__args", false);

// Building Code Units
                    buildSourceCodeUnits(new String[]{"Initialization_Pseudocode", "Initialization_Python_Code", "Remove_Pseudocode", "Search_Pseudocode", "Insert_Pseudocode"});

                    String initialization_pseudocode_str = "TYPE Node \n" +
                            "    DECLARE Item AS STRING \n" +
                            "    DECLARE Pointer AS INTEGER \n" +
                            "ENDTYPE \n" +
                            " \n" +
                            "DECLARE NodeArray AS ARRAY[0 : 9] OF Node \n" +
                            " \n" +
                            "DECLARE Head_Pointer, Tail_Pointer, Free_Pointer AS INTEGER \n" +
                            "Head_Pointer <- -1 \n" +
                            "Tail_Pointer <- -1 \n" +
                            "Free_Pointer <- 0 \n" +
                            " \n" +
                            "FOR Count <- 0 TO SIZEOF(NodeArray) - 1  \n" +
                            "    NodeArray[Count].Pointer <- Count + 1 \n" +
                            "ENDFOR  \n" +
                            " \n" +
                            "NodeArray[SIZEOF(NodeArray) - 1].Pointer <- -1 \n" +
                            "";
                    getSourceCodeUnit("Initialization_Pseudocode").setText(initialization_pseudocode_str);

                    String initialization_python_code_str = "class Node \n" +
                            "    def __init__(self): \n" +
                            "        self.Item : str \n" +
                            "        self.Pointer : int \n" +
                            " \n" +
                            "NodeArray : List[Node] = [Node for i in range(9)] \n" +
                            " \n" +
                            "Head_Pointer : int = -1 \n" +
                            "Tail_Pointer : int = -1 \n" +
                            "Free_Pointer : int = -1 \n" +
                            " \n" +
                            "for count in range(len(NodeArray)): \n" +
                            "    NodeArray[count].Pointer = count + 1         \n" +
                            " \n" +
                            "NodeArray[len(NodeArray) - 1].Pointer = -1 \n" +
                            "";
                    getSourceCodeUnit("Initialization_Python_Code").setText(initialization_python_code_str);

                    String remove_pseudocode_str = "FUNCTION Remove() RETURNS STRING  \n" +
                            "    IF Head_Pointer = -1 THEN \n" +
                            "        OUTPUT \"Error: Removal could not be performed as the Queue only contains free nodes.\" \n" +
                            "                 \n" +
                            "        RETURN \n" +
                            "    ELSE \n" +
                            " \n" +
                            "        Current_Pointer <- Head_Pointer \n" +
                            "        Output_From_Queue <- Node_Array[Current_Pointer].Item \n" +
                            " \n" +
                            "        Head_Pointer <- Node_Array[Current_Pointer].Pointer \n" +
                            "        IF Current_Pointer = Tail_Pointer THEN \n" +
                            "            Tail_Pointer <- -1 \n" +
                            "        ENDIF \n" +
                            " \n" +
                            "        Node_Array[Current_Pointer].Pointer <- Free_Pointer \n" +
                            "        Free_Pointer <- Current_Pointer \n" +
                            " \n" +
                            "        RETURN Output_From_Queue \n" +
                            "    ENDIF \n" +
                            "ENDFUNCTION \n" +
                            "";
                    getSourceCodeUnit("Remove_Pseudocode").setText(remove_pseudocode_str);

                    String search_pseudocode_str = "PROCEDURE Search(Data_Item)  \n" +
                            "    Item_Found <- False \n" +
                            "    Current_Pointer <- Head_pointer \n" +
                            " \n" +
                            "    WHILE Current_Pointer <> -1 AND NOT Item_Found \n" +
                            "        IF Node_Array[Current_Pointer].Item = Item_To_Be_Searched THEN \n" +
                            "            Item_Found <- True \n" +
                            "        ELSE  \n" +
                            "            CurrentPointer <- Node_Array[Current_Pointer].Pointer \n" +
                            "        ENDIF \n" +
                            "    ENDWHILE \n" +
                            " \n" +
                            "    IF NOT Item_Found THEN \n" +
                            "        OUTPUT \"No matching item was found.\" \n" +
                            "    ELSE \n" +
                            "        OUTPUT \"Matching item was found at index \" + TO_STRING(Current_Pointer) \n" +
                            "    ENDIF \n" +
                            "ENDPROCEDURE     \n" +
                            "";
                    getSourceCodeUnit("Search_Pseudocode").setText(search_pseudocode_str);

                    String insert_pseudocode_str = "PROCEDURE Insert(Data_Item) \n" +
                            "    IF Free_Pointer = -1 THEN \n" +
                            "        OUTPUT \"Error: Insertion could not be performed as the Queue has no free nodes.\" \n" +
                            "    ELSE \n" +
                            "        Current_Pointer <- Free_Pointer \n" +
                            "        Node_Array[Current_Pointer].Item <- Data_Item \n" +
                            " \n" +
                            "        Free_Pointer <- Node_Array[Current_Pointer].Pointer \n" +
                            " \n" +
                            "        IF Tail_Pointer <> -1 THEN \n" +
                            "            Node_Arry[Tail_Pointer].Pointer <- Current_Pointer \n" +
                            "        ENDIF \n" +
                            "        Node_Array[Current_Pointer].Pointer <- -1 \n" +
                            "         \n" +
                            "        Tail_Pointer <- Current_Pointer      \n" +
                            "        IF Head_Pointer = -1 THEN  \n" +
                            "            Head_Pointer <- Current_Pointer \n" +
                            "        ENDIF \n" +
                            " \n" +
                            "    ENDIF \n" +
                            "ENDPROCEDURE \n" +
                            "";
                    getSourceCodeUnit("Insert_Pseudocode").setText(insert_pseudocode_str);

                    pushVarStack("Variables");
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    hideSourceCodeUnit("Initialization_Pseudocode");
                    hideSourceCodeUnit("Initialization_Python_Code");
                    hideSourceCodeUnit("Remove_Pseudocode");
                    hideSourceCodeUnit("Search_Pseudocode");
                    hideSourceCodeUnit("Insert_Pseudocode");

                    showSourceCodeUnit("Initialization_Pseudocode");

                    showSourceCodeUnit("Initialization_Python_Code");

                    log("We begin by <b> setting up the data structures <\b> required for implementing the Queue.<br>	", false);
                }
            };

            algorithmTree.setInitializer(cmd_1);

            final Command cmd_2 = new Command("cmd_2") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    BluePrint nodearrayBluePrint = new BluePrint();
                    nodearrayBluePrint.addKey("Item", Type.STRING);
                    nodearrayBluePrint.addKey("Pointer", Type.INTEGER);
                    buildNodesStack("NodeArray", nodearrayBluePrint, 10);
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(new int[]{6, 1, 2, 4, 3}, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Initialization_Python_Code").highlight(new int[]{6, 1, 2, 4, 3}, HighLightingLevel.Secondary);

                    log("We use an array of custom objects (representing nodes of the Queue) to store the data represening the Queue ADT (Abstract Data Type)<br>	", false);
                }
            };

            final Command cmd_3 = new Command("cmd_3") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    buildVarStack("Pointers");
                    variables("Pointers").declareVariable("head_pointer", Type.INTEGER);
                    variables("Pointers").set("head_pointer", -1);
                    variables("Pointers").declareVariable("tail_pointer", Type.INTEGER);
                    variables("Pointers").set("tail_pointer", -1);
                    variables("Pointers").declareVariable("current_pointer", Type.INTEGER);
                    variables("Pointers").set("current_pointer", -1);
                    variables("Pointers").declareVariable("free_pointer", Type.INTEGER);
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(new int[]{9, 8, 10, 11}, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Initialization_Python_Code").highlight(new int[]{9, 8, 10}, HighLightingLevel.Secondary);

                    log("We make use of special 'pointers' to store important information about the Queue. Nodes withing the Queue ADT are chained together (using pointer values) to link interrelated free and occupied nodes.<br>	 <br> The variable 'Current_Pointer' is a special pointer value used when navigating through different nodes within a chain link-by-link.<br>	 <br>	 Regarding the chain of free nodes (free-list), each free node points to the next free node in the chain. When a free node is used, its pointer value immediately reveals whether there is another free node available or not, (depending on whether it has a null pointer value or not), and if there is, what that value is. The free node next in line for being used (or the null reference for it when there is no such node) is stored at the Free_Pointer.<br>	 <br> Regarding the chain of occupied nodes (the data-list), each occupied node points to the next occupied node that follows the logical order of traversal. The first node to be accessed is the head of the chain. It can be the Top Of Stack in a Stack ADT, otherwise the Head of a Queue in a Queue ADT and so on.<br>	 <br> The variable 'Head_Pointer' indicates the head node (the first addition to the Queue), whereas the variable 'Tail_pointer' indicates the tail node (the last addition to the Queue). These pointer allows a Queue to identify which node to remove during a removal operation and which node to link to when performing an insertion. The free pointer is another special pointer whose job is to indicate the head of the chain of free nodes (the free-list). <br>	 <br> Since a Queue is a FIFO (First-In-First-Out), when traversing the Queue, we begin at the head, the head points to the node that was filled right after it, which points to the node that filled subsequently after it, and so on until we reach the tail node which holds a null pointer value (doesn't point to anything).This linkage allows the Queue to determine which node logically comes after which node, regardless of jumbled index positions. For e.g if all the nodes in the array are filled and then the nodes at the beginning are popped out, a gap in the array will arise. New insertions to the array will be able to use this opened up space but despite later additions showing up at lower index positions, the order of items within the Queue will be clear because of the pointer values.<br>	", false);

                }
            };

            final Command cmd_4 = new Command("cmd_4") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    for (int count = 0; count < nodes("NodeArray").getSize(); count++) {
                        ;
                        nodes("NodeArray").set("Pointer", count, count + 1);
                    }
                    ;
                    nodes("NodeArray").set("Pointer", nodes("NodeArray").getSize() - 1, -1);
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(new int[]{14, 13, 15, 17}, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Initialization_Python_Code").highlight(new int[]{13, 15, 12}, HighLightingLevel.Secondary);

                    log("Since the nodes in our array are all free nodes at the beginning, when the Queue is still being set up, they are linked together to form the free-list. For convenience, we set the free-list such that each node points to the node an index after it, except for the node at the last index which has a null pointer value.<br>	", false);

                }
            };

            final Command impasse_5 = new Command("impasse_5") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    popVarStack("Variables");
                    log("Algorithm Terminated", false);

                }
            };

            final Function func_6 = new Function("func_6") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{1}, HighLightingLevel.Secondary);

                    log("Entering the Search procedure.", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Variables");
                    variables("Variables").declareVariable("search_item", Type.STRING);
                    variables("Variables").set("search_item", variables("__args").getStr("search_item"));
                }
            };

            func_6.setIdentifier("Search");

            final Command cmd_7 = new Command("cmd_7") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").declareVariable("item_found", Type.BOOLEAN);
                    variables("Variables").set("item_found", false);
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{2}, HighLightingLevel.Secondary);

                    log("item_found is a boolean flag to check whether a match has already been found before deciding to iterate over to the next node in the data-list. We set it to false at the beginning.<br>	", false);

                }
            };

            final Command cmd_8 = new Command("cmd_8") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{3}, HighLightingLevel.Secondary);

                    log("Some key ideas before entering execution. The variable 'Current_Pointer' is a special pointer value used when navigating through different nodes within a chain link-by-link.<br>	", false);

                }
            };

            final Command cmd_9 = new Command("cmd_9") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{6, 8, 7, 11, 9, 5, 10});

                    log("We will use Current_Pointer to iterate over each and every node in the data-list. Since we want to traverse the Queue in a logical order, we begin at the head.<br>	 <br> We test a node for a match if the node exists (Current_Pointer is not null) and if a match has not been found already (item_found is false). If we do not find a match, we set the Current_Pointer to the index the node is pointing to (the node's Pointer value)<br>	", false);

                }
            };

            final DoWhile while_10 = new DoWhile("while_10") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{5}, HighLightingLevel.Secondary);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") != -1 && !variables("Variables").getBool("item_found");
                }
            };

            final Command cmd_11 = new Command("cmd_11") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    log("While loop's condition evaluated to true.", false);

                }
            };

            final IfOrElse if_12 = new IfOrElse("if_12") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{6}, HighLightingLevel.Secondary);

                }

                @Override
                protected boolean evaluate() {
                    return nodes("NodeArray").getStr("Item", variables("Pointers").getInt("current_pointer")).equals(variables("Variables").getStr("search_item"));
                }
            };

            final Command cmd_13 = new Command("cmd_13") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").set("item_found", true);
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{7}, HighLightingLevel.Secondary);

                }
            };

            final Command else_14 = new Command("else_14") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{8}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_15 = new Command("cmd_15") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("current_pointer", nodes("NodeArray").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{9}, HighLightingLevel.Secondary);

                }
            };

            final EndIf endif_16 = new EndIf("endif_16") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{10}, HighLightingLevel.Secondary);

                }
            };

            final EndWhile endwhile_17 = new EndWhile("endwhile_17") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{11}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_18 = new Command("cmd_18") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    if (!variables("Variables").getBool("item_found")) {
                        ;
                        output("No matching item was found.<br>	");
                    } else {
                        ;
                        output("Matching item found at index " + variables("Pointers").getInt("current_pointer") + "<br>	");
                    }
                    ;
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{16, 14, 15, 13, 17}, HighLightingLevel.Secondary);

                    log("We output results based on whether a match was found or not.<br>	", false);

                }
            };

            final EndFunction endfunc_19 = new EndFunction("endfunc_19") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{18}, HighLightingLevel.Secondary);

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Variables");
                    popVarStack("__args");
                }
            };

            final Command cmd_20 = new Command("cmd_20") {
                @Override
                protected void onExecution() {
                    pushVarStack("Variables");
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").declareVariable("search_item", Type.STRING);
                    hideSourceCodeUnit("Initialization_Pseudocode");
                    hideSourceCodeUnit("Initialization_Python_Code");
                    hideSourceCodeUnit("Remove_Pseudocode");
                    hideSourceCodeUnit("Search_Pseudocode");
                    hideSourceCodeUnit("Insert_Pseudocode");

                    showSourceCodeUnit("Search_Pseudocode");

                    log("We'll be performing a Search operation on the Queue where we will test every node in the data-list, starting from the head node and ending at the tail, for a match.<br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Search", cmd_20);

            final Input input_21 = new Input("input_21", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").set("search_item", String.valueOf(getInputContent()));
                    log("Input the item to be searched.<br>	", false);

                }
            };

            final Call call_22 = new Call("call_22") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{16, 6, 8, 7, 11, 14, 15, 9, 17, 1, 2, 5, 13, 18, 4, 3, 10, 12});

                    log("Calling the Search procedure with " + variables("Variables").getStr("search_item") + " as the item to be searched.<br>	", false);

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("__args");
                    variables("__args").declareVariable("search_item", Type.STRING);
                }

                @Override
                protected void passArgs() {
                    variables("__args").set("search_item", variables("Variables").getStr("search_item"));
                }
            };

            call_22.setFunction(func_6);

            final Command impasse_23 = new Command("impasse_23") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    popVarStack("Variables");
                    log("Algorithm Terminated", false);

                }
            };

            final Function func_24 = new Function("func_24") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{1}, HighLightingLevel.Secondary);

                    log("Entering the insert procedure", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Variables");
                    variables("Variables").declareVariable("item", Type.STRING);
                    variables("Variables").set("item", variables("__args").getStr("item"));
                }
            };

            func_24.setIdentifier("Insert");

            final Command cmd_25 = new Command("cmd_25") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{20, 2, 4});

                    log("<b> Key ideas for Step 1 </b> <br> In the case of insertion, step 1 entails chosing not to perform the insertion when the Queue is already full.<br>	", false);

                }
            };

            final Command cmd_26 = new Command("cmd_26") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{5, 6});

                    log("<b> Key ideas for Step 2 </b> <br> Step 2 entails fetching the head of the free-list (indicated by Free_Pointer) to get a free node at which we can insert our new entry and then storing the entry at the node. To make it easier to track of things, we shall refer to this node as Current Node hereafter.<br>	", false);

                }
            };

            final Command cmd_27 = new Command("cmd_27") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{14, 8, 11, 15, 9, 17, 13, 18, 16, 10, 12});

                    log("<b> Key ideas for Step 3 </b> <br> Step 3 entails transferring a free node to the data list and making appropriate changes to pointer values to reflect that fact. The previously free node at which we store the inserted data (the current node) will no longer remain a free node. The node which it points to becomes the next head of the free-list. The pointer value of the current node, after being used to find the value of the next head of the free-list, is then over-written as the node gets attached to the data-list.<br>	", false);

                }
            };

            final IfOrElse if_28 = new IfOrElse("if_28") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{2}, HighLightingLevel.Secondary);

                    log("Beginning executing the steps.", false);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("free_pointer") == -1;
                }
            };

            final Command cmd_29 = new Command("cmd_29") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    output("Queue has no free nodes. Insertion could not be performed.");
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{3});

                    log("Since Free_Pointer is -1 (null), we enter the block that does not perform any action (No insertion is performed).", false);

                }
            };

            final Command else_30 = new Command("else_30") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{4}, HighLightingLevel.Secondary);

                    log("The Free_Pointer is not null so we are able to perform Insertion.", false);

                }
            };

            final Command cmd_31 = new Command("cmd_31") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("free_pointer"));
                    nodes("NodeArray").set("Item", variables("Pointers").getInt("current_pointer"), variables("Variables").getStr("item"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{5, 6}, HighLightingLevel.Secondary);

                    log("We will use the head of the free-list as the node where we insert our newly added data. <br>	", false);

                }
            };

            final Command cmd_32 = new Command("cmd_32") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("free_pointer", nodes("NodeArray").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{8}, HighLightingLevel.Secondary);

                    log("The head of the free-list is no longer free since we've entered data into it. Hence, the value of the sepcial pointer 'Free_Pointer' needs to be updated to point to the next node in the free-list. 'Current_Pointer' points to that node where we've added the data so we use the pointer value at this node as the value for the next 'Free_Pointer'.<br>	", false);

                }
            };

            final Command cmd_33 = new Command("cmd_33") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{13, 10, 11, 12});

                    log("<b> Key ideas for linking to data-list </b> <br> The Current Node (the one indicated by 'Current_Pointer' which was used to store the inserted data) is being removed from the free-list and linked to the data-list. We must consider what the Current Node should point to and should be pointed at by when linking it into the data-list. <br>	 <br> In a Queue, the last entered data should leave last so the Current Node will be attached as the tail to the data-list. This means that the previous tail must point to Current Node (if a previous tail does exist) and the Current Node must point to null. Note that if the Current Node's pointer value was updated to null before we used the previous value as the new Free_Pointer, we would have lost the required value for the Free_Pointer.<br>	", false);

                }
            };

            final IfOrElse if_34 = new IfOrElse("if_34") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{10}, HighLightingLevel.Secondary);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("tail_pointer") != -1;
                }
            };

            final Command cmd_35 = new Command("cmd_35") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    nodes("NodeArray").set("Pointer", variables("Pointers").getInt("tail_pointer"), variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{11}, HighLightingLevel.Secondary);

                }
            };

            final EndIf endif_36 = new EndIf("endif_36") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{12}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_37 = new Command("cmd_37") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    nodes("NodeArray").set("Pointer", variables("Pointers").getInt("current_pointer"), -1);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{13}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_38 = new Command("cmd_38") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{17, 18, 15, 16});

                    log("<b> Key ideas for updating special pointers </b> <br> We now consider the changes that need to be made to the special pointers 'Head_Pointer' and 'Tail_Pointer'. Since the Current Node is the tail of the data-list, 'Tail_Pointer' is updated to point to it. If the Current Node is the very first addition to the dta-list (if Head_Pointer has a null value indicating no head exists) then the Current Node also becomes the head of the data-list.<br>	", false);

                }
            };

            final Command cmd_39 = new Command("cmd_39") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("tail_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{15}, HighLightingLevel.Secondary);

                }
            };

            final IfOrElse if_40 = new IfOrElse("if_40") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{16}, HighLightingLevel.Secondary);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("head_pointer") == -1;
                }
            };

            final Command cmd_41 = new Command("cmd_41") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("head_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{17}, HighLightingLevel.Secondary);

                }
            };

            final EndIf endif_42 = new EndIf("endif_42") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{18}, HighLightingLevel.Secondary);

                }
            };

            final EndIf endif_43 = new EndIf("endif_43") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{20}, HighLightingLevel.Secondary);

                }
            };

            final EndFunction endfunc_44 = new EndFunction("endfunc_44") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{21}, HighLightingLevel.Secondary);

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Variables");
                    popVarStack("__args");
                }
            };

            final Command cmd_45 = new Command("cmd_45") {
                @Override
                protected void onExecution() {
                    pushVarStack("Variables");
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").declareVariable("item_to_be_inserted", Type.STRING);
                    hideSourceCodeUnit("Initialization_Pseudocode");
                    hideSourceCodeUnit("Initialization_Python_Code");
                    hideSourceCodeUnit("Remove_Pseudocode");
                    hideSourceCodeUnit("Search_Pseudocode");
                    hideSourceCodeUnit("Insert_Pseudocode");

                    showSourceCodeUnit("Insert_Pseudocode");

                    log("We'll be performing an insert operation on the Queue where we will:<br><br>		Step 0: Obtain an input and call the insert function.<br><br>		Step 1: Check if the operation is possible. <br><br>		Step 2: If possible, perform the insert operation. <br><br>		Step 3: Tie up loose ends. Since we end up modifying values withing the data and free list in Step 2, we need to make modifications to pointer values to ensure they reflect the new state of the Queue. We also keep in mind to make modifications in such an order so as to not over-write a pointer value while its information is still required for subsequent modifications. Consider the effect of the modifications on special pointers as well as those belonging to nodes on the free and data lists. <br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Insert", cmd_45);

            final Input input_46 = new Input("input_46", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Variables").set("item_to_be_inserted", String.valueOf(getInputContent()));
                    log("Input the item to be inserted.<br>	", false);

                }
            };

            final Call call_47 = new Call("call_47") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{11, 1, 14, 8, 15, 4, 3, 6, 7, 9, 19, 2, 13, 17, 10, 20, 5, 21, 18, 16, 12});

                    log("Calling the Insert procedure with " + variables("Variables").getStr("item_to_be_inserted") + " as the item to be inserted.<br>	", false);

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("__args");
                    variables("__args").declareVariable("item", Type.STRING);
                }

                @Override
                protected void passArgs() {
                    variables("__args").set("item", variables("Variables").getStr("item_to_be_inserted"));
                }
            };

            call_47.setFunction(func_24);

            final Command impasse_48 = new Command("impasse_48") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    popVarStack("Variables");
                    log("Algorithm Terminated", false);

                }
            };

            final Function func_49 = new Function("func_49") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("__return").declareVariable("Remove", Type.STRING);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{1}, HighLightingLevel.Secondary);

                    log("Entering the remove procedure", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Variables");

                }
            };

            func_49.setIdentifier("Remove");

            final Command cmd_50 = new Command("cmd_50") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{20, 2, 6});

                    log("<b> Key ideas for Step 1 </b> <br> In the case of removal, step 1 entails chosing not to perform the removal when the Queue is already empty.<br>	", false);

                }
            };

            final Command cmd_51 = new Command("cmd_51") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{9, 8});

                    log("<b> Key ideas for Step 2 </b> <br> Step 2 entails fetching the node to be removed from the data-list. To make it easier to track of things, we shall refer to this node as Current Node hereafter. The node to be fetched will depend on the type of ADT we are dealing with. It will be the Head node in a Queue, the Top Of Stack node in a Stack and so on. We then retrieve the content of the node and store it somewhere else. Although we could have cleared the contents of the Current Node since its contents are meant to be treated as removed, this is not compulsory as moving the Current Node into the free-list will make the node behave like any other free node, meaning that the contents of the node will be ignored when traversing or searching the content of the ADT (implementation detail: the procedures that perform Search and Traversal only make use of the data-list) and will be over-written when the time to use the node to store another data comes.<br>	", false);

                }
            };

            final Command cmd_52 = new Command("cmd_52") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{14, 11, 15, 17, 13, 16, 12});

                    log("<b> Key ideas for Step 3 </b> <br> Step 3 entails transferring the Current node to the free list and and making appropriate changes to pointer values to reflect that fact. <br>	", false);

                }
            };

            final IfOrElse if_53 = new IfOrElse("if_53") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{2}, HighLightingLevel.Secondary);

                    log("Beginning executing the steps", false);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("head_pointer") == -1;
                }
            };

            final Command cmd_54 = new Command("cmd_54") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    output("Queue is empty. Removal could not be performed.");
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{3}, HighLightingLevel.Secondary);

                    log("Since Head_Pointer is in fact null, we know that the Queue is empty and thus move into the block that does not perform any action. (No removal is performed.)", false);

                }
            };

            final Command return_55 = new Command("return_55") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("__return").set("Remove", "");
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{5}, HighLightingLevel.Secondary);

                    log("Since there is no item to be returned, we end the function here with an empty return statement.", false);

                }
            };

            final Command else_56 = new Command("else_56") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{6}, HighLightingLevel.Secondary);

                    log("Since Head_Pointer is not null, we know that the Queue is not empty and thus move into the block that performs the removal.", false);

                }
            };

            final Command cmd_57 = new Command("cmd_57") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                    variables("Variables").declareVariable("output", Type.STRING);
                    variables("Variables").set("output", nodes("NodeArray").getStr("Item", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{9, 8});

                    log("We moved to the Head node (by setting Current Pointer to Head Pointer) and retrieved the data item stored in the head node into another variable which will be later returned.", false);

                }
            };

            final Command cmd_58 = new Command("cmd_58") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{14, 13, 11, 12});

                    log("Some key ideas for the upcoming pointer adjustments. We now consider the changes that need to be made to the special pointers 'Head_Pointer' and 'Tail_Pointer'. Since the Current Node was the head of the data-list, the node it points to is the choice for the next head of the the data-list (the head node points to the node that was filled right after it, which points to the node filled right after that node and so on). <br><br>		The only event in which removing the head of the data-list would affect the Tail_Pointer is when the head node itself was the tail node (there was only one node in the Queue which was both the head and tail). If this was the case we would have to update the value of Tail_Pointer to null since the tail node would be removed too when the head nod was removed.<br><br>		Note that in removal we handle pointer corrections concerning the data list first since attaching the removed node to the free-list would the over-write pointer value in the Current Node which we needed for setting the  pointers involved with the data-list. The situation is reversed in insertion where we handle pointer corrections involving the free-list first since attaching the Current Node to the data-list would over-write pointer value in the Current Node which would be necessary in setting the pointers involved with the free-list.<br>	", false);

                }
            };

            final Command cmd_59 = new Command("cmd_59") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("head_pointer", nodes("NodeArray").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{11}, HighLightingLevel.Secondary);

                }
            };

            final IfOrElse if_60 = new IfOrElse("if_60") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{12}, HighLightingLevel.Secondary);

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") == variables("Pointers").getInt("tail_pointer");
                }
            };

            final Command cmd_61 = new Command("cmd_61") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("tail_pointer", -1);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{13}, HighLightingLevel.Secondary);

                }
            };

            final EndIf endif_62 = new EndIf("endif_62") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{14}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_63 = new Command("cmd_63") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{17, 16});

                    log("Some key ideas for attacting a data-node to the free-list. <br> The Current Node (the one indicated by 'Current_Pointer' from which data was removed) is being removed from the data-list and linked to the free-list. In our implementation, we only keep track of the head of the the free-list. We could traverse the free-list to find out the tail node at which the free-list ends and attach the Current Node to it. However, there is a faster and more effecient alternative of using the Current Node as the head itself and attaching the rest of the free-list to the Current Node. The Free_Pointer (pointer to head of free-list) is stored as the pointer value of the Current Node and the Free_Pointer will be updated to point to the Current Node, the new head of the free-list.<br>	", false);

                }
            };

            final Command cmd_64 = new Command("cmd_64") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    nodes("NodeArray").set("Pointer", variables("Pointers").getInt("current_pointer"), variables("Pointers").getInt("free_pointer"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{16}, HighLightingLevel.Secondary);

                }
            };

            final Command cmd_65 = new Command("cmd_65") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("Pointers").set("free_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{17}, HighLightingLevel.Secondary);

                }
            };

            final Command return_66 = new Command("return_66") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    variables("__return").set("Remove", variables("Variables").getStr("output"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{19}, HighLightingLevel.Secondary);

                    log("We now returned the data from the removed node.", false);

                }
            };

            final EndIf endif_67 = new EndIf("endif_67") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{20}, HighLightingLevel.Secondary);

                }
            };

            final EndFunction endfunc_68 = new EndFunction("endfunc_68") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{21}, HighLightingLevel.Secondary);

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Variables");
                    popVarStack("__args");
                }
            };

            final Command cmd_69 = new Command("cmd_69") {
                @Override
                protected void onExecution() {
                    pushVarStack("Variables");
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    hideSourceCodeUnit("Initialization_Pseudocode");
                    hideSourceCodeUnit("Initialization_Python_Code");
                    hideSourceCodeUnit("Remove_Pseudocode");
                    hideSourceCodeUnit("Search_Pseudocode");
                    hideSourceCodeUnit("Insert_Pseudocode");

                    showSourceCodeUnit("Remove_Pseudocode");

                    log("We'll be performing an removal operation on the Queue where we will:<br><br>		Step 0: Obtain an input and call the removal function.<br><br>		Step 1: Check if the operation is possible. <br><br>		Step 2: If possible, perform the removal operation. <br><br>		Step 3: Tie up loose ends. Since we end up modifying values withing the data and free list in Step 2, we need to make modifications to pointer values to ensure they reflect the new state of the Queue. We also keep in mind to make modifications in such an order so as to not over-write a pointer value while its information is still required for subsequent modifications. Consider the effect of the modifications on special pointers as well as those belonging to nodes on the free and data lists. <br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Remove", cmd_69);

            final Call call_70 = new Call("call_70") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{11, 1, 14, 8, 15, 4, 3, 6, 7, 9, 19, 2, 13, 17, 10, 20, 5, 21, 18, 16, 12});

                    log("We will now be calling the remove procedure for the Queue.", false);

                }

                @Override
                protected void buildArgs() {
                    pushVarStack("__args");
                }

                @Override
                protected void passArgs() {
                }
            };

            call_70.setFunction(func_49);

            final Command cmd_71 = new Command("cmd_71") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    output("" + variables("__return").getStr("Remove") + "");
                    log("Outputting the value returned by the Remove function.", false);

                }
            };

            final Command impasse_72 = new Command("impasse_72") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null);

                    getSourceCodeUnit("Initialization_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Initialization_Python_Code").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Search_Pseudocode").highlight(null, HighLightingLevel.Secondary);
                    getSourceCodeUnit("Insert_Pseudocode").highlight(null, HighLightingLevel.Secondary);

                    popVarStack("Variables");
                    log("Algorithm Terminated", false);

                }
            };

            // Chaining
            func_6.chainTo(cmd_7);
            cmd_7.chainTo(cmd_8);
            cmd_8.chainTo(cmd_9);
            cmd_9.chainTo(while_10);
            while_10.chainTo(cmd_11);
            cmd_11.chainTo(if_12);
            if_12.chainTo(cmd_13);

            if_12.elseChainTo(else_14);
            else_14.chainTo(cmd_15);

            if_12.endTo(endif_16);
            while_10.blockChainTo(cmd_18);
            while_10.endTo(endwhile_17);

            func_6.endTo(endfunc_19);

            func_24.chainTo(cmd_25);
            cmd_25.chainTo(cmd_26);
            cmd_26.chainTo(cmd_27);
            cmd_27.chainTo(if_28);
            if_28.chainTo(cmd_29);

            if_28.elseChainTo(else_30);
            else_30.chainTo(cmd_31);
            cmd_31.chainTo(cmd_32);
            cmd_32.chainTo(cmd_33);
            cmd_33.chainTo(if_34);
            if_34.chainTo(cmd_35);

            if_34.endTo(endif_36);
            if_34.blockChainTo(cmd_37);
            cmd_37.chainTo(cmd_38);
            cmd_38.chainTo(cmd_39);
            cmd_39.chainTo(if_40);
            if_40.chainTo(cmd_41);

            if_40.endTo(endif_42);
            if_28.endTo(endif_43);
            func_24.endTo(endfunc_44);

            func_49.chainTo(cmd_50);
            cmd_50.chainTo(cmd_51);
            cmd_51.chainTo(cmd_52);
            cmd_52.chainTo(if_53);
            if_53.chainTo(cmd_54);
            cmd_54.chainTo(return_55);

            if_53.elseChainTo(else_56);
            else_56.chainTo(cmd_57);
            cmd_57.chainTo(cmd_58);
            cmd_58.chainTo(cmd_59);
            cmd_59.chainTo(if_60);
            if_60.chainTo(cmd_61);

            if_60.endTo(endif_62);
            if_60.blockChainTo(cmd_63);
            cmd_63.chainTo(cmd_64);
            cmd_64.chainTo(cmd_65);
            cmd_65.chainTo(return_66);

            if_53.endTo(endif_67);
            func_49.endTo(endfunc_68);

            cmd_1.chainTo(cmd_2);
            cmd_2.chainTo(cmd_3);
            cmd_3.chainTo(cmd_4);
            cmd_4.chainTo(impasse_5);
            cmd_20.chainTo(input_21);
            input_21.chainTo(call_22);
            call_22.chainTo(impasse_23);
            cmd_45.chainTo(input_46);
            input_46.chainTo(call_47);
            call_47.chainTo(impasse_48);
            cmd_69.chainTo(call_70);
            call_70.chainTo(cmd_71);
            cmd_71.chainTo(impasse_72);

        }
    }
}
