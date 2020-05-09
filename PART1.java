
/**
 * Write a description of PART1 here.
 * 
 * @author (PANCHANG BHARDWAJ) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class PART1 {
// export find
public void find(CSVParser parser , String tofind)
    { System.out.println("given string is found at = ");
        for(CSVRecord pa : parser)
        {
            String str = pa.get("Exports");
            if(str.contains(tofind))
            {
                System.out.println(pa.get("Country"));
        }
    }
}   
// country info
 public void countryinfo(CSVParser parser,String country)
{   System.out.println("COUNTRY INFO");
    String stri = "no value";
    for(CSVRecord record : parser)
    {
        String str = record.get("Country");
       
        if(record.get("Country").equals(country))
        {
            
            //return (country + ":"  + record.get("Exports") + ": " + record.get("Value (dollars)") );
            
            stri  = country + ":"  + record.get("Exports") + ": " + record.get("Value (dollars)") ;
            System.out.println(stri);
            
        }
    }
    
}

//hav both exports countrys
  public void listexporterstwoproducts(CSVParser parser,String export1,String export2)
{ 
       int num =0;
    System.out.println("THE EXPORTERS THAT EXPORT BOTHPRODUCT " + export1 + export2 + "ARE :");   
    for(CSVRecord record : parser)
    {
        String str = record.get("Exports");
      
     if(str.contains(export1) && str.contains(export2)) 
     {
         System.out.println(record.get("Country"));
         }
  }
    
}

// print county that have exoirts amount hhighrt then the given amount
 public void bigexporter(CSVParser parser,String amount)
{
   System.out.println("county with value larger the given value");
    for(CSVRecord record : parser)
    {
        String str = record.get("Value (dollars)");
      
     if(str.length() > amount.length()) 
     {
         System.out.print(record.get("Country") + " ");
         System.out.println(record.get("Value (dollars)"));
        }
  }
}
// num of country export a item
;
  public int numberofexporters(CSVParser parser,String export)
{   int num =0;
    for(CSVRecord record : parser)
    {
        String str = record.get("Exports");
      
     if(str.contains(export)) 
     {
         num++;
         }
  }
  return num;
}
// test
  public void tester()
{
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
     
    parser = fr.getCSVParser();
    countryinfo(parser,"Nauru");
    
    parser = fr.getCSVParser();
    System.out.println("NO of contry exporting cocoa  are " + numberofexporters(parser,"cocoa"));
    parser = fr.getCSVParser();
    bigexporter(parser,"$999,999,999,999");
    parser = fr.getCSVParser();
    listexporterstwoproducts(parser,"cotton","flowers");
    parser = fr.getCSVParser();
    find(parser,"gold");
}

}
