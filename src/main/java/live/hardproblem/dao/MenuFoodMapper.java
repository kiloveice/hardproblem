package live.hardproblem.dao;

import java.util.List;
import live.hardproblem.dao.entity.MenuFood;
import live.hardproblem.dao.entity.MenuFoodExample;
import org.apache.ibatis.annotations.Param;

public interface MenuFoodMapper {
    long countByExample(MenuFoodExample example);

    int deleteByExample(MenuFoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuFood record);

    int insertSelective(MenuFood record);

    List<MenuFood> selectByExample(MenuFoodExample example);

    MenuFood selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuFood record, @Param("example") MenuFoodExample example);

    int updateByExample(@Param("record") MenuFood record, @Param("example") MenuFoodExample example);

    int updateByPrimaryKeySelective(MenuFood record);

    int updateByPrimaryKey(MenuFood record);
}