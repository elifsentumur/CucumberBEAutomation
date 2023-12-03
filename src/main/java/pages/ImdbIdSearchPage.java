package pages;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class ImdbIdSearchPage {
    private ApiPage apiPage;

    public ImdbIdSearchPage(ApiPage apiPage) {
        this.apiPage = apiPage;
    }

    public String getImdbIdByTitle(Response response, String title) {
        JsonPath jsonPath = response.jsonPath();
        List<String> titles = jsonPath.getList("Search.Title");
        System.out.println("title = " + title);
        if (titles != null) {
            for (int i = 0; i < titles.size(); i++) {
                String currentTitle = titles.get(i);
                System.out.println("Current title" +currentTitle);
                if (currentTitle.equals(title)) {
                    return jsonPath.getString("Search[" + i + "].imdbID");
                }
            }
        }
        return null;
    }

    public Response searchByImdbId(String imdbId) {
        return apiPage.sendGetRequest("/?i=" + imdbId);
    }

    public String getTitleFromResponse(Response response) {
        System.out.println("response.prettyPrint " + response.prettyPrint());
        JsonPath jsonPath = response.jsonPath();
        // İlgili JSON yolunu kullanarak title'ı al
        return jsonPath.getString("Title");
    }
    public String getImbdIDFromResponse(Response response) {
        System.out.println("response.prettyPrint " + response.prettyPrint());
        JsonPath jsonPath = response.jsonPath();
        // İlgili JSON yolunu kullanarak id'yi al
        return jsonPath.getString("imdbID");
    }


}
