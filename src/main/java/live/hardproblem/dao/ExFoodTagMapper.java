package live.hardproblem.dao;

import live.hardproblem.dao.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExFoodTagMapper extends FoodTagMapper {

    //仅仅支持include=true
    List<Integer> selectFoodIdByFoodIdListAndTagIdList(@Param("foodIdList") List<Integer> foodIdList,
                                                       @Param("tagIdList") List<Integer> tagIdList,
                                                       @Param("include") boolean include,
                                                       @Param("all") boolean all);

    List<Integer> selectFoodIdByFoodIdListByTagReverse(@Param("menuId") Integer menuId,
                                                       @Param("tagIdList") List<Integer> tagIdList,
                                                       @Param("all") boolean all);

    List<Tag> getTagByFoodId(@Param("foodId") Integer foodId,@Param("all") boolean all);
}
