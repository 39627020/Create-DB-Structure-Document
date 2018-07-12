package com.jzd1997.structure.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StructureDaoImpl implements IStructureDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;
	
	@Override
	public List<Map<String,Object>> findStructure() {
		String sql;
		
		String dbType = env.getProperty("database.type");
		if("1".equals(dbType)) {
			sql = "SELECT\r\n" +
					" table_name 表名," +
					"  COLUMN_NAME 列名,\r\n" + 
					"  COLUMN_TYPE 数据类型,\r\n" + 
					"  IS_NULLABLE 允许为空,\r\n" + 
					"  COLUMN_DEFAULT 默认值,\r\n" + 
					"  COLUMN_COMMENT 备注 \r\n" + 
					"FROM\r\n" + 
					" INFORMATION_SCHEMA.COLUMNS\r\n" + 
					"where\r\n" + 
					"table_schema =?";
			return jdbcTemplate.queryForList(sql,env.getProperty("database.name"));
		}else {
			sql = "SELECT t.table_name as 表名, \r\n" + 
					"					       t.colUMN_NAME as 列名,\r\n" + 
					"					       t.DATA_TYPE || '(' || t.DATA_LENGTH || ')' as 数据类型,\r\n" + 
					"								 t.NULLABLE as 允许为空,\r\n" + 
					"								 t.DATA_DEFAULT as 默认值,\r\n" + 
					"					       t1.COMMENTS as 备注\r\n" + 
					"					  FROM User_Tab_Cols t, User_Col_Comments t1\r\n" + 
					"					WHERE t.table_name = t1.table_name\r\n" + 
					"					    AND t.column_name = t1.column_name";
			return jdbcTemplate.queryForList(sql);
		}
	}
	
}
