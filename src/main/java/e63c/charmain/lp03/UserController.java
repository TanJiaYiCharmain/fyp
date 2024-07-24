
package e63c.charmain.lp03;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @GetMapping("/user")
    public String getUserPage() {
        return "user_data"; 
    }
    @GetMapping("/")
    public String getDesignPage() {
        return "index"; 
    }
    
    @GetMapping("/hello")
	public String hello(Model model) {
		return "hello";
	}
   
}
