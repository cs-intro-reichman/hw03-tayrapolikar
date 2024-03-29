
public class Calendar {
	static int dayOfMonth = 1;
	static int month = 1;
	static int dayOfWeek = 2;     // 1.1.1900 was a Monday (2nd day as sunday,monday)
	static int nDaysInMonth = 31; // Number of days in January
	static int year=1900;


	public static void main(String args[]) {
		int newYear = Integer.parseInt(args[0]);
		int debugDaysCounter = 0;
		String isSunday="";
		int firstSundayCounter=0;


		while (true) {
			isSunday="";
			if(dayOfWeek==1){
				isSunday = " Sunday";
				if(dayOfMonth==1){
				}
			}
			if(year==newYear) {
				System.out.println(dayOfMonth + "/" + month + "/" + year + isSunday);
			}
			advance();
			debugDaysCounter++;

			if (year>newYear) {
				break;
			}
		}
	}
	private static void advance() {
		nDaysInMonth = nDaysInMonth(month, year);

		dayOfWeek++;
		dayOfWeek = dayOfWeek % 7;
		dayOfMonth++;
		if (dayOfMonth > nDaysInMonth) {
			month++;
			dayOfMonth = 1;
		}
		if (month > 12) {
			month = 1;
			year++;
		}
	}

	public static boolean isLeapYear(int year) {
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return true;
		}
		return false;
	}

	public static int nDaysInMonth(int month, int year) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 2 && isLeapYear(year)) {
			return 29;
		} else if (month == 2) {
			return 28;
		}
		return 0;
	}
}
