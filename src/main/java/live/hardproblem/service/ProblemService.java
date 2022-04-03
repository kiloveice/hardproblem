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

    //有menu 有tag 正向筛选
    public Food solveWithMenuByTag(int menuId, List<Integer> tagIdList) {
        List<Integer> foodId = menuFoodMapper.selectFoodIdByMenuId(menuId);
        return getFoodIdByTag(foodId, tagIdList);
    }

    //没有menu 有tag 正向筛选
    public Food solveWithoutMenuByTag(List<Integer> tagIdList) {
        return getFoodIdByTag(null, tagIdList);
    }

    //从foodIdList中 筛选有tag 正向筛选
    public Food getFoodIdByTag(List<Integer> foodIdList, List<Integer> tagIdList) {
        List<Integer> foodId = foodTagMapper.
                selectFoodIdByFoodIdListAndTagIdList(foodIdList, tagIdList, true, false);
        int id = getRandFoodId(foodId);
        return foodMapper.selectByPrimaryKey(id);
    }

    public int getRandFoodId(List<Integer> foodId) {
        Random random = new Random();
        int index = random.nextInt(foodId.size());
        return foodId.get(index);
    }

    //有menu 没有tag
    public Food solveWithMenu(int menuId) {
        List<Integer> foodId = menuFoodMapper.selectFoodIdByMenuId(menuId);
        int id = getRandFoodId(foodId);
        return foodMapper.selectByPrimaryKey(id);
    }

    //没有menu 没有tag
    public Food solveWithoutMenu() {
        List<Integer> foodId = foodMapper.getAllId(false);
        int id = getRandFoodId(foodId);
        return foodMapper.selectByPrimaryKey(id);
    }
}
