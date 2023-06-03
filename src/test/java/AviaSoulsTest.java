import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {

    AviaSouls aviaSouls = new AviaSouls();

    Ticket ticket1 = new Ticket("London", "Paris", 10000, 11_20, 14_20);
    Ticket ticket2 = new Ticket("London", "Paris", 8500, 12_30, 16_40);
    Ticket ticket3 = new Ticket("London", "Paris", 7000, 11_00, 15_30);
    Ticket ticket4 = new Ticket("London", "Paris", 9000, 15_00, 18_50);
    Ticket ticket5 = new Ticket("London", "Paris", 6500, 13_00, 17_40);
    Ticket ticket6 = new Ticket("Milan", "Barcelona", 6600, 13_10, 17_50);
    Ticket ticket7 = new Ticket("Milan", "Barcelona", 6700, 13_20, 18_00);

    Ticket ticket8 = new Ticket("Moscow", "Saint-Petersburg", 6700, 13_20, 18_00);

    @BeforeEach

    public void setup(){

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        aviaSouls.add(ticket7);
        aviaSouls.add(ticket8);


    }

    @Test

    public void shouldFindAllTickets(){

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8};
        Ticket[] actual = aviaSouls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldCompareTicketsByPrice(){

        Ticket ticket6 = new Ticket("London", "Paris", 6500, 14_00, 16_00);
        aviaSouls.add(ticket6);

        Assertions.assertEquals(-1, ticket5.compareTo(ticket4));
        Assertions.assertEquals(1, ticket4.compareTo(ticket5));
        Assertions.assertEquals(0, ticket6.compareTo(ticket5));

    }

    @Test

    public void shouldSearchAndSortTicketsByPrice(){


        Ticket[] expected = {ticket5, ticket3, ticket2, ticket4, ticket1};
        Ticket[] actual = aviaSouls.search("London", "Paris");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldCompareByFlightTime(){
        Ticket ticket6 = new Ticket("London", "Paris", 6500, 10_20, 13_20);
        aviaSouls.add(ticket6);

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Assertions.assertEquals(-1, timeComparator.compare(ticket1,ticket2));
        Assertions.assertEquals(1, timeComparator.compare(ticket2,ticket1));
        Assertions.assertEquals(0, timeComparator.compare(ticket6,ticket1));


    }

    @Test

    public void shouldSearchAndSortTicketsByTime(){

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {ticket1, ticket4, ticket2, ticket3, ticket5};
        Ticket[] actual = aviaSouls.searchAndSortBy("London", "Paris",timeComparator);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test

    public void shouldSearchFewTickets(){

        Ticket[] expected = {ticket6, ticket7};
        Ticket[] actual = aviaSouls.search("Milan", "Barcelona");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test

    public void shouldSearchInOneTicket(){

        Ticket[] expected = {ticket8};
        Ticket[] actual = aviaSouls.search("Moscow", "Saint-Petersburg");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test

    public void shouldSearchUnExistedTicket(){

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Berlin", "Prague");

        Assertions.assertArrayEquals(expected, actual);
    }


}
