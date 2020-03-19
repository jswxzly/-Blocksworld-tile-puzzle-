import java.util.Arrays;

public class Node {
	 int[][] board;
	 String boardstring;
	 int x_label;
	 int y_label;
	 int depth;
	 
	 //A*
	 int heuristic;
	        
	 Node(int[][] B, int x, int y, int d) {
		 board = B;
	     boardstring = Arrays.deepToString(board);
	     x_label = x;
	     y_label = y;
	     depth = d;
	     
	     heuristic = 0;
	     int X = B.length; 
	     int Y = B[0].length;
	     for (int xx = 0; xx < X; ++xx)
	            for (int yy = 0; yy < Y; ++yy) {
	                if (board[xx][yy] == 1)
	                	heuristic += Math.abs(xx - 1) + Math.abs(yy - 1);
	                if (board[xx][yy] == 2)
	                	heuristic += Math.abs(xx - 2) + Math.abs(yy - 1);
	                if (board[xx][yy] == 3)
	                	heuristic += Math.abs(xx - 3) + Math.abs(yy - 1);
//	                if (board[xx][yy] == 4)
//	                	heuristic += Math.abs(xx - 4) + Math.abs(yy - 1);
//	                if (board[xx][yy] == 5)
//	                	heuristic += Math.abs(xx - 5) + Math.abs(yy - 1);
//	                if (board[xx][yy] == 6)
//                		heuristic += Math.abs(xx - 6) + Math.abs(yy - 1);
	            }

	 }
}
