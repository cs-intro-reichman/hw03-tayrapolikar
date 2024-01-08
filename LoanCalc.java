public class  LoanCalc {
	static double  epsilon = 0.001;
	static int iterationCounter=0;

	public static void main(String[] args) {
		double loan=Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
		iterationCounter=0;

		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

	}
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double g=loan/n;
		while (endBalance(loan,rate,n,g)>0){
			g= g+epsilon;
			iterationCounter++;
		}
		return g;
	}

	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double high = loan;
		double low = loan / n;
		double payment = (high + low) / 2;
		while (high - low > epsilon) {
			if (endBalance(loan, rate, n, payment) * endBalance(loan, rate, n, low) > 0) {
				low = payment;
			} else {
				high = payment;
			}
			payment = (high + low) / 2;
			iterationCounter++;
		}
		return payment;
	}
	private static double endBalance ( double loan, double rate, int n, double payment){
		double endBalance = loan;
		for (int i = 0; i < n; i++) {
			endBalance = (endBalance - payment) * (1 + rate / 100);
		}
		return endBalance;
	}
}