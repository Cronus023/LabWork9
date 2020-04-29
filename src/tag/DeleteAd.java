package tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import entity.Ad;
import entity.AdList;
import entity.User;
import helper.AdListHelper;

public class DeleteAd extends SimpleTagSupport {
	private Ad ad;

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public void doTag() throws JspException, IOException {
		String errorMessage = null;
		//get ad data
		AdList adList = (AdList) getJspContext().getAttribute("ads",
				PageContext.APPLICATION_SCOPE);
		//get data of current user
		User currentUser = (User) getJspContext().getAttribute("authUser",
				PageContext.SESSION_SCOPE);
		if (currentUser == null
				|| (ad.getId() > 0 && ad.getAuthorId() != currentUser.getId())) {
			errorMessage = "�� ��������� �������� ���������, � �������� �� ��������� ������� �������!";
		}
		if (errorMessage == null) {
			adList.deleteAd(ad);
			AdListHelper.saveAdList(adList);
		}
		//save error message
		getJspContext().setAttribute("errorMessage", errorMessage,
				PageContext.SESSION_SCOPE);
	}
}