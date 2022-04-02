package live.hardproblem.service;

import live.hardproblem.dao.*;
import live.hardproblem.dao.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProblemService {
    @Autowired
    ExFoodMapper foodMapper;
    @Autowired
    ExMenuFoodMapper menuFoodMapper;
    @Autowired
    ExFoodTagMapper foodTagMapper;

    public Food solveWithMenu(int menuId, List<Integer> tagIdList, boolean include) {
        List<Integer> foodId = menuFoodMapper.selectFoodIdByMenuId(menuId);
        return getFoodIdByTag(foodId, tagIdList, include);
    }

    public Food solveWithoutMenu(List<Integer> tagIdList, boolean include) {
        return getFoodIdByTag(null, tagIdList, include);
    }

    public Food getFoodIdByTag(List<Integer> foodIdList, List<Integer> tagIdList, boolean include) {
        List<Integer> foodId = foodTagMapper.
                selectFoodIdByFoodIdListAndTagIdList(foodIdList, tagIdList, include, false);
        int id = getRandFoodId(foodId);
        return foodMapper.selectByPrimaryKey(id);
    }

    public int getRandFoodId(List<Integer> foodId) {
        Random random = new Random();
        int index = random.nextInt(foodId.size());
        return foodId.get(index);
    }
}
