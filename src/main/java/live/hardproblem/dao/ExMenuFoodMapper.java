package live.hardproblem.dao;

import live.hardproblem.dao.entity.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExMenuFoodMapper extends MenuFoodMapper {
    List<Integer> selectFoodIdByMenuId(@Param("id") int id);

    List<Food> getFoodByMenuId(@Param("menuId") Integer menuId, boolean all);
}
