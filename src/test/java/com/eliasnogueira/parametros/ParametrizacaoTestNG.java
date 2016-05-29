package com.eliasnogueira.parametros;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParametrizacaoTestNG {

	private WebDriver driver;
	
	@BeforeMethod
	public void preCondicao() {
		driver = new FirefoxDriver();
		driver.get("http://eliasnogueira.com/arquivos_blog/post_parametros/");
	}
	
	@AfterMethod
	public void posCondicao() {
		driver.close();
	}
		
	
	@Test(dataProvider = "dados")
	public void testeParametrizacaoJUnit(String nome, String cidade, String faixa, String resultado) {
		driver.findElement(By.id("nome")).sendKeys(nome);
		driver.findElement(By.id("cidade")).sendKeys(cidade);
		new Select(driver.findElement(By.name("faixa"))).selectByVisibleText(faixa);
		driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();
		
		assertEquals(driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText(), nome);
		assertEquals(driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText(), cidade);
		assertEquals(driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText(), faixa);
		assertEquals(driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText(), resultado);
	}
	
	@DataProvider
	public Object[][] dados() {
		return new Object[][] {
			{"Jose", "São Paulo", "Até 18 anos", "Os juros serão de 60%"},
			{"Maria", "Belo Horizonte", "Entre 19 e 25 anos", "Os juros serão de 40%"},
			{"João", "Florianópolis", "Entre 26 e 60 anos", "Os juros serão de 30%"},
			{"Carla", "Vitória", "Maior que 60 anos", "Os juros serão de 20%"}
		};
	}
}
