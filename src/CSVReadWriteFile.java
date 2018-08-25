import java.io.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



//Directory Walker

public class CSVReadWriteFile {
	
	public static int valid = 0;
	public static int invalid = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  

				// This block configure the logger with handler and formatter  
		        try {
		        	
					fh = new FileHandler("//home//student_2018_01_5510//dk_govindaraj//CSVReaderWriter//output//Logs.txt");
					logger.addHandler(fh);
					SimpleFormatter formatter = new SimpleFormatter();  
			        fh.setFormatter(formatter);		
			        
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        
		       
				final long startTime = System.currentTimeMillis();
								
				CSVReadWriteFile.walk("//home//student_2018_01_5510//dk_govindaraj//CSVReaderWriter//JAVA_CSV");
				
				final long endTime = System.currentTimeMillis();
				
				logger.info("Total execution time: " + (endTime - startTime) +" ms");
				logger.info("Total number of valid rows: " + valid);
				logger.info("Total number of invalid rows: " + invalid);
				
				
		}
	
	
	public static void walk( String path ) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("//home//student_2018_01_5510//dk_govindaraj//CSVReaderWriter//output//Report.csv"),true));
        
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
            	
                System.out.println( "File:" + f.getAbsoluteFile() );
                pw = CSVReadWriteFile.SimpleCsvParser(f.getAbsoluteFile().getPath(),pw);
                
                //CSVManipulator.fileReader(f.getAbsoluteFile().getPath());
            }
        }
        
        pw.close();
    }
	
	
	private static PrintWriter SimpleCsvParser(String path, PrintWriter pw) {
		// TODO Auto-generated method stub
		
		boolean headerCheck = false;
		Reader in;
		try {
			in = new FileReader(path); 
			
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				StringBuilder sb = new StringBuilder();
				if (headerCheck)
				{
				String firstName = record.get(0);
                String lastName = record.get(1);
                String streetNumber = record.get(2);
                String street = record.get(3);
                String city = record.get(4);
                String province = record.get(5);
                String country = record.get(6);
                String postalCode = record.get(7);
                String phoneNumber = record.get(8);
                String emailAddress = record.get(9);
                if (!firstName.isEmpty()&&!lastName.isEmpty()&&!streetNumber.isEmpty()&&!street.isEmpty()&&
                                  !city.isEmpty()&&!province.isEmpty()&&!country.isEmpty()&&!postalCode.isEmpty()&&
                                            !phoneNumber.isEmpty()&&!emailAddress.isEmpty())
                {
                            valid++;
                }
                else
                {
                            invalid++;
                }
					sb.append('\n');
					pw.write(sb.toString());
					pw.write(record.toString());
				}  
				headerCheck = true;
							    
			}			
			
		} catch ( IOException e) {
						e.printStackTrace();
		}
		return pw;
		
	}

	
	}



  
