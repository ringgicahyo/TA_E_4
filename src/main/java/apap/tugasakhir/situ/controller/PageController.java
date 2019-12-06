package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.JenisLowonganModel;
import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.service.JenisLowonganService;
import apap.tugasakhir.situ.service.JenisSuratService;
import apap.tugasakhir.situ.service.RoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class PageController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private JenisLowonganService jenisLowonganService;
    
    @Autowired
    private JenisSuratService jenisSuratService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String home(Model model){
        // Mapper
        Gson gson = new Gson();

        // ================== LOWONGAN =====================
        // All Lowongan
        List<JenisLowonganModel> allJenisLowongan = jenisLowonganService.getAllJenisLowongan();

        // Initial list of all jenis lowongan and it's corresponding amount.
        List<String> allJenisLowonganString = new ArrayList<>();
        List<Integer> allJenisLowonganAmount = new ArrayList<>();

        for (JenisLowonganModel j : allJenisLowongan) {
            allJenisLowonganString.add(j.getNama());
            allJenisLowonganAmount.add(j.getListLowongan().size());
        }

        // String of all needed information for the chart to be drawn, in String, to be parsed to Javascript Array later in template.
        String allJenisLowonganFinal = "";
        String allJenisLowonganAmountFinal = "";
        String colorsLowonganFinal = colorStringGenerator(allJenisLowongan.size());

        // Obtain String berformat "aaa,bbb"
        for (String s : allJenisLowonganString) {
            allJenisLowonganFinal += s + ',';
        }
        allJenisLowonganFinal = allJenisLowonganFinal.substring(0,allJenisLowonganFinal.length()-1);

        // Obtain String berformat "1,2"
        for (Integer i : allJenisLowonganAmount) {
            allJenisLowonganAmountFinal += Integer.toString(i) + ',';
        }
        allJenisLowonganAmountFinal = allJenisLowonganAmountFinal.substring(0,allJenisLowonganAmountFinal.length()-1);
        
        // Add all String to model.
        model.addAttribute("jenisLowongan", allJenisLowonganFinal);
        model.addAttribute("amountJenisLowongan", allJenisLowonganAmountFinal);
        model.addAttribute("colorsLowongan", colorsLowonganFinal);

        // ================== SURAT =====================
        // All Surat
        List<JenisSuratModel> allJenisSurat = jenisSuratService.getListJenisSurat();

        // Initial list of all jenis surat and it's corresponding amount.
        List<String> allJenisSuratString = new ArrayList<>();
        List<Integer> allJenisSuratAmount = new ArrayList<>();

        for (JenisSuratModel j : allJenisSurat) {
            allJenisSuratString.add(j.getNama());
            allJenisSuratAmount.add(j.getListPengajuanSurat().size());
        }

        // String of all needed information for the chart to be drawn, in String, to be parsed to Javascript Array later in template.
        String allJenisSuratFinal = "";
        String allJenisSuratAmountFinal = "";
        String colorsSuratFinal = colorStringGenerator(allJenisSurat.size());

        // Obtain String berformat "aaa,bbb"
        for (String s : allJenisSuratString) {
            allJenisSuratFinal += s + ',';
        }
        allJenisSuratFinal = allJenisSuratFinal.substring(0,allJenisSuratFinal.length()-1);

        // Obtain String berformat "1,2"
        for (Integer i : allJenisSuratAmount) {
            allJenisSuratAmountFinal += Integer.toString(i) + ',';
        }
        allJenisSuratAmountFinal = allJenisSuratAmountFinal.substring(0,allJenisSuratAmountFinal.length()-1);

        // Add all String to model.
        model.addAttribute("jenisSurat", allJenisSuratFinal);
        model.addAttribute("amountJenisSurat", allJenisSuratAmountFinal);
        model.addAttribute("colorsSurat", colorsSuratFinal);

        return "home";
    }

    // Hex string color generator, separated by comma.
    public String colorStringGenerator(int numberOfColors) {
        String colors = "";

        // create random object - reuse this as often as possible
        Random random = new Random();

        for (int i=0; i < numberOfColors; i++) {
            // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
            int nextInt = random.nextInt(0xffffff + 1);

            // format it as hexadecimal string (with hashtag and leading zeros)
            String colorCode = String.format("#%06x", nextInt);

            // Add to String.
            colors += colorCode + ",";
        }

        // Remove ending comma.
        colors = colors.substring(0, colors.length()-1);

        return colors;
    }
}
