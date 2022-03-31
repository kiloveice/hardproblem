package live.hardproblem.dao;

import java.util.List;
import live.hardproblem.dao.entity.FoodTag;
import live.hardproblem.dao.entity.FoodTagExample;
import org.apache.ibatis.annotations.Param;

public interface FoodTagMapper {
    long countByExample(FoodTagExample example);

    int deleteByExample(FoodTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FoodTag record);

    int insertSelective(FoodTag record);

    List<FoodTag> selectByExample(FoodTagExample example);

    FoodTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FoodTag record, @Param("example") FoodTagExample example);

    int updateByExample(@Param("record") FoodTag record, @Param("example") FoodTagExample example);

    int updateByPrimaryKeySelective(FoodTag record);

    int updateByPrimaryKey(FoodTag record);
}