package com.str.engg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.str.engg.dao.UserDao;
import com.str.engg.model.User;
import com.str.engg.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	
	@Autowired
	 BCryptPasswordEncoder encoder;
	

	@Override
    public User save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setCompanyName(user.getCompanyName());
		return userDao.save(newUser);
    }
}
