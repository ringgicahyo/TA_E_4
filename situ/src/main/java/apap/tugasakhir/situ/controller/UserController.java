package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.service.RoleService;
import apap.tugasakhir.situ.service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {

        String newPassword = user.getPassword();

        boolean newPasswordValid = userService.checkPasswordValidity(newPassword);

        if (!newPasswordValid) {
            // Invalid new password.
            model.addAttribute("error", "Password harus mengandung huruf, angka, dan minimal 8 karakter!");
            model.addAttribute("listRole", roleService.findAll());
            return "form-add-user";
        }

        // Success.
        userService.addUser(user);

        model.addAttribute("listRole", roleService.findAll());
        return "form-add-user";

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    private String addUserForm(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "form-add-user";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    private String changePasswordForm(Model model) {
        return "form-update-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String changePasswordPost(
            @ModelAttribute("oldPassword") String oldPassword,
            @ModelAttribute("newPassword") String newPassword,
            @ModelAttribute("confirmNewPassword") String confirmNewPassword,
            Model model) {

        UserModel current = userService.getUserByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        boolean passwordCorrect = userService.comparePasswordAgainstDatabase(current, oldPassword);
        boolean passwordUnchanged = userService.comparePasswordAgainstDatabase(current, newPassword);
        boolean newPasswordMatch = newPassword.equals(confirmNewPassword);
        boolean newPasswordValid = userService.checkPasswordValidity(newPassword);

        if (!passwordCorrect) {
            // Wrong password.
            model.addAttribute("error", "Password salah!");
            return "form-update-password";
        }

        if (passwordUnchanged) {
            // Unchanged password.
            model.addAttribute("error", "Password lama dan baru sama!");
            return "form-update-password";
        }

        if (!newPasswordValid) {
            // Invalid new password.
            model.addAttribute("error", "Password harus mengandung huruf, angka, dan minimal 8 karakter!");
            return "form-update-password";
        }

        if (!newPasswordMatch) {
            // Password mismatch
            model.addAttribute("error", "Password baru dan konfirmasi password salah!");
            return "form-update-password";
        }

        // Success.
        userService.updatePassword(current, newPassword);

        model.addAttribute("error", "success");
        model.addAttribute("success", "Ganti password berhasil!");
        return "form-update-password";
    }
}
