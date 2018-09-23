package com.jzd1997.structure.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jzd1997.structure.dao.IStructureDao;
import com.jzd1997.structure.util.ExportExcelUtils;
import com.jzd1997.structure.util.MyUtils;

@Service
public class MainServiceImpl implements IMainService{
	@Autowired
	IStructureDao structureDao;
	
	@Autowired
	private Environment env;
	
	@Override
	public boolean findStructure() {
		List<Map<String,Object>> list = structureDao.findStructure();
		Map<String,List<List<String>>> sheets = new LinkedHashMap<String,List<List<String>>>();
		String tbl = "";
		// 根据每列数据进行循环
		List<List<String>> rows = null;
		List<String> cols = null;
		for(Map<String,Object> record:list) {
			if(!tbl.equals(MyUtils.toString(record.get("表名")))) {
				if(!"".equals(tbl)) {
					sheets.put(tbl, rows);
				}
				tbl = MyUtils.toString(record.get("表名"));
				rows = new ArrayList<List<String>>();
			}
			cols = new ArrayList<String>();

			cols.add(MyUtils.toString(record.get("列名")));
			cols.add(MyUtils.toString(record.get("数据类型")));
			cols.add(MyUtils.toString(record.get("允许为空")));
			cols.add(MyUtils.toString(record.get("主键")));
			cols.add(MyUtils.toString(record.get("默认值")));
			cols.add(MyUtils.toString(record.get("备注")));
			rows.add(cols);
		}
		sheets.put(tbl, rows);
		String fname = env.getProperty("output.path") + env.getProperty("database.name") + ".xlsx";
	    ExportExcelUtils.createExcel(sheets,fname);
		return true;
	}

}
