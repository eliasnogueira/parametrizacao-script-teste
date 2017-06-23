package com.eliasnogueira.parametros;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.eliasnogueira.parametros.utils.Utils;

public class ParametrizacaoProperties {

	@Test
	public void testeProprerties() {

	    // o driver do Google Chrome está vindo do arquivo de propriedades
		System.setProperty("webdriver.chrome.driver", Utils.lerPropriedade("chromedriver.path"));

		WebDriver driver = new ChromeDriver();

		// a URL incial para os testes está vindo do arquivo de propriedades
		driver.get(Utils.lerPropriedade("url.inicial") + "/arquivos_blog/post_parametros/");

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
