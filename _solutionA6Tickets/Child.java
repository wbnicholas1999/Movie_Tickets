package _solutionA6Tickets;

public class Child extends Ticket {
	private final double BEFORE_SIX_PM_FEE = 5.75;
	private final double SIX_PM_AND_AFTER_FEE = 10.75;
	private final double IMAX_FEE = 2.00;
	private final double THREE_D_FEE = 1.50;
	private final double TAX = 0.096;
	
	public Child() {
		super();
	}
	
	public Child(String movieName, String rating, int day, int time, String feature, String type, int id) {
		super(movieName, rating, day, time, feature, type, id);
		setTicketPrice(calculateTicketPrice());
	}
	
	public Child(String movieName, String rating, int day, int time, String feature, String type, int id, MovieTicketManager m) {
		super(movieName, rating, day, time, feature, type, id, m);
		setTicketPrice(calculateTicketPrice());
	}
	
	public int getId() {
		return -1;
	}
	
	public double calculateTicketPrice() {
		double price = 0;
		
		if (getTime() >= 8 && getTime() < 18) {
			price += BEFORE_SIX_PM_FEE;
		} else if (getTime() >= 18 && getTime() < 23) {
			price += SIX_PM_AND_AFTER_FEE;
		}
		
		if (getFeature().equals(Format.IMAX)) {
			price += IMAX_FEE;
		}
		
		if (getFeature().equals(Format.THREE_D)) {
			price += THREE_D_FEE;
		}
		
		price += price * TAX;
		
		return price;
	}
	
	public String toString() {
		return super.toString();
	}
}
