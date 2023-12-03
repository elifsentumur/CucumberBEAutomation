package stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.ApiPage;
import pages.ImdbIdSearchPage;
import utils.LoggerUtil;

import javax.lang.model.SourceVersion;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImdbIdSearchSteps {
    private ApiPage apiPage;
    private ImdbIdSearchPage imdbIdSearchPage;
    private String imdbId, searchAndImdbId, imdbIdSearchResponseTitle;
    private Response imdbIdSearchResponse;

    private Response idKeyResponse;

    private String searchResponseTitle, withIDSearchResponseTitle, idInfoResponseTitle;

    @Given("the API is up and running")
    public void givenApiIsUpAndRunning() {
        LoggerUtil.info("Before Scenario: Setting up API");
        apiPage = ApiPage.builder()
                .withBaseUrl("https://www.omdbapi.com")
                .build();
        imdbIdSearchPage = new ImdbIdSearchPage(apiPage);
    }

    @When("a GET request is sent to {string}")
    public void whenGetRequestSentToSearchByTitle(String movieTitle) {
        imdbIdSearchResponse = apiPage.searchResponse(movieTitle);
        LoggerUtil.info("Get Response value" + imdbIdSearchResponse);
    }

    @Then("the search response status code should be {int}")
    public void theSearchResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, imdbIdSearchResponse.getStatusCode());
    }

    @Then("the IMDb ID for {string} should be retrieved")
    public void thenImdbIdShouldBeRetrieved(String title) {
        imdbId = imdbIdSearchPage.getImdbIdByTitle(imdbIdSearchResponse, title);
        searchResponseTitle = imdbIdSearchPage.getTitleFromResponse(imdbIdSearchResponse);
        System.out.println("title value degeri = " + searchResponseTitle);
        LoggerUtil.info("IMDb ID for the movie: " + imdbId);
    }

    @And("id value should be checked")
    public void idValueShouldBeChecked() {
        Assert.assertNotNull("IMDb ID değeri boş olamaz.", imdbId);
        Assert.assertTrue("IMDb ID değeri boş olamaz.", imdbId.trim().length() > 0);
        LoggerUtil.info("IMDb ID değeri boş olamaz." + imdbId);
    }

    @And("a get actualTitle")
    public void aGetActualTitle() {
        Assert.assertNotNull("Title degeri boş olmaz.", searchResponseTitle);
        System.out.println("searchResponseTitle    = " + searchResponseTitle);
    }

    @When("a GET request is sent to the {string} search endpoint with type {string} and user specified type")
    public void aGETRequestIsSentToTheMovieSearchEndpointWithTypeAndUserSpecifiedType(String key, String value) {
        imdbIdSearchResponse = apiPage.keySearchResponse(imdbId, key, value);
        LoggerUtil.info("a GET request is sent to the {string} search endpoint with type {string} and user specified type : " + imdbIdSearchResponse);
    }

    @And("the title of the movie should be {string}")
    public void theTitleOfTheMovieShouldBe(String expectedTitle) {
        imdbIdSearchResponseTitle = imdbIdSearchResponse.jsonPath().getString("Title");
        LoggerUtil.info("Wait to actualTitle : " + imdbIdSearchResponseTitle);
        assertEquals(expectedTitle, imdbIdSearchResponseTitle);
    }

    @When("a GET request is sent to and used with imbdID")
    public void aGETRequestIsSentToAndUsedWithImbdID() {
        idKeyResponse = apiPage.idandSearchResponse(imdbId);
        System.out.println("api ve id ve search bilgisi ile  = " + idKeyResponse.prettyPrint());
        LoggerUtil.info("a GET request is sent to String and used with imbdID " + idKeyResponse.jsonPath());
    }

    @Then("this request should be title {string}")
    public void thisRequestShouldBeTitle(String exceptedTitle) {
        withIDSearchResponseTitle = imdbIdSearchPage.getTitleFromResponse(idKeyResponse);
        searchAndImdbId = imdbIdSearchPage.getImbdIDFromResponse(idKeyResponse);
        assertEquals(exceptedTitle, withIDSearchResponseTitle);
        LoggerUtil.info("this request should be title String" + withIDSearchResponseTitle);
        LoggerUtil.info("this request should be title searchAndImdbId :" + searchAndImdbId);
    }

    @And("get search id value should be checked")
    public void getSearchIdValueShouldBeChecked() {
        Assert.assertNotNull("IMDb ID değeri boş olamaz.", searchAndImdbId);
        Assert.assertTrue("IMDb ID değeri boş olamaz.", searchAndImdbId.trim().length() > 0);
        LoggerUtil.info("IMDb ID değeri boş olamaz." + searchAndImdbId);
    }

    @And("checking the header value of its two transactions")
    public void checkingTheHeaderValueOfItsTwoTransactions() {
        assertEquals(imdbIdSearchResponseTitle, withIDSearchResponseTitle);
    }

}
