package com.heal.framework.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HealWebElementDummy implements HealWebElement
{
    private String sElementName;

    public HealWebElementDummy(String sName)
    {
        sElementName = sName;
    }

    @Override
    public void clear() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public void click() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");

    }

    @Override
    public WebElement findElement(By arg0) {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public List<WebElement> findElements(By arg0) {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public String getAttribute(String arg0) {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public String getCssValue(String arg0) {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public Point getLocation() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public Dimension getSize() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public String getTagName() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public String getText() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public boolean isDisplayed() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public boolean isEnabled() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public boolean isSelected() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");
    }

    @Override
    public void sendKeys(CharSequence... arg0) {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");

    }

    @Override
    public void submit() {
        throw new com.gd.framework.ATFException("Page element " + sElementName + " not found!");

    }

}
