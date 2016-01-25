package warp.common.util;

import java.sql.ResultSet;
import java.util.List;

public interface IQueryHandler {
	public List onQueryResult(ResultSet rs);
}
