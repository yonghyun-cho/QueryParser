package queryParser.vo;

import java.util.ArrayList;
import java.util.List;

import queryParser.Comm.QueryParserCommFunc;

public class SubQueryInfo extends TableViewType {
	
	public String getCurrentQueryId() {
		return tableViewId;
	}

	public void setCurrentQueryId(String currentQueryId) {
		this.tableViewId = currentQueryId;
	}
	
	public String getAlias() {
		return tableViewAlias;
	}

	public void setAlias(String alias) {
		this.tableViewAlias = alias;
	}

	public static boolean isSubQueryId(String value) throws Exception{
		List<String> regexList = new ArrayList<String>();
		regexList.add("^[0-9]+_SUBQUERY_[a-zA-Z]+ \".+\""); // ex) #_SUBQUERY_000 "TAB table"
		regexList.add("^[0-9]+_SUBQUERY_[a-zA-Z]+[ ]+[a-zA-Z][a-zA-Z0-9]*$"); // ex) #_SUBQUERY_000 SUB1
		regexList.add("^[0-9]+_SUBQUERY_[a-zA-Z]+$"); // // ex) #_SUBQUERY_000
		
		return QueryParserCommFunc.isMatched(value, regexList);
	}
	
	public static boolean isSubQueryText(String value){
		String trimmedValue = value.trim();
		
		// remove parenthesis "(", ")"
		if(trimmedValue.startsWith("(")){
			trimmedValue = trimmedValue.substring(1);
		} else{
			return false;
		}
		
		if(trimmedValue.endsWith(")")){
			trimmedValue = trimmedValue.substring(0, trimmedValue.length() - 2);
		} else {
			return false;
		}

		return QueryInfo.isQueryType(trimmedValue.trim());
	}
	
	public static SubQueryInfo convertStringToInfo(String value) throws Exception{
		SubQueryInfo subQueryInfo = new SubQueryInfo();
		
		value = QueryParserCommFunc.trimWhiteSpace(value);
		
		String[] splitedValue = value.split(" ");
		
		if(splitedValue.length == 2){
			subQueryInfo.setCurrentQueryId(splitedValue[0].trim()); // table name
			subQueryInfo.setAlias(splitedValue[1].trim()); // alias
			
		}else if(splitedValue.length == 1){
			subQueryInfo.setCurrentQueryId(splitedValue[0].trim()); // table name
			
		}else{
			throw new Exception("Inappropriate SubQuery Type String format : [" + value + "]");
		}
		
		return subQueryInfo;
	}
	
	public String toString(){
		return "Subquery ID : [" + this.tableViewId + "] // Alias : ["+ this.tableViewAlias + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = super.equals(obj);
		
		if(super.equals(obj) == false){
			if(obj instanceof SubQueryInfo){
				SubQueryInfo subQueryInfo = (SubQueryInfo)obj;
				
				result = subQueryInfo.getCurrentQueryId().equals(this.tableViewId);
				result = subQueryInfo.getAlias().equals(this.tableViewAlias) && result;
			}
		}
		
		return result;
	}
}
