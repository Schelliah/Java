package Quote;
import java.time.*;
import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
 



public class Quote {
    static String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };
    


    static int getIndex(int days, int length){
        if (days <= length){
            return days -1;
        } else{
            return (days% length) -1;
        }
    }

    static String[] getQuote(LocalDate date){
        int amountOfQuotes = quotes.length;
        int days = date.getDayOfYear();
        int index=getIndex(days,amountOfQuotes);
        //int index= 5;
        String[] singleQuote=quotes[index];
        
        return singleQuote;
    }

    static String getSuffix(int dayOfMonth){
        String suffix = new String();
        switch (dayOfMonth) {
            case 1:
            case 21:
            case 31:
                suffix = "st";

            case 2:
            case 22:
                suffix = "nd";

            case 3:
            case 23:
                suffix = "rd";

            default:
                suffix = "th";
        }       
        return suffix;
            
    }

    static String stringUppercaseFirstLetter(String str){
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    static String formatQuote(String quote){
        quote=stringUppercaseFirstLetter(quote);
        if(quote.charAt(quote.length()-1)!='!'){
            quote = quote + ".";
        }
        return quote;
    }

    public static void main(String... args) {
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();


        int dayOfMonth = date.getDayOfMonth();
        String suffix = getSuffix(dayOfMonth);

        DayOfWeek weekday=date.getDayOfWeek();
        String dayOfTheWeek=weekday.getDisplayName(TextStyle.FULL, Locale.ENGLISH);


        String monthString =month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println("Quote for " + dayOfTheWeek+ " the " + dayOfMonth + suffix + " of " + monthString );


        //get quote
        String[] singleQuote=getQuote(date);
        String name=singleQuote[0];
        String quote=singleQuote[1];
        name=stringUppercaseFirstLetter(name);
        quote = formatQuote(quote);


        System.out.println('"' + quote +    '"' + " -- " +name);
        
    }
}
