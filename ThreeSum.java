import java.util.*;

class Solution{
    public static boolean threeSum(int arr[], int target){
        int n = arr.length;

        Arrays.sort(arr);

        for(int i=0;i<n-2;i++){
            int left = i+1, right = n-1;

            int targetedSum = target - arr[i];

            while(left < right){
                if(arr[left] + arr[right] == targetedSum){
                    return true;
                }else if (arr[left] + arr[right] < targetedSum){
                    left++;
                }else if (arr[left] + arr[right] > targetedSum){
                    right--;
                }
            }
        }
        return false;
    }
}