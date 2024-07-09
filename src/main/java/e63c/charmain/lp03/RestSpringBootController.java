/**
 * 
 * I declare that this code was written by me, 22012568. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Tan Jia Yi Charmain
 * Student ID: 22012568
 * Class: C372-4D-E63C-A
 * Date created: 2024-Apr-29 10:44:37 am 
 * 
 */

package e63c.charmain.lp03;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 22012568
 *
 */

@RestController
public class RestSpringBootController {
	
	@GetMapping(value = "/api/user")
	public List<Object> getUser() {
		String url = "https://fudsrzbhqpmryvmxgced.supabase.co/rest/v1/users?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ1ZHNyemJocXBtcnl2bXhnY2VkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTM5MjE3OTQsImV4cCI6MjAyOTQ5Nzc5NH0.6UMbzoD8J1BQl01h6NSyZAHVhrWerUcD5VVGuBwRcag";
		RestTemplate restTemplate = new RestTemplate();
		Object[] users = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(users);
	}
	
	@GetMapping(value = "/api/design")
	public List<Object> getDesign() {
		String url = "https://fudsrzbhqpmryvmxgced.supabase.co/rest/v1/generated image?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ1ZHNyemJocXBtcnl2bXhnY2VkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTM5MjE3OTQsImV4cCI6MjAyOTQ5Nzc5NH0.6UMbzoD8J1BQl01h6NSyZAHVhrWerUcD5VVGuBwRcag";
		RestTemplate restTemplate = new RestTemplate();
		Object[] design = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(design);
	}
	
	@GetMapping("/designData")
public ModelAndView getPieChart(Model model) {
    // Call the /api/design endpoint to fetch the design data
    List<Object> designData = getDesign();

    // Process the design data to generate graph data
    Map<String, Integer> graphData = new TreeMap<>();
    for (Object design : designData) {
        String theme = ((Map<String, String>) design).get("theme"); // Get the theme from the design object
        graphData.put(theme, graphData.getOrDefault(theme, 0) + 1); // Increment count for the theme
    }

    // Process the design data to generate line chart data.
    Map<String, Integer> lineChartData = new LinkedHashMap<>(); // Use LinkedHashMap to preserve the order
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Initialize the line chart data with months in order
    for (int month = 1; month <= 12; month++) {
        String monthYear = capitalizeFirstLetter(Month.of(month).toString().toLowerCase());
        lineChartData.put(monthYear, 0);
    }

    for (Object design : designData) {
        String createdAt = ((Map<String, String>) design).get("created_at");
        LocalDate date = LocalDate.parse(createdAt, formatter);
        String month = capitalizeFirstLetter(date.getMonth().toString().toLowerCase());
        lineChartData.put(month, lineChartData.get(month) + 1);
    }

    // Add the graph data to the model
    model.addAttribute("chartData", graphData);
    model.addAttribute("lineChartData", lineChartData);

    // Return ModelAndView with view name and model
    ModelAndView modelAndView = new ModelAndView("statistics");
    modelAndView.addObject("chartData", graphData);
    modelAndView.addObject("lineChartData", lineChartData);
    return modelAndView;
}

private String capitalizeFirstLetter(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
}
	
}
