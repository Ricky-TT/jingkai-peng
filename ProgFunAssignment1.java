package progFunAssignment1;
//import whatever packages are needed here

import java.util.Scanner;

public class ProgFunAssignment1 {

 
	public static void main(String[] args) {
	 // Create a Scanner object for input
		Scanner scanner = new Scanner(System.in);
        int maxRow, maxCol;
        maxRow = inputNumber(scanner, 3, 10, "Input max row(3 ~ 10): ");
        maxCol = inputNumber(scanner, 3, 10, "Input max column(3 ~ 10): ");
            
     // Create an object of class MyBlock using the 'new' operator, calling on the MyBlock constructor.
        MyBlock myBlock = new MyBlock(maxRow, maxCol);
     
     // Build a loop to display the menu, prompt for input and process it as per requirements.
        runMenu(myBlock, scanner, maxRow, maxCol);
     // Your program should quit gracefullu
        }
	
	private static void displayMenu() {
        System.out.println("1. Add a house");
        System.out.println("2. Display the block");
        System.out.println("3. Clear the block");
        System.out.println("4. Quit");
    }
	
	 public static void runMenu(MyBlock myBlock, Scanner scanner, int maxRow, int maxCol) {
	        int houseMark = 1;
	        while (true) {
	            displayMenu();
	            System.out.println("Please select option from [1] - [4]");
	            int option = scanner.nextInt();
	            switch (option) {
	                case 1:
	                    System.out.println("Input X pos:");
	                    int x = scanner.nextInt();
	                    System.out.println("Input Y pos:");
	                    int y = scanner.nextInt();
	                    System.out.println("Input Row:");
	                    int row = scanner.nextInt();
	                    System.out.println("Input Col:");
	                    int col = scanner.nextInt();
	                    myBlock.addHouse(x, y, row, col);
	                    break;
	                case 2:
	                    myBlock.displayBlock();
	                    break;
	                case 3:
	                    myBlock.clearBlock();
	                    break;
	                case 4:
	                    return;
	                default:
	                    System.out.println("Error input, please only input from [1] - [4]");
	            }
	        }
	    }

	 private static int inputNumber(Scanner scanner, int min, int max, String info)
	    {	
		 	int number;
	        do
	        {
	            System.out.println(info);
	            number = scanner.nextInt();
	            if (!isNumberValid(number, min, max))
	                System.out.println("Error, must be greater than " + min + " and less than or equal to " + max);
	        } while (!isNumberValid(number, min, max));
	        return number;
	    }

	    private static boolean isNumberValid(int input, int min, int max)
	    {
	        if (input < min)
	            return false;
	        if (input > max)
	            return false;
	        return true;
	    }
}

//MyBlock class 

//It does NOT need to handle any input at all.  
//All the input should occur in the main method
//
//If you find yourself needing to do input here rethink your solution.

class MyBlock {
	private int[][] block;
	private boolean vacant;
	private int houseMark;
	// you may need to add other variables.	
	// Constructor...
	// Assumption - rows and columns has been validated beforehand.
	// Initialise the block so that each cell is set to the character '0' 
	// (hint: use one of your methods!).
	// Set the initial value of any other variables
	public MyBlock(int maxRows, int maxColumns) {
		this.block = new int[maxRows][maxColumns];
		clearBlock();
	}
	
	
	// Display the entrie block as a matrix 
	public void displayBlock() {
		for(int i = 0; i < block.length; i++){
			for(int j = 0; j < block[i].length; j++) {
				System.out.print(block[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	
	// Clear out the block. This involves setting each cell to be '0'
	public void clearBlock() {
		 for (int i = 0; i < this.block.length; i++) {
	            for (int j = 0; j < this.block[i].length; j++) {
	                this.block[i][j] = 0;
	            }
	        }
	        this.vacant = true;
	        this.houseMark = 1;
	}
	
	
	// Build a house on the block
	
	// Ensure none of the other rules are violated
	// Return if any rule is violated
	// An error message should display when an error occurs
	// If all is ok "build" the house on the block 
	// Update relevant variable(s) and call on displayLand().
	public boolean addHouse(int rowPos, int colPos, int rows, int columns) {
		 int houseMark = 1;
		// You may find the code snippt below useful
		 if (isHousePosValid(rowPos, colPos, rows, columns)) {
	            for (int i = 0; i < this.block.length; i++) {
	                for (int j = 0; j < this.block[i].length; j++) {
	                    if (i >= rowPos && i <= rowPos + rows - 1 &&
	                            j >= colPos && j <= colPos + columns - 1)
	                        this.block[i][j] = this.houseMark;
	                }
	            }
	            System.out.println("Success!\n");
	            this.houseMark++;
	            return true;
	        }
	        else{
	            System.out.println("Fail! Error position!\n");
	            return false;
	        }
	}
	
	public boolean isHousePosValid(int rowPos, int colPos, int rows, int columns) {
        if (rowPos == 0 || colPos == 0) {
            System.out.println("Can not put house on the edge!");
            return false;
        }
        if (rows > this.block.length - rowPos - 1 || columns > this.block[0].length - colPos - 1) {
            System.out.println("House size over the edge!");
            return false;
        }
        for (int i = rowPos - 1; i <= rowPos + rows; i++) {
            for (int j = colPos - 1; j <= colPos + columns; j++) {
                    if (this.block[i][j] != 0){
                        System.out.println("No enough gap between two houses");
                        return false;
                    }
            }
        }
        return true;
    }
		
}