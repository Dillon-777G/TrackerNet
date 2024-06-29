package alpha.net.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        return "forward:/index.html"; 
    }
    
}