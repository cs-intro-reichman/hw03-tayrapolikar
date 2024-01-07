public class LoanCalc {

	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter=0;    // Monitors the efficiency of the calculation

	/**
	 * Gets the loan data and computes the periodical payment.
	 * Expects to get three command-line arguments: sum of the loan (double),
	 * interest rate (double, as a percentage), and number of payments (int).
	 */
	public static void main(String[] args) {
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}

	/**
	 * Uses a sequential search method  ("brute force") to compute an approximation
	 * of the periodical payment that will bring the ending balance of a loan close to 0.
	 * Given: the sum of the loan, the periodical interest rate (as a percentage),
	 * the number of periods (n), and epsilon, a tolerance level.
	 */
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double PeriodicalPayment=loan/n;
		double balance=loan-PeriodicalPayment;
		double FinalBalance = balance * ((100 + rate) / 100);

		while (FinalBalance>0){
			PeriodicalPayment= PeriodicalPayment+epsilon;
			balance = loan - PeriodicalPayment;
			FinalBalance = balance * (1 + rate / 100);
			iterationCounter++;
		}
		return 0;
	}

	/**
	 * Uses bisection search to compute an approximation of the periodical payment
	 * that will bring the ending balance of a loan close to 0.
	 * Given: the sum of the loan, the periodical interest rate (as a percentage),
	 * the number of periods (n), and epsilon, a tolerance level.
	 */
	// Side effect: modifies the class variable iterationCounter.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double L=loan/2*n;
		double H=loan/n;
		double PeriodicalPayment=(H+L)/2;
		double balance=loan-PeriodicalPayment;
		double FinalBalance = balance * ((100 + rate) / 100);


		while (H-L>epsilon){
			if(FinalBalance*L>0){
				L=PeriodicalPayment; //L büyürse f(L) küçülür
			} else {
				H=PeriodicalPayment;
			}
			PeriodicalPayment= (H+L)/2;
			balance = loan - PeriodicalPayment;
			FinalBalance = balance * (100 + rate / 100);
			iterationCounter++;
		}

		return PeriodicalPayment;
	}

	/**
	 * Computes the ending balance of a loan, given the sum of the loan, the periodical
	 * interest rate (as a percentage), the number of periods (n), and the periodical payment.
	 */
	private static double endBalance(double loan, double rate, int n, double payment) {
		double endBalance=loan;
		for (int i = 0; i <n; i++) {
			endBalance=(endBalance-payment)*(1+rate/100);
			iterationCounter++;
		}
		return endBalance;
	}
}
