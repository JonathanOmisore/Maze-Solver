package mazeproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class MazeSolver extends Application{
	private static int[] currentPosition = new int[2];
	private static MazeGenerator maze;
	Image robot = new Image("robot.png");
	ImageView robotView = new ImageView(robot);
	int currentPositionRow = 0;
	int currentPositionColumn = 2;
	GridPane pane = new GridPane();
	BorderPane borderPane = new BorderPane();
	Button takeStepButton = new Button("Take Step");
	Button findExitButton = new Button("Find Exit");
	int status = 2;
	Label statusLabel = new Label();
	Map<String,ImageView> yellows = new HashMap<String, ImageView>();

	public static void main(String[] args) {
		int[][] multi = new int[][]{
			{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0},
			{0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			maze = new MazeGenerator(multi);


			launch(args);

	}


	private void displayMaze() {

		for(int j = 0; j < maze.mazeArray.length; j++) {
			for(int x = 0; x < maze.mazeArray[j].length; x++) {
				if(j ==0 && x == 2) {
					robotView.setFitWidth(32);
					robotView.setFitHeight(32);
					pane.add(robotView, x,j);
					//System.out.print("@");
				}
				else if(maze.mazeArray[j][x] == 0) {
					Image wall = new Image("wall.png");
					ImageView wallView = new ImageView(wall);
					wallView.setFitWidth(32);
					wallView.setFitHeight(32);
					pane.add(wallView, x,j);
				}
				else if(maze.mazeArray[j][x] == 5) {
					//System.out.print(" ");
				}
				else if (maze.mazeArray[j][x] == 1) {
				}
				else if(maze.mazeArray[j][x] == 3) {

					//pane.add(yellowView, x, j);
				}
			}

		}

	}
	private void changeStatus(int status) {
		switch(status) {

		case -1:
			statusLabel.setText("I can't solve the maze");
			break;
		case 0:
			statusLabel.setText("Wrong direction - finding correct path");
			break;
		case 1:
			statusLabel.setText("Maze complete!");
			break;
		case 2:
			statusLabel.setText("");
			break;
		}
	}
	private void takeStep() {

		if(currentPositionRow == maze.exitPosition[0] && currentPositionColumn == maze.exitPosition[1]) {
			changeStatus(1);

		}
		if(currentPositionRow > 0) {


			if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 3){
				changeStatus(2);
				pane.setColumnIndex(robotView, currentPositionColumn - 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;


				pane.add(yellowView,currentPositionColumn, currentPositionRow);
				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow), yellowView);
				currentPositionColumn-=1;

			}
			else if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 3) {
				changeStatus(2);
				pane.setRowIndex(robotView, currentPositionRow + 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1), yellowView);

				pane.add(yellowView,currentPositionColumn, currentPositionRow);
				currentPositionRow+=1;


			}
			else if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 3){
				changeStatus(2);
				pane.setColumnIndex(robotView, currentPositionColumn + 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow), yellowView);
				pane.add(yellowView,currentPositionColumn, currentPositionRow);
				currentPositionColumn+=1;

			}
			else if(maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 3){
				changeStatus(2);
				pane.setRowIndex(robotView, currentPositionRow - 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1), yellowView);
				pane.add(yellowView,currentPositionColumn, currentPositionRow);
				currentPositionRow -=1;
			}
			else if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 0 || maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 5){


				if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 5 || maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 3) {
					changeStatus(0);
					pane.setColumnIndex(robotView, currentPositionColumn + 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

					currentPositionColumn+=1;


				}


			}

			else if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 0 || maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 5){

				if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 5 || maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 3) {

					changeStatus(0);
					pane.setColumnIndex(robotView, currentPositionColumn - 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn - 1) + Integer.toString(currentPositionRow)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn -1) + Integer.toString(currentPositionRow));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);

					yellows.put(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
					currentPositionColumn-=1;


				}

			}
			else if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 0 || maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 5){

				if(maze.mazeArray[currentPositionRow][currentPositionColumn] == 5 || maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 3) {
					changeStatus(0);
					pane.setRowIndex(robotView, currentPositionRow - 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;



				}
				else
					System.out.println(maze.mazeArray[currentPositionRow][currentPositionColumn]);


			}

			else if(maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 0 || maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 5){
				if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 5 || maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 3) {

					changeStatus(0);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1)));
					yellows.remove(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1));

					pane.setRowIndex(robotView, currentPositionRow + 1);
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
					pane.add(yellowView,currentPositionColumn, currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					currentPositionRow+=1;


				}

			}
		}
		else if(currentPositionRow == 0) {
			if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 3){
				changeStatus(2);
				pane.setColumnIndex(robotView, currentPositionColumn - 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

				currentPositionColumn-=1;

				pane.add(yellowView,currentPositionColumn + 1, currentPositionRow);
				yellows.put(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow), yellowView);
			}
			else if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 3) {
				changeStatus(2);
				pane.setRowIndex(robotView, currentPositionRow + 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
				currentPositionRow+=1;
				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1), yellowView);

				pane.add(yellowView,currentPositionColumn, currentPositionRow - 1);

			}
			else if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 3){
				changeStatus(2);
				pane.setColumnIndex(robotView, currentPositionColumn + 1);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

				currentPositionColumn+=1;
				yellows.put(Integer.toString(currentPositionColumn - 1) + Integer.toString(currentPositionRow), yellowView);

				pane.add(yellowView,currentPositionColumn - 1, currentPositionRow);
			}
			else if(maze.mazeArray[currentPositionRow][currentPositionColumn] == 3){
				changeStatus(2);
				pane.setRowIndex(robotView, currentPositionRow);
				Image yellow = new Image("yellow.png");
				ImageView yellowView = new ImageView(yellow);
				yellowView.setFitWidth(32);
				yellowView.setFitHeight(32);
				maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

				yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1), yellowView);
				pane.add(yellowView,currentPositionColumn, currentPositionRow + 1);
			}
			else if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 0 || maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 5){


				if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 5 || maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 3) {
					changeStatus(0);
					pane.setColumnIndex(robotView, currentPositionColumn + 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

					currentPositionColumn+=1;


				}


			}

			else if(maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 0 || maze.mazeArray[currentPositionRow][currentPositionColumn + 1] == 5){

				if(maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 5 || maze.mazeArray[currentPositionRow][currentPositionColumn - 1] == 3) {

					changeStatus(0);
					pane.setColumnIndex(robotView, currentPositionColumn - 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn - 1) + Integer.toString(currentPositionRow)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn -1) + Integer.toString(currentPositionRow));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);

					yellows.put(Integer.toString(currentPositionColumn + 1) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
					currentPositionColumn-=1;


				}

			}
			else if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 0 || maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 5){

				if(maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 5 || maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 3) {
					changeStatus(0);
					pane.setRowIndex(robotView, currentPositionRow - 1);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1)));
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					yellows.remove(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow - 1));
					pane.add(yellowView,currentPositionColumn,currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;

					currentPositionRow-=1;


				}
				else
					System.out.println(maze.mazeArray[currentPositionRow][currentPositionColumn]);


			}

			else if(maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 0 || maze.mazeArray[currentPositionRow - 1][currentPositionColumn] == 5){
				if(maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 5 || maze.mazeArray[currentPositionRow + 1][currentPositionColumn] == 3) {

					changeStatus(0);
					pane.getChildren().remove(yellows.get(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1)));
					yellows.remove(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow + 1));

					pane.setRowIndex(robotView, currentPositionRow + 1);
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					maze.mazeArray[currentPositionRow][currentPositionColumn] = 5;
					pane.add(yellowView,currentPositionColumn, currentPositionRow);
					yellows.put(Integer.toString(currentPositionColumn) + Integer.toString(currentPositionRow),yellowView);

					currentPositionRow+=1;


				}

			}
			
		}

	}



	private void findExit() {
		for(int j = 0; j < maze.mazeArray.length; j++) {
			for(int x = 0; x < maze.mazeArray[j].length; x++) {

				if(maze.mazeArray[j][x] == 3) {
					Image yellow = new Image("yellow.png");
					ImageView yellowView = new ImageView(yellow);
					yellowView.setFitWidth(32);
					yellowView.setFitHeight(32);
					pane.add(yellowView, x,j);
				}

			}}}

	private boolean findPath(int currentLocationRow, int currentLocationColumn) {
		System.out.println("Row: " + currentLocationRow);
		System.out.println("Column: " + currentLocationColumn);
		if(currentLocationRow == maze.exitPosition[0] && currentLocationColumn ==maze.exitPosition[1]) {
			maze.mazeArray[currentLocationRow][currentLocationColumn] = 3;

			return true;
		}
		if(currentLocationRow < 0 || currentLocationColumn < 0)
			return false;
		if(maze.mazeArray[currentLocationRow][currentLocationColumn] == 0)
			return false;
		if(maze.mazeArray[currentLocationRow][currentLocationColumn] == 5)
			return false;
		maze.mazeArray[currentLocationRow][currentLocationColumn] = 5;

		if(findPath(currentLocationRow + 1,currentLocationColumn))  {
			maze.mazeArray[currentLocationRow][currentLocationColumn] = 3;

			return true;
		}

		if(findPath(currentLocationRow,currentLocationColumn + 1)) { 
			maze.mazeArray[currentLocationRow][currentLocationColumn] = 3;

			return true;
		}
		if(findPath(currentLocationRow,currentLocationColumn - 1))  {
			maze.mazeArray[currentLocationRow][currentLocationColumn] = 3;

			return true;
		}
		if(findPath(currentLocationRow - 1,currentLocationColumn)) {
			maze.mazeArray[currentLocationRow][currentLocationColumn] = 3;
			return true;
		}





		return false;




	}

	@Override
	public void start(Stage arg0) throws Exception {
		HBox box = new HBox();
		box.getChildren().add(takeStepButton);
		box.getChildren().add(findExitButton);
		box.getChildren().add(statusLabel);
		borderPane.setBottom(box);
		borderPane.setCenter(pane);
		currentPosition[0] = 0;
		currentPosition[1] = 2;
		findPath(0,2);
		displayMaze();
		takeStepButton.setOnAction(e->{
			takeStep();
		});
		findExitButton.setOnAction(e->{
			findExit();
		});
		Scene scene = new Scene(borderPane);
		arg0.setScene(scene);
		arg0.show();
	}
}

