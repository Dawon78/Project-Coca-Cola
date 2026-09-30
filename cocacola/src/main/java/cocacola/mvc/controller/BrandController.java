package cocacola.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cocacola.mvc.domain.BrandDTO;
import cocacola.mvc.service.BrandService;

@Controller
@RequestMapping("/cocacola")
public class BrandController {

    @Autowired
    private BrandService brandService; // 스프링이 자동으로 주입

    @GetMapping("/brands")
    public String process(Model model) throws Exception {

        // 1. 데이터 조회 (Service에 위임)
        List<BrandDTO> list = brandService.getBrandList();

        // 2. Model에 저장 (기존 request.setAttribute 대체)
        model.addAttribute("mainBrands", list);

        // 3. 뷰 리턴 (prefix/suffix에 의해 경로 완성)
        return "cocacola/brands/brandList";
    }
}
