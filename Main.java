import java.util.*;

public class Main {
    // Function to go to row a and find first 'o' space then place a queen

    //Function to print 2d array
    public static void printBoard(char[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {

                System.out.print(" " + a[i][j]
                                 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    

    //Function to set array values to 'o'
    public static void fillArray(char[][] a){
        for(int i = 0; i < a.length; i++) {
            Arrays.fill(a[i], 'o');
        }
    }

    public static boolean win(char[][] a){
        int qCount = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 'x'){
                    qCount++;
                }
            }
        }

        if (qCount == a.length){
            return true;
        }

        return false;

    }


    public static boolean isSafe(char[][] board, int row, int col)
    {
        int i, j;


        //System.out.println(col + " " + row);

        /* Check this row on left side */ 

        for (i = 0; i < col; i++)
            if (board[row][i] == 'x')
                return false;

        /* Check this row on right side */
        for (i = col; i < board.length; i++)
            if (board[row][i] == 'x')
                return false;


         /* Check this column on down */
        for (i = row; i < board.length; i++)
            if (board[i][col] == 'x')
                return false;


         /* Check this column on up  */
        for (i = 0; i < row ; i++)
            if (board[i][col] == 'x')
                return false;


        /* Check upper diagonal on left side */
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == 'x')
                
                return false;


        /* Check lower diagonal on right side */
        for (i=row, j=col; i<board.length && j<board.length; i++, j++)
            if (board[i][j] == 'x')
                
                return false;


        /* Check lower diagonal on left side */
        for (i=row, j=col; j>=0 && i<board.length; i++, j--)
            if (board[i][j] == 'x')
                
                return false;

        /* Check upper diagonal on right side  */
        for (i=row, j=col; j<board.length && i>=0; i--, j++)
            if (board[i][j] == 'x')
                
                return false;
       
        
        return true;
    }


    public static boolean solve(char[][] board, int col, int index)
    {
        /* base case: If all queens are placed
         then return true */
        boolean Foo = false;
        if(col == index){
            solve(board, col + 1, index);
        }

        if(col>=board.length){
            Foo = true;
            return true; 
        } 
        else {
        /* Consider this column and try placing
         this queen in all rows one by one */
        for (int i = 0; i < board.length; i++)
        {

            /* Check if queen can be placed on
             board[i][col] */
            if (isSafe(board, i, col)) {
                /* Place this queen in board[i][col] */
                board[i][col] = 'x';

                /* recur to place rest of the queens */
                if (solve(board, col + 1, index) == true){
                    Foo = true;
                }
                /* If placing queen in board[i][col]
                 doesn't lead to a solution then
                 remove queen from board[i][col] */

                if(win(board)){
                    return true;
                } 
                board[i][col] = 'o'; // BACKTRACK
            }
        }
       } 
        /* If queen can not be place in any row in
         this colum col, then return false */
        //System.out.println(Foo);
        return Foo;
    }


    public static void main(String[] args) {

        int chessboardSize = 8;
        int Xcord = 2;
        int Ycord = 3;
        int index = Ycord-1;
        // Create 2D char array
        char[][] board = new char[chessboardSize][chessboardSize];

        // fill array with 'o' or open spaces
        fillArray(board);

        // Place first queen
        board[Xcord-1][Ycord-1] = 'x';
        /*board[0][0] = 'x';
        board[][] = 'x';
        board[][] = 'x';
        board[][] = 'x';
        board[5][6] = 'x';
        board[6][3] = 'x'; */
        printBoard(board); 
        if (solve(board,0,index) == false){
            System.out.println("No solutions");
        }
        else {
            printBoard(board);
        }


}
}