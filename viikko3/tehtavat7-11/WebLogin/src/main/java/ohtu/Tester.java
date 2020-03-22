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

        System.out.println(driver.getPageSource());

        sleep(2);

        // login with valid credentials
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//
//        sleep(2);
//
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));
//
//        sleep(2);
//        element.submit();
        // login with invalid password
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//
//        sleep(2);
//
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("cat");
//        element = driver.findElement(By.name("login"));
//
//        sleep(2);
//        element.submit();
        // creating new user
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + new Random().nextInt(10000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("secret123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("secret123");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        System.out.println(driver.getPageSource());

        sleep(1);

        // logging out after creating a user
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(1);

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        sleep(3);
        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
