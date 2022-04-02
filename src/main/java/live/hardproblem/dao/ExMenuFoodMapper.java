package live.hardproblem.dao;

import java.util.List;

public interface ExMenuFoodMapper extends MenuFoodMapper {


    List<Integer> selectFoodIdByMenuId(Integer id);
}
