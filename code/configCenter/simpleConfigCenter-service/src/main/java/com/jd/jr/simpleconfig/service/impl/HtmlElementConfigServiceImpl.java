package com.jd.jr.simpleconfig.service.impl;
import com.jd.jr.simpleconfig.service.HtmlElementConfigService;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
import com.jd.jr.simpleconfig.dao.HtmlElementConfigMapper;
import com.jd.jr.simpleconfig.util.exception.ErpException;
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
import org.springframework.dao.DuplicateKeyException;
@Service("htmlElementConfigService")
public class HtmlElementConfigServiceImpl implements HtmlElementConfigService{

    private static final Logger logger = LoggerFactory.getLogger(HtmlElementConfigServiceImpl.class);
    @Autowired
    private HtmlElementConfigMapper htmlElementConfigMapper;

     public List<HtmlElementConfig> findAll(){	 
	    List<HtmlElementConfig> htmlElementConfigList = htmlElementConfigMapper.findAll();
        return htmlElementConfigList;
     }

     public Long findCount(){
	Long count = htmlElementConfigMapper.findCount();
        return count;
     }

     public List<HtmlElementConfig> queryBySelective(Query<HtmlElementConfig> htmlElementConfig){
	List<HtmlElementConfig> htmlElementConfigList = htmlElementConfigMapper.queryBySelective(htmlElementConfig);       
        return htmlElementConfigList;
     }

     public Long queryCountBySelective(Query<HtmlElementConfig> htmlElementConfig){
	Long count = htmlElementConfigMapper.queryCountBySelective(htmlElementConfig);
        return count;
     }

     public HtmlElementConfig queryByPrimaryKey(Long id){
     	HtmlElementConfig htmlElementConfig = htmlElementConfigMapper.queryByPrimaryKey(id);
        return htmlElementConfig;
     }

     public Boolean deleteByPrimaryKey(Long id){
     	int result = htmlElementConfigMapper.deleteByPrimaryKey(id);
        return result == 0 ? false : true;
      
     }
 
     public Boolean updateByPrimaryKeySelective(HtmlElementConfig htmlElementConfig){
       	int result = htmlElementConfigMapper.updateByPrimaryKeySelective(htmlElementConfig);
        return result == 0 ? false : true;
     }

     public Boolean save(HtmlElementConfig htmlElementConfig) throws Exception{
        try{
            Long id = htmlElementConfigMapper.save(htmlElementConfig);
            return  id == 0 ? false : true;
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
            ErpException erpException = new ErpException("","请检查是否唯一");
            e = erpException;
        }
          throw  e;
        }
     }

     public List<HtmlElementConfig> queryBySelectiveForPagination(PageQuery<HtmlElementConfig> htmlElementConfig){
	    List<HtmlElementConfig> htmlElementConfigList = htmlElementConfigMapper.queryBySelective(htmlElementConfig);
        return htmlElementConfigList;
     }

     public Long queryCountBySelectiveForPagination(Query<HtmlElementConfig> htmlElementConfig){
	Long count = htmlElementConfigMapper.queryCountBySelective(htmlElementConfig);
        return count;
     }


        public Boolean deleteByUniqueIndexsystemNameelementName(String systemName  ,String elementName  ){
        HtmlElementConfig htmlElementConfig  = new HtmlElementConfig();
                    htmlElementConfig.setSystemName(systemName);
                    htmlElementConfig.setElementName(elementName);
               int result = htmlElementConfigMapper.deleteByUniqueIndexsystemNameelementName(htmlElementConfig);
       return result == 0 ? false : true;
    }
    

        public Boolean deleteByCommonIndexsystemName(String systemName ){
        HtmlElementConfig htmlElementConfig  = new HtmlElementConfig();
                  htmlElementConfig.setSystemName(systemName);
               int result = htmlElementConfigMapper.deleteByCommonIndexsystemName(htmlElementConfig);
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
    public Boolean batchSave(List<HtmlElementConfig> htmlElementConfigList) {
        // 事务控制
        TransactionStatus status = null;
        try {
            // 开始事务
            status = this.initTansactionStatus(transactionManager,
            TransactionDefinition.PROPAGATION_REQUIRED);
            for(HtmlElementConfig htmlElementConfig:htmlElementConfigList){
                htmlElementConfigMapper.save(htmlElementConfig);
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
        public Boolean batchDelete(List<HtmlElementConfig> htmlElementConfigList) {
            // 事务控制
            TransactionStatus status = null;
            try {
                // 开始事务
                status = this.initTansactionStatus(transactionManager,
                TransactionDefinition.PROPAGATION_REQUIRED);
                for(HtmlElementConfig htmlElementConfig:htmlElementConfigList){
                                            htmlElementConfigMapper.deleteByUniqueIndexsystemNameelementName(htmlElementConfig);
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
            public Boolean batchDeleteAndSave(List<HtmlElementConfig> htmlElementConfigList) {
                // 事务控制
                TransactionStatus status = null;
                try {
                    // 开始事务
                    status = this.initTansactionStatus(transactionManager,
                    TransactionDefinition.PROPAGATION_REQUIRED);
                    for(HtmlElementConfig htmlElementConfig:htmlElementConfigList){
                                                    htmlElementConfigMapper.deleteByUniqueIndexsystemNameelementName(htmlElementConfig);
                                                htmlElementConfigMapper.save(htmlElementConfig);
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