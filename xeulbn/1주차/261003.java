import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int pokemonNum = nums.length/2;
        Set<Integer> pokemonSort = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            pokemonSort.add(nums[i]);
        }

        if(pokemonSort.size()==pokemonNum){
            return pokemonNum;
        }else if(pokemonSort.size()<pokemonNum){
            return pokemonSort.size();
        }else{
            return pokemonNum;
        }

    }
}
