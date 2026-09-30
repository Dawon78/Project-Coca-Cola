package cocacola.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocacola.mvc.domain.BrandDTO;
import cocacola.mvc.domain.BrandVideoDTO;
import cocacola.mvc.domain.ProductDTO;
import cocacola.mvc.domain.SubBrandDTO;
import cocacola.mvc.mapper.BrandMapper;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    // 브랜드 전체 목록
    public List<BrandDTO> getBrandList() {
        return brandMapper.selectBrandList();
    }

    // 브랜드 상세 (기본정보 + 영상 + 제품목록을 하나의 BrandDTO로 조립)
    // 없는 브랜드면 null 반환
    public BrandDTO getBrandDetail(int brandId) {
        BrandDTO brand = brandMapper.selectBrand(brandId);
        if (brand == null) {
            return null;
        }

        BrandVideoDTO video = brandMapper.selectBrandVideo(brandId);
        if (video != null) {
            brand.setYoutubeVideoId(video.getYoutubeVideoId());
            brand.setVideoDescription(video.getVideoDescription());
            brand.setVideoThumbnailUrl(video.getVideoThumbnailUrl());
        }

        List<ProductDTO> products = brandMapper.selectProducts(brandId);
        brand.setProducts(products);

        return brand;
    }

    // 브랜드 + 제품목록만 (영상 불필요한 페이지용, 예: 미닛메이드)
    public BrandDTO getBrandWithProducts(int brandId) {
        BrandDTO brand = brandMapper.selectBrand(brandId);
        if (brand == null) {
            return null;
        }

        brand.setProducts(brandMapper.selectProducts(brandId));
        return brand;
    }

    // 서브 브랜드 목록
    public List<SubBrandDTO> getSubBrands(int brandId) {
        return brandMapper.selectSubBrands(brandId);
    }
}
