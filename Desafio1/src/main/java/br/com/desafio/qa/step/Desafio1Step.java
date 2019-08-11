package br.com.desafio.qa.step;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.PageFactory;

import br.com.desafio.qa.browser.Browser;
import br.com.desafio.qa.page.object.model.UnimedPageObject;
import br.com.desafio.qa.service.UnimedService;
import br.com.desafio.qa.util.ArquivoUtil;
import br.com.desafio.qa.util.PDFUtil;
import br.com.desafio.qa.util.WaitUtil;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class Desafio1Step {
	
	private UnimedPageObject unimedPage;
	private UnimedService unimedService;
	
	/**
	 * Acessa o site <b>https://www.unimed.coop.br/<b/>
	 * @param url
	 * @throws Throwable
	 */
	@Dado("^que o usuário acesse o site \"([^\"]*)\"$")
	public void acessarSite(String url) throws Throwable {
	    
		FileUtils.deleteDirectory(new File("C:\\temp\\prints"));
		Browser.abrirNavegador(url);
		unimedPage = new UnimedPageObject(); 
		unimedService = new UnimedService(unimedPage);
		PageFactory.initElements(Browser.tempDriver, unimedPage);
		ArquivoUtil.printar();
	}

	/**
	 * Acessa o Guia Médico.
	 * @param guiaMedico
	 * @throws Throwable
	 */
	@Quando("^acessar o Guia Médico$")
	public void acessarGuiaMedico() throws Throwable {
	    
		unimedService.acessarGuiaMedico();
	}

	/**
	 * Realiza a pesquisa de Médicos no Rio de Janeiro com validação.
	 * @param estado
	 * @throws Throwable
	 */
	@E("^realizar uma pesquisa de médicos no \"([^\"]*)\"$")
	public void pesquisarMedicos(String estado) throws Throwable {
	    
		unimedService.pesquisarMedicos(estado);
	}
	
	/**
	 * Validação da apresentação dos resultados com a Especialidade e Cidade.
	 * @param tabela
	 * @throws Throwable
	 */
	@Então("^será validado a apresentação do resultados com a Especialidade e Cidade$")
	public void validarResultadosEspecialidadeCidade(DataTable tabela) throws Throwable {
	    
		WaitUtil.waitPageLoads(unimedPage.resultadosEncontrados, 30, 2);
		ArquivoUtil.printar();
		
		List<UnimedPageObject> page = tabela.asList(UnimedPageObject.class);
		
		for (int i = 0; i < unimedPage.especialidades.size(); i++) {
		
			assertTrue(unimedPage.especialidades.get(i).getText().contains(page.get(0).especialidade));
			assertTrue(unimedPage.enderecos.get(i).getText().contains(page.get(0).cidade));
		}
		
		PDFUtil.gerarPdfExecucao("Teste 1");
	}
	
	/**
	 * Validação do acesso ao Guia Médico.
	 * @param guiaMedico
	 * @throws Throwable
	 */
	@Quando("^acessar o \"([^\"]*)\", o acesso será validado$")
	public void validarAcessoGuiaMedico(String guiaMedico) throws Throwable {
		
		unimedService.acessarGuiaMedico();
		
		assertTrue(Browser.tempDriver.getCurrentUrl().contains("guia-medico"));
		assertTrue(unimedPage.breadcumbGuiaMedico.getText().equals(guiaMedico));
		
		unimedService.scrollToElement(unimedPage.breadcumbGuiaMedico);
		ArquivoUtil.printar();
	}

	@Então("^será validado a não apresentação dos resultados com cidade de São Paulo nas três primeiras páginas$")
	public void validarResultados(DataTable tabela) throws Throwable {
	    
		WaitUtil.waitPageLoads(unimedPage.resultadosEncontrados, 30, 2);
		
		List<UnimedPageObject> page = tabela.asList(UnimedPageObject.class);
		
		for (int i = 3; i <= 4; i++) {
			
			for (int j = 0; j < unimedPage.especialidades.size(); j++) {

				assertTrue(unimedPage.especialidades.get(j).getText().contains(page.get(0).especialidade));
				assertFalse(unimedPage.enderecos.get(j).getText().contains(page.get(0).cidade));
			} 
			
			unimedService.scrollToElement(unimedPage.paginacao.get(i));
			ArquivoUtil.printar();
			
			unimedPage.paginacao.get(i).click();
			WaitUtil.waitPageLoads(unimedPage.resultadosEncontrados, 30, 2);
		}
		
		PDFUtil.gerarPdfExecucao("Teste 2");
		Browser.tempDriver.quit();
	    
	}
	

}
