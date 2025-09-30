class Solution {
    public static int maxWaterStorage(int height[]) {
        int lp = 0; // left pointer
        int rp = height.length - 1; // right pointer
        int maxWater = 0;

        while (lp < rp) {
            int ht = Math.min(height[lp], height[rp]); 
            int wt = rp - lp; 

            int currentWater = ht * wt;
            maxWater = Math.max(maxWater, currentWater);
          
            if (height[lp] < height[rp]) {
                lp++;
            } else {
                rp--;
            }
        }

        return maxWater; 
    }
}
