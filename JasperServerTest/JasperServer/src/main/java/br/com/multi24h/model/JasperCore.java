package br.com.multi24h.model;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperCore {

	public void generate(String templatePath, String jsonContent, String outputPath) {
		// Generate the report using the JSON data file
		try {
			InputStream inputStream = new FileInputStream(templatePath);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			// Load report
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			// Load parameters if exist
			HashMap parameters = new HashMap();
			// Convert json string to byte array.
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(jsonContent.getBytes());
			// Create json datasource from json stream
			JsonDataSource ds = new JsonDataSource(jsonDataStream);
			// Load
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
			// Write
			JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
