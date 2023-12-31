package com.a604.cake4u.recommendation.controller;

import com.a604.cake4u.buyer.entity.Buyer;
import com.a604.cake4u.buyer.repository.BuyerRepository;
import com.a604.cake4u.enums.EGender;
import com.a604.cake4u.enums.ESituation;
import com.a604.cake4u.portfolio.dto.PortfolioResponseDto;
import com.a604.cake4u.portfolio.repository.RecommPortfolioRepository;
import com.a604.cake4u.recommendation.dto.RecommendationFilter;
import com.a604.cake4u.recommendation.service.RecommendationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Recommendation Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommendation")
@Slf4j
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final RecommPortfolioRepository recommPortfolioRepository;
    private final BuyerRepository buyerRepository;

    @GetMapping("/personal")
    public ResponseEntity<?> recommendByGenderAndAge(@RequestParam(value="page") int page, @RequestParam(value="gender") String gender, @RequestParam(value="age") int age){
        List<PortfolioResponseDto> portfolios = recommendationService.getPortfolioRecommendationByAgeAndGender(page, age, EGender.valueOf(gender));
        return ResponseEntity.status(HttpStatus.OK).body(portfolios);
    }

    @GetMapping("/situation")
    public ResponseEntity<?> recommendBySituation(@RequestParam(value="situation") String situation, @RequestParam(value="page") int page){
        List<PortfolioResponseDto> portfolios = recommendationService.getPortfolioRecommendationBySituation(page, ESituation.valueOf(situation));
        return ResponseEntity.status(HttpStatus.OK).body(portfolios);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<?> recommendByWishlist(@RequestParam(value="user-id") long userId){
        List<PortfolioResponseDto> portfolios = recommendationService.getPortfolioRecommendationByWishlist(userId);
        return ResponseEntity.status(HttpStatus.OK).body(portfolios);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> recommendByFilter(RecommendationFilter recommendationFilter) {
        List<PortfolioResponseDto> portfolios = recommPortfolioRepository.findPortfolioRecommended(recommendationFilter);

        log.info("portfolios = ", portfolios);
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

}
