
/**

 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.lang.Float;
public class coldest {

    public CSVRecord coldesthour(CSVParser parser)
    {
        CSVRecord leastsofar = null;
        for(CSVRecord record : parser)
        {
            leastsofar = getleast(record,leastsofar);
        }
     return leastsofar;
      
}
public double getaveragetemp(CSVParser parser)
{   double totaltemp = 0;
    double numberoftemp = 0;
    for(CSVRecord record: parser)
    {
     numberoftemp ++;
     totaltemp = totaltemp + Double.parseDouble(record.get("TemperatureF")); 
    }
    return totaltemp/numberoftemp;
}
public void testaveragetemp()
{
 FileResource fr = new FileResource();
 CSVParser parser = fr.getCSVParser();
 System.out.println("THE AVERAGE TEMPERATURE FROM SELECTED FILE IS = " + getaveragetemp(parser));
}
    public CSVRecord lowesthumidityin(CSVParser parser)
    {
        CSVRecord leastsofar = null;
        for(CSVRecord record : parser)
        {
            leastsofar = getleasthumidity(record,leastsofar);
        }
     return leastsofar;
      
}

public CSVRecord lowesthumidity()
{
    CSVRecord leastsofar = null;
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles())
    {
        FileResource fr = new FileResource(f);
        CSVRecord current = lowesthumidityin(fr.getCSVParser());
        leastsofar = getleasthumidity(current,leastsofar);
    }
    return leastsofar;
}
public CSVRecord coldestmanydays()
{
    CSVRecord leastsofar = null;
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles())
    {
        FileResource fr = new FileResource(f);
        CSVRecord current = coldesthour(fr.getCSVParser());
        leastsofar = getleast(current,leastsofar);
    }
    return leastsofar;
}

public CSVRecord getleasthumidity(CSVRecord current,CSVRecord  leastsofar)
{
    
 if(leastsofar == null)
        {   leastsofar = current;
        }
        else
        {
              if(current.get("Humidity").equals("N/A"))
              { 
                  System.out.println("na");
                }
              else{
                    
              double currenttemp = Double.parseDouble(current.get("Humidity"));
              double leasttemp = Double.parseDouble(leastsofar.get("Humidity"));
              
               if(currenttemp < leasttemp)
              {
                 leastsofar = current;
                }}
            }   
            return leastsofar;
}
public CSVRecord getleast(CSVRecord current,CSVRecord  leastsofar)
{
    
 if(leastsofar == null)
        {   leastsofar = current;
        }
        else
        {
        
              double currenttemp = Double.parseDouble(current.get("TemperatureF"));
              double leasttemp = Double.parseDouble(leastsofar.get("TemperatureF"));
              if (currenttemp == -9999)
              {
               }
              else if(currenttemp < leasttemp)
              {
                 leastsofar = current;
                }
            }   
            return leastsofar;
}

public double averagetemperatureeithhighhumidity(CSVParser parser,int value)
{
    double total = 0;
    int num = 0;
    double average = 0;
    for(CSVRecord record:parser)
    {
        if((Double.parseDouble(record.get("Humidity"))) > value)
        {
            num++;
            System.out.println(record.get("Humidity")+"\n"+record.get("DateUTC"));
            total = total + Double.parseDouble(record.get("TemperatureF"));
        }
    }
    average = total/num;
    return average;
}

public void testatwhh()
{
 FileResource fr = new FileResource();
 CSVParser parser = fr.getCSVParser();
 int value = 80;
 double result = averagetemperatureeithhighhumidity(parser,value);
 if(result == 0)
 {
     System.out.println("NO teperature with that humidity ");
    }
    else
    {
        System.out.println("AVERAGE TEMPERATURE WITH GIVEN HUMIDITY IS = " + result);
    }
}

public void coldest()
{
    //FileResource fr = new FileResource();
    CSVRecord least = coldestmanydays();
    System.out.println( "least temperature is " + least.get("TemperatureF") + " AT " + least.get("DateUTC"));
    System.out.println("All the temperature on coldest dat are as :");
    testaveragetemp();
    CSVRecord lowh = lowesthumidity();
    System.out.println("least humidity is = " + lowh.get("Humidity") + " at "+ lowh.get("DateUTC"));
}
public void testhumidity()
{
    CSVRecord lowh = lowesthumidity();
    System.out.println("least humidity is = " + lowh.get("Humidity") + " at "+ lowh.get("DateUTC"));
}
}


