package com.readbean.shiro.conf;


import com.readbean.shiro.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.UUID;


public class RedisSessionDao extends EnterpriseCacheSessionDAO {
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        RedisUtil.setObject(SerializationUtils.serialize(sessionId), SerializationUtils.serialize(session));
        return sessionId;
    }

    @Override
    protected void doDelete(Session session) {
        if(session == null || session.getId() == null) {
            return;
        }
        RedisUtil.removeObject(SerializationUtils.serialize(session.getId()));
    }
}
