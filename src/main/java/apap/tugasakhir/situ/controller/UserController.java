package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.rest.AddEmployeeResponse;
import apap.tugasakhir.situ.rest.EmployeeDetail;
import apap.tugasakhir.situ.rest.EmployeeDetailResponse;
import apap.tugasakhir.situ.rest.SiPerpusUserDetailResponse;
import apap.tugasakhir.situ.service.RoleService;
import apap.tugasakhir.situ.service.SiPerpusUserService;
import apap.tugasakhir.situ.service.UserService;
import apap.tugasakhir.situ.service.UserRestService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private SiPerpusUserService siPerpusUserService;

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

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    private String addEmployeePost(@ModelAttribute UserModel user, HttpServletRequest httpRequest, Model model) {
        String newPassword = user.getPassword();

        boolean newPasswordValid = userService.checkPasswordValidity(newPassword);

        if (!newPasswordValid) {
            // Invalid new password.
            model.addAttribute("error", "Password harus mengandung huruf, angka, dan minimal 8 karakter!");
            model.addAttribute("listRole", roleService.findAll());
            return "form-add-employee";
        }

        // Success.
        UserModel newUser = userService.addUser(user);

        // Start processing JSON to fire to SI-SIVITAS API.
        JSONObject data = new JSONObject();
        data.put("idUser", newUser.getUuid());
        data.put("nip", "");
        data.put("nama", httpRequest.getParameter("nama"));
        data.put("tempatLahir", httpRequest.getParameter("tempatLahir"));
        data.put("tanggalLahir", httpRequest.getParameter("tanggalLahir"));
        data.put("alamat", httpRequest.getParameter("alamat"));
        data.put("telepon", httpRequest.getParameter("telepon"));

        Mono<AddEmployeeResponse> response = userRestService.postAddEmployee(data);

        // Check response status code.
        // Error (400)
        if (response.block().getStatus().equals("400")) {
            model.addAttribute("error", "Terdapat kesalahan data!");
            return "form-add-employee";
        }

        // Success (200)
        model.addAttribute("error", "success");
        return "form-add-employee";
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.GET)
    private String addEmployeeForm(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "form-add-employee";
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

    @GetMapping(value = "/profile")
    private String getUserProfile(Authentication authentication, Model model){
        UserModel loggedUser = userService.getUserByUsername(authentication.getName());
        try {
            EmployeeDetailResponse userResponse = userRestService.getUserProfile(loggedUser.getUuid()).block();
            EmployeeDetail user = userResponse.getResult();
            model.addAttribute("status", true);
            model.addAttribute("user", user);
            model.addAttribute("loggedUser", loggedUser);
        } catch (WebClientResponseException.NotFound exception){
            model.addAttribute("loggedUser", loggedUser);
        }
        return "user-profile";
    }

    @GetMapping(value = "/siperpus")
    private String getSiPerpusUser(Model model){
        SiPerpusUserDetailResponse siPerpusUser = siPerpusUserService.getSiPerpusUser().block();
        model.addAttribute("siPerpusUser", siPerpusUser.getResult());
        return "siperpus-user";
    }
}
