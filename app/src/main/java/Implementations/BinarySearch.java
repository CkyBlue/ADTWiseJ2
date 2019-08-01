/*
package Implementations;

import android.content.Context;

import com.example.ckyblue.adtwiser2.R;

import java.util.HashSet;
import java.util.Set;

import Utility.Algorithm.CasePort;
import Utility.Algorithm.Commands.Call;
import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.Function;
import Utility.Algorithm.Commands.Input;
import Utility.Algorithm.Commands.Iterator;
import Utility.Algorithm.Commands.Switch;
import Utility.Algorithm.Tree.Content;
import Utility.Algorithms.Algorithm;
import Utility.Algorithms.AlgorithmFamily;
import Utility.Algorithms.AlgorithmsList.AlgorithmsFeed;
import Utility.Algorithms.CodeFeedLayer;
import Utility.Algorithms.DataLayer;
import Utility.Commands.BaseCommand;
import Utility.Commands.Call;
import Utility.Commands.Command;
import Utility.Commands.Function;
import Utility.Commands.Header;
import Utility.Commands.Input;
import Utility.Commands.Iterator;
import Utility.Data.Items.Type;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Nodes.BluePrint.BluePrint;
import Utility.Data.Nodes.Nodes;
import Utility.Data.Type;
import Utility.Data.Variables;
import Utility.SourceCode.CodeFeed;
import Utility.Utilities;

class Implementations {
    private final Context context;

    public Content binarySearch = new Content() {
        {
            final String arguments = "Arguments";
            final String locals = "Locals";

            final String list = "List";
            final String index = "Iist";
            final String data = "data";

            final String start = "Start";
            final String end = "End";
            final String midPoint = "MidPoint";
            final String search_item = "Search Item";
            final String again = "Again";


            Command header = new Command("header") {
                @Override
                protected void onExecution() {
                    getDataLayer().clear();

                    getDataLayer().buildVariablesStack(arguments);
                    getDataLayer().buildVariablesStack(locals);

                    BluePrint nodeBlueprint = new BluePrint();
                    nodeBlueprint.addKey(index, Type.INTEGER);
                    nodeBlueprint.addKey(data, Type.STRING);

                    getDataLayer().buildNodesStack(list,
                            nodeBlueprint, 10);

                    for (int i = 0; i < nodes(list).getSize(); i++) {
                        nodes(list).set(index, i, i);
                        nodes(list).set(data, i,
                                String.valueOf((char) ('A' + i)));
                    }
                }
            };

            setHeader(header);

            Utility.Algorithm.Algorithm.Content algorithm = new Utility.Algorithm.Algorithm.Content() {
                {
                    Command header = new Command("header") {
                        @Override
                        protected void onExecution() {
                            variables(locals).declareVariable(search_item, Type.STRING);
                            variables(locals).declareVariable(again, Type.BOOLEAN);

                            variables(locals).set(again, true);

                            chainTo();
                        }
                    };

                    CasePort outerLoopCP = new CasePort() {
                        @Override
                        protected boolean condition() {
                            return getCommand().variables(locals).getBool("Again");
                        }
                    };
                    outerLoopCP.chainTo();

                    Iterator iterator = new Iterator("outer-loop", outerLoopCP) {
                        @Override
                        protected void onExecution() {

                        }
                    };

                    Input input_for_search_item = new Input("input_for_search_item", Type.STRING) {
                        @Override
                        protected void onExecution() {
                            variables(locals).set(search_item, getInputContent());
                            chainTo();
                        }
                    };

                    abstract class functionCall extends Call {
                        functionCall(String name) {
                            super(name);
                        }

                        @Override
                        protected void buildArgs() {
                            popVariables(arguments);

                            variables(arguments).declareVariable(start, Type.INTEGER);
                            variables(arguments).declareVariable(end, Type.INTEGER);
                            variables(arguments).declareVariable(search_item, Type.STRING);
                        }
                    }

                    Call call1 = new functionCall("call_1") {
                        @Override
                        protected void passArgs() {
                            variables(arguments).set(start, 0);
                            variables(arguments).set(end, nodes(list).getSize() - 1);
                            variables(arguments).set(search_item, variables(locals).getStr(search_item));
                        }

                        @Override
                        protected void onExecution() {
                            chainTo("input_for_search_again");
                        }
                    };

                    Input input_for_search_again = new Input("input_for_search_again ", Type.BOOLEAN) {

                        @Override
                        protected void onExecution() {
                            variables(locals).set(again, (boolean) getInputContent());
                        }
                    };

                    Function binSearchFunc = new Function("binSearchFunc", "binSearchFuncEnd") {
                        @Override
                        protected void buildLocalData() {
                            pushVariables(locals);
                            variables(locals).addAll(variables(arguments));
                        }

                        @Override
                        protected void onExecution() {
                            chainTo("func_2_4");
                        }
                    };

                    Switch func_2_4 = new Switch("func_2_4") {
                        @Override
                        protected void onExecution() {
                            CasePort casePort1 = new CasePort() {
                                @Override
                                protected boolean condition() {
                                    return variables(locals).getInt(end) < variables(locals).getInt(start);
                                }
                            };
                            casePort1.chainTo("func_3");

                            CasePort casePort2 = new CasePort() {
                                @Override
                                protected boolean condition() {
                                    return true;
                                }
                            };
                            casePort1.chainTo("func_5");

                            addCase(casePort1);
                            addCase(casePort2);
                        }
                    }

                    Command func_3 = new Command("func_3") {
                        @Override
                        protected void onExecution() {
                            output("Item not found");
                        }
                    };

                    Command func_5 = new Command("func_5") {
                        @Override
                        protected void onExecution() {
                            variables(locals).declareVariable(midPoint, Type.INTEGER);

                            variables(locals).set(midPoint, (variables(locals).getInt(end) + variables(locals).getInt(start)) / 2);
                            chainTo("func_6_8_10");
                        }
                    };

                    Switch func_6_8_10 = new Switch("func_6_8_10 ") {
                        @Override
                        protected void onExecution() {
                            final String item_at_mid_point = nodes(list).getStr(data,
                                    variables(locals).getInt(midPoint));

                            CasePort casePort1 = new CasePort() {
                                @Override
                                protected boolean condition() {
                                    return item_at_mid_point.equals(
                                            variables(locals).getStr(search_item)
                                    );
                                }
                            };
                            casePort1.chainTo("func_7");

                            CasePort casePort2 = new CasePort() {
                                @Override
                                protected boolean condition() {
                                    return item_at_mid_point.compareTo(
                                            variables(locals).getStr(search_item)) > 0;
                                    );
                                }
                            };
                            casePort1.chainTo("func_9");

                        }
                    }

                    Command func_6_8_10 = new Command("func_6_8_10 ") {

                        @Override
                        protected BaseCommand execution() {
                            int midPoint = num(l("MidPoint"));
                            String searchItem = l("Search_Item");

                            String item_at_mid_point = list().get("Data", midPoint);

                            if (item_at_mid_point.equals(searchItem)) {
                                return func7;

                            } else if (item_at_mid_point.compareTo(searchItem) > 0) {
                                return new functionCall() {
                                    @Override
                                    protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                                        CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                                        codeFeed.highlight(new int[]{9});
                                    }

                                    @Override
                                    protected void passArgs() {
                                        a("Start", l("Start"));
                                        a("End", l("MidPoint"));
                                        a("Search_Item", l("Search_Item"));
                                    }
                                };

                            } else {
                                return new functionCall() {
                                    @Override
                                    protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                                        CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                                        codeFeed.highlight(new int[]{11});
                                    }

                                    @Override
                                    protected void passArgs() {
                                        a("Start", l("MidPoint"));
                                        a("End", l("End"));
                                        a("Search_Item", l("Search_Item"));
                                    }
                                };
                            }
                        }

                        @Override
                        public String cmdDescription() {
                            return "If statements (Function - Line 6, 8 & 10)";
                        }
                    };

                    private Command func7 = new Command() {
                        @Override
                        protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                            CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                            codeFeed.highlight(new int[]{7});
                        }

                        @Override
                        protected BaseCommand execution() {
                            int midPoint = num(l("MidPoint"));
                            output("Item found at " + str(midPoint));

                            return null;
                        }

                        @Override
                        public String cmdDescription() {
                            return "Item Found (Function - Line 7)";
                        }
                    };
                }
            };
        }
    };

    public Implementations(Context context) {
        this.context = context;
    }
}

public class BinarySearch extends AlgorithmFamily {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void initDataLayer(DataLayer dataLayer) {
        dataLayer.clear();

        dataLayer.buildVariablesStack("Arguments");
        dataLayer.buildVariablesStack("Locals");

        BluePrint nodeBlueprint = new BluePrint();
        nodeBlueprint.addKey("Index", Type.INTEGER);
        nodeBlueprint.addKey("Data", Type.STRING);

        dataLayer.buildNodesStack("List",
                nodeBlueprint, 10);

        for (int i = 0; i < dataLayer.getNodes("List").getSize(); i++) {
            dataLayer.getNodes("List").set("Index", i, str(i));
            dataLayer.getNodes("List").set("Data", i, str((char) ('A' + i)));
        }
    }

    private Variables locals() {
        return getDataLayer().getVariables("Locals");
    }

    private String l(String key) {
        return locals().get(key);
    }

    private void l(String key, String content) {
        locals().set(key, content);
    }

    private Variables args() {
        return getDataLayer().getVariables("Arguments");
    }

    private void a(String key, String content) {
        args().set(key, content);
    }

    private Nodes list() {
        return getDataLayer().getNodes("List");
    }

    private Algorithm binarySearchAlgorithm = new Algorithm(this) {
        @Override
        public void initSourceCodes(CodeFeedLayer codeFeedLayer) {

            String content = Utilities.readRawTextFile(getContext(), R.raw.binary_search);
            String pseudocode = "Pseudocode";

            Set<String> keys = new HashSet<>();
            keys.add(pseudocode);

            codeFeedLayer.buildFeed(keys);
            codeFeedLayer.getCodeFeed(pseudocode).setContent(content);
        }

        private Header header = new Header() {
            @Override
            protected void buildVars() {
                locals().createVar("Search_Item", Type.STRING);
                locals().createVar("Again", Type.STRING);
                l("Again", "Y");
            }

            @Override
            protected BaseCommand execution() {
                return iterator;
            }

            @Override
            public String cmdDescription() {
                return "Header";
            }
        };

        private Iterator iterator = new Iterator() {
            @Override
            protected boolean evaluate() {
                return (l("Again").equals("Y") || l("Again").equals("y"));
            }

            @Override
            public String cmdDescription() {
                return "While loop";
            }

            @Override
            protected BaseCommand execution() {
                return input_for_search_item;
            }
        };

        private Input input_for_search_item = new Input(Input.InputType.CHAR) {
            @Override
            protected BaseCommand execution() {
                locals().set("Search_Item", getInputContent());
                return call1;
            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                super.adjustSourceCodes(codeFeedLayer);
            }

            @Override
            public String cmdDescription() {
                return "Input for Search Item";
            }
        };

        abstract class functionCall extends Call {
            @Override
            protected Function getFunction() {
                return func1;
            }

            @Override
            public String cmdDescription() {
                return "Function call";
            }

            @Override
            protected void setUpArgs() {
                getDataLayer().pushVariablesLayer("Arguments");

                args().createVar("Start", Type.INTEGER);
                args().createVar("End", Type.INTEGER);
                args().createVar("Search_Item", Type.STRING);
            }
        }

        private Call call1 = new functionCall() {
            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{14});
            }

            @Override
            protected void passArgs() {
                a("Start", "0");
                a("End", str(list().getSize() - 1));
                a("Search_Item", l("Search_Item"));
            }

            @Override
            protected BaseCommand chainedTo() {
                return input_for_search_again;
            }
        };

        private Input input_for_search_again = new Input(Input.InputType.CHAR) {
            @Override
            protected BaseCommand execution() {
                locals().set("Again", getInputContent());
                return null;
            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                super.adjustSourceCodes(codeFeedLayer);
            }

            @Override
            public String cmdDescription() {
                return "Input For 'Search Again?'";
            }
        };

        private Function func1 = new Function() {
            @Override
            protected void buildVars() {
                getDataLayer().pushVariablesLayer("Locals");
                locals().deepCopy(args());
            }

            @Override
            protected void destroyVars() {
                getDataLayer().popVariablesLayer("Locals");
                getDataLayer().popVariablesLayer("Arguments");
            }

            @Override
            protected void adjustSourceCodeAtEndFunction(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{13});
            }

            @Override
            protected BaseCommand execution() {
                return func2_4;
            }

            @Override
            public String cmdDescription() {
                return "Binary_Search Function Call (Function - Line 1)";
            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{1});
            }
        };

        private Command func2_4 = new Command() {
            @Override
            protected BaseCommand execution() {
                if (num(l("End")) < num(l("Start"))) {
                    return func3;

                } else {
                    return func5;

                }
            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{2, 4});
            }

            @Override
            public String cmdDescription() {
                return "Function - Line 2 & 4";
            }
        };

        private Command func3 = new Command() {
            @Override
            protected BaseCommand execution() {
                output("Item not found");
                return null;
            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{3});
            }

            @Override
            public String cmdDescription() {
                return "Function - Line 3";
            }
        };

        private Command func5 = new Command() {
            @Override
            protected BaseCommand execution() {
                locals().createVar("MidPoint", Type.INTEGER);

                int midPoint = (num(l("End")) + num(l("Start"))) / 2;
                l("MidPoint", str(midPoint));
                return func6_8_10;

            }

            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{5});
            }

            @Override
            public String cmdDescription() {
                return "Calculating MidPoint (Function - Line 5)";
            }
        };

        private Command func6_8_10 = new Command() {
            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{6, 8, 10});
            }

            @Override
            protected BaseCommand execution() {
                int midPoint = num(l("MidPoint"));
                String searchItem = l("Search_Item");

                String item_at_mid_point = list().get("Data", midPoint);

                if (item_at_mid_point.equals(searchItem)) {
                    return func7;

                } else if (item_at_mid_point.compareTo(searchItem) > 0) {
                    return new functionCall() {
                        @Override
                        protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                            CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                            codeFeed.highlight(new int[]{9});
                        }

                        @Override
                        protected void passArgs() {
                            a("Start", l("Start"));
                            a("End", l("MidPoint"));
                            a("Search_Item", l("Search_Item"));
                        }
                    };

                } else {
                    return new functionCall() {
                        @Override
                        protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                            CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                            codeFeed.highlight(new int[]{11});
                        }

                        @Override
                        protected void passArgs() {
                            a("Start", l("MidPoint"));
                            a("End", l("End"));
                            a("Search_Item", l("Search_Item"));
                        }
                    };
                }
            }

            @Override
            public String cmdDescription() {
                return "If statements (Function - Line 6, 8 & 10)";
            }
        };

        private Command func7 = new Command() {
            @Override
            protected void adjustSourceCodes(CodeFeedLayer codeFeedLayer) {
                CodeFeed codeFeed = codeFeedLayer.getCodeFeed("Pseudocode");
                codeFeed.highlight(new int[]{7});
            }

            @Override
            protected BaseCommand execution() {
                int midPoint = num(l("MidPoint"));
                output("Item found at " + str(midPoint));

                return null;
            }

            @Override
            public String cmdDescription() {
                return "Item Found (Function - Line 7)";
            }
        };

        @Override
        public Header getHead() {
            header.setAlgorithm(this);
            return header;
        }
    };

    @Override
    public void initAlgorithmsLayer(AlgorithmsFeed algorithmsFeed) {
        algorithmsFeed.clear();
        algorithmsFeed.addAlgorithm("Binary Search");
    }

    public BinarySearch(Context context, Utility.Algorithms.Dispatcher dispatcher) {
        super(dispatcher);
        setContext(context);
        addAlgorithm("Binary Search", binarySearchAlgorithm);
    }
}*/
