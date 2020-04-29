package entity;

public class UserList extends ListOfIdentifiables<User> {
	private static final long serialVersionUID = 7115985836992230188L;

	public synchronized User findUser(String login) {
		// find user by login
		for (User user : items) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}

	public synchronized User findUser(Integer id) {
		// find user by id
		for  (User user : items) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
    //method for add new user
	public synchronized User addUser(User user) throws UserExistsException {
		if (findUser(user.getLogin()) != null)
			throw new UserExistsException();
		// choose free id for user
		user.setId(getNextId());
		items.add(user);
		return user;
	}

	public static class UserExistsException extends Exception {
		private static final long serialVersionUID = 584737643480913385L;
	}
}
