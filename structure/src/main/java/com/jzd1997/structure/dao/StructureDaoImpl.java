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
			sql = "SELECT table_name 表名, \r\n" + 
					"					  COLUMN_NAME 列名,  \r\n" + 
					"					  COLUMN_TYPE 数据类型,  \r\n" + 
					"					  IS_NULLABLE 允许为空,  \r\n" + 
					"					  COLUMN_KEY 主键,\r\n" + 
					"					  COLUMN_DEFAULT 默认值,\r\n" + 
					"					  COLUMN_COMMENT 备注   \r\n" + 
					"					FROM  \r\n" + 
					"					 INFORMATION_SCHEMA.COLUMNS  \r\n" + 
					"					where  \r\n" + 
					"					table_schema =?";
			return jdbcTemplate.queryForList(sql,env.getProperty("database.name"));
		}else {
			sql = "SELECT t.table_name as 表名, \r\n" + 
					"					       t.column_name as 列名,\r\n" + 
					"					       t.DATA_TYPE || '(' || t.DATA_LENGTH || ')' as 数据类型,\r\n" + 
					"								 t.NULLABLE as 允许为空,\r\n" + 
					"								 nvl2(con.POSITION,'PRI','') 主键,\r\n" + 
					"								 t.DATA_DEFAULT as 默认值,\r\n" + 
					"					       t1.COMMENTS as 备注\r\n" + 
					"					  FROM User_Tab_Cols t\r\n" + 
					"					inner join User_Col_Comments t1\r\n" + 
					"					on t.table_name = t1.table_name\r\n" + 
					"					    AND t.column_name = t1.column_name\r\n" + 
					"					left join (\r\n" + 
					"					select cu.TABLE_NAME,cu.COLUMN_NAME,cu.POSITION\r\n" + 
					"from user_cons_columns cu, user_constraints au \r\n" + 
					"where cu.constraint_name = au.constraint_name and au.constraint_type = 'P'\r\n" + 
					"					) con on t.table_name=con.table_name and t.column_name =con.COlUMN_NAME";
			return jdbcTemplate.queryForList(sql);
		}
	}
	
}
