package Logic_Master;
import java.util.Scanner;
import java.util.InputMismatchException;
class  PatternProgPage
{
	static Scanner in = new Scanner(System.in);
	public static void patternProg() throws Exception{
		boolean flag = true;
		
		do{
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("1. Square Fill Pattern");
			System.out.println("2. Number Increasing Pyramid Pattern");
			System.out.println("3. Triangle Star Pattern");
			System.out.println("4. Hour Glass Star Pattern");
			System.out.println("5. Zero to One Triangle Pattern");
			System.out.println("6. Exit");
			System.out.println("----------------------------");
			System.out.println();
			try {
				System.out.print("Choose a pattern program:");
			
				int pattern = in.nextInt();		// Expecting integer input

				switch(pattern){		// square fill
					case 1: {
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						square(n);
						break;
					}
					case 2: {		// number increasing
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						number(n);
						break;
					}
					case 3: {		// triangle star
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						triangle(n);
						break;
					}
					case 4: {		// hour glass
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						hourGlass(n);
						break;
					}	
					case 5: {		// zero - one triangle
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						zeroOne(n);
						break;
					}
					case 6: {		
						flag = false;
						break;
					}
					default: {
						System.out.println();
						System.out.println("Invalid choice. Please select a valid option.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Error: Invalid input. Please enter a valid number.");
				in.next();  // Clear the invalid input from the scanner buffer
			}
		} while (flag);	
		System.out.println();
		System.out.println("Exiting  pattern logic page.");
		System.out.print("\t\t<<<<<Redirecting to the category selection.");
		Thread.sleep(700);
		System.out.print(" .");
		Thread.sleep(700);
		System.out.print(" .");
		Thread.sleep(700);
		System.out.print(" .");
		Thread.sleep(1000);
		System.out.println();			
	}
	
	public static int userInput(){
		System.out.print("Enter the number n: ");
		int n = in.nextInt();
		return n;
	}
	
	public static void square(int n){		// square fill 
        for (int row = 1; row <=n; row++) {
            for (int col = 1; col <=n; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
	
	public static void number(int n){		// number increasing
        for (int row = 1; row <=n; row++) {
            for (int col = 1; col <=row; col++) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
	
	public static void triangle(int n){		// triangle star
        for (int row = 1; row <=n; row++) {
            for (int col = 1; col <=n-row; col++) {
                System.out.print("  ");
            }
            for (int col = 1; col <=2*row-1; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
	
	public static void hourGlass(int n){		// hour glass
        for (int row = 1; row <=2*n; row++) {
            int spaces = (row<=n) ? row-1 : 2*n-row;
            int stars = (row<=n) ? n+1-row : row-n;
            for (int col = 1; col <=spaces; col++) {
                System.out.print(" ");
            }
            for (int col = 1; col <=stars; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
	
	public static void zeroOne(int n){		// zero - one triangle
        int x = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col <= row; col++) {
                System.out.print(x + " ");
                x = 1 - x;
            }
            System.out.println();
        }
    }
		
}
