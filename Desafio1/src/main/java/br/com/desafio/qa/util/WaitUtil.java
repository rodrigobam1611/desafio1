package br.com.desafio.qa.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import br.com.desafio.qa.browser.Browser;

public class WaitUtil {

	private static Wait<WebDriver> wait;
	
	public static void instanciaFluentWait(Integer tempoEspera, Integer intervaloTentativas){
		wait = new FluentWait<WebDriver>(Browser.tempDriver)  
                .withTimeout(tempoEspera, TimeUnit.SECONDS)
                .pollingEvery(intervaloTentativas, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);		
	}
	
	public static void waitDefaultClick(WebElement webElement, Integer tempoEspera, Integer intervaloTentativas) {
		
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	/**
	 * Espera até que a página tenha carregado o elemento.
	 * @param webElement
	 * @param tempoEspera
	 * @param intervaloTentativas
	 */
	public static void waitPageLoads(WebElement webElement, Integer tempoEspera, Integer intervaloTentativas) {
		
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	/**
	 * Espera até que a página tenha carregado o elemento.
	 * @param webElements
	 * @param tempoEspera
	 * @param intervaloTentativas
	 */
	public static void waitPageLoads(List<WebElement> webElements, Integer tempoEspera, Integer intervaloTentativas) {
		
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		
		for (int i = 0; i < webElements.size(); i++) {
			
			wait.until(ExpectedConditions.elementToBeClickable(webElements.get(i)));
		}
	}
	
	public static void findElementByClick(WebElement webElement, Integer tempoEspera, Integer intervaloTentativas) {
		
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public static WebElement waitLinkTextPresenceOfElementLocated(String valorBuscado, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(valorBuscado)));
	}

	public static WebElement waitTextPresenceOfElementLocated(String valorBuscado, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(valorBuscado)));
	}

	public static void waitTextoPresenceOfElementLocated(String valorBuscado, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), valorBuscado));
	}

	public static void waitIdOfElementLocated(String valorBuscado, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(valorBuscado)));
	}

	public static void waitFrameToBeAvailableAndSwitchToIt(WebElement webElement, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
	}
	
	public static void waitElementSelectionStateToBe(WebElement webElement,Boolean isSelecionada, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.elementSelectionStateToBe(webElement, isSelecionada));
	}
	
	/**
	 * Espera até que um texto esteja presente no elemento.
	 * @param webElement
	 * @param texto
	 * @param tempoEspera
	 * @param intervaloTentativas
	 */
	
	public static void waitTextOnElement(WebElement webElement, String texto, Integer tempoEspera, Integer intervaloTentativas) {
		instanciaFluentWait(tempoEspera, intervaloTentativas);
		wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, texto));
	}
	
}
