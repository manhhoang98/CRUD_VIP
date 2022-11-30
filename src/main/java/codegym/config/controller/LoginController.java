package codegym.config.controller;

import codegym.config.model.Account;
import codegym.config.model.Roles;
import codegym.config.repository.IRolesRepo;
import codegym.config.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession httpSession;


    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("account", new Account());
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        Account account = accountService.login(username, password);
        if (account == null) {
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("login", "Đăng nhập thành công");
            httpSession.setAttribute("account", account);
            return "redirect:/products";
        }
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return "/login";
        }
        Roles roles = accountService.FindById(Long.valueOf(2));
        account.setRole(roles);
        accountService.register(account);
        redirectAttributes.addFlashAttribute("register", "Đăng kí thành công");
        return "redirect:/login";
    }


}