package Profiling;

import java.util.*;
import java.util.stream.Stream;

class Profile{
    private String name;
    private String[] books; 
    final String[] nonMeaningfull = {"the", "in", "and", "or", "to"};

    Profile(String line){
        String[] lineElements=line.split(", ");
        books = new String[lineElements.length-1];
        name = lineElements[0];
        System.arraycopy(lineElements,1,books,0,lineElements.length-1);
    }

    String getName(){
        return name;
    }

    private boolean isMeaningfull(String word){
        word=word.toLowerCase();

        //int count = ( int) Arrays.stream(nonMeaningfull).filter(e-> e==word).count();
        for (int i = 0; i<nonMeaningfull.length;i++){
            if(nonMeaningfull[i].equals(word)){
                return false;
            }
        }
        return true;
    }

    String getFirstMeaningfullWord(String book){
        String[] bookWords =book.split(" ");
        for (int i =0; i<book.length();i++){
            if(isMeaningfull(bookWords[i])){
                return bookWords[i];
            }
        } 


        return null;

    }

    String getNameEdit(){
        String[] nameSplit=name.split("  ");
        String firstName=nameSplit[0];
        String secondTerm=getFirstMeaningfullWord(books[0]);

        return firstName + " " + secondTerm;
    }

    String getNameEdit2(){
        String[] nameSplit=name.split("  ");
        String firstName=nameSplit[0];
        String result ="";

        for (int i=0; i<books.length;i++){
            result = result + firstName;
        }
        

        return result;
    }

    String getNameEdit3(){
        return Character.toString(name.charAt(0));
    }




    int getAmountOfBooks(){
        return books.length;
    }

    private boolean isEqualsStringArray(String[] strArray1, String[] strArray2){
        if(!(strArray1.length==strArray2.length)){
            return false;
        }
        for (int i=0; i<strArray1.length;i++){
            if(!(strArray1[i].equals(strArray2[i]))) return false;
        }
        return true;
    }

    boolean equals(Profile profile){
        return this.name.equals(profile.getName()) && isEqualsStringArray(this.getBooks(), profile.getBooks()); 
    }


    List<String> getDifferentBooks(Profile profile){
        List<String> result=new LinkedList<String>();
        String[] books2=profile.getBooks();
        for (int i=0;i<books2.length;i++){
            if(!this.hasBook(books2[i])){
                result.add(books2[i]);
            }
        }
        
        return result;
    
    }

    int getreadSamebookIntTimes(Profile profile){
        int sameBooksRead=0;
        for (int i=0;this.books.length>i;i++){
            if(profile.hasBook(this.books[i])){
            sameBooksRead=sameBooksRead+1;
            //System.out.println(sameBooksRead);
            }
        }
        //System.out.println(sameBooksRead + " c");
        return sameBooksRead;

    }

    String[] getBooks(){
        return books;
    }

    public String toString(){
        return this.getName();
    } 

    boolean hasStartbook(String hasString){
        Stream<String> streamBooks = Arrays.stream(books);
        return streamBooks.anyMatch(e -> e.startsWith(hasString));  
    }

    boolean hasBook(String book){
        for(int i=0;i<books.length;i++){
            if(books[i].equals(book)){
                return true;
            }
        }
        return false;
    }
    

}