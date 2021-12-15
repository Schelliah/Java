package Profiling;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;
import java.io.FileWriter; 

public class Profiling {


    static void printBooks(String[] books){
        for (int i =0;i<books.length;i++){
            System.out.print(books[i] + "  ");
        }
    }

    static void printList(List<Profile> list){
        System.out.println(Arrays.toString(list.toArray()));
    }

    static void printStringList(List<String> list){
        System.out.println(Arrays.toString(list.toArray()));
    }


    
    public static void writeToFile(String str){
        try{    
            FileWriter fileWriter=new FileWriter("src/main/resources/intermediate/writeFile,txt", true);    
            fileWriter.write(str);    
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.close();    
           }catch(Exception e){
               System.out.println(e);
            }     
    }


    static void profilingB(Profiles profiles){
        ArrayList<Profile> profilesList = profiles.getList();

        //opdr 1
        Predicate<Profile> fiveBooks = e -> e.getAmountOfBooks() ==5;
        Predicate<Profile> hasRead = e -> e.hasBook("Great Expectations");
        Predicate<Profile> firstLetter = e -> e.getName().charAt(0)=='L';
        Predicate<Profile> startWithTheBooks = e->e.hasStartbook("The");
        List<Profile> result =  profilesList.stream().filter(fiveBooks).collect(Collectors.toList());
        List<Profile> result2 = profilesList.stream().filter(hasRead).collect(Collectors.toList());
        List<Profile> result3 = profilesList.stream().filter(startWithTheBooks).collect(Collectors.toList());
        printList(result);
        printList(result2);
        printList(result3);

        //opdr 2
        Function<Profile,String> first = e ->  e.getNameEdit();
        Function<Profile,String> second = e ->  e.getNameEdit2();
        Function<Profile,String> third = e -> e.getNameEdit3();

        List<String> secondresult1 = result.stream().map(first).collect(Collectors.toList());
        List<String> secondresult2 = result.stream().map(second).collect(Collectors.toList());
        List<String> secondresult3 = result.stream().map(third).collect(Collectors.toList());
        printStringList(secondresult1);
        printStringList(secondresult2);
        printStringList(secondresult3);

        //opdr 3
        Consumer<String> printStrToLine = e -> System.out.println(e);
        Consumer<String> saveStrToFile = e -> writeToFile(e);

        secondresult3.stream().forEach(printStrToLine);
        secondresult3.stream().forEach(saveStrToFile);

        //opdr4
        profilesList.stream().filter(hasRead).map(third).forEach(printStrToLine); 


        //opdr 5
        List<String> firstPart = profilesList.stream().filter(hasRead).map(third).collect(Collectors.toList());
        firstPart.forEach(printStrToLine);
        firstPart.forEach(saveStrToFile);







        

    }


    static void getRecommendation(Profiles profiles){
        Scanner input= new Scanner(System.in);
        System.out.println("Customer name: ");
        String name=input.next();
        input.close();

        
        Profile profile =profiles.getProfile(name);

        //testing
        //String name = "Margie luna";
        //String[] books = profiles.getBookread(name);
        //printBooks(books);
        //List<String> bookList=profiles.getNamesReadBook("Wuthering Heights");
        //System.out.println(Arrays.toString(bookList.toArray()));


        String rec=profiles.getRecommendation(profile); 
        if(rec.isEmpty()){
            System.out.println("No Recommendations available");
        } else{
            System.out.println(profile.getName().replace("  ", " ") + ", We recommend " + rec + " to you.");
        }



    }

    public static void main  (String... args) throws FileNotFoundException {
        Profiles profiles = new Profiles();
        try{
        File file = new File("src/main/resources/intermediate/profiling-data.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Profile profile = new Profile(line);
            profiles.addProfile(profile);

        }

        sc.close();
        } catch (FileNotFoundException e){
        System.out.println("File not found");
        }

        

        //getRecommendation(profiles);
        profilingB(profiles);



    


    }
}