package queryParser.vo;

import java.util.HashMap;
import java.util.Map;

import queryParser.parser.QueryParser;

public class QueryFactory {
	
	/** SubQuery HashMap (Query ID, QueryInfo) */
	private Map<String, QueryInfo> queryMap = new HashMap<String, QueryInfo>();
	
	// QueryParser
	private QueryParser queryParser = null;
	
	public QueryFactory() { }
	
	public QueryFactory(Map<String, QueryInfo> queryMap, Map<String, String> functionMap, Map<String, String> otherBracketMap) {
		this.queryMap = queryMap;
		
		this.queryParser = new QueryParser(functionMap, otherBracketMap);
	}

	public QueryInfo getQueryInfo(String queryId) throws Exception{
		QueryInfo queryInfo = queryMap.get(queryId);
		
		if(queryInfo != null){
			if(queryInfo.isParsed() == false){
				// 내부에서는 Statment Info만 추가로 설정해 준다.
				queryInfo = queryParser.setQueryStmtInfo(queryInfo);
			}
			
		} else {
			throw new Exception("QueryID [" + queryId + "] is NOT exist");
		}
		
		return queryInfo;
	}
	
	public QueryInfo getMainQueryInfo() throws Exception{
		// TODO 나중에 _SUBQUERY_TEMP를 공통 변수로 만들 것.
		return this.getQueryInfo("0_SUBQUERY_TEMP");
	}
	
	public Map<String, QueryInfo> getAllQueryInfo() throws Exception{
		for(String queryId: queryMap.keySet()){
			this.getQueryInfo(queryId);
		}
		
		return this.queryMap;
	}
	
	public void printAllQueryInfo() throws Exception{
		// Sub Query 츨력
		for(String subQueryId: queryMap.keySet()){
			QueryInfo queryInfo = queryMap.get(subQueryId);
			queryParser.setQueryStmtInfo(queryInfo);
			
			System.out.println("<< Map key: " + subQueryId + ">>");
			queryInfo.printQueryStructure();
		}
	}
}
