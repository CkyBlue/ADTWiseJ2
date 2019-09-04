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
    static Content algorithmTree = new Content();

    static {
        {
            final Command cmd_1 = new Command("cmd_1") {
                @Override
                protected void onExecution() {
                    buildVarStack("Locals");

                    // Building Code Units
                    buildSourceCodeUnits(new String[]{"Init_Pseudocode", "Remove_Pseudocode", "Search_Pseudocode", "Insert_Pseudocode", "CarbonCode"});

                    String init_pseudocode_str = "TYPE Node \n" +
                            "                DECLARE Item AS STRING \n" +
                            "                DECLARE Pointer AS INTEGER \n" +
                            "ENDTYPE \n" +
                            "DECLARE NodeArray AS ARRAY[0: 9] OF Node \n" +
                            " \n" +
                            "DECLARE Head_Pointer, Tail_Pointer, Free_Pointer AS INTEGER \n" +
                            "Head_Pointer <- -1 \n" +
                            "Tail_Pointer <- -1 \n" +
                            "Free_Pointer <- 0 \n" +
                            " \n" +
                            "FOR Count <- 0 TO SIZEOF(NodeArray) - 1  \n" +
                            "                NodeArray[Count].Pointer <- Count + 1 \n" +
                            "ENDFOR  \n" +
                            " \n" +
                            "NodeArray[SIZEOF(NodeArray) - 1].Pointer <- -1 \n" +
                            "";
                    getSourceCodeUnit("Init_Pseudocode").setText(init_pseudocode_str);

                    String remove_pseudocode_str = "FUNCTION Remove() RETURNS STRING  \n" +
                            "                IF Head_Pointer = -1 THEN \n" +
                            "                                OUTPUT \"Error: Removal could not be performed as the Queue only contains free nodes.\" \n" +
                            "                                 \n" +
                            "                                RETURN \n" +
                            "                ELSE \n" +
                            " \n" +
                            "                                Current_Pointer <- Head_Pointer \n" +
                            "                                Output_From_Queue <- Node_Array[Current_Pointer].Item \n" +
                            " \n" +
                            "                                Head_Pointer <- Node_Array[Current_Pointer].Pointer \n" +
                            "                                IF Current_Pointer = Tail_Pointer THEN \n" +
                            "                                                Tail_Pointer <- -1 \n" +
                            "                                ENDIF \n" +
                            " \n" +
                            "                                Node_Array[Current_Pointer].Pointer <- Free_Pointer \n" +
                            "                                Free_Pointer <- Current_Pointer \n" +
                            " \n" +
                            "                                RETURN Output_From_Queue \n" +
                            "                ENDIF \n" +
                            "ENDFUNCTION \n" +
                            "";
                    getSourceCodeUnit("Remove_Pseudocode").setText(remove_pseudocode_str);

                    String search_pseudocode_str = "PROCEDURE Search(Data_Item)  \n" +
                            "                Item_Found <- False \n" +
                            "                Current_Pointer <- Head_pointer \n" +
                            " \n" +
                            "                WHILE Current_Pointer <> -1 AND NOT Item_Found \n" +
                            "                                IF Node_Array[Current_Pointer].Item = Item_To_Be_Searched THEN \n" +
                            "                                                Item_Found <- True \n" +
                            "                                ELSE  \n" +
                            "                                                CurrentPointer <- Node_Array[Current_Pointer].Pointer \n" +
                            "                                ENDIF \n" +
                            "                ENDWHILE \n" +
                            " \n" +
                            "                IF NOT Item_Found THEN \n" +
                            "                                OUTPUT \"No matching item was found.\" \n" +
                            "                ELSE \n" +
                            "                                OUTPUT \"Matching item was found at index \" + TO_STRING(Current_Pointer) \n" +
                            "                ENDIF \n" +
                            "ENDPROCEDURE         \n" +
                            "";
                    getSourceCodeUnit("Search_Pseudocode").setText(search_pseudocode_str);

                    String insert_pseudocode_str = "PROCEDURE Insert(Data_Item) \n" +
                            "        IF Free_Pointer = -1 THEN \n" +
                            "                OUTPUT \"Error: Insertion could not be performed as the Queue has no free nodes.\" \n" +
                            "        ELSE \n" +
                            "                Current_Pointer <- Free_Pointer \n" +
                            "                Node_Array[Current_Pointer].Item <- Data_Item \n" +
                            " \n" +
                            "                Free_Pointer <- Node_Array[Current_Pointer].Pointer \n" +
                            " \n" +
                            "                IF Tail_Pointer <> -1 THEN \n" +
                            "                        Node_Arry[Tail_Pointer].Pointer <- Current_Pointer \n" +
                            "                ENDIF \n" +
                            "                Node_Array[Current_Pointer].Pointer <- -1 \n" +
                            "                 \n" +
                            "                Tail_Pointer <- Current_Pointer      \n" +
                            "                IF Head_Pointer = -1 THEN  \n" +
                            "                        Head_Pointer <- Current_Pointer \n" +
                            "                ENDIF \n" +
                            " \n" +
                            "        ENDIF \n" +
                            "ENDPROCEDURE \n" +
                            "";
                    getSourceCodeUnit("Insert_Pseudocode").setText(insert_pseudocode_str);

                    String carboncode_str = "<~        directive_type = \"src\", \n" +
                            "        location = \"pseudo_init.txt\", \n" +
                            "        name = \"Init_Pseudocode\", \n" +
                            "        handler = \"init\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"src\", \n" +
                            "        location = \"pseudo_remove.txt\", \n" +
                            "        name = \"Remove_Pseudocode\", \n" +
                            "        handler = \"remove\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"src\", \n" +
                            "        location = \"pseudo_search.txt\", \n" +
                            "        name = \"Search_Pseudocode\", \n" +
                            "        handler = \"search\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"src\", \n" +
                            "        location = \"pseudo_insert.txt\", \n" +
                            "        name = \"Insert_Pseudocode\", \n" +
                            "        handler = \"insert\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"log\", \n" +
                            "        location = \"logs.xml\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"log\", \n" +
                            "        location = \"adt_generic_logs.xml\" ~> \n" +
                            " \n" +
                            "<~        directive_type = \"log\", \n" +
                            "        location = \"linked_list_generic_logs.xml\" ~> \n" +
                            " \n" +
                            "Initializer at CMD 0  \n" +
                            "==================================== \n" +
                            " \n" +
                            "[**__init__**:         \n" +
                            "                        LIGHT: * @ !; LIGHT: init @ 1 - 16;   \n" +
                            "                        LOG: <ADT, init_1: ADT = \"Queue\"> \n" +
                            "                        ] \n" +
                            "         \n" +
                            "        [:        DIM: List @ STR Data | INT Pointer @ 10;  \n" +
                            "                                LIGHT: init @ 1 - 5;  \n" +
                            "                                LOG: <ADT, init_2: ADT = \"Queue\"> \n" +
                            "                                ] \n" +
                            " \n" +
                            "        [:        BUILD: p, Pointers;                 \n" +
                            "                 \n" +
                            "                DIM: INT head_pointer @ p; SET: head_pointer @ p, -1; \n" +
                            "                DIM: INT tail_pointer @ p; SET: tail_pointer @ p, -1; \n" +
                            "                DIM: INT current_pointer @ p; SET: current_pointer @ p, -1; \n" +
                            "                DIM: INT free_pointer @ p;          \n" +
                            "                 \n" +
                            "                LIGHT: init @ 7 - 10; \n" +
                            "                 \n" +
                            "                LOG: <Linked-List, special_pointers: ADT = \"Queue\"> [br] <Linked-List, current_pointer_1:> [br]         <Linked-List, free_list_1:> [br] <Linked-List, data_list:> [br] <Queue, special_pointers:> [br] <Queue, node_pointers:>; \n" +
                            "                ] \n" +
                            "         \n" +
                            "        [:        EVAL: for (int count = 0\\; count \\< <^List>.getSize()\\; count++) {; \n" +
                            "                SET: List[Pointer][count], count + 1 ; \n" +
                            "                EVAL: }; \n" +
                            " \n" +
                            "                SET: List[Pointer][<^List>.getSize() - 1], -1; \n" +
                            "                LIGHT: init @ 12 - 14, 16;  \n" +
                            "                LOG: <Linked-List, free_list_2: ADT = \"Queue\">; \n" +
                            "                ] \n" +
                            " \n" +
                            "[IMPASSE:] \n" +
                            " \n" +
                            "===================================== \n" +
                            " \n" +
                            "Search at CMD 5 \n" +
                            "===================================== \n" +
                            "                 \n" +
                            "[FUNC: VOID Search (STR search_item):  \n" +
                            "                        LIGHT: search @ 1; \n" +
                            "                        LOG: Entering the Search procedure.;  \n" +
                            "                        ] \n" +
                            "         \n" +
                            "        [:        DIM: BOOL item_found; SET: item_found, false; \n" +
                            "                                 LIGHT: search @ 2;         \n" +
                            "                                 LOG: <ADT, item_found_flag:>; \n" +
                            "                                 ] \n" +
                            "         \n" +
                            "        [:         SET: current_pointer @ p, <head_pointer @ p>; \n" +
                            "                                 LIGHT: search @ 3;         \n" +
                            "                                 LOG: Some key ideas before entering execution. <Linked-List, current_pointer_1:>; \n" +
                            "                                 ] \n" +
                            "         \n" +
                            "        [:         \n" +
                            "                                LIGHT: search @ 5 - 11;  \n" +
                            "                                LOG: <Linked-List, current_pointer_2: ADT = \"Queue\", entry = \"head\"> [br] <Linked-List, search_traversal:>;  \n" +
                            "                                ] \n" +
                            "         \n" +
                            "        [WHILE: (<current_pointer @ p> != -1 && ! <item_found>) : \n" +
                            "                                LIGHT: search @ 5; \n" +
                            "                                ] \n" +
                            "                 \n" +
                            "                [:         \n" +
                            "                                        LOG: While loop's condition evaluated to true.; \n" +
                            "                                        ] \n" +
                            "                 \n" +
                            "                [IF: (<^List[Data][<current_pointer @ p>]> == <search_item>): \n" +
                            "                                        LIGHT: search @ 6; \n" +
                            "                                        ] \n" +
                            "                         \n" +
                            "                        [:         SET: item_found, true; \n" +
                            "                                                LIGHT: search @ 7; \n" +
                            "                                                ] \n" +
                            "                 \n" +
                            "                [ELSE: \n" +
                            "                                        LIGHT: search @ 8; \n" +
                            "                                        ] \n" +
                            "                         \n" +
                            "                        [: SET: current_pointer @ p, <^List[Pointer][<current_pointer @ p>]>; \n" +
                            "                                                LIGHT: search @ 9; \n" +
                            "                                                ] \n" +
                            " \n" +
                            "                [ENDIF: \n" +
                            "                                        LIGHT: search @ 10; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "        [ENDWHILE:  \n" +
                            "                                LIGHT: search @ 11; \n" +
                            "                                ] \n" +
                            "         \n" +
                            "        [:        EVAL: if (!<item_found>) {; OUTPUT:  <ADT, matched_false:>; EVAL : } else {; OUTPUT: <ADT, matched_true:>; EVAL: }; \n" +
                            "                                 LIGHT: search @ 13 - 17;  \n" +
                            "                                 LOG: <ADT, testing_match:>; \n" +
                            "                                 ] \n" +
                            " \n" +
                            "[ENDFUNC:  \n" +
                            "        LIGHT : search @ 18; \n" +
                            "        ] \n" +
                            " \n" +
                            " \n" +
                            "[**Search**: DIM: STR search_item; \n" +
                            "                        LIGHT: * @ !; LIGHT: search @ 1 - 18;  \n" +
                            "                        LOG: <Linked-List, search: ADT = \"Queue\", entry = \"head\", end = \"tail\"> \n" +
                            "                        ] \n" +
                            "         \n" +
                            "        [INPUT: STR search_item: \n" +
                            "                                LOG: <ADT, input_item: action = \"searched\">; \n" +
                            "                                ] \n" +
                            "         \n" +
                            "        [CALL: Search (<search_item>):  \n" +
                            "                                LOG: <ADT, calling_with: procedure = \"Search\", value = \"<search_item>\", action = \"searched\">; \n" +
                            "                                ] \n" +
                            " \n" +
                            "[IMPASSE:] \n" +
                            " \n" +
                            "==================================== \n" +
                            " \n" +
                            "Insert @ 23 \n" +
                            "==================================== \n" +
                            " \n" +
                            "[FUNC: VOID Insert (STR item):  \n" +
                            "                        LIGHT: insert @ 1; \n" +
                            "                        LOG: Entering the insert procedure;         \n" +
                            "                        ] \n" +
                            " \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 2, 4, 20; \n" +
                            "                                LOG: [b] Key ideas for Step 1 [\\b] [br] <Linked-List, insertion_step_1: ADT = \"Queue\">; \n" +
                            "                                ] \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 5, 6; \n" +
                            "                                LOG: [b] Key ideas for Step 2 [\\b] [br] <Linked-List, insertion_step_2: ADT = \"Queue\">; \n" +
                            "                                ] \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 8 - 18; \n" +
                            "                                LOG: [b] Key ideas for Step 3 [\\b] [br] <Linked-List, insertion_step_3: ADT = \"Queue\">; \n" +
                            "                                ] \n" +
                            "         \n" +
                            "        [IF: (<free_pointer @ p> == -1) : \n" +
                            "                                LIGHT: insert @ 2; \n" +
                            "                                LOG: Beginning executing the steps. ;  \n" +
                            "                                ] \n" +
                            " \n" +
                            "                [:         OUTPUT: Queue has no free nodes. Insertion could not be performed.;         \n" +
                            "                                                LIGHT: insert @ 3; \n" +
                            "                                                LOG: Since Free_Pointer is -1 (null), we enter the block that does not perform any action (No insertion is performed).;  \n" +
                            "                                                ] \n" +
                            "         \n" +
                            "        [ELSE:  \n" +
                            "                                LIGHT: insert @ 4; \n" +
                            "                                LOG: The Free_Pointer is not null so we are able to perform Insertion.; \n" +
                            "                                ] \n" +
                            " \n" +
                            "                [: SET: current_pointer @ p, <free_pointer @ p>; SET: List[Data][<current_pointer @ p>], <item>; \n" +
                            "                                        LIGHT: insert @ 5 - 6; \n" +
                            "                                        LOG: <Linked-List, free_ptr_as_cur_ptr:>; \n" +
                            "                                        ] \n" +
                            "                 \n" +
                            "                [: SET: free_pointer @ p, <^List[Pointer][<current_pointer @ p>]>; \n" +
                            "                                        LIGHT: insert @ 8; \n" +
                            "                                        LOG:  <Linked-List, next_free_ptr:>;  \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [:  \n" +
                            "                                        LIGHT: insert @ 10 - 13; \n" +
                            "                                        LOG: [b] Key ideas for linking to data-list [\\b] [br] <Linked-List, current_node_into_data_list:> [br] <Queue, current_node_into_data_list:> ; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [IF: (<tail_pointer @ p> != -1): \n" +
                            "                                        LIGHT: insert @ 10; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                        [: SET: List[Pointer][<tail_pointer @ p>], <current_pointer @ p>; \n" +
                            "                                                LIGHT: insert @ 11; \n" +
                            "                                                ] \n" +
                            "                 \n" +
                            "                [ENDIF: \n" +
                            "                                        LIGHT: insert @ 12; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [: SET: List[Pointer][<current_pointer @ p>], -1 \n" +
                            "                                        LIGHT: insert @ 13; \n" +
                            "                                        ] \n" +
                            "                 \n" +
                            "                [:  \n" +
                            "                                        LIGHT: insert @ 15 - 18; \n" +
                            "                                        LOG: [b] Key ideas for updating special pointers [\\b] [br] <Queue, special_ptr_updates_in_insertion:> ; \n" +
                            "                                        ]                 \n" +
                            " \n" +
                            "                [: SET: tail_pointer @ p, <current_pointer @ p>; \n" +
                            "                                        LIGHT: insert @ 15; \n" +
                            "                                        ] \n" +
                            "                 \n" +
                            "                [IF: (<head_pointer @ p> == -1): \n" +
                            "                                        LIGHT: insert @ 16; \n" +
                            "                                        ] \n" +
                            "                         \n" +
                            "                        [: SET: head_pointer @ p, <current_pointer @ p>; \n" +
                            "                                                LIGHT: insert @ 17; \n" +
                            "                                                ] \n" +
                            "                 \n" +
                            "                [ENDIF: \n" +
                            "                                        LIGHT: insert @ 18; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "        [ENDIF: \n" +
                            "                                LIGHT: insert @ 20; \n" +
                            "                                ] \n" +
                            " \n" +
                            "[ENDFUNC: \n" +
                            "                        LIGHT: insert @ 21; \n" +
                            "                        ] \n" +
                            " \n" +
                            "[**Insert**: DIM: STR item_to_be_inserted; \n" +
                            "                        LIGHT: * @ !; LIGHT: insert @ 1 - 21;  \n" +
                            "                        LOG: <Linked-List, modify_steps: operation = \"insert\", ADT = \"Queue\"> \n" +
                            "                        ] \n" +
                            " \n" +
                            "        [INPUT: STR item_to_be_inserted:  \n" +
                            "                                LOG: <ADT, input_item: action = \"inserted\">; \n" +
                            "                                ] \n" +
                            " \n" +
                            "        [CALL: Insert (<item_to_be_inserted>): \n" +
                            "                                LOG: <ADT, calling_with: procedure = \"Insert\", value = \"<item_to_be_inserted>\", action = \"inserted\">; \n" +
                            "                                ] \n" +
                            " \n" +
                            "[IMPASSE:] \n" +
                            " \n" +
                            "==================================== \n" +
                            " \n" +
                            "Remove @ 48 \n" +
                            "==================================== \n" +
                            " \n" +
                            "[FUNC : STR Remove (STR item): \n" +
                            "                        LIGHT: remove @ 1; \n" +
                            "                        LOG: Entering the remove procedure;        ] \n" +
                            " \n" +
                            " \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 2, 6, 20; \n" +
                            "                                LOG: [b] Key ideas for Step 1 [\\b] [br] <Linked-List, removal_step_1: ADT = \"Queue\">; \n" +
                            "                                ] \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 8, 9; \n" +
                            "                                LOG: [b] Key ideas for Step 2 [\\b] [br] <Linked-List, removal_step_2:>; \n" +
                            "                                ] \n" +
                            "        [:          \n" +
                            "                                LIGHT: insert @ 11 - 17; \n" +
                            "                                LOG: [b] Key ideas for Step 3 [\\b] [br] <Linked-List, removal_step_3:>; \n" +
                            "                                ] \n" +
                            " \n" +
                            "        [IF: (<head_pointer @ p> == -1): \n" +
                            "                                LIGHT: remove @ 2; \n" +
                            "                                LOG: Beginning executing the steps; \n" +
                            "                                ] \n" +
                            " \n" +
                            "                [: OUTPUT: Queue is empty. Removal could not be performed.; \n" +
                            "                                        LIGHT: remove @ 3; \n" +
                            "                                        LOG: Since Head_Pointer is in fact null, we know that the Queue is empty and thus move into the block that does not perform any action. (No removal is performed.); \n" +
                            "                                        ] \n" +
                            "         \n" +
                            "                [RETURN: : \n" +
                            "                                        LIGHT: remove @ 5; \n" +
                            "                                        LOG: Since there is no item to be returned, we end the function here with an empty return statement.; \n" +
                            "                                        ]  \n" +
                            "         \n" +
                            "        [ELSE: \n" +
                            "                                        LIGHT: remove @ 6; \n" +
                            "                                        LOG: Since Head_Pointer is not null, we know that the Queue is not empty and thus move into the block that performs the removal.; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [: SET: current_pointer @ p, <head_pointer @ p>; DIM: STR output; SET: output, <^List[Pointer][<current_pointer @ p>]>; \n" +
                            "                                        LIGHT: remove @ 8, 9; \n" +
                            "                                        LOG: We moved to the Head node (by setting Current Pointer to Head Pointer) and retrieved the data item stored in the head node into another variable which will be later returned.; \n" +
                            "                                        ] \n" +
                            "                 \n" +
                            "        [: \n" +
                            "                                LIGHT: remove @ 11 - 14; \n" +
                            "                                LOG: Some key ideas for the upcoming pointer adjustments. <Queue, special_ptr_updates_in_removal:>; \n" +
                            "                                ] \n" +
                            " \n" +
                            "                [: SET: head_pointer @ p, <^List[Pointer][<current_pointer @ p>]>; \n" +
                            "                                        LIGHT: remove @ 11; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [IF: (<current_pointer @ p> == <tail_pointer @ p>): \n" +
                            "                                        LIGHT: remove @ 12; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                        [: SET: tail_pointer @ p, -1; \n" +
                            "                                                LIGHT: remove @ 13; \n" +
                            "                                                ] \n" +
                            " \n" +
                            "                [ENDIF: \n" +
                            "                                        LIGHT: remove @ 14; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [:  \n" +
                            "                                        LIGHT: remove @ 16, 17; \n" +
                            "                                        LOG: Some key ideas for attacting a data-node to the free-list. [br] <Linked-List, current_node_into_free_list:>; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [: SET: List[Pointer][<current_pointer @ p>], <free_pointer @ p>; \n" +
                            "                                        LIGHT: remove @ 16; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [: SET: free_pointer @ p, <current_pointer @ p>; \n" +
                            "                                        LIGHT: remove @ 17; \n" +
                            "                                        ] \n" +
                            " \n" +
                            "                [RETURN: <output> : \n" +
                            "                                        LIGHT: remove @ 19; \n" +
                            "                                        LOG: We now returned the data from the removed node.; \n" +
                            "                                        ] \n" +
                            "         \n" +
                            "        [ENDIF: \n" +
                            "                                LIGHT: remove @ 20; \n" +
                            "                                ] \n" +
                            " \n" +
                            "[ENDFUNC: \n" +
                            "                        LIGHT: remove @ 21; \n" +
                            "                        ] \n" +
                            " \n" +
                            " \n" +
                            "[**Remove**: DIM: STR item_to_be_removed; \n" +
                            "                        LIGHT: * @ !;  \n" +
                            "                        LIGHT: insert @ 1 - 21;  \n" +
                            "                        LOG: <Linked-List, modify_steps: operation = \"removal\", ADT = \"Queue\"> \n" +
                            "                        ] \n" +
                            " \n" +
                            "        [INPUT: STR item_to_be_removed: \n" +
                            "                                LOG: <ADT, input_item: action = \"removed\">; \n" +
                            "                                ] \n" +
                            " \n" +
                            "[CALL: Remove (<item_to_be_removed>): \n" +
                            "                        LOG: <ADT, calling_with: procedure = \"Remove\", value = \"<item_to_be_removed>\", action = \"removed\">; \n" +
                            "                        ] \n" +
                            " \n" +
                            "[: OUTPUT: <*RETURN>: \n" +
                            "                        LOG: Outputting the value returned by the Remove function.;] \n" +
                            " \n" +
                            "[IMPASSE:] \n" +
                            "==================================== \n" +
                            "";
                    getSourceCodeUnit("CarbonCode").setText(carboncode_str);

                    getSourceCodeUnit("CarbonCode").highlight(new int[]{36, 34, 35, 33});

                    getSourceCodeUnit("CarbonCode").highlight(null);

                    getSourceCodeUnit("Init_Pseudocode").highlight(new int[]{5, 7, 10, 8, 11, 14, 12, 16, 1, 15, 4, 3, 6, 2, 9, 13});

                    log("We begin by <b> setting up the data structures <\b> required for implementing the Queue.<br>	", false);
                }
            };

            algorithmTree.setInitializer(cmd_1);

            final Command cmd_2 = new Command("cmd_2") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{39, 40, 41, 38});

                    BluePrint listBluePrint = new BluePrint();
                    listBluePrint.addKey("Data", Type.STRING);
                    listBluePrint.addKey("Pointer", Type.INTEGER);
                    buildNodesStack("List", listBluePrint, 10);
                    getSourceCodeUnit("Init_Pseudocode").highlight(new int[]{5, 1, 4, 3, 2});

                    log("We use an array of custom objects (representing nodes of the Queue) to store the data represening the Queue ADT (Abstract Data Type)<br>	", false);
                }
            };

            final Command cmd_3 = new Command("cmd_3") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{43, 47, 52, 49, 46, 50, 51, 53, 44, 45, 48});

                    buildVarStack("Pointers");
                    variables("Pointers").declareVariable("head_pointer", Type.INTEGER);
                    variables("Pointers").set("head_pointer", -1);
                    variables("Pointers").declareVariable("tail_pointer", Type.INTEGER);
                    variables("Pointers").set("tail_pointer", -1);
                    variables("Pointers").declareVariable("current_pointer", Type.INTEGER);
                    variables("Pointers").set("current_pointer", -1);
                    variables("Pointers").declareVariable("free_pointer", Type.INTEGER);
                    getSourceCodeUnit("Init_Pseudocode").highlight(new int[]{7, 10, 8, 9});

                    log("We make use of special 'pointers' to store important information about the Queue. Nodes withing the Queue ADT are chained together (using pointer values) to link interrelated free and occupied nodes.<br>	 <br> The variable 'Current_Pointer' is a special pointer value used when navigating through different nodes within a chain link-by-link.<br>	 <br>	 Regarding the chain of free nodes (free-list), each free node points to the next free node in the chain. When a free node is used, its pointer value immediately reveals whether there is another free node available or not, (depending on whether it has a null pointer value or not), and if there is, what that value is. The free node next in line for being used (or the null reference for it when there is no such node) is stored at the Free_Pointer.<br>	 <br> Regarding the chain of occupied nodes (the data-list), each occupied node points to the next occupied node that follows the logical order of traversal. The first node to be accessed is the head of the chain. It can be the Top Of Stack in a Stack ADT, otherwise the Head of a Queue in a Queue ADT and so on.<br>	 <br> The variable 'Head_Pointer' indicates the head node (the first addition to the Queue), whereas the variable 'Tail_pointer' indicates the tail node (the last addition to the Queue). These pointer allows a Queue to identify which node to remove during a removal operation and which node to link to when performing an insertion. The free pointer is another special pointer whose job is to indicate the head of the chain of free nodes (the free-list). <br>	 <br> Since a Queue is a FIFO (First-In-First-Out), when traversing the Queue, we begin at the head, the head points to the node that was filled right after it, which points to the node that filled subsequently after it, and so on until we reach the tail node which holds a null pointer value (doesn't point to anything).This linkage allows the Queue to determine which node logically comes after which node, regardless of jumbled index positions. For e.g if all the nodes in the array are filled and then the nodes at the beginning are popped out, a gap in the array will arise. New insertions to the array will be able to use this opened up space but despite later additions showing up at lower index positions, the order of items within the Queue will be clear because of the pointer values.<br>	", false);

                }
            };

            final Command cmd_4 = new Command("cmd_4") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{55, 62, 61, 59, 56, 60, 58, 57});

                    for (int count = 0; count < nodes("List").getSize(); count++) {
                        ;
                        nodes("List").set("Pointer", count, count + 1);
                    }
                    ;
                    nodes("List").set("Pointer", nodes("List").getSize() - 1, -1);
                    getSourceCodeUnit("Init_Pseudocode").highlight(new int[]{14, 12, 16, 13});

                    log("Since the nodes in our array are all free nodes at the beginning, when the Queue is still being set up, they are linked together to form the free-list. For convenience, we set the free-list such that each node points to the node an index after it, except for the node at the last index which has a null pointer value.<br>	", false);

                }
            };

            final Command impasse_5 = new Command("impasse_5") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_6 = new Function("func_6") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{71, 74, 72, 73});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{1});

                    log("Entering the Search procedure.", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("search_item", Type.STRING);
                    variables("Locals").set("search_item", variables("args").getStr("search_item"));
                }
            };

            func_6.setIdentifier("Search");

            final Command cmd_7 = new Command("cmd_7") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{78, 76, 79, 77});

                    variables("Locals").declareVariable("item_found", Type.BOOLEAN);
                    variables("Locals").set("item_found", false);
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{2});

                    log("item_found is a boolean flag to check whether a match has already been found before deciding to iterate over to the next node in the data-list. We set it to false at the beginning.<br>	", false);

                }
            };

            final Command cmd_8 = new Command("cmd_8") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{82, 84, 81, 83});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{3});

                    log("Some key ideas before entering execution. The variable 'Current_Pointer' is a special pointer value used when navigating through different nodes within a chain link-by-link.<br>	", false);

                }
            };

            final Command cmd_9 = new Command("cmd_9") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{86, 88, 87, 89});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{5, 7, 10, 8, 11, 6, 9});

                    log("We will use Current_Pointer to iterate over each and every node in the data-list. Since we want to traverse the Queue in a logical order, we begin at the head.<br>	 <br> We test a node for a match if the node exists (Current_Pointer is not null) and if a match has not been found already (item_found is false). If we do not find a match, we set the Current_Pointer to the index the node is pointing to (the node's Pointer value)<br>	", false);

                }
            };

            final DoWhile while_10 = new DoWhile("while_10") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{91, 93, 92});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{5});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") != -1 && !variables("Locals").getBool("item_found");
                }
            };

            final Command cmd_11 = new Command("cmd_11") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{95, 96, 97});

                    log("While loop's condition evaluated to true.", false);

                }
            };

            final IfOrElse if_12 = new IfOrElse("if_12") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{101, 99, 100});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{6});

                }

                @Override
                protected boolean evaluate() {
                    return nodes("List").getStr("Data", variables("Pointers").getInt("current_pointer")) == variables("Locals").getStr("search_item");
                }
            };

            final Command cmd_13 = new Command("cmd_13") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{105, 103, 104});

                    variables("Locals").set("item_found", true);
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{7});

                }
            };

            final Command else_14 = new Command("else_14") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{107, 109, 108});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{8});

                }
            };

            final Command cmd_15 = new Command("cmd_15") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{113, 112, 111});

                    variables("Pointers").set("current_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{9});

                }
            };

            final EndIf endif_16 = new EndIf("endif_16") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{117, 116, 115});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{10});

                }
            };

            final EndWhile endwhile_17 = new EndWhile("endwhile_17") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{119, 121, 120});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{11});

                }
            };

            final Command cmd_18 = new Command("cmd_18") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{124, 123, 126, 125});

                    if (!variables("Locals").getBool("item_found")) {
                        ;
                        output("No matching item was found.<br>	");
                    } else {
                        ;
                        output("Matching item found at index " + variables("Pointers").getInt("current_pointer") + "<br>	");
                    }
                    ;
                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{14, 16, 17, 15, 13});

                    log("We output results based on whether a match was found or not.<br>	", false);

                }
            };

            final EndFunction endfunc_19 = new EndFunction("endfunc_19") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{129, 130, 128});

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{18});

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
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{135, 134, 133, 136});

                    variables("Locals").declareVariable("search_item", Type.STRING);
                    getSourceCodeUnit("CarbonCode").highlight(null);

                    getSourceCodeUnit("Search_Pseudocode").highlight(new int[]{5, 7, 10, 8, 11, 14, 12, 16, 17, 18, 1, 15, 4, 3, 6, 2, 9, 13});

                    log("We'll be performing a Search operation on the Queue where we will test every node in the data-list, starting from the head node and ending at the tail, for a match.<br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Search", cmd_20);

            final Input input_21 = new Input("input_21", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{138, 140, 139});

                    variables("Locals").set("search_item", String.valueOf(getInputContent()));
                    log("Input the item to be searched.<br>	", false);

                }
            };

            final Call call_22 = new Call("call_22") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{142, 144, 143});

                    log("Calling the Search procedure with " + variables("Locals").getStr("search_item") + " as the item to be searched.<br>	", false);

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

            call_22.setFunction(func_6);

            final Command impasse_23 = new Command("impasse_23") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_24 = new Function("func_24") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{153, 156, 155, 154});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{1});

                    log("Entering the insert procedure", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("item", Type.STRING);
                    variables("Locals").set("item", variables("args").getStr("item"));
                }
            };

            func_24.setIdentifier("Insert");

            final Command cmd_25 = new Command("cmd_25") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{160, 161, 158, 159});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{20, 4, 2});

                    log("<b> Key ideas for Step 1 <\b> <br> In the case of insertion, step 1 entails chosing not to perform the insertion when the Queue is already full.<br>	", false);

                }
            };

            final Command cmd_26 = new Command("cmd_26") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{165, 162, 163, 164});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{5, 6});

                    log("<b> Key ideas for Step 2 <\b> <br> Step 2 entails fetching the head of the free-list (indicated by Free_Pointer) to get a free node at which we can insert our new entry and then storing the entry at the node. To make it easier to track of things, we shall refer to this node as Current Node hereafter.<br>	", false);

                }
            };

            final Command cmd_27 = new Command("cmd_27") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{168, 169, 167, 166});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{9, 8, 11, 14, 12, 16, 17, 18, 15, 10, 13});

                    log("<b> Key ideas for Step 3 <\b> <br> Step 3 entails transferring a free node to the data list and making appropriate changes to pointer values to reflect that fact. The previously free node at which we store the inserted data (the current node) will no longer remain a free node. The node which it points to becomes the next head of the free-list. The pointer value of the current node, after being used to find the value of the next head of the free-list, is then over-written as the node gets attached to the data-list.<br>	", false);

                }
            };

            final IfOrElse if_28 = new IfOrElse("if_28") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{172, 171, 174, 173});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{2});

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
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{179, 178, 177, 176});

                    output("Queue has no free nodes. Insertion could not be performed.");
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{3});

                    log("Since Free_Pointer is -1 (null), we enter the block that does not perform any action (No insertion is performed).", false);

                }
            };

            final Command else_30 = new Command("else_30") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{182, 181, 184, 183});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{4});

                    log("The Free_Pointer is not null so we are able to perform Insertion.", false);

                }
            };

            final Command cmd_31 = new Command("cmd_31") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{188, 186, 189, 187});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("free_pointer"));
                    nodes("List").set("Data", variables("Pointers").getInt("current_pointer"), variables("Locals").getStr("item"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{5, 6});

                    log("We will use the head of the free-list as the node where we insert our newly added data. <br>	", false);

                }
            };

            final Command cmd_32 = new Command("cmd_32") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{191, 194, 193, 192});

                    variables("Pointers").set("free_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{8});

                    log("The head of the free-list is no longer free since we've entered data into it. Hence, the value of the sepcial pointer 'Free_Pointer' needs to be updated to point to the next node in the free-list. 'Current_Pointer' points to that node where we've added the data so we use the pointer value at this node as the value for the next 'Free_Pointer'.<br>	", false);

                }
            };

            final Command cmd_33 = new Command("cmd_33") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{196, 199, 198, 197});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{13, 12, 11, 10});

                    log("<b> Key ideas for linking to data-list <\b> <br> The Current Node (the one indicated by 'Current_Pointer' which was used to store the inserted data) is being removed from the free-list and linked to the data-list. We must consider what the Current Node should point to and should be pointed at by when linking it into the data-list. <br>	 <br> In a Queue, the last entered data should leave last so the Current Node will be attached as the tail to the data-list. This means that the previous tail must point to Current Node (if a previous tail does exist) and the Current Node must point to null. Note that if the Current Node's pointer value was updated to null before we used the previous value as the new Free_Pointer, we would have lost the required value for the Free_Pointer.<br>	", false);

                }
            };

            final IfOrElse if_34 = new IfOrElse("if_34") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{203, 202, 201});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{10});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("tail_pointer") != -1;
                }
            };

            final Command cmd_35 = new Command("cmd_35") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{205, 206, 207});

                    nodes("List").set("Pointer", variables("Pointers").getInt("tail_pointer"), variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{11});

                }
            };

            final EndIf endif_36 = new EndIf("endif_36") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{209, 210, 211});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{12});

                }
            };

            final Command cmd_37 = new Command("cmd_37") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{215, 214, 213});

                    nodes("List").set("Pointer", variables("Pointers").getInt("current_pointer"), -1);

                }
            };

            final Command cmd_38 = new Command("cmd_38") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{219, 218, 220, 217});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{15, 18, 16, 17});

                    log("<b> Key ideas for updating special pointers <\b> <br> We now consider the changes that need to be made to the special pointers 'Head_Pointer' and 'Tail_Pointer'. Since the Current Node is the tail of the data-list, 'Tail_Pointer' is updated to point to it. If the Current Node is the very first addition to the dta-list (if Head_Pointer has a null value indicating no head exists) then the Current Node also becomes the head of the data-list.<br>	", false);

                }
            };

            final Command cmd_39 = new Command("cmd_39") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{222, 224, 223});

                    variables("Pointers").set("tail_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{15});

                }
            };

            final IfOrElse if_40 = new IfOrElse("if_40") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{228, 226, 227});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{16});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("head_pointer") == -1;
                }
            };

            final Command cmd_41 = new Command("cmd_41") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{230, 232, 231});

                    variables("Pointers").set("head_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{17});

                }
            };

            final EndIf endif_42 = new EndIf("endif_42") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{234, 236, 235});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{18});

                }
            };

            final EndIf endif_43 = new EndIf("endif_43") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{238, 240, 239});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{20});

                }
            };

            final EndFunction endfunc_44 = new EndFunction("endfunc_44") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{244, 242, 243});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{21});

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Locals");
                    popVarStack("args");
                }
            };

            final Command cmd_45 = new Command("cmd_45") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{247, 248, 246, 249});

                    variables("Locals").declareVariable("item_to_be_inserted", Type.STRING);
                    getSourceCodeUnit("CarbonCode").highlight(null);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{7, 11, 14, 12, 18, 9, 16, 17, 1, 3, 10, 5, 4, 6, 19, 8, 21, 15, 20, 2, 13});

                    log("We'll be performing an insert operation on the Queue where we will:<br><br>		Step 0: Obtain an input and call the insert function.<br><br>		Step 1: Check if the operation is possible. <br><br>		Step 2: If possible, perform the insert operation. <br><br>		Step 3: Tie up loose ends. Since we end up modifying values withing the data and free list in Step 2, we need to make modifications to pointer values to ensure they reflect the new state of the Queue. We also keep in mind to make modifications in such an order so as to not over-write a pointer value while its information is still required for subsequent modifications. Consider the effect of the modifications on special pointers as well as those belonging to nodes on the free and data lists. <br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Insert", cmd_45);

            final Input input_46 = new Input("input_46", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{253, 251, 252});

                    variables("Locals").set("item_to_be_inserted", String.valueOf(getInputContent()));
                    log("Input the item to be inserted.<br>	", false);

                }
            };

            final Call call_47 = new Call("call_47") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{257, 255, 256});

                    log("Calling the Insert procedure with " + variables("Locals").getStr("item_to_be_inserted") + " as the item to be inserted.<br>	", false);

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

            call_47.setFunction(func_24);

            final Command impasse_48 = new Command("impasse_48") {
                @Override
                protected void onExecution() {
                }
            };

            final Function func_49 = new Function("func_49") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{266, 268, 267});

                    variables("return").set("Remove", "");
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{1});

                    log("Entering the remove procedure", false);

                }

                @Override
                protected void buildLocalData() {
                    pushVarStack("Locals");
                    variables("Locals").declareVariable("item", Type.STRING);
                    variables("Locals").set("item", variables("args").getStr("item"));
                }
            };

            func_49.setIdentifier("Remove");

            final Command cmd_50 = new Command("cmd_50") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{272, 273, 274, 271});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{20, 6, 2});

                    log("<b> Key ideas for Step 1 <\b> <br> In the case of removal, step 1 entails chosing not to perform the removal when the Queue is already empty.<br>	", false);

                }
            };

            final Command cmd_51 = new Command("cmd_51") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{276, 275, 278, 277});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{8, 9});

                    log("<b> Key ideas for Step 2 <\b> <br> Step 2 entails fetching the node to be removed from the data-list. To make it easier to track of things, we shall refer to this node as Current Node hereafter. The node to be fetched will depend on the type of ADT we are dealing with. It will be the Head node in a Queue, the Top Of Stack node in a Stack and so on. We then retrieve the content of the node and store it somewhere else. Although we could have cleared the contents of the Current Node since its contents are meant to be treated as removed, this is not compulsory as moving the Current Node into the free-list will make the node behave like any other free node, meaning that the contents of the node will be ignored when traversing or searching the content of the ADT (implementation detail: the procedures that perform Search and Traversal only make use of the data-list) and will be over-written when the time to use the node to store another data comes.<br>	", false);

                }
            };

            final Command cmd_52 = new Command("cmd_52") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{279, 280, 281, 282});

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{11, 14, 12, 16, 17, 15, 13});

                    log("<b> Key ideas for Step 3 <\b> <br> Step 3 entails transferring the Current node to the free list and and making appropriate changes to pointer values to reflect that fact. <br>	", false);

                }
            };

            final IfOrElse if_53 = new IfOrElse("if_53") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{287, 286, 284, 285});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{2});

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
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{292, 291, 289, 290});

                    output("Queue is empty. Removal could not be performed.");
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{3});

                    log("Since Head_Pointer is in fact null, we know that the Queue is empty and thus move into the block that does not perform any action. (No removal is performed.)", false);

                }
            };

            final Command return_55 = new Command("return_55") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{297, 296, 295, 294});

                    variables("return").set("Remove", "");
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{5});

                    log("Since there is no item to be returned, we end the function here with an empty return statement.", false);

                }
            };

            final Command else_56 = new Command("else_56") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{299, 301, 302, 300});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{6});

                    log("Since Head_Pointer is not null, we know that the Queue is not empty and thus move into the block that performs the removal.", false);

                }
            };

            final Command cmd_57 = new Command("cmd_57") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{305, 307, 304, 306});

                    variables("Pointers").set("current_pointer", variables("Pointers").getInt("head_pointer"));
                    variables("Locals").declareVariable("output", Type.STRING);
                    variables("Locals").set("output", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{8, 9});

                    log("We moved to the Head node (by setting Current Pointer to Head Pointer) and retrieved the data item stored in the head node into another variable which will be later returned.", false);

                }
            };

            final Command cmd_58 = new Command("cmd_58") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{310, 312, 309, 311});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{14, 12, 13, 11});

                    log("Some key ideas for the upcoming pointer adjustments. We now consider the changes that need to be made to the special pointers 'Head_Pointer' and 'Tail_Pointer'. Since the Current Node was the head of the data-list, the node it points to is the choice for the next head of the the data-list (the head node points to the node that was filled right after it, which points to the node filled right after that node and so on). <br><br>		The only event in which removing the head of the data-list would affect the Tail_Pointer is when the head node itself was the tail node (there was only one node in the Queue which was both the head and tail). If this was the case we would have to update the value of Tail_Pointer to null since the tail node would be removed too when the head nod was removed.<br><br>		Note that in removal we handle pointer corrections concerning the data list first since attaching the removed node to the free-list would the over-write pointer value in the Current Node which we needed for setting the  pointers involved with the data-list. The situation is reversed in insertion where we handle pointer corrections involving the free-list first since attaching the Current Node to the data-list would over-write pointer value in the Current Node which would be necessary in setting the pointers involved with the free-list.<br>	", false);

                }
            };

            final Command cmd_59 = new Command("cmd_59") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{316, 315, 314});

                    variables("Pointers").set("head_pointer", nodes("List").getInt("Pointer", variables("Pointers").getInt("current_pointer")));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{11});

                }
            };

            final IfOrElse if_60 = new IfOrElse("if_60") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{319, 320, 318});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{12});

                }

                @Override
                protected boolean evaluate() {
                    return variables("Pointers").getInt("current_pointer") == variables("Pointers").getInt("tail_pointer");
                }
            };

            final Command cmd_61 = new Command("cmd_61") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{322, 323, 324});

                    variables("Pointers").set("tail_pointer", -1);
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{13});

                }
            };

            final EndIf endif_62 = new EndIf("endif_62") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{328, 327, 326});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{14});

                }
            };

            final Command cmd_63 = new Command("cmd_63") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{333, 332, 331, 330});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{16, 17});

                    log("Some key ideas for attacting a data-node to the free-list. <br> The Current Node (the one indicated by 'Current_Pointer' from which data was removed) is being removed from the data-list and linked to the free-list. In our implementation, we only keep track of the head of the the free-list. We could traverse the free-list to find out the tail node at which the free-list ends and attach the Current Node to it. However, there is a faster and more effecient alternative of using the Current Node as the head itself and attaching the rest of the free-list to the Current Node. The Free_Pointer (pointer to head of free-list) is stored as the pointer value of the Current Node and the Free_Pointer will be updated to point to the Current Node, the new head of the free-list.<br>	", false);

                }
            };

            final Command cmd_64 = new Command("cmd_64") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{337, 336, 335});

                    nodes("List").set("Pointer", variables("Pointers").getInt("current_pointer"), variables("Pointers").getInt("free_pointer"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{16});

                }
            };

            final Command cmd_65 = new Command("cmd_65") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{341, 340, 339});

                    variables("Pointers").set("free_pointer", variables("Pointers").getInt("current_pointer"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{17});

                }
            };

            final Command return_66 = new Command("return_66") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{344, 345, 343, 346});

                    variables("return").set("Remove", variables("Locals").getStr("output"));
                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{19});

                    log("We now returned the data from the removed node.", false);

                }
            };

            final EndIf endif_67 = new EndIf("endif_67") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{349, 350, 348});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{20});

                }
            };

            final EndFunction endfunc_68 = new EndFunction("endfunc_68") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{354, 353, 352});

                    getSourceCodeUnit("Remove_Pseudocode").highlight(new int[]{21});

                }

                @Override
                protected void destroyLocalData() {
                    popVarStack("Locals");
                    popVarStack("args");
                }
            };

            final Command cmd_69 = new Command("cmd_69") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{360, 358, 357, 359, 361});

                    variables("Locals").declareVariable("item_to_be_removed", Type.STRING);
                    getSourceCodeUnit("CarbonCode").highlight(null);

                    getSourceCodeUnit("Insert_Pseudocode").highlight(new int[]{7, 11, 14, 12, 18, 9, 16, 17, 1, 3, 10, 5, 4, 6, 19, 8, 21, 15, 20, 2, 13});

                    log("We'll be performing an removal operation on the Queue where we will:<br><br>		Step 0: Obtain an input and call the removal function.<br><br>		Step 1: Check if the operation is possible. <br><br>		Step 2: If possible, perform the removal operation. <br><br>		Step 3: Tie up loose ends. Since we end up modifying values withing the data and free list in Step 2, we need to make modifications to pointer values to ensure they reflect the new state of the Queue. We also keep in mind to make modifications in such an order so as to not over-write a pointer value while its information is still required for subsequent modifications. Consider the effect of the modifications on special pointers as well as those belonging to nodes on the free and data lists. <br>	", false);
                }
            };

            algorithmTree.addAlgorithmHeader("Remove", cmd_69);

            final Input input_70 = new Input("input_70", Type.STRING) {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{365, 364, 363});

                    variables("Locals").set("item_to_be_removed", String.valueOf(getInputContent()));
                    log("Input the item to be removed.<br>	", false);

                }
            };

            final Call call_71 = new Call("call_71") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{369, 368, 367});

                    log("Calling the Remove procedure with " + variables("Locals").getStr("item_to_be_removed") + " as the item to be removed.<br>	", false);

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

            call_71.setFunction(func_49);

            final Command cmd_72 = new Command("cmd_72") {
                @Override
                protected void onExecution() {
                    getSourceCodeUnit("CarbonCode").highlight(new int[]{371, 372});

                    output("" + variables("return").getStr("Remove") + ":<br>			LOG: Outputting the value returned by the Remove function.");

                }
            };

            final Command impasse_73 = new Command("impasse_73") {
                @Override
                protected void onExecution() {
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

            cmd_20.chainTo(input_21);
            input_21.chainTo(call_22);

            cmd_45.chainTo(input_46);
            input_46.chainTo(call_47);

            cmd_69.chainTo(input_70);
            input_70.chainTo(call_71);
            call_71.chainTo(cmd_72);

        }
    }
}
