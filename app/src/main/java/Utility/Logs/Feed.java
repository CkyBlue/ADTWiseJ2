package Utility.Logs;

import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed<BaseContent, Printer> {
   public void logAdded(){
       for (Printer printer : getPrinters()) {
           printer.notifyOfNewLog();
       }
   };
}
