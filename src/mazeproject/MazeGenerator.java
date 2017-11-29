package mazeproject;

public class MazeGenerator {
	public int[] exitPosition = new int[2];
	public  int[][]mazeArray = new int[100][];
 public MazeGenerator(int[][] array) {
		mazeArray = array;
		for(int row = 0; row < mazeArray.length; row++) {
			for(int column  = 0; column < mazeArray[row].length; column++) {
					if(mazeArray[row][column] == 1) {
						if(row + 1 == mazeArray.length || column + 1 == mazeArray[row].length || row == 0 || column == 0) {
						
						exitPosition[0] = row;
						exitPosition[1] = column;
						System.out.println(row);
					}

				}
			}
		}
 }

}