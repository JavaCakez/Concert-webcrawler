import com.jaunt.JauntException;

import java.util.List;

public class SongkickTestWrapper {

    private static final String CSV_FILE_LOCATION = "C:\\Users\\Tom\\Desktop\\csv.csv";

    /* Note to Songkick:

        I didn't have time to do all I would have liked to do, but some thoughts about what I would've done with more time:

        * A big problem I didn't have time to address was that as I'm outputting CSV, I need to remove commas from anything scraped
            as this ruins the CSV
           
        * Since this whole application is fully reliant on parsing the DOM of WeGotTickets, it could all fall over at any moment.
          Ideally there would be far more robustness worked in.

        * I didn't have the time to get Java string replace working for replacing &pound; with £.

        * There could be many improvements made for the scraping.
            E.g. all dates could be normalised and formatted in a consistent way.

        * Working through the pagination was the last thing I did so again isn't done in the most robust way.
            (having the try catch to get over the fact that there is a 'next' page isn't the best)

        * I'm now even running out of time for writing these so I'll have to leave it here!


        I have included a CSV I generated of the first 10 pages in my submission (all of them would take far too long!)
    */


    public static void main(String[] args) throws JauntException {

        EventScraper eventScraper = new EventScraper();
        List<Event> events = eventScraper.scrapeEvents();

        CsvGenerator csvGenerator = new CsvGenerator();
        csvGenerator.generateCsvFile(CSV_FILE_LOCATION, events);
    }
}
