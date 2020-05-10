package _solutionA6Tickets;

public class MoviePass extends Ticket {
	private final double BEFORE_SIX_PM_FEE = 10.50;
	private final double SIX_PM_AND_AFTER_FEE = 13.50;
	private final double IMAX_FEE = 3.00;
	private final double THREE_D_FEE = 2.50;
	private final double TAX = 0.096;
	
	public MoviePass() {
		super();
	}
	
	public MoviePass(String movieName, String rating, int day, int time, String feature, String type, int id) {
		super(movieName, rating, day, time, feature, type, id);
		setTicketPrice(calculateTicketPrice());
	}
	
	public MoviePass(String movieName, String rating, int day, int time, String feature, String type, int id, MovieTicketManager m) {
		super(movieName, rating, day, time, feature, type, id, m);
		setTicketPrice(calculateTicketPrice());
	}
	
	public int getId() {
		return id;
	}
	
	public double calculateTicketPrice() {
		double price = 9.99;
		
		boolean hasWatchedThisMovie = ticketManager.numThisMovie(id, getMovieName()) != 0;
		boolean hasWatchedAMovieToday = ticketManager.numMoviesToday(id, getDay()) != 0;
		boolean isIMAXor3D = this.getFeature().equals(Format.IMAX) || this.getFeature().equals(Format.THREE_D);
		
		if (ticketManager.numVisits(id) > 0) {
			price = 0;
			
			if (hasWatchedThisMovie || hasWatchedAMovieToday || isIMAXor3D) {
				if (getTime() >= 8 && getTime() < 18) {
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
		}
		
		return price;
	}
	
	public String toString() {
		return super.toString();
	}
}
