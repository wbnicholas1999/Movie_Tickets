package _solutionA6Tickets;

public class Employee extends Ticket {
	private final double BEFORE_SIX_PM_FEE = 5.25;
	private final double SIX_PM_AND_AFTER_FEE = 6.75;
	private final double IMAX_FEE = 1.50;
	private final double THREE_D_FEE = 1.75;
	private final double TAX = 0.096;
	
	public Employee() {
		super();
	}
	
	public Employee(String movieName, String rating, int day, int time, String feature, String type, int id) {
		super(movieName, rating, day, time, feature, type, id);
		setTicketPrice(calculateTicketPrice());
	}
	
	public Employee(String movieName, String rating, int day, int time, String feature, String type, int id, MovieTicketManager m) {
		super(movieName, rating, day, time, feature, type, id, m);
		setTicketPrice(calculateTicketPrice());
	}
	
	public int getId() {
		return -1;
	}
	
	public double calculateTicketPrice() {
		double price = 0;
		
		if (ticketManager.numVisits(id) > 2) {
			if (getTime() > 7 && getTime() < 18) {
				price += BEFORE_SIX_PM_FEE;
			} else if (getTime() >= 18 && getTime() < 24) {
				price += SIX_PM_AND_AFTER_FEE;
			}
			
			if (getFeature().equals(Format.IMAX)) {
				price += IMAX_FEE;
			}
			
			if (getFeature().equals(Format.THREE_D)) {
				price += THREE_D_FEE;
			}
			
			price += price * TAX;
		}
		
		return price;
	}
	
	public String toString() {
		return super.toString();
	}
}
