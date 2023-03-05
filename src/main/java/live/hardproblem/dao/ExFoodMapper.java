package live.hardproblem.dao;

import live.hardproblem.dao.entity.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExFoodMapper extends FoodMapper {

    List<Food> selectAll(@Param("start") int page, @Param("end") int num, @Param("all") boolean all);

    List<Integer> getAllId(@Param("all") boolean all);

    List<Food> selectInFoodList(@Param("foodIdList") List<Integer> foodIdList,
                                @Param("all") boolean all);

    Integer removeById(@Param("id") int id);
}
