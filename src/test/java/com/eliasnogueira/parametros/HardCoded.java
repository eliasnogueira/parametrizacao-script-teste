package com.eliasnogueira.parametros;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/*
 * Note que todos os dados estão inseridos no script
 */
public class HardCoded {

	@Test
	public void testeHardCoded() {
	    /*
	     * Não esqueça de mudar o caminho para o seu arquivo chromedriver
	     * Se você usa uma máquina Windows, deve adicionar a extensão do arquivo (chromedriver.exe)
	     */
		System.setProperty("webdriver.chrome.driver", "/Users/eliasnogueira/Selenium/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://eliasnogueira.com/arquivos_blog/post_parametros/");
		
		driver.findElement(By.id("nome")).sendKeys("Elias");
		driver.findElement(By.id("cidade")).sendKeys("Porto Alegre");
		new Select(driver.findElement(By.name("faixa"))).selectByVisibleText("Entre 26 e 60 anos");
		driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();
		
		assertEquals("Elias", driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText());
		assertEquals("Porto Alegre", driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText());
		assertEquals("Entre 26 e 60 anos", driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText());
		assertEquals("Os juros serão de 30%", driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText());
	
		driver.quit();
	}

}
