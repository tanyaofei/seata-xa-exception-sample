package com.example;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author tanyaofei
 * @since 2024/4/18
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final SQLHelper sqlHelper;
    private final TransactionTemplate tx;

    public UserServiceImpl(UserRepository repo, SQLHelper sqlHelper, TransactionTemplate tx) {
        this.repo = repo;
        this.sqlHelper = sqlHelper;
        this.tx = tx;
    }

    @Override
    public User createUser() {
        var user = new User();
        user.setId(System.currentTimeMillis());
        user.setUsername("username");
        user.setPassword("password");
        repo.insert(user);
        return user;
    }

    @Override
    @GlobalTransactional(timeoutMills = 1_000)
    public void updateUser(Long id) {
        repo.updateUserName(id, "username_1");
        tx.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                sqlHelper.setInnodbLockWaitTimeout(1500);           // 给足够的时间让 seata 完成回滚
                repo.updatePassword(id, "password_1");     // 死锁卡住, 直至全局事务超时完成回滚
            }
        });
    }

    @Override
    public User getUser(Long id) {
        return repo.selectById(id);
    }

}
