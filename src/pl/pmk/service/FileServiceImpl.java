package pl.pmk.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

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
		
		db.startBatchQuerry();
		String buffer;
		saleService = new SaleRecordServiceImpl();
		try {
			buffer = reader.readLine();
			while (buffer!=null){				
				db.addRecordToBatchStatment(saleService.extractSaleTransaction(buffer),saleService.extractPropertyDetails(buffer));
				buffer = reader.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.endBatchQuerry();
		
	}

}
