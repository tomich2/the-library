package cz.fi.muni.pa165.sampleData;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.io.IOException;
import java.text.ParseException;

/**
 * @ author mcada
 */
public interface SampleDataLoader {
	/**
	 * Loads sample data to the database.
	 *
	 * @throws IOException
	 */
	void loadData() throws IOException, ParseException, DataAccessException;
}
