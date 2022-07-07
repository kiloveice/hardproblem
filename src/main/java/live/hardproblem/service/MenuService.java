package live.hardproblem.service;

import live.hardproblem.dao.ExFoodMapper;
import live.hardproblem.dao.ExMenuFoodMapper;
import live.hardproblem.dao.ExMenuMapper;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.Menu;
import live.hardproblem.dao.entity.MenuFood;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    ExMenuMapper menuMapper;
    @Autowired
    ExMenuFoodMapper menuFoodMapper;
    @Autowired
    ExFoodMapper foodMapper;

    protected void insert_fill(Menu menu) {
        menu.setId(null);
        menu.setStatus(null);
        menu.setCreatedAt(null);
        menu.setUpdatedAt(null);
    }

    protected void update_fill(Menu menu) {
        menu.setUpdatedAt(null);
    }

    public ArrayList<Menu> getAll(boolean all) {
        return (ArrayList<Menu>) menuMapper.getAll(all);
    }

    public int insert(Menu menu) {
        insert_fill(menu);
        return menuMapper.insertSelective(menu);
    }

    public int update(Menu menu) {
        update_fill(menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public int addMenuFood(MenuFood menuFood) {
        menuFood.setId(null);
        menuFood.setStatus(null);
        menuFood.setCreatedAt(null);
        menuFood.setUpdatedAt(null);
        return menuFoodMapper.insertSelective(menuFood);
    }

//    多表连接，废弃
//    public ArrayList<Food> getFoodByMenuId(Integer menuId, boolean all) {
//        return (ArrayList<Food>) menuFoodMapper.getFoodByMenuId(menuId, all);
//    }

    public ArrayList<Food> getFoodByMenuId(Integer menuId, boolean all) {
        List<Integer> foodIdList = menuFoodMapper.getFoodIdByMenuId(menuId, all);
        return (ArrayList<Food>) foodMapper.selectInFoodList(foodIdList, all);
    }

    public Menu getByMenuId(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    public int deleteFood(MenuFood menuFood) {
        return menuFoodMapper.deleteAFoodByMenuIdAndFoodId(menuFood);
    }
}
