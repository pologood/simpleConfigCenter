package com.jd.jr.simpleconfig.service.impl;
import com.jd.jr.simpleconfig.service.DataConfigService;
import com.jd.jr.simpleconfig.domain.DataConfig;
import com.jd.jr.simpleconfig.dao.DataConfigMapper;
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

@Service("dataConfigService")
public class DataConfigServiceImpl implements DataConfigService{

    private static final Logger logger = LoggerFactory.getLogger(DataConfigServiceImpl.class);

    @Resource(name="dataConfigMapper")
    private DataConfigMapper dataConfigMapper;

     public List<DataConfig> findAll(){	 
	    List<DataConfig> dataConfigList = dataConfigMapper.findAll();
        return dataConfigList;
     }

     public Long findCount(){
	Long count = dataConfigMapper.findCount();
        return count;
     }

     public List<DataConfig> queryBySelective(Query<DataConfig> dataConfig){
	List<DataConfig> dataConfigList = dataConfigMapper.queryBySelective(dataConfig);       
        return dataConfigList;
     }

     public Long queryCountBySelective(Query<DataConfig> dataConfig){
	Long count = dataConfigMapper.queryCountBySelective(dataConfig);
        return count;
     }

     public DataConfig queryByPrimaryKey(Long id){
     	DataConfig dataConfig = dataConfigMapper.queryByPrimaryKey(id);
        return dataConfig;
     }

     public Boolean deleteByPrimaryKey(Long id){
     	int result = dataConfigMapper.deleteByPrimaryKey(id);
        return result == 0 ? false : true;
      
     }
 
     public Boolean updateByPrimaryKeySelective(DataConfig dataConfig){
       	int result = dataConfigMapper.updateByPrimaryKeySelective(dataConfig);
        return result == 0 ? false : true;
     }

     public Boolean save(DataConfig dataConfig) throws Exception{
        try{
            Long id = dataConfigMapper.save(dataConfig);
            return  id == 0 ? false : true;
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
            ErpException erpException = new ErpException("","请检查是否唯一");
            e = erpException;
        }
          throw  e;
        }
     }

     public List<DataConfig> queryBySelectiveForPagination(PageQuery<DataConfig> dataConfig){
	    List<DataConfig> dataConfigList = dataConfigMapper.queryBySelective(dataConfig);
        return dataConfigList;
     }

     public Long queryCountBySelectiveForPagination(Query<DataConfig> dataConfig){
	Long count = dataConfigMapper.queryCountBySelective(dataConfig);
        return count;
     }


        public Boolean deleteByUniqueIndexsystemNamedataKey(String systemName  ,String dataKey  ){
        DataConfig dataConfig  = new DataConfig();
                    dataConfig.setSystemName(systemName);
                    dataConfig.setDataKey(dataKey);
               int result = dataConfigMapper.deleteByUniqueIndexsystemNamedataKey(dataConfig);
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
    public Boolean batchSave(List<DataConfig> dataConfigList) {
        // 事务控制
        TransactionStatus status = null;
        try {
            // 开始事务
            status = this.initTansactionStatus(transactionManager,
            TransactionDefinition.PROPAGATION_REQUIRED);
            for(DataConfig dataConfig:dataConfigList){
                dataConfigMapper.save(dataConfig);
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
        public Boolean batchDelete(List<DataConfig> dataConfigList) {
            // 事务控制
            TransactionStatus status = null;
            try {
                // 开始事务
                status = this.initTansactionStatus(transactionManager,
                TransactionDefinition.PROPAGATION_REQUIRED);
                for(DataConfig dataConfig:dataConfigList){
                                            dataConfigMapper.deleteByUniqueIndexsystemNamedataKey(dataConfig);
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
            public Boolean batchDeleteAndSave(List<DataConfig> dataConfigList) {
                // 事务控制
                TransactionStatus status = null;
                try {
                    // 开始事务
                    status = this.initTansactionStatus(transactionManager,
                    TransactionDefinition.PROPAGATION_REQUIRED);
                    for(DataConfig dataConfig:dataConfigList){
                                                    dataConfigMapper.deleteByUniqueIndexsystemNamedataKey(dataConfig);
                                                dataConfigMapper.save(dataConfig);
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