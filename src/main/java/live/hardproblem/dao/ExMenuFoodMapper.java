package live.hardproblem.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExMenuFoodMapper extends MenuFoodMapper {
    List<Integer> selectFoodIdByMenuId(@Param("id") int id);
}
