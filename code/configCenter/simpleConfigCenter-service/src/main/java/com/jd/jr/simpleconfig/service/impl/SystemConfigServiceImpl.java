package com.jd.jr.simpleconfig.service.impl;
import com.jd.jr.simpleconfig.service.SystemConfigService;
import com.jd.jr.simpleconfig.domain.SystemConfig;
import com.jd.jr.simpleconfig.dao.SystemConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.jd.jr.simpleconfig.util.PageQuery;
import com.jd.jr.simpleconfig.util.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.jd.jr.simpleconfig.util.exception.ErpException;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService{

    private static final Logger logger = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

    @Resource(name="systemConfigMapper")
    private SystemConfigMapper systemConfigMapper;

     public List<SystemConfig> findAll(){	 
	    List<SystemConfig> systemConfigList = systemConfigMapper.findAll();
        return systemConfigList;
     }

     public Long findCount(){
	Long count = systemConfigMapper.findCount();
        return count;
     }

     public List<SystemConfig> queryBySelective(Query<SystemConfig> systemConfig){
	List<SystemConfig> systemConfigList = systemConfigMapper.queryBySelective(systemConfig);       
        return systemConfigList;
     }

     public Long queryCountBySelective(Query<SystemConfig> systemConfig){
	Long count = systemConfigMapper.queryCountBySelective(systemConfig);
        return count;
     }

     public SystemConfig queryByPrimaryKey(Long id){
     	SystemConfig systemConfig = systemConfigMapper.queryByPrimaryKey(id);
        return systemConfig;
     }

     public Boolean deleteByPrimaryKey(Long id){
     	int result = systemConfigMapper.deleteByPrimaryKey(id);
        return result == 0 ? false : true;
      
     }
 
     public Boolean updateByPrimaryKeySelective(SystemConfig systemConfig){
       	int result = systemConfigMapper.updateByPrimaryKeySelective(systemConfig);
        return result == 0 ? false : true;
     }

     public Boolean save(SystemConfig systemConfig) throws Exception{
        try{
            Long id = systemConfigMapper.save(systemConfig);
            return  id == 0 ? false : true;
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
            ErpException erpException = new ErpException("","请检查是否唯一");
            e = erpException;
        }
          throw  e;
        }
     }

     public List<SystemConfig> queryBySelectiveForPagination(PageQuery<SystemConfig> systemConfig){
	    List<SystemConfig> systemConfigList = systemConfigMapper.queryBySelective(systemConfig);
        return systemConfigList;
     }

     public Long queryCountBySelectiveForPagination(Query<SystemConfig> systemConfig){
	Long count = systemConfigMapper.queryCountBySelective(systemConfig);
        return count;
     }


        public Boolean deleteByUniqueIndexname(String name  ){
        SystemConfig systemConfig  = new SystemConfig();
                    systemConfig.setName(name);
               int result = systemConfigMapper.deleteByUniqueIndexname(systemConfig);
       return result == 0 ? false : true;
    }
    

    

    @Autowired
    private PlatformTransactionManager transactionManager;
    protected TransactionStatus initTansactionStatus(
        PlatformTransactionManager transactionManager, int propagetion) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();// 事务定义类
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionManager.getTransaction(def);
    }


    @Override
    public Boolean batchSave(List<SystemConfig> systemConfigList) {
        // 事务控制
        TransactionStatus status = null;
        try {
            // 开始事务
            status = this.initTansactionStatus(transactionManager,
            TransactionDefinition.PROPAGATION_REQUIRED);
            for(SystemConfig systemConfig:systemConfigList){
                systemConfigMapper.save(systemConfig);
            }
            transactionManager.commit(status);
            return true;
        }catch (Exception e){
            transactionManager.rollback(status);
            logger.error("批量保存失败",e);
            return false;
        }
        }

        @Override
        public Boolean batchDelete(List<SystemConfig> systemConfigList) {
            // 事务控制
            TransactionStatus status = null;
            try {
                // 开始事务
                status = this.initTansactionStatus(transactionManager,
                TransactionDefinition.PROPAGATION_REQUIRED);
                for(SystemConfig systemConfig:systemConfigList){
                                            systemConfigMapper.deleteByUniqueIndexname(systemConfig);
                                    }
            transactionManager.commit(status);
            return true;
            }catch (Exception e){
                transactionManager.rollback(status);
                logger.error("批量删除失败",e);
                return false;
            }
            }

            @Override
            public Boolean batchDeleteAndSave(List<SystemConfig> systemConfigList) {
                // 事务控制
                TransactionStatus status = null;
                try {
                    // 开始事务
                    status = this.initTansactionStatus(transactionManager,
                    TransactionDefinition.PROPAGATION_REQUIRED);
                    for(SystemConfig systemConfig:systemConfigList){
                                                    systemConfigMapper.deleteByUniqueIndexname(systemConfig);
                                                systemConfigMapper.save(systemConfig);
                    }
                    transactionManager.commit(status);
                    return true;
                }catch (Exception e){
                    transactionManager.rollback(status);
                    logger.error("批量删除又保存失败",e);
                    return false;
                }
                }
}