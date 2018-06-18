package es.codeurjc.ais.tictactoe;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

public class WebAppTest {

	public WebDriver driverUno;
	public WebDriver driverDos;
	public String baseUrl;
	
	@BeforeClass
	public static void setupClass() {
		//System.setProperty("webdriver.chrome.driver","/Grado/2C/AIS/Prac1/chromedriver");
		ChromeDriverManager.getInstance().setup();
		WebApp.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	
	@Before
	public void setupTest() {
		driverUno = new ChromeDriver();
		driverDos = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
	}

	@After
	public void teardown() {
		if (driverUno != null){
			driverUno.quit();
		}
		if (driverDos != null) {
			driverDos.quit();
		}
	}
	
	@Test
	public void ganaPrimerJugador() throws InterruptedException {
		
		// O | O | X
		//   | X | O
		// X |   | X
		
		String mensaje = "Miguel wins! Pedro looses.";
		
		driverUno.get(baseUrl);
		driverDos.get(baseUrl);
		TimeUnit.SECONDS.sleep(1);
        
		driverUno.findElement(By.id("nickname")).clear();
		driverUno.findElement(By.id("nickname")).sendKeys("Miguel");
		driverUno.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverDos.findElement(By.id("nickname")).clear();
		driverDos.findElement(By.id("nickname")).sendKeys("Pedro");	
		driverDos.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverUno.findElement(By.id("cell-4")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-1")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-8")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-0")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-2")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-5")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-6")).click();
		TimeUnit.SECONDS.sleep(3);
		
		String mensajeWeb = driverUno.switchTo().alert().getText();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("mensajeWeb");
		
		TimeUnit.SECONDS.sleep(1);
		assertThat(mensajeWeb).isEqualTo(mensaje);
	
	}
	
	@Test
	public void pierdePrimerJugador() throws InterruptedException {
		
		// O |   | X
		// O | X | 
		// O | X | 	
		
		String mensaje = "Pedro wins! Miguel looses.";
		
		driverUno.get(baseUrl);
		driverDos.get(baseUrl);
		TimeUnit.SECONDS.sleep(1);
        
		driverUno.findElement(By.id("nickname")).clear();
		driverUno.findElement(By.id("nickname")).sendKeys("Miguel");
		driverUno.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverDos.findElement(By.id("nickname")).clear();
		driverDos.findElement(By.id("nickname")).sendKeys("Pedro");	
		driverDos.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverUno.findElement(By.id("cell-2")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-0")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-4")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-6")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-7")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-3")).click();
		TimeUnit.SECONDS.sleep(3);
		
		String mensajeWeb = driverUno.switchTo().alert().getText();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("mensajeWeb");
		
		TimeUnit.SECONDS.sleep(1);
		assertThat(mensajeWeb).isEqualTo(mensaje);
	
	}
	
	@Test
	public void hayEmpate() throws InterruptedException {
		
		// X | O | O
		// O | X | X
		// X | X | O
		
		String mensaje = "Draw!";
		
		driverUno.get(baseUrl);
		driverDos.get(baseUrl);
		TimeUnit.SECONDS.sleep(1);
        
		driverUno.findElement(By.id("nickname")).clear();
		driverUno.findElement(By.id("nickname")).sendKeys("Miguel");
		driverUno.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverDos.findElement(By.id("nickname")).clear();
		driverDos.findElement(By.id("nickname")).sendKeys("Pedro");	
		driverDos.findElement(By.id("startBtn")).click();
		TimeUnit.SECONDS.sleep(1);

		driverUno.findElement(By.id("cell-0")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-3")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-4")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-8")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-6")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-2")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-5")).click();
		TimeUnit.SECONDS.sleep(1);
		driverDos.findElement(By.id("cell-1")).click();
		TimeUnit.SECONDS.sleep(1);
		driverUno.findElement(By.id("cell-7")).click();
		TimeUnit.SECONDS.sleep(3);
		
		String mensajeWeb = driverUno.switchTo().alert().getText();

		TimeUnit.SECONDS.sleep(1);
		System.out.println(mensajeWeb);
		
		TimeUnit.SECONDS.sleep(1);
		assertThat(mensajeWeb).isEqualTo(mensaje);
	
	}

}