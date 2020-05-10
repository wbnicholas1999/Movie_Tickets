package _solutionA6Tickets;
import java.text.NumberFormat;

public abstract class Ticket {
	private Format feature;
	private String movieName;
	private String rating;
	private String type;
	private double ticketPrice;
	private int day;
	private int time;
	public int id;
	
	protected MovieTicketManager ticketManager;
	
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	public Ticket() {
		// lol, what's the point of this?
	}
	
	public Ticket(String movieName, String rating, int day, int time, String feature, String type, int id) {
		setMovieName(movieName);
		setRating(rating);
		setDay(day);
		setTime(time);
		
		if (feature.equalsIgnoreCase("IMAX")) {
			setFeature(Format.IMAX);
		} else if (feature.equalsIgnoreCase("3D")) {
			setFeature(Format.THREE_D);
		} else if (feature.equalsIgnoreCase("NONE")) {
			setFeature(Format.NONE);
		}
		
		setType(type);
		setId(id);
	}
	
	public Ticket(String movieName, String rating, int day, int time, String feature, String type, int id, MovieTicketManager m) {
		this(movieName, rating, day, time, feature, type, id);
		ticketManager = m;
	}
	
	public Format getFeature() {
		return feature;
	}
	
	public void setFeature(Format feature) {
		this.feature = feature;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract int getId();
	
	public abstract double calculateTicketPrice();
	
	public String toString() {
		return type.toUpperCase() + ((id > 0) ? "-" + id + " " : " ") +
				(feature.equals(Format.NONE) ? "" :
					(feature.equals(Format.THREE_D) ? "3D" : feature))
				+ "Movie: " + movieName + " Rating: " + rating + " Day: " + day
				+ " Time: " + time + " Price: " + currencyFormat.format(ticketPrice);
	}
}
