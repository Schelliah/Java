package Profiling;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
//import java.util.stream.Collectors;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
import java.util.stream.*;


public class Profiles {
    private ArrayList<Profile> profiles;

    Profiles(){
        profiles= new ArrayList<Profile>();
    }

    void addProfile(Profile profile){
        profiles.add(profile);
    }



    List<Profile> streamops(){
        Predicate<Profile> fiveBooks = e -> e.getAmountOfBooks() ==5;
        Predicate<Profile> hasRead = e -> e.hasBook("Great Expectations");
        Predicate<Profile> firstLetter = e -> e.getName().charAt(0)=='L';
        Predicate<Profile> startWithTheBooks = e->e.hasStartbook("The");
        List<Profile> result = profiles.stream().filter(fiveBooks).collect(Collectors.toList());
        List<Profile> result2 = profiles.stream().filter(hasRead).collect(Collectors.toList());
        List<Profile> result3 = profiles.stream().filter(startWithTheBooks).collect(Collectors.toList());




        return result;
    }







    private String[] concat(String[] array1,String[] array2){
        String[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2,0,result,array1.length-1,array2.length);
        return result;
    }

    List<String> getNamesReadBook(String book){
        List<String> names =  new LinkedList<String>();
        for (int i = 0; i<profiles.size();i++){
            if(profiles.get(i).hasBook(book)){ 
                names.add(profiles.get(i).getName());
            } 
        }
        return names;
    }


    List<String> getRecommendationList(Profile mainProfile){
        List <String> result = new LinkedList<String>();
        //System.out.println(this.profiles.size());
        for (int i = 0; i<this.profiles.size();i++){
            Profile currentProfile=profiles.get(i);
            if(!currentProfile.equals(mainProfile)){ 
                if (mainProfile.getreadSamebookIntTimes(currentProfile)>=3){
                    List<String> currList=mainProfile.getDifferentBooks(currentProfile);
                    //System.out.println(Arrays.toString(currList.toArray()));
                    result = Stream.concat(result.stream(), currList.stream()).distinct().collect(Collectors.toList());
                }
            }
        }

        return result;

    }


    String getRecommendation(Profile profile){
        List<String> recList = getRecommendationList(profile);
        Random rnd = new Random();
        int index =rnd.nextInt(recList.size()-1);
        return recList.get(index);
    }


    String getRecommendation(String name){
        Profile mainProfile=getProfile(name);
        return getRecommendation(mainProfile);
    }

    private boolean isSameName(String profileName,String name){
        name=name.toLowerCase();
        profileName=profileName.toLowerCase();

        if(name.equals(profileName)){
            return true;
        }

        String[] profileNameArray=profileName.split("  ");
        String[] nameArray = name.split(" ");
        for (int i=0;i<profileNameArray.length;i++){
            for(int j=0;j<nameArray.length;j++){
                nameArray[j]=nameArray[j].replaceAll("\\s+","");
                if(profileNameArray[i].equals(nameArray[j])){
                return true;
                }
            //}
            }
            //nameArray[i]=nameArray[i].replaceAll("\\s+","");
            //System.out.println(profileNameArray[i] + " fullname");
            //System.out.println(name + "  namee");
            //if(profileNameArray[i].equals(name)){
            //    return true;
            //}
        }

        return false;
    }

    String [] getBookread(String name){
        Profile prof = getProfile(name);
        if(prof==null){
            return null;
        }
        return prof.getBooks(); 
    }


    boolean isProfile(Profile profile, String name){
        return isSameName(profile.getName(),name);
    }
    

    Profile getProfile(String name){
        for (int i = 0; i<profiles.size();i++){
            if(isProfile(profiles.get(i), name)){
                return profiles.get(i);
            } 
        }

        System.out.println("profile not found");
        return null;
    }

    public Object stream() {
        return null;
    }

    public ArrayList<Profile> getList() {
        return profiles;
    }

}
