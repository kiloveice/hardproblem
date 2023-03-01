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


    protected boolean checkSignUp(Account account) {
        if (account.getUsername().length() < 1 || account.getUsername().length() > 20) {
            return false;
        }
        if (account.getNickname().length() < 1 || account.getNickname().length() > 20) {
            return false;
        }
        if (account.getPassword().length() < 6 || account.getPassword().length() > 20) {
            return false;
        }
        return true;
    }

    public int insert(Account account) {
        if (!checkSignUp(account)) {
            return 0;
        }
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

    public String hashPasswordBySalt(String password, String salt) {
        return shaHash(password, salt);
    }

    public Account getAccountSafeByAccount(Account account){
        account.setId(null);
        account.setPassword(null);
        account.setSalt(null);
        account.setCreateTime(null);
        account.setUpdateTime(null);
        return account;
    }
}
