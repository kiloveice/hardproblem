package live.hardproblem.dao;

import live.hardproblem.dao.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface ExAccountMapper extends AccountMapper {
    public Account selectByUsername(@Param("username") String username,
                                    @Param("all") boolean all);
}
