package live.hardproblem.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExFoodTagMapper extends FoodTagMapper {

    List<Integer> selectFoodIdByFoodIdListAndTagIdList(@Param("foodIdList") List<Integer> foodIdList,
                                                       @Param("tagIdList") List<Integer> tagIdList,
                                                       @Param("include") boolean include,
                                                       @Param("all") boolean all);
}
