package com.eliasnogueira.parametros;

import com.eliasnogueira.parametros.utils.Utils;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

/*
 * A execução pelo JUnit deve ser através do JUnitParamsRunner, para que ele possa ler o arquivo CSV e associar
 * futuramente os dados do arquivo com os parâmetros dentro do método de teste.
 */
@RunWith(JUnitParamsRunner.class)
public class ParametrizacaoJUnitCSV {

    private WebDriver driver;

    @Before
    public void preCondicao() {
        // o driver do Google Chrome está vindo do arquivo de propriedades
        System.setProperty("webdriver.chrome.driver", Utils.lerPropriedade("chromedriver.path"));

        driver = new ChromeDriver();

        // a URL incial para os testes está vindo do arquivo de propriedades
        driver.get(Utils.lerPropriedade("url.inicial") + "/arquivos_blog/post_parametros/");
    }

    @After
    public void posCondicao() {
        driver.quit();
    }

    /*
     * Devemos fazer duas ações:
     *  1. Inserir a anotação @FileParameters indicando o local onde está o arquivo .csv
     *  2. Para cada "coluna" do arquivo CSV, criar uma variável como pariametro no teste onde a usaremos no código
     *
     *  Observação: o arquivo .csv deve ter os dados separados por vírgula
     */
    @Test
    @FileParameters("massa_dados/dados.csv")
    public void testeParametrizacaoJUnit(String nome, String cidade, String faixaIdade, String resultado) {
        driver.findElement(By.id("nome")).sendKeys(nome);
        driver.findElement(By.id("cidade")).sendKeys(cidade);
        new Select(driver.findElement(By.name("faixa"))).selectByVisibleText(faixaIdade);
        driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();

        assertEquals(nome, driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText());
        assertEquals(cidade, driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText());
        assertEquals(faixaIdade, driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText());
        assertEquals(resultado, driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText());
    }

}