package com.zenglm.dts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author zeng_limin@163.com
 * @date 2014年11月28日 上午9:42:50
 *
 */
public abstract class AbstractBatchDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractBatchDao.class);

	private static final int MAXSZIE = 1000;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	protected void updateBatch(List<?> parameterObjectArray, String statementId) {
		if (parameterObjectArray == null || parameterObjectArray.isEmpty())
			return;
		int size = parameterObjectArray.size();
		SqlSession session = sqlSessionFactory.openSession();
		Configuration configuration = sqlSessionFactory.getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(statementId);
		Connection conn = session.getConnection();

		BoundSql boundSql = ms.getBoundSql(parameterObjectArray.get(0));
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(boundSql.getSql());
			if (size > MAXSZIE) {
				int fromIndex = 0, toIndex = 0;
				while (fromIndex < size) {
					toIndex = fromIndex + MAXSZIE;
					updateBatch(parameterObjectArray.subList(fromIndex,
							toIndex > size ? size : toIndex),
							parameterMappings, boundSql, configuration, ps);
					fromIndex = toIndex;
				}
			} else {
				updateBatch(parameterObjectArray, parameterMappings, boundSql,
						configuration, ps);
			}
		} catch (SQLException e) {
			logger.error("", e);
			throw new RuntimeException("batch exception:" + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("", e);
				}
			}
		}

		Cache cache = ms.getCache();
		if (cache != null)
			cache.clear();
	}

	@SuppressWarnings("all")
	private void updateBatch(List<?> parameterObjectArray,
			List<ParameterMapping> parameterMappings, BoundSql boundSql,
			Configuration configuration, PreparedStatement ps)
			throws SQLException {
		try {
			for (Object parameterObject : parameterObjectArray) {
				if (parameterMappings != null) {
					for (int i = 0; i < parameterMappings.size(); i++) {
						ParameterMapping parameterMapping = parameterMappings
								.get(i);
						if (parameterMapping.getMode() != ParameterMode.OUT) {
							Object value;
							String propertyName = parameterMapping
									.getProperty();
							if (boundSql.hasAdditionalParameter(propertyName)) {
								value = boundSql
										.getAdditionalParameter(propertyName);
							} else if (parameterObject == null) {
								value = null;
							} else if (configuration.getTypeHandlerRegistry()
									.hasTypeHandler(parameterObject.getClass())) {
								value = parameterObject;
							} else {
								MetaObject metaObject = configuration
										.newMetaObject(parameterObject);
								value = metaObject.getValue(propertyName);
							}
							TypeHandler typeHandler = parameterMapping
									.getTypeHandler();
							JdbcType jdbcType = parameterMapping.getJdbcType();
							if (value == null && jdbcType == null)
								jdbcType = configuration.getJdbcTypeForNull();
							typeHandler
									.setParameter(ps, i + 1, value, jdbcType);
						}
					}
				}
				ps.addBatch();
			}
			ps.executeBatch();

		} catch (SQLException e) {
			throw e;
		}
	}

}
