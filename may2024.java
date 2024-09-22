import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> squares = square(12);
        System.out.println(squares);
    }
     
    // FIRST ROW - Perfect Squares
    /**
     * Function to get all perfect squares that don't exceed the length "num"
     * @param num the number of digits in the maximum limit for perfect squares
     * @return a list of all perfect squares less than the limit
     */
    public static ArrayList<Integer> square(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        int limit = (int) Math.pow(10, num);
        int curr = 1; 
        while (curr * curr < limit) {
            list.add(curr * curr);
            curr += 1; 
        }
        return list;
    }
    // SECOND ROW - 1 more than a palindrome
    // THIRD ROW - Prime ^ Prime
    // FOURTH ROW - Sum of Digits is 7
    // FIFTH ROW - Fibonacci
    // SIXTH ROW - Perfect Square
    // SEVENTH ROW - Multiple of 37
    // EIGHTH ROW - Palindrome & Mulitple of 23
    // NINETH ROW - Product of Dgiits ends in 1
    // TENTH ROW - Multiple of 88
    // ELEVENTH ROW - 1 less than a paldndrome
}
