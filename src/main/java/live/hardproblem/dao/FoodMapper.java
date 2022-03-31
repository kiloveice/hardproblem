package live.hardproblem.dao;

import java.util.List;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.FoodExample;
import org.apache.ibatis.annotations.Param;

public interface FoodMapper {
    long countByExample(FoodExample example);

    int deleteByExample(FoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    List<Food> selectByExampleWithBLOBs(FoodExample example);

    List<Food> selectByExample(FoodExample example);

    Food selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByExampleWithBLOBs(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByExample(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKeyWithBLOBs(Food record);

    int updateByPrimaryKey(Food record);
}