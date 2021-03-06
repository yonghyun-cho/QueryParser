﻿package queryParser.vo;

public class TableViewType extends QueryComponentType {
	// 테이블(뷰) ID(이름)
	protected String tableViewId = "";
	
	// 테이블(뷰) alias
	protected String tableViewAlias = "";
	
	public static boolean isTableViewType(String value) throws Exception{
		return TableInfo.isTableType(value) || SubQueryInfo.isSubQueryId(value);
	}
	
	public static TableViewType convertStringToType(String value) throws Exception{
		TableViewType tableViewType = null;
		
		if(SubQueryInfo.isSubQueryId(value)){
			tableViewType = SubQueryInfo.convertStringToInfo(value);
			
		}else if(TableInfo.isTableType(value)){
			tableViewType = TableInfo.convertStringToInfo(value);
			
		}else{
			throw new Exception("잘못된 TableViewType 입니다.");
		}
		
		return tableViewType;
	}
}
