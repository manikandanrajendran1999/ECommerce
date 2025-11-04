package com.stepStepDefinition;

import java.io.IOException;

import org.base.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePage {
	
	Hooks base;
	
	public HomePage(Hooks base) {
		this.base = base;
	}
	
	@Given("launch the browser")
	public void launch_the_browser() throws IOException {
		base.launchBrowser();
	}
	@Given("verify the title of the page")
	public void verify_the_title_of_the_page() {
	}
	@When("Click the search box")
	public void click_the_search_box() {
	}
	@When("fill the {string}")
	public void fill_the(String string) {
		System.out.println(string);
	}
	@Then("click the enter button")
	public void click_the_enter_button() {
	}

	
}
