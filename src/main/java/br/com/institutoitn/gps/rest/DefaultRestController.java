package br.com.institutoitn.gps.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.institutoitn.gps.models.GpsModel;
import br.com.institutoitn.gps.services.GpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value="/")
public class DefaultRestController {

	private ArrayList<String> receivedRequests = new ArrayList<String>();
	private int maxStoredRequests = 10;
	private final GpsService gpsService;
	private static final Logger logger = LoggerFactory.getLogger(DefaultRestController.class);

	@Autowired
	DefaultRestController(GpsService gpsService){
	    this.gpsService = gpsService;
    }
	/**
	 * Receives any HTTP request and returns an HTML response with the contents of the request.
	 * @param request
	 * @return String
	 * @throws Exception
	 */
/*	@RequestMapping(value="/**", method={
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.PUT,
		RequestMethod.DELETE,
		RequestMethod.PATCH,
		RequestMethod.OPTIONS
	})
	public @ResponseBody String receiveAllRequests(HttpServletRequest request) throws Exception {
		synchronized (this) {

			StringBuilder builder = new StringBuilder();
			builder.append("<html>");
			builder.append("<body>");
			builder.append("----------------------------------------- New request !<br/>");

			// date, path, method
			builder.append("Date: " + new Date() + "<br/>");
			builder.append("Path: " + request.getRequestURI() + "<br/>");
			builder.append("Method: " + request.getMethod() + "<br/>");

			// headers
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				builder.append("Header " + headerName + ": " + request.getHeader(headerName) + "<br/>");
			}

			// query string
			builder.append("QueryString: " + request.getQueryString() + "<br/>");

			// body
			builder.append("Body:<br/>");
			StringWriter writer = new StringWriter();
			InputStream stream = request.getInputStream();
	        String encoding = "UTF-8";
//			IOUtils.copy(stream, writer, encoding);
			builder.append(writer.toString());
			writer.close();

			builder.append("<br/><br/>");
			builder.append("</body>");
			builder.append("</html>");

			String details = builder.toString();
			receivedRequests.add(details);

			// remove older request details
			if (receivedRequests.size() > maxStoredRequests) receivedRequests.remove(0);

			System.out.println(details);
            logger.info("requests: "+receivedRequests.size());
			return details;
		}
    }*/

	/**
	 * Returns an HTML response with the contents of the last received requests.
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/last-requests", method=RequestMethod.GET)
	public @ResponseBody String showLast10Requests(HttpServletRequest request) throws Exception {

		StringBuilder builder = new StringBuilder();
		for (int i=receivedRequests.size()-1; i>=receivedRequests.size()-1-maxStoredRequests; i--) {
			if (i < 0) break;
			builder.append(receivedRequests.get(i));
		}

		return builder.toString();
	}
// TODO
    @RequestMapping(method= RequestMethod.POST, path="/gps/receiver")
    public ResponseEntity<GpsModel> gpsReceiver(@RequestBody GpsModel gpsModel) {
        //onReceivedMesage(gpsModel);
        return ResponseEntity.ok(gpsModel);
    }


}