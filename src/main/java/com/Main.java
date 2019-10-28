package com;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        // ��ʼ��������
        System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://passport.csdn.net/account/login";
        // ����url
        driver.get(baseUrl);
        // �ȴ��������
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // ��ȡҳ��Ԫ��
        WebElement elemUsername = driver.findElement(By.name("username"));
        WebElement elemPassword = driver.findElement(By.name("password"));
        WebElement btn = driver.findElement(By.className("logging"));
        WebElement rememberMe = driver.findElement(By.id("rememberMe"));
        // ����ҳ��Ԫ��
        elemUsername.clear();
        elemPassword.clear();
        elemUsername.sendKeys("username");
        elemPassword.sendKeys("password");
        rememberMe.click();
        btn.click();
        // �ύ��
        //btn.submit();
        Thread.sleep(5000);
        //driver.get("http://msg.csdn.net/");
        Thread.sleep(5000);
        // ��ȡcookies
        //driver.manage().getCookies();
        Set<org.openqa.selenium.Cookie> cookies = driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());
        Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();

        CookieStore cookieStore = new BasicCookieStore();

        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            BasicClientCookie bcco = new BasicClientCookie(cookie.getName(), cookie.getValue());
            bcco.setDomain(cookie.getDomain());
            bcco.setPath(cookie.getPath());
            cookieStore.addCookie(bcco);
        }
    }
}
