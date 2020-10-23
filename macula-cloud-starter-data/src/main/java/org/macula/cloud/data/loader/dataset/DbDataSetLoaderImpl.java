package org.macula.cloud.data.loader.dataset;

import org.macula.cloud.data.domain.DataSet;
import org.macula.cloud.data.repository.DataSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b>DbDataSetLoaderImpl</b> 加载数据库中的DataSet
 * </p>
 */
@Component
public class DbDataSetLoaderImpl implements DataSetLoader {

	@Autowired
	private DataSetRepository dataSetRepository;

	@Override
	public DataSet loader(String dataSetCode) {
		return dataSetRepository.findByCode(dataSetCode);
	}

	@Override
	public void refresh() {
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
