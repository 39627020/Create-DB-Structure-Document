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
			//Mysql
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
					"					table_schema =? order by table_name";
			System.out.println(sql);
			return jdbcTemplate.queryForList(sql,env.getProperty("database.name"));
		}else if("2".equals(dbType)) {
			//Oracle
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
					"					) con on t.table_name=con.table_name and t.column_name =con.COlUMN_NAME order by t.table_name";
			System.out.println(sql);
			return jdbcTemplate.queryForList(sql);
		}else{
            //Postgres
            String schema = env.getProperty("database.name");
            sql =   "SELECT c.relname as 表名,\n" + 
                    "       a.attname AS 列名,\n" +
                    "       t.typname||'('||a.attlen||')' AS 数据类型,\n" +
                    "       (CASE WHEN a.attnotnull='t' THEN 'True' ELSE 'False' END) AS 允许为空,\n" +
                    "       (CASE WHEN a2.attname = a.attname THEN 'True' ELSE '' END) AS 主键,\n" +
                    "       '' AS 默认值,\n" +
                    "       b.description AS 备注\n" +
                    "  FROM pg_attribute a\n" +
                    "       LEFT JOIN pg_description b ON a.attrelid=b.objoid AND a.attnum = b.objsubid,\n" +
                    "       pg_class c\n" +
                    "       LEFT JOIN pg_constraint pg_co ON pg_co.conrelid = c.oid AND pg_co.contype = 'p'\n" +
                    "       LEFT JOIN pg_attribute a2 ON a2.attrelid = c.oid AND a2.attnum = pg_co.conkey [ 1 ],\n" +
                    "       pg_type t\n" +
                    " WHERE c.relname in (select tablename from pg_tables where schemaname='" + schema + "')\n" +
                    "       and a.attnum > 0\n" +
                    "       and a.attrelid = c.oid\n" +
                    "       and a.atttypid = t.oid\n" +
                    "       and pg_table_is_visible (c.oid)\n" +
                    " ORDER BY c.relname,a.attnum\n";
            System.out.println(sql);
            return jdbcTemplate.queryForList(sql);
        }
	}
	
}
