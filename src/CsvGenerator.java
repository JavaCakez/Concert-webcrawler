import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class responsible for generating a CSV of scraped event data.
 */
public class CsvGenerator {

    public void generateCsvFile(String sFileName, List<Event> events)
    {
        try
        {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("Name");
            writer.append(',');
            writer.append("City");
            writer.append(',');
            writer.append("Venue");
            writer.append(',');
            writer.append("Price");
            writer.append(',');
            writer.append("Date");
            writer.append('\n');

            for (Event event : events) {
                writer.append(event.getArtist());
                writer.append(',');
                writer.append(event.getCity());
                writer.append(',');
                writer.append(event.getVenue());
                writer.append(',');
                writer.append(event.getPrice());
                writer.append(',');
                writer.append(event.getDate());
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
