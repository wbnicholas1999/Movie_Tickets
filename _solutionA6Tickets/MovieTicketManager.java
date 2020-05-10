package _solutionA6Tickets;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.text.NumberFormat;

public class MovieTicketManager extends java.lang.Object implements MovieTicketManagerInterface {
	
	private java.text.NumberFormat currencyFormat;
	private java.util.ArrayList<Ticket> ticketList;
	
	public MovieTicketManager() {
		this.currencyFormat = NumberFormat.getCurrencyInstance();
		this.ticketList = new ArrayList<Ticket>();
	}
	
	public double addTicket(String movieName, String rating, int d, int t, String f, String type, int id) {
		Ticket ticket = null;
		
		if (type.equalsIgnoreCase("ADULT")) {
			ticket = new Adult(movieName, rating, d, t, f, type, id, this);
		} else if (type.equalsIgnoreCase("CHILD")) {
			if (rating.equalsIgnoreCase("PG") || rating.equalsIgnoreCase("G")) {
				ticket = new Child(movieName, rating, d, t, f, type, id, this);
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Unfortunately, a child ticket could not be saved.\n" +
						"The rating for child tickets must be either G or PG.");
				alert.showAndWait();
			}
		} else if (type.equalsIgnoreCase("EMPLOYEE")) {
			ticket = new Employee(movieName, rating, d, t, f, type, id, this);
		} else if (type.equalsIgnoreCase("MOVIEPASS")) {
			ticket = new MoviePass(movieName, rating, d, t, f, type, id, this);
		}
		
		if (ticket != null) {
			ticketList.add(ticket);
			return ticket.getTicketPrice();
		}
		return -1;
	}

	public ArrayList<String> get3DTickets() {
		sortByDay();
		
		ArrayList<String> threeDTickets = new ArrayList<>();
		
		for (Ticket ticket : ticketList) {
			if (ticket.getFeature().equals(Format.THREE_D)) {
				threeDTickets.add(ticket.toString() + "\n");
			}
		}
		
		return threeDTickets;
	}
	
	public ArrayList<String> getAllTickets() {
		sortByDay();
		
		ArrayList<String> allTickets = new ArrayList<>();
		
		for (Ticket ticket : ticketList) {
			allTickets.add(ticket.toString() + "\n");
		}
		
		return allTickets;
	}

	public ArrayList<String> getMoviePassTickets() {
		sortById();
		
		ArrayList<String> moviePassTickets = new ArrayList<>();
		
		for (Ticket ticket : ticketList) {
			if (ticket instanceof MoviePass) {
				moviePassTickets.add(ticket.toString() + "\n");
			}
		}
		
		return moviePassTickets;
	}
	
	public String monthlySalesReport() {
		String table = "";
		table += String.format("%15s %30s %15s", "", "Monthly Sales Report", "") + "\n";
		table += " \n";
		table += String.format("%15s %30s %15s", "", "Sales", "Number") + "\n";
		
		int countAdult = 0, countChild = 0, countEmployee = 0, countMoviePass = 0;
		double totalAdult = 0, totalChild = 0, totalEmployee = 0, totalMoviePass = 0;
		
		for (Ticket ticket : ticketList) {
			if (ticket instanceof Adult) {
				totalAdult += ticket.getTicketPrice();
				countAdult++;
			} else if (ticket instanceof Child) {
				totalChild += ticket.getTicketPrice();
				countChild++;
			} else if (ticket instanceof Employee) {
				totalEmployee += ticket.getTicketPrice();
				countEmployee++;
			} else if (ticket instanceof MoviePass) {
				totalMoviePass += ticket.getTicketPrice();
				countMoviePass++;
			}
		}
		
		table += String.format("%15s %22s %20s", "ADULT",
				currencyFormat.format(totalAdult), countAdult) + "\n";
		table += String.format("%15s %23s %20s", "CHILD",
				currencyFormat.format(totalChild), countChild) + "\n";
		table += String.format("%15s %20s %20s", "EMPLOYEE",
				currencyFormat.format(totalEmployee), countEmployee) + "\n";
		table += String.format("%15s %18s %20s", "MOVIEPASS",
				currencyFormat.format(totalMoviePass), countMoviePass) + "\n";
		table += "\nTotal Monthly Sales: " + currencyFormat.format(totalSalesMonth());
		
		return table;
	}

	public int numMoviesToday (int id, int date) {
		int visits = 0;
		
		for (Ticket ticket : ticketList) {
			if (ticket.getId() == id && ticket.getDay() == date) {
				visits++;
			}
		}
		
		return visits;
	}
	
	public int numThisMovie (int id, String movie) {
		int visits = 0;
		
		for (Ticket ticket : ticketList) {
			boolean watchedMovie = ticket.getMovieName().equalsIgnoreCase(movie);
			if (ticket.getId() == id && watchedMovie) {
				visits++;
			}
		}
		
		return visits;
	}
	
	public int numVisits(int id) {
		int visits = 0;
		
		for (Ticket ticket : ticketList) {
			if (ticket.getId() == id) {
				visits++;
			}
		}
		
		return visits;
	}
	
	public void readFile(File file) throws FileNotFoundException {
		Scanner scanFile = new Scanner(file);
		while(scanFile.hasNextLine()) {
			String[] movieInfo = scanFile.nextLine().split(":");
			String movieName = movieInfo[0];
			String rating = movieInfo[1];
			int day = Integer.parseInt(movieInfo[2]);
			int time = Integer.parseInt(movieInfo[3]);
			String feature = movieInfo[4];
			String type = movieInfo[5];
			int id = Integer.parseInt(movieInfo[6]);
			addTicket(movieName, rating, day, time, feature, type, id);
		}
	}
	
	public void sortByDay() {
		Ticket ticket;
		for (int i = 1; i < ticketList.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (ticketList.get(j).getDay() < ticketList.get(j - 1).getDay()) {
					ticket = ticketList.get(j);
					ticketList.set(j, ticketList.get(j - 1));
					ticketList.set(j - 1, ticket);
				}
			}
		}
	}
	
	private void sortById() {
		Ticket ticket;
		for (int i = 1; i < ticketList.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (ticketList.get(j).getId() < ticketList.get(j - 1).getId()) {
					ticket = ticketList.get(j);
					ticketList.set(j, ticketList.get(j - 1));
					ticketList.set(j - 1, ticket);
				}
			}
		}
	}
	
	public double totalSalesMonth() {
		double total = 0;
		
		for (Ticket ticket : ticketList) {
			total += ticket.getTicketPrice();
		}
		
		return total;
	}
}
