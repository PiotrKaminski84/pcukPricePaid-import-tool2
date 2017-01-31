package pl.pmk.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import pl.pmk.access.DbConnector;

public class FileServiceImpl implements FileService{

	private BufferedReader reader;
	private SaleRecordService saleService;
	
	@Override
	public void openFile(File f) {
		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void saveAllRecordsFromFile(DbConnector db) {
		
	
			
		
		
	}

}
