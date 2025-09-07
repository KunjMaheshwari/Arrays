class Solution{
    public static void swapping(int arr[]){
        for(int i=0;i<arr.length;i++){
            if(i % 2 == 0){
                if(arr[i] > arr[i+1]){
                    swap(arr, i, i+1);
                }
            }else{
                if(arr[i] < arr[i+1]){
                    swap(arr, i, i+1);
                }
            }
        }
    }

    public static void swap(int arr[], int start, int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}