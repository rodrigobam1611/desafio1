package br.com.desafio.qa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import br.com.desafio.qa.browser.Browser;
import br.com.desafio.qa.page.object.model.UnimedPageObject;
import br.com.desafio.qa.util.ArquivoUtil;
import br.com.desafio.qa.util.WaitUtil;

public class UnimedService {

	private UnimedPageObject unimedPage;
	
	public UnimedService(UnimedPageObject unimedPage) {
	   
		this.unimedPage = unimedPage;
	}
	
	public void acessarGuiaMedico() throws IOException {
		
		WaitUtil.waitPageLoads(unimedPage.linkGuiaMedico, 15, 2);
		ArquivoUtil.printar();
		unimedPage.linkGuiaMedico.click();
		
		WaitUtil.waitPageLoads(unimedPage.breadcumbGuiaMedico, 30, 2);
		ArquivoUtil.printar();
	}
	
	/**
	 * Pesquisa de médicos no Rio de Janeiro com a validação do Estado.
	 * @param estado
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void pesquisarMedicos(String estado) throws InterruptedException, IOException {
		
	   WaitUtil.waitPageLoads(unimedPage.buscaDetalhada, 30, 2);
	   unimedPage.buscaDetalhada.click();
	   
	   WaitUtil.waitPageLoads(unimedPage.campoEstado, 15, 2);
	   WaitUtil.waitPageLoads(unimedPage.campoCidade, 15, 2);
	   
	   scrollToElement(unimedPage.campoEstado);
	  
	   unimedPage.campoEstado.sendKeys(estado);
	   WaitUtil.waitTextOnElement(unimedPage.campoEstado, estado, 10, 1);
	   unimedPage.campoEstado.sendKeys(Keys.ENTER);
	   
	   unimedPage.campoCidade.sendKeys(estado);
	   WaitUtil.waitTextOnElement(unimedPage.campoCidade, estado, 10, 1);
	   unimedPage.campoCidade.sendKeys(Keys.ENTER);
	 
	   WaitUtil.waitDefaultClick(unimedPage.radioBtnUnimedRio, 10, 5);
	   
	   ArquivoUtil.printar();
	   
	   assertEquals(estado, unimedPage.valorCampoEstado.getText());
	   assertEquals(estado, unimedPage.valorCampoCidade.getText());
	  
	   unimedPage.radioBtnUnimedRio.click();
	   assertTrue(unimedPage.radioBtnUnimedRio.isSelected());
	
	   unimedPage.btnContinuar.click();
	   
	   Thread.sleep(2000);
	   
	   WaitUtil.waitPageLoads(unimedPage.campoEspecialidade, 15, 2);
	   unimedPage.campoEspecialidade.sendKeys("Clínica Médica");
	   Thread.sleep(2000);
	   unimedPage.campoEspecialidade.sendKeys(Keys.ENTER);
	   
	   ArquivoUtil.printar();
	   
	   unimedPage.btnPesquisar.click();
	   Thread.sleep(2000);
	   
	   WaitUtil.waitPageLoads(unimedPage.especialidades, 30, 2);
	   ArquivoUtil.printar();
	}
	
	public static void scrollToElement(WebElement element) {
		
		 ((JavascriptExecutor) Browser.tempDriver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
