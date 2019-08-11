package br.com.desafio.qa.page.object.model;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UnimedPageObject {

   @FindBy(how = How.LINK_TEXT, using = "Guia Médico")
   public WebElement linkGuiaMedico;
   
   @FindBy(how = How.ID, using = "campo_pesquisa")
   public WebElement campoPesquisa;
   
   @FindBy(how = How.ID, using = "busca_detalhada")
   public WebElement buscaDetalhada;
   
   @FindBy(how = How.ID, using = "btn_pesquisar")
   public WebElement btnPesquisar;
   
   @FindBy(how = How.ID, using = "react-select-2-input")
   public WebElement campoEstado;
   
   @FindBy(how = How.ID, using = "campo_estado")
   public WebElement campoEstadoBuscaDetalhada;
   
   @FindBy(how = How.CSS, using = "div.s-field.control-group.selecione-rede.big-field.pesquisa-avancada > div > div > div.css-k71zgk > div.css-dvua67-singleValue")
   public WebElement valorCampoEstado;
   
   @FindBy(how = How.CSS, using = "div.s-field.control-group.selecione-plano.big-field.pesquisa-avancada > div > div > div.css-k71zgk > div.css-dvua67-singleValue")
   public WebElement valorCampoCidade;
   
   @FindBy(how = How.ID, using = "react-select-3-input")
   public WebElement campoCidade;
   
   @FindBy(how = How.CSS, using = "input[type=radio]")
   public WebElement radioBtnUnimedRio;
   
   @FindBy(how = How.CSS, using = "button.btn.btn-success")
   public WebElement btnContinuar;
   
   @FindBy(how = How.ID, using = "react-select-8-input")
   public WebElement campoEspecialidade;
   
   @FindBy(how = How.XPATH, using = "//div[@class='DadosPrestador']/div/div/div[@class='resultado-resumido padding relative']")
   public List<WebElement> resultadosPesquisa;
   
   @FindBy(how = How.XPATH, using = "//div/div[@class='resultado-resumido padding relative']")
   public List<WebElement> especialidades;
   
   @FindBy(how = How.ID, using = "txt_endereco")
   public List<WebElement> enderecos;
 
   @FindBy(how = How.ID, using = "txt_resultado_encontrado")
   public WebElement resultadosEncontrados;
   
   @FindBy(how = How.XPATH, using = "//div[@class='container-interno relative']/ol[@class='breadcrumb']/li[@class='pagina-atual aria-current=']")
   public WebElement breadcumbGuiaMedico;
   
   @FindBy(how = How.ID, using = "conteudo-mzzp_")
   public WebElement tituloGuiaMedico;
   
   @FindBy(how = How.XPATH, using = "//div[@class='span12 pagination text-center no-margin-left']/div/ul/li")
   public List<WebElement> paginacao;
   
   public String especialidade;
   public String cidade;
   
}
