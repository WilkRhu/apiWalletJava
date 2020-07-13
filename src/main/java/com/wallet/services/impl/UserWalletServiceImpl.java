package com.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.services.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService{
	
	@Autowired
	UserWalletRepository repository;
	
	@Override
	public UserWallet save(UserWallet uw) {
		return repository.save(uw);
	}
}
