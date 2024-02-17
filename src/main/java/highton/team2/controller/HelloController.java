package highton.team2.controller;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    public HelloController() {
        // WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // 브라우저 미표시 실행을 위한 옵션, 필요에 따라 제거 가능
        this.driver = new ChromeDriver(options);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    @GetMapping("/open")
    public String open() {
        driver.get("https://wrtn.ai/");
        return "ok!";
    }

    @GetMapping("/input/{value}")
    public String input(@PathVariable String value) {
        try {
            WebElement textarea = driver.findElement(By.cssSelector("textarea"));
            textarea.sendKeys(value);
            textarea.sendKeys(Keys.ENTER);

            String lastValue = ""; // 마지막으로 확인된 값을 저장할 변수
            String stableValue = ""; // 안정화된 값을 확인하기 위한 변수
            long lastChangeTime = System.currentTimeMillis(); // 마지막으로 값이 변경된 시간

            while (true) {
                Thread.sleep(1000); // 2초마다 검사

                String script = "const codeElements = document.querySelectorAll('swiper-container > swiper-slide > div > div:nth-child(1)');" +
                        "const lastCodeElement = codeElements[codeElements.length - 1];" +
                        "return lastCodeElement ? lastCodeElement.innerText.replaceAll(',', '').replaceAll('\"', '').replaceAll('[', '').replaceAll(']', '').replaceAll('줄바꿈', '\\n') : '';";
                String currentValue = (String) jsExecutor.executeScript(script);

//                String currentValue = (String) jsExecutor.executeScript(
//                        "const codeElements = document.querySelectorAll('p');" +
//                                "const lastCodeElement = codeElements[codeElements.length - 2];" +
//                                "return lastCodeElement ? lastCodeElement.innerText.replaceAll(',', '').replaceAll('\"', '').replaceAll('[', '').replaceAll(']', '').replaceAll('줄바꿈', '\n') : '';"
//                );
//                String currentValue = (String) jsExecutor.executeScript(
//                        "const codeElements = document.querySelectorAll('pre');" +
//                                "const lastCodeElement = codeElements[codeElements.length - 1];" +
//                                "return lastCodeElement ? lastCodeElement.innerText.replaceAll(',', '').replaceAll('\"', '').replaceAll('[', '').replace(']', '').replace('한 줄 요약: ', '') : '';"
//                );

                if (currentValue.equals(lastValue)) {
                    // 이전 값과 동일한 경우, 안정화 시간 체크
                    if (System.currentTimeMillis() - lastChangeTime >= 2000) { // 최소 4초간 값이 안정적인지 확인
                        // 현재 값과 안정화된 값이 동일한 경우, 값을 반환
                        return currentValue; // 변화가 없고 안정화된 값이 확인되면 현재 값을 반환
                    }
                } else {
                    // 값에 변화가 있는 경우, 시간과 안정화 값을 업데이트
                    lastChangeTime = System.currentTimeMillis();
                    stableValue = currentValue;
                }
                lastValue = currentValue; // 마지막 값을 현재 값으로 업데이트
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: Interrupted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Exception occurred - " + e.getMessage();
        }
    }

    // 애플리케이션 종료 시 WebDriver 자원 해제
    @Override
    protected void finalize() throws Throwable {
        if (driver != null) {
            driver.quit();
        }
        super.finalize();
    }
}
