
package e63c.charmain.lp03;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/user")
    public String getUserPage() {
        return "user_data"; 
    }
    @GetMapping("/design")
    public String getDesignPage() {
        return "design"; 
    }
    @GetMapping("/")
    public String loginPage() {
        return "index"; 
    }
    
    
    
    
    
    
   
}
