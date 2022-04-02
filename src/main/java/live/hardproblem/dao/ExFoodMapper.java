package live.hardproblem.dao;

import live.hardproblem.dao.entity.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExFoodMapper extends FoodMapper {

    List<Food> selectAll(@Param("page") int page, @Param("num") int num);
}