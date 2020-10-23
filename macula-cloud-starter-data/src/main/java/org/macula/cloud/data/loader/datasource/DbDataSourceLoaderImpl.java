package org.macula.cloud.data.loader.datasource;

import org.macula.cloud.data.domain.DataSource;
import org.macula.cloud.data.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b>DbDataSourceLoaderImpl</b> 加载数据库中的DataSource
 * </p>
 */
@Component
public class DbDataSourceLoaderImpl implements DataSourceLoader {

	@Autowired
	private DataSourceRepository dataSourceRepository;

	@Override
	public DataSource loader(String dataSourceCode) {
		return dataSourceRepository.findByCode(dataSourceCode);
	}

	@Override
	public void refresh() {
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
