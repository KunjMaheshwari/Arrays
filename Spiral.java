import java.util.*;

class Solution{
    public static ArrayList<Integer> spiralMatrix(int mat[][]){
        ArrayList<Integer> result = new ArrayList<>();

        int m = mat.length;
        int n = mat[0].length;

        int top = 0;
        int bottom = m-1;
        int left = 0;
        int right = n-1;

        while(top <= bottom && left <= right){
            for(int i=left;i <= right;i++){
                result.add(mat[top][i]);
            }

            for(int i=top;i<=bottom;i++){
                result.add(mat[i][right]);
            }

            if(top < bottom){ // point
                for(int i=right;i>=left;i--){
                    result.add(mat[bottom][i]);
                }
            }

            if(left < right){
                for(int i=bottom;i>top;i--){ // point.
                    result.add(mat[i][left]); 
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }
}