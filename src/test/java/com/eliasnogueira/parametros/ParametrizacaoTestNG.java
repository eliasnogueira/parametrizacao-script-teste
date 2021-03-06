package com.eliasnogueira.parametros;


import com.eliasnogueira.parametros.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class ParametrizacaoTestNG {


    private WebDriver driver;

    @BeforeMethod
    public void preCondicao() {
        // o driver do Google Chrome está vindo do arquivo de propriedades
        System.setProperty("webdriver.chrome.driver", Utils.lerPropriedade("chromedriver.path"));

        driver = new ChromeDriver();

        // a URL incial para os testes está vindo do arquivo de propriedades
        driver.get(Utils.lerPropriedade("url.inicial") + "/arquivos_blog/post_parametros/");
    }

    @AfterMethod
    public void posCondicao() {
        driver.quit();
    }

    @Test (dataProvider = "massaDeDados")
    public void testeParametrizacaoTestNG(String nome, String cidade, String faixaIdade, String resultado) {
        driver.findElement(By.id("nome")).sendKeys(nome);
        driver.findElement(By.id("cidade")).sendKeys(cidade);
        new Select(driver.findElement(By.name("faixa"))).selectByVisibleText(faixaIdade);
        driver.findElement(By.cssSelector(".btn.btn-primary.nextBtn.btn-lg.pull-right")).click();

        assertEquals(driver.findElement(By.cssSelector("span[ng-bind='nome']")).getText(), nome);
        assertEquals(driver.findElement(By.cssSelector("span[ng-bind='cidade']")).getText(), cidade);
        assertEquals(driver.findElement(By.cssSelector("span[ng-bind='faixaSelecionada']")).getText(), faixaIdade);
        assertEquals(driver.findElement(By.cssSelector("span[ng-bind='retorno']")).getText(), resultado);
    }

    @DataProvider(name = "massaDeDados")
    public Object[][] criacaoMassaDados() {
        return new Object[][] {
                {"Jose", "São Paulo", "Até 18 anos", "Os juros serão de 60%"},
                {"Maria", "Belo Horizonte", "Entre 19 e 25 anos", "Os juros serão de 40%"},
                {"João", "Florianópolis", "Entre 26 e 60 anos", "Os juros serão de 30%"},
                {"Carla", "Vitória", "Maior que 60 anos", "Os juros serão de 20%"}
        };
    }
}
