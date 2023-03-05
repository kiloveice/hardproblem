package live.hardproblem.service;

import live.hardproblem.dao.ExFoodMapper;
import live.hardproblem.dao.ExFoodTagMapper;
import live.hardproblem.dao.ExMenuFoodMapper;
import live.hardproblem.dao.FoodMapper;
import live.hardproblem.dao.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class FoodService {
    @Autowired
    ExFoodMapper foodMapper;
    @Autowired
    ExFoodTagMapper foodTagMapper;
    @Autowired
    ExMenuFoodMapper menuFoodMapper;

    protected void insert_fill(Food food) {
        food.setId(null);
        food.setStatus(null);
        food.setCreatedAt(null);
        food.setUpdatedAt(null);
    }

    protected void update_fill(Food food) {
        food.setUpdatedAt(null);
    }

    @CacheEvict(cacheNames = {"food_all"}, allEntries = true)
    public int insert(Food food) {
        insert_fill(food);
        return foodMapper.insertSelective(food);
    }

    @CacheEvict(cacheNames = {"food_all", "food_id", "menu_food_all"}, allEntries = true)
    public int update(Food food) {
        update_fill(food);
        return foodMapper.updateByPrimaryKeySelective(food);
    }

    @Cacheable(cacheNames = "food_all")
    public ArrayList<Food> selectAll(int start, int end, boolean all) {
        return (ArrayList<Food>) foodMapper.selectAll(start, end, all);
    }

    @Cacheable(cacheNames = "food_id")
    public Food getById(Integer id) {
        return foodMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(cacheNames = {"food_all", "food_id", "menu_food_all"}, allEntries = true)
    public boolean removeById(Food food) {
        Integer n = foodMapper.removeById(food.getId());
//        if (n == null || n <= 0) {
//            return false;
//        }
        foodTagMapper.removeByFoodId(food.getId());
        menuFoodMapper.deleteFoodByFoodId(food.getId());
        return true;
    }
}
