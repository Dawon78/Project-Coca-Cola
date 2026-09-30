package cocacola.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cocacola.mvc.domain.BrandDTO;
import cocacola.mvc.service.BrandService;

@Controller
@RequestMapping("/cocacola")
public class MinuteMadeController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/minutemaid")
    public String minutemaid(Model model) throws Exception {

        int brandId = 9;

        BrandDTO brand = brandService.getBrandWithProducts(brandId);

        model.addAttribute("brand", brand);

        return "cocacola/brands/minutemaidHome";
    }

    @GetMapping("/minutemaidProduct")
    public String minutemaidProduct(
            @RequestParam int start,
            @RequestParam int end,
            Model model) {

        int brandId = 9;

        BrandDTO brand = brandService.getBrandWithProducts(brandId);

        model.addAttribute("brand", brand);
        model.addAttribute("start", start);
        model.addAttribute("end", end);

        return "cocacola/brands/minutemaidProduct";
    }


}
