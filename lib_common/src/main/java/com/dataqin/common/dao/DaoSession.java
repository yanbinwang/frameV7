package com.dataqin.common.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.dataqin.common.model.SlicingDBModel;

import com.dataqin.common.dao.SlicingDBModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig slicingDBModelDaoConfig;

    private final SlicingDBModelDao slicingDBModelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        slicingDBModelDaoConfig = daoConfigMap.get(SlicingDBModelDao.class).clone();
        slicingDBModelDaoConfig.initIdentityScope(type);

        slicingDBModelDao = new SlicingDBModelDao(slicingDBModelDaoConfig, this);

        registerDao(SlicingDBModel.class, slicingDBModelDao);
    }
    
    public void clear() {
        slicingDBModelDaoConfig.clearIdentityScope();
    }

    public SlicingDBModelDao getSlicingDBModelDao() {
        return slicingDBModelDao;
    }

}
