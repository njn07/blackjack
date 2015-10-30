package controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import routing.GamesRouter;
import utils.JSONParser;

@Path("rest")
public class RestController {
	static GamesRouter router = new GamesRouter();

	@GET
	public Response processResponse(@QueryParam("action") String action,
			@QueryParam("userId") String userId)
			throws JsonGenerationException, JsonMappingException, IOException {
		HashMap<String, String> responseResult = router.handleUserRequest(
				userId, action);
		String JSONResult = JSONParser.parseToJson(responseResult);
		Response resp=Response.ok(JSONResult, MediaType.APPLICATION_JSON).build();
		MultivaluedMap<String, Object> headers = resp.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept");
		return resp;
		//return Response.ok(JSONResult, MediaType.APPLICATION_JSON).build();
	}

}
