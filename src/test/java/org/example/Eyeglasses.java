package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Eyeglasses {
    static WebDriver driver = WebDriverManager.chromedriver().avoidShutdownHook().create();
    static Actions act = new Actions(driver);

    public static void sub(List<WebElement> subCategory){
        ///2.List for sub category
        for(int j=0; j<subCategory.size(); j++){
            act.moveToElement(subCategory.get(j)).build().perform();
            String str = subCategory.get(j).getText();
            System.out.println(str);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        driver.manage().window().maximize();
        driver.get("https://www.lenskart.com/");
        WebElement EYEGLASSES = driver.findElement(By.xpath("//a[contains(text(),'EYEGLASSES')]"));

        //1.List for main category
        List<WebElement> mainCategory = driver.findElements(By.xpath
                ("(//div[@class='gender_info']/following-sibling::div)[1]//span[@class='fw700']"));

        List<WebElement> subCategory = driver.findElements(By.xpath
                ("(//div[@class='display-none select-submenu'])//div[@class='nav-level-4']/a/span"));
        System.out.println(subCategory.size());

        act.moveToElement(EYEGLASSES).build().perform();

        int i = 1;
        while (i < mainCategory.size()) {
            act.moveToElement(mainCategory.get(i)).build().perform();
            Thread.sleep(100);
            sub(subCategory);
            i++;
        }


        /*subCategory.forEach(webElement -> {
            act.moveToElement(webElement).build().perform();
            String str = webElement.getText();
            System.out.println(str);
        });*/
    }
}
