import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import _solutionA6Tickets.MovieTicketManager;

public class MovieTicketManagerSTUDENT_Test {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//Student add adult tickets
		
		//Student add children tickets
		
		//Student add employee tickets

		//Student add MoviePass member tickets

	}

	@After
	public void tearDown() throws Exception {
		//Student set ticketList to null;
	}

	/**
	 * Student Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		fail("testNumVisits not implemented yet");
		//Student test Employees' number of visits
	
		//Student test MoviePass members' number of visits
	
	}

	/**
	 * Student Test the number of times this movie has been viewed
	 * This only applied to those who have id numbers - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		fail("testNumThisMovie not implemented yet");
		//Student test Employees' number of views

		//Student test MoviePass members' number of views
		
	}

}