package com.example.domain.repository;

import com.example.service.RoleOperation;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/28 10:24
 * @Description:
 * 2
 */
public enum RoleEnum implements RoleOperation {

    //系统管理员（有A操作权限）
    ROLE_ROOT_ADMIN{
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN:" + "has AAA permission";
        }
    },
    //订单管理员（有B操作权限）
    ROLE_ORDER_ADMIN{
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN:" + "has BBB permission";
        }
    },
    //普通用户（有C操作权限）
    ROLE_NORMAL{
        @Override
        public String op() {
            return "ROLE_NORMAL:" + "has CCC permission";
        }
    };

}
