﻿package queryParser.test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;

import queryParser.parser.FromParser;
import queryParser.vo.TableInfo;
import queryParser.vo.TableViewType;

public class FromParserTest {
	
	FromParser fromParser = new FromParser(null, null);

	@Test
	public void simple_FromStmt_Test() throws Exception {
		String fromStmt = "EMP, DEPT";
		
		 List<TableViewType> fromStmtInfoList = fromParser.parsingFromStatement(fromStmt);
		 
		 TableInfo tableInfo1 = new TableInfo();
		 tableInfo1.setTableName("EMP");
		 
		 TableInfo tableInfo2 = new TableInfo();
		 tableInfo2.setTableName("DEPT");
		 
		 assertThat(fromStmtInfoList, 
				 IsIterableContainingInAnyOrder.<TableViewType>containsInAnyOrder(tableInfo1, tableInfo2));
	}
	
	@Test
	public void alias_FromStmt_Test() throws Exception {
		String fromStmt = "EMP E, DEPT D";
		
		 List<TableViewType> fromStmtInfoList = fromParser.parsingFromStatement(fromStmt);
		 
		 TableInfo tableInfo1 = new TableInfo();
		 tableInfo1.setTableName("EMP");
		 tableInfo1.setAlias("E");
		 
		 TableInfo tableInfo2 = new TableInfo();
		 tableInfo2.setTableName("DEPT");
		 tableInfo2.setAlias("D");
		 
		 assertThat(fromStmtInfoList, 
				 IsIterableContainingInAnyOrder.<TableViewType>containsInAnyOrder(tableInfo1, tableInfo2));
	}

}
