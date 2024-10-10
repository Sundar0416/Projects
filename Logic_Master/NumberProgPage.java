package Logic_Master;
import java.util.Scanner;
import java.util.InputMismatchException;
class  NumberProgPage
{
	static Scanner in = new Scanner(System.in);
	public static void numberProg() throws Exception{
		boolean flag = true;
		
		do{
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("1. Prime Number ?");
			System.out.println("2. Perfect Number ?");
			System.out.println("3. Palindrome Number ?");
			System.out.println("4. Neon Number ?");
			System.out.println("5. Spy Number ?");
			System.out.println("6. Exit");
			System.out.println("----------------------------");
			System.out.println();

			try {
				System.out.print("Choose a number logic program:");
			
				int number = in.nextInt();		// Expecting integer input
				
				switch(number){
					case 1: {		// prime
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						prime(n);
						break;
					}
					case 2: {		// perfect
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						perfect(n);
						break;
					}
					case 3: {		 // palindrome
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						pali(n);
						break;
					}
					case 4: {		// neon
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						neon(n);
						break;
					}	
					case 5: {		// spy
						int n = userInput();
						Thread.sleep(1000);
						System.out.println();
						spy(n);
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
		System.out.println("Exiting number logic page.");
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
		System.out.print("Enter the number: ");
		int num = in.nextInt();
		return num;
	}
	
	public static void neon(int num){		//neon
		int square = num*num;
		int sum = 0;
		while(square>0){
			sum+=square%10;
			square/=10;
		}
		if(sum == num)
			System.out.println(num+" is Neon number");
		else 
			System.out.println(num+" is not Neon number");
	}
	
	public static void spy(int num){		//spy
		int product = 1;
		int sum = 0;
		while(num>0){
			sum+=num%10;
			product*=num%10;
			num/=10;
		}
		if(sum == product)
			System.out.println("It is Spy number");
		else 
				System.out.println("It is not Spy number");
	}
	
	public static void prime(int n){		//prime
		
		if(n==0 || n==1)
			System.out.println(n+" is neither prime nor composite");
		else{
			boolean isprime = true;		
			for(int i=2;i<n;i++){
				if(n%i==0){
					isprime = false;
					break;
				}
			}
			if(isprime)
				System.out.println(n+" is prime number");
			else
				System.out.println(n+" is not prime number");
		}
	}
	
	public static void perfect(int num){
		int sum = 0;
		for(int i =1;i<=num/2;i++){		// perfect number
			if(num%i==0)
				sum+=i;
		}
		if(sum==num)
			System.out.println(num+" is perfect number");
		else
			System.out.println(num+" is not a perfect number");
	}
	
	public static void pali(int num){		//palindrome
		if(num<9 && num>=0)
			System.out.println(num+" is palindrome");
		else{
			int rev = 0;
			int copy = num;
			while(copy>0){
				rev = rev*10 + copy%10;
				copy/=10;
			}
			if(rev == num)
				System.out.println(num+" is palindrome :)");
			else
				System.out.println(num+" is not palindrome :(");
		}
	}
}

