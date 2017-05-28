package org.xjtusicd3.partner.view;

import java.util.List;

public class Notice_NoticeView {
	private List<Notice_NoticeSystemView> systemViews;
	private List<Notice_NoticeFAQView> faqViews;
	private List<Notice_NoticeCommunityView> communityViews;
	public List<Notice_NoticeSystemView> getSystemViews() {
		return systemViews;
	}
	public void setSystemViews(List<Notice_NoticeSystemView> systemViews) {
		this.systemViews = systemViews;
	}
	public List<Notice_NoticeFAQView> getFaqViews() {
		return faqViews;
	}
	public void setFaqViews(List<Notice_NoticeFAQView> faqViews) {
		this.faqViews = faqViews;
	}
	public List<Notice_NoticeCommunityView> getCommunityViews() {
		return communityViews;
	}
	public void setCommunityViews(List<Notice_NoticeCommunityView> communityViews) {
		this.communityViews = communityViews;
	}
	
	
}
