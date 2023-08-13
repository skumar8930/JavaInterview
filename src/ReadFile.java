import javax.jnlp.ClipboardService;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReadFile {
    public static void main(String ss[]){
        //printAllPrimeNumberFromTheRange();
        chechStringPalindromeorNot();


    }


public static Integer limit(){
        Integer limit =0;
    String text="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\91752\\IdeaProjects\\JavaInterview\\src\\textfile.txt")));
            while ( (text = br.readLine())!=null){
                if(Pattern.compile("[0-9]+").matcher(text).matches()){
                    limit= Integer.parseInt(text);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return limit;
}

public static  void printAllPrimeNumberFromTheRange(){
        List<Integer> listofPrimeNumber=  new ArrayList<>();
        Integer start= 1;
        Integer limit = limit();
        for(int i=start;i<limit;i++){
           if(chechPrime(i)){
               listofPrimeNumber.add(i);
           }
        }
        listofPrimeNumber.stream().forEach(System.out::println);

}

    public static Boolean chechPrime(Integer n) {
    Boolean isPrime=false;
    if (n  <2) {
        isPrime=false;
    } else {
        for (int j = 2; j < n; j++) {
            if (n % j == 0) {
                isPrime=false;
                break;

            } else
                isPrime= true;
        }
    }

return isPrime;
}


public static void chechStringPalindromeorNot(){
        try {
            String text="";

                BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\91752\\IdeaProjects\\JavaInterview\\src\\textfile.txt")));
            while ((text= br.readLine())!=null){
                if(chechStringPalindrome(text)){
                    System.out.println(text+" is Palindrome");
                }

            }

        }
        catch (IOException ex)
        {
        ex.printStackTrace();
        }
}
public static Boolean chechStringPalindrome(String str){
        Boolean isPalindrome=false;
        String reverse="";
       for(int i=str.length()-1;i>=0;--i){
          reverse+=str.charAt(i);
       }
       if(reverse.equals(str)){
          isPalindrome=true;
       }
       else {
           isPalindrome = false;
       }

       return isPalindrome;
}



}
