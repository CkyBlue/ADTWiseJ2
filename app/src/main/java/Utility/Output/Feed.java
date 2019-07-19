package Utility.Output;

import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed<Content, Printer> {
   void logAdded(){
       for (Printer printer : getPrinters()) {
           printer.notifyOfNewLog();
       }
   };
}
