
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StringPrograms {

    public static void  main(String ss[]){
     //System.out.println(maximumOccuringChar("sandeep sandeeppppp pp"));
        frequencyOfEachChar();

    }

    /* String program to find frequency of each  character in String
    * 1. remove all space to remove all space split the string from space and concatinate the array String
    * now create a hash map and put each char, if it persent then increase its occurance else put and
    * and create its occurance 1
    * */

    public static void frequencyOfEachChar(){


        String str="Good morning";
        Map<Character,Integer> map= new HashMap();
        //1. split string
        String str2 []= str.split("\\s+");
        //2. concatinate all string array
        String withoutSpaceString="";
        for(String s:str2){
            withoutSpaceString +=s;
        }
        // 3. put each char in hash map if not persent then put its occurence one , if persent then put and increase its occurance
        // by 1.
        for(int  i=0;i<withoutSpaceString.length();i++){
          Character key=  withoutSpaceString.charAt(i);
          if(map.containsKey(key)){
              map.put(key,map.get(key)+1);
          }
          else
              map.put(key,1);
        }

        System.out.println(map);

        //to find max occurance char
   // System.out.println(Collections.max(map.values()));
      // System.out.println( map.values().stream().max((a,b)-> a.compareTo(b)).get());
        System.out.println( map.values().stream().max((Integer::compareTo)).get());

    }


   /* public  static char  maximumOccuringChar(String str) {
        return str.chars().mapToObj(x -> (char) x)                  // box to Character
                .collect(groupingBy(x -> x, counting()))  // collect to Map<Character, Long>
                .entrySet().stream()
                .max(comparingByValue())                  // find entry with largest count
                .get()                                    // or throw if source string is empty
                .getKey();
    }*/
   public  static char  maximumOccuringChar(String str) {
       return str.chars().mapToObj(x -> (char) x)                  // box to Character
               .collect(groupingBy(x -> x, counting()))  // collect to Map<Character, Long>
               .entrySet().stream()
               .max(comparingByValue())                  // find entry with largest count
               .get()                                    // or throw if source string is empty
               .getKey();
   }


}