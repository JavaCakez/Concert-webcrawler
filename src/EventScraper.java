import com.jaunt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for scraping event information from the WeGotTickets UK homepage.
 */
public class EventScraper {

    private static final String SCRAPE_URL = "http://www.wegottickets.com/searchresults/all";

    public List<Event> scrapeEvents() throws JauntException {
        List<Event> events = new ArrayList<>();

        UserAgent userAgent = new UserAgent();
        userAgent.settings.autoSaveAsHTML = true;
        userAgent.visit(SCRAPE_URL);

        Elements pages = userAgent.doc.findFirst("<div class=paginator>").findEvery("<a>");
        int numberOfPages = 0;
        for (Element page : pages) {
            try {
                numberOfPages = Integer.parseInt(page.getText());
            } catch (NumberFormatException e) {
            }
        }

        for (int pageNumber = 1; pageNumber < numberOfPages + 1; pageNumber++) {
            String pageUrl = "http://www.wegottickets.com/searchresults/page/" + pageNumber + "/all";
            userAgent.visit(pageUrl);

            Elements eventListings = userAgent.doc.findEvery("<div class=ListingOuter>");

            for (Element eventListing : eventListings) {
                events.add(scrapeEvent(eventListing));
            }
        }

        return events;
    }

    private Event scrapeEvent(Element eventListing) throws JauntException {
        String artist = scrapeArtistName(eventListing);
        String city = scrapeCityName(eventListing);
        String venue = scrapeVenueName(eventListing);
        String price = scrapePrice(eventListing);
        String date = scrapeDate(eventListing);

        return new Event(artist, city, venue, date, price);
    }

    private String scrapeArtistName(Element eventListing) throws JauntException {
        return eventListing.findFirst("<h3>").findFirst("<a>").getText();
    }

    private String scrapeCityName(Element eventListing) throws JauntException {
        return eventListing.findFirst("<span class=venuetown>").getText();
    }

    private String scrapeVenueName(Element eventListing) throws JauntException {
        return eventListing.findFirst("<span class=venuename>").getText();
    }

    private String scrapePrice(Element eventListing) throws JauntException {
        String price;
        try {
            price = eventListing.findFirst("<div class=searchResultsPrice>").findFirst("<strong>").getText();
        } catch (JauntException e) {
            price = "Information unavailable";
        }
        price.replaceAll("&pound;(?!\\-)", "£");

        return price;
    }

    private String scrapeDate(Element eventListing) throws JauntException {
        return eventListing.findFirst("<p>").getText();
    }
}
