package online.qiqiang.toybricks.core.vo.helper;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import online.qiqiang.toybricks.core.vo.UserVO;
import online.qiqiang.toybricks.dal.entity.UserEntity;

import java.time.LocalDateTime;

/**
 * @author quince
 */
public class UserVoHelper {

    public static UserVO entityToVo(UserEntity entity) {
        UserVO userVO = new UserVO();
        userVO.setId(IdUtil.getSnowflakeNextId());
        userVO.setAddress(entity.getAddress());
        userVO.setUsername(entity.getUsername());
        userVO.setEmail(entity.getEmail());
        userVO.setGender(entity.getGender());
        userVO.setStatus(entity.getStatus());
        UserVO.User user = new UserVO.User();
        UserVO.User.Detail detail = new UserVO.User.Detail();
        detail.setAge(entity.getAge());
        user.setDetail(detail);
        userVO.setUser(user);
        userVO.setAvatar("https://qiqiang.oss-cn-hangzhou.aliyuncs.com/muan/avatar-jianchun.jpg");
        userVO.setIdCard(entity.getIdCard());
        return userVO;
    }

    public static UserEntity createUser(int i) {
        UserEntity userEntity = new UserEntity();
        // userEntity.setId(IdUtil.getSnowflakeNextId());
        userEntity.setAddress("江南大道第" + i + "号");
        userEntity.setUsername("批量机器人" + i);
        userEntity.setEmail("robot" + i + "@toy-bricks.com");
        userEntity.setGender(i % 2 + 1);
        userEntity.setStatus(RandomUtil.randomInt(0, 3));
        userEntity.setAge(i);
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setAvatar("https://qiqiang.oss-cn-hangzhou.aliyuncs.com/muan/avatar-jianchun.jpg");
        userEntity.setIdCard("33010520220101112" + i % 10);
        return userEntity;
    }
}
