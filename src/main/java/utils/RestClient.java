package utils;

import com.api.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import io.restassured.response.Response;
public class RestClient {


    String baseUrl ;
    String basePath ;

/*    public RestClient(String url, String path) {
        this.baseUrl=url;
        this.basePath=path;

    }*/

    public RestClient  (String url, String path ) {
        this.baseUrl=url;
        this.basePath=path;


    }

    public io.restassured.response.Response post(Object body, Map<String,String> headers, Map<String,Object> queryParam){
        RequestSpecification requestSpecification = getRequestSpec(body,headers,queryParam);
        io.restassured.response.Response resp = RestAssured.given().spec(requestSpecification).when().post();

        return resp;
    }


    public io.restassured.response.Response put(Object body, Map<String,String> headers, Map<String,Object> queryParam){
        RequestSpecification requestSpecification = getRequestSpec(body,headers,queryParam);
        io.restassured.response.Response resp = RestAssured.given().spec(requestSpecification).when().put();

        return resp;
    }

    public io.restassured.response.Response get(Object body, Map<String,String> headers, Map<String,Object> queryParam){
        RequestSpecification requestSpecification = getRequestSpec(body,headers,queryParam);
        io.restassured.response.Response resp = RestAssured.given().spec(requestSpecification).when().get();

        return resp;
    }

    public io.restassured.response.Response delete(Object body, Map<String,String> headers, Map<String,Object> queryParam){
        RequestSpecification requestSpecification = getRequestSpec(body,headers,queryParam);
        Response resp = RestAssured.given().spec(requestSpecification).when().delete();

        return resp;
    }

    public RequestSpecification getRequestSpec(Object body,Map<String,String> headers,Map<String,Object> queryParam){

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setUrlEncodingEnabled(true);


        if(body!=null){
            requestSpecBuilder.setBody(body);
        }

        if(headers!=null){
            for(String h:headers.keySet()){
                requestSpecBuilder.addHeader(h,headers.get(h));
            }
        }
        if(queryParam!=null){
            for(String q:queryParam.keySet()){
                requestSpecBuilder.addQueryParam(q,queryParam.get(q));
            }
        }

        return requestSpecBuilder.build();

    }

}
