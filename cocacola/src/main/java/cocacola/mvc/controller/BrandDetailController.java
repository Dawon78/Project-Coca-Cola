package cocacola.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cocacola.mvc.domain.BrandDTO;
import cocacola.mvc.domain.SubBrandDTO;
import cocacola.mvc.service.BrandService;

@Controller
@RequestMapping("/cocacola")
public class BrandDetailController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brandDetail")
    public String process(
            @RequestParam("brandId") int brandId,
            @RequestParam(value = "tab", defaultValue = "home") String tab,
            Model model) throws Exception {

        // 브랜드 기본정보 + 영상 + 제품목록 조립은 Service가 담당
        BrandDTO brand = brandService.getBrandDetail(brandId);
        if (brand == null) {
            return "error/404";
        }

        if (brandId != 9) {
            List<SubBrandDTO> subBrands = brandService.getSubBrands(brandId);
            model.addAttribute("subBrands", subBrands);
        }

        model.addAttribute("brand", brand);

        if (brandId == 1) {
            if ("products".equals(tab)) {
                return "cocacola/brands/cocacolaProducts";
            }
            return "cocacola/brands/cocacolaHomeAndProduct";
        } else if (brandId == 8) {
            return "cocacola/brands/georgiaDetail";
        } else if (brandId == 9) {
            return "cocacola/brands/minutemaidHome";
        } else {
            return "cocacola/brands/brandDetail";
        }
    }
}
