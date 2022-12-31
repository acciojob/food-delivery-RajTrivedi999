package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity=FoodEntity.builder().
                foodCategory(food.getFoodCategory()).
                foodId(food.getFoodId()).
                foodName(food.getFoodName()).
                foodPrice(food.getFoodPrice()).
                build();
        foodRepository.save(foodEntity);
        return FoodDto.builder().
                foodId(foodEntity.getFoodId()).
                foodCategory(foodEntity.getFoodCategory()).
                foodName(foodEntity.getFoodName()).
                foodPrice(foodEntity.getFoodPrice()).
                id(foodEntity.getId()).
                build();
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);

        return FoodDto.builder().
                foodCategory(foodEntity.getFoodCategory()).
                foodName(foodEntity.getFoodName()).
                foodPrice(foodEntity.getFoodPrice()).
                foodId(foodEntity.getFoodId()).
                id(foodEntity.getId()).
                build();
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity=FoodEntity.builder().
                id(foodDetails.getId()).
                foodId(foodDetails.getFoodId()).
                foodPrice(foodDetails.getFoodPrice()).
                foodCategory(foodDetails.getFoodCategory()).
                foodName(foodDetails.getFoodName()).
                build();
        foodRepository.save(foodEntity);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity =foodRepository.findByFoodId(id);
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntities= (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtos=null;
        for(FoodEntity foodEntity:foodEntities){
            foodDtos.add(FoodDto.builder().
                    foodName(foodEntity.getFoodName()).
                    foodPrice(foodEntity.getFoodPrice()).
                    foodId(foodEntity.getFoodId()).
                    foodCategory(foodEntity.getFoodCategory()).
                    id(foodEntity.getId()).
                    build());
        }
        return foodDtos;
    }
}