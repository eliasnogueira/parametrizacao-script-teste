package com.eliasnogueira.parametros;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.eliasnogueira.parametros.utils.Utils;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CSVJUnit {

	@Test
	@FileParameters("src/test/resources/dados.csv")
	public void testeCSVJUnit(String nome, String cidade, String faixa, String resultado) {
		WebDriver driver = new FirefoxDriver();
		driver.get(Utils.lerPropriedade("url.inicial"));
		
		driver.findElement(By.id("nome")).sendKeys(nome);
		driver.findElement(By.id("cidade")).sendKeys(cidade);
		new Select(driver.findElement(By.name("faixa"))).selectByVisibleText(faixa);
		driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();
		
		assertEquals(nome, driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText());
		assertEquals(cidade, driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText());
		assertEquals(faixa, driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText());
		assertEquals(resultado, driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText());
	
		driver.quit();
	}

}
