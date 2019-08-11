package br.com.desafio.qa.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.com.desafio.qa.browser.Browser;


public class ArquivoUtil {

	public static void printar() throws IOException{

		DateTime dt = new DateTime();
		String hora = String.valueOf(dt.getHourOfDay());
	    String minuto = String.valueOf(dt.getMinuteOfHour());
	    String segundo = String.valueOf(dt.getSecondOfMinute());
	    String horario = hora + minuto + segundo;
		
		File scrFile = ((TakesScreenshot)Browser.tempDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\temp\\prints\\" + horario + ".png"));
	}

	public static File salvarArquivo(BufferedImage bufferedImage) {
	
		DateTime dt = new DateTime();
		String hora = String.valueOf(dt.getHourOfDay());
	    String minuto = String.valueOf(dt.getMinuteOfHour());
	    String segundo = String.valueOf(dt.getSecondOfMinute());
	    String horario = hora + minuto + segundo;
		
	     File arquivo = new File("C:\\temp\\prints\\" + bufferedImage + horario + ".jpg");

	     try {
		
		  FileUtils.touch(arquivo);
				
	     } catch (IOException e) {
		 	
		   e.printStackTrace();
	     }
	
	  return arquivo;
	}
	
	public static void main(String[] args) throws IOException {
		
		printar();
	}
}
