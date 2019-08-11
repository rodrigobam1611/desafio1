package br.com.desafio.qa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {

	static String caminhoPDF;
	
	public static void gerarPdfExecucao(String desafio) throws DocumentException, MalformedURLException, IOException {
		
		try {

			System.out.println("gerando o pdf...");
			DateTime dt = new DateTime();
			String hora = String.valueOf(dt.getHourOfDay());
		    String minuto = String.valueOf(dt.getMinuteOfHour());
		    String segundo = String.valueOf(dt.getSecondOfMinute());
		    String horario = hora + minuto + segundo;
		    
			Document document = new Document();

			PdfWriter.getInstance(document,
					new FileOutputStream("C:\\temp\\prints"
							+ File.separator + desafio + "_" + LocalDate.now().toString("ddMMyyyy") 
							+ horario + ".pdf"));

			document.open();
			
			String extensao[] = {"png"};
			
			for (File print : IteratorUtils
					.toList(FileUtils.iterateFiles(new File("C:\\temp\\prints"), extensao, false))) {
				Image img = Image.getInstance(print.getAbsolutePath());
				img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				document.add(img);
			}
			
			document.close();
			
			 File f = new File("C:\\temp\\prints"); // use here your file directory path
		        String[] allFiles = f.list();
		        
		        for (String filez:allFiles ) {
		            
		        	if(filez.endsWith(".pdf")) {
		        		
		        		FileUtils.moveFileToDirectory(new File("C:\\temp\\prints\\" + filez), new File("C:\\temp"),
		        									  false);
		        		System.out.println("movido " + filez);
		        	}
		        }
			
		    FileUtils.deleteDirectory(new File("C:\\temp\\prints"));    
			System.out.println("Relatório em PDF gerado com sucesso!");
			
		} catch (FileNotFoundException e) {

		} 
	}
	
	public static void main(String[] args) throws MalformedURLException, DocumentException, IOException {
		
		gerarPdfExecucao("Desafio 1");
	}
	
}
