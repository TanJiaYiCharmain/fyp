
package e63c.charmain.lp03;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String getUserPage() {
        return "user_data"; // This corresponds to user_data.html
    }
    @GetMapping("/design")
    public String getDesignPage() {
        return "design"; // This corresponds to user_data.html
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // This corresponds to user_data.html
    }
    
   
}
