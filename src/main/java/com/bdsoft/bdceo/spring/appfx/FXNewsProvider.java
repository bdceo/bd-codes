package com.bdsoft.bdceo.spring.appfx;

import java.util.Date;

//@Component
public class FXNewsProvider {

//	@Autowired
	private IFXNewsListener newsListener;
//	@Autowired
	private IFXNewsPersister newsPersister;

	private String clientId;
	private String baseUrl;
	private Date addDate;

	public void getAndPersistNews() {
		System.out.println("FXNewsProvider-->getAndPersistNews");
		String[] newsIds = newsListener.getAvaliableNewsIds();
		if (newsIds == null) {
			return;
		}
		for (String newsId : newsIds) {
			FXNewsBean newsBean = newsListener.getNewsByPK(newsId);
			newsPersister.persistNews(newsBean);
			newsListener.postProcessIfNecessary(newsId);
		}
	}

	public FXNewsProvider() {
		super();
	}

	// 构造函数注入
	public FXNewsProvider(IFXNewsListener listener, IFXNewsPersister persister) {
		this.newsListener = listener;
		this.newsPersister = persister;
	}

	// setter方法注入
	public void setNewsListener(IFXNewsListener newsListener) {
		this.newsListener = newsListener;
	}

	public void setNewsPersister(IFXNewsPersister newsPersister) {
		this.newsPersister = newsPersister;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
 
}
