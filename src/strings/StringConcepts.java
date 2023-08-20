package strings;

public class StringConcepts {

    public static void stringClassDemo(){
        String str= "Hello";
        str.concat(" how are you");
        System.out.println(str);/*it is still printing Hello while we are expecting Hello how are you it is because
        String class is immutable once object created we can not change its content*/
        str= str.concat(" how are you");// now str referring to new object which is Hello how are you in memory
        System.out.println(str);//Now it is printing Hello how are you because now it is new object in memory.

    }

    public static void StringBufferDemo(){
        StringBuffer stringBuffer = new StringBuffer("hello");
        //https://www.javatpoint.com/difference-between-stringbuffer-and-stringbuilder
        //StringBuffer and StringBuilder is same(both are mutable) excep StringBuffer is synchronized while StringBuilder is  non-synchronized.
         stringBuffer.append(" how are you");
        System.out.println(stringBuffer); /*
        It is printing hello how are you because StringBuffer class is mutable class we can change its content after creation of object
        */

    }
    public static void main(String ss[]){
       // stringClassDemo();
       StringBufferDemo();


    }

}
