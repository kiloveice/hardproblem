package live.hardproblem.service;

import live.hardproblem.dao.ExFoodMapper;
import live.hardproblem.dao.ExFoodTagMapper;
import live.hardproblem.dao.ExMenuFoodMapper;
import live.hardproblem.dao.FoodMapper;
import live.hardproblem.dao.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
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

    public int insert(Food food) {
        insert_fill(food);
        return foodMapper.insertSelective(food);
    }

    public int update(Food food) {
        update_fill(food);
        return foodMapper.updateByPrimaryKeySelective(food);
    }

    public ArrayList<Food> selectAll(int page, int num, boolean all) {
        return (ArrayList<Food>) foodMapper.selectAll(page, num, all);
    }

    public Food getById(Integer id) {
        return foodMapper.selectByPrimaryKey(id);
    }

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
