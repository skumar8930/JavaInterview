
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
        import java.util.regex.Pattern;
        import java.util.stream.Collectors;
import java.util.stream.Stream;

        import static java.util.Map.Entry.comparingByValue;
        import static java.util.stream.Collectors.counting;
        import static java.util.stream.Collectors.groupingBy;

public class StringPrograms {
    //string programs
    //https://medium.com/javarevisited/top-21-string-programming-interview-questions-for-beginners-and-experienced-developers-56037048de45
//https://www.java67.com/2018/04/21-string-programming-and-coding-interview-questions-answers.html
    public static void  main(String ss[]){
        //System.out.println(maximumOccuringChar("sandeep sandeeppppp pp"));
        //frequencyOfEachChar();
        //reverseString();
       // printDuplicateChar();
        //removeAllDuplicateChaFromStringWithSpace();
        // removeDuplicatesExceptSpace();
        //removeAllduplicatewordsFromString();
        //checktwoStringAngramorNot();
        //checkOnlyDigitInString();
       // countNumberOfVowelsAndConsonentInString();
        //printMaximumOccuringCharInString();
       // printMaximumOccuringWordInString();
       // frequencyOfEachCharUsingJava8();
        frequencyOfEachWord();

    }
//=======================================*************String program to find frequency of each  character in String*********============================
    /*
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
    public static void frequencyOfEachCharUsingJava8(){
        String str="abcdefabc";
        Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().forEach(System.out::println);
    }

    //=======================================*************String program to find frequency of each  word in String*********============================
    public static void frequencyOfEachWord(){
        String str="abc abc xyz xyz mno";
        Stream.of(str.split(" ")).collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().forEach(System.out::println);
    }
    public  static char  maximumOccuringChar(String str) {
        return str.chars().mapToObj(x -> (char) x).collect(groupingBy(x -> x, counting())).entrySet().stream().max(comparingByValue()).get().getKey();
    }

    //reverse String

    public static void reverseString(){

        String str="Hello";

       /*String reve =  Stream.of(str.split("")).reduce("",(reversed,character)->character+reversed);
       System.out.println(reve);*/
//------------------------------------------------------------------------------------------------------------

        //second method

        // String abc = Arrays.asList(str).stream().map(s -> new StringBuilder(s).reverse().toString()).collect(Collectors.toList()).get(0);
        //System.out.println(abc);

//..............................................................................................................
        // Reverse each words

        //System.out.println(Stream.of(str.split(" ")).map(x->new StringBuilder(x).reverse()).collect(Collectors.joining(" ")));

        //________________________________________________________________________________________________________

        /*
         * 1. get number from 0 to length-1 (if str= hello then 0 to 5)
         * 2. revese the number stream 4 to 0
         * 3.fetch each char from string using charAt() method
         * 4.convert each char to string
         * 5.collect by joining.
         *
         * */
        /*int len = str.length();//len=5  // 5-1=4
        String reverse= IntStream.range(0, len).map(i -> len - 1 - i).mapToObj(j->str.charAt(j)).map(x->String.valueOf((char)x)).collect(Collectors.joining());
        System.out.println(reverse);
*/

        //=======================================================================================================

        //  similar can be do as below
        /*

        String s = "blast";
        String re=  IntStream.range(0, s.length()).boxed().sorted(Collections.reverseOrder()).map(i -> String.valueOf(s.charAt(i))).collect(Collectors.joining());
         System.out.println(re);
*/
//===========================================================================================

