package stepDefinitions;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.HooverNavigationUtil;
import utilities.PropertyReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class HooverStepDefs {


    @Given("The baseURL")
    public void the_base_url() {
        baseURI = PropertyReader.getProperty("api_base_URI");
    }

    RequestSpecification requestSpecification;
    Response response;






    @Given("The details given in RequestPayload.json document")
    public void the_details_given_in_requestpayload_json_document() {

        JsonPath jsonPath = new JsonPath(new File("src/RequestPayload.json"));
        System.out.println(jsonPath.getString(""));

        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        body((Object)jsonPath.get("")).
                        header("Content-Type", "application/json");
    }



    @When("I send a POST request to cleaning-sessions resource")
    public void i_send_a_post_request_to_cleaning_sessions_resource() {
        response = requestSpecification.
                when().log().all().
                        post("/v1/{resource}");

    }

    @Then("The response payload should be correct in accordance with the request")
    public void the_response_payload_should_be_correct_in_accordance_with_the_request() {

        

        // Build the dirty patches collection as list of lists because you can remove the inner lists easily once the dirty patch is encountered
        List<List<Integer>> patches = new ArrayList<>();
        ArrayList<Integer> each = new ArrayList<>();
        each.add(1);
        each.add(0);
        patches.add(each);
        each = new ArrayList<>();
        each.add(2);
        each.add(2);
        patches.add(each);
        each = new ArrayList<>();
        each.add(2);
        each.add(3);
        patches.add(each);

        List<Integer> result = HooverNavigationUtil.getResult(5, 5, 1, 2, patches, "NNESEESWNWW");
        int expected_x_pos = result.get(0);
        int expected_y_pos = result.get(1);
        int expected_total_patches = result.get(2);


        System.out.println();

        response.
                then().log().all().
                    assertThat().
                statusCode(200).
                    body("coords[0]", equalTo(expected_x_pos)).
                    body("coords[1]", equalTo(expected_y_pos)).
                    body("patches", equalTo(expected_total_patches)).
                    time(lessThan(1500L));
    }




    @Given("The following room size details")
    public void the_following_room_size_details(List<Integer> x_and_y_room) {

        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    "+x_and_y_room.get(0)+",\n" +
                "    "+x_and_y_room.get(1)+"\n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    1,\n" +
                "    2\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "    [\n" +
                "      1,\n" +
                "      0\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      2\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      3\n" +
                "    ]\n" +
                "  ],\n" +
                "  \"instructions\": \"NNESEESWNWW\"\n" +
                "}";


        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);
    }
    @Then("The response status code should not be {int}")
    public void the_response_status_code_should_not_be(Integer status_code) {

        response.
                then().log().all().
                        assertThat().statusCode(not(status_code)).
                        time(lessThan(1500L));


    }



    @Given("The details given in RequestPayload.json document and given the following direction word {string}")
    public void the_details_given_in_request_payload_json_document_and_given_the_following_direction_word(String directionsWord) {


        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    5,\n" +
                "    5\n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    1,\n" +
                "    2\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "    [\n" +
                "      1,\n" +
                "      0\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      2\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      3\n" +
                "    ]\n" +
                "  ],\n" +
                "  \"instructions\": \""+directionsWord+"\"\n" +
                "}";


        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);

    }


    @Given("The details given in RequestPayload.json document and negative roomSize coordinates")
    public void the_details_given_in_request_payload_json_document_and_negative_room_size_coordinates(List<Integer> room_coords) {

        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    "+room_coords.get(0)+",\n" +
                "    "+room_coords.get(1)+"\n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    1,\n" +
                "    2\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "    [\n" +
                "      1,\n" +
                "      0\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      2\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      3\n" +
                "    ]\n" +
                "  ],\n" +
                "  \"instructions\": \"NNESEESWNWW\"\n" +
                "}";
        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);


    }
    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(Integer status_code) {


        response.
                then().log().all().
                assertThat().statusCode((status_code)).
                time(lessThan(1500L));



    }



    @Given("That I send a request with no dirty patches")
    public void that_i_send_a_request_with_no_dirty_patches() {

        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    5,\n" +
                "    5\n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    1,\n" +
                "    2\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"instructions\": \"NNESEESWNWW\"\n" +
                "}";
        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);


    }
    @Then("The patches cleaned should show {int}")
    public void the_patches_cleaned_should_show(Integer sum_of_patches_cleaned) {

        response.
                then().log().all().
                    assertThat().
                    body("patches", equalTo(sum_of_patches_cleaned)).
                    time(lessThan(1500L));

    }

    Integer roomLimitX;
    Integer roomLimitY;
    Integer sharedStartX;
    Integer sharedStartY;
    String sharedDirection;

    @Given("The roomsize {int} by {int}, Hoover starting point of {int} and {int}, and directions {string}")
    public void the_roomsize_by_hoover_starting_point_of_and_and_directions(Integer roomSizeX, Integer roomSizeY, Integer startX, Integer startY, String direction) {

        roomLimitX = roomSizeX;
        roomLimitY = roomSizeY;
        sharedStartX = startX;
        sharedStartY = startY;
        sharedDirection = direction;

        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    "+roomSizeX+",\n" +
                "    "+roomSizeY+"\n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    "+startX+",\n" +
                "    "+startY+"\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "    [\n" +
                "      1,\n" +
                "      0\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      2\n" +
                "    ],\n" +
                "    [\n" +
                "      2,\n" +
                "      3\n" +
                "    ]\n" +
                "  ],\n" +
                "  \"instructions\": \""+direction+"\"\n" +
                "}";

        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);


    }
    @Then("I should see that the Hoover doesn't end up beyond the limits of the room")
    public void i_should_see_that_the_hoover_doesn_t_end_up_beyond_the_limits_of_the_room() {
        response.
                then().log().all().
                    assertThat().
                    body("coords[0]", lessThanOrEqualTo(roomLimitX)).
                    body("coords[1]", lessThanOrEqualTo(roomLimitY)).
                    time(lessThan(1500L));

    }

    @Then("I should see that the Hoover ends up at the correct last Northern positions")
    public void i_should_see_that_the_hoover_ends_up_at_the_correct_last_Northern_positions() {
        List<List<Integer>> patches = new ArrayList<>();


        List<Integer> result = HooverNavigationUtil.getResult(roomLimitX, roomLimitY, sharedStartX, sharedStartY, patches, sharedDirection);


        response.
                then().log().all().
                assertThat().
                body("coords[0]", equalTo(sharedStartX)).
                body("coords[1]", equalTo(roomLimitY)).
                time(lessThan(1500L));
    }




    @Then("I should see that the Hoover ends up at the correct last Eastern positions")
    public void i_should_see_that_the_hoover_ends_up_at_the_correct_last_Eastern_positions() {
        List<List<Integer>> patches = new ArrayList<>();


        List<Integer> result = HooverNavigationUtil.getResult(roomLimitX, roomLimitY, sharedStartX, sharedStartY, patches, sharedDirection);


        response.
                then().log().all().
                assertThat().
                body("coords[0]", equalTo(roomLimitX)).
                body("coords[1]", equalTo(sharedStartY)).
                time(lessThan(1500L));
    }




    @Given("The following room size details2")
    public void the_following_room_size_details2(List<Integer> x_and_y_room) {

        String requestPayload = "{\n" +
                "  \"roomSize\": [\n" +
                "    "+x_and_y_room.get(0)+",\n" +
                "    "+x_and_y_room.get(1)+",\n" +
                "    "+x_and_y_room.get(2)+",\n" +
                "    "+x_and_y_room.get(3)+"\n" +
                "  \n" +
                "  ],\n" +
                "  \"coords\": [\n" +
                "    1,\n" +
                "    2\n" +
                "  ],\n" +
                "  \"patches\": [\n" +
                "\n" +
                "  ],\n" +
                "  \"instructions\": \"N\"\n" +
                "}";




        requestSpecification =
                given().
                        pathParams("resource", "cleaning-sessions").
                        header("Content-Type", "application/json").
                        body(requestPayload);
    }









































}
