/**
 * Object representing an Event.
 */
public class Event {

    private String artist;
    private String city;
    private String venue;
    private String date;
    private String price;

    public Event(String artist, String city, String venue, String date, String price) {
        this.artist = artist;
        this.city = city;
        this.venue = venue;
        this.date = date;
        this.price = price;
    }

    public String getArtist() {
        return artist;
    }

    public String getCity() {
        return city;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
}
