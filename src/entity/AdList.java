package entity;

import java.util.HashSet;

public class AdList extends ListOfIdentifiables<Ad> {
	private static final long serialVersionUID = 882150501461356499L;

	//add message
	public synchronized Ad addAd(User author, Ad ad) {
		ad.setAuthorId(author.getId());
		ad.setAuthor(author);
		//choose free id for new message
		ad.setId(getNextId());
		items.add(ad);
		return ad;
	}

	// refresh message
	public synchronized void updateAd(Ad ad) {
		items.add(ad);
	}

	// delete message
	public synchronized void deleteAd(Ad ad) {
		items.remove(ad);
	}

	// get Clone list of message
	@SuppressWarnings("unchecked")
	public synchronized HashSet<Ad> getAds() {
		// Clone for synchronization
		return (HashSet<Ad>) items.clone();
	}
}
