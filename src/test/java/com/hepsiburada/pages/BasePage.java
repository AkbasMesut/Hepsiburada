package com.hepsiburada.pages;

import com.hepsiburada.utilities.*;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    // #1 initialize the driver instance and object of the class

    public BasePage() {
    /*   initElements method will create connection in between the current
    driver session(instance) and the object of the current class.  */

        PageFactory.initElements(Driver.getDriver(),this);
    }

}