        /*char[] charArray = "Aniruddh".toCharArray();
        IntStream.range(0, charArray.length).mapToObj(i -> charArray[(charArray.length - 1) - i]).forEach(System.out::print);*/


//===========================================================================================

    }

    //=======================================================// How do you print duplicate characters from a string?=======================================
    public static void printDuplicateChar(){
        /*
         * get stream of char by spliting string ""
         * group all repeating char and count,  now we will get map  char and number of time its occurance
         * filter entry which occuring more than 1
         * now get the list of key only
         * */
        String str= "abccc dd ee gg f";
        Arrays.asList(str.split("")).stream().collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().filter(entry->entry.getValue()>1).map(Map.Entry::getKey).forEach(System.out::println);
    }

    //===============================================================//Remove all duplicate char including space from string================================
    public  static void removeAllDuplicateChaFromStringWithSpace() {
       String str = "abc zbc xbc";
      System.out.println(Stream.of(str.split("")).distinct().collect(Collectors.joining()));

   }
   //=========================================================================== //Remove all duplicates except space========================================
    public static void removeDuplicatesExceptSpace(){
        String str = "abc zbc xbc";
        AtomicReference<String> result = new AtomicReference<>("");
        String c="v";
        Stream.of(str.split("")).forEach(x -> {
            if(!result.get().contains(x)) {
                result.set(result+x);
            }
            else if(x.equals(" ")) {
                result.set(result+x);// result= result+x;
            }

        });
        System.out.println(result);
     /*   String input="abc zbc xbc";
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if(!result.contains(String.valueOf(input.charAt(i)))) {
                result += String.valueOf(input.charAt(i));
            }
            else if(input.charAt(i)==' '){
                result += String.valueOf(input.charAt(i));

            }
        }
        System.out.println(result);*/
    }
    //========================================================================= // remove all duplicate words from string========================================
    public static void removeAllduplicatewordsFromString(){
        String str= "hello how are you   hello you";
       System.out.println(Stream.of(str.split("\\s+")).distinct().collect(Collectors.joining(" ")));
    }

    //============================================================================// How do you check if two strings are anagrams of each other?======================
    /*
     * An anagram of a string is another string that contains the same characters,
     * only the order of characters can be different.
     * For example, “abcd” and “dabc” are an anagram of each other.
     * */
    /*solution
     * sort both string
     * both content should be equal
     * */
    public static void checktwoStringAngramorNot(){
        String str1="abcde";
        String str2="dabc";
        if(str1!=null &&str2!=null && !str1.equals("")&& !str2.equals("")) {
            str1 = Stream.of(str1.split("")).sorted().collect(Collectors.joining());
            str2 = Stream.of(str2.split("")).sorted().collect(Collectors.joining());
        }
        // we can sort as below also
        // String str1Sorted= str1.chars().sorted().mapToObj(i->String.valueOf((char)i)).collect(Collectors.joining());
        //String str2Sorted= str2.chars().sorted().mapToObj(i->String.valueOf((char)i)).collect(Collectors.joining());
        System.out.println(str1 +"    "+str2);
        if(str1.equals(str2)){
            System.out.println("Both string is angram to each other");
        }
        else
            System.out.println("Both string is not angram to each other");
    }
//==================================================== //How do you check if a string contains only digits==================================================
    public static void checkOnlyDigitInString(){
        String str="123";
        if(Pattern.compile("[0-9]+").matcher(str).matches()){
            System.out.println("String contain only digit");
        }
        else System.out.println("String contain not only digit");

    }
    //==================================================================//How do you count a number of vowels and consonants in a given string?========================
    public static void countNumberOfVowelsAndConsonentInString(){
        //vowels = aeiou
        //rest consonent
        String str="aghil aghil    lmn LMN ";
       Long vowels_count =Arrays.asList(str.split("")).stream().filter(c-> Pattern.compile("[aeiouAEIOU]").matcher(c).matches()).count();
       Long totalConsonent= Arrays.asList(str.split("")).stream().filter(c-> !c.equals(" ") && !Pattern.compile("[aeiouAEIOU]").matcher(c).matches() ).count();
       System.out.println("Number of vowels= "+vowels_count);
       System.out.println("Number of Consonent= "+totalConsonent);
    }
    //=================================================write a program to print maximum occuring char===============================================
    public static void printMaximumOccuringCharInString(){
        String str="abccc   dddd eeeeee";
       String maxOccuringChar= Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
       System.out.println(maxOccuringChar);
    }

    //=================================================write a program to print maximum occuring word===============================================//
    public static void printMaximumOccuringWordInString(){
        String str="abc abc def jkl jkl jkl";
        String maxOccuringChar= Stream.of(str.split(" ")).collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(maxOccuringChar);
    }

//=================================================================================================================================================



}
