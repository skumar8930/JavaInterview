import java.util.Arrays;

public class MatrixProblems {
    public static void main(String ss[]){
        Integer arr[][] = {{1,3},{5,6}};
        Integer sumOfDigonal=0;
        for(int i=0; i<2;i++){
            for(int j=0;j<2;j++){
                if(i==j)
                {
                  sumOfDigonal+= arr[i][j];
                }
            }
        }
        System.out.println("Sum is= "+sumOfDigonal);

    }



}
