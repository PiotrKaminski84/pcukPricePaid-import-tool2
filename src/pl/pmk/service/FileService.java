package pl.pmk.service;

import java.io.File;

import pl.pmk.access.DbConnector;

public interface FileService {
	public void openFile(File f);
	public void saveAllRecordsFromFile(DbConnector db);
}
