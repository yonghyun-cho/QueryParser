package queryParser.executor;

import java.io.File;
import java.util.Map;

import queryParser.parser.BracketDistributor;
import queryParser.parser.TempQueryInput;
import queryParser.vo.QueryFactory;
import queryParser.vo.QueryInfo;
import tempMain.ParsingTest;

public class Executor {
	public static QueryFactory execute() throws Exception{
		File path1 = new File("testQuery.txt");
		
		// 1. read QueryString
		String queryString = TempQueryInput.readQueryTextFile(path1.getAbsolutePath());
		
		// 2. seperate bracket(parenthesis) "(, )"
		BracketDistributor bracketDistributor = new BracketDistributor();
		bracketDistributor.splitSubQuery(queryString);
		
		// 분리된 SubQuery 목록 
		Map<String, QueryInfo> queryMap = bracketDistributor.getQueryMap();
		// 분리된 함수 목록 
		Map<String, String> functionMap = bracketDistributor.getFunctionMap();
		// 분리된 기타 (연산자 관련 소괄호)
		Map<String, String> otherBracketMap = bracketDistributor.getOtherBracketMap();
		
		// 3. set QueryFactory
		QueryFactory visualQueryInfo = new QueryFactory(queryMap, functionMap, otherBracketMap);
		
		return visualQueryInfo;
	}
}
