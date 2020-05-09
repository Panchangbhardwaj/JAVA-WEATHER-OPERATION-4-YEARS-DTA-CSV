
/**
 * Write a description of PART2 here.
 * 
 * @author (PANCHANG BHARDWAJ) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class PART2 {

    public CSVRecord hottesthour(CSVParser parser)
    {
        CSVRecord largestsofar = null;
        for(CSVRecord record : parser)
        {
            largestsofar = getlargest(record,largestsofar);
        }
     return largestsofar;
      
}

public CSVRecord hottestmanydays()
{
    CSVRecord largestsofar = null;
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles())
    {
        FileResource fr = new FileResource(f);
        CSVRecord current = hottesthour(fr.getCSVParser());
        largestsofar = getlargest(current,largestsofar);
    }
    return largestsofar;
}

public CSVRecord getlargest(CSVRecord current,CSVRecord  largestsofar)
{
 if(largestsofar == null)
        {   largestsofar = current;
        }
        else
        {
        
              double currenttemp = Double.parseDouble(current.get("TemperatureF"));
              double largesttemp = Double.parseDouble(largestsofar.get("TemperatureF"));
              if(currenttemp > largesttemp)
              {
                 largestsofar = current;
                }
            }   
            return largestsofar;
}
public void hottest()
{
    //FileResource fr = new FileResource();
    CSVRecord largest = hottestmanydays();
    System.out.println("hottest temperature is " + largest.get("TemperatureF") + " AT " + largest.get("DateUTC"));
    
}
}