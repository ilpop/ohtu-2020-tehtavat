package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        Random random = new Random();
       
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());
        
        System.out.println(driver.navigate());
        
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkepakkep1");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        System.out.println(driver.navigate());
        sleep(3);
        

        System.out.println(driver.getPageSource());
        

        
        driver.get("http://localhost:4567");
        sleep(2);
        WebElement element2 = driver.findElement(By.linkText("register new user"));
        element2.click();
        
        element2 = driver.findElement(By.name("username"));
        element2.sendKeys("pekka" +random.nextInt(100000));
        element2.findElement(By.name("password"));
        element2.sendKeys("pekkapassword");
        element2.findElement(By.name("passwordConfirmation"));
        element2.sendKeys("pekkapassword");
        element2.findElement(By.name("signup"));
        
        sleep(2);
        element2.submit();
        System.out.println(driver.navigate());
 

        WebElement element3 = driver.findElement(By.linkText("continue to application mainpage"));
        element3.findElement(By.name("logout"));
        sleep(2);
        element3.submit();
        System.out.println(driver.getPageSource());

        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
