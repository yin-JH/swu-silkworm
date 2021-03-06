package cn.edu.swu.mapper;

import cn.edu.swu.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    public Account getOneAccount(@Param("accountName") String accountName, @Param("accountPassword") String accountPassword);
}
