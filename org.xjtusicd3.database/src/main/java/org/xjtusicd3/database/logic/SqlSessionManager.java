package org.xjtusicd3.database.logic;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.xjtusicd3.database.mapper.AdvisePersistenceMapper;
import org.xjtusicd3.database.mapper.AgreePersistenceMapper;
import org.xjtusicd3.database.mapper.AnswerPersistenceMapper;
import org.xjtusicd3.database.mapper.ClassifyPersistenceMapper;
import org.xjtusicd3.database.mapper.CollectionPersistenceMapper;
import org.xjtusicd3.database.mapper.CommentPersistenceMapper;
import org.xjtusicd3.database.mapper.CommunityAnswerPersistenceMapper;
import org.xjtusicd3.database.mapper.CommunityQuestionPersistenceMapper;
import org.xjtusicd3.database.mapper.ComputerPersistenceMapper;
import org.xjtusicd3.database.mapper.ConfigureHistoryPersistenceMapper;
import org.xjtusicd3.database.mapper.ConfigurePersistenceMapper;
import org.xjtusicd3.database.mapper.CurrentConfigurePersistenceMapper;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;
import org.xjtusicd3.database.mapper.DriversPersistenceMapper;
import org.xjtusicd3.database.mapper.EquipmentPersistenceMapper;
import org.xjtusicd3.database.mapper.ITPersistenceMapper;
import org.xjtusicd3.database.mapper.LogPersistenceMapper;
import org.xjtusicd3.database.mapper.LogPersistenceMapper;
import org.xjtusicd3.database.mapper.MessageHistoryPersistenceMapper;
import org.xjtusicd3.database.mapper.MessagePersistenceMapper;
import org.xjtusicd3.database.mapper.MessagePersistenceMapper;
import org.xjtusicd3.database.mapper.PatchPersistenceMapper;
import org.xjtusicd3.database.mapper.PayPersistenceMapper;
import org.xjtusicd3.database.mapper.PermissionPersistenceMapper;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.mapper.RobotPersistenceMapper;
import org.xjtusicd3.database.mapper.RolePermissionPersistenceMapper;
import org.xjtusicd3.database.mapper.RolePersistenceMapper;
import org.xjtusicd3.database.mapper.ScorePersistenceMapper;
import org.xjtusicd3.database.mapper.ServerPersistenceMapper;
import org.xjtusicd3.database.mapper.SharePersistenceMapper;
import org.xjtusicd3.database.mapper.SoftPersistenceMapper;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.mapper.User_Equipment_HistoryPersistenceMapper;
import org.xjtusicd3.database.model.RolePermissionPersistence;



public class SqlSessionManager {
	private static SqlSessionFactory bizSqlSessionFactory;
	public static SqlSessionFactory getSqlSessionFactory()  {
		if (bizSqlSessionFactory==null) {
			try {
				bizSqlSessionFactory = convertSqlSession(new Dbconfig("/databaseconfig.properties"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bizSqlSessionFactory;
	}
	
	private static SqlSessionFactory convertSqlSession(Dbconfig dbConfig) throws Exception { 
		
			DataSource dataSource = ConnectionManager.getProxoolDataSource(dbConfig.getDriver(), dbConfig.getUrl(), dbConfig.getUsername(),
					dbConfig.getPassword(), dbConfig.getAlias(), dbConfig.getMaxConnection(), dbConfig.getMinConnection(),
					dbConfig.getSimultaneousBuildThrottle());
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration configuration = new Configuration(environment);
			configuration.addMapper(IBaseDao.class);
			configuration.addMapper(UserPersistenceMapper.class);
			configuration.addMapper(ConfigurePersistenceMapper.class);
			configuration.addMapper(ConfigureHistoryPersistenceMapper.class);
			configuration.addMapper(SoftPersistenceMapper.class);
			configuration.addMapper(DriversPersistenceMapper.class);
			configuration.addMapper(PatchPersistenceMapper.class);
			configuration.addMapper(QuestionPersistenceMapper.class);
			configuration.addMapper(AnswerPersistenceMapper.class);
			configuration.addMapper(ClassifyPersistenceMapper.class);
			configuration.addMapper(RobotPersistenceMapper.class);
			configuration.addMapper(AdvisePersistenceMapper.class);
			configuration.addMapper(LogPersistenceMapper.class);
			configuration.addMapper(PermissionPersistenceMapper.class);
			configuration.addMapper(CommunityQuestionPersistenceMapper.class);
			configuration.addMapper(CommunityAnswerPersistenceMapper.class);
			configuration.addMapper(ComputerPersistenceMapper.class);
			configuration.addMapper(UserQuestionPersistenceMapper.class);
			configuration.addMapper(EquipmentPersistenceMapper.class);
			configuration.addMapper(CurrentConfigurePersistenceMapper.class);
			configuration.addMapper(CurrentEquipmentPersistenceMapper.class);
			configuration.addMapper(User_Equipment_HistoryPersistenceMapper.class);
			configuration.addMapper(ITPersistenceMapper.class);
			configuration.addMapper(PayPersistenceMapper.class);
			configuration.addMapper(CommentPersistenceMapper.class);
			configuration.addMapper(AgreePersistenceMapper.class);
			configuration.addMapper(RolePersistenceMapper.class);
			configuration.addMapper(CollectionPersistenceMapper.class);
			configuration.addMapper(ScorePersistenceMapper.class);
			configuration.addMapper(ServerPersistenceMapper.class);
			configuration.addMapper(RolePermissionPersistenceMapper.class);
			configuration.addMapper(MessagePersistenceMapper.class);
			configuration.addMapper(MessageHistoryPersistenceMapper.class);
			configuration.addMapper(SharePersistenceMapper.class);
			configuration.addInterceptor(new BasePlugin());
			bizSqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return bizSqlSessionFactory;
	}
}
