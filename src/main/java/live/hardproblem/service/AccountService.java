package live.hardproblem.service;

import live.hardproblem.dao.ExAccountMapper;
import live.hardproblem.dao.entity.Account;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountService {
    @Autowired
    SecureRandomNumberGenerator secureRandomNumberGenerator;
    @Autowired
    ExAccountMapper accountMapper;
    @Autowired
    Environment env;

    protected void insert_fill(Account account) {
        account.setId(null);
        account.setStatus(true);
        account.setCreateTime(null);
        account.setUpdateTime(null);
    }

    protected void update_fill(Account account) {
        account.setUpdateTime(null);
    }

    protected String generateSalt() {
        return secureRandomNumberGenerator.nextBytes().toHex();
    }

    protected String shaHash(String password, String salt) {
        Integer turns = Integer.parseInt((Objects.requireNonNull(env.getProperty("shiro.turns"))));
        return new Sha256Hash(password, salt, turns).toBase64();
    }

    public int insert(Account account) {
        account.setSalt(generateSalt());
        account.setPassword(shaHash(account.getPassword(), account.getSalt()));
        insert_fill(account);
        return accountMapper.insert(account);
    }

    public Account getByUsername(String username) {
        return accountMapper.selectByUsername(username, false);
    }

    public String hashPasswordByUsername(String username, String password) {
        Account account = getByUsername(username);
        return shaHash(password, account.getSalt());
    }
}
